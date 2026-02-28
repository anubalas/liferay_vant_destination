/application/README.md

# Inventory Management Application (IMA)

## Introduction
The Inventory Management Application (IMA) is designed to streamline retail store operations by managing product information and tracking inventory levels. The application aims to provide a centralized platform for managing product data, ensuring real-time visibility into inventory, and reducing human error through validated inventory operations.

## Features
- Centralized management of product data.
- Real-time inventory visibility.
- Validation of inventory operations to minimize errors.
- Role-based access control to prevent unauthorized changes.

## Tech Stack
- Backend: FastAPI, Python, SQLAlchemy
- Frontend: React (TypeScript)
- Database: PostgreSQL

## Getting Started
1. Clone the repository.
2. Install dependencies using `pip install -r requirements.txt`.
3. Set up the database and environment variables.
4. Run the application using `uvicorn application.main:app --reload`.

## API Endpoints
### Create Product
- **Endpoint**: POST /products
- **Description**: Creates a new product in the system catalog.
- **Request Body**: { "name": "string", "price": "float" }
- **Responses**:
  - 201: Product created
  - 400: Missing/invalid fields

### Edit Product
- **Endpoint**: PUT /products/{productId}
- **Description**: Allows updating product information such as name and price.
- **Request Body**: { "name": "string", "price": "float" }
- **Responses**:
  - 200: Product updated
  - 400: Invalid input
  - 404: Product not found

### Remove Product
- **Endpoint**: DELETE /products/{productId}
- **Description**: Removes a product from the catalog after user confirmation and prevents removal if positive inventory exists.
- **Responses**:
  - 204: Successfully removed
  - 404: Product not found

### Perform Inventory Operation
- **Endpoint**: POST /inventory/operations
- **Description**: Logs an inventory event such as adding new stock or recording a sale. Adjusts the inventory count accordingly.
- **Request Body**: { "product_id": "string", "operation_type": "string", "number_of_products": "integer > 0" }
- **Responses**:
  - 200: Updated inventory count returned
  - 400: Invalid fields or insufficient stock
  - 404: Product not found

### Get Current Inventory
- **Endpoint**: GET /inventory
- **Description**: Returns all products along with their current stock count.
- **Responses**:
  - 200: Product + inventory list
  - 500: Server error

## Testing
- Use pytest for unit testing.
- Aim for at least 80% test coverage.

## Role Definitions
- **Manager**: Full permissions (add, edit, remove products, perform inventory operations, view all data).
- **Staff**: Limited permissions (perform inventory operations, view product and inventory information).
- **Viewer**: Read-only access to product and inventory information.

## Environment Variables
- **DB_HOST**: Database host address.
- **DB_PORT**: Database port number.
- **DB_NAME**: Database name.
- **DB_USER**: Database user.
- **DB_PASSWORD**: Database password.

## Kubernetes Secrets
To securely manage sensitive data, use Kubernetes Secrets to store the database credentials and JWT keys. Follow the Kubernetes documentation for creating and managing secrets.