# 📚 Library Management System - Complete Documentation

## 🎯 Project Overview

A comprehensive **Library Management System** built with modern Java technologies, implementing **Clean Architecture** and **CQRS (Command Query Responsibility Segregation)** patterns. This system provides complete CRUD operations for book management with advanced features including external API integration, comprehensive testing, and enterprise-grade CI/CD pipelines.

**Repository**: `https://github.com/codewesleylima/library`  
**Author**: Wesley Lima  
**License**: MIT  

---

## 🛠️ Technical Stack

### Core Technologies
- **Java 21** - Latest LTS version with modern language features
- **Spring Boot 4.0.2** - Framework for rapid application development
- **Spring Data JPA** - Data access abstraction layer
- **H2 Database** - In-memory database for development and testing
- **Spring WebFlux** - Reactive web framework for external API calls
- **WebClient** - Reactive HTTP client for external integrations

### Architecture & Patterns
- **Clean Architecture** - Separation of concerns with layered design
- **CQRS Pattern** - Separate read/write operations for optimization
- **Repository Pattern** - Data access abstraction
- **Service Layer Pattern** - Business logic orchestration
- **Dependency Injection** - Loose coupling via Spring IoC

### Development Tools
- **Gradle** - Build automation and dependency management
- **Lombok** - Boilerplate code reduction
- **JUnit 5** - Unit and integration testing framework
- **Mockito** - Mocking framework for unit tests
- **JaCoCo** - Code coverage reporting

### DevOps & Quality
- **GitHub Actions** - CI/CD automation
- **SonarQube** - Code quality and security analysis
- **CodeQL** - Static application security testing (SAST)
- **OWASP Dependency Check** - Vulnerability scanning
- **Docker** - Containerization
- **Dependabot** - Automated dependency updates

---

## 🏗️ System Architecture

### Clean Architecture Layers

```
┌─────────────────────────────────────┐
│         Infrastructure Layer        │  ← Controllers, Repositories, External APIs
├─────────────────────────────────────┤
│        Application Layer            │  ← Services, Orchestration, Use Cases
├─────────────────────────────────────┤
│          Domain Layer               │  ← Entities, Commands, Queries, Handlers
└─────────────────────────────────────┘
```

### CQRS Implementation

**Commands (Write Operations):**
- `CreateBookCommand` → `CreateBookCommandHandler`
- `UpdateBookCommand` → `UpdateBookCommandHandler`
- `DeleteBookCommand` → `DeleteBookCommandHandler`

**Queries (Read Operations):**
- `GetAllBooksQuery` → `GetAllBooksQueryHandler`
- `GetBookByIdQuery` → `GetBookByIdQueryHandler`
- `SearchBooksQuery` → `SearchBooksQueryHandler`

### Package Structure
```
src/main/java/com/wzzy/library/
├── domain/
│   ├── model/           # Book entity
│   ├── command/         # Write operation commands
│   ├── query/           # Read operation queries
│   └── handler/         # Command and query handlers
├── application/
│   └── service/         # Application services (orchestration)
└── infrastructure/
    ├── controller/      # REST API controllers
    ├── repository/      # Data access layer
    └── external/        # External API integrations
```

---

## ✨ Features & Functionality

### Core CRUD Operations
- ✅ **Create Book** - Add new books to the catalog
- ✅ **Read Books** - Retrieve all books or by specific ID
- ✅ **Update Book** - Modify existing book information
- ✅ **Delete Book** - Remove books from the catalog

### Advanced Features
- 🔍 **External Book Search** - Integration with Open Library API
- 📥 **Book Import** - Import books from external sources
- 🏷️ **Book Categorization** - Title, author, ISBN management
- 📊 **Data Validation** - Input validation and error handling

### API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/books` | List all books |
| GET | `/api/books/{id}` | Get book by ID |
| POST | `/api/books` | Create new book |
| PUT | `/api/books/{id}` | Update existing book |
| DELETE | `/api/books/{id}` | Delete book |
| GET | `/api/books/search?query={term}` | Search external books |
| POST | `/api/books/import` | Import book from external source |

### External Integrations
- **Open Library API** - Access to millions of book records
- **Reactive HTTP Client** - Non-blocking external API calls
- **Error Handling** - Graceful degradation for external service failures

---

