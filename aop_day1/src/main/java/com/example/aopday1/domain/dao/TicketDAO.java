package com.example.aopday1.domain.dao;

import com.example.aopday1.domain.util.Ticket;
import com.example.aopday1.domain.util.User;
import com.example.aopday1.utility.TrainClass;

import java.util.Optional;

public interface TicketDAO {
    Optional<User> getUserByTicketId(int ticketNo);
    Optional<Ticket> getTicketById(int ticketNo);
    boolean login(String unm, String pwd);
    void showTrains();
    void bookTicket(int trainno, TrainClass trainClass, int userId);
    void cancelTicket(int ticketno);
}
