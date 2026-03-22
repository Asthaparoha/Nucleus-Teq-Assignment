# Student Performance Analyzer

This project is a console-based JavaScript program that analyzes student performance using basic programming concepts like arrays, objects, loops, and functions.

The goal of this assignment was to understand how data can be processed and analyzed using pure JavaScript without any UI or DOM manipulation.

---

## 📌 Features Implemented

- Calculated total marks for each student
- Calculated average marks
- Found subject-wise highest scores
- Calculated subject-wise averages
- Identified the class topper
- Implemented grade system (A, B, C, Fail)
- Handled fail conditions:
  - Subject score ≤ 40
  - Attendance < 75%
  - 
## 📊 Output Screenshots

### 🧮 1. Total Marks & Average
This shows total and average calculation for each student.

![Image1](frontend/screenshots/image1.png)

---

### 📈 2. Continued Output (Totals & Averages)
Additional output verification.

![Image2](frontend/screenshots/image2.png)

---

### 🏆 3. Subject-wise Toppers
Displays highest scorer in each subject.

![Image3](frontend/screenshots/image3.png)

---

### 📊 4. Subject-wise Averages
Shows average marks per subject.

![Image4](frontend/screenshots/image4.png)

---

### 🥇 5. Class Topper
Identifies the student with highest total marks.

![Image5](frontend/screenshots/image5.png)

---

### 🎓 6. Grades & Fail Conditions
Final grading with fail logic (attendance + subject).

![Image6](frontend/screenshots/image6.png)
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

## 📁 Folder Structure


frontend/
├── js/
│ └── astha_student_analyzer.js
├── screenshots/
│ ├── image1.png
│ ├── image2.png
│ ├── image3.png
│ ├── image4.png
│ ├── image5.png
│ └── image6.png


---

## 💡 Learnings

- Handling structured data using JavaScript
- Using loops and functions efficiently
- Writing clean and readable logic
- Applying real-world conditions in code
