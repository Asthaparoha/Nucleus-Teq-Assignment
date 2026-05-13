# Restaurant Orders Portal - Software Requirement Specification (SRS)

## 1. Introduction

The Restaurant Orders Portal is a web-based application that enables customers to browse restaurants, explore menus, and place food orders. Restaurant owners can manage restaurants, menu items, and process incoming orders efficiently.

The system demonstrates REST API design, authentication, role-based access, and order lifecycle management.

---

## 2. Actors and Roles

### 2.1 Customer (USER)

* Register and login
* Browse restaurants and menus
* Add items to cart
* Place and track orders
* Cancel orders within allowed time
* View order history

### 2.2 Restaurant Owner

* Manage restaurants
* Manage categories and menu items
* View and process incoming orders
* Update order status

---

## 3. Functional Requirements

### 3.1 User Management

* User registration with unique email
* Secure login using JWT authentication
* Password encryption using BCrypt
* Wallet initialization with ₹1000 balance

---

### 3.2 Restaurant Management

* Create, update, and delete restaurants
* Manage categories within restaurants
* Add, update, and delete menu items
* Assign menu items to categories

---

### 3.3 Cart Management

* Add items to cart
* Update item quantity
* Remove items from cart
* Restrict cart to one restaurant at a time

---

### 3.4 Order Management

* Place order from cart
* Validate wallet balance before order
* Deduct wallet balance on successful order
* Track order status

---

### 3.5 Order Lifecycle

* PLACED → PENDING → DELIVERED → COMPLETED
* CANCELLED (only within 30 seconds)

---

### 3.6 Order Cancellation

* Allowed within 30 seconds of order placement
* Not allowed after time limit expires

---

### 3.7 Order History

* View all previous orders
* Includes items, total amount, date, and status

---

## 4. Non-Functional Requirements

### 4.1 Security

* JWT-based authentication
* Role-based access control
* Encrypted password storage

---

### 4.2 Reliability

* Transactional order processing
* Atomic operations (order + wallet deduction)

---

### 4.3 Maintainability

* Layered architecture (Controller, Service, Repository)

---

## 5. System Modules

* User Module
* Restaurant Module
* Cart Module
* Order Module

---

## 6. Business Rules

* Initial wallet balance: ₹1000
* One restaurant per order
* Order cancellation allowed within 30 seconds
* Only authenticated users can place orders

---

## 7. Assumptions

* Payment is simulated via wallet
* Single-user session handling
