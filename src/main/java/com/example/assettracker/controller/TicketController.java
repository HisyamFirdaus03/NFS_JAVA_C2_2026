package com.example.assettracker.controller;

import com.example.assettracker.dto.TicketResponse;
import com.example.assettracker.service.TicketService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService ticketService;

    // Spring injects the TicketService here (constructor injection).
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping
    public List<TicketResponse> getAllTickets() {
        // Controller stays thin: it just asks the service for the data.
        return ticketService.getAllTickets();
    }
}
