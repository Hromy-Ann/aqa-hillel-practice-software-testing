package com.practicesoftwaretesting.product.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductsFilterRequest {
    private String brand;
    private String category;
    private Boolean isRental;
    private String between;
    private String sort;
    private int page;
}
