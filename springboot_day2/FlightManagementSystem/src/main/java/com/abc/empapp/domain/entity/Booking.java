package com.abc.empapp.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "booking")
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

    @Id
    @Column(name = "booking_id")
    private Long bookingId;

    @Column(name = "flight_id")
    private Long flightId;

    @Column(name = "booking_user_id")
    private Long userId;

    @Column(name = "confirmed")
    private boolean isConfirmed;

    @Column(name = "booking_date")
    private LocalDate dateOfBooking;
}
