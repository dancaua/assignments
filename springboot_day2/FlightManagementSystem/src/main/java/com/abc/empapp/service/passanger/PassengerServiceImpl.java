package com.abc.empapp.service.passanger;

import com.abc.empapp.domain.dto.CustomAdminResponse;
import com.abc.empapp.domain.entity.Booking;
import com.abc.empapp.domain.entity.Passenger;
import com.abc.empapp.domain.entity.PassengerDetails;
import com.abc.empapp.exception.flight.FlightNotFoundException;
import com.abc.empapp.exception.passanger.InvalidPassengerDetailsException;
import com.abc.empapp.exception.passanger.PassengerNotFoundException;
import com.abc.empapp.repository.passenger.BookingDAO;
import com.abc.empapp.repository.passenger.PassangerDetailsDAO;
import com.abc.empapp.repository.passenger.PassengerDAO;
import com.abc.empapp.service.flight.FlightService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.nonNull;

@Service
public class PassengerServiceImpl implements PassengerService {

    private final PassengerDAO passengerDAO;
    private final PassangerDetailsDAO passangerDetailsDAO;
    private final BookingDAO bookingDAO;
    private final FlightService flightService;
    private Logger log = LoggerFactory.getLogger(PassengerServiceImpl.class);

    @Autowired
    public PassengerServiceImpl(PassengerDAO passengerDAO, PassangerDetailsDAO passangerDetailsDAO, BookingDAO bookingDAO, FlightService flightService) {
        this.passengerDAO = passengerDAO;
        this.passangerDetailsDAO = passangerDetailsDAO;
        this.bookingDAO = bookingDAO;
        this.flightService = flightService;
    }

    @Override
    public Passenger addPassenger(Passenger passenger) {
        return passengerDAO.save(passenger);
    }

    @Override
    public List<Passenger> getPassengersByFlightDetails(String city1, String city2) {
        return null;
    }

    @Override
    @Transactional
    public PassengerDetails getPassengersDetailsById(Long userId) {
        Optional<Passenger> passengerOptional = passengerDAO.findById(userId);
        if (passengerOptional.isPresent()) {
            PassengerDetails passengerDetails = passengerOptional.get().getPassengerDetails();
            if (nonNull(passengerDetails)) {
                return passengerDetails;
            } else {
                log.error("Passenger details for user with id {} does not exist.", userId);
                throw new InvalidPassengerDetailsException("Passenger details for user with id " + userId + " do not exist.");
            }
        } else {
            log.error("Passenger with id {} does not exist.", userId);
            throw new PassengerNotFoundException("Passenger with id " + userId + " does not exist.");
        }
    }

    @Override
    public List<Booking> findConfirmedBookingsPerUser(Long userId) {
        Optional<Passenger> passengerOptional = passengerDAO.findById(userId);
        if (passengerOptional.isPresent()) {
            return bookingDAO.findAllActiveBookingsByUserId(userId);
        } else {
            log.error("Passenger with id {} does not exist.", userId);
            throw new PassengerNotFoundException("Passenger with id " + userId + " does not exist.");
        }
    }

    @Override
    public List<CustomAdminResponse> findCustomAdminResponsePerFlightId(Long flightId) {

        if (!flightService.existsById(flightId)) {
            log.error("Flight with id {} does not exist.", flightId);
            throw new FlightNotFoundException("Flight with id " + flightId + " does not exist");
        }
        List<CustomAdminResponse> customAdminResponses = new ArrayList<>();
        List<Booking> bookings = bookingDAO.findAllActiveBookingsByFlight(flightId);
        for (Booking b : bookings) {
            customAdminResponses.add(processResponse(b));
        }
        return customAdminResponses;
    }

    private CustomAdminResponse processResponse(Booking b) {
        CustomAdminResponse customAdminResponse = new CustomAdminResponse();
        customAdminResponse.setFlightNumber(b.getFlightId());
        customAdminResponse.setUserId(b.getUserId());
        PassengerDetails passengerDetails = passengerDAO.findPassengerDetailsByUserId(b.getUserId());
        customAdminResponse.setAge(passengerDetails.getAge());
        customAdminResponse.setGender(passengerDetails.getGender());
        customAdminResponse.setFullName(passengerDetails.getFirstName() + " " + passengerDetails.getLastName());
        customAdminResponse.setPhoneNumber(passengerDetails.getPhoneNumber());
        return customAdminResponse;
    }
}
