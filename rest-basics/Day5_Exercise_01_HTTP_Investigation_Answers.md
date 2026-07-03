# Day 5 Exercise 5.1 - HTTP Investigation (Answers)

API tested: `http://localhost:8081` (the provided `mock-api.js`, started with
`node mock-api.js` from the `rest-basics` folder).

> Note: the mock data uses IDs like `CO001` / `CO002`, so I used `CO001` for the
> "get one item" test and `CO999` for the not-found test. (The IDs `C001` /
> `C999` in `requests.http` do not exist, so those actually return 404.)

## Investigation Table

| # | Method | URL | Status Code | Response Type | What Happened? |
|---|---|---|---:|---|---|
| 1 | GET | `/api/course-offerings` | 200 | List | Success. Returned a JSON array of all course offerings (CO001, CO002). |
| 2 | GET | `/api/course-offerings/CO001` | 200 | Single object | Success. Returned the one course offering whose id is CO001. |
| 3 | GET | `/api/course-offerings/CO999` | 404 | Error object | Failed. No offering with id CO999 exists, so the API returned a "not found" message. |
| 4 | POST | `/api/course-offerings` | 201 | Single object | Success. A valid body was sent, the server created a new offering (CO003) and returned it. |
| 5 | POST | `/api/course-offerings` | 400 | Error object | Failed. The body had empty fields and capacity 0, so validation failed and the server listed each field error. |

### Exact bodies observed

- **#1 (200, List):** array of 2 objects, each with `id, courseTitle, instructorName, startDate, capacity, status`.
- **#2 (200, Single):** `{ "id": "CO001", "courseTitle": "Java Fundamentals", ... }`.
- **#3 (404, Error):** `{ "message": "Course offering CO999 was not found" }`.
- **#4 (201, Single):** `{ "id": "CO003", "courseTitle": "Spring Boot Fundamentals", "status": "OPEN", ... }`.
- **#5 (400, Error):** `{ "message": "Validation failed", "errors": [ {courseTitle...}, {instructorName...}, {startDate...}, {capacity...} ] }`.

## Answers to the Five Questions

**1. Which request returned a successful list response?**
Request #1, `GET /api/course-offerings` — status **200**, body was a JSON **array**
(list) of all course offerings.

**2. Which request returned a not-found response?**
Request #3, `GET /api/course-offerings/CO999` — status **404**. The id does not
exist, so the server returned an error object explaining it was not found.

**3. Which request returned a validation error?**
Request #5, `POST /api/course-offerings` with empty fields — status **400 (Bad
Request)**. The server rejected the body and returned a list of field errors.

**4. What is the difference between a successful response and an error response?**
A **successful** response uses a 2xx status code (200 for a read, 201 for a
create) and its body contains the **requested/created data** (a list or an
object). An **error** response uses a 4xx status code (404, 400) and its body
contains a **message explaining what went wrong** instead of real data. The
status code is the quickest way to tell them apart — you don't even have to read
the body.

**5. Why is the status code important for frontend developers?**
It tells the frontend **what actually happened without parsing the whole body**,
so the UI can react correctly:
- **200/201** → show the data / show a "created successfully" message.
- **404** → show a "not found" / empty state.
- **400** → show the validation errors next to the form fields.
- **500** → show a generic "something went wrong, try again" message.

The code drives the branching (`if (!response.ok) { ... }`), so the user sees
the right screen for each outcome instead of a crash or a blank page.

## Reflection

One thing I understand better about REST after this exercise: the **status code
and the response body work together but do different jobs**. The status code is
a short, standard signal of the outcome (success vs. which kind of failure),
and the body carries the details (the data on success, or the reason on
failure). The same endpoint and method can return very different results —
`GET /api/course-offerings/CO001` succeeds with 200 while `.../CO999` fails with
404 — so the client must always check the status first, then decide what to do
with the body. That is exactly the pattern the Day 4 `fetch` exercise used with
`if (!response.ok) throw ...`, and now I can see what those codes mean on the
server side.
