import os
import pytest
from fastapi import FastAPI, HTTPException
from unittest.mock import patch

app = FastAPI()

# Sample endpoint to demonstrate environment variable usage
@app.get("/config")
async def get_config():
    db_url = os.getenv("DATABASE_URL")
    if not db_url:
        raise HTTPException(status_code=500, detail="DATABASE_URL not set")
    return {"database_url": db_url}

@pytest.fixture(scope="function")
def setup_env_variables(monkeypatch):
    """
    Fixture to set up environment variables for testing.
    """
    monkeypatch.setenv("DATABASE_URL", "postgresql://user:password@localhost/db")

@pytest.mark.parametrize("env_var, expected_status", [
    ("DATABASE_URL", 200),
    ("NON_EXISTENT_VAR", 500)
])
def test_environment_variable_access(setup_env_variables, env_var, expected_status):
    """
    Test to verify access to environment variables.
    """
    if env_var == "NON_EXISTENT_VAR":
        with pytest.raises(HTTPException) as excinfo:
            get_config()
        assert excinfo.value.status_code == expected_status
    else:
        response = get_config()
        assert response["database_url"] == os.getenv(env_var)
        assert response.status_code == expected_status

@pytest.mark.parametrize("missing_var", ["DATABASE_URL"])
def test_missing_environment_variable(missing_var):
    """
    Test to verify that the application raises an error when required environment variables are missing.
    """
    with patch.dict(os.environ, {missing_var: ''}):
        with pytest.raises(HTTPException) as excinfo:
            get_config()
        assert excinfo.value.status_code == 500
        assert "DATABASE_URL not set" in str(excinfo.value.detail)
