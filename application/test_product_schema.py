import pytest
from fastapi.testclient import TestClient
from main import app
from unittest.mock import patch

client = TestClient(app)

@pytest.fixture(scope="module")
def setup_logging():
    import logging
    logging.basicConfig(level=logging.DEBUG)
    yield
    logging.shutdown()

# Test creation of inventory with valid product IDs

def test_create_inventory_valid_product_id():
    """
    Test case for creating inventory with valid product ID.
    """
    response = client.post("/inventory/operations", json={"product_id": "valid_product_id", "operation_type": "add", "number_of_products": 10})
    assert response.status_code == 200
    assert response.json()["new_count"] == 10

# Test creation of inventory with non-existent product ID

def test_create_inventory_invalid_product_id():
    """
    Test case for creating inventory with non-existent product ID.
    """
    response = client.post("/inventory/operations", json={"product_id": "invalid_product_id", "operation_type": "add", "number_of_products": 10})
    assert response.status_code == 404
    assert "Product not found" in response.text

# Test validation of required fields in inventory data structure

def test_inventory_data_structure_validation():
    """
    Test case for validating required fields in inventory data structure.
    """
    response = client.post("/inventory/operations", json={"product_id": "", "operation_type": "add", "number_of_products": 10})
    assert response.status_code == 422

# Test data type validation for each field in inventory schema

def test_inventory_data_type_validation():
    """
    Test case for validating data types in inventory schema.
    """
    response = client.post("/inventory/operations", json={"product_id": "valid_product_id", "operation_type": "add", "number_of_products": "ten"})
    assert response.status_code == 422

# Test logging of inventory operations

def test_logging_inventory_operation(setup_logging):
    """
    Test case to verify logging during inventory operations.
    """
    with patch('main.logger.info') as mock_logger:
        response = client.post("/inventory/operations", json={"product_id": "valid_product_id", "operation_type": "add", "number_of_products": 10})
        assert response.status_code == 200
        mock_logger.assert_called_with("Inventory updated for product_id valid_product_id: 10")

# Test logging of removal operation

def test_logging_inventory_removal(setup_logging):
    """
    Test case to verify logging during inventory removal operations.
    """
    with patch('main.logger.info') as mock_logger:
        response = client.post("/inventory/operations", json={"product_id": "valid_product_id", "operation_type": "remove", "number_of_products": 5})
        assert response.status_code == 200
        mock_logger.assert_called_with("Inventory updated for product_id valid_product_id: 5")
