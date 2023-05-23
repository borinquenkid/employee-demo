package com.borinquenterrier.employeedemo.repository;


import com.borinquenterrier.employeedemo.model.*;
import net.datafaker.Faker;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Service
public class EmployeeRepository {


    private List<IEmployee> employees = new ArrayList<>();


    public EmployeeRepository() {
        Faker faker = new Faker();
        IntStream.rangeClosed(1, 30).forEach((workDays) -> {
            IEmployee employee = null;
            if (workDays > 0 && workDays <= 10) {
                employee = new HourlyEmployee(faker.name().fullName(), workDays);
            } else if (workDays > 10 && workDays <= 20) {
                employee = new SalariedEmployee(faker.name().fullName(), workDays);
            } else {
                employee = new ManagerEmployee(faker.name().fullName(), workDays);
            }
            employee.work(workDays);
            employees.add(employee);
        });


    }

    public List<IEmployee> getAllEmployees() {
        return employees;
    }

    public IEmployee getEmployee(int oneBasedIndex) {
        if (oneBasedIndex > 0 && oneBasedIndex <= 30) {
            return employees.get(oneBasedIndex - 1);
        } else {
            return null;
        }
    }

    public IEmployee updateWorkDays(int id, int workDays) {
        IEmployee employee = getEmployee(id);
        if (employee == null) {
            throw new RuntimeException();
        }
        employee.work(workDays);
        return employee;
    }

    public IEmployee updateVacationDays(int id, float vacationDays) {
        IEmployee employee = getEmployee(id);
        if (employee == null) {
            throw new RuntimeException();
        }
        employee.takeVacation(vacationDays);
        return employee;
    }
}
