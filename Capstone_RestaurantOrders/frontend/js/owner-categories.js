let editId = null;



window.onload = function () {

    const token = localStorage.getItem("token");

    if(!token){
        alert("Please login first");
        window.location.href = "login.html";
        return;
    }

    loadRestaurants();
};



function loadRestaurants() {

    const token = localStorage.getItem("token");

    fetch("http://localhost:8082/api/restaurants", {
        headers: { Authorization: "Bearer " + token }
    })
    .then(async res => {

        if(!res.ok){
            throw new Error("Failed to load restaurants");
        }

        return res.json();
    })
    .then(data => {

        const select = document.getElementById("restaurantSelect");

        select.innerHTML =
            `<option value="">Select Restaurant</option>`;

        if(!data || data.length === 0){
            return;
        }

        data.forEach(r => {

            select.innerHTML += `
                <option value="${r.id}">
                    ${r.name}
                </option>
            `;
        });
    })
    .catch(err => {
        console.error(err);
        alert(err.message);
    });
}



function loadCategories() {

    const token = localStorage.getItem("token");
    const restaurantId =
    document.getElementById("restaurantSelect").value;

    if (!restaurantId){
        alert("Please select restaurant");
        return;
    }

    fetch(`http://localhost:8082/api/categories/restaurant/${restaurantId}`, {
        headers: { Authorization: "Bearer " + token }
    })
    .then(async res => {

        if(!res.ok){
            throw new Error("Failed to load categories");
        }

        return res.json();
    })
    .then(data => display(data))
    .catch(err => {
        console.error(err);
        alert(err.message);
    });
}



function display(list) {

    const div = document.getElementById("list");
    div.innerHTML = "";

    if(!list || list.length === 0){
        div.innerHTML = "<p>No categories available</p>";
        return;
    }

    list.forEach(c => {

        let imgList = [
            "https://images.unsplash.com/photo-1504674900247-0877df9cc836?w=800",
            "https://images.unsplash.com/photo-1550547660-d9450f859349?w=800",
            "https://images.unsplash.com/photo-1594007654729-407eedc4be65?w=800",
            "https://images.unsplash.com/photo-1562967916-eb82221dfb92?w=800",
            "https://images.unsplash.com/photo-1544145945-f90425340c7e?w=800"
        ];

        const img =
        imgList[Math.floor(Math.random() * imgList.length)];

        div.innerHTML += `
            <div class="rest-card">

                <img src="${img}"
                     onerror="this.src='https://images.unsplash.com/photo-1504674900247-0877df9cc836?w=800'">

                <div class="rest-content">

                    <h3>${c.name}</h3>

                    <div class="rest-actions">

                        <button onclick="setEdit(${c.id}, '${c.name}')">
                            Edit
                        </button>

                        <button onclick="deleteCategory(${c.id})">
                            Delete
                        </button>

                    </div>

                </div>

            </div>
        `;
    });
}



function setEdit(id, name) {

    editId = id;
    document.getElementById("name").value = name;
}



function saveCategory() {

    const name =
    document.getElementById("name").value.trim();

    const restaurantId =
    document.getElementById("restaurantSelect").value;

    const token = localStorage.getItem("token");

    if (!name || !restaurantId) {
        alert("Select restaurant and enter category name");
        return;
    }

    if(!/^[A-Za-z ]+$/.test(name)){
        alert("Category name should contain only alphabets");
        return;
    }

    const url = editId
        ? `http://localhost:8082/api/categories/${editId}`
        : "http://localhost:8082/api/categories";

    const method = editId ? "PUT" : "POST";

    fetch(url, {
        method: method,
        headers: {
            "Authorization": "Bearer " + token,
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            name,
            restaurantId
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
                errorMessage = "Failed to save category";
            }

            throw new Error(errorMessage);
        }

        return data;
    })
    .then(() => {

        alert(editId ? "Category updated" : "Category added");

        editId = null;

        document.getElementById("name").value = "";

        loadCategories();
    })
    .catch(err => {
        console.error(err);
        alert(err.message);
    });
}



function deleteCategory(id){

  const token = localStorage.getItem("token");

  if(!id){
      alert("Invalid category");
      return;
  }

  fetch(`http://localhost:8082/api/categories/${id}`, {
    method: "DELETE",
    headers: {
      "Authorization": "Bearer " + token
    }
  })
  .then(async res => {

    let data;

    try {
      data = await res.json();
    } catch {
      data = { message: "Unexpected error" };
    }

    if (!res.ok) {
      throw new Error(data.message || "Cannot delete category");
    }

    return data;
  })
  .then(() => {
    alert("Category deleted successfully");
    loadCategories();
  })
  .catch(err => {
    console.error(err);
    alert(err.message);
  });
}