package com.practicesoftwaretesting.product.controller;

import com.practicesoftwaretesting.common.BaseController;
import com.practicesoftwaretesting.common.ResponseDecorator;
import com.practicesoftwaretesting.product.model.ProductsFilterRequest;
import com.practicesoftwaretesting.product.model.ProductsResponse;

public class ProductController extends BaseController {
    public ResponseDecorator<ProductsResponse> getProducts(ProductsFilterRequest filter) {
        return new ResponseDecorator<>(
                baseClient()
                        .queryParam("by_brand", filter.getBrand())
                        .queryParam("by_category", filter.getCategory())
                        .queryParam("is_rental", filter.getIsRental())
                        .queryParam("between", filter.getBetween())
                        .queryParam("sort", filter.getSort())
                        .queryParam("page", filter.getPage())
                        .get("/products"),
                ProductsResponse.class);
    }
}
