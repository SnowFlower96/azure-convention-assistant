package com.example.manager.controller;

import com.example.manager.model.Product;
import com.example.manager.service.ProductService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @Title ProductController.java
 * @Description 상품 Rest API 컨트롤러
 * @History
 *   2025.09.15 노정현 [SRM202508104354] 상품 관리
 *                   - 최초작성
 */
@RestController
@RequestMapping("/products")
public class ProductController {

    // ===========================================
    // 필드
    // ===========================================
    private final ProductService productService;


    // ===========================================
    // 생성자
    // ===========================================
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    // ===========================================
    // 메소드
    // ===========================================
    /**
     * @Title list
     * @Description 상품 목록 전체 반환
     * @History
     *   2025.09.15 노정현 [SRM202508104354] 상품 관리
     *                   - 최초작성
     */
    @GetMapping
    public List<Product> list() throws Exception {
        return productService.getAllProducts();
    }

    /**
     * @Title get
     * @Description 단일 상품 조회
     * @History
     *   2025.09.15 노정현 [SRM202508104354] 상품 관리
     *                   - 최초작성
     */
    @GetMapping("/{id}")
    public Product get(@PathVariable int id) throws Exception {
        return productService.getProductById(id);
    }

    /**
     * @Title create
     * @Description 새로운 상품 등록
     * @History
     *   2025.09.15 노정현 [SRM202508104354] 상품 관리
     *                   - 최초작성
     */
    @PostMapping
    public Product create(@RequestBody Product p) throws Exception {
        return productService.createProduct(p);
    }

    /**
     * @Title update
     * @Description 기존 상품 수정
     * @History
     *   2025.09.15 노정현 [SRM202508104354] 상품 관리
     *                   - 최초작성
     */
    @PutMapping("/{id}")
    public Product update(@PathVariable int id, @RequestBody Product p) throws Exception {
        return productService.updateProduct(id, p);
    }

    /**
     * @Title delete
     * @Description 상품 삭제
     * @History
     *   2025.09.15 노정현 [SRM202508104354] 상품 관리
     *                   - 최초작성
     */
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable int id) throws Exception {
        return productService.deleteProduct(id);
    }

}
