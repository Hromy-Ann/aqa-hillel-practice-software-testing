package com.practicesoftwaretesting.cart.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddCartItemRequest {
    private String productId;
    private int quantity;
}
