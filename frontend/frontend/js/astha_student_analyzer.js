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

