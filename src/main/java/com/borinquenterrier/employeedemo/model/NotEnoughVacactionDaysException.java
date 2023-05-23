package com.borinquenterrier.employeedemo.model;

public class NotEnoughVacactionDaysException extends RuntimeException {

    public NotEnoughVacactionDaysException() {
        super("You have not worked enough days");
    }
}
