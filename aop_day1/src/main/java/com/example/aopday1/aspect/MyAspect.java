package com.example.aopday1.aspect;

import com.example.aopday1.logger.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAspect {

    private final Logger logger;

    public MyAspect() {
        logger = new Logger();
    }

    @AfterThrowing("execution(public boolean com.example.aopday1.service.TicketManagementServiceImpl.login(..))")
    public void logLoggingError() {
        logger.log("Invalid username/password error");
    }

    @After("execution(public boolean com.example.aopday1.service.TicketManagementServiceImpl.login(..)),")
    public void logTrains() {
        logger.log("Successfully logged in.");
        logger.log("Logging trains.");
    }

    @After("execution(public void com.example.aopday1.service.TicketManagementServiceImpl.bookTicket(..))")
    public void ticketSuccessfullyBookedLog() {
        logger.log("Ticket successfully booked for train");
    }


    @AfterReturning(pointcut = "execution(* com.example.aopday1.service.TicketManagementServiceImpl.bookTicket(..))")
    public void logAfterReturningBookTicket(JoinPoint joinPoint) {
        logger.log("Returning book ticket method");
    }

    @AfterThrowing("execution(public void com.example.aopday1.service.TicketManagementServiceImpl.bookTicket(..))")
    public void logInvalidTrainClass() {
        logger.log("Invalid train class");
    }

    @After("execution(public void com.example.aopday1.service.TicketManagementServiceImpl.bookTicket(..))")
    public void logCancelationFee() {
        logger.log("Cancelation fee applied for user.");
    }
}
