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

# Test logging for successful user action

def test_logging_successful_user_action(setup_logging):
    """
    Test case to verify logging during a successful user action.
    """
    with patch('main.logger.info') as mock_logger:
        # Simulate a user action
        user_id = "user123"
        action_type = "create"
        response = client.post("/products", json={"name": "Test Product", "price": 10.0})
        assert response.status_code == 201
        mock_logger.assert_called_with(f"User {user_id} performed action: {action_type} on product: Test Product")

# Test logging for inventory change

def test_logging_inventory_change(setup_logging):
    """
    Test case to verify logging during an inventory change.
    """
    with patch('main.logger.info') as mock_logger:
        # Simulate an inventory change
        product_id = "product123"
        operation_type = "add"
        number_of_products = 5
        response = client.post("/inventory/operations", json={"product_id": product_id, "operation_type": operation_type, "number_of_products": number_of_products})
        assert response.status_code == 200
        mock_logger.assert_called_with(f"Inventory changed: {operation_type} {number_of_products} units for product {product_id}")

# Test logging for error scenario

def test_logging_error_scenario(setup_logging):
    """
    Test case to verify logging during an error scenario.
    """
    with patch('main.logger.error') as mock_logger:
        # Simulate an error scenario (e.g., invalid product ID)
        response = client.post("/inventory/operations", json={"product_id": "invalid_id", "operation_type": "remove", "number_of_products": 5})
        assert response.status_code == 404
        mock_logger.assert_called_with("Error: Product not found for inventory operation")

# Test logging to ensure no PII is logged

def test_logging_no_pii(setup_logging):
    """
    Test case to ensure that no PII is logged.
    """
    with patch('main.logger.info') as mock_logger:
        user_id = "user123"
        response = client.post("/products", json={"name": "Test Product", "price": 10.0})
        assert response.status_code == 201
        log_messages = [call[0][0] for call in mock_logger.call_args_list]
        for message in log_messages:
            assert "user_id" not in message
            assert "email" not in message
            assert "phone" not in message
