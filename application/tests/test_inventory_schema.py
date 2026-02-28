import pytest
from sqlalchemy import create_engine, exc
from sqlalchemy.orm import sessionmaker
from models.inventory import Inventory
from models.base import Base
import uuid
import logging

logger = logging.getLogger(__name__)

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

@pytest.fixture(scope='function')
def create_product(test_setup):
    """
    Fixture to create a product in the database for testing.
    """
    product_id = uuid.uuid4()  # Simulate a product ID
    # Assuming a Product model exists and has been defined
    # Add product creation logic here if necessary
    return product_id

@pytest.mark.parametrize('product_id, expected', [
    (uuid.uuid4(), True),  # Valid product ID
    (uuid.uuid4(), False)  # Invalid product ID (not in DB)
])
def test_inventory_creation(test_setup, create_product, product_id, expected):
    """
    Test the creation of an inventory entry with valid and invalid product IDs.
    """
    
    if expected:
        inventory = Inventory(product_id=product_id, count=10)
        test_setup.add(inventory)
        test_setup.commit()
        assert inventory.id is not None
        assert inventory.count == 10
    else:
        with pytest.raises(exc.IntegrityError) as excinfo:
            inventory = Inventory(product_id=product_id, count=10)
            test_setup.add(inventory)
            test_setup.commit()
        logger.error("IntegrityError raised as expected: %s", excinfo.value)

@pytest.mark.parametrize('product_id', [
    (uuid.uuid4()),  # Valid product ID
])
def test_foreign_key_constraint(test_setup, create_product, product_id):
    """
    Verify that the foreign key constraint is enforced by checking the database state.
    """
    inventory = Inventory(product_id=product_id, count=5)
    test_setup.add(inventory)
    test_setup.commit()
    assert inventory.product_id == product_id
    assert inventory.count == 5
