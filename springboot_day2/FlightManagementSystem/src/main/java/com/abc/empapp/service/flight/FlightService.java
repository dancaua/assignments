package com.abc.empapp.service.flight;

import java.util.List;

import com.abc.empapp.domain.dto.FlightResponseDTO;
import com.abc.empapp.domain.dto.ScheduleDTO;
import com.abc.empapp.domain.entity.Flight;

public interface FlightService {

	List<Flight> getAllFlights(String sortDirection);
	List<Flight> getAllFlightsByType(String flightType);
	List<Flight> getAllFlightsByStop(String flightType);
	List<Flight> getAllFlights();
	FlightResponseDTO addFlight(Flight flight);
	void deleteFlight(Long flightId);
	FlightResponseDTO updateFlight(Flight flight);
	FlightResponseDTO addSchedule(Long flightId, ScheduleDTO schedule);
	ScheduleDTO getScheduleByFlight(Long flightId);
	Flight getFlightById(Long id);
	boolean existsById(Long id);
}
