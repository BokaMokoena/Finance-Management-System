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
