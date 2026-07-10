# Day 7 Exercise 3 - Convert Ticket Read API to MongoDB (Answers)

## Deliverables

- `service/TicketService.java` - reads tickets from `TicketRepository` (MongoDB),
  maps `Ticket` documents to `TicketResponse` DTOs, throws
  `ResourceNotFoundException` (-> 404) for a missing id.
- `controller/TicketController.java` - `GET /api/tickets` and
  `GET /api/tickets/{id}`, no database logic (delegates to the service).
- `dto/TicketResponse.java` - the response DTO.
- Updated `requests/assets.http` with ticket read requests.

## How I confirmed the data came from MongoDB (not an in-memory list)

The ticket returned by `GET /api/tickets` is the **exact document I inserted by
hand in mongosh** during Exercise 1 - same `_id`
(`6a506fbbe2465ceac63e081f`), same fields, and the same `createdAt`
(`2026-07-10T04:06:19.125Z`). None of that is written anywhere in the Java code,
so it can only have come from the database.

Further proof:
1. The service has **no in-memory list** - it calls
   `ticketRepository.findAll()` / `findById(...)`, which query MongoDB.
2. The app authenticates as the limited `supportDeskApp` user against
   `support_desk_db`; querying that same database in mongosh
   (`db.tickets.countDocuments()`) returns the same count the API returns.
3. If I insert or delete a ticket in mongosh, the API response changes on the
   next request - the API is reading live database state.

## Test results (app on port 8081)

| Request | Result |
|---------|--------|
| `GET /api/tickets` | 200, JSON array with the seeded ticket |
| `GET /api/tickets/6a506fbbe2465ceac63e081f` | 200, that ticket |
| `GET /api/tickets/000000000000000000000000` | 404, `{ "message": "Ticket ... was not found" }` |

## Note on the MongoDB connection (important)

Spring Boot 4.x's auto-configuration was **not** applying the credentials from
`spring.data.mongodb.uri` (the driver logged `credential=null`), so an
auth-enabled MongoDB rejected every command with `13 (Unauthorized)`. Fixed by
adding `config/MongoConfig.java`, which builds the `MongoClient` explicitly from
the URI and pins the `MongoDatabaseFactory` to `support_desk_db`. Credentials
stay out of the committed file - the full URI is provided via the `MONGODB_URI`
environment variable:

```
export MONGODB_URI='mongodb://supportDeskApp:<password>@localhost:27017/support_desk_db?authSource=support_desk_db'
export SERVER_PORT=8081
./mvnw spring-boot:run
```
