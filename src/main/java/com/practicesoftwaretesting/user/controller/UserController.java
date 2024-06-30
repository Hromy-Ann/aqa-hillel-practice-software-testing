package com.practicesoftwaretesting.user.controller;

import com.practicesoftwaretesting.common.BaseController;
import com.practicesoftwaretesting.user.model.LoginRequest;
import com.practicesoftwaretesting.user.model.RegisterUserRequest;
import io.restassured.response.Response;

public class UserController extends BaseController {

    public Response registerUser(RegisterUserRequest registerUserRequest) {
        return baseClient()
                .body(registerUserRequest)
                .post("/users/register");
    }

    public Response loginUser(LoginRequest loginRequest) {
        return baseClient()
                .body(loginRequest)
                .post("/users/login");
    }

    public Response getCurrentUser(String userToken) {
        return baseClient()
                .header("Authorization", "Bearer " + userToken)
                .when()
                .get("/users/me");
    }

    public Response deleteUser(String userId, String token) {
        return baseClient()
                .header("Authorization", "Bearer " + token)
                .delete("users/" + userId);
    }
}
