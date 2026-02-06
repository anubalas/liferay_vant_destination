import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';

const OrderDetail = () => {
    const { id } = useParams();
    const [order, setOrder] = useState(null);

    useEffect(() => {
        fetch(`/orders/${id}`)
            .then(response => response.json())
            .then(data => setOrder(data));
    }, [id]);

    if (!order) return <div>Loading...</div>;

    return (
        <div>
            <h1>{order.name}</h1>
            <p>{order.description}</p>
            <p>Created At: {new Date(order.createdAt).toLocaleString()}</p>
        </div>
    );
};

export default OrderDetail;