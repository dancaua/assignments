package com.example.aopday1.service;

import com.example.aopday1.domain.dao.TicketDAO;
import com.example.aopday1.domain.dao.TicketDAOImpl;
import com.example.aopday1.domain.util.Ticket;
import com.example.aopday1.domain.util.User;
import com.example.aopday1.utility.TrainClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Optional;
import java.util.Scanner;

@Component
@EnableTransactionManagement(proxyTargetClass = true)
public class TicketManagementServiceImpl implements TicketManagementService {

    private final TicketDAO ticketDAO;

    @Autowired
    public TicketManagementServiceImpl() {
        ticketDAO = new TicketDAOImpl();
    }

    @Override
    public void login(String unm, String pwd) {
        boolean isLoggedIn = ticketDAO.login(unm, pwd);
        if (!isLoggedIn) {
            throw new RuntimeException("Invalid username/password error");
        }
    }

    @Override
    public void showTrains() {
        ticketDAO.showTrains();
    }

    @Override
    public void bookTicket(int trainno) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Train class you wish is: ");
        String trainClass = scanner.next().toUpperCase();
        if (trainClass.equals("SL")) {
            ticketDAO.bookTicket(trainno, TrainClass.SL, 1);
        } else if (trainClass.equalsIgnoreCase("AC")) {
            ticketDAO.bookTicket(trainno, TrainClass.AC, 2);
        } else {
            throw new RuntimeException("Invalid train class.");
        }
    }

    @Override
    public void cancelTicket(int ticketno) {
        Optional<Ticket> ticketOptional = ticketDAO.getTicketById(ticketno);
        if (ticketOptional.isPresent()) {
            ticketDAO.cancelTicket(ticketno);
            Optional<User> userOptional = ticketDAO.getUserByTicketId(ticketno);
            if (userOptional.isPresent()) {
                User user = userOptional.get();

                user.setTaxes(ticketOptional.get().getTrainClass().getCancelationFee());
            }
        }
    }
}
