package com.abc.empapp.service.passanger;

import com.abc.empapp.domain.dto.CustomAdminResponse;
import com.abc.empapp.domain.entity.Booking;
import com.abc.empapp.domain.entity.Passenger;
import com.abc.empapp.domain.entity.PassengerDetails;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface PassengerService {
    Passenger addPassenger(@RequestBody Passenger passenger);
    List<Passenger> getPassengersByFlightDetails(String city1, String city2);
    PassengerDetails getPassengersDetailsById(Long userId);
    List<Booking> findConfirmedBookingsPerUser(Long userId);
    List<CustomAdminResponse> findCustomAdminResponsePerFlightId(Long flightId);
}
