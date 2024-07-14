package com.practicesoftwaretesting.cart.model;

import com.practicesoftwaretesting.product.model.Product;
import lombok.Data;

@Data
public class CartItem {
    private String id;
    private int quantity;
    private Double discountPercentage;
    private String cartId;
    private String productId;
    private Product product;
}
