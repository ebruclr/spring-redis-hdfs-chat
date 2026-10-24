document.addEventListener("DOMContentLoaded", () => {
    const orderModal = document.getElementById("orderModal");
    const openOrderBtn = document.getElementById("openOrderModalBtn");
    const closeOrderBtn = document.getElementById("closeOrderModalBtn");
    const orderForm = document.getElementById('create-order-form');
    const modalTitle = document.getElementById('orderModalTitle');

    if (openOrderBtn && orderModal) {
        openOrderBtn.addEventListener("click", () => {
            orderForm.reset();
            document.getElementById('orderId').value = ""; // Clear ID
            modalTitle.innerText = "Create New Order";
            orderModal.style.display = "block";
        });
    }

    if (closeOrderBtn && orderModal) {
        closeOrderBtn.addEventListener("click", () => {
            orderModal.style.display = "none";
        });
    }

    window.addEventListener("click", (e) => {
        if (e.target === orderModal) {
            orderModal.style.display = "none";
        }
    });

    document.querySelectorAll('.btn-edit-order').forEach(btn => {
        btn.addEventListener('click', (e) => {
            const button = e.currentTarget;

            // Extract data attributes
            const id = button.getAttribute('data-id');
            const customerId = button.getAttribute('data-customer-id');
            const productId = button.getAttribute('data-product-id');
            const city = button.getAttribute('data-city');
            const status = button.getAttribute('data-status');

            // Populate Modal Fields
            document.getElementById('orderId').value = id;
            document.getElementById('customerId').value = customerId;
            document.getElementById('productId').value = productId;
            document.getElementById('city').value = city;
            document.getElementById('deliveryStatus').value = status;

            modalTitle.innerText = `Update Order #${id}`;
            orderModal.style.display = "block";
        });
    });
});

document.getElementById('create-order-form')?.addEventListener('submit', async (e) => {
    e.preventDefault();

    const orderId = document.getElementById('orderId').value;
    const isUpdate = orderId !== "";

    const payload = {
        customerId: parseInt(document.getElementById('customerId').value),
        productId: parseInt(document.getElementById('productId').value),
        city: document.getElementById('city').value,
        deliveryStatus: document.getElementById('deliveryStatus').value
    };

    const url = isUpdate ? `/api/orders/${orderId}` : '/api/orders';
    const method = isUpdate ? 'PUT' : 'POST';

    const response = await fetch(url, {
        method: method,
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(payload)
    });

    if (response.ok) {
        window.location.reload();
    } else {
        alert(isUpdate ? 'Failed to update order' : 'Failed to create order');
    }
});

async function deleteOrder(orderId) {
    if (!confirm(`Are you sure you want to delete order #${orderId}?`)) return;

    const response = await fetch(`/api/orders/${orderId}`, {
        method: 'DELETE'
    });

    if (response.ok) {
        window.location.reload();
    } else {
        alert('Failed to delete order');
    }
}