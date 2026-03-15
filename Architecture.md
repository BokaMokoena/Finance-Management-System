# ARCHITECTURE.md — ZakaWise: Personal Finance Management System

---

## Project Overview

**Project Title**: ZakaWise — Personal Finance Management System
**Domain**: FinTech (Financial Technology)
**Problem Statement**: Many people lack financial management discipline, literacy, and skills. Their finances are spread across different accounts making real-time tracking difficult, they overspend without budget awareness, and they make poor financial decisions without guidance. ZakaWise solves these issues in one unified platform where users can plan, track, and improve their finance management.
**Individual Scope**: Single developer, one semester (~14 weeks). Stack: React (frontend), Java Spring Boot (REST API), PostgreSQL (database), Firebase Authentication (identity). Deployed on Render.

---

## C4 Diagram Overview

The C4 model describes software architecture at four increasing levels of detail:

| Level | Focus |
|---|---|
| **Level 1 — System Context** | Who uses ZakaWise and what external systems does it depend on? |
| **Level 2 — Container** | What are the deployable units that make up the system? |
| **Level 3 — Component** | What are the internal components inside the Spring Boot API? |
| **Level 4 — Code** | Class and entity-level detail for the core domain model |

---

## Level 1 — System Context Diagram

> Shows ZakaWise in relation to its user and all system components it depends on.

```mermaid
C4Context
  title System Context Diagram — ZakaWise

  Person(user, "ZakaWise user", "[Person] The user of the website that wants to manage their finances")

  System(zakawise, "ZakaWise", "[Software System] Personal Financial Management System")

  System_Ext(firebase_auth, "Firebase authentication", "[Component: Firebase Service] Identify the user")
  System_Ext(firebase_storage, "Firebase storage", "[Component: Firebase Service] Store user data and files")
  System_Ext(transaction_mgmt, "Transaction management", "[Component: Spring Boot Service] Management user transaction that they manually add")
  System_Ext(budget_mgmt, "Budget management", "[Component: Spring Boot Service] User creates, manage, update, view and delete the budget")
  System_Ext(saving_goals, "Saving goals", "[Component: Spring Boot Service] User create saving goals and system display progress")
  System_Ext(dashboard, "Dashboard and analytics", "[Component: Spring Boot Service] Dashboard displays report of the user")

  Rel(user, zakawise, "Uses")
  Rel(zakawise, firebase_auth, "")
  Rel(zakawise, firebase_storage, "")
  Rel(zakawise, transaction_mgmt, "")
  Rel(zakawise, budget_mgmt, "")
  Rel(zakawise, saving_goals, "")
  Rel(zakawise, dashboard, "")
```

---

## Level 2 — Container Diagram

> Shows all deployable containers that make up ZakaWise.

```mermaid
C4Container
  title Container Diagram — ZakaWise

  Person(user, "ZakaWise user", "[Person] Manages personal finances")

  System_Boundary(zw_boundary, "ZakaWise — Render Hosting") {

    Container(web_app, "React web application", "React 18, TailwindCSS, Recharts, Firebase JS SDK", "[Container: SPA] Provides the transaction UI, budget tracker, savings goals, and financial dashboard. Uses Firebase JS SDK to authenticate users and obtain ID tokens.")

    Container(api_server, "Spring Boot REST API", "Java 21, Spring Boot 3, Spring Security, Spring Data JPA", "[Container: REST API] Exposes all endpoints. Verifies Firebase ID tokens on every request. Implements all business logic for transactions, budgets, goals, and dashboard.")

    Container(db, "PostgreSQL database", "PostgreSQL 15 — Render Managed Postgres", "[Container: Database] Stores all user profiles, transactions, budgets, and savings goals.")
  }

  System_Ext(firebase_auth, "Firebase authentication", "[External: Firebase Service] Identify the user")
  System_Ext(firebase_storage, "Firebase storage", "[External: Firebase Service] Store user data and files")

  Rel(user, web_app, "Opens in browser", "HTTPS")
  Rel(web_app, firebase_auth, "Gets ID token", "Firebase JS SDK")
  Rel(web_app, api_server, "API calls with Bearer token", "HTTPS / JSON")
  Rel(api_server, firebase_auth, "Verifies ID token", "Firebase Admin SDK")
  Rel(api_server, firebase_storage, "Stores files", "Firebase Admin SDK")
  Rel(api_server, db, "Reads and writes data", "JDBC / JPA")
```

