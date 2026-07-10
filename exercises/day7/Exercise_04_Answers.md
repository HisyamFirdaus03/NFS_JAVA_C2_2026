# Day 7 Exercise 4 - Save New Tickets to MongoDB (Answers)

## Deliverables

- `dto/CreateTicketRequest.java` - request DTO with `@NotBlank` on title,
  description, category, priority, createdBy (no `status` from the client).
- `service/TicketService.java` - added `createTicket(...)`: builds a `Ticket`,
  sets `status = "OPEN"` and `createdAt = Instant.now()`, saves via
  `ticketRepository.save(...)`, returns a `TicketResponse`.
- `controller/TicketController.java` - added `POST /api/tickets` with
  `@Valid @RequestBody`, returns `201 Created`.
- Updated `requests/assets.http` with valid and invalid POST requests.

## Behaviour

| Request | Result |
|---------|--------|
| POST valid body | 201 Created, returns the ticket with a generated `id`, `status: OPEN`, and a backend `createdAt` |
| POST blank body | 400 Bad Request, per-field validation errors |
| GET after POST | the new ticket appears in the list |

The client sends 5 fields; the backend sets `id`, `status`, and `createdAt`.
The response is a `TicketResponse` DTO, never the raw `Ticket` model.

## Proof the ticket was saved in MongoDB

After `POST /api/tickets`, a direct mongosh query on the database (independent of
the running app) shows the new document:

```
mongosh "mongodb://supportDeskApp:<password>@localhost:27017/support_desk_db?authSource=support_desk_db"
> db.tickets.countDocuments()      // went from 1 to 2
2
> db.tickets.find({}, {title:1, status:1, createdBy:1})
{ _id: ObjectId('6a506fbbe2465ceac63e081f'), title: 'Cannot access email', status: 'OPEN', createdBy: 'amir@example.com' }
{ _id: ObjectId('6a50b180be2c56d341fd1adf'), title: 'VPN connection not working', status: 'OPEN', createdBy: 'siti@example.com' }
```

The same document is visible in MongoDB Compass under
`support_desk_db` -> `tickets`. Because the data survives outside the app
process (and shows up in mongosh/Compass), it is truly persisted in MongoDB.
