// I created this dataset to simulate a small classroom
// Added more students to make calculations more realistic

const students = [
  {
    name: "Lalit",
    marks: [
      { subject: "Math", score: 78 },
      { subject: "English", score: 82 },
      { subject: "Science", score: 74 },
      { subject: "History", score: 69 },
      { subject: "Computer", score: 88 }
    ],
    attendance: 82
  },
  {
    name: "Rahul",
    marks: [
      { subject: "Math", score: 90 },
      { subject: "English", score: 85 },
      { subject: "Science", score: 80 },
      { subject: "History", score: 76 },
      { subject: "Computer", score: 92 }
    ],
    attendance: 91
  },
  {
    name: "Aman",
    marks: [
      { subject: "Math", score: 45 },
      { subject: "English", score: 50 },
      { subject: "Science", score: 38 }, // fail subject
      { subject: "History", score: 60 },
      { subject: "Computer", score: 55 }
    ],
    attendance: 80
  },
  {
    name: "Riya",
    marks: [
      { subject: "Math", score: 88 },
      { subject: "English", score: 92 },
      { subject: "Science", score: 85 },
      { subject: "History", score: 90 },
      { subject: "Computer", score: 95 }
    ],
    attendance: 72 // low attendance fail
  }
];
// -------- TOTAL MARKS --------
// Function to calculate total marks of one student
function getTotalMarks(student) {
  let sum = 0;

  for (let i = 0; i < student.marks.length; i++) {
    sum += student.marks[i].score;
  }

  return sum;
}
// -------- AVERAGE --------
// Average is simply total divided by number of subjects
function getAverageMarks(student) {
  const total = getTotalMarks(student);
  return total / student.marks.length;
}
// -------- PRINT TOTAL + AVERAGE --------
console.log("---- STUDENT TOTAL & AVERAGE ----");

students.forEach(stu => {
  const total = getTotalMarks(stu);
  const avg = getAverageMarks(stu);

  console.log(stu.name + " Total Marks: " + total);
  console.log(stu.name + " Average: " + avg.toFixed(1));
});
// -------- SUBJECT-WISE HIGHEST --------
// Loop through subjects and compare all students

console.log("\n---- SUBJECT TOPPERS ----");

const subjects = students[0].marks.map(m => m.subject);

for (let i = 0; i < subjects.length; i++) {
  let highest = 0;
  let topper = "";

  for (let j = 0; j < students.length; j++) {
    const stu = students[j];

    for (let k = 0; k < stu.marks.length; k++) {
      if (stu.marks[k].subject === subjects[i]) {
        if (stu.marks[k].score > highest) {
          highest = stu.marks[k].score;
          topper = stu.name;
        }
      }
    }
  }

  console.log("Highest in " + subjects[i] + ": " + topper + " (" + highest + ")");
}
