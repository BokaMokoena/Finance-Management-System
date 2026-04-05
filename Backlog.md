# Product Backlog — ZakaWise

| Story ID | User Story | Priority (MoSCoW) | Effort Estimate | Dependencies |
|----------|------------|------------------|--------------|--------------|
| US-001 | As a user, I want to register using Firebase so that I can securely create an account. | Must-have | 3 | None |
| US-002 | As a user, I want to log in and be authenticated so that I can securely access my dashboard. | Must-have | 4 | US-001 |
| US-003 | As a user, I want to manage my profile so that my financial and user information stays updated. | Should-have | 2 | US-002 |
| US-004 | As a user, I want to add transactions so that I can track my income and expenses. | Must-have | 3 | US-002 |
| US-005 | As a user, I want to edit or delete transactions so that I can correct mistakes. | Must-have | 3 | US-004 |
| US-006 | As a user, I want to filter transactions so that I can quickly find specific records. | Should-have | 2 | US-004 |
| US-007 | As a user, I want to create and manage budgets so that I can control my spending. | Must-have | 4 | US-004 |
| US-008 | As a user, I want to receive budget alerts so that I avoid overspending. | Could-have | 3 | US-007 |
| US-009 | As a user, I want notifications so that I stay informed about updates and analytics. | Could-have | 2 | US-010, US-011 |
| US-010 | As a user, I want to view a financial dashboard so that I can understand my finances visually. | Must-have | 5 | US-004, US-011 |
| US-011 | As an analytics generator, I want to generate dashboards from transactions so that users can see insights. | Must-have | 5 | US-004 |
| US-012 | As a database, I want to securely store user data and update it immediately when updates are made. | Must-have | 3 | US-002 |
