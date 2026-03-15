# 1. Introduction

# 1.1 Project Title
**ZakaWise — Personal Finance Management System**

# 1.2 Domain
**FinTech**
The FinTech is the combination of finance and technology. This domain help users to manage their finances better with the help of technology. FinTech is one of the most fast growing domains in IT, systems like online banking and financial literacy were the results of technology and finance integration. FinTech systems handle sensitive fiancial data therefore they must enforce strong security and privacy protocols, real-time data accuracy and clear reports for users to read their numerical data easily.

# 1.3 Problem Statement
Many people lack financial management discipline, literacy and skills. Their finances are spread across different accounts, savings or banks making it difficult to have real-time data and they also lack budget awareness where people would overspend unnecesary without realizing the impact on their account. They also don't have great saving skills because they can't track their money and they risk being in debt and affecting their credit score and they also make poor financial decisions and they don't have anyone to warn or regularly advice them regarding finance.

ZakaWise aims to slove all these issues in one single platform where users can plan, track, and improve their finance management and literacy.

### 1.4 Individual Scope & Feasibility Justification

This project is scoped for a single developer over one semester (~14 weeks). Feasibility is justified as follows:

 **Authentication**  Firebase Authentication handles all user identity management (email/password and Google Sign-In) with Firebase ID tokens verified server-side via the Firebase Admin SDK for Java — no custom auth system required 
 **Core Features**  Transaction CRUD, budget management, and savings goals are standard data management patterns achievable with Spring Boot REST controllers and Spring Data JPA 
 **Database**  PostgreSQL with a normalised relational schema is sufficient for all financial data requirements 
 **Deployment**  Render hosting

## 2. Stakeholders
Users, developer and tester (lecturer)

## 3. Requirements

### 3.1 User Authentication & Profile Management
-  Users can register with email and password or via Google Sign-In, both handled by Firebase Authentication
-  On login, Firebase issues an ID token; the React frontend sends this token in the `Authorization: Bearer` header with every API request
- The Spring Boot backend verifies the Firebase ID token on every request using the Firebase Admin SDK; no passwords are stored in PostgreSQL
-  Users can create and update a financial profile including: display name, default currency, monthly income estimate, and notification preferences
-  Users can delete their account; all associated financial data is permanently removed from PostgreSQL (GDPR compliance)
### 3.2 Transaction Management
-  Users can manually add a transaction with: amount, type (income/expense), category, description, date, and payment method
-  Users can edit or delete any manually entered transaction
-  Users can search and filter transactions by date range, category, amount range, or type (income/expense)
### 3.3 Budget Management
-  Users can create a monthly budget for a spending category (e.g., Food, Transport, Entertainment) with a maximum spend limit
-  Users can update the budget on how much they spent
-  The system automatically calculates how much of each budget has been spent based on matching categorised transactions
-  Users can view a budget overview page showing all budgets with progress bars and remaining amounts
-  Budgets reset automatically at the start of each calendar month
### 3.4 Savings Goals
-  Users can create a savings goal with: name, target amount, target date
-  The system displays progress as a percentage and estimated days remaining to reach the goal
-  Users can pause, resume, or delete a savings goal
### 3.5 Financial Dashboard & Analytics
- The dashboard displays: total income, total expenses, and net balance for the current month
-  A bar chart shows monthly income vs. expenses for the past 6 months
-  The dashboard displays a list of the 5 most recent transactions
