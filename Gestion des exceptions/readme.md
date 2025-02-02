# Compte Bancaire - Java Banking System

## Description

This project simulates a **banking system** that allows users to create and manage various types of bank accounts (`CompteBancaire`, `CompteCourant`, `CompteEpargne`). The system supports the following operations:

- **Deposit money** into an account
- **Withdraw money** from an account (with exception handling for insufficient funds)
- **Display account balance**
- **Transfer money** between accounts (with exception handling for insufficient funds and non-existent accounts)

### Account Types

- **CompteCourant**: Allows overdraft functionality (negative balance).
- **CompteEpargne**: Allows interest generation based on the account balance.

### Exceptions

- **FondsInsuffisantsException**: Thrown when attempting to withdraw money with insufficient funds.
- **CompteInexistantException**: Thrown when attempting to transfer money to a non-existing account.

## Features

- **Dépôt d'argent**: Allows depositing money into any account.
- **Retrait d'argent**: Allows withdrawing money, with exceptions for insufficient funds.
- **Affichage du solde**: Displays the current balance of the account.
- **Transfert d'argent**: Allows transferring money between accounts, with checks for insufficient funds and non-existent accounts.

## Project Setup

1. **Clone the repository:**

   ```bash
   git clone https://github.com/adamlk09/UML_Java-avanc-/tree/main/Gestion%20des%20exceptions
