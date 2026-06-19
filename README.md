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

## Day 1 Exercise 01 - Code Explanation

> Explained from a C++ background. Note: the current project only contains
> `Course.java` and `Instructor.java`. `Student.java` and `Main.java` don't
> exist yet, the assign method is actually `setInstructor(...)`, and
> `printProfile()` belongs to `Instructor`, not `Student`. Answers below
> reflect the real code.

**1. What is the purpose of Course.java?**
It's a blueprint (like a C++ `class`) describing a course. It holds the
course's data — `courseId`, `title`, `durationHours`, `level` — plus a
reference to the `Instructor` teaching it. It also knows how to print itself
via `printSummary()`.

**2. What is the purpose of Instructor.java?**
A blueprint for an instructor: holds `instructorId`, `instructorName`, and
`expertise`, and can print itself via `printProfile()`. A `Course` *has an*
`Instructor` — this is the object relationship (composition/association).

**3. What is the purpose of Student.java?**
Not present in the project yet. By analogy it would be a blueprint for a
student (id, name, etc.) with its own `printProfile()` method.

**4. What does the constructor do?**
`public Course(String courseId, ...)` runs when you create an object with
`new`. It initialises the fields from the arguments — same job as a C++
constructor. `this.title = title;` is just C++'s `this->title = title;`
(used here to tell the field apart from the same-named parameter).

**5. Why are the fields marked as private?**
Encapsulation — same reason as C++ `private:`. Outside code can't poke the
fields directly; it must go through getters/setters (`getTitle()`,
`setInstructor()`). This lets the class control and validate its own state.

**6. What does course1.setInstructor(instructor1) mean?**
(The sheet calls it `assignInstructor`; in the code it's `setInstructor`.)
It stores the `instructor1` reference inside `course1`'s `instructor` field —
linking the two objects so the course now "has" that instructor. In C++ terms
it's like assigning a pointer: `course1.instructor = &instructor1;`. Before
this is called the field is `null` (C++ `nullptr`), which is why
`printSummary()` checks `if (instructor == null)`.

**7. What does printProfile() do?**
On `Instructor`, it prints the instructor's id, name, and expertise to the
console via `System.out.println` (Java's `std::cout`). It's a member method
that reads the object's own fields and displays them.

### AI-Assisted Task

**One explanation from AI that helped me:**
That Java objects are always heap references managed by a garbage collector,
`Course c = new Course(...)` is closer to `Course* c = new Course(...)` in
C++, but I never call `delete`. That cleared up why there are no pointers,
`*`, `&`, or destructors.

**One part I still needed the trainer / my own reading for:**
_how packages map to folders?_

