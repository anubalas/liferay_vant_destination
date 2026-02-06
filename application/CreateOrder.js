import React, { useState } from 'react';
import { useHistory } from 'react-router-dom';

const CreateOrder = () => {
    const [name, setName] = useState('');
    const [description, setDescription] = useState('');
    const history = useHistory();

    const handleSubmit = (e) => {
        e.preventDefault();
        fetch('/orders', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ name, description })
        })
        .then(() => history.push('/'));
    };

    return (
        <form onSubmit={handleSubmit}>
            <h1>Create Order</h1>
            <div>
                <label>Name:</label>
                <input type="text" value={name} onChange={(e) => setName(e.target.value)} required />
            </div>
            <div>
                <label>Description:</label>
                <textarea value={description} onChange={(e) => setDescription(e.target.value)} required></textarea>
            </div>
            <button type="submit">Create</button>
        </form>
    );
};

export default CreateOrder;