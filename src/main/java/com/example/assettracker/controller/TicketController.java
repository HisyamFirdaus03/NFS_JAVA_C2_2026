package com.example.assettracker.controller;

import com.example.assettracker.dto.CreateTicketRequest;
import com.example.assettracker.dto.TicketResponse;
import com.example.assettracker.service.TicketService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
 * TicketController
 * ----------------
 * Web layer for tickets. No database logic here - it just receives requests
 * and delegates to TicketService, which talks to MongoDB via TicketRepository.
 */
@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    // GET /api/tickets -> all tickets from MongoDB
    @GetMapping
    public List<TicketResponse> getAllTickets() {
        return ticketService.getAllTickets();
    }

    // GET /api/tickets/{id} -> one ticket, or 404 if it does not exist
    @GetMapping("/{id}")
    public TicketResponse getTicketById(@PathVariable String id) {
        return ticketService.getTicketById(id);
    }

    // POST /api/tickets -> validate, save to MongoDB, return 201 with the ticket.
    // @Valid triggers the @NotBlank checks; failures become 400 via the handler.
    @PostMapping
    public ResponseEntity<TicketResponse> createTicket(@Valid @RequestBody CreateTicketRequest request) {
        TicketResponse created = ticketService.createTicket(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
}
