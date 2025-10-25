# Docker Setup for Todo Application Backend

This guide explains how to run the Todo Application Backend using Docker with MySQL.

## Prerequisites

- Docker Desktop installed and running
- Docker Compose (usually comes with Docker Desktop)

## Quick Start

### 1. Build and Run with Docker Compose

```bash
# Build and start all services
docker-compose up --build

# Run in detached mode (background)
docker-compose up -d --build
```

### 2. Access the Application

- **Spring Boot Application**: http://localhost:8081
- **API Documentation (Swagger)**: http://localhost:8081/swagger-ui.html
- **Health Check**: http://localhost:8081/actuator/health
- **phpMyAdmin**: http://localhost:8080 (for database management)

### 3. Database Connection Details

- **Host**: localhost (from host machine) or mysql (from within Docker network)
- **Port**: 3306
- **Database**: todo_db
- **Username**: todo_user
- **Password**: todo_password
- **Root Password**: rootpassword

## Services Overview

### MySQL Database
- **Image**: mysql:8.0
- **Port**: 3306
- **Data Persistence**: Data is stored in a Docker volume `mysql_data`
- **Initialization**: Database and user are created automatically

### Spring Boot Application
- **Port**: 8081
- **Profile**: docker (uses application-docker.properties)
- **Dependencies**: Waits for MySQL to be healthy before starting

### phpMyAdmin (Optional)
- **Port**: 8080
- **Purpose**: Web-based MySQL administration
- **Login**: Use todo_user/todo_password or root/rootpassword

## Useful Commands

### Docker Compose Commands

```bash
# Start services
docker-compose up

# Start in background
docker-compose up -d

# Stop services
docker-compose down

# Stop and remove volumes (WARNING: This will delete all data)
docker-compose down -v

# View logs
docker-compose logs

# View logs for specific service
docker-compose logs todo-app
docker-compose logs mysql

# Rebuild and restart
docker-compose up --build --force-recreate

# Scale services (if needed)
docker-compose up --scale todo-app=2
```

### Docker Commands

```bash
# Build the application image
docker build -t todo-application-backend .

# Run the application container
docker run -p 8081:8081 todo-application-backend

# View running containers
docker ps

# View container logs
docker logs todo-application-backend

# Execute commands in running container
docker exec -it todo-application-backend bash
```

### Database Commands

```bash
# Connect to MySQL container
docker exec -it todo-mysql mysql -u todo_user -p todo_db

# Connect as root
docker exec -it todo-mysql mysql -u root -p

# Backup database
docker exec todo-mysql mysqldump -u todo_user -p todo_db > backup.sql

# Restore database
docker exec -i todo-mysql mysql -u todo_user -p todo_db < backup.sql
```

## Configuration

### Environment Variables

The application uses the following environment variables (set in docker-compose.yml):

- `SPRING_PROFILES_ACTIVE=docker`
- `SPRING_DATASOURCE_URL=jdbc:mysql://mysql:3306/todo_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC`
- `SPRING_DATASOURCE_USERNAME=todo_user`
- `SPRING_DATASOURCE_PASSWORD=todo_password`

### Application Properties

- **Local Development**: `application.properties`
- **Docker**: `application-docker.properties`

## Troubleshooting

### Common Issues

1. **Port Already in Use**
   ```bash
   # Check what's using the port
   netstat -an | findstr :8081
   netstat -an | findstr :3306
   
   # Kill the process or change ports in docker-compose.yml
   ```

2. **Database Connection Issues**
   ```bash
   # Check if MySQL is running
   docker-compose logs mysql
   
   # Check network connectivity
   docker-compose exec todo-app ping mysql
   ```

3. **Application Won't Start**
   ```bash
   # Check application logs
   docker-compose logs todo-app
   
   # Check if all dependencies are met
   docker-compose ps
   ```

4. **Permission Issues**
   ```bash
   # Fix file permissions
   sudo chown -R $USER:$USER .
   ```

### Health Checks

```bash
# Check application health
curl http://localhost:8081/actuator/health

# Check MySQL connection
docker-compose exec mysql mysql -u todo_user -p -e "SELECT 1"
```

## Development Workflow

### Making Changes

1. **Code Changes**: Make your changes to the source code
2. **Rebuild**: `docker-compose up --build`
3. **Test**: Access the application and test your changes

### Database Changes

1. **Schema Changes**: Modify your JPA entities
2. **Restart**: `docker-compose restart todo-app`
3. **Verify**: Check the database structure in phpMyAdmin

### Logs and Debugging

```bash
# Follow logs in real-time
docker-compose logs -f todo-app

# Check specific log levels
# Modify logging.level in application-docker.properties
```

## Production Considerations

For production deployment, consider:

1. **Security**: Change default passwords
2. **SSL**: Enable SSL for database connections
3. **Backup**: Set up automated database backups
4. **Monitoring**: Add monitoring and alerting
5. **Scaling**: Use Docker Swarm or Kubernetes for scaling

## Clean Up

```bash
# Remove everything (including volumes)
docker-compose down -v

# Remove images
docker rmi todo-application-backend

# Clean up unused resources
docker system prune -a
```
