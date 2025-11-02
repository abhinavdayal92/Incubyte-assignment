# Sweet Shop Management System

A full-stack web application for managing a sweet shop inventory, built with **Java Spring Boot** (backend) and **React** (frontend).

## 🎯 Project Overview

This application allows users to:
- Register and login with JWT-based authentication
- Browse available sweets with search and filter functionality
- Purchase sweets (decreasing inventory quantity)
- Admin users can add, update, delete, and restock sweets

## 🛠️ Tech Stack

### Backend
- **Java 17** with **Spring Boot 3.2.0**
- **PostgreSQL** database
- **Spring Security** with JWT authentication
- **Spring Data JPA** for database operations
- **Maven** for dependency management

### Frontend
- **React 18** with hooks
- **React Router** for navigation
- **Axios** for API calls
- **Vite** for build tooling

## 📋 Prerequisites

Before running this application, ensure you have:
- **Java 17** or higher
- **Maven 3.6+**
- **PostgreSQL 12+**
- **Node.js 18+** and **npm**
- **Git**

## 🚀 Setup Instructions

### 1. Clone the Repository

```bash
git clone <your-repository-url>
cd Incubyte
```

### 2. Database Setup

1. Create a PostgreSQL database:
```sql
CREATE DATABASE sweetshop_db;
```

2. Update database credentials in `backend/src/main/resources/application.properties`:
```properties
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### 3. Backend Setup

1. Navigate to the backend directory:
```bash
cd backend
```

2. Build the project:
```bash
mvn clean install
```

3. Run the application:
```bash
mvn spring-boot:run
```

The backend API will be available at `http://localhost:8080`

### 4. Frontend Setup

1. Navigate to the frontend directory (in a new terminal):
```bash
cd frontend
```

2. Install dependencies:
```bash
npm install
```

3. Start the development server:
```bash
npm run dev
```

The frontend will be available at `http://localhost:3000`

## 🧪 Running Tests

### Backend Tests
```bash
cd backend
mvn test
```

### Frontend Tests
```bash
cd frontend
npm test
```

## 📡 API Endpoints

### Authentication
- `POST /api/auth/register` - Register a new user
- `POST /api/auth/login` - Login and get JWT token

### Sweets (Protected)
- `GET /api/sweets` - Get all sweets
- `GET /api/sweets/search?name=&category=&minPrice=&maxPrice=` - Search sweets
- `POST /api/sweets` - Create a new sweet (Admin only)
- `PUT /api/sweets/{id}` - Update a sweet (Admin only)
- `DELETE /api/sweets/{id}` - Delete a sweet (Admin only)

### Inventory (Protected)
- `POST /api/sweets/{id}/purchase` - Purchase a sweet (decreases quantity)
- `POST /api/sweets/{id}/restock` - Restock a sweet (Admin only, increases quantity)

### Authentication Headers
Include the JWT token in the Authorization header:
```
Authorization: Bearer <your-token>
```

## 👤 User Roles

- **USER**: Can view sweets, search, and purchase
- **ADMIN**: Has all USER permissions + can add, update, delete, and restock sweets

**Note**: By default, new users are created with USER role. To create an admin user, you can manually update the database or use a database migration script.

## 📁 Project Structure

```
Incubyte/
├── backend/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/incubyte/sweetshop/
│   │   │   │   ├── config/          # Security and CORS configuration
│   │   │   │   ├── controller/     # REST controllers
│   │   │   │   ├── dto/            # Data Transfer Objects
│   │   │   │   ├── entity/         # JPA entities
│   │   │   │   ├── exception/      # Exception handlers
│   │   │   │   ├── repository/    # Data repositories
│   │   │   │   ├── security/       # JWT and security
│   │   │   │   ├── service/        # Business logic
│   │   │   └── resources/
│   │   │       └── application.properties
│   │   └── test/                   # Test files
│   └── pom.xml
├── frontend/
│   ├── src/
│   │   ├── components/              # React components
│   │   ├── pages/                  # Page components
│   │   ├── utils/                  # Utility functions
│   │   ├── App.jsx
│   │   └── main.jsx
│   ├── package.json
│   └── vite.config.js
└── README.md
```