---

## Level 3 — Component Diagram

> Zooms into the Spring Boot API and shows its internal components.

```mermaid
C4Component
  title Component Diagram — ZakaWise Spring Boot REST API

  Container_Boundary(api_boundary, "Spring Boot REST API — Java 21 / Spring Boot 3") {

    Component(firebase_filter, "Firebase auth filter", "Spring Security OncePerRequestFilter", "[Component: Security] Intercepts every request, extracts the Bearer token, calls verifyIdToken(), and sets the SecurityContext with the user's Firebase UID.")

    Component(transaction_mgmt, "Transaction management", "Spring Boot @RestController + @Service", "[Component: Spring Boot Service] CRUD endpoints for income and expense transactions. Supports filtering by date, category, and type.")

    Component(budget_mgmt, "Budget management", "Spring Boot @RestController + @Service", "[Component: Spring Boot Service] Creates and tracks monthly budgets per category. Calculates current spend and resets budgets each month.")

    Component(saving_goals, "Saving goals", "Spring Boot @RestController + @Service", "[Component: Spring Boot Service] Creates savings goals, tracks progress percentage, and handles pause, resume, and delete.")

    Component(dashboard, "Dashboard and analytics", "Spring Boot @RestController + @Service", "[Component: Spring Boot Service] Aggregates total income, total expenses, net balance, 5 recent transactions, and 6-month chart data.")

    Component(db, "PostgreSQL database", "PostgreSQL 15 — Render Managed", "[Container: Database] Persists all financial data.")
  }

  System_Ext(firebase_auth, "Firebase authentication", "[External: Firebase Service] Identify the user")
  System_Ext(firebase_storage, "Firebase storage", "[External: Firebase Service] Store user data and files")

  Rel(firebase_filter, firebase_auth, "Verifies token via verifyIdToken()")
  Rel(transaction_mgmt, db, "Reads/writes transactions")
  Rel(budget_mgmt, db, "Reads/writes budgets")
  Rel(budget_mgmt, transaction_mgmt, "Queries monthly spend totals")
  Rel(saving_goals, db, "Reads/writes goals")
  Rel(dashboard, db, "Queries aggregated data")
  Rel(dashboard, firebase_storage, "Stores report files")
```

---

## Level 4 — Code Diagram (JPA Entities & Service Layer)

> Class-level view of the core JPA entities and their service relationships.

```mermaid
classDiagram
  class User {
    +UUID id
    +String firebaseUid
    +String email
    +String displayName
    +String defaultCurrency
    +LocalDateTime createdAt
  }

  class Transaction {
    +UUID id
    +UUID userId
    +TransactionType type
    +BigDecimal amount
    +String category
    +String description
    +LocalDate transactionDate
    +Boolean isDeleted
    +LocalDateTime createdAt
  }

  class Budget {
    +UUID id
    +UUID userId
    +String category
    +BigDecimal monthlyLimit
    +BigDecimal currentSpend
    +String monthYear
    +LocalDateTime createdAt
  }

  class SavingsGoal {
    +UUID id
    +UUID userId
    +String name
    +BigDecimal targetAmount
    +BigDecimal currentAmount
    +LocalDate targetDate
    +GoalStatus status
    +LocalDateTime createdAt
  }

  class TransactionService {
    +createTransaction(uid, dto) Transaction
    +getTransactions(uid, filters) Page~Transaction~
    +updateTransaction(id, dto) Transaction
    +deleteTransaction(id) void
    -notifyBudgetService(uid, transaction) void
  }

  class BudgetService {
    +createBudget(uid, dto) Budget
    +recalculateProgress(uid, category) void
    +resetMonthlyBudgets() void
    -getCurrentMonthSpend(uid, category) BigDecimal
  }

  class SavingsGoalService {
    +createGoal(uid, dto) SavingsGoal
    +updateGoalStatus(id, status) SavingsGoal
    +getProgress(id) GoalProgressDTO
    -calculateEstimatedDays(goal) int
  }

  class DashboardService {
    +getDashboardSummary(uid) DashboardDTO
    -getMonthlyTotals(uid) TotalsDTO
    -getRecentTransactions(uid) List~Transaction~
    -getSixMonthChartData(uid) List~MonthlyBarDTO~
  }

  TransactionService --> BudgetService : triggers recalculation
  BudgetService --> Transaction : queries monthly spend
  BudgetService --> Budget : reads and updates
  DashboardService --> Transaction : queries recent and aggregates
  User "1" --> "0..*" Transaction : owns
  User "1" --> "0..*" Budget : owns
  User "1" --> "0..*" SavingsGoal : owns
```

