package com.borinquenterrier.employeedemo.model;

public class ManagerEmployee extends SalariedEmployee {

    public ManagerEmployee(String name, int id) {
        super(name, id);
        vacationDays = 0f;
        vacationDaysPerYear = 30f;
    }

}
