# Branch Protection Rules

## Overview

Branch protection rules were configured for the `main` branch to improve code quality, enforce testing, and prevent unstable code from being merged into production.

---

## Configured Rules

### 1. Pull Request Reviews Required

At least one reviewer must approve changes before they can be merged into the `main` branch.

### Why this matters
- Prevents unreviewed code from entering production
- Encourages collaboration among developers
- Helps detect bugs and design issues early

---

### 2. Status Checks Must Pass

The GitHub Actions CI workflow must complete successfully before pull requests can be merged.

### Why this matters
- Ensures all automated tests pass
- Prevents broken code from being merged
- Maintains project stability

---

### 3. Direct Pushes Disabled

Developers cannot push directly to the `main` branch. All changes must go through pull requests.

### Why this matters
- Protects the production branch
- Enforces review and testing processes
- Reduces accidental mistakes
