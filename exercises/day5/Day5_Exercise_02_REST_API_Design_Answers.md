# Day 5 Exercise 5.2 - REST API Design (Answers)

System: **Event Booking System**. Two main resources: **events** and
**bookings**. A booking refers to an event. All URLs are resource-style (nouns,
plural), and the HTTP method expresses the action — not the URL.

## 1. API Specification Table

| Resource | Method | Endpoint | Purpose | Request Body Needed? | Success Status | Possible Error Status |
|---|---|---|---|---|---:|---:|
| Events | GET | `/api/events` | View all available events | No | 200 | 500 |
| Event | GET | `/api/events/{eventId}` | View details of one event | No | 200 | 404 |
| Bookings | POST | `/api/bookings` | Create a booking for an event | Yes | 201 | 400, 404, 409 |
| Bookings | GET | `/api/bookings` | View all bookings | No | 200 | 500 |
| Booking | GET | `/api/bookings/{bookingId}` | View one booking | No | 200 | 404 |
| Booking | PATCH | `/api/bookings/{bookingId}` | Cancel a booking (set status to CANCELLED) | Yes (small) | 200 | 404, 409 |

**Note on "cancel a booking":** cancelling is a **change of state**, not a new
action-named endpoint. So I `PATCH` the booking resource and set its `status` to
`CANCELLED`. (An alternative valid design is `DELETE /api/bookings/{bookingId}`
if cancelling should fully remove the booking. I chose `PATCH` because the
booking is kept for history and can report an "already cancelled" state.)

## 2. Request Body Planning Table

| Endpoint | Request Body Description |
|---|---|
| `POST /api/bookings` | The event being booked and who is booking: `eventId`, `customerName` (or `userId`), and `quantity` (number of seats). No `id` or `status` — the server generates those. |
| `PATCH /api/bookings/{bookingId}` | Only the field being changed: `status: "CANCELLED"`. Small partial-update body, which is exactly what PATCH is for. |

The GET endpoints and the event endpoints need **no body** — they only read
data, and any identifier they need is already in the URL.

## 3. Error Planning Table

| Error Case | Related Endpoint | Suitable Status Code | Explanation |
|---|---|---:|---|
| Required field missing (e.g. no `eventId` or `quantity`) | `POST /api/bookings` | 400 | The client sent an invalid/incomplete body, so the server rejects it as a Bad Request and lists which fields failed. |
| Event does not exist | `GET /api/events/{eventId}` | 404 | No event matches that id, so there is nothing to return — Not Found. |
| Booking does not exist | `GET` / `PATCH /api/bookings/{bookingId}` | 404 | The booking id in the URL points to no record. |
| Event is fully booked | `POST /api/bookings` | 409 | The request is valid, but it conflicts with the current state (no seats left). 409 Conflict signals a state clash, not a bad body. |
| Booking is already cancelled | `PATCH /api/bookings/{bookingId}` | 409 | Trying to cancel a booking that is already CANCELLED conflicts with its current state. |

(At least two required — five are given, covering missing-field, not-found, and
conflict categories.)

## 4. Why These Endpoint Names Follow REST Principles

- **URLs name resources (nouns), not actions.** I use `/api/events` and
  `/api/bookings`, never `/getAllEvents`, `/createBooking`, or
  `/cancelBooking`. The **HTTP method** already says what to do, so putting a
  verb in the URL would be redundant and un-RESTful.
- **The method carries the verb.** `GET` = read, `POST` = create, `PATCH` =
  partial update (used to cancel by changing `status`). Same URL
  `/api/bookings` behaves differently by method: `GET` lists, `POST` creates.
- **A collection vs. a single item is shown by the path shape.**
  `/api/events` is the whole collection; `/api/events/{eventId}` is one item.
  This is consistent and predictable across both resources.
- **Status codes match the outcome.** 200 for reads, 201 for a successful
  create, 400 for a bad body, 404 for a missing record, 409 for a state
  conflict (full event / already cancelled). The client can branch on the code
  without parsing the body.
- **Cancel is modelled as a state change, not a special endpoint**, which keeps
  the URL space clean — you only ever have `events` and `bookings`, and their
  sub-paths, rather than an ever-growing list of action URLs.
