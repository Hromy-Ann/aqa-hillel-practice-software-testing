package com.practicesoftwaretesting.cart.asserts;

import com.practicesoftwaretesting.cart.model.CreateCartResponse;
import lombok.AllArgsConstructor;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@AllArgsConstructor
public class CreateCartResponseAsserts {
    private final CreateCartResponse actual;

    public CreateCartResponseAsserts idIsNotNull() {
        assertNotNull(actual.getId());
        return this;
    }
}
