# FoodieFlow – Restaurant Ordering System

## Project Overview

FoodieFlow is a full-stack Restaurant Ordering System developed as part of the Capstone Project. The application allows customers to browse restaurants, explore menu items, add food to cart, place orders, and track order history. Restaurant owners can manage restaurants, categories, menu items, and customer orders through a dedicated owner dashboard.

The project is built using Spring Boot for backend development, MySQL for database management, and HTML, CSS, and JavaScript for frontend development.

---

# Problem Statement

Traditional restaurant ordering systems often lack centralized digital management and efficient user experience. Customers face issues while placing orders manually and restaurant owners struggle with order tracking and management.

FoodieFlow solves these issues by providing:

* Secure authentication
* Restaurant and menu management
* Online ordering system
* Cart management
* Wallet functionality
* Order tracking
* Role-based access control

---

# Objectives

* Develop a full-stack restaurant ordering platform
* Implement secure JWT-based authentication
* Create role-based access for USER and OWNER
* Allow restaurant owners to manage restaurant operations
* Provide seamless ordering experience to customers
* Implement validations and exception handling

---

# Tech Stack

## Backend

* Java
* Spring Boot
* Spring Security
* JWT Authentication
* Hibernate / JPA
* Maven
* MySQL

## Frontend

* HTML5
* CSS3
* JavaScript

## Tools & Platforms

* IntelliJ IDEA
* VS Code
* Git & GitHub
* Postman
* MySQL Workbench

---

# System Architecture

## Frontend Layer

Handles:

* User Interface
* Form validations
* API calls using Fetch API
* Local storage management
* Dynamic rendering

## Backend Layer

Handles:

* REST APIs
* Business logic
* Authentication & Authorization
* Database operations
* Exception handling

## Database Layer

Stores:

* Users
* Restaurants
* Categories
* Menu Items
* Cart Items
* Orders
* Wallet Balance

---

# Project Modules

## 1. Authentication Module

Features:

* User Registration
* User Login
* JWT Token Generation
* Token Validation
* Role-based Authentication

Technologies Used:

* Spring Security
* JWT
* BCrypt Password Encoder

---

## 2. Restaurant Management Module

Owner can:

* Add restaurant
* Update restaurant
* Delete restaurant
* View restaurant list

Validations:

* Restaurant name validation
* Location validation
* Empty field validation

---

## 3. Category Management Module

Owner can:

* Add category
* Update category
* Delete category
* View categories by restaurant

Special Handling:

* Category cannot be deleted if menu items exist

---

## 4. Menu Management Module

Owner can:

* Add menu items
* Update menu items
* Delete menu items
* View menu items category-wise

Validations:

* Positive price validation
* Name validation
* Empty field validation

---

## 5. Cart Module

User can:

* Add items to cart
* Remove items from cart
* View total amount
* Checkout cart

Features:

* Dynamic cart update
* Real-time total calculation

---

## 6. Order Module

User can:

* Place order
* View order history
* Cancel order within limited time

Owner can:

* Accept order
* Reject order
* Mark order as delivered

Order Status Flow:
PLACED → PENDING → DELIVERED

---

## 7. Wallet Module

Features:

* Wallet balance tracking
* Automatic deduction after order placement
* Refund on order cancellation

---

# Database Design

## Main Entities

* User
* Restaurant
* Category
* MenuItem
* Cart
* CartItem
* Order
* OrderItem

## Relationships

* One Restaurant → Many Categories
* One Category → Many Menu Items
* One User → Many Orders
* One Order → Many Order Items
* One User → One Cart

---

# JWT Authentication Flow

## Login Process

1. User logs in
2. Backend validates credentials
3. JWT token generated
4. Token stored in localStorage
5. Token sent in Authorization header

## Protected API Access

* JwtFilter intercepts requests
* Token validated
* User authenticated
* Access granted based on role

---

# Security Features

* JWT Authentication
* Role-based Authorization
* Protected APIs
* Password Encryption
* Secure API Access

---

# Validations Implemented

## Backend Validations

Using Jakarta Validation:

* @NotBlank
* @Email
* @Pattern
* @Positive
* @Size

## Frontend Validations

Using JavaScript:

* Required field validation
* Regex validation
* Email validation
* Phone number validation
* Positive price validation
* Zip code validation

---

# Exception Handling

Global Exception Handler implemented using:

* @ControllerAdvice
* @ExceptionHandler

Handled Exceptions:

* Validation exceptions
* Resource not found
* Unauthorized access
* Conflict exceptions
* Runtime exceptions

---

# API Endpoints

## User APIs

* POST /api/users/register
* POST /api/users/login
* GET /api/users/{id}

## Restaurant APIs

* GET /api/restaurants
* POST /api/restaurants
* PUT /api/restaurants/{id}
* DELETE /api/restaurants/{id}

## Category APIs

* GET /api/categories/restaurant/{restaurantId}
* POST /api/categories
* PUT /api/categories/{id}
* DELETE /api/categories/{id}

## Menu APIs

* GET /api/menu-items/category/{categoryId}
* POST /api/menu-items
* PUT /api/menu-items/{id}
* DELETE /api/menu-items/{id}

## Cart APIs

* POST /api/cart/add
* GET /api/cart/{userId}
* DELETE /api/cart/item/{id}

## Order APIs

* POST /api/orders/place
* GET /api/orders/user/{userId}
* GET /api/orders/all
* DELETE /api/orders/cancel/{id}
* PUT /api/orders/updateStatus/{id}

---

# Frontend Pages

## User Pages

* index.html
* login.html
* register.html
* home.html
* categories.html
* menu.html
* cart.html
* orders.html

## Owner Pages

* owner.html
* owner-restaurants.html
* owner-categories.html
* owner-menu.html
* owner-orders.html

---

# Folder Structure

## Backend Structure

src/main/java/com/capstone/restaurantorders

* config
* controller
* dto
* entity
* exception
* repository
* security
* service
* service/impl

## Frontend Structure

frontend/

* css/
* js/
* html pages

---

# Key Concepts Used

* REST API
* MVC Architecture
* Dependency Injection
* Constructor Injection
* JWT Authentication
* Spring Security Filters
* Fetch API
* Local Storage
* Role-based Authorization
* Exception Handling
* Validation

---

# Testing

## API Testing

Performed using Postman:

* Authentication APIs
* CRUD APIs
* Protected APIs
* Order APIs

## Frontend Testing

* Validation testing
* Login flow testing
* Cart testing
* Order flow testing
* Error handling testing

---

# Future Enhancements

* Payment Gateway Integration
* Responsive Design
* Live Order Tracking
* Admin Dashboard
* Search & Filters
* Ratings & Reviews
* Cloud Deployment

---

# Challenges Faced

* JWT Authentication implementation
* Role-based authorization
* Managing entity relationships
* Handling merge conflicts
* Frontend-backend integration
* Validation handling
* Error handling improvements

---

# Learnings

* Spring Boot project structure
* REST API development
* JWT authentication flow
* Frontend integration with APIs
* Exception handling
* Git & GitHub workflow
* Database relationship mapping

---

# Conclusion

FoodieFlow successfully demonstrates a complete full-stack restaurant ordering platform with authentication, restaurant management, ordering workflow, validations, and secure API handling. The project follows modular architecture and implements real-world backend and frontend concepts.

---

# Author

Astha Paroha

Capstone Project – Restaurant Ordering System
