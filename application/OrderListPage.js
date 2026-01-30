import React, { useEffect, useState } from 'react';

/**
 * Component to list all orders and provide options to create and delete orders.
 */
const OrderListPage = () => {
    const [orders, setOrders] = useState([]);

    useEffect(() => {
        fetchOrders();
    }, []);

    const fetchOrders = async () => {
        const response = await fetch('/api/orders');
        const data = await response.json();
        setOrders(data);
    };

    const deleteOrder = async (id) => {
        await fetch(`/api/orders/${id}`, { method: 'DELETE' });
        fetchOrders();
    };

    return (
        <div>
            <h1>Order List</h1>
            <ul>
                {orders.map(order => (
                    <li key={order.id}>
                        {order.name} - {order.description}
                        <button onClick={() => deleteOrder(order.id)}>Delete</button>
                    </li>
                ))}
            </ul>
            <button onClick={() => window.location.href = '/create-order'}>Create New Order</button>
        </div>
    );
};

export default OrderListPage;