import os
import logging
from flask import Flask, request, jsonify
from flask_sqlalchemy import SQLAlchemy
from marshmallow import Schema, fields, ValidationError

# Initialize Flask app and database
app = Flask(__name__)
app.config['SQLALCHEMY_DATABASE_URI'] = os.getenv('DATABASE_URL')
db = SQLAlchemy(app)

# Configure logging
logging.basicConfig(level=logging.INFO)
logger = logging.getLogger(__name__)

# Database model for Product
class Product(db.Model):
    __tablename__ = 'products'
    id = db.Column(db.Integer, primary_key=True, autoincrement=True)
    name = db.Column(db.String, nullable=False)
    description = db.Column(db.Text, nullable=False)
    price = db.Column(db.Numeric, nullable=False)
    sku = db.Column(db.String, unique=True, nullable=False)
    created_at = db.Column(db.DateTime, server_default=db.func.current_timestamp())
    updated_at = db.Column(db.DateTime, server_default=db.func.current_timestamp(), onupdate=db.func.current_timestamp())

# Product schema for validation
class ProductSchema(Schema):
    name = fields.Str(required=True)
    description = fields.Str(required=True)
    price = fields.Decimal(required=True)
    sku = fields.Str(required=True)

product_schema = ProductSchema()

@app.route('/api/products', methods=['POST'])
def add_product():
    try:
        # Validate input data
        data = product_schema.load(request.json)
        # Check for existing SKU
        existing_product = Product.query.filter_by(sku=data['sku']).first()
        if existing_product:
            return jsonify({'error': 'SKU already exists.'}), 409
        # Create new product
        new_product = Product(**data)
        db.session.add(new_product)
        db.session.commit()
        logger.info('Product added successfully: %s', new_product)
        return product_schema.dump(new_product), 201
    except ValidationError as err:
        logger.error('Validation error: %s', err.messages)
        return jsonify(err.messages), 400
    except Exception as e:
        logger.error('Error adding product: %s', str(e))
        return jsonify({'error': 'An error occurred.'}), 500

if __name__ == '__main__':
    db.create_all()  # Create database tables
    app.run(debug=True)
