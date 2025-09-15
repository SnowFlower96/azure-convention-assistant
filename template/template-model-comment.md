# 템플릿 적용 범위
- 주석만 해당

# 체크리스트
- javadoc 주석은 어노테이션 위쪽
- 필드의 한글명을 주석으로 표시
- 필드 추가시 한줄 띄우로 SR번호가 포함된 주석 이후에 작성
- Lombok 사용

# 템플릿
```java
/**
 * @Title {클래스명}.java
 * @Description {클래스 설명 작성}
 * @History
 *   {작성일} {작성자} [{SR번호}] {기능/모듈명}
 *                  - {1~2문장 정도의 간단한 수정내용1}
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
{클래스 선언부} {

    {필드}  // {한글로된 필드명}

    // {작성일} {작성자} [{SR번호}] - 필드추가
    {필드}  // {한글로된 필드명}

}
```