package com.abc.empapp.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "passenger_details")
@NoArgsConstructor
@AllArgsConstructor
public class PassengerDetails {

    @Id
    @Column(name = "passenger_details_id")
    private Long passengerDetailsId;
    private String firstName;
    private String lastName;
    private int age;
    private String gender;
    private String phoneNumber;
}
