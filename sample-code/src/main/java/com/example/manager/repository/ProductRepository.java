package com.example.manager.repository;

import com.example.manager.model.Product;

import java.util.List;


/**
 * @Title ProductRepository.java
 * @Description 상품 DAO 인터페이스
 * @History
 *   2025.09.15 노정현 [SRM202508104354] 상품 관리
 *                   - 최초작성
 */
public interface ProductRepository {

    // 2025.09.15 노정현 [SRM202508104354] 상품 관리
    List<Product> findAll() throws Exception;
    Product findById(int id) throws Exception;
    Product insert(Product p) throws Exception;
    Product update(Product p) throws Exception;
    boolean delete(int id) throws Exception;

}