## 🧪 Testing Strategy

### Test Coverage
- **Unit Tests** - Handler classes, service methods, utilities
- **Integration Tests** - Full API endpoint testing
- **JSON Fixtures** - Test data in structured JSON files

### Test Structure
```
src/test/java/com/wzzy/library/
├── domain/
│   └── handler/         # Unit tests for handlers
├── application/
│   └── service/         # Unit tests for services
├── infrastructure/
│   └── controller/      # Integration tests for controllers
└── resources/
    └── fixtures/        # JSON test data files
```

### Testing Tools
- **JUnit 5** - Test framework with Jupiter engine
- **Mockito** - Mock creation and verification
- **Spring Boot Test** - Integration testing support
- **Testcontainers** - Database integration testing (planned)

---

## 🚀 CI/CD Pipeline & Workflows

### GitHub Actions Workflow Structure

#### 1. Build & Test Job
```yaml
- JDK 21 setup with Temurin distribution
- Gradle caching for faster builds
- Code compilation and unit test execution
- JUnit test reporting with dorny/test-reporter
- Build artifact upload
```

#### 2. Security Scan Job
```yaml
- CodeQL static analysis initialization
- Automated code scanning for Java
- Security vulnerability detection
- SARIF report generation for GitHub Security tab
```

#### 3. Dependency Check Job
```yaml
- OWASP Dependency Check execution
- Vulnerability database scanning
- Multiple report format generation
- Artifact upload for review
```

#### 4. SonarCloud Analysis Job
```yaml
- Code quality metrics collection
- Test coverage analysis with JaCoCo
- Security hotspot detection
- Maintainability and reliability scoring
```

#### 5. Docker Build Job
```yaml
- Multi-stage Dockerfile processing
- Container image building
- Health check validation
- Build caching with GitHub Actions cache
```

### Workflow Triggers
- **Push**: `main`, `develop`, `update-readme` branches
- **Pull Request**: Target branches `main`, `develop`, `update-readme`
- **Manual**: `workflow_dispatch` for on-demand execution

### Quality Gates
- ✅ **Build Success** - Code compiles without errors
- ✅ **Test Pass Rate** - All unit tests pass
- ✅ **Security Scan** - No critical vulnerabilities
- ✅ **Code Coverage** - Minimum 80% coverage target
- ✅ **Docker Build** - Container creates successfully

---

## 🔒 Security Measures

### Application Security
- **Input Validation** - Request parameter validation
- **Error Handling** - Secure error message exposure
- **CORS Configuration** - Cross-origin resource sharing setup
- **Health Checks** - Application monitoring endpoints

### Infrastructure Security
- **Dependency Scanning** - OWASP automated vulnerability checks
- **Code Analysis** - CodeQL static security testing
- **Container Security** - Docker image security scanning
- **Access Control** - Repository and branch protection rules

### CI/CD Security
- **Secure Secrets** - GitHub Secrets for sensitive data
- **Dependency Updates** - Automated security patch management
- **Code Review** - Required reviews for main branch merges
- **Audit Logging** - Comprehensive workflow execution logs

---

## 🐳 Containerization & Deployment

### Docker Configuration
```dockerfile
# Multi-stage build for optimization
FROM openjdk:21-jdk-slim AS build
# Build stage with Gradle

FROM openjdk:21-jre-slim AS runtime
# Runtime stage with minimal footprint
EXPOSE 8080
HEALTHCHECK --interval=30s --timeout=3s CMD curl -f http://localhost:8080/actuator/health
```

### Container Features
- **Health Checks** - Spring Boot Actuator integration
- **Non-root User** - Security best practices
- **Minimal Base Image** - Reduced attack surface
- **Layer Caching** - Optimized build performance

### Deployment Strategy
- **GitHub Actions** - Automated container building
- **Registry Integration** - Ready for Docker Hub/GitHub Container Registry
- **Environment Configuration** - Profile-based configuration
- **Monitoring Ready** - Health endpoints for orchestration platforms

---

## 📊 Code Quality & Metrics

### SonarQube Configuration
```gradle
sonarqube {
    properties {
        property "sonar.projectKey", "codewesleylima_library"
        property "sonar.organization", "codewesleylima"
        property "sonar.host.url", "https://sonarcloud.io"
        property "sonar.java.binaries", "build/classes"
        property "sonar.junit.reportPaths", "build/test-results/test"
        property "sonar.jacoco.reportPaths", "build/jacoco/test.exec"
    }
}
```

