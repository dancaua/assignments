package com.abc.empapp.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum FlightType {

    INTERNATIONAL("International"),
    DOMESTIC("Domestic");

    private String type;

    public static boolean isValid(String type) {
        return Arrays.stream(FlightType.values()).anyMatch(flightType -> flightType.getType().equals(type));
    }
}
