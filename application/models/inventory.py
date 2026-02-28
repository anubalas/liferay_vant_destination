from sqlalchemy import Column, Integer, ForeignKey, String
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.orm import relationship
import uuid

Base = declarative_base()

class Inventory(Base):
    """
    Represents the inventory schema for tracking product stock.

    Attributes:
        id (UUID): The primary key for the inventory entry.
        product_id (UUID): The foreign key referencing the products table.
        count (int): The number of items in stock.
    """
    __tablename__ = 'inventory'

    id = Column(UUID(as_uuid=True), primary_key=True, default=uuid.uuid4)
    product_id = Column(UUID(as_uuid=True), ForeignKey('products.id'), nullable=False)
    count = Column(Integer, default=0)

    product = relationship('Product', back_populates='inventory')

    def __init__(self, product_id: uuid.UUID, count: int = 0) -> None:
        self.product_id = product_id
        self.count = count

    def __repr__(self) -> str:
        return f'<Inventory(id={self.id}, product_id={self.product_id}, count={self.count})>'
