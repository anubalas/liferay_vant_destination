"""
Order Repository Module

This module provides the OrderRepository class for managing order entities.
"""

from datetime import datetime

class Order:
    """
    Order entity representing an order in the system.
    """
    def __init__(self, id, name, description):
        self.id = id
        self.name = name
        self.description = description
        self.created_at = datetime.now()

class OrderRepository:
    """
    Repository for managing Order entities.
    """
    def __init__(self):
        self.orders = {}
        self.next_id = 1

    def find_one(self, order_id):
        """
        Retrieve a single order by its ID.
        """
        return self.orders.get(order_id)

    def find_all(self):
        """
        Retrieve all orders.
        """
        return list(self.orders.values())

    def save(self, name, description):
        """
        Save a new order with a system-generated ID and timestamp.
        """
        order = Order(self.next_id, name, description)
        self.orders[self.next_id] = order
        self.next_id += 1
        return order

    def delete(self, order_id):
        """
        Delete an order by its ID.
        """
        return self.orders.pop(order_id, None)