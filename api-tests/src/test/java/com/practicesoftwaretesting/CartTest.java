package com.practicesoftwaretesting;

import com.practicesoftwaretesting.cart.asserts.CartDetailsAsserts;
import com.practicesoftwaretesting.cart.asserts.CreateCartResponseAsserts;
import com.practicesoftwaretesting.cart.asserts.UpdateCartResponseAsserts;
import com.practicesoftwaretesting.cart.controller.CartController;
import com.practicesoftwaretesting.cart.model.AddCartItemRequest;
import com.practicesoftwaretesting.product.controller.ProductController;
import com.practicesoftwaretesting.product.model.ProductsFilterRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CartTest extends BaseTest {
    CartController cartController = new CartController();
    ProductController productController = new ProductController();
    private String productId;

    @BeforeEach
    void setUp() {
        productId = productController
                .getProducts(ProductsFilterRequest.builder().page(1).build())
                .as()
                .getData()
                .getFirst()
                .getId();
    }

    @Test
    void createUpdateAndDeleteCart() {
        var cart = cartController.createCart()
                .assertStatusCode(201)
                .as();
        new CreateCartResponseAsserts(cart)
                .idIsNotNull();

        var cartId = cart.getId();
        var updateCartResponse = cartController.addItemToCart(cartId, new AddCartItemRequest(productId, 1))
                .assertStatusCode(200)
                .as();
        new UpdateCartResponseAsserts(updateCartResponse)
                .hasGoodResult();

        var cartDetails = cartController.getCart(cartId)
                .assertStatusCode(200)
                .as();
        new CartDetailsAsserts(cartDetails)
                .containsProduct(productId);

        cartController.deleteCart(cartId)
                .assertStatusCode(204);
    }
}
