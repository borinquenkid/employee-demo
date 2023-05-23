package com.borinquenterrier.employeedemo.model;

abstract class AEmployee implements IEmployee {

    protected int id;
    protected float vacationDays;
    protected float vacationDaysPerYear;

    protected int workDays;

    protected String name;

    public int getWorkDays() {
        return workDays;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public float getVacationDays() {
        return vacationDays;
    }

    @Override
    public float getVacationDaysPerYear() {
        return vacationDaysPerYear;
    }

    public float getVacationRate() {
        return vacationDaysPerYear / MAX_WORK_DAYS_PER_YEAR;
    }

    public String getName() {
        return name;
    }


    @Override
    public void work(int workDays) {
        validate(workDays);
        vacationDays = calculateVacationDays(getVacationDays(), workDays);
    }

    @Override
    public void takeVacation(float vacationDays) {
        if (this.vacationDays < vacationDays) {
            throw new NotEnoughVacactionDaysException();
        }
        this.vacationDays -= vacationDays;
    }
}
