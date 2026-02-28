import pytest
from fastapi.testclient import TestClient
from main import app
from unittest.mock import patch, MagicMock

client = TestClient(app)

@pytest.fixture
def mock_auth_service():
    """
    Fixture to mock the authentication service for testing.
    """
    with patch('services.auth_service.check_access') as mock_access:
        mock_access.return_value = True  # Simulate Manager or Staff role
        yield mock_access

@pytest.mark.parametrize("product_id, operation_type, number_of_products, expected_status", [
    (1, "add", 10, 200),  # Successful addition
    (1, "remove", 5, 200),  # Successful removal
    (1, "remove", 10, 400),  # Insufficient stock
    (999, "remove", 5, 404),  # Non-existent product
    (1, "remove", -5, 400),  # Invalid number of products
])
def test_perform_inventory_operation(mock_auth_service, product_id, operation_type, number_of_products, expected_status):
    """
    Test the Perform Inventory Operation API.

    Args:
        mock_auth_service: Mocked authentication service.
        product_id (int): The ID of the product to operate on.
        operation_type (str): The type of operation (add/remove).
        number_of_products (int): The number of products to add/remove.
        expected_status (int): The expected HTTP status code.
    """
    response = client.post("/inventory/operations", json={
        "product_id": product_id,
        "operation_type": operation_type,
        "number_of_products": number_of_products
    })
    assert response.status_code == expected_status

@pytest.mark.parametrize("product_id, operation_type, number_of_products, expected_message", [
    (1, "remove", 10, "Insufficient stock"),  # Trying to remove more than available
])
def test_perform_inventory_operation_insufficient_stock(mock_auth_service, product_id, operation_type, number_of_products, expected_message):
    """
    Test the Perform Inventory Operation API for insufficient stock.

    Args:
        mock_auth_service: Mocked authentication service.
        product_id (int): The ID of the product to operate on.
        operation_type (str): The type of operation (remove).
        number_of_products (int): The number of products to remove.
        expected_message (str): The expected error message.
    """
    response = client.post("/inventory/operations", json={
        "product_id": product_id,
        "operation_type": operation_type,
        "number_of_products": number_of_products
    })
    assert response.status_code == 400
    assert expected_message in response.json().get("detail", "")
