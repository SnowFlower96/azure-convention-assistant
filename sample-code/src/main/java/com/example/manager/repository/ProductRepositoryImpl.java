package com.example.manager.repository;

import com.example.manager.model.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @Title ProductRepositoryImpl.java
 * @Description ProductRepository 구현체, JSON 파일 기반 CRUD 처리
 * @History
 *   2025.09.15 노정현 [SRM202508104354] 상품 관리
 *                   - 최초작성
 */
@Repository
public class ProductRepositoryImpl implements ProductRepository {

    // ===========================================
    // 필드
    // ===========================================
    private static final String FILE_PATH = "src/main/resources/products.json";
    private final ObjectMapper mapper = new ObjectMapper();


    // ===========================================
    // 생성자
    // ===========================================


    // ===========================================
    // 메소드
    // ===========================================
    /**
     * @Title load
     * @Description products.json 파일에서 전체 상품 목록 불러오기
     * @History
     *   2025.09.15 노정현 [SRM202508104354] 상품 관리
     *                   - 최초작성
     */
    private List<Product> load() throws Exception {
        // 파일 경로 확인
        File file = new File(FILE_PATH);
        if (!file.exists()) return new ArrayList<>();

        return mapper.readValue(file, new TypeReference<List<Product>>() {});
    }

    /**
     * @Title save
     * @Description 상품 목록을 products.json 파일에 저장
     * @History
     *   2025.09.15 노정현 [SRM202508104354] 상품 관리
     *                   - 최초작성
     */
    private void save(List<Product> list) throws Exception {
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(FILE_PATH), list);
    }

    /**
     * @Title findAll
     * @Description 전체 상품 목록 조회
     * @History
     *   2025.09.15 노정현 [SRM202508104354] 상품 관리
     *                   - 최초작성
     */
    @Override
    public List<Product> findAll() throws Exception {
        return load();
    }

    /**
     * @Title findById
     * @Description ID로 단일 상품 조회
     * @History
     *   2025.09.15 노정현 [SRM202508104354] 상품 관리
     *                   - 최초작성
     */
    @Override
    public Product findById(int id) throws Exception {
        return load().stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    /**
     * @Title insert
     * @Description 새로운 상품 추가, ID 자동 증가
     * @History
     *   2025.09.15 노정현 [SRM202508104354] 상품 관리
     *                   - 최초작성
     */
    @Override
    public Product insert(Product p) throws Exception {
        List<Product> list = load();

        // 신규 ID 발급
        int nextId = list.stream().mapToInt(Product::getId).max().orElse(0) + 1;
        p.setId(nextId);
        list.add(p);

        // 저장
        save(list);

        return p;
    }

    /**
     * @Title update
     * @Description 기존 상품 정보 수정
     * @History
     *   2025.09.15 노정현 [SRM202508104354] 상품 관리
     *                   - 최초작성
     */
    @Override
    public Product update(Product p) throws Exception {
        List<Product> list = load();

        // 상품 저장
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == p.getId()) {
                list.set(i, p);
                save(list);

                return p;
            }
        }

        return null;
    }

    /**
     * @Title delete
     * @Description ID로 상품 삭제
     * @History
     *   2025.09.15 노정현 [SRM202508104354] 상품 관리
     *                   - 최초작성
     */
    @Override
    public boolean delete(int id) throws Exception {
        // 상품 목록 확인
        List<Product> list = load();

        // 삭제여부 확인
        boolean removed = list.removeIf(p -> p.getId() == id);

        // 삭제되었으면 저장
        if (removed) save(list);

        return removed;
    }
}
