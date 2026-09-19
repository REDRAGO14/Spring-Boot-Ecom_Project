package com.app.ecom.DTO;

import lombok.Data;

import java.math.BigDecimal;
public record ProductResponse (
    Long id,
    String name,
    String description,
    BigDecimal price,
    Integer stockQuantity,
    String catagory,
    String imageUrl,
    boolean active
){}
