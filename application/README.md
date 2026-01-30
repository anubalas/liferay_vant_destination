# Calendar Application

This is a calendar application built using Python. It allows users to manage events, set reminders, and customize their preferences.

## Features
- User registration and authentication
- Event creation, updating, and deletion
- Calendar view for events
- Notifications and reminders
- User preferences management

## Installation

1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd calendar-application
   ```
2. Install the dependencies:
   ```bash
   pip install -r requirements.txt
   ```
3. Run the application:
   ```bash
   python main.py
   ```

## API Endpoints
- `POST /register`: Register a new user
- `POST /login`: User login
- `POST /events`: Create a new event
- `PUT /events/{event_id}`: Update an event
- `DELETE /events/{event_id}`: Delete an event
- `GET /events`: Retrieve events for a user
- `PUT /users/{user_id}/preferences`: Update user preferences

## License
This project is licensed under the MIT License.