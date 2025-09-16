# Java 주석 컨벤션 (공통)
- 적용 확장자: .java

## C001 작성일 형식
- C001-01: YYYY.MM.DD

## C002 SR번호
- C002-01: SR번호는 SRM으로 시작하며 SRM + YYYYMMDD형식의 날짜 + 5자리로 구성

## C003 줄바꿈
- C003-01: 클래스 내 상단과 하단 줄바꿈: 1라인
- C003-02: 필드/생성자/메서드 주석 사이 줄바꿈: 2라인
- C003-03: 서비스/레포지토리는 인터페이스 상속하여 작성

## C004 클래스 주석
- C004-01: 클래스 선언부 어노테이션 바로 위에 작성
- C004-02: @Title, @Description, @History 필드로 구성
- 예시
```java
/**
 * @Title ProductController.java
 * @Description 상품 관련 요청을 처리하는 컨트롤러
 * @History
 *   2025.08.15 노정현 [SRM2025091512345] 상품 관리 기능 추가
 *                    - 최초작성
 *   2025.09.15 노정현 [SRM2025091512345] 주문 기능 추가
 *                    - 상품별 주문 이력 확인 API 추가
 */
public class ProductController {
    ...
}
```

## C005 필드 주석
- C005-01: 필드명 주석 작성
- 예시
```java
private int productId;  // 상품ID
```

## C006 메서드 주석
- C006-01: 메서드 선언 위에 Javadoc 작성
- C006-02: @Title, @Description, @History 포함
- C006-03: boolean 반환 메서드는 is/has/can 접두사 권장
- C006-04: 조회 메서드는 search 접두사 권장
- 예시
```java
/**
 * @Title getProductList
 * @Description 상품 목록을 반환
 * @History
 *   2025.08.15 노정현 [SRM2025091512345] 상품 관리 기능 추가
 *                    - 최초작성
 *   2025.09.15 노정현 [SRM2025091512345] 주문 횟수 반영
 *                    - 결과에 상품 주문 횟수 추가
 */
public List<Product> getProductList() {
    ...
}
```

## C007 메서드 내 라인 주석
- C007-01: 코드 수정 시 바로 위에 수정일, 수정자, SR번호를 포함하여 간략하게 작성
- C007-02: 5단어 이하 요약 중심
- 예시
```java
// 2025.09.15 노정현 [SRM2025091512345] - 상품 목록 조회 추가
List<Product> products = productService.getAllProducts();
```

## C008 @History 규칙
- C008-01: 클래스/메서드 수정 시 새 라인 추가
- C008-02: 날짜, 작성자, SR번호, 수정 내용 포함
- C008-03: 1~2문장 간략히 작성
- 예시
```java
/**
 * @History
 *   ...
 *   2025.09.15 노정현 [SRM2025091512345] 주문 횟수 반영
 *                    - 결과에 상품 주문 횟수 추가
 *                    - 리턴 타입 수정
 */
```