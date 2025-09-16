# ☁️ Azure Convention Assistant

**Azure Convention Assistant**는 업로드된 소스코드에 대해 주석 가이드라인 준수 여부를 자동으로 확인하고 시각화해주는 웹 애플리케이션입니다.  
Streamlit, Azure Cognitive Search, Azure OpenAI API를 활용하여 정적 분석 및 문서 기반 가이드 추천 기능을 제공합니다.

---

## 🔧 주요 기능

- 📤 Java, Python, JS 등 다양한 코드 업로드 지원
- 🔎 Azure Cognitive Search를 통해 관련 주석 가이드라인 문서 검색
- 🤖 Azure OpenAI (GPT) 모델을 통해 소스코드 분석 및 가이드라인 위반 탐지
- 💬 위반된 규칙에 대해 자동으로 설명 및 예시 제공
- 📊 시각화된 테이블 및 주석 삽입 코드 제공

---

## 📁 프로젝트 구조

```bash
.
├── app/                        # Streamlit 기반 앱 코드
│   ├── app.py                 # 메인 웹 인터페이스
│   ├── ai_search.py           # Azure Search & OpenAI 연동 모듈
│   ├── prompt/
│   │   └── comment-prompt.md  # OpenAI 프롬프트 템플릿
│   └── streamlit.sh           # 실행 스크립트
│
├── templates/                 # 주석 가이드라인 문서 (검색 대상)
│   ├── java-common-comment.md
│   ├── java-controller-comment.md
│   └── java-model-comment.md
│
├── sample-code/               # 예시 Java 프로젝트
│   └── src/main/java/...      # Controller, Service, Repository 포함
│
├── test-product.http          # HTTP 테스트 샘플
├── upload_templates.sh        # Azure Blob Storage 업로드 스크립트
└── uv.lock                    # Python 환경 설정
```

---

## 🚀 실행 방법

### 1. 환경변수 설정

`.env` 파일을 `app/` 디렉토리에 생성하고 다음 항목들을 설정합니다:

```env
AI_SEARCH_ENDPOINT=https://<your-search-service>.search.windows.net
AI_SEARCH_KEY=<your-search-key>
AI_SEARCH_INDEX_NAME_TEMPLATE=your-template-index

OPENAPI_KEY=<your-azure-openai-key>
OPENAPI_VERSION=2023-12-01-preview
OPENAPI_ENDPOINT=https://<your-openai-resource>.openai.azure.com
```

### 2. 패키지 설치

```bash
pip install -r requirements.txt  # or use Poetry/uv if used
```

### 3. Streamlit 실행

```bash
cd app
streamlit run app.py
```

---

## 🧠 기술 스택

- **Frontend**: [Streamlit](https://streamlit.io/)
- **Search**: [Azure Cognitive Search](https://learn.microsoft.com/en-us/azure/search/)
- **LLM**: [Azure OpenAI (GPT-4)](https://learn.microsoft.com/en-us/azure/cognitive-services/openai/)
- **Language Support**: Java (우선), Python, JS 등 확장 가능

---

## 📝 주요 파일 설명

| 파일 | 설명 |
|------|------|
| `app/app.py` | 사용자 코드 업로드 및 가이드 위반 분석 결과 표시 |
| `app/ai_search.py` | Azure Search와 OpenAI 연동, 분석 및 포맷 처리 |
| `templates/*.md` | Java용 주석 컨벤션 문서들 |
| `sample-code/` | 샘플 분석 대상 Java 백엔드 프로젝트 |
| `prompt/comment-prompt.md` | GPT에게 전달되는 분석 요청 프롬프트 템플릿 |

---