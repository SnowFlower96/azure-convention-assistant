import os
import json
import pandas as pd
import streamlit as st
from ai_search import AzureAiAgent

agent = AzureAiAgent()

st.set_page_config(page_title="Azure File Guidelines Checker", layout="wide")
st.subheader("샘플 파일 업로드 후 가이드라인 확인")

# 파일 업로드 처리
uploaded_file = st.file_uploader(
    "파일을 업로드하세요", type=["java", "py", "js", "ts", "txt"]
)

if uploaded_file is not None:
    try:
        # 새 파일이면 session_state 초기화
        if "current_file_name" not in st.session_state or st.session_state.current_file_name != uploaded_file.name:
            st.session_state.current_file_name = uploaded_file.name
            st.session_state.numbered_lines = [
                f"{i+1}: {line}" for i, line in enumerate(uploaded_file.read().decode("utf-8").splitlines())
            ]
            st.session_state.guidelines = []  # 탭 2용

        ext = os.path.splitext(uploaded_file.name)[1][1:] if os.path.splitext(uploaded_file.name)[1] else "plain"
        st.code("\n".join(st.session_state.numbered_lines), language=ext)

        # ------------------- 가이드라인 체크 -------------------
        if st.button("Check Guidelines"):
            numbered_lines_content = "\n".join(st.session_state.numbered_lines)

            # 가이드라인 검색
            guidelines = agent.search_guidelines(f"{uploaded_file.name} 주석 템플릿", top=2)
            st.session_state.guidelines = guidelines  # 탭 2용 저장

            # 가이드라인 체크
            response = agent.check_guidelines("prompt/comment-prompt.md", numbered_lines_content, guidelines)

            if response:
                result = json.loads(response.choices[0].message.content)

                # 주석 추가
                for v in result["violations"]:
                    idx = v["line"] - 1
                    code_part = st.session_state.numbered_lines[idx].split(": ", 1)
                    next_indent = len(code_part[1]) - len(code_part[1].lstrip()) + len(code_part[0])
                    st.session_state.numbered_lines[idx - 1] += f"\n{' ' * next_indent}// >>>>> Violation: {v['rule_id']} - {v['rule']}\n"
                    st.session_state.numbered_lines[idx - 1] += f"{' ' * next_indent}// >>>>> Reason: {v['reason']}\n"
                    st.session_state.numbered_lines[idx - 1] += f"{' ' * next_indent}// >>>>> Sample:\n"
                    for sample_line in v['sample'].splitlines():
                        st.session_state.numbered_lines[idx - 1] += f"{' ' * next_indent}// {sample_line}\n"
                    st.session_state.numbered_lines[idx - 1] += f"{' ' * next_indent}// >>>>> End of Sample"

                # ------------------- 탭 생성 -------------------
                tab1, tab2 = st.tabs(["Checked Code & Violations", "Reference Guidelines"])

                # 탭 1: 주석 추가된 코드 + 위반 테이블
                with tab1:
                    st.subheader("Updated File with Violations")
                    st.code("\n".join(st.session_state.numbered_lines), language=ext)

                    st.subheader("Guideline Violations")
                    table_data = [
                        {
                            "Line": v["line"],
                            "Code": v["code"],
                            "Rule ID": v["rule_id"],
                            "Rule": v["rule"],
                            "Reason": v["reason"],
                            "Sample": v["sample"]
                        }
                        for v in result["violations"]
                    ]
                    st.dataframe(pd.DataFrame(table_data), use_container_width=True)

                # 탭 2: 근거로 사용된 가이드라인
                with tab2:
                    st.subheader("Reference Guidelines")
                    for i, g in enumerate(st.session_state.guidelines, start=1):
                        st.markdown(f"**Guideline {i}:** {g}")

    except Exception as e:
        st.error(f"파일 처리 중 오류가 발생했습니다: {e}")
