package com.example.b5.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {
    private int productId;
    private String name;
    private long price;
    private String image;
    private int quantity;
}
