# Finance-Management-System
ZakaWise is a full-stack personal finance management web application that allow users to take charge of their money. It provides users with the functionaliity of tracking their income and expenses, create and manage budgets, create and monitor saving goals and gives daily financial tips to users. This is secure with Firebase Authentication.

## Kanban Board Customization

The Kanban board for ZakaWise was customized using the Automated Kanban template to support Agile development.

Two additional columns were added:

- **Testing**: This column is used to track tasks that have been implemented but require testing. This ensures quality assurance before completion.
- **Blocked**: This column is used to identify tasks that cannot proceed due to dependencies or technical issues. This helps in quickly identifying bottlenecks.

The board includes user stories and sprint tasks from Assignment 6, allowing full traceability between planning and implementation.

Automation was used to move tasks between "To Do", "In Progress", and "Done", improving workflow efficiency and reducing manual updates.

## Assignment 10

The system follows a layered architecture:
- Controller Layer (REST API endpoints)
- Service Layer (business logic)
- Repository Layer (data access using Spring Data JPA)
- Model Layer (entities representing database tables)
- Creational Patterns Layer (object creation logic/design patterns)

## Design patterns used:
- Singleton pattern was used for DatabaseConnection to ensure only one instance of the database connection exists throughout the application lifecycle. This improves performance and prevents multiple unnecessary connections.
- Factory pattern was used in NotificationFactory and other object creation utilities to centralize object creation logic and reduce duplication in service classes.
- Builder pattern was Used for User, Budget, Transaction, SavingsGoal, and Notification objects because these entities contain multiple fields, some fields are optional, improves readability and maintainability prevents regeneration of constructors

## Technologies used

- Java 17+
- Spring Boot
- Spring Data JPA
- Hibernate
- JUnit 5
- Mockito
- Maven
- 
## Key decisions

- Builder Pattern was chosen for complex entity creation.
- Service layer handles business logic only (no direct repository logic in controllers).
- Repository layer abstracts database operations.
- Mockito was avoided in final testing to demonstrate manual unit testing using JUnit and in-memory fakes.

## Project Documents

- [System Specification](Specification.md)
- [System Architecture](Architecture.md)
- [System Stakeholder](Stakeholders.md)
- [System SystemRequirements](SystemRequirements.md)
- [System Test cases](TestCases.md)
- [System Use case](UseCaseZakaWise.png)
- [System Use case specifications](UseCaseSpecifications.md)
- [System Use Stories](UserStories.md)
- [System Sprint planning](SprintPlanning.md)
- [System Backlog planning](Backlog.md)
- [System Reflection](Reflection.md)
- [System State diagram](StateDiagram.md)
- [System Activity diagram](ActivityDiagram.md)
- [System Traceability](Traceability.md)
- [System Domain model](DomainModel.md)
- [System Class diagram](ClassDiagram.md)

