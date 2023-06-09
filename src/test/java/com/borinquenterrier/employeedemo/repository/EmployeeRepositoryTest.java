package com.borinquenterrier.employeedemo.repository;

import com.borinquenterrier.employeedemo.model.IEmployee;
import com.borinquenterrier.employeedemo.model.SalariedEmployee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EmployeeRepositoryTest {

    private EmployeeRepository employeeRepository;
    @BeforeEach
    void setup() {
        employeeRepository = new EmployeeRepository();
    }
    @Test
    void getAllEmployees() {
        List<IEmployee> allEmployees = employeeRepository.getAllEmployees();
        assertEquals(30, allEmployees.size());
    }
    @Test
    void work_and_vacation() {
        IEmployee employee = null;
        employee = employeeRepository.getEmployee(11);
        assert employee instanceof SalariedEmployee;
        assertEquals(11,employee.getId());
        //Employee 11 has worked 11 days, 52 work days days = 3 vac days
        employee.work(52-11);
        employee = employeeRepository.getEmployee(11);
        assertEquals(3f, employee.getVacationDays());
        employee.takeVacation(1.0f);
        employee = employeeRepository.getEmployee(11);
        assertEquals(2f, employee.getVacationDays());
    }

}