package com.abc.empapp.exception.flight;

import lombok.Getter;

public class IllegalFlightTypeException extends IllegalArgumentException {

    @Getter
    private String type;

    public IllegalFlightTypeException(String s, String type) {
        super(s);
        this.type = type;
    }
}
