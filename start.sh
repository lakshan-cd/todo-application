#!/bin/bash

# To-Do Application - Start Script
# This script builds and starts all Docker containers

set -e

echo "🚀 Starting To-Do Application..."
echo ""

# Check if Docker is running
if ! docker info > /dev/null 2>&1; then
    echo "❌ Error: Docker is not running!"
    echo "Please start Docker and try again."
    exit 1
fi

# Build and start containers
echo "📦 Building containers..."
docker-compose up --build -d

echo ""
echo "✅ Application started successfully!"
echo ""
echo "📍 Access the application at:"
echo "   - Frontend:   http://localhost:3000"
echo "   - Backend:    http://localhost:8081"
echo "   - phpMyAdmin: http://localhost:8080"
echo ""
echo "📋 Useful commands:"
echo "   - View logs:    docker-compose logs -f"
echo "   - Stop app:     docker-compose down"
echo "   - Restart:      docker-compose restart"
echo ""
