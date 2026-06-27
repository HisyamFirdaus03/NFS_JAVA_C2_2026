// D4 Exercise 01 - Represent one student as a JavaScript object literal.
// Same idea as the Course demo, applied to Student.
// to run, type: node student-object.js

const student = {
    studentId: "S001",
    studentName: "Ignacio de Paul",
    email: "ignacio@example.com",
    status: "Active"
};

// 2. Print the whole object.
console.log("=== Student Object ===");
console.log(student);

console.log();

// 3 + 4. Print each property - dot notation (used more than twice here).
console.log(`Student ID: ${student.studentId}`);
console.log(`Name: ${student.studentName}`);
console.log(`Email: ${student.email}`);

// 5. Bracket notation at least once (key is a string in [ ]).
console.log(`Status: ${student["status"]}`);
