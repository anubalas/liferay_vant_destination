import pytest
from unittest.mock import MagicMock
from main import User, UserRole, PermissionService

@pytest.fixture
def permission_service() -> PermissionService:
    """
    Fixture to create a PermissionService instance.
    """
    return PermissionService()

@pytest.mark.parametrize("user_role, expected_create, expected_edit, expected_view", [
    (UserRole.MANAGER, True, True, True),
    (UserRole.STAFF, False, False, True),
    (UserRole.VIEWER, False, False, True),
    (None, False, False, False)
])
def test_permissions(user_role, expected_create, expected_edit, expected_view, permission_service):
    """
    Test the permission service for various user roles.
    """
    user = User(username="test_user", role=user_role) if user_role else User(username="test_user", role=None)
    assert permission_service.can_create_item(user) == expected_create
    assert permission_service.can_edit_item(user) == expected_edit
    assert permission_service.can_view_item(user) == expected_view
