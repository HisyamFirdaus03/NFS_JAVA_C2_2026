# Day 7 Exercise 5 - Persistence Checkpoint (Answers)

## Test steps

1. Started MongoDB (running as a Homebrew service) and the Spring Boot app
   (`./mvnw spring-boot:run`, port 8081, connected to `support_desk_db`).
2. Created a ticket: `POST /api/tickets` with
   `{ "title": "Printer not responding", "description": "Level 3 printer will not print.",
   "category": "Hardware", "priority": "LOW", "createdBy": "aisyah@example.com" }`
   -> `201 Created`.
3. Confirmed with `GET /api/tickets` -> the ticket appeared (total count 3).
4. **Stopped** the Spring Boot app (verified the port stopped responding).
5. **Started** the app again (cold start).
6. Ran `GET /api/tickets` again -> total count still 3.
7. Ran `GET /api/tickets/6a50b248be2c56d341fd1ae0` -> `200 OK`, the ticket was
   still there with all its fields intact.

## Ticket ID created

```
6a50b248be2c56d341fd1ae0
```

## Confirmation after restart

The ticket **survived the restart**. Because it lives in MongoDB (not in a Java
list that is recreated on each boot), stopping and starting the application did
not lose it. This is the difference from Day 6, where an in-memory list was
wiped on every restart.

## Reflection Questions

**1. What is the role of the repository?**
It is the data-access layer. It handles all database operations (save, findAll,
findById, delete, count) for a model, so the rest of the app never writes raw
database code. `TicketRepository extends MongoRepository<Ticket, String>`, and
Spring generates the implementation - we just call its methods.

**2. What is the difference between `Ticket` and `TicketResponse`?**
`Ticket` is the **database model** - a `@Document` mapped to the `tickets`
collection, representing how data is stored (its `@Id`, its internal shape).
`TicketResponse` is the **API DTO** - what we send back to clients. Separating
them means the API's shape is decoupled from the database: we can change storage
without breaking clients, and we never expose the raw model.

**3. What does MongoDB store as the document ID?**
The `_id` field. When we don't supply one, MongoDB generates a 12-byte
**ObjectId** (e.g. `6a50b248be2c56d341fd1ae0`). Spring maps that `_id` to the
`@Id private String id;` field on the model, exposing it as a string.

**4. Why should the controller not talk directly to MongoDB?**
Separation of concerns. The controller is the web layer (receive request, return
response); the service holds business logic; the repository talks to the
database. Keeping database code out of the controller makes each layer simpler,
independently testable, and swappable - e.g. we could change the database or
add validation/caching in the service without touching the controller.
