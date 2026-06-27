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

---

## Day 4 Exercise 04 - JavaScript Array Methods

### 1. What is the difference between `filter`, `find`, and `map`?

- **`filter`** keeps every item that passes a condition and returns a **new
  array** (zero or more items).
- **`find`** returns the **first single item** that passes a condition (one
  object), or `undefined` if none match — not an array.
- **`map`** **transforms** every item and returns a **new array of the same
  length** (e.g. turn an array of student objects into an array of emails).

In short: `filter` = "give me the matching ones", `find` = "give me the first
matching one", `map` = "give me a changed version of each one".

### 2. Which four array methods change the original array?

`push`, `pop`, `shift`, and `unshift`. (`forEach`, `filter`, `find`, and `map`
do **not** change the original.)

### 3. What does `push` return?

The **new length** of the array after adding the item to the end.

### 4. What does `pop` return?

The **item that was removed** from the end of the array.

### 5. What is the difference between `shift` and `unshift`?

They both work on the **front** of the array but in opposite directions:
- **`shift`** *removes* the first item and returns that removed item.
- **`unshift`** *adds* one or more items to the beginning and returns the new
  length.

---

## Day 4 Exercise 05 - Render Student Cards in HTML

### What does the DOM allow JavaScript to do?

The DOM (Document Object Model) is the browser's live, tree-shaped
representation of the HTML page. It lets JavaScript **read and change the page
while it's running**, instead of the HTML being fixed text. Through the DOM,
JavaScript can:

- **Find** elements (`document.getElementById("student-list")`),
- **Create** new elements (`document.createElement("div")`),
- **Fill** them with content (`innerHTML`),
- **Add or remove** them from the page (`appendChild`),
- and **respond to events** like clicks.

So instead of writing four student cards by hand in HTML, I keep the data in a
JavaScript array and let the code **generate** the page from it. This is the
same core idea React is built on — data drives the UI — just done manually here
with raw DOM methods.

---

## Day 4 Exercise 06 - Add Search to the Student List

### How is JavaScript `filter` used in a search feature?

`filter` builds a **new array containing only the items that match what the
user typed**, and that smaller array is what gets rendered. The flow is:

1. Read the search box value and lowercase it (so the search ignores case).
2. Run `students.filter(...)` with a condition that checks each student's name
   against the keyword, e.g.
   `student.studentName.toLowerCase().includes(keyword)`.
3. `filter` keeps only the students whose name contains the keyword and returns
   them as a new array — the **original `students` array is untouched**, which
   is why Reset can instantly show everyone again.
4. Pass that filtered array to `renderStudents()` to redraw the cards.

So `filter` is the "search engine" part — it decides *which* records match —
and the DOM code just displays whatever `filter` returns. An empty result is a
normal case (`length === 0`), which is why we show "No students found".

