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

# Mock JWT validation function

def mock_jwt_validation(token: str):
    if token == "valid_admin_token":
        return {"role": "admin"}
    elif token == "valid_editor_token":
        return {"role": "editor"}
    elif token == "valid_manager_token":
        return {"role": "manager"}
    elif token == "valid_staff_token":
        return {"role": "staff"}
    elif token == "expired_token":
        raise Exception("Token expired")
    else:
        raise Exception("Malformed token")

# Test admin user performing a write operation
@patch('main.validate_jwt', side_effect=mock_jwt_validation)
def test_admin_user_write_operation(mock_validate):
    response = client.post("/products", json={"name": "Admin Product", "price": 10.0}, headers={"Authorization": "Bearer valid_admin_token"})
    assert response.status_code == 201
    assert response.json()["name"] == "Admin Product"

# Test editor user performing a write operation
@patch('main.validate_jwt', side_effect=mock_jwt_validation)
def test_editor_user_write_operation(mock_validate):
    response = client.post("/products", json={"name": "Editor Product", "price": 15.0}, headers={"Authorization": "Bearer valid_editor_token"})
    assert response.status_code == 201
    assert response.json()["name"] == "Editor Product"

# Test expired JWT token
@patch('main.validate_jwt', side_effect=mock_jwt_validation)
def test_expired_jwt_token(mock_validate):
    response = client.post("/products", json={"name": "Expired Token Product", "price": 20.0}, headers={"Authorization": "Bearer expired_token"})
    assert response.status_code == 401
    assert "Token expired" in response.text

# Test malformed JWT token
@patch('main.validate_jwt', side_effect=mock_jwt_validation)
def test_malformed_jwt_token(mock_validate):
    response = client.post("/products", json={"name": "Malformed Token Product", "price": 25.0}, headers={"Authorization": "Bearer malformed_token"})
    assert response.status_code == 401
    assert "Malformed token" in response.text

# Test staff user attempting to perform a write operation
@patch('main.validate_jwt', side_effect=mock_jwt_validation)
def test_staff_user_write_operation(mock_validate):
    response = client.post("/products", json={"name": "Staff Product", "price": 30.0}, headers={"Authorization": "Bearer valid_staff_token"})
    assert response.status_code == 403
    assert "Access denied" in response.text

# Test manager user performing a write operation
@patch('main.validate_jwt', side_effect=mock_jwt_validation)
def test_manager_user_write_operation(mock_validate):
    response = client.post("/products", json={"name": "Manager Product", "price": 35.0}, headers={"Authorization": "Bearer valid_manager_token"})
    assert response.status_code == 201
    assert response.json()["name"] == "Manager Product"