package com.borinquenterrier.employeedemo.model;

public class NotEnoughVacationDaysException extends RuntimeException {

    public NotEnoughVacationDaysException() {
        super("You have not worked enough days");
    }
}