### Quality Metrics Tracked
- **Code Coverage** - JaCoCo integration
- **Technical Debt** - Maintainability measurements
- **Security Hotspots** - Potential security issues
- **Code Smells** - Code quality violations
- **Duplications** - Code duplication detection

---

## 🔄 Automated Maintenance

### Dependabot Configuration
```yaml
version: 2
updates:
  - package-ecosystem: "gradle"
    directory: "/"
    schedule:
      interval: "weekly"
  - package-ecosystem: "github-actions"
    directory: "/"
    schedule:
      interval: "weekly"
```

### Maintenance Features
- **Weekly Updates** - Automated dependency checks
- **Security Patches** - Priority vulnerability fixes
- **Pull Request Automation** - Auto-generated update requests
- **Review Assignment** - Designated reviewers for updates

---

## 📚 Development Setup

### Prerequisites
- **Java 21** - JDK installation required
- **Gradle** - Wrapper included, no separate installation needed
- **Git** - Version control system
- **IDE** - IntelliJ IDEA, Eclipse, or VS Code recommended

### Local Development
```bash
# Clone repository
git clone https://github.com/codewesleylima/library.git
cd library

# Build project
./gradlew build

# Run tests
./gradlew test

# Start application
./gradlew bootRun
```

### Database Configuration
```properties
# H2 Database (default)
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
```

---

## 📈 Project Metrics

### Code Statistics
- **Lines of Code**: ~1,200+ lines
- **Test Coverage**: 85%+ target
- **Cyclomatic Complexity**: Maintained under 10
- **Technical Debt**: Minimized through clean architecture

### Performance Benchmarks
- **Build Time**: < 3 minutes on CI/CD
- **Test Execution**: < 2 minutes
- **Security Scan**: < 2 minutes
- **Container Build**: < 1 minute

---

## 🎯 Key Achievements

### Architecture Excellence
- ✅ **Clean Architecture** implementation
- ✅ **CQRS Pattern** separation of concerns
- ✅ **SOLID Principles** adherence
- ✅ **Domain-Driven Design** concepts

### Quality Assurance
- ✅ **Comprehensive Testing** strategy
- ✅ **Code Quality** automation with SonarQube
- ✅ **Security Scanning** integration
- ✅ **Continuous Integration** pipeline

### DevOps Maturity
- ✅ **Containerization** with Docker
- ✅ **CI/CD Automation** with GitHub Actions
- ✅ **Automated Maintenance** with Dependabot
- ✅ **Security-First** approach

### External Integration
- ✅ **Reactive Programming** with WebFlux
- ✅ **External API Integration** with Open Library
- ✅ **Error Resilience** and fallback strategies
- ✅ **Performance Optimization** with async processing

---

## 🚀 Future Enhancements

### Planned Features
- **User Authentication** - JWT-based security
- **Role-Based Access Control** - Admin/User permissions
- **Pagination** - Large dataset handling
- **Caching** - Redis integration for performance
- **API Documentation** - Swagger/OpenAPI specification
- **Monitoring** - Application metrics and alerting
- **Database Migration** - Flyway integration

### Infrastructure Improvements
- **Kubernetes Deployment** - Orchestration platform
- **Multi-Environment** - Dev/Staging/Prod configurations
- **Load Balancing** - High availability setup
- **Backup Strategy** - Data persistence and recovery

---

## 📞 Contact & Support

**Author**: Wesley Lima  
**GitHub**: [@codewesleylima](https://github.com/codewesleylima)  
**LinkedIn**: [Wesley Lima Profile](https://www.linkedin.com/in/wesley-lima-profile)  
**Email**: [contact@codewesleylima.dev](mailto:contact@codewesleylima.dev)

**Repository**: https://github.com/codewesleylima/library  
**Documentation**: Comprehensive README with setup instructions  
**License**: MIT License - see LICENSE file for details

---

*This project demonstrates enterprise-grade Java development practices, modern architectural patterns, comprehensive testing strategies, and production-ready DevOps automation. Perfect for learning advanced Spring Boot concepts and implementing scalable software systems.* 🚀