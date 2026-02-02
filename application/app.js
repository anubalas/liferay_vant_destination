const orderListDiv = document.getElementById('order-list');
const orderDetailDiv = document.getElementById('order-detail');
const createOrderButton = document.getElementById('create-order');

// Fetch all orders and display them
function fetchOrders() {
    fetch('/orders')
        .then(response => response.json())
        .then(orders => {
            orderListDiv.innerHTML = '';
            orders.forEach(order => {
                const orderElement = document.createElement('div');
                orderElement.innerText = order.name;
                orderElement.onclick = () => viewOrder(order.id);
                orderListDiv.appendChild(orderElement);
            });
        });
}

// View a specific order
function viewOrder(id) {
    fetch(`/orders/${id}`)
        .then(response => response.json())
        .then(order => {
            orderDetailDiv.innerHTML = `<h2>${order.name}</h2><p>${order.description}</p>`;
        });
}

// Create a new order
createOrderButton.onclick = () => {
    const newOrder = { name: 'New Order', description: 'Description for new order' };
    fetch('/orders', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(newOrder)
    }).then(() => fetchOrders());
};

// Delete an order
function deleteOrder(id) {
    fetch(`/orders/${id}`, { method: 'DELETE' })
        .then(() => fetchOrders());
}

// Initial fetch of orders
fetchOrders();