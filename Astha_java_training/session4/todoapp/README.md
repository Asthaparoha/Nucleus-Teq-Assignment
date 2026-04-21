# 📝 Todo Application (Spring Boot)

## 📌 Project Overview
This is a Todo Application built using **Spring Boot + JPA (Hibernate)**.  
It follows a clean **layered architecture** and implements REST APIs for managing tasks.

---

## 🚀 Tech Stack

- Java 17
- Spring Boot
- Spring Data JPA (Hibernate)
- Maven
- H2 / In-Memory Database
- Thunder Client / Postman (for API testing)

---

## 📂 Project Structure
Controller → Service → Repository → Entity
↑
DTO

com.astha.todoapp
├── controller
├── dto
├── entity
├── exception
├── repository
├── service
│ └── impl


---

## 📌 Features

✔ Create Todo  
✔ Get All Todos  
✔ Get Todo by ID  
✔ Update Todo  
✔ Delete Todo  

✔ DTO-based API (No direct entity exposure)  
✔ Validation using `@Valid`, `@NotNull`, `@Size`  
✔ Exception Handling (Global)  
✔ Status Transition Validation  

---

## 📌 API Endpoints

### ➤ Create Todo

POST /todos


**Request Body:**
```json
{
  "title": "Study Spring",
  "description": "Complete assignment"
}
➤ Get All Todos
GET /todos
➤ Get Todo by ID
GET /todos/{id}
➤ Update Todo
PUT /todos/{id}
➤ Delete Todo
DELETE /todos/{id}
⚠️ Validation Rules
Title must not be null
Title must have at least 3 characters
🔄 Status Transitions

Allowed:

PENDING → COMPLETED
COMPLETED → PENDING

Invalid transitions will throw an error.

❗ Exception Handling
Custom Exception: ResourceNotFoundException
Global handler using @RestControllerAdvice
▶️ How to Run
./mvnw spring-boot:run

Open:

http://localhost:8081
🧪 Testing

Use Thunder Client / Postman:

POST → create task
GET → fetch tasks
PUT → update
DELETE → remove
👩‍💻 Author

Astha Paroha


---

