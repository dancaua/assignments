package com.example.aopday1.domain.util;

import com.example.aopday1.utility.TrainClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class Ticket {
    private int ticketNo;
    private int trainNo;
    private int fare;
    private TrainClass trainClass;
    private int userId;
}
