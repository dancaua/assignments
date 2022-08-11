package com.example.aopday1.utility;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TrainClass {
    SL(100, 50),
    AC(200, 100);

    private int price;
    private int cancelationFee;
}
