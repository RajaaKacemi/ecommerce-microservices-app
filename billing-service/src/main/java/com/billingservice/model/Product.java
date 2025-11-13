package com.billingservice.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    private long id;
    private String name;
    private double price;
    private int quantity;
}
