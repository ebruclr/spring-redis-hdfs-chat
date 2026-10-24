document.addEventListener("DOMContentLoaded", () => {
    const customerModal = document.getElementById("customerModal");
    const openCustomerBtn = document.getElementById("openCustomerModalBtn");
    const closeCustomerBtn = document.getElementById("closeCustomerModalBtn");

    if (openCustomerBtn && customerModal) {
        openCustomerBtn.addEventListener("click", () => {
            customerModal.style.display = "block";
        });
    }

    if (closeCustomerBtn && customerModal) {
        closeCustomerBtn.addEventListener("click", () => {
            customerModal.style.display = "none";
        });
    }

    window.addEventListener("click", (e) => {
        if (e.target === customerModal) {
            customerModal.style.display = "none";
        }
    });
});

document.getElementById('create-customer-form')?.addEventListener('submit', async (e) => {
    e.preventDefault();

    const payload = {
        name: document.getElementById('custName').value,
        phone: document.getElementById('custPhone').value,
        address: document.getElementById('custAddress').value
    };

    const response = await fetch('/api/customers', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(payload)
    });

    if (response.ok) {
        alert('Customer saved successfully!');
        window.location.reload();
    } else {
        alert('Failed to save customer');
    }
});