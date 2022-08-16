package com.abc.empapp.repository.flight;

import com.abc.empapp.domain.entity.Flight;

import java.util.List;

public interface CustomFlightRepository
{
    List<Flight> getFlightsBasedOnTypeAndCity1(String city1, String flightType);
}