// Day 4 Exercise 04 - Practise 8 JavaScript array methods with student data.
// to run, type: node student-array-methods.js

const students = [
    { studentId: "S001", studentName: "Ignacio de Paul", email: "ignacio@example.com", status: "Active" },
    { studentId: "S002", studentName: "Ben Tan", email: "ben@example.com", status: "Inactive" },
    { studentId: "S003", studentName: "Chong Mei", email: "mei@example.com", status: "Active" }
];

console.log("=== Original Students ===");
console.log(students);

// ----- Part A: read / create a new array (original NOT changed) -----

// 1. forEach - run code for each item, returns undefined.
console.log("\n=== All Student Names ===");
students.forEach((student) => {
    console.log(student.studentName);
});

// 2. filter - keep only matching items, returns a NEW array.
const activeStudents = students.filter((student) => student.status === "Active");
console.log("\n=== Active Students ===");
console.log(activeStudents);

// 3. find - first matching item, returns ONE object (or undefined).
const foundStudent = students.find((student) => student.studentId === "S002");
console.log("\n=== Find Student S002 ===");
console.log(foundStudent);

// 4. map - transform each item, returns a NEW array (here, just the emails).
const studentEmails = students.map((student) => student.email);
console.log("\n=== Student Emails ===");
console.log(studentEmails);

// ----- Part B: modify the ORIGINAL array -----

// 5. push - add to the END, returns the new length.
const newLengthAfterPush = students.push({
    studentId: "S004",
    studentName: "Danish Nawaz",
    email: "danish@example.com",
    status: "Active"
});
console.log("\n=== After push ===");
console.log(students);
console.log("New length after push: " + newLengthAfterPush);

// 6. pop - remove the LAST item, returns the removed item.
const removedLastStudent = students.pop();
console.log("\n=== After pop ===");
console.log(students);
console.log("Removed last student:");
console.log(removedLastStudent);

// 7. unshift - add to the BEGINNING, returns the new length.
const newLengthAfterUnshift = students.unshift({
    studentId: "S000",
    studentName: "Ignacio de Paul",
    email: "ignacio@example.com",
    status: "Active"
});
console.log("\n=== After unshift ===");
console.log(students);
console.log("New length after unshift: " + newLengthAfterUnshift);

// 8. shift - remove the FIRST item, returns the removed item.
const removedFirstStudent = students.shift();
console.log("\n=== After shift ===");
console.log(students);
console.log("Removed first student:");
console.log(removedFirstStudent);

console.log("\n=== Final Students Array ===");
console.log(students);
