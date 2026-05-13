window.onload = function(){

  const token = localStorage.getItem("token");

  if(!token){
    alert("Please login first");
    window.location.href = "login.html";
    return;
  }

  loadMenu();
  loadCart();
};



function loadMenu(){

    const categoryId = localStorage.getItem("categoryId");
    const token = localStorage.getItem("token");

    if (!categoryId) {
        alert("No category selected");
        window.location.href = "categories.html";
        return;
    }

    fetch(`http://localhost:8082/api/menu-items/category/${categoryId}`, {
        headers: {
            "Authorization": "Bearer " + token
        }
    })
    .then(async res => {

        if(!res.ok){
            throw new Error("Failed to load menu");
        }

        return res.json();
    })
    .then(data => displayMenu(data))
    .catch(err => {
        console.error(err);
        alert(err.message);
    });
}



function displayMenu(items) {

    const container = document.getElementById("menuList");
    container.innerHTML = "";

    if(!items || items.length === 0){
        container.innerHTML = "<p>No menu items available</p>";
        return;
    }

    items.forEach(item => {

        let imageUrl =
        "https://images.unsplash.com/photo-1504674900247-0877df9cc836?w=800";

        const name = item.name.toLowerCase();

        if(name.includes("pizza"))
            imageUrl =
            "https://images.unsplash.com/photo-1594007654729-407eedc4be65?w=800";

        else if(name.includes("burger"))
            imageUrl =
            "https://images.unsplash.com/photo-1550547660-d9450f859349?w=800";

        else if(name.includes("chicken"))
            imageUrl =
            "https://images.unsplash.com/photo-1562967916-eb82221dfb92?w=800";

        const div = document.createElement("div");
        div.className = "menu-card";

        div.innerHTML = `
            <img src="${imageUrl}" 
                 onerror="this.src='https://images.unsplash.com/photo-1504674900247-0877df9cc836?w=800'">

            <div class="menu-content">
                <h3>${item.name}</h3>
                <p>₹${item.price}</p>

                <button class="primary"
                        onclick="addToCart(${item.id})">
                        Add
                </button>
            </div>
        `;

        container.appendChild(div);
    });
}



function addToCart(menuItemId) {

    const token = localStorage.getItem("token");
    const userId = localStorage.getItem("userId");

    if(!menuItemId){
        alert("Invalid menu item");
        return;
    }

    fetch("http://localhost:8082/api/cart/add", {
        method: "POST",
        headers: {
            "Authorization": "Bearer " + token,
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            userId: Number(userId),
            menuItemId: menuItemId,
            quantity: 1
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
                errorMessage = "Failed to add item";
            }

            throw new Error(errorMessage);
        }

        return data;
    })
    .then(() => {
        alert("Item added to cart");
        loadCart();
    })
    .catch(err => {
        console.error(err);
        alert(err.message);
    });
}



function loadCart(){

    const userId = localStorage.getItem("userId");
    const token = localStorage.getItem("token");

    fetch(`http://localhost:8082/api/cart/${userId}`,{
        headers:{Authorization:"Bearer "+token}
    })
    .then(async res => {

        if(!res.ok){
            throw new Error("Failed to load cart");
        }

        return res.json();
    })
    .then(data => {

        const div = document.getElementById("cartItems");
        div.innerHTML = "";

        if(!data.items || data.items.length === 0){
            div.innerHTML = "<p>Cart is empty</p>";

            document.getElementById("cartTotal").innerText =
                "Total: ₹0";

            return;
        }

        data.items.forEach(i => {

            div.innerHTML += `
                <p>${i.itemName} x ${i.quantity}</p>
            `;
        });

        document.getElementById("cartTotal").innerText =
            "Total: ₹" + data.totalAmount;
    })
    .catch(err => {
        console.error(err);
        alert(err.message);
    });
}



function goToCart() {
    window.location.href = "cart.html";
}