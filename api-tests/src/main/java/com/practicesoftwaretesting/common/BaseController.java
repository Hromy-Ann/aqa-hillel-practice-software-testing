package com.practicesoftwaretesting.common;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

import static io.restassured.http.ContentType.JSON;

public abstract class BaseController {
    private static final String BASE_URI = "https://api.practicesoftwaretesting.com";

    protected RequestSpecification baseClient() {
        return RestAssured.given()
                .baseUri(BASE_URI)
                .contentType(JSON);
    }
}
