package com.practicesoftwaretesting.cart.model;

import lombok.Data;

import java.util.List;

@Data
public class CartDetails {
    private String id;
    private Double additionalDiscountPercentage;
    private Double lat;
    private Double lng;
    private List<CartItem> cartItems;
}
