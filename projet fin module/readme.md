# Employee Management System

## 🚀 Overview
Java console application for complete employee management. Provides functionality for managing employee data, including CRUD operations, salary management, and data analysis.

## 📋 Features
- Add new employees with data validation
- Update existing employee information
- Delete employees
- Display all employees
- Search by name or position
- Calculate total salary expenses
- Sort employees by salary

## 🔧 Prerequisites
- Java JDK 8 or higher
- IDE (Eclipse, IntelliJ IDEA, etc.)
- UTF-8 compatible console

## 📁 Project Structure
```
src/
├── IEmployeManagement.java   # Management interface
├── Employe.java             # Employee model class
├── EmployeException.java    # Custom exception handling
├── GestionEmployes.java     # Main implementation
├── Menu.java                # User interface
└── Main.java                # Entry point
```

## ⚙️ Installation

1. Clone the repository:
```bash
git clone [repository-url]
cd employee-management
```

2. Compile the source files:
```bash
javac *.java
```

3. Run the application:
```bash
java Main
```

## 📖 Usage

Launch the application and use the interactive menu:

```
=== EMPLOYEE MANAGEMENT ===
1. Add an employee
2. Modify an employee
3. Delete an employee
4. Display all employees
5. Search for an employee
6. Calculate total salary
7. Sort employees by salary
0. Exit
```

# Employee Management System

[Previous sections remain the same until Usage...]

## 📖 Usage

Launch the application and use the interactive menu:

```
=== EMPLOYEE MANAGEMENT ===
1. Add an employee
2. Modify an employee
3. Delete an employee
4. Display all employees
5. Search for an employee
6. Calculate total salary
7. Sort employees by salary
0. Exit
```

## 📋 Test Cases and Expected Results

### 1️⃣ Adding Employees

```
// Case 1: Adding first employee
Input:
  Option: 1
  ID: 1
  Name: Pierre Dubois
  Position: Senior Developer
  Salary: 45000
Output: "Employee successfully added!"

// Case 2: Adding second employee
Input:
  Option: 1
  ID: 2
  Name: Marie Martin
  Position: Project Manager
  Salary: 55000
Output: "Employee successfully added!"

// Case 3: Adding third employee
Input:
  Option: 1
  ID: 3
  Name: Jean Dupont
  Position: Analyst
  Salary: 42000
Output: "Employee successfully added!"
```

### 2️⃣ Displaying Employees

```
Input:
  Option: 4
Output:
  ID: 1 | Name: Pierre Dubois | Position: Senior Developer | Salary: 45000.00€
  ID: 2 | Name: Marie Martin | Position: Project Manager | Salary: 55000.00€
  ID: 3 | Name: Jean Dupont | Position: Analyst | Salary: 42000.00€
```

### 3️⃣ Searching Employees

```
// Search by position
Input:
  Option: 5
  Search: Developer
Output:
  ID: 1 | Name: Pierre Dubois | Position: Senior Developer | Salary: 45000.00€

// Search by name
Input:
  Option: 5
  Search: Martin
Output:
  ID: 2 | Name: Marie Martin | Position: Project Manager | Salary: 55000.00€
```

### 4️⃣ Modifying Employee

```
Input:
  Option: 2
  ID: 1
  New name: Pierre Dubois
  New position: Lead Developer
  New salary: 50000
Output: "Employee successfully modified!"

// Verification
Input:
  Option: 4
Output: [Shows updated employee information]
```

### 5️⃣ Salary Mass Calculation

```
Input:
  Option: 6
Output:
  "Total salary mass: 147000.00€"
```

### 6️⃣ Sorting Employees

```
// Ascending order
Input:
  Option: 7
  Choice: 1
Output:
  ID: 3 | Name: Jean Dupont | Position: Analyst | Salary: 42000.00€
  ID: 1 | Name: Pierre Dubois | Position: Lead Developer | Salary: 50000.00€
  ID: 2 | Name: Marie Martin | Position: Project Manager | Salary: 55000.00€

// Descending order
Input:
  Option: 7
  Choice: 2
Output:
  ID: 2 | Name: Marie Martin | Position: Project Manager | Salary: 55000.00€
  ID: 1 | Name: Pierre Dubois | Position: Lead Developer | Salary: 50000.00€
  ID: 3 | Name: Jean Dupont | Position: Analyst | Salary: 42000.00€
```

### 7️⃣ Deleting Employee

```
Input:
  Option: 3
  ID: 3
Output: "Employee successfully deleted!"

// Verification
Input:
  Option: 4
Output: [Shows only remaining employees]
```

### 8️⃣ Error Cases

```
// Invalid ID
Input:
  Option: 1
  ID: -1
Output: "Error: ID must be a positive number"

// Invalid Name
Input:
  Option: 1
  ID: 1
  Name: A
Output: "Error: Name must contain only letters, spaces and hyphens (2-50 characters)"

// Invalid Position
Input:
  Option: 1
  ID: 1
  Name: Pierre Dubois
  Position: D3v
Output: "Error: Position must contain only letters, spaces and hyphens (2-50 characters)"

// Invalid Salary
Input:
  Option: 1
  ID: 1
  Name: Pierre Dubois
  Position: Developer
  Salary: -5000
Output: "Error: Salary must be between 0 and 1000000"
```

### 9️⃣ Exiting Application

```
Input:
  Option: 0
Output: [Application terminates properly]
```

[Rest of the README remains the same...]

## ✅ Data Validation

- ID: Positive unique number
- Name: 2-50 characters (letters, spaces, hyphens)
- Position: 2-50 characters (letters, spaces, hyphens)
- Salary: Between 0 and 1,000,000

## 🔒 Error Handling

The system includes comprehensive error handling:
- Input validation
- Clear error messages
- Custom exception management

## 📝 Current Limitations

- In-memory storage (no persistence)
- Fixed maximum capacity
- Console interface only

## 🔄 Future Improvements

- Data persistence implementation
- GUI development
- Department management
- Advanced reporting
- Data export functionality

