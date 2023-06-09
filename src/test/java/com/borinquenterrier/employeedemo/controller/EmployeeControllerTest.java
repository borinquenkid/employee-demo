package com.borinquenterrier.employeedemo.controller;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.put;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EmployeeControllerTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setup() {
        RestAssured.port = port;
        RestAssured.baseURI = "http://localhost";
    }

    @Test
    void allEmployees() {
        get("/api/employees")
                .then()
                .statusCode(200)
                .contentType(JSON)
                .body("$.size()", equalTo(30));

    }

    @Test
    void work_and_vacation() {
        get("/api/employees/11")
                .then()
                .statusCode(200)
                .contentType(JSON)
                .body("id", equalTo(11));
        //Employee 11 has worked 11 days, 52 work days days = 3 vac days
        put("/api/employees/11/workdays/" + (52 - 11))
                .then()
                .statusCode(200)
                .contentType(JSON)
                .body("vacationDays", equalTo(3f));
        put("/api/employees/11/vacationdays/1.0")
                .then()
                .statusCode(200)
                .contentType(JSON)
                .body("vacationDays", equalTo(2f));
    }

    @Test
    void work_and_vacation_bad_employee_id() {
        get("/api/employees/a")
                .then()
                .statusCode(400);
    }


    @Test
    void work_and_vacation_bad_workdays() {
        get("/api/employees/12")
                .then()
                .statusCode(200)
                .body("id", equalTo(12));
        put("/api/employees/12/workdays/" + (-1))
                .then()
                .statusCode(400);
    }

    @Test
    void work_and_vacation_bad_vacation_days() {
        get("/api/employees/13")
                .then()
                .statusCode(200)
                .contentType(JSON)
                .body("id", equalTo(13));
        put("/api/employees/13/workdays/" + (52 - 13))
                .then()
                .statusCode(200)
                .contentType(JSON)
                .body("vacationDays", equalTo(3.0f));
        put("/api/employees/13/vacationdays/" + (-1))
                .then()
                .statusCode(400);
    }

}