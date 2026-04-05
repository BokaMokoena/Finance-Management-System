| Story ID | User Story                                                                                                 | Acceptance Criteria                                      | Priority |
| -------- | ---------------------------------------------------------------------------------------------------------- | -------------------------------------------------------- | -------- |
| US-001   | As a user, I want to register using Firebase so that I can securely create an account.                     | Account created successfully; no password stored locally | High     |
| US-002   | As a user, I want to log in and be authenticated so that I can securely access my dashboard.               | Valid token → access; invalid → 401 error                | High     |
| US-003   | As a user, I want to manage my profile so that my financial and user information stays updated.                     | Changes reflect immediately                              | Medium   |
| US-004   | As a user, I want to add transactions so that I can track my income and expenses.                          | Stored in DB; required fields validated                  | High     |
| US-005   | As a user, I want to edit or delete transactions so that I can correct mistakes.                           | Updates reflect instantly; soft delete applied           | High     |
| US-006   | As a user, I want to filter transactions so that I can quickly find specific records.                      | Results returned ≤2 seconds                              | Medium   |
| US-007   | As a user, I want to create and manage budgets so that I can control my spending.                          | Monthly reset; progress tracked                          | High     |
| US-008   | As a user, I want to receive budget alerts so that I avoid overspending.                                   | Alerts at 80% and 100%                                   | Medium   |
| US-009   | As a user, I want notifications so that I stay informed about updates and analytics.                       | Notifications triggered correctly                        | Low      |
| US-010   | As a user, I want to view a financial dashboard so that I can understand my finances visually.             | Loads ≤3 seconds; accurate data                          | High     |
| US-011   | As an analytics generator, I want to generate dashboards from transactions so that users can see insights. | Charts generated from data                               | High     |
| US-012   | As a database, I want to securely store user data and update it immediately when updates are made. | Store and update transaction on the database                              | High     |
