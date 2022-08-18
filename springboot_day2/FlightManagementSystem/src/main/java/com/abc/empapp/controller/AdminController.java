package com.abc.empapp.controller;

import com.abc.empapp.domain.entity.Flight;
import com.abc.empapp.service.flight.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final FlightService flightService;

    @Autowired
    public AdminController(FlightService flightService) {
        this.flightService = flightService;
    }

    @PostMapping
    public void saveFlight(@RequestBody Flight flight) {
        flightService.addFlight(flight);
    }

    @DeleteMapping("/{id}")
    public void deleteFlight(@PathVariable("id") Long flightId) {
        flightService.deleteFlight(flightId);
    }
}