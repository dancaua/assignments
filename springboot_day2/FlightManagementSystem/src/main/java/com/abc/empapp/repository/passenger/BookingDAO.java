package com.abc.empapp.repository.passenger;

import com.abc.empapp.domain.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingDAO extends JpaRepository<Booking, Long> {

    @Query("SELECT b FROM Booking b WHERE b.userId =:userId AND b.isConfirmed = true")
    List<Booking> findAllActiveBookingsByUserId(@Param("userId") Long userId);

    @Query("SELECT b FROM Booking b WHERE b.flightId =:flightId AND b.isConfirmed = true")
    List<Booking> findAllActiveBookingsByFlight(@Param("flightId") Long flightId);
}
