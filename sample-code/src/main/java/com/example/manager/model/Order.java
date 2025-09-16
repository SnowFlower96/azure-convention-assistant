package com.example.manager.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private int id;
    private int productId;
    private int quantity;  //hi
    private double totalPrice; // product price * quantity
}
