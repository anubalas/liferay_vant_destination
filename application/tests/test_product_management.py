import pytest
from fastapi.testclient import TestClient
from main import app
from unittest.mock import patch

client = TestClient(app)

@pytest.mark.parametrize("product_data, expected_status", [
    ({"name": "Test Product", "price": 10.0}, 201),  # Valid product
    ({"name": "", "price": 10.0}, 400),  # Invalid name
    ({"name": "Test Product", "price": -5.0}, 400),  # Invalid price
    ({"name": "Test Product", "price": 10.0}, 400),  # Duplicate name
])
def test_add_product(product_data, expected_status):
    """
    Test adding a product to the inventory.

    Args:
        product_data (dict): The product data to send in the request.
        expected_status (int): The expected HTTP status code.
    """
    with patch('services.auth_service.check_access') as mock_check_access:
        mock_check_access.return_value = True  # Simulate Manager role
        response = client.post("/products", json=product_data)
        assert response.status_code == expected_status

@patch('main.get_user_by_username')
def test_add_product_server_error(mock_get_user):
    """
    Test server error when adding a product.
    """
    mock_get_user.side_effect = Exception("Database error")
    response = client.post("/products", json={"name": "Test Product", "price": 10.0})
    assert response.status_code == 500
