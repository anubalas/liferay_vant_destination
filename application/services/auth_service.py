import os
import jwt
import datetime
from models.inventory import User
from typing import Optional

SECRET_KEY = os.getenv("SECRET_KEY", "your_secret_key")

class Role:
    MANAGER = "manager"
    STAFF = "staff"
    VIEWER = "viewer"

ROLE_PERMISSIONS = {
    Role.MANAGER: ["add_product", "edit_product", "remove_product", "perform_inventory_operation", "view_inventory"],
    Role.STAFF: ["perform_inventory_operation", "view_inventory"],
    Role.VIEWER: ["view_inventory"],
}

def generate_jwt_token(user: User) -> str:
    payload = {
        "sub": user.username,
        "iat": datetime.datetime.utcnow(),
        "exp": datetime.datetime.utcnow() + datetime.timedelta(hours=1),
        "role": user.role
    }
    token = jwt.encode(payload, SECRET_KEY, algorithm="HS256")
    return token

def authenticate_user(username: str, password: str) -> Optional[User]:
    user = get_user_by_username(username)
    if user and user.password == password:  # Replace with hashed password check
        return user
    return None

def check_access(role: str, permission: str) -> bool:
    """Check if the user role has the required permission."""
    return permission in ROLE_PERMISSIONS.get(role, [])
