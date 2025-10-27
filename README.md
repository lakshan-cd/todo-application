# To-Do Application - Full Stack Project

A full-stack to-do task management application built with React/Next.js frontend and Spring Boot backend, all containerized with Docker.

## 🏗️ Architecture

```
┌─────────────┐     ┌─────────────┐     ┌─────────────┐
│  Frontend   │────▶│   Backend   │────▶│  Database  │
│  Next.js    │     │ Spring Boot │     │   MySQL    │
│  Port:3000  │     │  Port:8081  │     │ Port:3306  │
└─────────────┘     └─────────────┘     └─────────────┘
```

## 📋 Prerequisites

### Required for Docker
- **Docker** (version 20.10 or later)
- **Docker Compose** (version 2.0 or later)
- **Git**

### Required for Running Tests
- **Node.js** (v20.9.0 or later) - For frontend tests
- **npm** or **yarn** - Package manager
- **Maven** (optional) - For backend tests (or use the Maven wrapper `mvnw`)

### Operating System
- **Linux/Windows/Mac** development environment with Bash

## 🚀 Quick Start

### 1. Clone the Repository

```bash
git clone <repository-url>
cd to-do-application
```

### 2. Run the Entire Application

```bash
# Build and start all services (database, backend, and frontend)
docker-compose up --build

# Or run in detached mode (background)
docker-compose up -d --build
```

This will start:
- **MySQL Database** on port 3306
- **Backend API** on port 8081
- **Frontend UI** on port 3000
- **phpMyAdmin** on port 8080 (optional)

### 3. Access the Application

- **Frontend**: http://localhost:3000
- **Backend API**: http://localhost:8081
- **API Health Check**: http://localhost:8081/actuator/health
- **phpMyAdmin**: http://localhost:8080 (username: `todo_user`, password: `todo_password`)

## 🧪 Testing

### Important: Running Tests

**Workflow:**
1. Run `docker-compose up -d` to start all services (database, backend, frontend)
2. Run tests from the host machine using the development tools
3. E2E tests connect to the running Docker services

### Backend Tests

The backend tests need to be run from the host machine since the Docker container is a minimal runtime image without the Maven wrapper.

**Prerequisites:** Java 17 JDK and Maven (or use the Maven wrapper provided)

```bash
# Navigate to backend directory
cd todo-application-backend

# Make Maven wrapper executable (first time only, Linux/Mac)
chmod +x ./mvnw

# Run unit tests using Maven wrapper (recommended)
./mvnw test

# Or if Maven is installed globally
mvn test

# Run tests with coverage
./mvnw test jacoco:report

```

### Frontend Tests

**Note**: Frontend tests must be run on the host machine (not inside Docker container) because they require Node.js and npm.

```bash
# Navigate to frontend directory
cd todo-appilication-frontend

# Install dependencies (only needed once)
npm install

# Run unit tests
npm test

# Run tests in watch mode
npm run test:watch

# Run tests with coverage
npm run test:coverage

# E2E tests (Playwright) - requires backend to be running via docker-compose
# Make sure docker-compose is running: docker-compose up -d
npm run test:e2e

# Interactive E2E testing
npm run test:e2e:ui
```

### Run All Tests

```bash
# From project root - start all services
docker-compose up -d

# Wait for services to be ready
# Frontend will be ready when you can access http://localhost:3000
# Backend will be ready when you can access http://localhost:8081/actuator/health

# Run Backend tests (from host machine)
cd todo-application-backend
./mvnw test

# Run Frontend tests
cd todo-appilication-frontend
npm install
npm run test
npm run test:coverage
npm run test:e2e
```

## 📦 Services Overview

### MySQL Database
- **Container**: `todo-mysql`
- **Port**: 3306
- **Database**: `todo_db`
- **Username**: `todo_user`
- **Password**: `todo_password`
- **Root Password**: `rootpassword`
- **Data Persistence**: Docker volume `mysql_data`

### Spring Boot Backend
- **Container**: `todo-backend`
- **Port**: 8081
- **Technology**: Java 17, Spring Boot 3.x
- **Profile**: docker
- **API Documentation**: http://localhost:8081/swagger-ui.html

