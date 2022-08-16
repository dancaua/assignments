package com.abc.empapp.exception.handler;

import com.abc.empapp.exception.flight.IllegalFlightTypeException;
import com.abc.empapp.exception.template.MyExceptionTemplate;
import com.abc.empapp.exception.flight.NoFLightException;
import com.abc.empapp.exception.flight.FlightNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDate;
import java.time.LocalTime;

@ControllerAdvice
public class MyCommonExceptionHandler {

    public MyCommonExceptionHandler() {

        System.out.println("Inside MyCommonExceptionHandler contructor ...");
    }

    @ExceptionHandler
    public ResponseEntity<MyExceptionTemplate> handlingNoSuchElementException(java.util.NoSuchElementException exception) {
        // code to configure what to return (Exception tempalate)
        System.out.println(" --- inside No Element exception ");
        MyExceptionTemplate template = new MyExceptionTemplate();

        template.setTodayDate(LocalDate.now());
        template.setTodayTime(LocalTime.now());
        template.setUrl("/flight/<flight id>");
        template.setClassName("Flightcontroller");
        template.setCustomMsg(exception.getMessage());
        template.setSolution("Verify the Flight ID and try again");

        return new ResponseEntity<>(template, HttpStatus.BAD_REQUEST);

    }


    @ExceptionHandler
    public ResponseEntity<MyExceptionTemplate> handlingNoFlightExceptions(NoFLightException exception) {
        System.out.println(" --- inside No flight exception ");

        MyExceptionTemplate template = new MyExceptionTemplate();

        template.setTodayDate(LocalDate.now());
        template.setTodayTime(LocalTime.now());
        template.setUrl("/flight/" + exception.getEnteredFlightNumber());
        template.setClassName(exception.getClassName());
        template.setCustomMsg(exception.getMsg());
        template.setSolution("Verify the Flight ID and try again");

        return new ResponseEntity<>(template, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<MyExceptionTemplate> flightNotFoundExceptionHandler(FlightNotFoundException exception) {

        MyExceptionTemplate template = new MyExceptionTemplate();
        template.setTodayDate(LocalDate.now());
        template.setTodayTime(LocalTime.now());
        template.setUrl("/flight/" + exception.getId());
        template.setSolution("Verify the Flight ID and try again");
        return new ResponseEntity<>(template, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<MyExceptionTemplate> illegalFlightTypeExceptionHandler(IllegalFlightTypeException exception) {

        MyExceptionTemplate template = new MyExceptionTemplate();
        template.setTodayTime(LocalTime.now());
        template.setTodayTime(LocalTime.now());
        template.setSolution("Provided flight type is not correct. Accepted types: Domestic / International");
        return new ResponseEntity<>(template, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<MyExceptionTemplate> flightScheduleAdditionExceptionHandler(IllegalFlightTypeException exception) {

        MyExceptionTemplate template = new MyExceptionTemplate();
        template.setTodayDate(LocalDate.now());
        template.setTodayTime(LocalTime.now());
        template.setSolution("Arrival date is before departure date, please fix time schedules.");
        return new ResponseEntity<>(template, HttpStatus.BAD_REQUEST);
    }
}
