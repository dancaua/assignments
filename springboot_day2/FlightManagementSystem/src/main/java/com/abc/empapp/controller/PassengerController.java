package com.abc.empapp.controller;

import com.abc.empapp.domain.entity.Passenger;
import com.abc.empapp.domain.entity.PassengerDetails;
import com.abc.empapp.service.passanger.PassengerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/passanger")
public class PassengerController {

    private final PassengerService passengerService;
    private final Logger log = LoggerFactory.getLogger(PassengerController.class);

    @Autowired
    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @PostMapping
    public Passenger addPassenger(@RequestBody Passenger passenger) {
        log.info("Entering PassengerController :: addPassenger method");
        return passengerService.addPassenger(passenger);
    }

    @GetMapping("/flights")
    public List<Passenger> getPassengersByFlightDetails(@RequestParam String city1, @RequestParam String city2) {
        log.info("Entering PassengerController :: getPassengersByFlightDetails method");
        return passengerService.getPassengersByFlightDetails(city1, city2);
    }

    @GetMapping("/details/{id}")
    public PassengerDetails getPassengersDetailsById(@PathVariable("id") Long userId) {
        log.info("Entering PassengerController :: getPassengersDetailsById method");
        return passengerService.getPassengersDetailsById(userId);
    }
}
