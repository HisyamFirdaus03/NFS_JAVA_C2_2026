// Day 4 Exercise 07 - Load students from a JSON file using fetch + async/await.

// 1. Select the HTML elements.
const statusMessage = document.getElementById("status-message");
const studentList = document.getElementById("student-list");

// 2. Render whatever student array it is given.
function renderStudents(students) {
    studentList.innerHTML = "";

    students.forEach((student) => {
        const studentCard = document.createElement("div");
        studentCard.className = "student-card";
        studentCard.innerHTML = `
            <h2>${student.studentName}</h2>
            <p>Student ID: ${student.studentId}</p>
            <p>Email: ${student.email}</p>
            <p>Status: ${student.status}</p>
        `;
        studentList.appendChild(studentCard);
    });
}

// 3. async function: it is allowed to wait for slow tasks (loading a file).
async function loadStudents() {
    try {
        // 4. Show a loading message before the data arrives.
        statusMessage.textContent = "Loading students...";

        // 5. Request the file and WAIT for the response.
        const response = await fetch("students.json");

        if (!response.ok) {
            throw new Error("Failed to load student data.");
        }

        // 6. Convert the JSON text into a JavaScript array (also waits).
        const students = await response.json();

        // 7. Success: clear the status and render the data.
        statusMessage.textContent = "";
        renderStudents(students);

    } catch (error) {
        // 8. Something went wrong (missing file, bad JSON, no server).
        statusMessage.textContent = "Error: " + error.message;
    }
}

// 9. Actually start the loading. Without this line nothing happens.
loadStudents();
