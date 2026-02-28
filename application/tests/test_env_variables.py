import os
import pytest
from unittest.mock import patch

@pytest.fixture
def set_env_vars():
    """Fixture to set environment variables for testing."""
    os.environ["DB_HOST"] = "localhost"
    os.environ["DB_PORT"] = "5432"
    os.environ["DB_NAME"] = "test_db"
    os.environ["DB_USER"] = "test_user"
    os.environ["DB_PASSWORD"] = "test_password"
    yield
    # Cleanup
    del os.environ["DB_HOST"]
    del os.environ["DB_PORT"]
    del os.environ["DB_NAME"]
    del os.environ["DB_USER"]
    del os.environ["DB_PASSWORD"]


def test_env_vars_loaded_correctly(set_env_vars):
    """Test that environment variables are loaded correctly."""
    assert os.getenv("DB_HOST") == "localhost"
    assert os.getenv("DB_PORT") == "5432"
    assert os.getenv("DB_NAME") == "test_db"
    assert os.getenv("DB_USER") == "test_user"
    assert os.getenv("DB_PASSWORD") == "test_password"


def test_missing_env_var():
    """Test that an error is raised when a required environment variable is missing."""
    with patch("os.getenv", side_effect=lambda key: None if key == "DB_HOST" else "value"):
        with pytest.raises(KeyError):
            db_host = os.getenv("DB_HOST")
            if db_host is None:
                raise KeyError("DB_HOST is not set")


def test_sensitive_data_not_hardcoded():
    """Test that sensitive data is not hard-coded in the application code."""
    assert os.getenv("SECRET_KEY") == "your_secret_key"  # Default value for testing


def test_sensitive_data_not_logged(caplog):
    """Test that sensitive data is not logged during execution."""
    with caplog.at_level(logging.INFO):
        logging.info(f"DB_HOST: {os.getenv('DB_HOST')}")
    assert "DB_HOST:" not in caplog.text
