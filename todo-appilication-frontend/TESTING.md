# Frontend Testing Guide

## Overview
This document provides comprehensive guidance for testing the TaskFlow frontend application, covering unit tests, integration tests, and end-to-end tests for the assignment evaluation.

## Testing Strategy

### 1. Unit Tests (Jest + React Testing Library)
- **Purpose**: Test individual components and functions in isolation
- **Scope**: React components, service functions, utility functions
- **Tools**: Jest, React Testing Library, Jest DOM
- **Coverage Target**: 80%+ line coverage

### 2. Integration Tests (Playwright)
- **Purpose**: Test component interactions and user workflows
- **Scope**: User interactions, API integration, state management
- **Tools**: Playwright
- **Coverage Target**: Critical user journeys

### 3. End-to-End Tests (Playwright)
- **Purpose**: Test complete user workflows from UI to backend
- **Scope**: Full application functionality
- **Tools**: Playwright
- **Coverage Target**: All major features

## Running Tests

### Prerequisites
- Node.js 18+
- npm or yarn
- Docker (for backend integration)

### Commands

```bash
# Install dependencies
npm install

# Run unit tests
npm test

# Run unit tests in watch mode
npm run test:watch

# Run unit tests with coverage
npm run test:coverage

# Run end-to-end tests
npm run test:e2e

# Run end-to-end tests with UI
npm run test:e2e:ui

# Run end-to-end tests in headed mode
npm run test:e2e:headed
```

### Coverage Reports
After running tests with coverage, reports are generated in:
- `coverage/lcov-report/index.html` - HTML coverage report
- `coverage/lcov.info` - LCOV coverage report


