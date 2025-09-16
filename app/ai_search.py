import os
from dotenv import load_dotenv

from azure.search.documents import SearchClient
from azure.search.documents.models import QueryType
from azure.core.credentials import AzureKeyCredential
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
        self.search_client_git = SearchClient(
            endpoint=os.getenv("AI_SEARCH_ENDPOINT"),
            index_name=os.getenv("AI_SEARCH_INDEX_NAME_GIT"),
            credential=AzureKeyCredential(os.getenv("AI_SEARCH_KEY"))
        )
        self.openai_client = AzureOpenAI(
            api_version=os.getenv("OPENAPI_VERSION"),
            azure_endpoint=os.getenv("OPENAPI_ENDPOINT"),
            api_key=os.getenv("OPENAPI_KEY"),
        )

    def search_guidelines(self, query: str, top: int = 3) -> str:
        results = self.search_client_template.search(query, query_type=QueryType.SIMPLE, top=top)

        return "\n".join([doc['content'] for doc in results])

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
    
    def search_code(self, query: str, top: int = 3):
        results = self.search_client_git.search(query)
        print([doc for doc in results])
        return [doc for doc in results]