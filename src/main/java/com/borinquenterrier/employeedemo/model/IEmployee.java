package com.borinquenterrier.employeedemo.model;

public interface IEmployee {

    int MAX_WORK_DAYS_PER_YEAR = 260;
    int MIN_WORK_DAYS_PER_YEAR = 0;

    int getId();

    int getWorkDays();

    float getVacationDays();

    void work(int workDays);

    float getVacationDaysPerYear();

    float getVacationRate();

    String getName();

    default float calculateVacationDays(float currentVacationDays, int workDays) {
        float vacationDaysPerYear = getVacationDaysPerYear();
        float vacationDays = currentVacationDays + workDays * getVacationRate();
        if (vacationDays > vacationDaysPerYear) {
            vacationDays = vacationDaysPerYear;
        }
        return vacationDays;
    }

    default void validate(int workDays) {
        if (workDays > IEmployee.MAX_WORK_DAYS_PER_YEAR) {
            throw new TooMuchWorkException();
        }
        if (workDays < IEmployee.MIN_WORK_DAYS_PER_YEAR) {
            throw new NegativeWorkException();
        }
    }

    void takeVacation(float vacationDays);
}
