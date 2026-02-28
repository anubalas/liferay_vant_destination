import pytest
from fastapi.testclient import TestClient
from main import app
from unittest.mock import patch

client = TestClient(app)

@pytest.mark.parametrize("user_role, expected_status", [
    ("Manager", 200),  # Should have access to all endpoints
    ("Staff", 200),    # Should have access to inventory operations
    ("Viewer", 200),   # Should have read-only access
    ("Guest", 403),    # Should not have access
])
def test_role_based_access(user_role: str, expected_status: int) -> None:
    """
    Test role-based access control for various user roles.

    Args:
        user_role (str): The role of the user.
        expected_status (int): The expected HTTP status code.
    """
    with patch('services.auth_service.get_user_role') as mock_get_user_role:
        mock_get_user_role.return_value = user_role
        response = client.get("/inventory")  # Example endpoint
        assert response.status_code == expected_status

@pytest.mark.parametrize("user_role, product_data, expected_status", [
    ("Manager", {"name": "Test Product", "price": 10.0}, 201),  # Valid for Manager
    ("Staff", {"name": "Test Product", "price": 10.0}, 403),    # Invalid for Staff
    ("Viewer", {"name": "Test Product", "price": 10.0}, 403),   # Invalid for Viewer
])
def test_add_product_access(user_role: str, product_data: dict, expected_status: int) -> None:
    """
    Test access control for adding a product based on user role.

    Args:
        user_role (str): The role of the user.
        product_data (dict): The product data to send in the request.
        expected_status (int): The expected HTTP status code.
    """
    with patch('services.auth_service.get_user_role') as mock_get_user_role:
        mock_get_user_role.return_value = user_role
        response = client.post("/products", json=product_data)
        assert response.status_code == expected_status

@pytest.mark.parametrize("user_role, operation_type, expected_status", [
    ("Manager", "add", 200),  # Manager can add stock
    ("Staff", "add", 200),    # Staff can add stock
    ("Viewer", "add", 403),   # Viewer cannot add stock
])
def test_inventory_operation_access(user_role: str, operation_type: str, expected_status: int) -> None:
    """
    Test access control for inventory operations based on user role.

    Args:
        user_role (str): The role of the user.
        operation_type (str): The type of inventory operation.
        expected_status (int): The expected HTTP status code.
    """
    with patch('services.auth_service.get_user_role') as mock_get_user_role:
        mock_get_user_role.return_value = user_role
        response = client.post("/inventory/operations", json={
            "product_id": 1,
            "operation_type": operation_type,
            "number_of_products": 10
        })
        assert response.status_code == expected_status
