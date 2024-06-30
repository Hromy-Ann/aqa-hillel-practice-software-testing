package com.practicesoftwaretesting;

import com.practicesoftwaretesting.user.controller.UserController;
import com.practicesoftwaretesting.user.model.LoginRequest;
import com.practicesoftwaretesting.user.model.LoginResponse;
import com.practicesoftwaretesting.user.model.RegisterUserRequest;
import com.practicesoftwaretesting.user.model.RegisterUserResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserTest extends BaseTest {
    private static final String PASSWORD = "23Pass!word#";
    private static final String EMAIL = "hromy@ann.test";

    UserController userController = new UserController();

    @Test
    void testUser() {
        var registerUserRequest = buildUser();
        var registerUserResponse = userController.registerUser(registerUserRequest)
                .as(RegisterUserResponse.class);
        var loginRequestBody = new LoginRequest(EMAIL, PASSWORD);
        var userLoginResponse = userController.loginUser(loginRequestBody)
                .as(LoginResponse.class);
        var userToken = userLoginResponse.getAccessToken();
        var getUserResponse = userController.getCurrentUser(userToken)
                .as(RegisterUserResponse.class);

        assertEquals(registerUserResponse.getId(), getUserResponse.getId());
        var adminLoginRequestBody = new LoginRequest("admin@practicesoftwaretesting.com", "welcome01");
        var adminloginResponse = userController.loginUser(adminLoginRequestBody)
                .as(LoginResponse.class);

        var userId = getUserResponse.getId();
        var token = adminloginResponse.getAccessToken();
        userController.deleteUser(userId, token)
                .then()
                .statusCode(204);
    }

    private RegisterUserRequest buildUser() {
        return RegisterUserRequest.builder()
                .firstName("Ann")
                .lastName("Hromy")
                .address("+")
                .city("Odesa")
                .state("-")
                .country("Ukraine")
                .postcode("65000")
                .phone("0997654321")
                .dob("2000-10-10")
                .password(PASSWORD)
                .email(EMAIL)
                .build();
    }
}
