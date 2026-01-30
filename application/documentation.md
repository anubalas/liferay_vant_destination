# API Documentation for Calendar Application

## User Registration
- **Endpoint:** `POST /register`
- **Description:** Register a new user.
- **Request Body:**
  ```json
  {
      "name": "User Name",
      "email": "user@example.com",
      "password": "userpassword"
  }
  ```
- **Response:**
  - 201 Created: User registered successfully!
  - 400 Bad Request: Invalid input.

## User Login
- **Endpoint:** `POST /login`
- **Description:** User login.
- **Request Body:**
  ```json
  {
      "email": "user@example.com",
      "password": "userpassword"
  }
  ```
- **Response:**
  - 200 OK: Login successful!
  - 401 Unauthorized: Invalid credentials.

## Event Management
### Create Event
- **Endpoint:** `POST /events`
- **Description:** Create a new event.
- **Request Body:**
  ```json
  {
      "title": "Event Title",
      "description": "Event Description",
      "start_time": "2023-10-01T10:00:00",
      "end_time": "2023-10-01T12:00:00",
      "user_id": 1
  }
  ```
- **Response:**
  - 201 Created: Event created successfully!

### Update Event
- **Endpoint:** `PUT /events/{event_id}`
- **Description:** Update an existing event.
- **Request Body:** Same as create event.
- **Response:**
  - 200 OK: Event updated successfully!
  - 404 Not Found: Event not found.

### Delete Event
- **Endpoint:** `DELETE /events/{event_id}`
- **Description:** Delete an event.
- **Response:**
  - 200 OK: Event deleted successfully!
  - 404 Not Found: Event not found.

## User Preferences
- **Endpoint:** `PUT /users/{user_id}/preferences`
- **Description:** Update user preferences.
- **Response:**
  - 200 OK: Preferences updated successfully!
  - 404 Not Found: User not found.