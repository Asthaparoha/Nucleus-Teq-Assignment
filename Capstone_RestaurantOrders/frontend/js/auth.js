function login(){

const email = document.getElementById("email").value.trim();
const password = document.getElementById("password").value.trim();

if(!email || !password){
    alert("Email and password are required");
    return;
}

fetch("http://localhost:8082/api/users/login",{
method:"POST",
headers:{
"Content-Type":"application/json"
},
body:JSON.stringify({
email,
password
})
})
.then(async res => {

let data;

if(!res.ok){
console.error("Error:", text);
alert("Login failed ");
return;
}

if(!res.ok){
    alert(data.message || "Invalid email or password");
    return;
}

if(!data.token){
alert("Invalid response ");
return;
}

localStorage.setItem("token",data.token);
localStorage.setItem("userId",data.id);
localStorage.setItem("role",data.role);

if(data.role === "OWNER"){
window.location.href="owner.html";
}else{
window.location.href="home.html";
}

})
.catch(err=>{
console.error(err);
alert("Login error");
});
}



function register(){

const firstName = document.getElementById("firstName").value.trim();
const lastName = document.getElementById("lastName").value.trim();
const email = document.getElementById("email").value.trim();
const password = document.getElementById("password").value.trim();
const phoneNumber = document.getElementById("phoneNumber").value.trim();

const street = document.getElementById("street").value.trim();
const city = document.getElementById("city").value.trim();
const state = document.getElementById("state").value.trim();
const zipCode = document.getElementById("zipCode").value.trim();

const role = document.getElementById("role").value;

if(!firstName || !lastName || !email || !password || !phoneNumber ||
   !street || !city || !state || !zipCode){
    alert("Please fill all fields");
    return;
}

fetch("http://localhost:8082/api/users/register",{
method:"POST",
headers:{
"Content-Type":"application/json"
},
body:JSON.stringify({
firstName,
lastName,
email,
password,
phoneNumber,
street,
city,
state,
zipCode,
role
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
        errorMessage = "Registration failed";
    }

    throw new Error(errorMessage);
}

return data;

})
.then(data=>{

alert("Account created successfully ");
window.location.href = "login.html";

})
.catch(err=>{
console.error(err);
alert("Email already exists ");
});
}
