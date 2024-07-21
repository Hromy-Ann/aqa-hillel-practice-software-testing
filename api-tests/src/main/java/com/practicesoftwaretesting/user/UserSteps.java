package com.practicesoftwaretesting.user;
import com.practicesoftwaretesting.user.controller.UserController;
import com.practicesoftwaretesting.user.model.LoginRequest;
import com.practicesoftwaretesting.user.model.RegisterUserRequest;

public class UserSteps {

    public static final String DEFAULT_PASSWORD = "12Example#";
    public static final String ADMIN_EMAIL = "admin@practicesoftwaretesting.com";
    public static final String ADMIN_PASSWORD = "welcome01";

    public void registerUser(String userEmail, String password) {
        var userController = new UserController();
        var registerUserRequest = buildUser(userEmail, password);
        userController.registerUser(registerUserRequest).as();
    }

    public String loginUser(String userEmail, String password) {
        var userController = new UserController();
        var userLoginResponse = userController.loginUser(new LoginRequest(userEmail, password))
                .as();
        return userLoginResponse.getAccessToken();
    }

    public RegisterUserRequest buildUser(String email, String password) {
        return RegisterUserRequest.builder()
                .firstName("Anna")
                .lastName("Hromova")
                .address("Street")
                .city("City")
                .state("State")
                .country("Country")
                .postcode("1234AA")
                .phone("0987654321")
                .dob("2000-10-20")
                .password(password)
                .email(email)
                .build();
    }
}
