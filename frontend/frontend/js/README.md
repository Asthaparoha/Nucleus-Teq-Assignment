# Student Performance Analyzer

This project is a console-based JavaScript program that analyzes student performance using arrays, objects, loops, and functions.

The goal is to process and evaluate student data using pure JavaScript logic without any DOM or UI.

---

## 📌 Features Implemented

- Total marks calculation
- Average marks calculation
- Subject-wise highest score
- Subject-wise average calculation
- Class topper identification
- Grade logic (A, B, C, Fail)
- Fail conditions (low marks and attendance)

---

## 📊 Output Screenshots

### 🧮 1. Total Marks & Average
![Image1](screenshots/image1.png)

---

### 📈 2. Continued Output
![Image2](screenshots/image2.png)

---

### 🏆 3. Subject-wise Toppers
![Image3](screenshots/image3.png)

---

### 📊 4. Subject-wise Averages
![Image4](screenshots/image4.png)

---

### 🥇 5. Class Topper
![Image5](screenshots/image5.png)

---

### 🎓 6. Grades & Fail Conditions
![Image6](screenshots/image6.png)

---

## 🧠 Explanation of Logic

- Used array of objects to represent student data
- Used loops to calculate totals and averages
- Nested loops for subject-wise comparisons
- Functions used for modular and readable code
- Conditions used for grading and fail logic

---

## ⚙️ How to Run

1. Open browser
2. Open Console (Inspect → Console)
3. Paste the JavaScript file
4. Press Enter
5. View output using console.log()

---

## 📁 Folder Structure
frontend/frontend/js/
├── astha_student_analyzer.js
├── README.md
└── screenshots/
├── image1.png
├── image2.png
├── image3.png
├── image4.png
├── image5.png
└── image6.png
## 🧠 Logic Explanation

- Used an **array of objects** to store student data.
- Each student contains:
  - Name
  - Marks (array of subjects)
  - Attendance
- Used **functions** to calculate:
  - Total marks
  - Average
  - Grade
- Used **nested loops** for:
  - Subject-wise highest
  - Subject-wise averages
- Applied **conditions** to:
  - Identify failures
  - Assign grades

---

## 🏆 Sample Output


Lalit Total Marks: 391
Rahul Total Marks: 423
Aman Total Marks: 248
Riya Total Marks: 450

Class Topper: Riya with 450 marks

Lalit Grade: B
Rahul Grade: B
Aman Grade: Fail (Failed in Science)
Riya Grade: Fail (Low Attendance)


---

## ⚙️ How to Run

1. Open browser (Chrome recommended)
2. Right click → Inspect → Console
3. Paste JavaScript code
4. Press Enter
5. View output in console

---

## 💡 Learnings

- Handling structured data using JavaScript
- Using loops and functions efficiently
- Writing clean and readable logic
- Applying real-world conditions in code
