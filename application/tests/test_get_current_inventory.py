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

@pytest.mark.parametrize("role, expected_status", [
    ("manager", 200),  # Manager should have access
    ("staff", 200),    # Staff should have access
    ("viewer", 200),   # Viewer should have access
])
def test_get_current_inventory(mock_auth_service, role, expected_status):
    """
    Test the Get Current Inventory API.

    Args:
        mock_auth_service: Mocked authentication service.
        role (str): The role of the user accessing the API.
        expected_status (int): The expected HTTP status code.
    """
    with patch('services.auth_service.check_access', return_value=True):
        response = client.get("/inventory")
        assert response.status_code == expected_status

@pytest.mark.parametrize("role, expected_status", [
    ("manager", 500),  # Simulate server error for manager
    ("staff", 500),    # Simulate server error for staff
    ("viewer", 500),   # Simulate server error for viewer
])
def test_get_current_inventory_server_error(mock_auth_service, role, expected_status):
    """
    Test the Get Current Inventory API for server errors.

    Args:
        mock_auth_service: Mocked authentication service.
        role (str): The role of the user accessing the API.
        expected_status (int): The expected HTTP status code.
    """
    with patch('services.auth_service.check_access', return_value=True), \
         patch('models.inventory.Inventory.query', side_effect=Exception("Database error")):
        response = client.get("/inventory")
        assert response.status_code == expected_status
