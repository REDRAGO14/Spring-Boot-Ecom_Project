package com.app.ecom.DTO;

import lombok.Data;

import java.math.BigInteger;

@Data
public class ProductResponse {
    private Long id;
    private String name;
    private String description;
    private BigInteger price;
    private Integer stockQuantity;
    private String catagory;
    private String imageUrl;
    private boolean active;
}
