window.onload = function(){

  loadRestaurants();

  const token = localStorage.getItem("token");

  if(token){
    loadWallet();
  } else {
    document.getElementById("wallet").innerText =
      "Login to see wallet";
  }
};



function loadRestaurants(){

  const token = localStorage.getItem("token");

  fetch("http://localhost:8082/api/restaurants", {
    headers: token ? { Authorization:"Bearer "+token } : {}
  })
  .then(async res => {

    if(!res.ok){
      throw new Error("Failed to load restaurants");
    }

    return res.json();
  })
  .then(data => {

    const div = document.getElementById("restaurants");
    div.innerHTML = "";

    if(!data || data.length === 0){
      div.innerHTML = "<p>No restaurants available</p>";
      return;
    }

    data.forEach(r => {

      let imageUrl =
      "https://images.unsplash.com/photo-1504674900247-0877df9cc836?w=800";

      const name = r.name.toLowerCase();

      if(name.includes("dominos") || name.includes("pizza")){
        imageUrl =
        "https://images.unsplash.com/photo-1594007654729-407eedc4be65?w=800";
      }
      else if(name.includes("kfc") || name.includes("chicken")){
        imageUrl =
        "https://images.unsplash.com/photo-1562967916-eb82221dfb92?w=800";
      }
      else if(name.includes("burger")){
        imageUrl =
        "https://images.unsplash.com/photo-1550547660-d9450f859349?w=800";
      }

      div.innerHTML += `
        <div class="card" onclick="openRest(${r.id})">

          <img src="${imageUrl}" 
          onerror="this.onerror=null;
          this.src='https://images.unsplash.com/photo-1504674900247-0877df9cc836?w=800'">

          <div class="card-content">
            <h3>${r.name}</h3>
            <p>${r.location}</p>
          </div>

        </div>
      `;
    });
  })
  .catch(err => {
    console.error(err);
    document.getElementById("restaurants").innerHTML =
      "<p>Failed to load restaurants</p>";
  });
}



function openRest(id){

  if(!id){
    alert("Invalid restaurant");
    return;
  }

  localStorage.setItem("restaurantId",id);
  window.location.href="categories.html";
}



function loadWallet(){

  const userId = localStorage.getItem("userId");
  const token = localStorage.getItem("token");

  if(!userId || !token){
    document.getElementById("wallet").innerText =
      "Login to see wallet";
    return;
  }

  fetch(`http://localhost:8082/api/users/${userId}`,{
    headers:{Authorization:"Bearer "+token}
  })
  .then(async res => {

    if(!res.ok){
      throw new Error("Failed to load wallet");
    }

    return res.json();
  })
  .then(data => {

    document.getElementById("wallet").innerText =
      "Wallet Balance: ₹" + (data.walletBalance ?? 0);
  })
  .catch(err => {

    console.error(err);

    document.getElementById("wallet").innerText =
      "Failed to load wallet";
  });
}



function logout(){

  localStorage.clear();
  window.location.href = "index.html";
}