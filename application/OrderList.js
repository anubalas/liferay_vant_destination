import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';

const OrderList = () => {
    const [orders, setOrders] = useState([]);

    useEffect(() => {
        fetch('/orders')
            .then(response => response.json())
            .then(data => setOrders(data));
    }, []);

    const deleteOrder = (id) => {
        fetch(`/orders/${id}`, { method: 'DELETE' })
            .then(() => setOrders(orders.filter(order => order.id !== id)));
    };

    return (
        <div>
            <h1>Order List</h1>
            <Link to="/create">Create New Order</Link>
            <ul>
                {orders.map(order => (
                    <li key={order.id}>
                        <Link to={`/${order.id}`}>{order.name}</Link>
                        <button onClick={() => deleteOrder(order.id)}>Delete</button>
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default OrderList;