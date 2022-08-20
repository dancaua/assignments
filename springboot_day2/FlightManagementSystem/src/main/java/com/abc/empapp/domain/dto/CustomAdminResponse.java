package com.abc.empapp.domain.dto;

import com.abc.empapp.domain.entity.Passenger;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomAdminResponse {
    private Long flightNumber;
    private Long userId;
    private Passenger passenger;
    private String fullName;
    private int age;
    private String gender;
    private String phoneNumber;
}
