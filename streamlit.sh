#!/bin/bash
PORT=${PORT:-8181}

pip install -r requirements.txt
python -m streamlit run app/app.py --server.port $PORT --server.address 0.0.0.0