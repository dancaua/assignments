package com.example.aopday1.main;


import com.example.aopday1.service.TicketManagementServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AopDay1Application {

    public static void main(String[] args) {

        ApplicationContext spring = new ClassPathXmlApplicationContext("spring-conf.xml");
        TicketManagementServiceImpl ticketManagementService = spring.getBean("ticketManagementServiceImpl",
                TicketManagementServiceImpl.class);

//        ticketManagementService.login("u1", "p2");

//        ticketManagementService.bookTicket(1);

//        ticketManagementService.cancelTicket(1);
    }

}
