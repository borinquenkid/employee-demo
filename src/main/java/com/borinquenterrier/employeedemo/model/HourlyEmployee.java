package com.borinquenterrier.employeedemo.model;

public class HourlyEmployee extends AEmployee {

    public HourlyEmployee(String name, int id) {
        this.id = id;
        this.name = name;
        vacationDays = 0f;
        vacationDaysPerYear = 10f;
    }


}
