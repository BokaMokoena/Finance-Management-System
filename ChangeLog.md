# ZakaWise Changelog

All notable changes to this project are documented here.

---

## Initial Setup
### Added
- Base Spring Boot project structure
- Entity classes:
  - User
  - Transaction
  - Budget
  - SavingsGoal
  - Notification
  - Dashboard
  - Database

---

## Repository Layer
### Added
- Spring Data JPA repositories for all entities
- Custom query methods:
  - findByUserUserId
  - findByUserUserIdAndDeletedFalse

---

## Service Layer Implementation
### Added
- UserService
- TransactionService
- BudgetService
- SavingsGoalService
- NotificationService
- DashboardService

### Improved
- Business logic separated from controllers
- Soft delete implemented for transactions

---

## Creational Design Patterns
### Added
- Singleton Pattern (DatabaseConnection)
- Simple Factory Pattern (NotificationFactory)
- Builder Pattern for all major entities:
  - UserBuilder
  - BudgetBuilder
  - TransactionBuilder
  - SavingsGoalBuilder
  - NotificationBuilder

---

## Testing Phase
### Added
- JUnit 5 unit tests for all services
- Mockito-based tests for isolated service logic
- Fake repository implementations for manual testing approach

## Assignment 12 changes

## Initial Release

### Added
- Core REST API for financial management system
- User management APIs (Create, Read, Update, Delete)
- Transaction management APIs
- Savings goal tracking APIs
- Budget management APIs
- Notification system APIs
- Dashboard summary APIs

### Features
- Spring Boot REST API architecture
- Layered design (Controller → Service → Repository)
- Builder pattern used for:
  - User creation
  - Transaction creation
  - Budget creation
  - Savings goal creation
  - Notification creation

---

## API Improvements & Swagger Integration

### Added
- OpenAPI/Swagger documentation using springdoc-openapi
- Swagger UI endpoint:
  - `/swagger-ui/index.html`
- OpenAPI JSON endpoint:
  - `/v3/api-docs`
- Controller documentation with:
  - `@Operation` summaries
  - `@ApiResponses`
- Improved endpoint naming for REST consistency

---

## Exception Handling Enhancement

### Added
- Global exception handling using `@RestControllerAdvice`
- Standardized error response format:
  - timestamp
  - status
  - error
  - message

### Handled Exceptions
- 404 Not Found (RuntimeException mapping)
- 400 Bad Request (IllegalArgumentException)
- 500 Internal Server Error (Generic Exception handler)

### Improved
- Service layer now throws meaningful exceptions instead of returning null
- Improved error clarity across all modules

---

## Service Layer Refactor & Validation

### Improved
- Services refactored for clean separation of concerns
- Removed HTTP logic from service layer
- Replaced silent failures with explicit exceptions

### Added
- Input validation in services:
  - Amount must be greater than 0
  - Limit must be greater than 0
  - Progress amount must be valid

### Fixed
- Budget update logic improved
- Savings goal progress tracking corrected
- Notification retrieval filtered by user

---

## API Design & REST Improvements

### Improved
- RESTful endpoint structure:
  - `/api/transactions/user/{userId}`
  - `/api/notifications/user/{userId}`
  - `/api/goals/user/{userId}`
- Improved consistency across controllers
- Better separation of user-scoped endpoints

### Fixed
- Incorrect use of request bodies in GET endpoints
- Query parameter misuse replaced with REST path variables

---

### Added
- Complete Swagger/OpenAPI documentation coverage
- CHANGELOG.md added for documentation and version tracking
- Consistent API error handling behavior across all endpoints
- Link to closed issues https://github.com/BokaMokoena/Finance-Management-System/issues?q=is%3Aissue%20state%3Aclosed

