// Day 4 Exercise 06 - Add search + reset to the student list.

const students = [
    { studentId: "S001", studentName: "Ignacio de Paul", email: "ignacio@example.com", status: "Active" },
    { studentId: "S002", studentName: "Ben Tan", email: "ben@example.com", status: "Inactive" },
    { studentId: "S003", studentName: "Chong Mei", email: "mei@example.com", status: "Active" },
    { studentId: "S004", studentName: "Danish Nawaz", email: "danish@example.com", status: "Active" }
];

const studentList = document.getElementById("student-list");
const searchInput = document.getElementById("search-input");
const searchButton = document.getElementById("search-button");
const resetButton = document.getElementById("reset-button");

// Render whatever array it is given (could be all students or filtered ones).
function renderStudents(studentArray) {
    // Clear the list first so old cards don't pile up.
    studentList.innerHTML = "";

    // Empty result -> friendly message instead of a blank page.
    if (studentArray.length === 0) {
        studentList.innerHTML = `<p class="empty-message">No students found</p>`;
        return;
    }

    studentArray.forEach((student) => {
        const card = document.createElement("div");
        card.className = "student-card";
        card.innerHTML = `
            <h2>${student.studentName}</h2>
            <p>Student ID: ${student.studentId}</p>
            <p>Email: ${student.email}</p>
            <p>Status: ${student.status}</p>
        `;
        studentList.appendChild(card);
    });
}

// --- Search button: filter by name, then render the matches ---
searchButton.addEventListener("click", () => {
    const keyword = searchInput.value.toLowerCase();
    const filteredStudents = students.filter((student) =>
        student.studentName.toLowerCase().includes(keyword)
    );
    renderStudents(filteredStudents);
});

// --- Reset button: clear the input and show everyone again ---
resetButton.addEventListener("click", () => {
    searchInput.value = "";
    renderStudents(students);
});

// Show all students when the page first loads.
renderStudents(students);
