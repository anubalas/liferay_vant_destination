"""
Test Order Repository Module

This module contains tests for the OrderRepository class.
"""

import unittest
from order_repository import OrderRepository

class TestOrderRepository(unittest.TestCase):
    """
    Test cases for OrderRepository.
    """
    def setUp(self):
        self.repo = OrderRepository()

    def test_save_and_find_one(self):
        order = self.repo.save('Test Order', 'This is a test order.')
        found_order = self.repo.find_one(order.id)
        self.assertEqual(found_order.name, 'Test Order')
        self.assertEqual(found_order.description, 'This is a test order.')

    def test_find_all(self):
        self.repo.save('Order 1', 'Description 1')
        self.repo.save('Order 2', 'Description 2')
        orders = self.repo.find_all()
        self.assertEqual(len(orders), 2)

    def test_delete(self):
        order = self.repo.save('Order to delete', 'This order will be deleted.')
        self.repo.delete(order.id)
        self.assertIsNone(self.repo.find_one(order.id))

    def test_delete_non_existent(self):
        result = self.repo.delete(999)
        self.assertIsNone(result)

if __name__ == '__main__':
    unittest.main()