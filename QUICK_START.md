# Quick Start Guide

## Prerequisites Check
1. **PostgreSQL**: Make sure PostgreSQL is running
2. **Java 17**: Verify with `java -version`
3. **Maven**: Verify with `mvn -version`
4. **Node.js**: Verify with `node -version`

## Step-by-Step Setup

### 1. Create Database

Open PowerShell and run:
```powershell
psql -U postgres
```

Then in the PostgreSQL prompt:
```sql
CREATE DATABASE sweetshop_db;
\q
```

**OR** if you have a GUI tool like pgAdmin, create the database manually.

### 2. Update Database Credentials (if needed)

Edit `backend/src/main/resources/application.properties`:
- Update `spring.datasource.username` if your PostgreSQL username is not `postgres`
- Update `spring.datasource.password` if your PostgreSQL password is not `postgres`

### 3. Start Backend

Open a **new PowerShell terminal** and run:
```powershell
cd backend
mvn clean install -DskipTests
mvn spring-boot:run
```

Wait for the message: `Started SweetShopApplication` 

The backend will be available at: `http://localhost:8080`

### 4. Start Frontend

Open **another new PowerShell terminal** and run:
```powershell
cd frontend
npm install
npm run dev
```

The frontend will be available at: `http://localhost:3000`

## Alternative: Use the Scripts

You can also use the provided PowerShell scripts:

**Terminal 1** (Backend):
```powershell
.\start-backend.ps1
```

**Terminal 2** (Frontend):
```powershell
.\start-frontend.ps1
```

## Access the Application

1. Open your browser and go to: `http://localhost:3000`
2. Register a new user account
3. Login and start using the Sweet Shop Management System!

## Creating an Admin User

After registering a user, you can make them an admin by running this SQL:

```sql
-- Find the user_id first
SELECT id, username FROM users;

-- Then add admin role (replace 1 with your user_id)
INSERT INTO user_roles (user_id, role) VALUES (1, 'ADMIN');
```

## Troubleshooting

### Backend won't start:
- Check PostgreSQL is running
- Verify database credentials in `application.properties`
- Make sure port 8080 is not in use

### Frontend won't start:
- Make sure Node.js is installed
- Try deleting `node_modules` and running `npm install` again
- Check if port 3000 is not in use

### Database connection issues:
- Ensure PostgreSQL service is running
- Verify database name is `sweetshop_db`
- Check username/password in `application.properties`

