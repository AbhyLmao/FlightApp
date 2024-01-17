package com.example.demo;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.application.Flight;
import com.example.application.ModifyFlightsQuery;

@RestController
@RequestMapping("/api/flight")
public class FlightController {
    @GetMapping("/flightList")
    public ResponseEntity<List<Flight>> getAllFlights() {
        ModifyFlightsQuery readFlightsQuery = new ModifyFlightsQuery();
        List<Flight> flights = readFlightsQuery.performDatabaseQuery();
        return ResponseEntity.ok(flights);
    }

    @GetMapping("/flightDetails")
    public ResponseEntity<Flight> getFlightDetails(@RequestParam int flightNumber) {
        ModifyFlightsQuery readFlightsQuery = new ModifyFlightsQuery();
        Flight flightDetails = readFlightsQuery.getFlightDetails(flightNumber);

        if (flightDetails != null) {
            return ResponseEntity.ok(flightDetails);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/addFlight")
    public ResponseEntity<String> addFlight(@RequestBody Flight newFlight) {
        System.out.println(newFlight);
        ModifyFlightsQuery modifyFlightsQuery = new ModifyFlightsQuery();
        modifyFlightsQuery.addFlight(newFlight);
        return ResponseEntity.ok("Flight added successfully");
    }

    @DeleteMapping("/removeFlight")
    public ResponseEntity<String> removeFlight(@RequestParam int flightNumber) {
        ModifyFlightsQuery modifyFlightsQuery = new ModifyFlightsQuery();
        modifyFlightsQuery.removeFlight(flightNumber);
        return ResponseEntity.ok("Flight removed successfully");
    }

    @PutMapping("/modifyFlight")
    public ResponseEntity<String> modifyFlight(@RequestBody Flight modifiedFlight) {
        ModifyFlightsQuery modifyFlightsQuery = new ModifyFlightsQuery();
        modifyFlightsQuery.modifyFlight(modifiedFlight);
        return ResponseEntity.ok("Flight modified successfully");
    }
}
