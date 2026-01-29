# Fibonacci API

This is a simple API for generating Fibonacci numbers based on user input.

## API Endpoint

### Get Fibonacci Number
- **URL**: `/fibonacci`
- **Method**: `GET`
- **Query Parameters**:
    - `n`: The position in the Fibonacci sequence (non-negative integer).

- **Response**:
    - **Success**: Returns a JSON object with the Fibonacci number at position `n`.
    - **Error**: Returns a JSON object with an error message for invalid inputs.

## Error Handling
- Returns a 400 status code for invalid inputs (e.g., negative numbers, non-integer values).
