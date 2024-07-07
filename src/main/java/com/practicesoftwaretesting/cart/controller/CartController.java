package com.practicesoftwaretesting.cart.controller;

import com.practicesoftwaretesting.cart.model.AddCartItemRequest;
import com.practicesoftwaretesting.cart.model.CartDetails;
import com.practicesoftwaretesting.cart.model.CreateCartResponse;
import com.practicesoftwaretesting.cart.model.UpdateCartResponse;
import com.practicesoftwaretesting.common.BaseController;
import com.practicesoftwaretesting.common.ResponseDecorator;

public class CartController extends BaseController {

    public ResponseDecorator<CreateCartResponse> createCart() {
        return new ResponseDecorator<>(
                baseClient().post("/carts"),
                CreateCartResponse.class);
    }

    public ResponseDecorator<UpdateCartResponse> addItemToCart(String cartId, AddCartItemRequest cartItem) {
        return new ResponseDecorator<>(
                baseClient().body(cartItem).post("/carts/" + cartId),
                UpdateCartResponse.class);
    }

    public ResponseDecorator<CartDetails> getCart(String cartId) {
        return new ResponseDecorator<>(
                baseClient().get("/carts/" + cartId),
                CartDetails.class);
    }

    public ResponseDecorator<Void> deleteCart(String cartId) {
        return new ResponseDecorator<>(
                baseClient().delete("/carts/" + cartId),
                Void.class);
    }
}
