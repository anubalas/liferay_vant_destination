import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';

/**
 * Component to view details of a single order.
 */
const OrderDetailPage = () => {
    const { id } = useParams();
    const [order, setOrder] = useState(null);

    useEffect(() => {
        fetchOrder();
    }, [id]);

    const fetchOrder = async () => {
        const response = await fetch(`/api/orders/${id}`);
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