#!/bin/bash

# To-Do Application - Stop Script
# This script stops all Docker containers

set -e

echo "🛑 Stopping To-Do Application..."
echo ""

# Stop containers
docker-compose down

echo ""
echo "✅ Application stopped successfully!"
echo ""

# Ask if user wants to remove volumes
read -p "Do you want to remove data volumes? (y/N) " -n 1 -r
echo
if [[ $REPLY =~ ^[Yy]$ ]]; then
    echo "🗑️  Removing volumes..."
    docker-compose down -v
    echo "✅ All data has been removed."
fi
