package com.practicesoftwaretesting.cart.asserts;

import com.practicesoftwaretesting.cart.model.CartDetails;
import com.practicesoftwaretesting.cart.model.CartItem;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CartDetailsAsserts {
    private final CartDetails actual;

    public CartDetailsAsserts(CartDetails actual) {
        this.actual = actual;
    }

    public CartDetailsAsserts containsProduct(String expectedProductId) {
        assertTrue(actual
                .getCartItems()
                .stream()
                .map(CartItem::getProductId)
                .anyMatch(expectedProductId::equals));
        return this;
    }
}
