from flask import Blueprint, request, jsonify
from models import Event, db

# Create a blueprint for events
event_bp = Blueprint('event', __name__)

@event_bp.route('/events', methods=['POST'])
def create_event():
    data = request.get_json()
    new_event = Event(title=data['title'], description=data['description'],
                      start_time=data['start_time'], end_time=data['end_time'],
                      user_id=data['user_id'])
    db.session.add(new_event)
    db.session.commit()
    return jsonify({'message': 'Event created successfully!'}), 201

@event_bp.route('/events/<int:event_id>', methods=['PUT'])
def update_event(event_id):
    data = request.get_json()
    event = Event.query.get(event_id)
    if event:
        event.title = data['title']
        event.description = data['description']
        event.start_time = data['start_time']
        event.end_time = data['end_time']
        db.session.commit()
        return jsonify({'message': 'Event updated successfully!'}), 200
    return jsonify({'message': 'Event not found!'}), 404

@event_bp.route('/events/<int:event_id>', methods=['DELETE'])
def delete_event(event_id):
    event = Event.query.get(event_id)
    if event:
        db.session.delete(event)
        db.session.commit()
        return jsonify({'message': 'Event deleted successfully!'}), 200
    return jsonify({'message': 'Event not found!'}), 404