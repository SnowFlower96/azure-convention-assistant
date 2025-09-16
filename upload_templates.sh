#!/bin/bash

# app 폴더 아래 .env 파일 로드
if [ -f "./app/.env" ]; then
  export $(grep -v '^#' ./app/.env | xargs)
else
  echo "app/.env 파일이 존재하지 않습니다."
  exit 1
fi

echo "Creating container..."
az storage container create \
  --account-name "$AZURE_STORAGE_ACCOUNT" \
  --subscription "$SUBSCRIPTION_ID" \
  --name templates \
  --auth-mode key \
  --account-key "$AZURE_STORAGE_KEY" \
  --output none \
  --overwrite

echo "Uploading files..."
az storage blob upload-batch \
  -d templates \
  -s templates \
  --account-name "$AZURE_STORAGE_ACCOUNT" \
  --auth-mode key \
  --account-key "$AZURE_STORAGE_KEY" \
  --output none \
  --overwrite
