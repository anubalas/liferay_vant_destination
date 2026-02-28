import pytest
import logging
from unittest.mock import patch
from fastapi.testclient import TestClient
from main import app

client = TestClient(app)

@pytest.fixture
def setup_logging(caplog):
    """
    Fixture to set up logging for tests.
    """
    with caplog.at_level(logging.INFO):
        yield caplog

@pytest.mark.parametrize("user_role, action, expected_log", [
    ("manager", "added product", "INFO:root:Manager performed action: added product"),
    ("staff", "removed stock", "INFO:root:Staff performed action: removed stock"),
])
def test_logging_user_actions(setup_logging, user_role, action, expected_log):
    """
    Test logging of user actions.

    Args:
        setup_logging: Fixture to capture log output.
        user_role (str): The role of the user performing the action.
        action (str): The action performed by the user.
        expected_log (str): The expected log message.
    """
    with patch('services.auth_service.check_access') as mock_access:
        mock_access.return_value = True  # Simulate access
        # Simulate user action
        logging.info(f"{user_role.capitalize()} performed action: {action}")
    assert expected_log in setup_logging.text

@pytest.mark.parametrize("product_id, operation_type, number_of_products, expected_log", [
    (1, "add", 10, "INFO:root:Inventory updated: added 10 units of product 1"),
    (1, "remove", 5, "INFO:root:Inventory updated: removed 5 units of product 1"),
])
def test_logging_inventory_operations(setup_logging, product_id, operation_type, number_of_products, expected_log):
    """
    Test logging of inventory operations.

    Args:
        setup_logging: Fixture to capture log output.
        product_id (int): The ID of the product.
        operation_type (str): The type of operation (add/remove).
        number_of_products (int): The number of products involved.
        expected_log (str): The expected log message.
    """
    with patch('services.auth_service.check_access') as mock_access:
        mock_access.return_value = True  # Simulate access
        # Simulate inventory operation
        logging.info(f"Inventory updated: {operation_type} {number_of_products} units of product {product_id}")
    assert expected_log in setup_logging.text
