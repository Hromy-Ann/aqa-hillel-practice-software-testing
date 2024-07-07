package com.practicesoftwaretesting;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.LogDetail;

public abstract class BaseTest {
    static {
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .log(LogDetail.ALL)
                .build();
        RestAssured.responseSpecification = new ResponseSpecBuilder()
                .log(LogDetail.ALL)
                .build();
        RestAssured.config = RestAssuredConfig.config()
                .objectMapperConfig(new ObjectMapperConfig()
                        .jackson2ObjectMapperFactory((type, s) -> {
                                    ObjectMapper objectMapper = new ObjectMapper();
                                    objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
                                    return objectMapper;
                                }
                        ));
    }
}
