// ================== PRODUCT DATA ==================
// Load from localStorage or use default data

let products = JSON.parse(localStorage.getItem("products")) || [
{ id: 1, name: "Laptop", price: 55000, stock: 5, category: "electronics" },
{ id: 2, name: "Shirt", price: 1200, stock: 10, category: "clothing" },
{ id: 3, name: "Book", price: 500, stock: 0, category: "books" },
{ id: 4, name: "Headphones", price: 2000, stock: 3, category: "electronics" }
];

// ================== SIMULATED API ==================
function fetchProducts() {
return new Promise((resolve) => {
setTimeout(() => {
resolve(products);
}, 1500);
});
}

// ================== DOM REFERENCES ==================
const productList = document.getElementById("productList");
const emptyMessage = document.getElementById("emptyMessage");

// ================== RENDER PRODUCTS ==================
function renderProducts(data) {
productList.innerHTML = "";

if (data.length === 0) {
emptyMessage.style.display = "block";
return;
} else {
emptyMessage.style.display = "none";
}

data.forEach((p) => {
const card = document.createElement("div");
card.className = "product-card";

```
card.innerHTML = `
  <h3>${p.name}</h3>
  <p>Price: ₹${p.price}</p>
  <p>Stock: ${p.stock}</p>
  <p>${p.category}</p>
`;

productList.appendChild(card);
```

});
}
// ================== FILTER + SEARCH + SORT ==================
function applyFilters() {

let filtered = [...products];

const search = document.getElementById("searchInput").value.toLowerCase();
const category = document.getElementById("categorySelect").value;
const sort = document.getElementById("sortSelect").value;
const lowStock = document.getElementById("stockFilter").checked;

if (search) {
filtered = filtered.filter(p =>
p.name.toLowerCase().includes(search)
);
}

if (category !== "all") {
filtered = filtered.filter(p => p.category === category);
}

if (lowStock) {
filtered = filtered.filter(p => p.stock < 5);
}

if (sort === "low") filtered.sort((a, b) => a.price - b.price);
if (sort === "high") filtered.sort((a, b) => b.price - a.price);
if (sort === "az") filtered.sort((a, b) => a.name.localeCompare(b.name));
if (sort === "za") filtered.sort((a, b) => b.name.localeCompare(a.name));

renderProducts(filtered);
}
// ================== ANALYTICS ==================
function updateAnalytics() {

const total = products.length;

const totalValue = products.reduce((sum, p) => {
return sum + (p.price * p.stock);
}, 0);

const outOfStock = products.filter(p => p.stock === 0).length;

document.getElementById("totalProducts").textContent = total;
document.getElementById("totalValue").textContent = totalValue;
document.getElementById("outOfStock").textContent = outOfStock;
}

// ================== CATEGORY DROPDOWN ==================
function populateCategories() {

const select = document.getElementById("categorySelect");
select.innerHTML = `<option value="all">All Categories</option>`;

const categories = [...new Set(products.map(p => p.category))];

categories.forEach(cat => {
const option = document.createElement("option");
option.value = cat;
option.textContent = cat;
select.appendChild(option);
});
}


