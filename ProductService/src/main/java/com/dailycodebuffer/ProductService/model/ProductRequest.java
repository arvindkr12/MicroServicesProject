package com.dailycodebuffer.ProductService.model;

import lombok.Data;

@Data
public class ProductRequest {
    private String name;
    private long quantity;
    private long price;
}
