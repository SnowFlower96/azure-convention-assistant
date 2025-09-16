package com.example.manager.service;

import com.example.manager.model.Product;

import java.util.List;

/**
 * @Title ProductService.java
 * @Description 상품 관리 서비스 인터페이스
 *              CRUD 기능 정의
 * @History
 *   2025.09.15 노정현 [SRM202508104354] 상품 관리
 *                   - 최초작성
 */
public interface ProductService {

    public abstract List<Product> getAllProducts() throws Exception;
    public abstract Product getProductById(int id) throws Exception;
    public abstract Product createProduct(Product p) throws Exception;
    public abstract Product updateProduct(int id, Product p) throws Exception;
    public abstract boolean deleteProduct(int id) throws Exception;

}
