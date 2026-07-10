# Day 7 Exercise 1 - Install and Secure MongoDB (Answers)

## What was done

| Task | Action | Result |
|------|--------|--------|
| 1 | Installed MongoDB Community 8.3.4, Compass 1.49.8, mongosh 2.9.2 (via Homebrew) | Service runs as a login service; both Compass and mongosh connect |
| 2 | `use admin` → created `rootAdmin` with role `root` | `{ ok: 1 }`, confirmed via `db.getUsers()` |
| 3 | Added `security: authorization: enabled` to `mongod.conf`, restarted | Anonymous commands now rejected: "requires authentication" |
| 4 | `use support_desk_db` → created `supportDeskApp` with `readWrite` on `support_desk_db` only | `{ ok: 1 }` |
| 5 | Inserted one ticket into `support_desk_db.tickets` | `insertedId` returned; `countDocuments()` = 1 |
| 6 | Connected Compass as the app user (`authSource=support_desk_db`) | `support_desk_db` → `tickets` → document visible |

### Connection strings

- Admin: `mongodb://rootAdmin:<password>@localhost:27017/?authSource=admin`
- App user: `mongodb://supportDeskApp:<password>@localhost:27017/support_desk_db?authSource=support_desk_db`

## Reflection Questions

### 1. What is the purpose of the `admin` database?

The `admin` database is MongoDB's special administrative database. It stores
server-wide accounts and roles (like the `rootAdmin` user) and is where
cluster-wide/administrative commands are authorised. Users created here can be
given permissions that span **all** databases, which is why the root
administrator lives in `admin`.

### 2. Why should an application use its own database user instead of the root administrator?

**Least privilege.** The app only needs to read and write its own data, so it
gets a user (`supportDeskApp`) with `readWrite` on **only** `support_desk_db`.
The root account can do anything on every database (create/drop databases,
manage users), so if the app's credentials leaked, the damage is limited to one
database instead of the whole server. It also separates concerns: admin tasks
use the admin account, the app uses the app account.

### 3. What is the difference between authentication and authorization?

- **Authentication** = proving *who you are* (logging in with a correct
  username and password). It answers "are you really this user?"
- **Authorization** = deciding *what you are allowed to do* once logged in
  (which databases and actions your roles permit). It answers "may you do this?"

Example: `supportDeskApp` **authenticates** with its password, but is only
**authorized** to read/write `support_desk_db` — it cannot touch other
databases or manage users.

### 4. What would happen if authentication was disabled on a production database?

Anyone who can reach the server's port could connect with **no credentials**
and have full read/write access to all data — read/steal sensitive records,
modify or delete data, drop entire databases, or create their own admin users.
That is exactly the "Access control is not enabled... access is unrestricted"
startup warning MongoDB shows when auth is off. On a production database exposed
to a network, this is a critical security hole and a common cause of real-world
data breaches and ransomware.
