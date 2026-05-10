## Class diagram for repository interfaces and implementation

```mermaid
classDiagram

%% =======================
%% Repository Interfaces
%% =======================

class UserRepository {
    <<interface>>
    +save(User user)
    +findById(String id) Optional~User~
    +findAll() List~User~
    +delete(String id)
}

class TransactionRepository {
    <<interface>>
    +save(Transaction t)
    +findById(Long id) Optional~Transaction~
    +findAll() List~Transaction~
    +delete(Long id)
    +findByUserUserIdAndDeletedFalse(String userId) List~Transaction~
}

class BudgetRepository {
    <<interface>>
    +save(Budget b)
    +findById(Long id) Optional~Budget~
    +findAll() List~Budget~
    +delete(Long id)
    +findByUser(User user) List~Budget~
    +findByNameAndUser(String name, User user) Optional~Budget~
}

class SavingsGoalRepository {
    <<interface>>
    +save(SavingsGoal g)
    +findById(Long id) Optional~SavingsGoal~
    +findAll() List~SavingsGoal~
    +delete(Long id)
    +findByUserUserId(String userId) List~SavingsGoal~
}

class NotificationRepository {
    <<interface>>
    +save(Notification n)
    +findById(String id) Optional~Notification~
    +findAll() List~Notification~
    +delete(String id)
    +findByUserUserId(String userId) List~Notification~
}

%% =======================
%% In-Memory Implementations
%% =======================

class InMemoryUserRepository
class InMemoryTransactionRepository
class InMemoryBudgetRepository
class InMemorySavingsGoalRepository
class InMemoryNotificationRepository

%% =======================
%% Services (DI Layer)
%% =======================

class UserService {
    -UserRepository repo
    +create()
    +get()
    +update()
    +delete()
}

class TransactionService {
    -TransactionRepository repo
    +create()
    +get()
    +getUserTransactions()
    +updateAmount()
    +delete()
}

class BudgetService {
    -BudgetRepository repo
    -NotificationService notificationService
    +create()
    +getAll()
    +updateLimit()
    +applyExpense()
    +delete()
}

class SavingsGoalService {
    -SavingsGoalRepository repo
}

class NotificationService {
    -NotificationRepository repo
}

%% =======================
%% Relationships
%% =======================

UserRepository <|.. InMemoryUserRepository
TransactionRepository <|.. InMemoryTransactionRepository
BudgetRepository <|.. InMemoryBudgetRepository
SavingsGoalRepository <|.. InMemorySavingsGoalRepository
NotificationRepository <|.. InMemoryNotificationRepository

UserService --> UserRepository
TransactionService --> TransactionRepository
BudgetService --> BudgetRepository
BudgetService --> NotificationService
SavingsGoalService --> SavingsGoalRepository
NotificationService --> NotificationRepository
