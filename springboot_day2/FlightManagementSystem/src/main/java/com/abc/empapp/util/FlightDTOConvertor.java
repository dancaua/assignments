package com.abc.empapp.util;

import org.springframework.stereotype.Component;

import com.abc.empapp.domain.dto.FlightResponseDTO;
import com.abc.empapp.domain.entity.Flight;

@Component
public class FlightDTOConvertor {

    public FlightResponseDTO getFlightReponseDTO(Flight flight) {
        FlightResponseDTO dto = new FlightResponseDTO(flight.getFlightId() + "", flight.getFlightName());
        return dto;
    }
}
