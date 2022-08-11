package com.example.aopday1.domain.util;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Data
@ToString
public class Train {
    private int trainNo;
    private LocalDate departureTime;
    private LocalDate arrivalTime;
    private String origin;
    private String destination;
    private List<Ticket> bookedTickets;
}
