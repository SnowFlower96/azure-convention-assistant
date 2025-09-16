#!/bin/bash

# Set values for your storage account

echo "Creating container..."
az storage container create \
  --account-name "$azure_storage_account" \
  --subscription "$subscription_id" \
  --name templates \
  --auth-mode key \
  --account-key "$azure_storage_key" \
  --output none \
  --overwrite

echo "Uploading files..."
az storage blob upload-batch \
  -d templates \
  -s templates \
  --account-name "$azure_storage_account" \
  --auth-mode key \
  --account-key "$azure_storage_key" \
  --output none \
  --overwrite
