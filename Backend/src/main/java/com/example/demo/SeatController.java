package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import com.example.application.*;

@RestController
@RequestMapping("/api/seat")
public class SeatController {

    @PostMapping("/vacate-seat")
    public void vacateSeat(
            @RequestParam("flightNumber") int flightNumber,
            @RequestParam("seatNumber") int seatNumber) {
        try 
        {
            UpdateSeatQuery updateSeat = new UpdateSeatQuery();
            updateSeat.performDatabaseQuery(flightNumber, seatNumber, true);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    @PostMapping("/occupy-seat")
    public void occupySeat(
            @RequestParam("flightNumber") int flightNumber,
            @RequestParam("seatNumber") int seatNumber) {
        try {
            UpdateSeatQuery updateSeat = new UpdateSeatQuery();
            updateSeat.performDatabaseQuery(flightNumber, seatNumber, false);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/getSeats")
    public ResponseEntity<ArrayList<Seat>> getSeats(@RequestParam int flightNumber) {
        ReadSeatsQuery readSeatsQuery = new ReadSeatsQuery();
        ArrayList<Seat> seats = readSeatsQuery.performDatabaseQuery(flightNumber);
        if (seats != null) {
            return ResponseEntity.ok(seats);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}