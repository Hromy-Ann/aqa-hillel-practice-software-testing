package com.practicesoftwaretesting;

import com.practicesoftwaretesting.user.asserts.GetUserResponseAsserts;
import com.practicesoftwaretesting.user.asserts.LoginResponseAsserts;
import com.practicesoftwaretesting.user.asserts.RegisterUserResponseAsserts;
import com.practicesoftwaretesting.user.controller.UserController;
import com.practicesoftwaretesting.user.model.LoginRequest;
import com.practicesoftwaretesting.user.model.RegisterUserRequest;
import org.junit.jupiter.api.Test;

class UserTest extends BaseTest {
    private static final String PASSWORD = "23Pass!word#";
    private static final String EMAIL = "hromy@ann.test";

    UserController userController = new UserController();

    @Test
    void testUser() {
        var registerUserRequest = buildUser();
        var registeredUser = userController.registerUser(registerUserRequest)
                .assertStatusCode(201)
                .as();
        new RegisterUserResponseAsserts(registeredUser)
                .idIsNotNull()
                .firstNameIs(registerUserRequest.getFirstName())
                .firstNameIs(registerUserRequest.getFirstName())
                .lastNameIs(registerUserRequest.getLastName())
                .addressIs(registerUserRequest.getAddress())
                .cityIs(registerUserRequest.getCity())
                .stateIs(registerUserRequest.getState())
                .countryIs(registerUserRequest.getCountry())
                .postCodeIs(registerUserRequest.getPostcode())
                .phoneIs(registerUserRequest.getPhone())
                .dobIs(registerUserRequest.getDob())
                .emailIs(registerUserRequest.getEmail())
                .createdAtIsNotNull();

        var loginRequestBody = new LoginRequest(EMAIL, PASSWORD);
        var userLoginResponse = userController.loginUser(loginRequestBody)
                .assertStatusCode(200)
                .as();
        new LoginResponseAsserts(userLoginResponse)
                .tokenIsNotNull()
                .tokenIsNotExpired()
                .tokenTypeIsBearer();

        var userToken = userLoginResponse.getAccessToken();
        var getUserResponse = userController
                .getCurrentUser(userToken)
                .as();
        new GetUserResponseAsserts(getUserResponse)
                .idIs(registeredUser.getId())
                .firstNameIs(registeredUser.getFirstName())
                .lastNameIs(registeredUser.getLastName())
                .addressIs(registeredUser.getAddress())
                .cityIs(registeredUser.getCity())
                .stateIs(registeredUser.getState())
                .countryIs(registeredUser.getCountry())
                .postCodeIs(registeredUser.getPostcode())
                .phoneIs(registeredUser.getPhone())
                .dobIs(registeredUser.getDob())
                .emailIs(registeredUser.getEmail())
                .createdAtIs(registeredUser.getCreatedAt());

        var adminLoginRequestBody = new LoginRequest(adminEmail, adminPassword);
        var adminloginResponse = userController.loginUser(adminLoginRequestBody)
                .as();

        var userId = getUserResponse.getId();
        var token = adminloginResponse.getAccessToken();
        userController.withToken(token).deleteUser(userId)
                .assertStatusCode(204);
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
