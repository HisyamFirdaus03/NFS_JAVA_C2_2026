// Day 4 Exercise 03 - Functions and arrow functions for student data.
// to run, type: node student-functions.js

const student = {
    studentId: "S001",
    studentName: "Aina Rahman",
    email: "aina@example.com",
    status: "Active"
};

// 1. Normal function - returns a formatted string.
function formatStudent(student) {
    return `${student.studentId} - ${student.studentName} (${student.status})`;
}

// 2. Arrow function with a body - returns the email.
const getStudentEmail = (student) => {
    return student.email;
};

// 3. Short arrow function - implicit return (no braces, no 'return').
const getStudentStatus = (student) => student.status;

console.log(formatStudent(student));
console.log(getStudentEmail(student));
console.log(getStudentStatus(student));
