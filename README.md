# NFS_JAVA_C2_2026 | Full-Stack Development with Java, React & MongoDB



## Programme Description



This 20-day programme is designed to help participants build a complete full-stack web application using Java, Spring Boot, React, and MongoDB.



The programme takes learners from programming and web fundamentals to backend API development, frontend interface design, database modelling, authentication, testing, performance improvement, and final capstone presentation.



Throughout the programme, participants will work on practical exercises and gradually build a small but production-like web application. The final outcome is a working capstone project that demonstrates the use of a React frontend, Spring Boot backend, MongoDB database, secure authentication, API documentation, testing practices, and deployment-readiness basics.



AI tools such as Gemini are used as learning accelerators to help scaffold examples, suggest refactoring ideas, draft tests, generate sample data, and support MongoDB query or aggregation design. However, participants are expected to review, verify, understand, and take ownership of all generated code.



---



## Programme Duration



* Duration: 20 training days

* Daily Duration: 7 hours per day

* Total Training Hours: 140 hours

* Mode: Instructor-led training with guided labs, team build activities, review sessions, quizzes, and capstone development



---



## Programme Objectives



By the end of this programme, participants will be able to:



* Understand web fundamentals, HTTP, REST, and JSON.

* Write basic to intermediate Java and JavaScript code.

* Build REST APIs using Spring Boot.

* Apply validation, authentication, authorisation, and error-handling practices.

* Model data effectively using MongoDB.

* Use MongoDB indexes, queries, pagination, and aggregation pipelines.

* Build accessible React user interfaces with routing, forms, state, and data fetching.

* Apply testing practices for backend and frontend development.

* Use AI coding assistants responsibly for learning, refactoring, testing, and documentation.

* Design, build, document, and present a full-stack capstone project.



---





---



## AI-Assisted Learning Guidelines



Participants may use AI tools to:



* Generate README drafts and documentation sections.

* Create API call examples and JSON payload samples.

* Suggest method signatures and edge cases.

* Propose refactoring options.

* Draft test scenarios for backend and frontend features.

* Suggest MongoDB document structures, queries, indexes, and aggregation pipelines.

* Improve demo scripts and presentation notes.



Participants must always review, verify, test, and understand any AI-generated output. No passwords, API keys, tokens, private keys, or confidential data should be placed into AI prompts.

---

## Day 4 Exercise 01 - Create a JavaScript Student Object

### What is one difference between a Java object and a JavaScript object?

A Java object must be built from a **class** (a fixed blueprint): the type,
fields, and their types are declared ahead of time and checked by the compiler,
so an object always has exactly the fields the class defines. A JavaScript
object can be created **directly** with an object literal `{}` — no class
needed — and it's **dynamically typed**: you can add, remove, or change
properties at runtime, and a property can hold any type. So Java is rigid and
checked before it runs; JavaScript is flexible and only finds mistakes while
running.

(Other valid differences: Java enforces `private` fields and getters/setters;
JS properties are open by default. Java needs compilation; JS runs directly
with `node`.)

---

## Day 4 Exercise 02 - Store Instructors in an Array and Loop Through Them

### How is a JavaScript array similar to Java `ArrayList`?

Both are **resizable, ordered lists** — they grow and shrink as you add or
remove items, unlike a fixed-size Java array (`Instructor[]`). You access items
by index (`instructors[0]`), keep insertion order, and ask for the count
(`.length` in JS ≈ `.size()` in `ArrayList`). You can also loop over both the
same way: JS `for...of` is the direct equivalent of Java's enhanced
`for (Instructor i : instructors)`.

The main difference is typing: a Java `ArrayList<Instructor>` can only hold
`Instructor` objects (checked by the compiler), while a JavaScript array can
hold anything — numbers, strings, objects, all mixed — because JS is
dynamically typed.

---

## Day 4 Exercise 03 - Functions and Arrow Functions for Student Data

### Why are arrow functions important before learning React?

React code is **full of arrow functions**, so being comfortable with them now
makes React much easier later:

- **Event handlers and callbacks** are almost always written as arrows, e.g.
  `onClick={() => handleClick(student)}`.
- **Array methods** that React uses to build UI — especially `.map()` to turn a
  list of data into a list of components — take an arrow:
  `students.map((s) => <li>{s.studentName}</li>)`.
- The **short arrow form** (`(x) => x.status`, implicit return, no braces) is
  used constantly because it's compact and reads cleanly inside JSX.
- Arrow functions also **don't rebind `this`**, which avoids a common class of
  bugs React developers used to hit with normal functions.

So practising normal → arrow → short arrow on plain student data first means
the syntax is already familiar when it shows up everywhere in React.

