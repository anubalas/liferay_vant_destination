from flask import Flask
from database import init_db
from auth import auth_bp
from event import event_bp

app = Flask(__name__)
app.config['SQLALCHEMY_DATABASE_URI'] = 'sqlite:///calendar.db'
app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False

# Initialize the database
init_db(app)

# Register blueprints
app.register_blueprint(auth_bp)
app.register_blueprint(event_bp)

if __name__ == '__main__':
    app.run(debug=True)