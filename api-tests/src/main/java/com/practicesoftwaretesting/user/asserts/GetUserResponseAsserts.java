package com.practicesoftwaretesting.user.asserts;

import com.practicesoftwaretesting.user.model.RegisterUserResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetUserResponseAsserts {
    private final RegisterUserResponse actual;

    public GetUserResponseAsserts(RegisterUserResponse actual) {
        this.actual = actual;
    }


    public GetUserResponseAsserts createdAtIs(String expected) {
        assertEquals(expected, actual.getCreatedAt());
        return this;
    }

    public GetUserResponseAsserts emailIs(String expected) {
        assertEquals(expected, actual.getEmail());
        return this;
    }

    public GetUserResponseAsserts dobIs(String expected) {
        assertEquals(expected, actual.getDob());
        return this;
    }

    public GetUserResponseAsserts phoneIs(String expected) {
        assertEquals(expected, actual.getPhone());
        return this;
    }

    public GetUserResponseAsserts postCodeIs(String expected) {
        assertEquals(expected, actual.getPostcode());
        return this;
    }

    public GetUserResponseAsserts countryIs(String expected) {
        assertEquals(expected, actual.getCountry());
        return this;
    }

    public GetUserResponseAsserts stateIs(String expected) {
        assertEquals(expected, actual.getState());
        return this;
    }

    public GetUserResponseAsserts cityIs(String expected) {
        assertEquals(expected, actual.getCity());
        return this;
    }

    public GetUserResponseAsserts addressIs(String expected) {
        assertEquals(expected, actual.getAddress());
        return this;
    }

    public GetUserResponseAsserts lastNameIs(String expected) {
        assertEquals(expected, actual.getLastName());
        return this;
    }

    public GetUserResponseAsserts firstNameIs(String expected) {
        assertEquals(expected, actual.getFirstName());
        return this;
    }

    public GetUserResponseAsserts idIs(String expected) {
        assertEquals(expected, actual.getId());
        return this;
    }
}
