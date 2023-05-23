package com.borinquenterrier.employeedemo.controller;

import io.restassured.RestAssured;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class RestAssuredExtension implements BeforeAllCallback {

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        RestAssured.port = 8080;
        RestAssured.baseURI = "http://localhost";
        RestAssured.rootPath = "/api";
    }
}
