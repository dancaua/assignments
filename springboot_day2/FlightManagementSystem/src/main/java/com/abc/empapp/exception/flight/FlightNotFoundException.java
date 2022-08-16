package com.abc.empapp.exception.flight;

import lombok.Getter;

import java.util.NoSuchElementException;

public class FlightNotFoundException extends NoSuchElementException {

    @Getter
    private Long id;

    public FlightNotFoundException(String cause, Long id) {
        super(cause);
        this.id = id;
    }
}
