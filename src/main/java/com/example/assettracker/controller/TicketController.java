package com.example.assettracker.controller;

import com.example.assettracker.dto.CreateTicketRequest;
import com.example.assettracker.dto.TicketResponse;
import com.example.assettracker.service.TicketService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
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

    @GetMapping("/{id}")
    public TicketResponse getTicketById(@PathVariable String id) {
        // The {id} from the URL is passed straight to the service, which does
        // the lookup and throws ResourceNotFoundException (-> 404) if missing.
        return ticketService.getTicketById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // 201 on success
    public TicketResponse createTicket(@Valid @RequestBody CreateTicketRequest request) {
        // @Valid triggers the @NotBlank checks. If any fail, Spring throws
        // MethodArgumentNotValidException, which the GlobalExceptionHandler
        // turns into a 400 - so we never reach the service with bad data.
        return ticketService.createTicket(request);
    }
}
