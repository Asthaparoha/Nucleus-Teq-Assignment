window.onload = function () {

    const token = localStorage.getItem("token");

    if(!token){
        alert("Please login first");
        window.location.href = "login.html";
        return;
    }

    loadOrders();
};



function loadOrders() {

    const token = localStorage.getItem("token");

    fetch("http://localhost:8082/api/orders/all", {
        headers: { Authorization: "Bearer " + token }
    })
    .then(async res => {

        if(!res.ok){
            throw new Error("Failed to load orders");
        }

        return res.json();
    })
    .then(data => displayOrders(data))
    .catch(err => {
        console.error(err);
        alert(err.message);
    });
}



function displayOrders(orders) {

    const container = document.getElementById("ordersList");
    container.innerHTML = "";

    if (!orders || orders.length === 0) {
        container.innerHTML = "<p>No orders found</p>";
        return;
    }

    orders.forEach(order => {

        const img =
        "https://images.unsplash.com/photo-1504674900247-0877df9cc836?w=800";

        let buttons = "";

        if(order.status === "PLACED"){

            buttons = `
                <button onclick="updateStatus(${order.orderId}, 'PENDING')">
                    Accept
                </button>

                <button onclick="updateStatus(${order.orderId}, 'CANCELLED')">
                    Reject
                </button>
            `;
        }
        else if(order.status === "PENDING"){

            buttons = `
                <button onclick="updateStatus(${order.orderId}, 'DELIVERED')">
                    Mark Delivered
                </button>
            `;
        }

        container.innerHTML += `
            <div class="owner-order-card">

                <img src="${img}"
                     onerror="this.src='https://images.unsplash.com/photo-1504674900247-0877df9cc836?w=800'">

                <div class="order-info">

                    <h3>Order #${order.orderId}</h3>

                    <p>Total: ₹${order.totalAmount}</p>

                    <p class="status ${order.status}">
                        Status: ${order.status}
                    </p>

                    <p>
                        Time:
                        ${new Date(order.createdAt).toLocaleString()}
                    </p>

                    <div class="order-actions">
                        ${buttons}
                    </div>

                </div>

            </div>
        `;
    });
}



function updateStatus(orderId, status) {

    if(!orderId){
        alert("Invalid order");
        return;
    }

    if(!status){
        alert("Invalid status");
        return;
    }

    if(!confirm("Are you sure?")){
        return;
    }

    const token = localStorage.getItem("token");

    fetch(`http://localhost:8082/api/orders/updateStatus/${orderId}?status=${status}`, {
        method: "PUT",
        headers: { Authorization: "Bearer " + token }
    })
    .then(async res => {

        let message = "";

        try{
            message = await res.text();
        }catch{
            message = "Update failed";
        }

        if(!res.ok){
            throw new Error(message || "Failed to update order");
        }

        return message;
    })
    .then(msg => {

        alert(msg || "Order updated successfully");

        loadOrders();
    })
    .catch(err => {

        console.error(err);

        alert(err.message);
    });
}