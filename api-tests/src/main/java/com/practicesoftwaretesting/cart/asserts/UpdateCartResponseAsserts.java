package com.practicesoftwaretesting.cart.asserts;

import com.practicesoftwaretesting.cart.model.UpdateCartResponse;
import lombok.AllArgsConstructor;

import static org.junit.jupiter.api.Assertions.assertTrue;

@AllArgsConstructor
public class UpdateCartResponseAsserts {
    private static final String EXPECTED_RESULT = "item added or updated";

    private final UpdateCartResponse actual;

    public UpdateCartResponseAsserts hasGoodResult() {
        assertTrue(EXPECTED_RESULT.equalsIgnoreCase(actual.getResult()));
        return this;
    }
}
