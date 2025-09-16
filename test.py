import os
import json
from app.ai_search import AzureAiAgent

GIT_DIR = os.path.join(os.path.dirname(os.path.abspath(__file__)), "../git-repository/src/main/java/com/example/manager")

# test_file = "model/Order.java"
test_file = "controller/OrderController.java"

agent = AzureAiAgent()
guidelines = agent.search_guidelines(f"{test_file} 주석 템플릿", top=2)
with open("guidelines", "w", encoding="utf-8") as f:
    f.write(guidelines)

with open(f"{GIT_DIR}/{test_file}", "r", encoding="utf-8") as f:
    numbered_lines = [f"{i+1}: {line.rstrip()}" for i, line in enumerate(f.readlines())]
    src = "\n".join(numbered_lines)
    
response = agent.check_guidelines("prompt/comment-prompt.md", src, guidelines)
with open("response.json", "w", encoding="utf-8") as f:
    f.write(response.choices[0].message.content)
print(response.choices[0].message.content)

json_response = json.loads(response.choices[0].message.content)
with open("result.md", "w", encoding="utf-8") as f:
    for violation in json_response['violations']:
        f.write(f"- line: {violation['line']}\n")
        f.write(f"- code: {violation['code']}\n")
        f.write(f"- rule_id: {violation['rule_id']}\n")
        f.write(f"- rule: {violation['rule']}\n")
        f.write(f"- sample:\n")
        f.write(f"```java\n{violation['sample']}\n```\n")
        f.write(f"// ===========================================\n")