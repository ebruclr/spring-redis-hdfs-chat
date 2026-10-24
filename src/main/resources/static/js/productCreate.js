document.addEventListener("DOMContentLoaded", () => {
    const productModal = document.getElementById("productModal");
    const openProductBtn = document.getElementById("openProductModalBtn");
    const closeProductBtn = document.getElementById("closeProductModalBtn");

    if (openProductBtn && productModal) {
        openProductBtn.addEventListener("click", () => {
            productModal.style.display = "block";
        });
    }

    if (closeProductBtn && productModal) {
        closeProductBtn.addEventListener("click", () => {
            productModal.style.display = "none";
        });
    }

    window.addEventListener("click", (e) => {
        if (e.target === productModal) {
            productModal.style.display = "none";
        }
    });
});

document.getElementById('create-product-form')?.addEventListener('submit', async (e) => {
    e.preventDefault();

    const formData = new FormData();
    formData.append('name', document.getElementById('prodName').value);
    formData.append('supplier', document.getElementById('prodSupplier').value);
    formData.append('price', document.getElementById('prodPrice').value);

    const fileInput = document.getElementById('prodImage');
    if (fileInput.files[0]) {
        formData.append('image', fileInput.files[0]);
    }

    const response = await fetch('/api/products', {
        method: 'POST',
        body: formData
    });

    if (response.ok) {
        alert('Product & HDFS image created!');
        window.location.reload();
    } else {
        alert('Failed to upload product');
    }
});