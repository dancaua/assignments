package com.abc.empapp.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "passenger")
@NoArgsConstructor
@AllArgsConstructor
public class Passenger {

    @Id
    @Column(name = "passenger_id")
    private Long passengerId;

    @Column(name = "user_id")
    private Long userId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "passenger_id", referencedColumnName = "passenger_details_id")
    private PassengerDetails passengerDetails;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<Booking> allBookings;

    @Override
    public String toString() {
        return "Passenger{" +
                "passengerId=" + passengerId +
                ", userId=" + userId +
                '}';
    }
}
