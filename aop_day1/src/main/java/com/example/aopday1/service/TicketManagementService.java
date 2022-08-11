package com.example.aopday1.service;


public interface TicketManagementService {
    void login(String unm, String pwd);
    void showTrains();
    void bookTicket(int trainno);
    void cancelTicket(int ticketno);
}
