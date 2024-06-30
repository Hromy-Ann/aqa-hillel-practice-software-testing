package com.practicesoftwaretesting.cart.controller;

import com.practicesoftwaretesting.common.BaseController;
import com.practicesoftwaretesting.cart.model.AddCartItemRequest;
import io.restassured.response.Response;

public class CartController extends BaseController {

    public Response createCart() {
        return baseClient()
                .post("/carts");
    }

    public Response addItemToCart(String cartId, AddCartItemRequest cartItem) {
        return baseClient()
                .body(cartItem)
                .post("/carts/" + cartId);
    }

    public Response getCart(String cartId) {
        return baseClient()
                .get("/carts/" + cartId);
    }

    public Response deleteCart(String cartId) {
        return baseClient()
                .delete("/carts/" + cartId);
    }
}
