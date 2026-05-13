window.onload = function(){

  const token = localStorage.getItem("token");

  if(!token){
    alert("Please login first");
    window.location.href = "login.html";
    return;
  }

  loadWallet();
  loadCart();
};



function loadCart() {

    const userId = localStorage.getItem("userId");
    const token = localStorage.getItem("token");

    fetch(`http://localhost:8082/api/cart/${userId}`, {
        headers: { "Authorization": "Bearer " + token }
    })
    .then(async res => {

        if(!res.ok){
            throw new Error("Failed to load cart");
        }

        return res.json();
    })
    .then(data => displayCart(data))
    .catch(err => {
        console.error(err);
        alert(err.message);
    });
}



function displayCart(cart) {

    const container = document.getElementById("cartList");
    const totalDiv = document.getElementById("totalAmount");

    container.innerHTML = "";

    if (!cart.items || cart.items.length === 0) {
        container.innerHTML = "<p>Your cart is empty</p>";
        totalDiv.innerText = "₹ 0";
        return;
    }

    cart.items.forEach(item => {

        let img = "https://images.unsplash.com/photo-1504674900247-0877df9cc836?w=800";

        const name = item.itemName.toLowerCase();

        if(name.includes("pizza"))
            img = "https://images.unsplash.com/photo-1594007654729-407eedc4be65?w=800";

        else if(name.includes("burger"))
            img = "https://images.unsplash.com/photo-1550547660-d9450f859349?w=800";

        else if(name.includes("chicken"))
            img = "https://images.unsplash.com/photo-1562967916-eb82221dfb92?w=800";

        container.innerHTML += `
            <div class="cart-item">
                <img src="${img}" 
                     onerror="this.src='https://images.unsplash.com/photo-1504674900247-0877df9cc836?w=800'">

                <div class="cart-item-content">
                    <h3>${item.itemName}</h3>
                    <p>₹${item.price} × ${item.quantity}</p>
                    <button onclick="removeItem(${item.cartItemId})">Remove</button>
                </div>
            </div>
        `;
    });

    totalDiv.innerText = "₹ " + cart.totalAmount;
}



function removeItem(cartItemId) {

    const token = localStorage.getItem("token");

    fetch(`http://localhost:8082/api/cart/item/${cartItemId}`, {
        method: "DELETE",
        headers: { "Authorization": "Bearer " + token }
    })
    .then(async res => {

        if(!res.ok){
            throw new Error("Failed to remove item");
        }

        loadCart();
        loadWallet();
    })
    .catch(err => {
        console.error(err);
        alert(err.message);
    });
}



function placeOrder() {

    const userId = localStorage.getItem("userId");
    const token = localStorage.getItem("token");

    const street = document.getElementById("street").value.trim();
    const city = document.getElementById("city").value.trim();
    const state = document.getElementById("state").value.trim();
    const zip = document.getElementById("zip").value.trim();

    if (!street || !city || !state || !zip) {
        alert("All address fields are required");
        return;
    }

    if(!/^[A-Za-z ]+$/.test(city)){
        alert("City should contain only alphabets");
        return;
    }

    if(!/^[A-Za-z ]+$/.test(state)){
        alert("State should contain only alphabets");
        return;
    }

    if(!/^[0-9]{6}$/.test(zip)){
        alert("Zip code must be exactly 6 digits");
        return;
    }

    fetch("http://localhost:8082/api/orders/place", {
        method: "POST",
        headers: {
            "Authorization": "Bearer " + token,
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            userId: userId,
            street: street,
            city: city,
            state: state,
            zipCode: zip
        })
    })
    .then(async res => {

        let data;

        try{
            data = await res.json();
        }catch{
            data = {};
        }

        if(!res.ok){

            let errorMessage = "";

            if(typeof data === "object"){
                for(let key in data){
                    errorMessage += data[key] + "\n";
                }
            }else{
                errorMessage = "Order failed";
            }

            throw new Error(errorMessage);
        }

        return data;
    })
    .then(() => {
        alert("Order placed successfully");
        loadWallet();
        window.location.href = "orders.html";
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
            "Wallet: ₹" + data.walletBalance;
    })
    .catch(err => console.error(err));
}



function logout(){
  localStorage.clear();
  window.location.href = "index.html";
}