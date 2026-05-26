# 🏦 Banking Management System

A console-based Banking Management System built in Java, demonstrating core Object-Oriented Programming principles including abstraction, inheritance, polymorphism, and thread safety.

## 📋 Features

- Create **Savings** and **Current** bank accounts
- **Deposit** and **Withdraw** funds with validation
- **Transfer** money between accounts
- View detailed **transaction history** with timestamps
- **Monthly interest** calculation for Savings accounts (4% p.a.)
- **Overdraft support** for Current accounts
- Monthly **withdrawal limit** enforcement for Savings accounts (max 5/month)
- **Overdraft fee** auto-applied for negative Current account balances

## 🛠️ Tech Stack

- **Language:** Java (JDK 8+)
- **Concepts:** OOP, Abstract Classes, Inheritance, Polymorphism, Multithreading, Exception Handling, Collections

## 🏗️ Project Structure

```
BankingSystem/
└── src/
    ├── Account.java          # Abstract base class with core banking logic
    ├── SavingsAccount.java   # Extends Account — interest & withdrawal limits
    ├── CurrentAccount.java   # Extends Account — overdraft support
    ├── Bank.java             # Manages all accounts, account creation & registry
    └── BankingApp.java       # Main entry point — CLI menu
```

## ⚙️ OOP Design

```
Account  (abstract)
├── deposit()                → synchronized, thread-safe
├── withdraw()               → synchronized, thread-safe
├── transfer()               → synchronized, thread-safe
├── applyMonthlyInterest()   → abstract (polymorphic)
│
├── SavingsAccount
│   ├── Interest: 4% p.a. applied monthly
│   └── Max 5 withdrawals per month
│
└── CurrentAccount
    ├── Configurable overdraft limit
    └── 1% overdraft fee on negative balance
```

## 🚀 How to Run

**Prerequisites:** Java JDK 8 or above

```bash
cd src
javac *.java
java BankingApp
```

## 🖥️ Menu Options

```
1. Create Savings Account
2. Create Current Account
3. Deposit
4. Withdraw
5. Transfer
6. View Statement
7. View All Accounts
8. Apply Monthly Interest/Fees
9. Exit
```

## 💡 Sample Output

```
=== KaifBank — All Accounts ===
  SAV1001      | Mohammad Kaif        | Savings | ₹60000.00
  SAV1002      | Alice Johnson        | Savings | ₹30000.00
  CUR1003      | TechCorp Ltd         | Current | ₹100000.00

══════════════════════════════════════════
  Savings Account: SAV1001
  Holder  : Mohammad Kaif
  Balance : ₹55000.00
  --- Transaction History ---
  2026-05-26 14:30 | Account opened | Initial deposit: ₹50000.0
  2026-05-26 14:30 | Deposit        | ₹10000.0 | Balance: ₹60000.0
  2026-05-26 14:32 | Withdrawal     | ₹5000.0  | Balance: ₹55000.0
══════════════════════════════════════════
```

## 🔑 Key Concepts Demonstrated

| Concept | Implementation |
|---|---|
| Abstraction | `Account` is abstract — cannot be instantiated directly |
| Inheritance | `SavingsAccount` and `CurrentAccount` extend `Account` |
| Polymorphism | `applyMonthlyInterest()` behaves differently per account type |
| Encapsulation | All fields are private, accessed via methods |
| Thread Safety | `synchronized` keyword on deposit, withdraw, transfer |
| Exception Handling | Custom messages for insufficient funds, invalid amounts, limits |
| Collections | `LinkedHashMap` for ordered account registry |
| AtomicInteger | Thread-safe account number generation |

## 👨‍💻 Author

**Mohammad Kaif**
[LinkedIn](https://linkedin.com/in/mohammad--kaif) · [GitHub](https://github.com/mohmmadkaif7)