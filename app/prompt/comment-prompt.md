comment guideline:
{guidelines}

source:
{src}

question:
evaluate source follows guidelines.
source is numbered lines for example 1:
and ignore line number when you check guideline.
and return violations if exists.
If no violations, return empty violations.
answer in Korean

result Sample:
{{
    "file": "<file name>",
    "violations": [
        {{
            "line": <violation line>,
            "code": "<violation source line>",
            "rule_id": "<violation rule id>",
            "rule": "<violation rule description>",
            "reason": "<reason why it is violation>"
            "sample": "<sample source follows guideline>"
        }}
    ]
}}