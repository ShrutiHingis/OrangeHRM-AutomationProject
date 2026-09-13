# OrangeHRM Automation Project

This project is created as part of the Quality Engineer (Automation) technical assignment.

I have used Selenium with Java and TestNG to automate the Employee Lifecycle flow in OrangeHRM. REST Assured is used for basic API validation.

## Application

OrangeHRM Demo Application:

https://opensource-demo.orangehrmlive.com/

## Tools and Technologies

- Java
- Selenium WebDriver
- TestNG
- Maven
- REST Assured
- Jackson
- Page Object Model (POM)
- Git and GitHub
- Maven Surefire Report

---

## Project Structure

The framework is organized using Page Object Model and separates the page classes, test classes, utilities, test data and configuration.

```text
OrangeHRM-AutomationProject
│
├── .idea/
├── .mvn/
│
├── Reports/
│   └── surefire.html
│
├── Videos/
│   └── EmployeeLifecycleTest.mp4
│
├── src/
│   │
│   ├── main/
│   │   └── java/
│   │       └── com.orangehrm/
│   │           │
│   │           ├── api/
│   │           │
│   │           ├── driver/
│   │           │   └── DriverManager.java
│   │           │
│   │           ├── listeners/
│   │           │
│   │           ├── pages/
│   │           │   ├── LoginPage.java
│   │           │   ├── DashboardPage.java
│   │           │   ├── PIMPage.java
│   │           │   ├── AddEmployeePage.java
│   │           │   ├── EmployeeDetailsPage.java
│   │           │   └── EmployeeListPage.java
│   │           │
│   │           └── utils/
│   │               ├── JsonReader.java
│   │               ├── PropertiesReader.java
│   │               └── WaitHelper.java
│   │
│   └── test/
│       │
│       ├── java/
│       │   └── com.orangehrm/
│       │       │
│       │       ├── api/
│       │       │   └── EmployeeApiTest.java
│       │       │
│       │       ├── base/
│       │       │   └── BaseTest.java
│       │       │
│       │       └── tests/
│       │           ├── JSONReaderTest.java
│       │           ├── LoginTest.java
│       │           └── EmployeeTest.java
│       │
│       └── resources/
│           │
│           ├── Screenshots/
│           │
│           ├── test-data/
│           │   ├── Employee.json
│           │   └── images/
│           │       └── Emp-Pic1.jpg
│           │
│           └── config.properties
│
├── .gitignore
├── pom.xml
└── README.md


## Employee Lifecycle Flow

Login
  ↓
Verify Dashboard
  ↓
Navigate to PIM
  ↓
Add Employee
  ↓
Read Employee Data from JSON
  ↓
Enter Employee Details
  ↓
Upload Profile Picture
  ↓
Save Employee
  ↓
Update Job Title & Employment Status
  ↓
Verify Updated Details
  ↓
Search Employee by Employee ID
  ↓
Verify Employee Exists
  ↓
Delete Employee
  ↓
Verify Employee is Deleted
  ↓
Logout
  ↓
Verify Login Page
  ↓
Verify Session is Invalidated