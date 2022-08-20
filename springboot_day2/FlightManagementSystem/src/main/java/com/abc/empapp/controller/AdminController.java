package com.abc.empapp.controller;

import com.abc.empapp.domain.dto.CustomAdminResponse;
import com.abc.empapp.domain.entity.Booking;
import com.abc.empapp.domain.entity.Flight;
import com.abc.empapp.service.flight.FlightService;
import com.abc.empapp.service.passanger.PassengerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final FlightService flightService;
    private final PassengerService passengerService;
    private final Logger log = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    public AdminController(FlightService flightService, PassengerService passengerService) {
        this.flightService = flightService;
        this.passengerService = passengerService;
    }

    @PostMapping
    public void saveFlight(@RequestBody Flight flight) {
        flightService.addFlight(flight);
    }

    @DeleteMapping("/{id}")
    public void deleteFlight(@PathVariable("id") Long flightId) {
        flightService.deleteFlight(flightId);
    }

    @GetMapping("/passengers/{userId}")
    public List<Booking> findConfirmedBookingsPerUser(@PathVariable("userId") Long userId) {
        log.info("Entering AdminController :: findConfirmedBookingsPerUser method");
        return passengerService.findConfirmedBookingsPerUser(userId);
    }

    @GetMapping("/passengers/{flightId}")
    public List<CustomAdminResponse> findCustomAdminResponsePerFlightId(@PathVariable("flightId") Long flightId) {
        log.info("Entering AdminController :: findCustomAdminResponsePerFlightId method");
        return passengerService.findCustomAdminResponsePerFlightId(flightId);
    }
}