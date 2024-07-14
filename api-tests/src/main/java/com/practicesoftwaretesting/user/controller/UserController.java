package com.practicesoftwaretesting.user.controller;

import com.practicesoftwaretesting.common.BaseController;
import com.practicesoftwaretesting.common.ResponseDecorator;
import com.practicesoftwaretesting.user.model.LoginRequest;
import com.practicesoftwaretesting.user.model.LoginResponse;
import com.practicesoftwaretesting.user.model.RegisterUserRequest;
import com.practicesoftwaretesting.user.model.RegisterUserResponse;

public class UserController extends BaseController {

    public ResponseDecorator<RegisterUserResponse> registerUser(RegisterUserRequest registerUserRequest) {
        return new ResponseDecorator<>(
                baseClient()
                        .body(registerUserRequest)
                        .post("/users/register"),
                RegisterUserResponse.class);
    }

    public ResponseDecorator<LoginResponse> loginUser(LoginRequest loginRequest) {
        return new ResponseDecorator<>(
                baseClient()
                        .body(loginRequest)
                        .post("/users/login"),
                LoginResponse.class);
    }

    public ResponseDecorator<RegisterUserResponse> getCurrentUser(String token) {
        return new ResponseDecorator<>(
                baseClient()
                        .header("Authorization", "Bearer " + token)
                        .get("/users/me"),
                RegisterUserResponse.class);
    }

    public ResponseDecorator<Void> deleteUser(String userId, String token) {
        return new ResponseDecorator<>(
                baseClient()
                        .header("Authorization", "Bearer " + token)
                        .delete("users/" + userId),
                Void.class);
    }
}
