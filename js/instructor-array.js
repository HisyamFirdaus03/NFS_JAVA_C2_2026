// Day 4 Exercise 02 - Store instructors in an array and loop through them.
// to run, type: node instructor-array.js

const instructors = [
    { instructorId: "I001", instructorName: "Ignacio de Paul", expertise: "Java and Spring Boot" },
    { instructorId: "I002", instructorName: "Roberto Tan", expertise: "React Development" },
    { instructorId: "I003", instructorName: "Juan Carlos Lee", expertise: "MongoDB" },
    { instructorId: "I004", instructorName: "Carlos Kim", expertise: "Testing" }
];

// 2 + 3. for...of loop to print each instructor in a readable format.
console.log("=== Instructor List ===");
for (const instructor of instructors) {
    console.log(`${instructor.instructorId} - ${instructor.instructorName} - ${instructor.expertise}`);
}

// 4. Total count using .length (the array grows/shrinks, like ArrayList.size()).
console.log();
console.log(`Total instructors: ${instructors.length}`);
