package com.example.aopday1.utility;

import com.example.aopday1.domain.util.Ticket;
import com.example.aopday1.domain.util.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FareCalculator {

    private static final int EXTRA_FARE_PER_TICKET = 200;

    public static void setFarePerTicket(Ticket ticket, TrainClass trainClass) {
        switch (trainClass) {
            case SL: {
                ticket.setFare(TrainClass.SL.getPrice() + EXTRA_FARE_PER_TICKET);
                break;
            }
            case AC: {
                ticket.setFare(TrainClass.AC.getPrice());
                break;
            }
            default:
                throw new IllegalArgumentException("Illegal train class");
        }
    }
}
