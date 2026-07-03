// Standalone mock API for the Event client (Day 5 Exercise 04).
// The provided rest-basics/mock-api.js only serves /api/course-offerings and
// /api/instructors - it has NO /api/events endpoint. This small server fills
// that gap so the client can be tested. Run: node events-mock-api.js
const http = require("http");

const PORT = 8081;

const events = [
    { id: "EV001", title: "Tech Career Fair", date: "2026-08-10", venue: "Kuala Lumpur Convention Centre", availableSeats: 120 },
    { id: "EV002", title: "Web Development Bootcamp", date: "2026-08-15", venue: "Digital Learning Hub", availableSeats: 35 },
    { id: "EV003", title: "AI for Business Workshop", date: "2026-08-20", venue: "Innovation Centre", availableSeats: 50 }
];

function corsHeaders() {
    return {
        "Access-Control-Allow-Origin": "*",
        "Access-Control-Allow-Methods": "GET,OPTIONS",
        "Access-Control-Allow-Headers": "Content-Type",
        "Content-Type": "application/json"
    };
}

function sendJson(response, statusCode, data) {
    response.writeHead(statusCode, corsHeaders());
    response.end(JSON.stringify(data, null, 2));
}

const server = http.createServer((request, response) => {
    const url = new URL(request.url, `http://${request.headers.host}`);
    const method = request.method;

    if (method === "OPTIONS") {
        response.writeHead(204, corsHeaders());
        response.end();
        return;
    }

    if (method === "GET" && url.pathname === "/api/events") {
        sendJson(response, 200, events);
        return;
    }

    const match = url.pathname.match(/^\/api\/events\/([^/]+)$/);
    if (method === "GET" && match) {
        const id = match[1];
        const found = events.find(event => event.id === id);
        if (!found) {
            sendJson(response, 404, { message: `Event ${id} was not found` });
            return;
        }
        sendJson(response, 200, found);
        return;
    }

    sendJson(response, 404, { message: "Endpoint not found" });
});

server.listen(PORT, () => {
    console.log(`Events mock API running at http://localhost:${PORT}`);
    console.log(`Try GET http://localhost:${PORT}/api/events`);
});
