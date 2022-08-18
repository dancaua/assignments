package com.abc.empapp.controller;

import java.util.List;

import com.abc.empapp.domain.dto.ScheduleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.abc.empapp.domain.dto.FlightResponseDTO;
import com.abc.empapp.domain.entity.Flight;
import com.abc.empapp.service.flight.FlightService;

@RestController
@RequestMapping("/flights")
public class FlightRestController {

    private final FlightService flightService;

    @Autowired
    public FlightRestController(FlightService flightService)  {
        this.flightService = flightService;
    }

    @GetMapping("/all")
    public List<Flight> getAllFlights(@RequestParam(required = false) String sortDirection) {
        return flightService.getAllFlights(sortDirection);
    }

    @GetMapping("/type")
    public List<Flight> getAllFlightsByType(@RequestParam(required = false) String flightType) {
        return flightService.getAllFlightsByType(flightType);
    }

    @GetMapping("/{flightid}")
    public Flight getFlightBasedOnId(@PathVariable Long flightid) {
        return flightService.getFlightById(flightid);
    }

    @PostMapping
    public ResponseEntity<FlightResponseDTO> saveFlight(@RequestBody Flight flight) {
        FlightResponseDTO dto = flightService.addFlight(flight);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @PatchMapping("/{flightid}")
    public ResponseEntity<FlightResponseDTO> updateFlight(@RequestBody Flight flight) {
        FlightResponseDTO dto = flightService.updateFlight(flight);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @PostMapping("/{id}/schedule")
    public ResponseEntity<FlightResponseDTO> addSchedule(@PathVariable("id") Long flightId,
                                                         @RequestBody ScheduleDTO scheduleDTO) {
        FlightResponseDTO dto = flightService.addSchedule(flightId, scheduleDTO);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}/schedule")
    public ResponseEntity<ScheduleDTO> getSchedule(@PathVariable Long flightId) {
        ScheduleDTO dto = flightService.getScheduleByFlight(flightId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
