package com.practicesoftwaretesting.user.asserts;

import com.practicesoftwaretesting.user.model.LoginResponse;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginResponseAsserts {
    private static final String EXPECTED_TOKEN_TYPE = "bearer";
    private final LoginResponse actual;

    public LoginResponseAsserts(LoginResponse actual) {
        this.actual = actual;
    }

    public LoginResponseAsserts tokenIsNotExpired() {
        assertTrue(0 < actual.getExpiresIn());
        return this;
    }

    public LoginResponseAsserts tokenIsNotNull() {
        assertNotNull(actual.getAccessToken());
        return this;
    }

    public LoginResponseAsserts tokenTypeIsBearer() {
        assertTrue(EXPECTED_TOKEN_TYPE.equalsIgnoreCase(actual.getTokenType()));
        return this;
    }
}
