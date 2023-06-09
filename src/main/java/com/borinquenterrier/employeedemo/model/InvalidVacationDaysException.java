package com.borinquenterrier.employeedemo.model;

public class InvalidVacationDaysException extends RuntimeException {

    public InvalidVacationDaysException() {
        super("Negative days");
    }
}
