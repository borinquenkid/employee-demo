package com.borinquenterrier.employeedemo.controller;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.JSON;
import static io.restassured.matcher.RestAssuredMatchers.*;
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
                .contentType(JSON)
                .statusCode(200)
                .body("$.size()", equalTo(30));

    }

    @Test
    void work_and_vacation() {
        get("/api/employees/11")
                .then()
                .contentType(JSON)
                .statusCode(200)

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

}