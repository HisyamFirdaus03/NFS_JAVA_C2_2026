package com.example.assettracker.service;

import com.example.assettracker.dto.CreateTicketRequest;
import com.example.assettracker.dto.TicketResponse;
import com.example.assettracker.exception.ResourceNotFoundException;
import com.example.assettracker.model.Ticket;
import com.example.assettracker.repository.TicketRepository;

import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

/*
 * TicketService
 * -------------
 * Business logic for tickets. Reads ticket DOCUMENTS from MongoDB through the
 * TicketRepository (no in-memory list) and maps them to response DTOs.
 */
@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    // Return all tickets from MongoDB, mapped model -> response DTO.
    public List<TicketResponse> getAllTickets() {
        return ticketRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    // Find one ticket by id, or throw ResourceNotFoundException (-> 404,
    // handled by GlobalExceptionHandler).
    public TicketResponse getTicketById(String id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket " + id + " was not found"));

        return toResponse(ticket);
    }

    // Create a new ticket from the request DTO. The backend sets status and
    // createdAt; the client never provides those. Saved into MongoDB.
    public TicketResponse createTicket(CreateTicketRequest request) {
        Ticket ticket = new Ticket(
                request.getTitle().trim(),
                request.getDescription().trim(),
                request.getCategory().trim(),
                request.getPriority().trim(),
                "OPEN",
                request.getCreatedBy().trim(),
                Instant.now()
        );

        Ticket savedTicket = ticketRepository.save(ticket);
        return toResponse(savedTicket);
    }

    // Helper: convert a Ticket document into a TicketResponse DTO.
    private TicketResponse toResponse(Ticket ticket) {
        return new TicketResponse(
                ticket.getId(),
                ticket.getTitle(),
                ticket.getDescription(),
                ticket.getCategory(),
                ticket.getPriority(),
                ticket.getStatus(),
                ticket.getCreatedBy(),
                ticket.getCreatedAt()
        );
    }
}
