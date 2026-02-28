import pytest
from sqlalchemy import create_engine
from sqlalchemy.orm import sessionmaker
from models.inventory import Inventory
from models.base import Base
import uuid

@pytest.fixture(scope='module')
def test_setup():
    """
    Setup a test database and create the inventory table.
    """
    engine = create_engine('sqlite:///:memory:')
    Base.metadata.create_all(engine)
    Session = sessionmaker(bind=engine)
    session = Session()
    yield session
    session.close()

def test_inventory_creation(test_setup):
    """
    Test the creation of an inventory entry.
    """
    product_id = uuid.uuid4()
    inventory = Inventory(product_id=product_id, count=10)
    test_setup.add(inventory)
    test_setup.commit()
    assert inventory.id is not None
    assert inventory.count == 10

def test_inventory_relationship(test_setup):
    """
    Test the relationship between inventory and product.
    """
    product_id = uuid.uuid4()
    inventory = Inventory(product_id=product_id, count=5)
    test_setup.add(inventory)
    test_setup.commit()
    assert inventory.product_id == product_id
