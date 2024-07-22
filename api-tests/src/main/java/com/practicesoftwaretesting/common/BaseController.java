package com.practicesoftwaretesting.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import com.practicesoftwaretesting.utils.ConfigReader;

import static io.restassured.http.ContentType.JSON;

public abstract class BaseController<T> {

    private final ConfigReader configReader = new ConfigReader();

    private String authToken;

    static {
        configureRestAssured();
    }

    private static void configureRestAssured() {
        var objectMapper = new ObjectMapper();
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);

        RestAssured.config = RestAssured.config()
                .objectMapperConfig(
                        RestAssured.config()
                                .getObjectMapperConfig()
                                .jackson2ObjectMapperFactory((cls, charset) -> objectMapper)
                );
    }
    protected RequestSpecification baseClient() {
        var requestSpecification = RestAssured.given()
                .baseUri(configReader.getProperty("base.api.url"))
                .contentType(JSON);
        if (authToken != null) {
            requestSpecification.header("Authorization", "Bearer" + authToken);
        }
        return requestSpecification;
    }

    public T withToken(String token) {
        this.authToken = token;
        return (T) this;
    }

    public void cleanToken() {
        this.authToken = null;
    }
}
