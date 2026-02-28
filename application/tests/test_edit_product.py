import pytest
from fastapi.testclient import TestClient
from main import app
from unittest.mock import patch, MagicMock

client = TestClient(app)

@pytest.fixture
def mock_product_service():
    """
    Fixture to mock the product service for testing.
    """
    with patch('services.product_service') as mock_service:
        yield mock_service

@pytest.mark.parametrize("product_id, product_data, expected_status", [
    (1, {"name": "Updated Product", "price": 15.0}, 200),  # Successful update
    (1, {"name": "", "price": 15.0}, 400),  # Invalid name
    (999, {"name": "Non-existent Product", "price": 15.0}, 404),  # Non-existent product
    (1, {"name": "Existing Product", "price": 15.0}, 400),  # Duplicate name
])
def test_edit_product(product_id, product_data, expected_status, mock_product_service):
    """
    Test the Edit Product API.

    Args:
        product_id (int): The ID of the product to edit.
        product_data (dict): The new product data.
        expected_status (int): The expected HTTP status code.
    """
    # Mock the product service methods
    if expected_status == 200:
        mock_product_service.update_product.return_value = True
    elif expected_status == 404:
        mock_product_service.update_product.side_effect = Exception("Product not found")
    elif expected_status == 400:
        mock_product_service.update_product.side_effect = Exception("Invalid input")

    response = client.put(f"/products/{product_id}", json=product_data)
    assert response.status_code == expected_status

@pytest.fixture
def mock_auth_service():
    """
    Fixture to mock the authentication service for testing.
    """
    with patch('services.auth_service.check_access') as mock_access:
        mock_access.return_value = True  # Simulate Manager role
        yield mock_access

@patch('main.get_user_by_username')
def test_edit_product_server_error(mock_get_user, mock_auth_service):
    """
    Test server error when editing a product.
    """
    mock_get_user.side_effect = Exception("Database error")
    response = client.put("/products/1", json={"name": "Updated Product", "price": 15.0})
    assert response.status_code == 500
