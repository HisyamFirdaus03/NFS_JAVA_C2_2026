// Day 4 Exercise 05 - Render student cards into the page using the DOM.

const students = [
    { studentId: "S001", studentName: "Ignacio de Paul", email: "ignacio@example.com", status: "Active" },
    { studentId: "S002", studentName: "Ben Tan", email: "ben@example.com", status: "Inactive" },
    { studentId: "S003", studentName: "Chong Mei", email: "mei@example.com", status: "Active" },
    { studentId: "S004", studentName: "Danish Nawaz", email: "danish@example.com", status: "Active" }
];

// 1. Select the container div from the HTML.
const studentList = document.getElementById("student-list");

// 2. Loop through the students.
students.forEach((student) => {
    // 3. Create a card element for this student.
    const card = document.createElement("div");
    card.className = "student-card";

    // 4. Fill the card with the student's details.
    card.innerHTML = `
        <h2>${student.studentName}</h2>
        <p>Student ID: ${student.studentId}</p>
        <p>Email: ${student.email}</p>
        <p>Status: ${student.status}</p>
    `;

    // 5. Add the finished card to the page.
    studentList.appendChild(card);
});
