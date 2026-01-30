import React, { useState } from 'react';

/**
 * Component to create a new order.
 */
const CreateOrderPage = () => {
    const [name, setName] = useState('');
    const [description, setDescription] = useState('');

    const createOrder = async () => {
        await fetch('/api/orders', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ name, description })
        });
        window.location.href = '/';
    };

    return (
        <div>
            <h1>Create New Order</h1>
            <input type="text" value={name} onChange={(e) => setName(e.target.value)} placeholder="Order Name" />
            <input type="text" value={description} onChange={(e) => setDescription(e.target.value)} placeholder="Order Description" />
            <button onClick={createOrder}>Create Order</button>
        </div>
    );
};

export default CreateOrderPage;