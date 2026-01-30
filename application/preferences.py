from flask import Blueprint, request, jsonify
from models import User, db

# Create a blueprint for user preferences
preferences_bp = Blueprint('preferences', __name__)

@preferences_bp.route('/users/<int:user_id>/preferences', methods=['PUT'])
def update_preferences(user_id):
    data = request.get_json()
    user = User.query.get(user_id)
    if user:
        # Update user preferences logic here
        return jsonify({'message': 'Preferences updated successfully!'}), 200
    return jsonify({'message': 'User not found!'}), 404