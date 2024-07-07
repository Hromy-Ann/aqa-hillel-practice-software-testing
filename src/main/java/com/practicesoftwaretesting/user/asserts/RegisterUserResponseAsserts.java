package com.practicesoftwaretesting.user.asserts;

import com.practicesoftwaretesting.user.model.RegisterUserResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RegisterUserResponseAsserts {
    private final RegisterUserResponse actual;

    public RegisterUserResponseAsserts(RegisterUserResponse actual) {
        this.actual = actual;
    }

    public RegisterUserResponseAsserts createdAtIsNotNull() {
        assertNotNull(actual.getCreatedAt());
        return this;
    }

    public RegisterUserResponseAsserts emailIs(String expected) {
        assertEquals(expected, actual.getEmail());
        return this;
    }

    public RegisterUserResponseAsserts dobIs(String expected) {
        assertEquals(expected, actual.getDob());
        return this;
    }

    public RegisterUserResponseAsserts phoneIs(String expected) {
        assertEquals(expected, actual.getPhone());
        return this;
    }

    public RegisterUserResponseAsserts postCodeIs(String expected) {
        assertEquals(expected, actual.getPostcode());
        return this;
    }

    public RegisterUserResponseAsserts countryIs(String expected) {
        assertEquals(expected, actual.getCountry());
        return this;
    }

    public RegisterUserResponseAsserts stateIs(String expected) {
        assertEquals(expected, actual.getState());
        return this;
    }

    public RegisterUserResponseAsserts cityIs(String expected) {
        assertEquals(expected, actual.getCity());
        return this;
    }

    public RegisterUserResponseAsserts addressIs(String expected) {
        assertEquals(expected, actual.getAddress());
        return this;
    }

    public RegisterUserResponseAsserts lastNameIs(String expected) {
        assertEquals(expected, actual.getLastName());
        return this;
    }

    public RegisterUserResponseAsserts firstNameIs(String expected) {
        assertEquals(expected, actual.getFirstName());
        return this;
    }

    public RegisterUserResponseAsserts idIsNotNull() {
        assertNotNull(actual.getId());
        return this;
    }
}
