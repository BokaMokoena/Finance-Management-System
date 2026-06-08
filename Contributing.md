# Contributing to ZakaWise

Thank you for your interest in contributing to ZakaWise.

## Requirements

Before contributing, ensure you have:

* Java 17 or later
* Maven 3.9+
* Git
* IntelliJ IDEA (recommended)

## Setup Instructions

1. Fork this repository.
2. Clone your fork:

```bash
https://github.com/BokaMokoena/Finance-Management-System.git
```

3. Navigate to the project:

```bash
cd Finance-Management-System/zakawise
```

4. Install dependencies and build:

```bash
mvn clean install
```

5. Run tests:

```bash
mvn test
```

## Coding Standards

* Follow Java naming conventions.
* Write meaningful class and method names.
* Add JUnit tests for new functionality.
* Ensure all tests pass before submitting a Pull Request.
* Keep methods focused on a single responsibility.

## How to Contribute

1. Fork this repository.
2. Pick an issue labeled.
3. Create a feature branch.
4. Write code and tests.
5. Commit your changes.
6. Push to your fork.
7. Submit a Pull Request with a clear description.

## Pull Request Guidelines

* Describe what was changed.
* Reference related issues.
* Ensure GitHub Actions checks pass.
* Keep Pull Requests focused on a single feature or fix.

Thank you for helping improve ZakaWise!

## Branch Naming Convention

When creating branches, use descriptive names:

```text
feature/feature-name
fix/bug-name
docs/documentation-update
```

Examples:

```text
feature/user-authentication
fix/login-validation
docs/update-readme
```

## Contribution Workflow

1. Sync your fork with the latest upstream changes.
2. Create a new branch from `main`.
3. Make focused changes related to a single feature or fix.
4. Commit changes with meaningful commit messages.
5. Push the branch to your fork.
6. Open a Pull Request for review.

## Best Practices

* Keep Pull Requests small and focused.
* Run all tests before submitting changes.
* Update documentation when introducing new features.
* Follow existing project structure and coding standards.
* Respond promptly to code review feedback.