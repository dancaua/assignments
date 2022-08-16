package com.abc.empapp.repository.flight;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abc.empapp.domain.entity.Flight;

@Repository
public interface FlightDAO extends JpaRepository<Flight, Long>
{
	
}
