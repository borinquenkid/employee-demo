package com.borinquenterrier.employeedemo.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class HourlyEmployeeTest {

    private HourlyEmployee hourlyEmployee;

    @BeforeEach
    void setup() {
        hourlyEmployee = new HourlyEmployee("hourly", 0);
    }

    @Test
    void getDefault() {
        assertEquals(0f, hourlyEmployee.getVacationDays());
    }

    @Test
    public void whenGreaterThanMaxDays_tooMuchException() {
        Exception exception = assertThrows(TooMuchWorkException.class, () -> hourlyEmployee.work(IEmployee.MAX_WORK_DAYS_PER_YEAR + 1));

        assertEquals("You are working too much", exception.getMessage());
    }

    @Test
    public void whenLessThanZeroDays_tooLittleException() {
        Exception exception = assertThrows(NegativeWorkException.class, () -> hourlyEmployee.work(IEmployee.MIN_WORK_DAYS_PER_YEAR - 1));

        assertEquals("You can not work negative days", exception.getMessage());
    }

    @ParameterizedTest(name = "work {0} days, expect {1} vacation days")
    @CsvSource(textBlock = """
               0,   0f
              26,   1f
              52,   2f
              78,   3f
             104,   4f
             130,   5f
             156,   6f
             182,   7.0000005f
             208,   8f
             234,   9f
             260 , 10f
            """
    )
    void workDays(int workDays, float expectedVacationDays) {
        hourlyEmployee.work(workDays);
        assertEquals(expectedVacationDays, hourlyEmployee.getVacationDays());
    }

    @Test
    void valid_take_vacationDays() {
        hourlyEmployee.work(26);
        hourlyEmployee.takeVacation(1.0f);
        assertEquals(0, hourlyEmployee.getVacationDays());
    }

    @Test
    public void invalid_take_vacationDays() {
        Exception exception = assertThrows(NotEnoughVacactionDaysException.class, () -> hourlyEmployee.takeVacation(1f));
        assertEquals("You have not worked enough days", exception.getMessage());
    }


}