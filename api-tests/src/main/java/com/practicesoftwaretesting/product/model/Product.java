package com.practicesoftwaretesting.product.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {
    private String id;
    private String name;
    private String description;
    private double price;
    private boolean isLocationOffer;
    private boolean isRental;
    private boolean inStock;
}
