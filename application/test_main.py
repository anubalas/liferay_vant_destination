import pytest
from fastapi.testclient import TestClient
from main import app
from unittest.mock import patch

client = TestClient(app)

@pytest.fixture(scope="module")
def setup_logging():
    """
    Fixture to set up logging for tests.
    """
    import logging
    logging.basicConfig(level=logging.DEBUG)
    yield
    logging.shutdown()

# Test the root endpoint

def test_read_root():
    """
    Test case for the root endpoint.
    """
    response = client.get("/")
    assert response.status_code == 200
    assert response.json() == {"message": "Welcome to the Inventory Management Application API!"}

# Test valid product creation

def test_create_product_valid():
    """
    Test case for creating a product with valid data.
    """
    response = client.post("/products", json={"name": "Valid Product", "price": 10.0})
    assert response.status_code == 201
    assert response.json()["name"] == "Valid Product"
    assert response.json()["price"] == 10.0

# Test invalid product name (empty)

def test_create_product_invalid_name():
    """
    Test case for creating a product with an empty name.
    """
    response = client.post("/products", json={"name": "", "price": 10.0})
    assert response.status_code == 422
    assert "value is not a valid string" in response.text

# Test duplicate product name

def test_create_product_duplicate_name():
    """
    Test case for creating a product with a duplicate name.
    """
    client.post("/products", json={"name": "Duplicate Product", "price": 10.0})  # Create first product
    response = client.post("/products", json={"name": "Duplicate Product", "price": 15.0})
    assert response.status_code == 409
    assert "Product already exists" in response.text

# Test invalid price (negative)

def test_create_product_invalid_price():
    """
    Test case for creating a product with a negative price.
    """
    response = client.post("/products", json={"name": "Invalid Price Product", "price": -5.0})
    assert response.status_code == 422
    assert "must be greater than 0" in response.text

# Test inventory entry creation

def test_inventory_entry_creation():
    """
    Test case for creating a product and verifying its inventory entry.
    """
    response = client.post("/products", json={"name": "New Product", "price": 20.0})
    assert response.status_code == 201
    product_id = response.json()["id"]
    inventory_response = client.get(f"/inventory")
    inventory_items = inventory_response.json()
    assert any(item["product_id"] == product_id and item["quantity_available"] == 0 for item in inventory_items)

# Test logging of product creation

def test_logging_product_creation(setup_logging):
    """
    Test case to verify logging during product creation.
    """
    with patch('main.logger.info') as mock_logger:
        response = client.post("/products", json={"name": "Test Product", "price": 10.0})
        assert response.status_code == 201
        mock_logger.assert_called_with("Product created: Test Product")

# Test logging of product update

def test_logging_product_update(setup_logging):
    """
    Test case to verify logging during product update.
    """
    # First create a product
    response = client.post("/products", json={"name": "Test Product", "price": 10.0})
    product_id = response.json()["id"]
    with patch('main.logger.info') as mock_logger:
        response = client.put(f"/products/{product_id}", json={"name": "Updated Product", "price": 15.0})
        assert response.status_code == 200
        mock_logger.assert_called_with("Product updated: Updated Product")

# Test logging of product deletion

def test_logging_product_deletion(setup_logging):
    """
    Test case to verify logging during product deletion.
    """
    # First create a product
    response = client.post("/products", json={"name": "Test Product", "price": 10.0})
    product_id = response.json()["id"]
    with patch('main.logger.info') as mock_logger:
        response = client.delete(f"/products/{product_id}")
        assert response.status_code == 204
        mock_logger.assert_called_with(f"Product deleted: {product_id}")

# Test successful removal of a product with zero inventory

def test_delete_product_success():
    """
    Test case for successfully removing a product with zero inventory.
    """
    response = client.post("/products", json={"name": "Removable Product", "price": 10.0})
    product_id = response.json()["id"]
    delete_response = client.delete(f"/products/{product_id}")
    assert delete_response.status_code == 204

# Test failure to remove a product with positive inventory

def test_delete_product_with_positive_inventory():
    """
    Test case for attempting to remove a product that has positive inventory.
    """
    response = client.post("/products", json={"name": "Product With Inventory", "price": 10.0})
    product_id = response.json()["id"]
    client.post("/inventory/operations", json={"product_id": product_id, "operation_type": "add", "number_of_products": 5})
    delete_response = client.delete(f"/products/{product_id}")
    assert delete_response.status_code == 400
    assert "Cannot delete product with positive inventory" in delete_response.text

# Test confirmation prompt before removal

def test_delete_product_confirmation_prompt():
    """
    Placeholder for the confirmation prompt test.
    """
    # In a real scenario, this would involve simulating user input
    pass
