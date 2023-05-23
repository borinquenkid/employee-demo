package com.borinquenterrier.employeedemo.controller;


import com.borinquenterrier.employeedemo.model.*;
import com.borinquenterrier.employeedemo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class EmployeeController {


    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/employees")
    public ResponseEntity<List<IEmployee>> getAllEmployees() {
        List<IEmployee> tutorials = employeeRepository.getAllEmployees();
        return new ResponseEntity<>(tutorials, HttpStatus.OK);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<IEmployee> getEmployee(@PathVariable("id") int id) {
        IEmployee employee = employeeRepository.getEmployee(id);
        return employee != null
                ? new ResponseEntity<>(employee, HttpStatus.OK)
                : new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PutMapping("/employees/{id}/workdays/{workDays}")
    public ResponseEntity<IEmployee> updateWorkDays(@PathVariable("id") int id, @PathVariable("workDays") int workDays) {
        try {
            IEmployee employee = employeeRepository.updateWorkDays(id, workDays);
            return new ResponseEntity<>(employee, HttpStatus.OK);
        } catch (Throwable e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/employees/{id}/vacationdays/{vacationDays}")
    public ResponseEntity<IEmployee> updateVacationDays(@PathVariable("id") int id, @PathVariable("vacationDays") float vacationDays) {
        try {
            IEmployee employee = employeeRepository.updateVacationDays(id, vacationDays);
            return new ResponseEntity<>(employee, HttpStatus.OK);
        } catch (Throwable e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }


}
