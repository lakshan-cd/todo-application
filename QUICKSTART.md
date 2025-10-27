# Quick Start Guide

## 🎯 Fastest Way to Run

### Prerequisites
- Docker Desktop installed and running
- Git installed

### Steps

1. **Clone and Navigate**
   ```bash
   git clone <repository-url>
   cd to-do-application
   ```

2. **Run the Application**
   ```bash
   # On Linux/Mac
   chmod +x start.sh
   ./start.sh
   
   # Or manually
   docker-compose up --build -d
   ```

3. **Access the Application**
   - Frontend UI: http://localhost:3000
   - Backend API: http://localhost:8081
   - Database Admin: http://localhost:8080

## 🛑 Stop the Application

```bash
# On Linux/Mac
chmod +x stop.sh
./stop.sh

# Or manually
docker-compose down
```

## 🧪 Run Tests

```bash
# On Linux/Mac
chmod +x test.sh
./test.sh

# Or manually
cd todo-appilication-frontend
npm install
npm test
npm run test:e2e

cd ../todo-application-backend
docker-compose exec backend ./mvnw test
```

## 📝 Common Commands

```bash
# View logs
docker-compose logs -f

# View specific service logs
docker-compose logs -f frontend
docker-compose logs -f backend

# Restart a service
docker-compose restart frontend

# Rebuild and restart
docker-compose up --build frontend

# Check status
docker-compose ps
```

## 🐛 Troubleshooting

**Problem**: Port already in use
```bash
# Find what's using the port
lsof -i :3000
lsof -i :8081

# Stop the process or change port in docker-compose.yml
```

**Problem**: Containers won't start
```bash
# Check logs
docker-compose logs

# Rebuild from scratch
docker-compose down -v
docker-compose up --build
```

**Problem**: Frontend can't connect to backend
```bash
# Verify backend is running
curl http://localhost:8081/actuator/health

# Check network
docker-compose ps
```

## 📚 More Information

For detailed documentation, see [README.md](README.md)

---
**That's it! You're ready to go! 🚀**