---

## End-to-End Request Flow — Add a Transaction

> The complete request lifecycle from the React UI through Firebase token verification to PostgreSQL and back.

```mermaid
sequenceDiagram
  actor User
  participant Browser as React Web App
  participant Firebase as Firebase Auth
  participant API as Spring Boot API
  participant Filter as FirebaseAuthFilter
  participant TS as TransactionService
  participant BS as BudgetService
  participant DB as PostgreSQL

  User->>Browser: Fills transaction form, clicks Save
  Browser->>Firebase: getIdToken()
  Firebase-->>Browser: Returns Firebase ID token
  Browser->>API: POST /api/transactions — Authorization: Bearer token
  API->>Filter: Intercepts request
  Filter->>Firebase: verifyIdToken(token)
  Firebase-->>Filter: DecodedToken (uid, email)
  Filter-->>API: SecurityContext set with uid
  API->>TS: createTransaction(uid, dto)
  TS->>DB: transactionRepository.save(transaction)
  DB-->>TS: Persisted Transaction
  TS->>BS: recalculateProgress(uid, category)
  BS->>DB: Sum expense transactions for category and month
  DB-->>BS: Current spend total
  BS->>DB: budgetRepository.save(updatedBudget)
  API-->>Browser: 201 Created
  Browser-->>User: Success toast; dashboard refreshes
```

---

## Deployment Architecture

> ZakaWise is fully deployed on Render.

```mermaid
graph TB
  subgraph Client["Client (Browser)"]
    B[React SPA]
  end

  subgraph Firebase["Google Firebase (Managed)"]
    FA[Firebase Authentication]
    FS[Firebase Storage]
  end

  subgraph Render["Render Cloud Hosting"]
    N[React App — Static Site]
    A[Spring Boot API — Web Service]
    P[(PostgreSQL — Managed DB)]
  end

  B -->|HTTPS| N
  B -->|Firebase JS SDK| FA
  N -->|API calls| A
  A -->|Firebase Admin SDK| FA
  A -->|Firebase Admin SDK| FS
  A -->|JDBC / JPA| P
```

---

## Technology Mapping Summary

| Concern | Solution |
|---|---|
| User Identity & Login | Firebase Authentication (email/password + Google Sign-In) |
| Frontend | React 18 SPA — TailwindCSS, Recharts |
| API Layer | Java 21 + Spring Boot 3 REST Controllers |
| Token Verification | `FirebaseAuthFilter` + Firebase Admin SDK `verifyIdToken()` |
| Data Persistence | Spring Data JPA (Hibernate) + PostgreSQL 15 |
| Budget Progress | `BudgetService` — sums expense transactions per category per month |
| Dashboard Aggregation | `DashboardService` — totals, recent 5 transactions, 6-month chart |
| Deployment | Render (Static Site + Web Service + Managed PostgreSQL) |
