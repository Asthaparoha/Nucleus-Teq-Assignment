window.onload = function(){

  const token = localStorage.getItem("token");

  if(!token){
    alert("Please login first");
    window.location.href = "login.html";
    return;
  }

  loadWallet();
  loadOrders();
};



function loadOrders() {

    const userId = localStorage.getItem("userId");
    const token = localStorage.getItem("token");

    fetch(`http://localhost:8082/api/orders/user/${userId}`, {
        headers: {
            "Authorization": "Bearer " + token
        }
    })
    .then(async res => {

        if(!res.ok){
            throw new Error("Failed to load orders");
        }

        return res.json();
    })
    .then(data => {
        displayOrders(data);
    })
    .catch(err => {
        console.error(err);
        alert(err.message);
    });
}



function displayOrders(orders) {

    const container = document.getElementById("ordersList");
    container.innerHTML = "";

    if (!orders || orders.length === 0) {
        container.innerHTML = "<p>No orders yet</p>";
        return;
    }

    orders.forEach(order => {

        const createdTime = new Date(order.createdAt);
        const now = new Date();

        const diffSeconds = (now - createdTime) / 1000;

        const canCancel =
            diffSeconds <= 30 && order.status === "PLACED";

        let img =
        "https://images.unsplash.com/photo-1504674900247-0877df9cc836?w=800";

        container.innerHTML += `
            <div class="order-card">

                <img src="${img}"
                     onerror="this.src='https://images.unsplash.com/photo-1504674900247-0877df9cc836?w=800'">

                <div class="order-content">

                    <h3>Order #${order.orderId}</h3>

                    <p>Total: ₹${order.totalAmount}</p>

                    <p class="status ${order.status}">
                        Status: ${order.status}
                    </p>

                    <p>
                        Time:
                        ${new Date(order.createdAt).toLocaleString()}
                    </p>

                    ${
                        canCancel
                        ? `<button onclick="cancelOrder(${order.orderId})">
                                Cancel
                           </button>`
                        : ""
                    }

                </div>

            </div>
        `;
    });
}



function cancelOrder(orderId) {

    const token = localStorage.getItem("token");

    if(!orderId){
        alert("Invalid order");
        return;
    }

    fetch(`http://localhost:8082/api/orders/cancel/${orderId}`, {
        method: "DELETE",
        headers: {
            "Authorization": "Bearer " + token
        }
    })
    .then(async res => {

        let message = "";

        try{
            message = await res.text();
        }catch{
            message = "Cancel failed";
        }

        if(!res.ok){
            throw new Error(message || "Cancel failed");
        }

        return message;
    })
    .then(msg => {

        alert(msg || "Order cancelled successfully");

        loadWallet();
        loadOrders();
    })
    .catch(err => {

        console.error(err);
        alert(err.message);
    });
}



function loadWallet() {

    const userId = localStorage.getItem("userId");
    const token = localStorage.getItem("token");

    if (!userId || !token) return;

    fetch(`http://localhost:8082/api/users/${userId}`, {
        headers: {
            "Authorization": "Bearer " + token
        }
    })
    .then(async res => {

        if(!res.ok){
            throw new Error("Failed to load wallet");
        }

        return res.json();
    })
    .then(data => {

        document.getElementById("wallet").innerText =
            "Wallet: ₹" + (data.walletBalance ?? 0);
    })
    .catch(err => {
        console.error(err);
    });
}



function logout(){

  localStorage.clear();
  window.location.href = "index.html";
}