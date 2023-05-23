package com.borinquenterrier.employeedemo.model;

public class SalariedEmployee extends AEmployee {


    public SalariedEmployee(String name, int id) {
        this.id = id;
        this.name = name;
        vacationDays = 0f;
        vacationDaysPerYear = 15f;
    }

}
