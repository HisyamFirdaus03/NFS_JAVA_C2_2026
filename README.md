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

## Day 3 Exercise 01 - Build and Trace the Code Flow

### When `getCourseById("C004")` is called, which file does the request go to first, second, and third?

1. **`CourseService.java`** — `getCourseById()` runs first. It's the entry
   point the demo class calls; it holds the business logic and decides what to
   do (here, ask the repository and throw `CourseNotFoundException` if missing).
2. **`InMemoryCourseRepository.java`** (called through the
   **`CourseRepository.java`** interface) — the service calls
   `courseRepository.findById("C004")`. The service depends on the *interface*,
   and the in-memory class is the actual implementation that runs.
3. **The `LinkedHashMap`** inside `InMemoryCourseRepository` — the repository
   looks the course up in its in-memory map and returns it (wrapped in an
   `Optional`). The `Course` then travels back up: map → repository → service →
   demo class, where it's printed.

So the direction is **demo → service → repository → map**, and the found
object returns back up the same path.

---

## Day 3 Exercise 02 - Interface and Repository Storage Practice

### Why is `InMemoryCourseRepository` temporary storage? What would replace it with MongoDB?

It stores courses in a `LinkedHashMap` that lives in **RAM**, inside the
running program. The moment the program stops, that map is gone — nothing is
written to disk, so every run starts empty. That's fine for practising and
testing, but useless for a real app where data must survive restarts and be
shared between users.

Later, MongoDB replaces it: we'd write a new class (e.g.
`MongoCourseRepository`) that **implements the same `CourseRepository`
interface** but stores courses in a MongoDB collection instead of a map.
Because the service only depends on the `CourseRepository` interface, we swap
the implementation **without changing the service or any business logic** —
that's the whole point of splitting the interface from the implementation.
(With Spring Boot, a `MongoRepository` interface usually generates this for
us.)

---

## Day 3 Exercise 03 - Exception Practice with CourseService

### Why is throwing `CourseNotFoundException` better than printing inside `CourseService`?

If the service printed `"Course not found"` itself, it would be **stuck with
one fixed reaction** for everyone. But the same missing-course situation needs
to be shown differently depending on who's calling:

- a **console app** prints a friendly line to the terminal,
- a **web API** returns an HTTP 404 with a JSON error body,
- a **frontend app** shows a red toast or an "empty state" screen.

By **throwing** the exception, the service only says *"this went wrong and
why"* — it doesn't decide how to display it. The **caller** catches it and
reacts in whatever way suits its context. This keeps the business logic
reusable across console, API, and UI without change, and it stops the service
from silently swallowing errors (a print can be missed; an uncaught exception
cannot). It's the same separation as the repository interface: the service
reports the problem, the caller owns the response.

---

## Day 3 Exercise 04 - Object Relationships and Composition

### Why is `CourseOffering` a better design than putting start date, end date, and capacity directly inside `Course`?

A `Course` is the **template** (title, level, duration) — it doesn't change
per run. But a course is taught **many times**: different dates, capacities,
instructors, and delivery modes each intake.

If start date, end date, and capacity lived *inside* `Course`, then one course
could only ever hold **one** schedule. To offer "Java Fundamentals" in June
and again in July you'd have to **duplicate the whole course**, copying the
title/level/duration each time — and if you fixed a typo in the title you'd
have to fix it in every copy.

`CourseOffering` separates "what the course is" from "one scheduled run of it".
This gives a clean **one-to-many** relationship: one `Course` can be reused by
many `CourseOffering`s (my OFF001 and OFF003 both point at the *same*
`javaCourse` object). Through **composition**, each offering *has a* `Course`
and *has an* `Instructor` — it references the real objects rather than copying
their text, so the course is defined once and shared. This maps directly to how
the data will later be modelled in MongoDB: a courses collection and an
offerings collection that reference it.

---

## Day 3 Exercise 05 - Loop Search vs Stream Search

### Which version is easier to understand: loop or stream? Why?

The **loop** version is easier to understand the first time, because every step
is written out explicitly: make an empty list, walk through each course one by
one, check a condition, add the matches, return the list. You can read it
top-to-bottom like instructions and trace exactly what happens.

The **stream** version (`findAll().stream().filter(...).toList()`) is shorter
and reads more like a sentence ("take all courses, keep the ones that match,
collect them"), but it hides the loop and the temporary list. Once you're
comfortable, it's quicker to write and harder to get wrong — but the loop is
the better mental model for a beginner because nothing is hidden. Both produce
the **exact same result** (proven in the demo output).

### What does `filter()` do in a stream?

`filter()` takes a condition (a boolean test) and **keeps only the elements
that pass it**, dropping the rest. It's the stream equivalent of the
`if (...) results.add(course);` line inside the loop — the elements that make
the condition `true` continue down the stream, and the ones that make it
`false` are removed.

