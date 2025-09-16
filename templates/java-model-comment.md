# Java 주석 컨벤션 (DTO/Model)

## M000 DTO/Model 규칙
- M000-01: DTO/Model 클래스는 **필드 블록만 존재**
- M000-02: 생성자는 Lombok(@NoArgsConstructor, @AllArgsConstructor) 사용
- M000-03: Getter/Setter는 @Getter, @Setter 사용
- M000-04: "필드" 블록 주석은 두 개의 구분선(`// =====`) 사이에 위치
- 예시
```java
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    // ===========================================
    // 필드
    // ===========================================
    private int id;              // 상품ID

}
private String productName;  // 상품명

```

# M001 필드 추가 규칙
- M001-01: 필드 추가 개발할 경우 기존 필드와 구분지어 작성
- 예시
```java
// ===========================================
// 필드
// ===========================================
private int id;              // 상품ID
private String productName;  // 상품명

// 2025.09.15 노정현 [SRM2025091512345] - 주문 횟수 추가
private int orderCnt;        // 주문 횟수
```