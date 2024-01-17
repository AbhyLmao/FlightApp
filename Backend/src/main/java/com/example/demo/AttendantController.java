package com.example.demo;

import com.example.application.Passenger;
import com.example.application.ReadPassengersQuery;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AttendantController {

    private final ReadPassengersQuery readPassengersQuery;


    public AttendantController(ReadPassengersQuery readPassengersQuery) {
        this.readPassengersQuery = readPassengersQuery;
    }

    @PostMapping("attendant/passengerlist")
    public List<Passenger> passengerList(@RequestParam int flightNumber){

        return readPassengersQuery.getListByFlight(flightNumber);
    }
}
