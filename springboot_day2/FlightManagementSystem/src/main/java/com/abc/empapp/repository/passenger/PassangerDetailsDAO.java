package com.abc.empapp.repository.passenger;

import com.abc.empapp.domain.entity.Booking;
import com.abc.empapp.domain.entity.PassengerDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PassangerDetailsDAO extends JpaRepository<PassengerDetails, Long> {
}
