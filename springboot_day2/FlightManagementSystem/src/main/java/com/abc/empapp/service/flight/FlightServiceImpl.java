package com.abc.empapp.service.flight;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.abc.empapp.domain.dto.ScheduleDTO;
import com.abc.empapp.domain.entity.Schedule;
import com.abc.empapp.exception.flight.IllegalFlightTypeException;
import com.abc.empapp.exception.schedule.ScheduleAdditionError;
import com.abc.empapp.repository.flight.FlightRepository;
import com.abc.empapp.repository.schedule.ScheduleDAO;
import com.abc.empapp.util.FlightType;
import com.abc.empapp.util.ScheduleDTOConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.empapp.domain.dto.FlightResponseDTO;
import com.abc.empapp.domain.entity.Flight;
import com.abc.empapp.repository.flight.FlightDAO;
import com.abc.empapp.util.FlightDTOConvertor;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import static java.util.Objects.nonNull;

@Service
public class FlightServiceImpl implements FlightService {

    private final FlightDAO flightDAO;
    private final ScheduleDAO scheduleDAO;
    private final FlightRepository flightRepository;
    private final FlightDTOConvertor dtoConvertor;
    private final ScheduleDTOConvertor scheduleDTOConvertor;
    private final String DESCENDING_ORDER = "desc";

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public FlightServiceImpl(FlightDTOConvertor dtoConvertor, FlightDAO flightDAO, ScheduleDAO scheduleDAO, FlightRepository flightRepository, ScheduleDTOConvertor scheduleDTOConvertor) {
        this.dtoConvertor = dtoConvertor;
        this.flightDAO = flightDAO;
        this.scheduleDAO = scheduleDAO;
        this.flightRepository = flightRepository;
        this.scheduleDTOConvertor = scheduleDTOConvertor;
    }

    @Override
    public Flight getFlightById(Long flightId) {
        Optional<Flight> flight = flightDAO.findById(flightId);
        if (flight.isPresent()) {
            return flight.get();
        }
        throw new NoSuchElementException("Cannot find flight " + flightId);
    }

    @Override
    public boolean existsById(Long id) {
        return flightDAO.existsById(id);
    }

    @Override
    public FlightResponseDTO addFlight(Flight flight) {
        Flight savedFlight = flightDAO.save(flight);
        if (nonNull(flight.getFlightSchedule())) {
            scheduleDAO.save(flight.getFlightSchedule());
        }
        return dtoConvertor.getFlightReponseDTO(savedFlight);
    }

    @Override
    public void deleteFlight(Long flightId) {
        flightDAO.deleteById(flightId);
    }

    @Override
    public FlightResponseDTO updateFlight(Flight flight) {
        Optional<Flight> updatedFlight = flightDAO.findById(flight.getFlightId());
        if (updatedFlight.isPresent()) {
            Flight f = updatedFlight.get();
            f.setFlightName(flight.getFlightName());
            f.setFlightType(flight.getFlightType());
            f.setFlightSchedule(flight.getFlightSchedule());
            flightDAO.save(f);
            return dtoConvertor.getFlightReponseDTO(flight);
        } else {
            throw new NoSuchElementException("Cannot update flight");
        }
    }

    @Override
    public FlightResponseDTO addSchedule(Long flightId, ScheduleDTO schedule) {
        Optional<Flight> flight = flightDAO.findById(flightId);
        if (flight.isPresent()) {
            Flight f = flight.get();
            f.setFlightSchedule(scheduleDTOConvertor.dtoToDO(schedule));
            flightDAO.save(f);
            scheduleDAO.save(scheduleDTOConvertor.dtoToDO(schedule));
            return dtoConvertor.getFlightReponseDTO(flightDAO.findById(flightId).get());
        }
        throw new ScheduleAdditionError("Cannot update schedule for flight " + flightId);
    }

    @Override
    public ScheduleDTO getScheduleByFlight(Long flightId) {
        Optional<Flight> flight = flightDAO.findById(flightId);
        if (flight.isPresent()) {
            Schedule schedule = flight.get().getFlightSchedule();
            return scheduleDTOConvertor.doToDTO(schedule);
        }
        throw new NoSuchElementException("Cannot update schedule for flight " + flightId);
    }

    @Override
    public List<Flight> getAllFlights(String sortDirection) {
        List<Flight> flights = flightRepository.findAll();
        if (DESCENDING_ORDER.equalsIgnoreCase(sortDirection)) {
            flights.sort(Comparator.comparing(Flight::getFlightName, Comparator.reverseOrder()));
        } else {
            flights.sort(Comparator.comparing(Flight::getFlightName));
        }
        return flights;
    }

    @Override
    public List<Flight> getAllFlightsByType(String flightType) {
        if (!FlightType.isValid(flightType)) {
            throw new IllegalFlightTypeException("Illegal flight type ", flightType);
        }
        String query = "from Flight where flightType =: type";
        TypedQuery<Flight> q = entityManager.createQuery(query, Flight.class);
        q.setParameter("type", flightType);
        return q.getResultList();
    }

    @Override
    public List<Flight> getAllFlightsByStop(String flightType) {
        return null;
    }

    @Override
    public List<Flight> getAllFlights() {
        return flightDAO.findAll();
    }
}
