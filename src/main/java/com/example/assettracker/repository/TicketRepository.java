package com.example.assettracker.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.assettracker.model.Ticket;

// MongoRepository<Ticket, String> gives us save/findAll/findById/deleteById/etc.
// for Ticket documents, keyed by the String id. Custom finder methods (e.g.
// findByStatus) can be added here later.
public interface TicketRepository extends MongoRepository<Ticket, String> {

}