## 🎨 Features

### User Features
- ✅ User registration and login
- ✅ Browse all available sweets
- ✅ Search sweets by name, category, or price range
- ✅ Purchase sweets (button disabled when out of stock)
- ✅ Modern, responsive UI with gradient design

### Admin Features
- ✅ Add new sweets
- ✅ Update existing sweets
- ✅ Delete sweets
- ✅ Restock sweets (increase quantity)
- ✅ All user features

## 📸 Screenshots

*Note: Add screenshots of your application in action here*

## 🤖 My AI Usage

I used **AI assistance** (Cursor AI/Claude) to help build this project. Here's how:

### AI Tools Used
- **Cursor AI** - For code generation, boilerplate creation, and architectural guidance
- **Claude (via Cursor)** - For generating complex components, test files, and documentation

### How I Used AI

1. **Project Structure Setup**: I asked AI to help generate the complete project structure for both Spring Boot backend and React frontend, including all necessary configuration files.

2. **Backend Development**:
   - Generated entity classes (User, Sweet) with proper JPA annotations
   - Created repository interfaces with custom query methods
   - Generated service layer with business logic
   - Created REST controllers with proper endpoint mappings
   - Generated security configuration and JWT authentication filters

3. **Frontend Development**:
   - Generated React components (Login, Register, Dashboard, SweetCard, SweetModal, SearchBar)
   - Created API utility functions with axios interceptors
   - Generated routing configuration
   - Created CSS styling with modern gradient design

4. **Testing**:
   - Generated unit tests for service layer (SweetServiceTest, AuthServiceTest)
   - Created test structures following TDD principles

5. **Documentation**:
   - Generated comprehensive README with setup instructions
   - Created API endpoint documentation

### My Workflow

1. I started by understanding the requirements from the PDF
2. I asked AI to help generate the project structure and initial boilerplate
3. I manually reviewed and customized all generated code to match the requirements
4. I wrote additional tests and refined the business logic
5. I integrated all components and ensured they work together
6. I manually tested all features and fixed issues

### Reflection on AI Impact

**Positive Impacts**:
- **Speed**: AI helped generate boilerplate code much faster than writing it manually
- **Best Practices**: AI suggestions followed Spring Boot and React best practices
- **Consistency**: Generated code maintained consistent patterns throughout the project
- **Learning**: I learned new patterns and approaches from AI suggestions

**What I Did Manually**:
- All business logic refinement and customization
- Integration of components
- Testing and debugging
- Understanding and modifying generated code to fit specific requirements
- Writing comprehensive documentation sections
- Ensuring code quality and maintainability

**Responsible Usage**:
- I reviewed and understood all AI-generated code
- I customized and refined code to match exact requirements
- I wrote my own tests and verified functionality
- I ensured all code follows SOLID principles and clean coding practices

AI was a powerful **assistant** in this project, helping with repetitive tasks and providing structure, but I maintained full understanding and control of the codebase.

## 📝 Notes

- The application uses JWT tokens stored in localStorage
- Admin role must be manually assigned in the database (update user_roles table)
- The search endpoint supports partial matching for name and category
- Price filters support decimal values

## 🔒 Security Considerations

- Passwords are encrypted using BCrypt
- JWT tokens expire after 24 hours (configurable)
- CORS is configured to allow requests from frontend origin only
- Admin-only endpoints are protected by Spring Security

## 🚀 Future Enhancements

- [ ] Add unit tests with higher coverage
- [ ] Implement pagination for sweets list
- [ ] Add user profile management
- [ ] Implement order history
- [ ] Add image upload for sweets
- [ ] Implement email notifications
- [ ] Add admin dashboard with analytics

## 📄 License

This project is part of an assignment for Incubyte.

## 👨‍💻 Author

Built as part of TDD Kata assignment.

---

**Note**: This project follows Test-Driven Development (TDD) principles. Tests were written to ensure code quality and reliability.

