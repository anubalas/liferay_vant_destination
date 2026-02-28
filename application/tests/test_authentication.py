import pytest
from unittest.mock import patch, MagicMock
from services.auth_service import generate_jwt_token, authenticate_user, Role
from models.inventory import User

@pytest.fixture
def user():
    """Fixture to create a mock user for testing."""
    return User(username="testuser", password="testpass", role=Role.MANAGER)

@pytest.mark.parametrize(
    "username, password, expected_token",
    [
        ("testuser", "testpass", True),  # Valid credentials
        ("wronguser", "wrongpass", False),  # Invalid credentials
    ]
)
def test_authenticate_user(username, password, expected_token, user):
    """Test user authentication for valid and invalid credentials."""
    with patch('services.auth_service.get_user_by_username') as mock_get_user:
        if expected_token:
            mock_get_user.return_value = user
        else:
            mock_get_user.return_value = None

        result = authenticate_user(username, password)
        if expected_token:
            assert result is not None
            assert result.username == user.username
        else:
            assert result is None

@pytest.mark.parametrize(
    "user, expected_structure",
    [
        (user(), True),  # Valid user
    ]
)
def test_generate_jwt_token(user, expected_structure):
    """Test JWT token generation for a valid user."""
    token = generate_jwt_token(user)
    assert token is not None
    assert len(token.split('.')) == 3  # JWT token should have 3 parts

@pytest.mark.parametrize(
    "role, permission, expected_access",
    [
        (Role.MANAGER, "add_product", True),
        (Role.STAFF, "add_product", False),
        (Role.VIEWER, "add_product", False),
    ]
)
def test_check_access(role, permission, expected_access):
    """Test access control based on user role and permissions."""
    assert check_access(role, permission) == expected_access
