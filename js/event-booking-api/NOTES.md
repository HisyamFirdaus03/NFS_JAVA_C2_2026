# Day 5 Final Exercise - Submission Note

## Files

- `mock-api.js` - the mock API with events + bookings.
- `requests.http` - the 8 test requests (Part I), status code noted on each.

> The trainer's `rest-basics/mock-api.js` is course-offering based and has no
> `/api/events`, which the day-5 exercises assume. This `mock-api.js` is the
> events-based version with the booking endpoints added, so it runs on its own.

## Endpoints completed

| Method | Endpoint | Status codes | Notes |
|---|---|---|---|
| GET | `/api/events` | 200 | Included so seat reduction is verifiable. |
| GET | `/api/events/{id}` | 200 / 404 | |
| GET | `/api/bookings` | 200 | Returns `[]` when empty. |
| GET | `/api/bookings/{id}` | 200 / 404 | |
| POST | `/api/bookings` | 201 / 400 / 404 | Validate body (400) → event exists (404) → enough seats (400) → create (201). |
| DELETE | `/api/bookings/{id}` | 200 / 404 | Optional task: soft-cancel, adds seats back once. |

## Minimum requirements - all met

- `GET /api/bookings`, `GET /api/bookings/{id}`, `POST /api/bookings` all work.
- Invalid booking data -> **400**.
- Unknown event ID -> **404**.
- Successful booking -> **201**.
- Available seats reduce after a successful booking (verified: EV001 120 → 115
  after booking 2 + 3 seats).

## Optional DELETE - completed

- Cancelling sets status to `"CANCELLED"` (booking kept, not removed).
- Seats are returned to the event (EV001 115 → 117 after cancelling a 2-seat
  booking).
- A guard (`if status !== "CANCELLED"`) means cancelling twice does **not**
  refund seats twice.

## Verified test results

| Test | Result |
|---|---|
| GET bookings (empty) | 200, `[]` |
| POST valid booking | 201 |
| GET one booking | 200 (missing id -> 404) |
| POST missing fields | 400 |
| POST invalid eventId | 404 |
| POST too many seats | 400 |
| Seats reduced after booking | EV001 = 115 |
| Cancel booking | 200, status CANCELLED, EV001 = 117 |
