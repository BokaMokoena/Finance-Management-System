# ZakaWise: Class Diagram

## Mermaid.js Diagram

```mermaid
classDiagram

class User {
    -userId: String
    -name: String
    -email: String
    -salary: Double
    -occupation: String
    +register()
    +login()
    +updateProfile()
}

class Transaction {
    -transactionId: String
    -amount: Double
    -category: String
    -type: String
    -date: Date
    -isDeleted: Boolean
    +create()
    +update()
    +delete()
}

class Budget {
    -budgetId: String
    -category: String
    -limit: Double
    -currentSpend: Double
    -month: String
    +createBudget()
    +updateBudget()
    +trackUsage()
}

class SavingsGoal {
    -goalId: String
    -name: String
    -targetAmount: Double
    -currentAmount: Double
    -targetDate: Date
    +createGoal()
    +updateProgress()
}

class Notification {
    -notificationId: String
    -message: String
    -status: String
    -date: Date
    +send()
    +markAsRead()
}

class Dashboard {
    -totalIncome: Double
    -totalExpense: Double
    -balance: Double
    +generateReport()
}

class Database {
    +save()
    +update()
    +delete()
}

User "1" -- "0..*" Transaction : owns
User "1" -- "0..*" Budget : manages
User "1" -- "0..*" SavingsGoal : sets
User "1" -- "0..*" Notification : receives

Transaction "0..*" -- "0..1" Budget : affects

Dashboard "1" -- "0..*" Transaction : aggregates
Dashboard "1" -- "0..*" Budget : includes

Database "1" -- "0..*" User : stores
Database "1" -- "0..*" Transaction : stores
Database "1" -- "0..*" Budget : stores
Database "1" -- "0..*" SavingsGoal : stores
Database "1" -- "0..*" Notification : stores
