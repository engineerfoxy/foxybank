# PROJECT IS UNDER DEVELOPMENT

# Foxy Core Banking

A modular, SQLite‑based core banking system built with Java 21. Provides customer management, account handling, deposits, and withdrawals through a clean CLI interface with a layered architecture.

## Features

- **Customer Management** — Create, list, and remove customers
- **Account Management** — Open and close bank accounts
- **Transactions** — Deposit and withdraw funds
- **Data Persistence** — SQLite database with JDBC

## Architecture

```
┌─────────────────────────────────────────────┐
│                  Main.java                   │  CLI Menu
├─────────────────────────────────────────────┤
│             bank_service.java                │  Facade / Orchestrator
├──────────────────┬──────────────────────────┤
│ Customer_Services│   Account_Services       │  Business Logic
├──────────────────┴──────────────────────────┤
│           DatabaseManager.java               │  DB Connection (Singleton)
├─────────────────────────────────────────────┤
│            SQLite (bankdb.db)                │  Persistence
└─────────────────────────────────────────────┘
```

| Layer | Description |
|---|---|
| `template/` | Domain models (`Customers`, `Accounts`) |
| `DBmanage/` | Database connection management and utilities |
| `customer/` | Business logic for customers and accounts |
| `main_pipeline/` | Service facade orchestrating operations |
| `Main.java` | CLI entry point |

## Tech Stack

- **Java 21** — Latest LTS with pattern matching and records
- **Maven** — Build and dependency management
- **SQLite** — Embedded, zero‑configuration database
- **JDBC** — Database connectivity

## Getting Started

### Prerequisites

- Java 21 (JDK 21+)
- Maven 3.9+

### Build

```bash
mvn clean compile
```

### Run

```bash
mvn exec:java -Dexec.mainClass="com.foxbank.Main"
```

Or run the compiled class directly:

```bash
mvn package
java -cp target/foxy-core-banking-1.0-SNAPSHOT.jar:$(find ~/.m2 -name "sqlite-jdbc-*.jar" | head -1) com.foxbank.Main
```

### CLI Commands

| Option | Action |
|---|---|
| `1` | Add a new customer |
| `2` | List all customers |
| `3` | Remove a customer |
| `4` | Open an account |
| `5` | Close an account |
| `6` | Deposit funds |
| `7` | Withdraw funds |
| `0` | Quit |

## Project Structure

```
src/main/java/com/foxbank/
├── Main.java                          # CLI entry point
├── main_pipeline/
│   └── bank_service.java              # Service orchestration
├── customer/
│   ├── Customer_Services.java         # Customer CRUD
│   └── Account_Services.java          # Account CRUD
├── DBmanage/
│   ├── DatabaseManager.java           # SQLite connection (singleton)
│   └── gen_rand_numb.java             # ID generation utility
└── template/
    ├── Customers.java                 # Customer model
    └── Accounts.java                  # Account model
```

## Roadmap

- [x] Maven project scaffold
- [x] SQLite database connection
- [x] Customer CRUD (create / remove)
- [ ] Account CRUD (open / close / list)
- [ ] Deposit and withdrawal logic
- [ ] Database table creation on startup
- [ ] Spring Boot migration (REST API + JPA)
- [ ] Unit tests (JUnit + Mockito)
- [ ] Docker containerization
- [ ] Transaction history logging

## License

MIT
