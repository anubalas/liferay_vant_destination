import React, { useEffect, useState } from 'react';

/**
 * Component to view a single order's details.
 */
const OrderDetailPage = ({ match }) => {
    const [order, setOrder] = useState(null);

    useEffect(() => {
        fetchOrder();
    }, []);

    const fetchOrder = async () => {
        const response = await fetch(`/api/orders/${match.params.id}`);
        const data = await response.json();
        setOrder(data);
    };

    if (!order) return <div>Loading...</div>;

    return (
        <div>
            <h1>{order.name}</h1>
            <p>{order.description}</p>
            <p>Created At: {order.createdAt}</p>
        </div>
    );
};

export default OrderDetailPage;