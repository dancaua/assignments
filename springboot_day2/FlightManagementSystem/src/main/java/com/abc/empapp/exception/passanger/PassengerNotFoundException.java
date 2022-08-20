package com.abc.empapp.exception.passanger;

import java.util.NoSuchElementException;

public class PassengerNotFoundException extends NoSuchElementException {
    public PassengerNotFoundException(String s) {
        super(s);
    }
}
