import pytest
from fastapi.testclient import TestClient
from main import app
from unittest.mock import patch, MagicMock

client = TestClient(app)

@pytest.fixture
def mock_database():
    """
    Fixture to mock the database interactions for product removal.
    """
    with patch('services.auth_service.check_access') as mock_check_access:
        mock_check_access.return_value = True  # Simulate Manager role
        yield

@pytest.mark.parametrize("product_id, expected_status", [
    (1, 204),  # Successful removal
    (999, 404),  # Non-existent product
])
def test_remove_product(mock_database, product_id, expected_status):
    """
    Test the removal of a product from the inventory.

    Args:
        mock_database: Mocked database interactions.
        product_id (int): The ID of the product to remove.
        expected_status (int): The expected HTTP status code.
    """
    response = client.delete(f"/products/{product_id}")
    assert response.status_code == expected_status

def test_remove_product_confirmation(mock_database):
    """
    Test the confirmation requirement for product removal.
    """
    response = client.delete("/products/1", json={"confirm": False})
    assert response.status_code == 400  # Expecting a 400 for missing confirmation

    response = client.delete("/products/1", json={"confirm": True})
    assert response.status_code == 204  # Expecting a 204 for successful removal
