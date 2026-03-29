# ZakaWise: Test Case Development


## Overview

This document defines test cases to validate the functional and non-functional requirements of ZakaWise. All test cases are traceable to requirements defined in SRD.md and are derived from the use case specifications in Use Case Specifications.md.


## Functional Test Cases

| Test Case ID | Requirement ID | Description | Preconditions | Test Steps | Expected Result | Actual Result | Status |
|---|---|---|---|---|---|---|---|
| TC-001 | FR-01 | Register with valid email and password | User is not registered | 1. Click Register. 2. Enter a valid email, password 3. Click Register. | Firebase account created; PostgreSQL `users` record created; user redirected to dashboard. | — | — |
| TC-002 | FR-01 | Register with duplicate email | A ZakaWise account already exists for the email | 1. Click register. 2. Enter the email of an existing account. 3. Click Register. | System displays: "An account with this email already exists. Please log in." No new record created. | — | — |
| TC-003 | FR-02 | Login with correct credentials | User has a registered ZakaWise account | 1. Navigate to login. 2. Enter valid email and password. 3. Click Login. | Firebase returns a valid ID token; user is redirected to the dashboard. | — | — |
| TC-004 | FR-02 | Login with invalid details | User entered invalid details or unauthorised to access the system | 1. Enter login details. 2 Click Login. | System display: Please enter valid Login details. | — | — |
| TC-005 | FR-04 | Add a valid expense transaction | User is logged in; an active Food budget exists for current month | 1. Navigate to Add Transaction. 2. Select type: Expense, category: Food, amount: 150.00, date: today. 3. Click Save. | Transaction saved in PostgreSQL; Food budget `current_spend` increases by 150.00; success toast shown. | — | — |
| TC-006 | FR-04 | Add transaction with missing required field | User is logged in | 1. Navigate to Add Transaction. 2. Leave the Amount field empty. 3. Click Save. | Form displays inline error: "Amount is required." No API call is made. Transaction is not saved. | — | — |
| TC-007 | FR-05 | Delete a transaction | User is logged in; at least one transaction exists | 1. Navigate to Transactions list. 2. Click Delete on a transaction. 3. Click Confirm in the confirmation dialog. | Transaction `is_deleted` flag set to `true` in PostgreSQL; transaction disappears from the list; affected budget is recalculated. | — | — |
| TC-008 | FR-07 | Create a monthly budget | User is logged in; no Food budget exists for current month | 1. Navigate to Budgets > Create Budget. 2. Select category: Food, limit: 2000.00. 3. Click Save. | Budget record created in PostgreSQL for current month; progress bar shows 0% if no Food expenses exist; budget card visible on overview page. | — | — |
| TC-009 | FR-08 | Budget alert triggered at 80% threshold | User is logged in; a Food budget of R 1000.00 exists; current spend is R 750.00 | 1. Add an expense transaction: category Food, amount: R 50.00 (bringing total to R 800.00 = 80%). | An 80% alert badge appears on the Food budget card. | — | — |
| TC-010 | FR-09 | Create a savings goal | User is logged in | 1. Navigate to Savings Goals > New Goal. 2. Enter name: "Emergency Fund", target: R 5000.00, current: R 500.00, target date: 6 months from today. 3. Click Save. | Goal record created in PostgreSQL. | — | — |
| TC-011 | FR-10 | Dashboard loads with correct totals | User is logged in; income transactions total R 8000 and expense transactions total R 3500 for current month | 1. Navigate to Dashboard. | Dashboard shows: Total Income = R 8,000.00; Total Expenses = R 3,500.00; Net Balance = R 4,500.00. All values correct within 3 seconds. | — | — |
| TC-012 | FR-11 | Delete account removes all data | User is logged in with existing transactions, budgets, and goals | 1. Navigate to Settings > Delete Account. 2. Type "DELETE" in confirmation field. 3. Click Confirm. | All PostgreSQL records for this UID deleted from `users`, `transactions`, `budgets`, `savings_goals`. Firebase account deactivated. User redirected to login page. Attempting to log in again fails. | — | — |


## Non-Functional Test Cases

### NFR Test Case 1 — Performance: Dashboard Load Time

| Field | Detail |
|---|---|
| **Test Case ID** | TC-NFR-001 |
| **Requirement ID** | NFR-P01 |
| **Category** | Performance |
| **Description** | Verify that the financial dashboard loads and renders all content within 3 seconds on a standard broadband connection |
| **Preconditions** | User is logged in; the user account has at least 50 transactions, 3 budgets, and 2 savings goals in PostgreSQL; Render web service is active (not cold-started) |
| **Test Steps** | 1. Open Chrome DevTools → Network tab. 2. Navigate to the ZakaWise dashboard. 3. Record the time from navigation initiation to full render (all cards, chart, and recent transactions visible). 4. Repeat 5 times and record each result. |
| **Expected Result** | All 5 dashboard loads complete within 3,000ms. The `GET /api/dashboard` API response time recorded in the Network tab is ≤ 1,500ms in each run. No partial render states are visible to the user. |
| **Actual Result** | — |
| **Status** | — |


### NFR Test Case 2 — Security: Unauthenticated API Access Blocked

| Field | Detail |
|---|---|
| **Test Case ID** | TC-NFR-002 |
| **Requirement ID** | NFR-SE01 |
| **Category** | Security |
| **Description** | Verify that every protected API endpoint rejects requests made without a valid Firebase ID token, returning HTTP 401 with no financial data exposed |
| **Preconditions** | The Spring Boot API is running on Render; a registered user account exists with financial data in PostgreSQL |
| **Test Steps** | 1. Using Postman or curl, send requests to the following endpoints **without** an Authorization header: `GET /api/dashboard`, `GET /api/transactions`, `GET /api/budgets`, `GET /api/goals`. 2. Send the same requests with an **expired** Firebase ID token in the Authorization header. 3. Send a request with a **malformed** token string (e.g., `Bearer abc123`). |
| **Expected Result** | All requests in steps 1, 2, and 3 return **HTTP 401 Unauthorized**. The response body contains an error message (e.g., `{"error": "Unauthorized"}`). **No financial data** (transactions, budget figures, goal details) is present in any 401 response body. No 200 responses are returned for any unauthenticated request. |
| **Actual Result** | — |
| **Status** | — |
