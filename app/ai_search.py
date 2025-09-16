import os
import base64
import textwrap
from typing import List
from dotenv import load_dotenv

from azure.search.documents import SearchClient
from azure.search.documents.models import QueryType
from azure.core.credentials import AzureKeyCredential
from azure.core.paging import ItemPaged
from openai import AzureOpenAI
from openai.types.chat import ChatCompletion



class AzureAiAgent:
    _template_base_dir = os.path.dirname(os.path.abspath(__file__))
    _template = None
    
    def __init__(self):
        load_dotenv()
        self.search_client_template = SearchClient(
            endpoint=os.getenv("AI_SEARCH_ENDPOINT"),
            index_name=os.getenv("AI_SEARCH_INDEX_NAME_TEMPLATE"),
            credential=AzureKeyCredential(os.getenv("AI_SEARCH_KEY"))
        )
        self.openai_client = AzureOpenAI(
            api_version=os.getenv("OPENAPI_VERSION"),
            azure_endpoint=os.getenv("OPENAPI_ENDPOINT"),
            api_key=os.getenv("OPENAPI_KEY"),
        )

    def search_guidelines(self, query: str, top: int = 3):
        results = self.search_client_template.search(query, query_type=QueryType.SIMPLE, top=top)

        return self._format_search_results_to_string(results)

    def check_guidelines(self, prompt_path: str, src: str, guidelines: str) -> ChatCompletion:
        with open(f"{self._template_base_dir}/{prompt_path}", "r", encoding="UTF-8") as prompt_file:
            self._template = prompt_file.read().format(src=src, guidelines=guidelines)
        
            response = self.openai_client.chat.completions.create(
                model="gpt-4.1-mini",
                messages=[
                    {"role": "system", "content": "You are a Java web project code guideline assistant."},
                    {"role": "user", "content": self._template}
                ],
                temperature=0.3
            )
        
        return response
    
    def _format_search_results_to_string(self, results: list) -> dict:
        output_lines = []
        contents = []

        for i, result in enumerate(results):
            contents.extend(result['content'])
            "\n".join([doc['content'] for doc in results])
            lines = []

            # Base64 디코딩
            encoded_path = result.get('metadata_storage_path', '')
            try:
                decoded_path = base64.b64decode(encoded_path).decode('utf-8')
            except Exception:
                decoded_path = "(디코딩 실패)"

            lines.append(f"📄 Path: {decoded_path}\n")
            lines.append(f"⭐ Score: {result.get('@search.score', 'N/A')}\n")

            # Content
            content = result.get('content', '').strip()
            if content:
                lines.append("📄 Content:\n")
                lines.append(content)
                lines.append("\n")
            else:
                lines.append("📄 Content: (없음)")

            # Keyphrases
            keyphrases = result.get('keyphrases', [])
            if keyphrases:
                preview = ', '.join(keyphrases[:10])
                lines.append(f"🔑 Keyphrases: {preview}{'...' if len(keyphrases) > 10 else ''}")

            output_lines.extend(lines)

        return '\n'.join(output_lines), ''.join(contents)

