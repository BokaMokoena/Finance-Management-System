# ZakaWise: Activity Diagrams

## Overview

This document defines activity diagrams for 8 workflows in the ZakaWise Personal Finance Management System. Each diagram uses UML activity diagram conventions (start/end nodes, actions, decisions, parallel actions, and swimlanes) expressed in Mermaid flowchart syntax.

## 1. User registration
```mermaid
flowchart TD
A[Start] --> B[Enter Email/Password]
B --> C[Firebase Authentication]
C --> D{Valid?}
D -->|Yes| E[Generate Token]
E --> F[Store User in DB]
F --> G[Access system]
D -->|No| H[Show Error e.g email already exist, please login.]
H --> B
G --> I[End]
```
## Explaination
- User authentication ensures that a user enters a valid email meeting the user's security requirement.
- User authentication also ensures no one accesses someone else's account improving the system security and reliability

## 2. Login
```mermaid
flowchart TD
A[Start] --> B[Enter Credentials]
B --> C[Firebase Auth]
C --> D{Token Valid?}
D -->|Yes| E[Access System]
D -->|No| F[401 Error]
E --> G[End]
F --> G
```
## Explaination
- User authentication ensures that a user enters a valid email and password meeting the user's security requirement.
- User authentication also ensures only authenticated and authorized users access the system and their accounts.

## 3. Profile management
```mermaid
flowchart TD
A[Start] --> B[Open Profile]
B --> C[Edit Details]
C --> D[Submit]
D --> E[Update DB]
E --> F[Reflect Changes]
F --> G[End]
```
## Explaination
- Parallel actions ensure profile updates in real time, ensuring the user's scalability requirement
- Proper communication with database ensure concurrent updates on the system and database ensuring reliability of the system.

## 4. Add transaction
```mermaid
flowchart TD
A[Start] --> B[Enter Transaction]
B --> C[Validate Fields]
C --> D{Are all fields Valid?}
D -->|Yes| E[Save to DB]
D -->|No| F[Show Error e.g All fields must be entered]
E --> G[Update the system dashboard]
G --> H[End]
```
## Explaination
- Field validation ensures accurate data is entered by the user which meets the integrity of the analytics report requirement
- Parallel actions ensure the system dashboard is updated in real time, ensuring the user's scalability requirement.

## 5. Edit & Delete transaction
```mermaid
flowchart TD
A[Start] --> B[Select Transaction]
B --> C{Edit or Delete?}
C -->|Edit| D[Update Data]
C -->|Delete| E[Soft Delete]
D --> F[Save]
E --> F
F --> G[Update DB]
G --> H[End]
```
## Explaination
- Data modification enhance data integrity in the system and reports
- Soft delete improves data reliability by storing the deleted data for a period of time on the backend just incase the user needs that data or transaction again.

# 6. Budget
```mermaid
flowchart TD
A[Start] --> B[Create Budget]
B --> C[Set Limit]
C --> D[Track Usage]
D --> E{Limit Reached?}
E -->|80%| F[Send Warning]
E -->|100%| G[Send Alert]
D --> H[End]
```
## Explaination
- System alerts in real time ensures reliability on the requirements to realy on the system to alert the user when exceeding the budget

## 7. Analytics report
```mermaid
flowchart TD
A[Start] --> B[Fetch Transactions]
B --> C[Generate Analytics]
C --> D[Create Charts]
D --> E[Display Dashboard]
E --> F[End]
```
## Explaination
- AI-powered report generation will ensure the accurate and data oriented analytics report

## 8. Notification
```mermaid
flowchart TD
A[Start] --> B[Trigger Event]
B --> C[Generate Notification]
C --> D[Send to User]
D --> E{User Opens?}
E -->|Yes| F[Mark as Read]
E -->|No| G[Remain Unread]
F --> H[End]
G --> H
```
## Explaination
- Real time notofications will ensure reliability of the system by alerting the user whenever there's a change in data or on the system

