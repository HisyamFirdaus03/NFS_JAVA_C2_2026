const API_BASE_URL = "http://localhost:8081/api";

// Write your JavaScript here.

const loadButton = document.getElementById("loadButton");
const searchInput = document.getElementById("searchInput");
const searchButton = document.getElementById("searchButton");
const statusText = document.getElementById("statusText");
const eventList = document.getElementById("eventList");

// Turn one event object into readable text.
function formatEvent(event) {
    return `${event.title} - ${event.date} - ${event.venue} - ${event.availableSeats} seats available`;
}

// Render an array of events into the <ul>.
function renderEvents(events) {
    eventList.innerHTML = "";
    events.forEach((event) => {
        const listItem = document.createElement("li");
        listItem.textContent = formatEvent(event);
        eventList.appendChild(listItem);
    });
}

// Load ALL events from the API.
async function loadEvents() {
    try {
        statusText.textContent = "Loading events...";

        const response = await fetch(`${API_BASE_URL}/events`);

        if (!response.ok) {
            throw new Error(`Request failed with status ${response.status}`);
        }

        const events = await response.json();
        renderEvents(events);
        statusText.textContent = `${events.length} event(s) loaded successfully.`;

    } catch (error) {
        eventList.innerHTML = "";
        statusText.textContent = "Error: " + error.message;
    }
}

// Challenge: load ONE event by ID.
async function searchEventById() {
    const id = searchInput.value.trim();

    if (id === "") {
        statusText.textContent = "Please enter an event ID.";
        return;
    }

    try {
        statusText.textContent = `Searching for ${id}...`;

        const response = await fetch(`${API_BASE_URL}/events/${id}`);

        // A missing event returns 404 - handle it as a friendly message.
        if (response.status === 404) {
            eventList.innerHTML = "";
            statusText.textContent = `No event found with ID ${id}.`;
            return;
        }

        if (!response.ok) {
            throw new Error(`Request failed with status ${response.status}`);
        }

        const event = await response.json();
        renderEvents([event]);
        statusText.textContent = `Found event ${id}.`;

    } catch (error) {
        eventList.innerHTML = "";
        statusText.textContent = "Error: " + error.message;
    }
}

loadButton.addEventListener("click", loadEvents);
searchButton.addEventListener("click", searchEventById);
