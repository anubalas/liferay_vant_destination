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

# Test adding stock to inventory

def test_add_stock_valid():
    """
    Test case for adding stock to inventory with valid data.
    """
    response = client.post("/inventory/operations", json={"product_id": "valid_product_id", "operation_type": "add", "number_of_products": 10})
    assert response.status_code == 200
    assert response.json()["new_count"] == 10

# Test removing stock from inventory

def test_remove_stock_valid():
    """
    Test case for removing stock from inventory with valid data.
    """
    response = client.post("/inventory/operations", json={"product_id": "valid_product_id", "operation_type": "remove", "number_of_products": 5})
    assert response.status_code == 200
    assert response.json()["new_count"] == 5

# Test insufficient stock removal

def test_remove_stock_insufficient():
    """
    Test case for attempting to remove stock when insufficient stock is available.
    """
    response = client.post("/inventory/operations", json={"product_id": "valid_product_id", "operation_type": "remove", "number_of_products": 10})
    assert response.status_code == 400
    assert "Insufficient stock to remove" in response.text

# Test invalid operation type

def test_invalid_operation_type():
    """
    Test case for providing an invalid operation type.
    """
    response = client.post("/inventory/operations", json={"product_id": "valid_product_id", "operation_type": "invalid", "number_of_products": 5})
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

# Test getting current inventory

def test_get_current_inventory():
    """
    Test case for retrieving the current inventory.
    """
    response = client.get("/inventory")
    assert response.status_code == 200
    assert isinstance(response.json(), list)

# Test server error handling

def test_get_current_inventory_server_error():
    """
    Test case for simulating a server error when retrieving inventory.
    """
    with patch('main.get_db', side_effect=Exception("Database error")):
        response = client.get("/inventory")
        assert response.status_code == 500
        assert "Internal Server Error" in response.text
