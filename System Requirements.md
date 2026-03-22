# System Requirements Document

## ZakaWise: Personal Finance Management System


## Purpose

This document defines all functional and non-functional requirements for ZakaWise.

## Functional Requirements

### 01 User Registration
The system shall allow users to register via Firebase.

**Acceptance Criteria:**
- Account created successfully
- No passwords stored locally
- Password

### 02 Login & Authentication
The system shall verify Firebase tokens on all requests.

**Acceptance Criteria:**
- Invalid token → 401 error
- Valid token → access granted
- No access for unauthorized users

### 03 Profile Management
- Users shall manage financial profile( occupattion. salary etc)
- Edit and update their personal profile( profile picture, name etc)

**Acceptance Criteria:**
- Updates reflect immediately

### 04 Add Transaction
- Users shall add income/expense manually for now but later might be able to upload the bank statement

**Acceptance Criteria:**
- Stored in DB
- Required fields validated

### 05 Edit/Delete Transaction
- Users shall modify transactions.

**Acceptance Criteria:**
- Updates reflect instantly
- Soft delete applied
- 
### 06 Transaction Filtering
- Users shall filter transactions based on the choice(date, transaction name etc).

**Acceptance Criteria:**
- Results returned ≤2 seconds

### 07 Budget Management
- Users shall create budgets
- Users Users shall create saving goals
- Users Users shall manage goals

**Acceptance Criteria:**
- Monthly reset
- Progress tracking

### 08 Budget Alerts
- System shall alert users that they are overspent.

**Acceptance Criteria:**
- Alerts at 80% and 100%

### 09 Notifications
- Sytem shall notify the user when there is a new analytic report/dashboard
- System shall notify users that there is a financial lesson of the week
- System shall alert the user of the new updates on the system

**Acceptance Criteria:**
- Progress % accurate

### 10 Dashboard
- System shall display financial overview
- System shall have downloadable analytics reports

**Acceptance Criteria:**
- Load ≤3 seconds
- Accurate analytics report

---

## Non-Functional Requirements

### Usability
- Responsive UI
- Clear validation messages
- Clear and accurate dashboard

### Deployability
- Deployable on Render
- Accessible on the internet using any device and operating system

### Maintainability
- Clean architecture
- ≥70% test coverage
- Unit testing for progress or updates

### Scalability
- Support 100+ users
- Well expandable code
- Use OOP for resuable and expandable methods

### Security
- HTTPS will be enforced
- Password will be hashed on the database
- Token verification
- Firebase will generate the token and Springboot will verify it

### Performance
- API ≤2s response
- Dashboard ≤3s load

---

