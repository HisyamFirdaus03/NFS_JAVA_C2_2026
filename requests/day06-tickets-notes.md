# Day 6 Exercise 5 - HTTP Test File Notes

Test file: `requests/day06-tickets.http`. All requests run against the running
Support Desk API at `http://localhost:8080`.

## Which endpoints worked

| # | Test | Method + Endpoint | Expected | Actual |
|---|------|-------------------|---------:|-------:|
| 1 | Health | GET `/api/health` | 200 | 200 ✅ |
| 2 | About | GET `/api/about` | 200 | 200 ✅ |
| 3 | Get all tickets | GET `/api/tickets` | 200 | 200 ✅ |
| 4 | Get existing ticket | GET `/api/tickets/T001` | 200 | 200 ✅ |
| 5 | Get missing ticket | GET `/api/tickets/T999` | 404 | 404 ✅ |
| 6 | Create valid ticket | POST `/api/tickets` | 201 | 201 ✅ |
| 7 | Create invalid ticket | POST `/api/tickets` | 400 | 400 ✅ |

All seven endpoints returned the expected status code.

## Example of a successful response

`GET /api/tickets/T001` -> **200 OK**

```json
{
  "id": "T001",
  "title": "Cannot access email",
  "description": "User cannot login to company email account.",
  "category": "Email",
  "priority": "HIGH",
  "status": "OPEN",
  "createdBy": "amir@example.com",
  "createdAt": "2026-07-03"
}
```

## Example of an error response

`POST /api/tickets` with a blank body -> **400 Bad Request**

```json
{
  "message": "Validation failed",
  "errors": [
    { "field": "title", "message": "Title is required" },
    { "field": "description", "message": "Description is required" },
    { "field": "category", "message": "Category is required" },
    { "field": "priority", "message": "Priority is required" },
    { "field": "createdBy", "message": "Created by is required" }
  ]
}
```

(A missing ticket, `GET /api/tickets/T999`, returns **404** with
`{ "message": "Ticket T999 was not found", "errors": [] }`.)
