# ZakaWise: Domain

## Overview

This document defines the core domain entities of the ZakaWise Personal Finance Management System, including their attributes, responsibilities, relationships, and business rules.  

---

## Domain Entities

### 1. User

| Property | Detail |
|---|---|
| **Description** | Represents a registered user of the system. This is the central entity that owns all financial data. |
| **Attributes** | `uid: String` (Firebase UID, PK), `email: String`, `name: String`, `occupation: String`, `salary: Double`, `profilePictureUrl: Blob`, `createdAt: LocalDateTime`, `isActive: Boolean` |
| **Methods** | `register()`, `login()`, `logout()`, `updateProfile()`, `deleteAccount()`, `getDashboardSummary()` |
| **Relationships** | Owns many `Transaction`, `Budget`, `SavingsGoal`, `AnalyticsReport`; receives many `Notification` |
| **Business Rules** | Email must be unique. No password stored locally. User must exist in Firebase before being saved in PostgreSQL. Deleting a user deletes all user owned data. |

---

### 2. Transaction

| Property | Detail |
|---|---|
| **Description** | Represents a financial record (income or expense). Core unit of financial tracking. |
| **Attributes** | `transactionId: Long` (PK), `uid: String` (FK), `type: Enum (INCOME/EXPENSE)`, `category: String`, `amount: Double`, `date: LocalDate`, `note: String`, `isDeleted: Boolean`, `createdAt: LocalDateTime` |
| **Methods** | `save()`, `update()`, `softDelete()`, `affectsBudget()` |
| **Relationships** | Belongs to `User`; may affect `Budget`; contributes to `AnalyticsReport` |
| **Business Rules** | Amount > 0. Category required. Date cannot be in future. Uses soft delete (`isDeleted = true`). Only EXPENSE transactions affect budgets. |

---

### 3. Budget

| Property | Detail |
|---|---|
| **Description** | Represents a monthly spending limit per category. Tracks and controls expenses. |
| **Attributes** | `budgetId: Long`, `uid: String`, `category: String`, `monthlyLimit: Double`, `currentSpend: Double`, `month: YearMonth`, `alertStatus: Enum (NONE/WARNING/EXCEEDED)`, `createdAt` |
| **Methods** | `recalculateSpend()`, `checkAlertThreshold()`, `getProgressPercentage()`, `resetForNewMonth()` |
| **Relationships** | Belongs to `User`; updated by `Transaction`; generates `Notification` |
| **Business Rules** | One budget per category per month. Limit > 0. `currentSpend` is calculated, not entered. Alerts at 80% and 100%. Resets monthly. |

---

### 4. SavingsGoal

| Property | Detail |
|---|---|
| **Description** | Represents a financial goal with a target amount and deadline. |
| **Attributes** | `goalId: Long`, `uid: String`, `name: String`, `targetAmount: Double`, `currentAmount: Double`, `targetDate: LocalDate`, `status: Enum (IN_PROGRESS/ACHIEVED/OVERDUE)` |
| **Methods** | `getProgressPercentage()`, `updateCurrentAmount()`, `checkStatus()` |
| **Relationships** | Belongs to `User` |
| **Business Rules** | Target > 0. Date must be future. Status auto-updates based on progress and time. |

---

### 5. Notification

| Property | Detail |
|---|---|
| **Description** | Represents system-generated alerts and updates. |
| **Attributes** | `notificationId: Long`, `uid: String`, `type: Enum`, `message: String`, `isRead: Boolean`, `createdAt`, `deliveryStatus: Enum` |
| **Methods** | `markAsRead()`, `deliver()` |
| **Relationships** | Belongs to `User`; triggered by `Budget` and `AnalyticsReport` |
| **Business Rules** | Cannot be modified after creation. Triggered automatically. Cannot be manually created by user. |

---

### 6. AnalyticsReport (Dashboard)

| Property | Detail |
|---|---|
| **Description** | Represents aggregated financial data used for dashboard visualization. |
| **Attributes** | `reportId: Long`, `uid: String`, `month: YearMonth`, `totalIncome: Double`, `totalExpenses: Double`, `netBalance: Double`, `generatedAt`, `isDownloaded` |
| **Methods** | `generate()`, `calculateNetBalance()`, `exportToPdf()` |
| **Relationships** | Belongs to `User`; aggregates `Transaction`; triggers `Notification` |
| **Business Rules** | Must calculate from non-deleted transactions. Must load within ≤3 seconds. |

---

### 7. UserSession

| Property | Detail |
|---|---|
| **Description** | Represents an authenticated session using Firebase token. |
| **Attributes** | `uid`, `idToken`, `expiryTime`, `isActive` |
| **Methods** | `refreshToken()`, `invalidate()`, `isExpired()` |
| **Relationships** | Linked to `User`; used by Spring Boot API |
| **Business Rules** | Token required for all API calls. Expiry handled by Firebase. Not stored in database. |

---

## Relationship Summary

| Relationship | Type | Description |
|---|---|---|
| User → Transaction | One-to-Many | User owns transactions |
| User → Budget | One-to-Many | User manages budgets |
| User → SavingsGoal | One-to-Many | User sets goals |
| User → Notification | One-to-Many | User receives notifications |
| Transaction → Budget | Optional | Expense updates budget |
| Transaction → AnalyticsReport | Dependency | Used for reporting |
| Budget → Notification | Dependency | Generates alerts |
| AnalyticsReport → Notification | Dependency | New report alert |
| User → UserSession | One-to-One | Authentication session |
