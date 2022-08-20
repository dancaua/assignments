package com.abc.empapp.exception.passanger;

import java.util.NoSuchElementException;

public class InvalidPassengerDetailsException extends NoSuchElementException {
    public InvalidPassengerDetailsException(String s) {
        super(s);
    }
}
