package com.example.demo;

import com.example.application.InsertTicketQuery;
import com.example.application.UpdateSeatQuery;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {


    @PostMapping("/start")
    public void startPayment(
            @RequestParam("flightNumber") int flightNumber,
            @RequestParam("seatNumber") int seatNumber,
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("email") String email
    ) {
        try {
                InsertTicketQuery insertTicket = new InsertTicketQuery();
                insertTicket.performDatabaseQuery(1000, firstName, lastName, flightNumber, seatNumber, email, -1);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
