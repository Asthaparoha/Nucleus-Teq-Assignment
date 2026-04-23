# Database Schema - Restaurant Orders Portal

## 1. User

* id (PK)
* first_name
* last_name
* email (unique)
* password
* phone_number
* role (USER / RESTAURANT_OWNER)
* wallet_balance
* created_at
* updated_at

---

## 2. Restaurant

* id (PK)
* name
* owner_id (FK → User.id)
* status (OPEN / CLOSED)
* created_at
* updated_at

---

## 3. Category

* id (PK)
* name
* restaurant_id (FK → Restaurant.id)

---

## 4. MenuItem

* id (PK)
* name
* description
* price
* category_id (FK → Category.id)
* restaurant_id (FK → Restaurant.id)
* is_available (BOOLEAN)

---

## 5. Cart

* id (PK)
* user_id (FK → User.id)
* restaurant_id (FK → Restaurant.id)
* created_at

---

## 6. CartItem

* id (PK)
* cart_id (FK → Cart.id)
* menu_item_id (FK → MenuItem.id)
* quantity
* price

---

## 7. Orders

* id (PK)
* user_id (FK → User.id)
* restaurant_id (FK → Restaurant.id)
* total_amount
* status (PLACED, PENDING, DELIVERED, COMPLETED, CANCELLED)
* created_at
* updated_at

---

## 8. OrderItem

* id (PK)
* order_id (FK → Orders.id)
* menu_item_id (FK → MenuItem.id)
* quantity
* price

---

## 🔗 Relationships Summary

* One User → Many Orders
* One Restaurant → Many MenuItems
* One Cart → Many CartItems
* One Order → Many OrderItems
* One Category → Many MenuItems
