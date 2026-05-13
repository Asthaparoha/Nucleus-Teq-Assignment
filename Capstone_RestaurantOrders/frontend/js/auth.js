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

<<<<<<< HEAD
if(!res.ok){
console.error("Error:", text);
alert("Login failed ");
return;
=======
try{
    data = await res.json();
}catch{
    data = {};
>>>>>>> c3f4998 (Added validations and improved error handling)
}

if(!res.ok){
    alert(data.message || "Invalid email or password");
    return;
}

if(!data.token){
<<<<<<< HEAD
alert("Invalid response ");
=======
alert("Invalid response");
>>>>>>> c3f4998 (Added validations and improved error handling)
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
<<<<<<< HEAD
alert("Login error");
=======
alert("Login failed");
>>>>>>> c3f4998 (Added validations and improved error handling)
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
<<<<<<< HEAD
=======
    return;
}

if(!/^[A-Za-z]+$/.test(firstName)){
    alert("First name should contain only alphabets");
    return;
}

if(!/^[A-Za-z]+$/.test(lastName)){
    alert("Last name should contain only alphabets");
    return;
}

if(!/^[0-9]{10}$/.test(phoneNumber)){
    alert("Phone number must be exactly 10 digits");
    return;
}

if(password.length < 6){
    alert("Password must be at least 6 characters");
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

if(!/^[0-9]{6}$/.test(zipCode)){
    alert("Zip code must be exactly 6 digits");
>>>>>>> c3f4998 (Added validations and improved error handling)
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

<<<<<<< HEAD
alert("Account created successfully ");
=======
alert("Account created successfully");
>>>>>>> c3f4998 (Added validations and improved error handling)
window.location.href = "login.html";

})
.catch(err=>{
console.error(err);
<<<<<<< HEAD
alert("Email already exists ");
=======
alert(err.message);
>>>>>>> c3f4998 (Added validations and improved error handling)
});
}
