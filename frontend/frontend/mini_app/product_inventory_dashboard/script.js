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
