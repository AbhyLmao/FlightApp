package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import com.example.application.*;

@RestController
@RequestMapping("/api/ticket")
public class TicketController {

    @GetMapping("/get-tickets")
    public ResponseEntity<ArrayList<Ticket>> getTickets(@RequestParam String email) {
        TicketsQuery readTicketsQuery = new TicketsQuery();
        ArrayList<Ticket> ticketsOwnedByUser = readTicketsQuery.getTickets(email);
        if (ticketsOwnedByUser != null) {
            return ResponseEntity.ok(ticketsOwnedByUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete-ticket/{ticketID}")
    public void deleteTicket(@PathVariable int ticketID) {
        TicketsQuery deleteTicketQuery = new TicketsQuery();
        deleteTicketQuery.removeTicket(ticketID);
    }
}