package com.example.assettracker.service;

import java.util.ArrayList;
import java.util.List;

import com.example.assettracker.dto.TicketResponse;
import com.example.assettracker.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class TicketService {
    private final List<TicketResponse> tickets = new ArrayList<>();

    public TicketService() {
        tickets.add(new TicketResponse(
            "T001",
            "Cannot access email",
            "User cannot login to company email account.",
            "Email",
            "HIGH",
            "OPEN",
            "amir@example.com",
            "2026-07-03"
        ));

        tickets.add(new TicketResponse(
            "T002",
            "Laptop is slow",
            "Laptop takes several minutes to start up.",
            "Hardware",
            "MEDIUM",
            "OPEN",
            "siti@example.com",
            "2026-07-03"
        ));

        tickets.add(new TicketResponse(
            "T003",
            "VPN connection not working",
            "User cannot connect to the office VPN from home.",
            "Network",
            "HIGH",
            "IN_PROGRESS",
            "john@example.com",
            "2026-07-04"
        ));
    }

    public List<TicketResponse> getAllTickets() {
        return tickets;
    }

    // Search logic lives here (service layer), not in the controller.
    public TicketResponse getTicketById(String id) {
        return tickets.stream()
                .filter(ticket -> ticket.getId().equalsIgnoreCase(id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Ticket " + id + " was not found"));
    }
}
