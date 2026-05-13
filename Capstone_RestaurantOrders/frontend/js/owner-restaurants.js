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
        div.innerHTML = "<p>No restaurants found</p>";
        return;
    }

    list.forEach(r => {

        let img =
        "https://images.unsplash.com/photo-1504674900247-0877df9cc836?w=800";

        const name = r.name.toLowerCase();

        if(name.includes("pizza"))
            img =
            "https://images.unsplash.com/photo-1594007654729-407eedc4be65?w=800";

        else if(name.includes("burger"))
            img =
            "https://images.unsplash.com/photo-1550547660-d9450f859349?w=800";

        else if(name.includes("kfc") || name.includes("chicken"))
            img =
            "https://images.unsplash.com/photo-1562967916-eb82221dfb92?w=800";

        div.innerHTML += `
            <div class="rest-card">

                <img src="${img}" 
                     onerror="this.src='https://images.unsplash.com/photo-1504674900247-0877df9cc836?w=800'">

                <div class="rest-content">

                    <h3>${r.name}</h3>

                    <p>${r.description}</p>

                    <p>${r.location}</p>

                    <div class="rest-actions">

                        <button onclick="setEdit(${r.id}, '${r.name}', '${r.description}', '${r.location}')">
                            Edit
                        </button>

                        <button onclick="deleteRestaurant(${r.id})">
                            Delete
                        </button>

                    </div>

                </div>

            </div>
        `;
    });
}



function setEdit(id, name, description, location) {

    editId = id;

    document.getElementById("name").value = name;
    document.getElementById("description").value = description;
    document.getElementById("location").value = location;
}



function addRestaurant() {

    const name =
    document.getElementById("name").value.trim();

    const description =
    document.getElementById("description").value.trim();

    const location =
    document.getElementById("location").value.trim();

    const token = localStorage.getItem("token");

    if (!name || !description || !location) {
        alert("Fill all fields");
        return;
    }

    if(!/^[A-Za-z ]+$/.test(name)){
        alert("Restaurant name should contain only alphabets");
        return;
    }

    if(description.length < 5){
        alert("Description must be at least 5 characters");
        return;
    }

    if(!/^[A-Za-z0-9 ,.-]+$/.test(location)){
        alert("Invalid location");
        return;
    }

    const url = editId
        ? `http://localhost:8082/api/restaurants/${editId}`
        : "http://localhost:8082/api/restaurants";

    const method = editId ? "PUT" : "POST";

    fetch(url, {
        method: method,
        headers: {
            "Authorization": "Bearer " + token,
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            name,
            description,
            location
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
                errorMessage = "Operation failed";
            }

            throw new Error(errorMessage);
        }

        return data;
    })
    .then(() => {

        alert(editId ? "Restaurant updated" : "Restaurant added");

        editId = null;

        document.getElementById("name").value = "";
        document.getElementById("description").value = "";
        document.getElementById("location").value = "";

        loadRestaurants();
    })
    .catch(err => {
        console.error(err);
        alert(err.message);
    });
}



function deleteRestaurant(id) {

    const token = localStorage.getItem("token");

    if(!id){
        alert("Invalid restaurant");
        return;
    }

    fetch(`http://localhost:8082/api/restaurants/${id}`, {
        method: "DELETE",
        headers: { Authorization: "Bearer " + token }
    })
    .then(async res => {

        let message = "";

        try{
            message = await res.text();
        }catch{
            message = "Delete failed";
        }

        if (!res.ok){
            throw new Error(message || "Cannot delete restaurant");
        }

        return message;
    })
    .then(() => {

        alert("Restaurant deleted successfully");

        loadRestaurants();
    })
    .catch(err => {

        console.error(err);

        alert(err.message);
    });
}