// Day 5 Final Exercise - Mock API with events + bookings.
// Run: node mock-api.js   (listens on http://localhost:8081)
//
// Note: the trainer's rest-basics/mock-api.js is course-offering based and has
// no /api/events. This file is the events-based version the day-5 exercises
// assume, extended with the booking endpoints required by the final exercise.
const http = require("http");

const PORT = 8081;

let events = [
    { id: "EV001", title: "Tech Career Fair", date: "2026-08-10", venue: "Kuala Lumpur Convention Centre", availableSeats: 120 },
    { id: "EV002", title: "Web Development Bootcamp", date: "2026-08-15", venue: "Digital Learning Hub", availableSeats: 35 },
    { id: "EV003", title: "AI for Business Workshop", date: "2026-08-20", venue: "Innovation Centre", availableSeats: 50 }
];

// Part A: bookings live in memory only (empty again after a restart).
let bookings = [];

function corsHeaders() {
    return {
        "Access-Control-Allow-Origin": "*",
        "Access-Control-Allow-Methods": "GET,POST,DELETE,OPTIONS",
        "Access-Control-Allow-Headers": "Content-Type",
        "Content-Type": "application/json"
    };
}

function sendJson(response, statusCode, data) {
    response.writeHead(statusCode, corsHeaders());
    response.end(JSON.stringify(data, null, 2));
}

function readJsonBody(request) {
    return new Promise((resolve, reject) => {
        let body = "";
        request.on("data", chunk => { body += chunk; });
        request.on("end", () => {
            try {
                resolve(body ? JSON.parse(body) : {});
            } catch (error) {
                reject(error);
            }
        });
    });
}

function createId(prefix, currentLength) {
    return `${prefix}${String(currentLength + 1).padStart(3, "0")}`;
}

// Part B: validation - returns an array of { field, message } errors.
function validateBooking(payload) {
    const errors = [];

    if (!payload.eventId || payload.eventId.trim() === "") {
        errors.push({ field: "eventId", message: "Event ID is required" });
    }
    if (!payload.participantName || payload.participantName.trim() === "") {
        errors.push({ field: "participantName", message: "Participant name is required" });
    }
    if (!payload.participantEmail || payload.participantEmail.trim() === "") {
        errors.push({ field: "participantEmail", message: "Participant email is required" });
    }
    if (!Number.isInteger(payload.seats) || payload.seats < 1) {
        errors.push({ field: "seats", message: "Seats must be a whole number greater than 0" });
    }

    return errors;
}

const server = http.createServer(async (request, response) => {
    const url = new URL(request.url, `http://${request.headers.host}`);
    const method = request.method;

    if (method === "OPTIONS") {
        response.writeHead(204, corsHeaders());
        response.end();
        return;
    }

    try {
        // --- Events (read only, so seat reduction can be verified) ---
        if (method === "GET" && url.pathname === "/api/events") {
            sendJson(response, 200, events);
            return;
        }

        const eventMatch = url.pathname.match(/^\/api\/events\/([^/]+)$/);
        if (method === "GET" && eventMatch) {
            const found = events.find(event => event.id === eventMatch[1]);
            if (!found) {
                sendJson(response, 404, { message: `Event ${eventMatch[1]} was not found` });
                return;
            }
            sendJson(response, 200, found);
            return;
        }

        // --- Part C: GET all bookings ---
        if (method === "GET" && url.pathname === "/api/bookings") {
            sendJson(response, 200, bookings);
            return;
        }

        const bookingMatch = url.pathname.match(/^\/api\/bookings\/([^/]+)$/);

        // --- Part D: GET one booking ---
        if (method === "GET" && bookingMatch) {
            const found = bookings.find(booking => booking.id === bookingMatch[1]);
            if (!found) {
                sendJson(response, 404, { message: `Booking ${bookingMatch[1]} was not found` });
                return;
            }
            sendJson(response, 200, found);
            return;
        }

        // --- Part E/F/G/H: POST create booking ---
        if (method === "POST" && url.pathname === "/api/bookings") {
            const payload = await readJsonBody(request);

            // B: validate the body first.
            const errors = validateBooking(payload);
            if (errors.length > 0) {
                sendJson(response, 400, { message: "Validation failed", errors });
                return;
            }

            // F: the event must exist.
            const event = events.find(item => item.id === payload.eventId);
            if (!event) {
                sendJson(response, 404, { message: `Event ${payload.eventId} was not found` });
                return;
            }

            // G: enough seats must be available.
            if (payload.seats > event.availableSeats) {
                sendJson(response, 400, { message: "Not enough seats available" });
                return;
            }

            // Create the booking.
            const created = {
                id: createId("BK", bookings.length),
                eventId: payload.eventId,
                participantName: payload.participantName.trim(),
                participantEmail: payload.participantEmail.trim(),
                seats: payload.seats,
                status: "CONFIRMED"
            };
            bookings.push(created);

            // H: reduce the event's available seats.
            event.availableSeats -= payload.seats;

            sendJson(response, 201, created);
            return;
        }

        // --- Optional: DELETE cancels a booking (soft cancel) ---
        if (method === "DELETE" && bookingMatch) {
            const booking = bookings.find(item => item.id === bookingMatch[1]);
            if (!booking) {
                sendJson(response, 404, { message: `Booking ${bookingMatch[1]} was not found` });
                return;
            }

            // Only give seats back the first time it is cancelled.
            if (booking.status !== "CANCELLED") {
                booking.status = "CANCELLED";
                const event = events.find(item => item.id === booking.eventId);
                if (event) {
                    event.availableSeats += booking.seats;
                }
            }

            sendJson(response, 200, booking);
            return;
        }

        sendJson(response, 404, { message: "Endpoint not found" });
    } catch (error) {
        if (error instanceof SyntaxError) {
            sendJson(response, 400, { message: "Request body must be valid JSON" });
            return;
        }
        sendJson(response, 500, { message: "Unexpected server error" });
    }
});

server.listen(PORT, () => {
    console.log(`Mock API running at http://localhost:${PORT}`);
});
