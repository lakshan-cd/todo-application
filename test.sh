#!/bin/bash

# To-Do Application - Test Script
# This script runs all tests (backend and frontend)

set -e

echo "🧪 Running Tests..."
echo ""

# Start containers if not running
if ! docker-compose ps | grep -q "Up"; then
    echo "Starting containers..."
    docker-compose up -d
    echo "Waiting for services to be ready..."
    sleep 30
fi

echo ""
echo "=========================================="
echo "Running Backend Tests"
echo "=========================================="
echo ""

# Note: Backend tests are run on the host machine, not in the container
cd todo-application-backend

# Check if Maven wrapper exists
if [ -f "./mvnw" ]; then
    ./mvnw test
else
    echo "Maven wrapper not found. Trying with system mvn..."
    mvn test
fi

echo ""
echo "=========================================="
echo "Running Frontend Unit Tests"
echo "=========================================="
echo ""

cd ../todo-appilication-frontend

# Install dependencies if node_modules doesn't exist
if [ ! -d "node_modules" ]; then
    npm install
fi

npm run test:coverage

echo ""
echo "=========================================="
echo "Running Frontend E2E Tests"
echo "=========================================="
echo ""

# E2E tests require the services to be running
npm run test:e2e

echo ""
echo "✅ All tests completed!"
echo ""
