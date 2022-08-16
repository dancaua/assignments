package com.abc.empapp.repository.flight;

import com.abc.empapp.domain.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer>, CustomFlightRepository
{

    @Query("From Flight where flightType = :searchedType")
    List<Flight> getAllFlightsBasedOnType(@Param("searchedType") String searchedType);

}