### Next.js Frontend
- **Container**: `todo-frontend`
- **Port**: 3000
- **Technology**: React 19, Next.js 16, Redux Toolkit
- **Environment**: Production mode

### phpMyAdmin (Optional)
- **Container**: `todo-phpmyadmin`
- **Port**: 8080
- **Purpose**: Database management UI

## 🔧 Useful Docker Commands

```bash
# Start all services
docker-compose up

# Start in background
docker-compose up -d

# Stop all services
docker-compose down

# Stop and remove volumes (WARNING: deletes all data)
docker-compose down -v

# View logs
docker-compose logs

# View logs for specific service
docker-compose logs frontend
docker-compose logs backend
docker-compose logs mysql

# Rebuild specific service
docker-compose up --build frontend

# Execute commands in container
docker-compose exec backend bash
docker-compose exec frontend sh

# View running containers
docker-compose ps

# Check service health
docker-compose ps
curl http://localhost:8081/actuator/health
```

## 🔍 Troubleshooting

### Port Already in Use

```bash
# Check what's using the port
netstat -an | grep :3000
netstat -an | grep :8081
netstat -an | grep :3306

# Change ports in docker-compose.yml if needed
```

### Application Won't Start

```bash
# Check logs
docker-compose logs frontend
docker-compose logs backend

# Check if all services are running
docker-compose ps

# Restart a specific service
docker-compose restart frontend
```

### Database Connection Issues

```bash
# Check MySQL logs
docker-compose logs mysql

# Connect to MySQL
docker-compose exec mysql mysql -u todo_user -p todo_db
# Password: todo_password
```

### Frontend Build Issues

```bash
# Rebuild frontend
docker-compose up --build frontend

# Remove and rebuild
docker-compose rm -sf frontend
docker-compose up --build frontend
```

## 📝 API Endpoints

### Task Management

- `GET /api/task` - Get all tasks (with pagination)
- `GET /api/task/{id}` - Get task by ID
- `POST /api/task` - Create new task
- `PATCH /api/task/{id}/complete` - Mark task as completed
- `PATCH /api/task/{id}/delete` - Delete task (soft delete)

### Request/Response Examples

#### Create Task
```bash
curl -X POST http://localhost:8081/api/task \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Complete project",
    "description": "Finish the to-do application"
  }'
```

#### Get All Tasks
```bash
curl "http://localhost:8081/api/task?page=0&pageSize=5&sortDirection=desc&statusId=10&isActive=true"
```

## 🗄️ Database Schema

```sql
CREATE TABLE task (
    uid INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    description VARCHAR(255),
    status_id INT DEFAULT 10,
    is_active BOOLEAN DEFAULT true,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Status IDs:
-- 10 = Pending
-- 20 = Completed
```

## 🧹 Clean Up

```bash
# Stop all services and remove containers
docker-compose down

# Remove volumes (deletes all data)
docker-compose down -v

# Remove images
docker-compose down --rmi all

# Clean up unused Docker resources
docker system prune -a
```

## 📚 Project Structure

```
to-do-application/
├── todo-application-backend/    # Spring Boot backend
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/            # Java source code
│   │   │   └── resources/       # Configuration files
│   │   └── test/                # Unit tests
│   ├── pom.xml
│   ├── Dockerfile
│   └── docker-compose.yml
│
├── todo-appilication-frontend/  # Next.js frontend
│   ├── app/                     # Next.js app directory
│   ├── components/              # React components
│   ├── __tests__/               # Unit tests
│   ├── tests/                   # E2E tests
│   ├── package.json
│   └── Dockerfile
│
├── docker-compose.yml            # Root docker-compose
└── README.md                     # This file
```

## 🎯 Features

- ✅ Create, complete, and delete tasks
- ✅ Pagination (shows only latest 5 tasks)
- ✅ Form validation
- ✅ Responsive design
- ✅ Real-time task updates
- ✅ Comprehensive test coverage

## 🧪 Test Coverage

- **Backend**: Unit tests with JUnit and Mockito
- **Frontend**: Component tests with Jest and React Testing Library
- **E2E**: End-to-end tests with Playwright

## 📄 License

This project is developed as a take-home assignment.

## 🤝 Support

For issues or questions, please create an issue in the repository.

---

**Happy Coding! 🚀**
