# Inventory Management Application

## Overview
The Inventory Management Application (IMA) supports retail store operations by managing product information and tracking item availability.

## Features
- Add, edit, and remove products.
- Perform inventory operations (add/remove stock).
- View current inventory.

## API Endpoints
- `POST /products`: Create a new product.
- `PUT /products/{productId}`: Update product information.
- `DELETE /products/{productId}`: Remove a product.
- `POST /inventory/operations`: Log an inventory event.
- `GET /inventory`: Retrieve current inventory.

## Testing
Run tests using pytest:
```bash
pytest application/test_main.py
pytest application/test_inventory_operations.py
```

## Requirements
- FastAPI
- SQLAlchemy
- Pydantic
- pytest
