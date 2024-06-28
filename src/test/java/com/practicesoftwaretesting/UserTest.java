package com.practicesoftwaretesting;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.LogDetail;
import org.junit.jupiter.api.Test;
import practicesoftwaretesting.LoginResponse;
import practicesoftwaretesting.RegisterUserResponse;

import static io.restassured.http.ContentType.JSON;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {

    static {
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri("https://api.practicesoftwaretesting.com")
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

    @Test
    void testUser() {
        var registerUserRequest = """
                {
                  "first_name": "Ann",
                  "last_name": "Hromy",
                  "address": "+",
                  "city": "Odesa",
                  "state": "-",
                  "country": "Ukraine",
                  "postcode": "65000",
                  "phone": "0997654321",
                  "dob": "2000-10-10",
                  "password": "23Pass!word#",
                  "email": "hromy@ann.test"
                }
                """;
        var registerUserResponse = RestAssured.given()
                .contentType(JSON)
                .body(registerUserRequest)
                .post("/users/register")
                .as(RegisterUserResponse.class);
        var loginRequestBody = """
                {
                    "password": "23Pass!word#",
                    "email": "hromy@ann.test"
                }
                """;

        var userLoginResponse = RestAssured.given()
                .contentType(JSON)
                .body(loginRequestBody)
                .post("/users/login")
                .as(LoginResponse.class);
        var userToken = userLoginResponse.getAccessToken();
        var getUserResponse = RestAssured.given()
                .contentType(JSON)
                .header("Authorization", "Bearer " + userToken)
                .when()
                .get("/users/me")
                .as(RegisterUserResponse.class);

        assertEquals(registerUserResponse.getId(), getUserResponse.getId());
        var adminLoginRequestBody = """
                {
                  "email": "admin@practicesoftwaretesting.com",
                  "password": "welcome01"
                }
                """;
        var adminloginResponse = RestAssured.given()
                .contentType(JSON)
                .body(adminLoginRequestBody)
                .post("/users/login")
                .as(LoginResponse.class);

        var userId = getUserResponse.getId();
        var token = adminloginResponse.getAccessToken();
        RestAssured.given()
                .contentType(JSON)
                .header("Authorization", "Bearer " + token)
                .delete("users/" + userId)
                .then()
                .statusCode(204);
    }
}
