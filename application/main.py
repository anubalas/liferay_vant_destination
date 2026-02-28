import logging
import uuid
from enum import Enum
from fastapi import FastAPI, HTTPException, Depends
from pydantic import BaseModel, Field
from sqlalchemy import create_engine, Column, Integer, String, Float
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.orm import sessionmaker, Session
import os

# Database setup
DATABASE_URL = os.getenv("DATABASE_URL")
engine = create_engine(DATABASE_URL)
SessionLocal = sessionmaker(autocommit=False, autoflush=False, bind=engine)
Base = declarative_base()

# Logging setup
logging.basicConfig(level=logging.INFO)
logger = logging.getLogger(__name__)

# User Roles
class UserRole(Enum):
    MANAGER = 'Manager'
    STAFF = 'Staff'
    VIEWER = 'Viewer'

# User Class
class User:
    def __init__(self, username: str, role: UserRole):
        self.username = username
        self.role = role

# Permission Service Class
class PermissionService:
    def can_create_item(self, user: User) -> bool:
        return user.role == UserRole.MANAGER

    def can_edit_item(self, user: User) -> bool:
        return user.role == UserRole.MANAGER

    def can_view_item(self, user: User) -> bool:
        return user.role in [UserRole.MANAGER, UserRole.STAFF, UserRole.VIEWER]

# Models
class Product(Base):
    __tablename__ = "products"
    id = Column(String, primary_key=True, index=True, default=str(uuid.uuid4()))
    name = Column(String, unique=True, index=True)
    price = Column(Float)

class Inventory(Base):
    __tablename__ = "inventory"
    id = Column(Integer, primary_key=True, index=True)
    product_id = Column(String)
    count = Column(Integer, default=0)

Base.metadata.create_all(bind=engine)

# Pydantic models
class ProductCreate(BaseModel):
    name: str = Field(..., min_length=1)
    price: float = Field(..., gt=0)

class ProductUpdate(BaseModel):
    name: str = Field(..., max_length=100)
    price: float = Field(..., gt=0)

class InventoryOperation(BaseModel):
    product_id: str = Field(...)
    operation_type: str = Field(..., regex="^(add|remove)$")
    number_of_products: int = Field(..., gt=0)

# Dependency to get DB session

def get_db() -> Session:
    db = SessionLocal()
    try:
        yield db
    finally:
        db.close()

# FastAPI app
app = FastAPI()

@app.post("/products", status_code=201)
async def create_product(product: ProductCreate, db: Session = Depends(get_db)):
    db_product = db.query(Product).filter(Product.name == product.name).first()
    if db_product:
        logger.error("Product already exists")
        raise HTTPException(status_code=409, detail="Product already exists")
    new_product = Product(name=product.name, price=product.price)
    db.add(new_product)
    db.commit()
    db.refresh(new_product)
    logger.info(f"Product created: {new_product.name}")
    # Create inventory entry
    inventory_entry = Inventory(product_id=new_product.id)
    db.add(inventory_entry)
    db.commit()
    db.refresh(inventory_entry)
    return new_product

@app.put("/products/{product_id}", status_code=200)
async def update_product(product_id: str, product: ProductUpdate, db: Session = Depends(get_db)):
    db_product = db.query(Product).filter(Product.id == product_id).first()
    if not db_product:
        logger.error("Product not found")
        raise HTTPException(status_code=404, detail="Product not found")
    if product.name != db_product.name:
        existing_product = db.query(Product).filter(Product.name == product.name).first()
        if existing_product:
            logger.error("Product name already exists")
            raise HTTPException(status_code=409, detail="Product name already exists")
    db_product.name = product.name
    db_product.price = product.price
    db.commit()
    logger.info(f"Product updated: {db_product.name}")
    return db_product

@app.delete("/products/{product_id}", status_code=204)
async def delete_product(product_id: str, db: Session = Depends(get_db)):
    db_product = db.query(Product).filter(Product.id == product_id).first()
    if not db_product:
        logger.error("Product not found")
        raise HTTPException(status_code=404, detail="Product not found")
    inventory = db.query(Inventory).filter(Inventory.product_id == product_id).first()
    if inventory and inventory.count > 0:
        logger.error("Cannot delete product with positive inventory")
        raise HTTPException(status_code=400, detail="Cannot delete product with positive inventory")
    db.delete(db_product)
    db.commit()
    logger.info(f"Product deleted: {db_product.name}")

@app.post("/inventory/operations", status_code=200)
async def perform_inventory_operation(operation: InventoryOperation, db: Session = Depends(get_db)):
    product_id = operation.product_id
    db_product = db.query(Product).filter(Product.id == product_id).first()
    if not db_product:
        logger.error("Product not found")
        raise HTTPException(status_code=404, detail="Product not found")
    inventory = db.query(Inventory).filter(Inventory.product_id == product_id).first()
    if operation.operation_type == "add":
        if inventory:
            inventory.count += operation.number_of_products
        else:
            new_inventory = Inventory(product_id=product_id, count=operation.number_of_products)
            db.add(new_inventory)
    elif operation.operation_type == "remove":
        if inventory and inventory.count >= operation.number_of_products:
            inventory.count -= operation.number_of_products
        else:
            logger.error("Insufficient stock to remove")
            raise HTTPException(status_code=400, detail="Insufficient stock to remove")
    db.commit()
    logger.info(f"Inventory updated for product_id {product_id}: {inventory.count if inventory else 0}")
    return {"product_id": product_id, "new_count": inventory.count if inventory else 0}

@app.get("/inventory")
async def get_current_inventory(db: Session = Depends(get_db)):
    products = db.query(Product).all()
    inventory_list = []
    for product in products:
        inventory = db.query(Inventory).filter(Inventory.product_id == product.id).first()
        inventory_list.append({
            "product_id": product.id,
            "name": product.name,
            "price": product.price,
            "quantity_available": inventory.count if inventory else 0
        })
    logger.info("Current inventory retrieved successfully.")
    return inventory_list

@app.get("/")
async def read_root():
    return {"message": "Welcome to the Inventory Management Application API!"}
