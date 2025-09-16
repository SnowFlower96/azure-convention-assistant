package com.example.manager.model;

import lombok.*;


/**
 * @Title Product.java
 * @Description Product 모델 클래스
 * @History
 *   2025.09.15 노정현 [SRM202508104354] 상품 관리
 *                   - 최초작성
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    private int id;        // 상품 ID
    private String name;   // 상품명
    private double price;  // 가격

}
