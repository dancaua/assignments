package com.example.aopday1.domain.dao;

import com.example.aopday1.domain.util.Ticket;
import com.example.aopday1.domain.util.Train;
import com.example.aopday1.domain.util.User;
import com.example.aopday1.utility.FareCalculator;
import com.example.aopday1.utility.TrainClass;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;

@Component
public class TicketDAOImpl implements TicketDAO {

    private final List<User> users;
    private final List<Train> trains;

    public TicketDAOImpl() {
        users = new ArrayList<>();
        users.add(new User(1, "u1", "p1", 0));
        users.add(new User(2, "u2", "p2", 0));
        users.add(new User(3, "u3", "p3", 0));
        users.add(new User(4, "u4", "p4", 0));

        trains = new ArrayList<>();
        Train t1 = new Train();
        t1.setTrainNo(1);
        t1.setOrigin("Paris");
        t1.setDestination("Moscow");
        t1.setDepartureTime(LocalDate.now());
        t1.setArrivalTime(LocalDate.now());
        t1.setBookedTickets(new ArrayList<>());

        Train t2 = new Train();
        t2.setTrainNo(2);
        t2.setOrigin("Washington");
        t2.setDestination("New York");
        t2.setDepartureTime(LocalDate.now());
        t2.setArrivalTime(LocalDate.now());
        t2.setBookedTickets(new ArrayList<>());
    }

    @Override
    public Optional<User> getUserByTicketId(int ticketNo) {
        for (Train train : trains) {
            for (Ticket ticket : train.getBookedTickets()) {
                if (ticket.getTicketNo() == ticketNo) {
                    return users.stream().filter(user -> user.getId() == ticket.getUserId()).findFirst();
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<Ticket> getTicketById(int ticketNo) {
        for (Train train : trains) {
            for (Ticket ticket : train.getBookedTickets()) {
                if (ticket.getTicketNo() == ticketNo) {
                    return Optional.of(ticket);
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean login(String unm, String pwd) {
        return users
                .stream()
                .anyMatch(user -> credentialsAreValid(unm, pwd, user));
    }

    private boolean credentialsAreValid(String unm, String pwd, User user) {
        return user.getUsername().equals(unm) && user.getPassword().equals(pwd);
    }

    @Override
    public void showTrains() {
        for (Train t : trains) {
            System.out.println(t);
        }
        System.out.println("No more train details are available 333");
    }

    @Override
    public void bookTicket(int trainno, TrainClass trainClass, int userId) {
        Optional<Train> train = trains.stream().filter(t -> t.getTrainNo() == trainno).findFirst();
        if (train.isPresent()) {
            Train t = train.get();
            Ticket ticket = new Ticket(new Random().nextInt(100000), t.getTrainNo(), 0, trainClass, userId);
            FareCalculator.setFarePerTicket(ticket, trainClass);
            t.getBookedTickets().add(ticket);
            System.out.println("Train success");
        }
    }

    @Override
    public void cancelTicket(int ticketno) {
        for (Train train : trains) {
            List<Ticket> tickets = train.getBookedTickets();
            for (Ticket ticket : tickets) {
                if (ticket.getTicketNo() == ticketno) {
                    tickets.remove(ticket);
                    train.setBookedTickets(tickets);
                    return;
                }
            }
        }
    }
}
