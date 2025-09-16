package com.example.manager.service;

import com.example.manager.model.Product;
import com.example.manager.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Title ProductServiceImpl.java
 * @Description ProductService 인터페이스 구현체, 상품 CRUD 기능 제공
 * @History
 *   2025.09.15 노정현 [SRM202508104354] 상품 관리
 *                   - 최초작성
 */
@Service
public class ProductServiceImpl implements ProductService {

    // ===========================================
    // 필드
    // ===========================================
    private final ProductRepository repo;



    // ===========================================
    // 생성자
    // ===========================================
    public ProductServiceImpl(ProductRepository repo) {
        this.repo = repo;
    }



    // ===========================================
    // 메소드
    // ===========================================
    /**
     * @Title getAllProducts
     * @Description 전체 상품 목록 조회
     * @History
     *   2025.09.15 노정현 [SRM202508104354] 상품 관리
     *                   - 최초작성
     */
    @Override
    public List<Product> getAllProducts() throws Exception {
        return repo.findAll();
    }

    /**
     * @Title getProductById
     * @Description ID로 단일 상품 조회
     * @History
     *   2025.09.15 노정현 [SRM202508104354] 상품 관리
     *                   - 최초작성
     */
    @Override
    public Product getProductById(int id) throws Exception {
        // 상품 id로 정보 확인
        Product p = repo.findById(id);

        // 조회가 되지 않으면 에러 발생
        if (p == null) throw new RuntimeException("Product not found");

        return p;
    }

    /**
     * @Title createProduct
     * @Description 새로운 상품 등록
     * @History
     *   2025.09.15 노정현 [SRM202508104354] 상품 관리
     *                   - 최초작성
     */
    @Override
    public Product createProduct(Product p) throws Exception {
        return repo.insert(p);
    }

    /**
     * @Title updateProduct
     * @Description 기존 상품 정보 수정
     * @History
     *   2025.09.15 노정현 [SRM202508104354] 상품 관리
     *                   - 최초작성
     */
    @Override
    public Product updateProduct(int id, Product p) throws Exception {
        // 상품 정보 수정
        p.setId(id);
        Product updated = repo.update(p);

        // 업데이트 되지 않았으면 에러 발생
        if (updated == null) throw new RuntimeException("Product not found");

        return updated;
    }

    /**
     * @Title deleteProduct
     * @Description ID로 상품 삭제
     * @History
     *   2025.09.15 노정현 [SRM202508104354] 상품 관리
     *                   - 최초작성
     */
    @Override
    public boolean deleteProduct(int id) throws Exception {
        // 상품 삭제
        boolean deleted = repo.delete(id);

        // 삭제되지 않았으면 에러처리
        if (!deleted) throw new RuntimeException("Product not found");

        return deleted;
    }
}
