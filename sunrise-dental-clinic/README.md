# 🦷 Sunrise Dental Clinic Management System

A Java-based web application designed to manage the daily operations of a dental clinic, including appointments, staff accounts, billing, reports, audit logs and patient-related information.

This project was developed as part of the **CIS6003 Advanced Programming** assessment, with a focus on object-oriented programming, software architecture, design patterns, database integration, security, testing and user-friendly UI/UX.

---

## 📌 Project Overview

The **Sunrise Dental Clinic Management System** provides a centralized platform for clinic staff and administrators to manage important clinic activities.

The system is designed to reduce manual work, improve appointment management, provide secure staff access and make billing and reporting easier.

### Main objectives

* Manage dental appointments efficiently
* Prevent dentist double-booking
* Provide secure staff authentication
* Allow administrators to manage staff accounts
* Generate and print patient bills
* Provide reports and dashboard statistics
* Maintain system audit logs
* Provide REST API endpoints
* Apply software design patterns
* Implement automated unit testing
* Provide a clean and user-friendly interface

---

# ✨ Key Features

## 🔐 Authentication & Security

* Secure staff login
* Role-based access
* Admin and Staff roles
* Active/inactive account control
* BCrypt password hashing
* Authentication filter for protected pages
* Session-based user authentication
* Administrators cannot deactivate their own active account

---

## 👥 Staff Management

Administrators can manage clinic user accounts from the Staff Management section.

### Available functions

* Create new staff accounts
* Create administrator accounts
* View registered users
* Activate/deactivate accounts
* Reset staff passwords
* Assign user roles
* Validate usernames and passwords
* Store passwords securely using BCrypt

Only administrators have access to staff administration functions.

---

## 📅 Appointment Management

The appointment module provides the main workflow for managing clinic appointments.

### Features

* Register new appointments
* Capture patient information
* Select dentist
* Select treatment
* Select appointment date and time
* Validate patient contact information
* Prevent dentist double-booking
* Search appointments
* View appointment details
* Complete appointments
* Cancel appointments
* Display appointment status
* Prevent modification of completed/cancelled appointments

The system uses redirect-after-submit to reduce accidental duplicate submissions when refreshing the browser.

---

## 💳 Billing Management

The billing module allows staff to generate and manage treatment bills.

### Features

* Generate bills from completed appointments
* Calculate treatment charges
* Display itemized billing information
* Format monetary values to two decimal places
* View bill details
* Print receipts
* Printer-friendly billing layout

### Billing rule

The selected treatment's `base_cost` is used as the authoritative treatment charge.

There is **no hidden consultation fee automatically added to every bill**.

For example:

> Dental Consultation = Rs. 1,500

rather than automatically adding another Rs. 1,500 consultation charge.

---

## 📊 Dashboard & Reports

The dashboard provides a quick overview of clinic activity.

### Dashboard includes

* Appointment statistics
* Today's appointments
* Recent appointments
* Quick action buttons
* Important system information

### Reports

The reporting section provides summarized clinic information to assist administrators and staff with monitoring system activity.

---

## 📝 Audit Logs

Important system actions are recorded through the audit logging system.

Examples include:

* User login
* Staff account creation
* Account activation/deactivation
* Appointment actions
* Billing-related actions
* Administrative activities

This improves accountability and allows administrators to track important system activity.

---

# 🌐 REST API

The project also provides REST API functionality.

### Available resources

* Appointment API
* Treatment API

The REST layer allows external applications or services to communicate with selected clinic functionality.

---

# 🏗️ System Architecture

The project follows a **3-Tier / MVC-inspired architecture**.

```text
                 ┌──────────────────────┐
                 │       Web Browser    │
                 │      JSP / UI / UX   │
                 └──────────┬───────────┘
                            │
                            ▼
                 ┌──────────────────────┐
                 │     Controllers      │
                 │    Request Handling  │
                 └──────────┬───────────┘
                            │
                            ▼
                 ┌──────────────────────┐
                 │       Services       │
                 │ Business Logic       │
                 │ Validation / Rules   │
                 └──────────┬───────────┘
                            │
                            ▼
                 ┌──────────────────────┐
                 │         DAO          │
                 │ Database Operations  │
                 └──────────┬───────────┘
                            │
                            ▼
                 ┌──────────────────────┐
                 │        MySQL         │
                 │      Database        │
                 └──────────────────────┘
```

### Main layers

| Layer          | Responsibility                            |
| -------------- | ----------------------------------------- |
| **View**       | JSP pages and UI/UX                       |
| **Controller** | Handles HTTP requests                     |
| **Service**    | Business rules and validation             |
| **DAO**        | Database communication                    |
| **Model**      | Represents application data               |
| **API**        | Provides REST endpoints                   |
| **Filter**     | Authentication and access control         |
| **Utility**    | Password hashing, sessions and validation |

---

# 🧩 Design Patterns

Several design patterns are implemented to improve maintainability, reusability and separation of responsibilities.

### Singleton

Used for shared database/application-related resources where a single instance is appropriate.

### Factory

Used to manage creation of database connection-related objects.

### Builder

Used for constructing appointment objects in a controlled way.

### Strategy

Used for billing calculation through the billing strategy abstraction.

```text
BillingStrategy
       │
       ▼
StandardBillingStrategy
```

This allows additional billing strategies to be introduced in the future without changing the main billing service.

### Observer

Used for system event notification and logging.

```text
EventManager
     │
     ├── EventListener
     │
     └── SystemNotificationListener
```

---

# 🛠️ Technologies Used

| Technology          | Purpose                                |
| ------------------- | -------------------------------------- |
| **Java**            | Main programming language              |
| **JSP**             | Web interface                          |
| **Servlets**        | Web request handling                   |
| **Apache Tomcat 9** | Application server                     |
| **Maven**           | Dependency and build management        |
| **MySQL**           | Database                               |
| **JDBC**            | Database connectivity                  |
| **BCrypt**          | Password hashing                       |
| **JAX-RS**          | REST API                               |
| **JUnit**           | Unit testing                           |
| **HTML5**           | Page structure                         |
| **CSS3**            | UI/UX styling                          |
| **JavaScript**      | Client-side validation and interaction |
| **Git / GitHub**    | Version control                        |

---

# 🧪 Testing

Automated unit tests are included using **JUnit**.

Current test coverage includes important application logic such as:

* Authentication service
* Billing service
* Appointment validation

### Test classes

```text
src/test/java/
└── com/sunrise/dental/
    ├── service/
    │   ├── AuthServiceTest.java
    │   └── BillingServiceTest.java
    │
    └── util/
        └── AppointmentValidatorTest.java
```

The tests help verify that important business rules work correctly and reduce the risk of introducing errors when modifying the system.

### Run tests with Maven

```bash
mvn test
```

---

# 🎨 UI/UX Improvements

The latest version includes a redesigned user interface focused on usability and consistency.

### Improvements include

* Modern dashboard layout
* Consistent navigation
* Responsive design
* Improved forms
* Clear form labels
* Better spacing and typography
* Status badges
* Improved tables
* Search/filter functionality
* Clear action buttons
* Confirmation for destructive actions
* Improved empty states
* Printer-friendly billing
* Role-aware navigation
* Shared CSS styling
* Improved login interface
* Help and user guidance

The interface was designed to make common tasks such as creating appointments, managing staff and generating bills easier to understand.

---

# 🗂️ Project Structure

```text
SunriseDentalClinic
│
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com.sunrise.dental
│   │   │       ├── api
│   │   │       ├── builder
│   │   │       ├── controller
│   │   │       ├── dao
│   │   │       ├── filter
│   │   │       ├── model
│   │   │       ├── observer
│   │   │       ├── service
│   │   │       ├── strategy
│   │   │       └── util
│   │   │
│   │   └── webapp
│   │       ├── assets
│   │       ├── WEB-INF
│   │       │   └── view
│   │       └── index.jsp
│   │
│   └── test
│       └── java
│
├── pom.xml
├── database_schema.sql
└── README.md
```

---

# 💻 Installation & Setup

## 1. Requirements

Install the following:

* **JDK 17 or later**
* **Apache Tomcat 9**
* **MySQL Server**
* **MySQL Workbench** or another MySQL client
* **Maven**
* **Eclipse IDE** or another Java IDE

> The application uses `javax.servlet`, therefore **Tomcat 9** is recommended.

---

## 2. Clone the Repository

```bash
git clone YOUR_GITHUB_REPOSITORY_URL
```

Then open the project in Eclipse.

Go to:

```text
File
 → Import
 → Maven
 → Existing Maven Projects
```

Select the project folder.

---

# 🗄️ Database Setup

Create the MySQL database using the provided:

```text
database_schema.sql
```

The default database configuration is:

```text
Database: sunrise_dental_clinic
Host: 127.0.0.1
Port: 3306
User: root
```

### Environment variables

For better security, database configuration can be provided through environment variables:

```text
SUNRISE_DB_URL
SUNRISE_DB_USER
SUNRISE_DB_PASSWORD
```

Equivalent Java system properties are:

```text
sunrise.db.url
sunrise.db.user
sunrise.db.password
```

**Do not commit real production passwords or credentials to GitHub.**

---

# 🔑 Default Login

For development/testing:

```text
Username: admin
Password: admin123
Role: ADMIN
```

After logging in as an administrator, the **Staff Management** section can be used to create additional accounts.

> For a production deployment, change/remove development credentials before deployment.

---

# ▶️ Running the Application

### Eclipse

1. Import the Maven project.
2. Run **Maven → Update Project**.
3. Configure Apache Tomcat 9.
4. Add the Sunrise Dental Clinic project to the server.
5. Run the project on Tomcat.
6. Open the application in a browser.

Example:

```text
http://localhost:8080/SunriseDentalClinic/
```

---

# 🔧 Troubleshooting

## MySQL driver error

If you receive:

```text
java.sql.SQLException:
No suitable driver found
```

check that Maven dependencies have been downloaded.

Run:

```bash
mvn clean install
```

Then in Eclipse:

```text
Maven → Update Project
```

Make sure the MySQL Connector/J dependency is available under:

```text
Maven Dependencies
```

---

## Login does not work

Check:

1. MySQL server is running.
2. Database name is correct.
3. Username/password are correct.
4. Database tables were created.
5. The application is connected to the correct database.
6. The user account is active.

---

## JSP error

If Tomcat reports an error such as:

```text
Exception processing [/WEB-INF/view/appointments.jsp]
```

check the Tomcat console for the complete stack trace and verify that Maven dependencies and JSTL libraries are correctly deployed.

---

# 🔒 Security Considerations

The application includes several basic security measures:

* BCrypt password hashing
* Role-based authorization
* Authentication filtering
* Session management
* Active account checking
* Server-side validation
* Client-side validation
* Protected administrative functionality
* Audit logging

Future production deployment should additionally use:

* HTTPS
* Secure environment-based secrets
* Strong password policies
* CSRF protection
* Secure session cookies
* Database users with restricted privileges

---

# 🚀 Future Improvements

Possible future improvements include:

* Full patient management module
* Dentist management
* Treatment management
* Advanced appointment calendar
* Email/SMS appointment reminders
* Online patient booking
* Payment gateway integration
* Advanced analytics dashboard
* PDF invoice generation
* Backup and restore functionality
* More comprehensive automated testing
* Deployment using Docker
* Cloud deployment
* Improved accessibility support

---

# 👨‍💻 Development Principles

The project follows several software engineering principles:

* Separation of concerns
* Encapsulation
* Reusability
* Maintainability
* Modularity
* Input validation
* Secure password handling
* Consistent UI/UX
* Automated testing
* Object-oriented programming
* Design-pattern-based development

---

# 📄 Academic Project

**Module:** CIS6003 – Advanced Programming

**Project:** Sunrise Dental Clinic Management System

**Application Type:** Java Web Application

**Architecture:** MVC / 3-Tier Architecture

**Database:** MySQL

**Testing Framework:** JUnit

**Server:** Apache Tomcat

---

# 📜 License

This project was developed for academic purposes as part of the CIS6003 Advanced Programming assessment.
# Sunrise Dental Clinic Management System

A Java web application for the CIS6003 Advanced Programming assessment.

## Main functions
- Staff login
- Register a new patient appointment
- Search appointment by appointment number
- Prevent dentist double-booking
- Calculate and print a bill
- Dashboard and reports
- Help section
- REST web services
- MySQL database
- JUnit automated tests

## Architecture
The project uses a 3-tier/MVC structure:
1. Controller – receives web requests
2. Service – contains business rules and validation
3. DAO – communicates with MySQL
4. Model – stores application data

Design patterns used: Singleton, Factory, Builder, Strategy and Observer.

## Eclipse + Tomcat setup (important)
1. Install JDK 17.
2. Install/configure Apache Tomcat 9 (the application uses `javax.servlet`, so Tomcat 9 is the safe choice).
3. In Eclipse choose **File > Import > Maven > Existing Maven Projects** and select this project.
4. Right-click the project > **Maven > Update Project...** > tick **Force Update of Snapshots/Releases** > **OK**.
5. Confirm **Maven Dependencies** appears under the project and contains `mysql-connector-j-8.4.0.jar`.
6. Create the MySQL database by running `database_schema.sql` in MySQL Workbench/phpMyAdmin/CLI.
7. By default the application connects to:
   - URL: `jdbc:mysql://127.0.0.1:3306/sunrise_dental_clinic`
   - User: `root`
   - Password: `Dm!@2001`
   If your MySQL password is different, edit the default in `DBConnection.java` or set `SUNRISE_DB_PASSWORD`.
8. In Eclipse: **Project > Clean**.
9. In the Servers view, right-click Tomcat > **Clean**, then remove/re-add the project if necessary and restart Tomcat.
10. Login with **admin / admin123**.

## Fix for "No suitable driver found"
The project now explicitly loads `com.mysql.cj.jdbc.Driver` and uses the current Maven coordinate `com.mysql:mysql-connector-j:8.4.0`.
If Eclipse still reports that the driver is missing, Maven dependencies have not been deployed to Tomcat. Repeat steps 4, 8 and 9 above. In **Project Properties > Deployment Assembly**, Maven Dependencies should be mapped to `/WEB-INF/lib`.

## Optional database configuration
Instead of editing source code, the database values can be supplied by environment variables:
- `SUNRISE_DB_URL`
- `SUNRISE_DB_USER`
- `SUNRISE_DB_PASSWORD`

Equivalent Java system properties are:
- `sunrise.db.url`
- `sunrise.db.user`
- `sunrise.db.password`

## UI/UX and administration improvements in this fixed version
- Added **Staff Management** for administrator accounts.
- Administrators can create STAFF or ADMIN users with BCrypt-hashed passwords.
- Administrators can activate/deactivate accounts and reset passwords.
- Prevents an administrator from deactivating their own currently signed-in account.
- Navigation is role-aware: normal staff no longer see Staff Management or Audit Logs.
- Dashboard now includes quick actions, clearer statistics and recent appointments.
- Appointment page now has clearer grouped fields, browser-side date/contact validation, table filtering, status badges and safer action buttons.
- Appointment create/complete/cancel actions use redirect-after-submit to avoid duplicate actions on browser refresh.
- Completed or cancelled appointments cannot be changed again through the normal status update query.
- Shared responsive styling was moved to `src/main/webapp/assets/app.css` instead of repeating large CSS blocks on every JSP.
- All main JSP views now use a shared navigation header and context-safe links.
- The authentication filter allows static assets and all REST `/api/*` routes while protecting application pages.

### Staff Management
Sign in as `admin / admin123`, then open **Staff** from the navigation bar. Create an account using a unique username and a password of at least 8 characters. New passwords are stored only as BCrypt hashes in MySQL.


### Billing rules
- The selected treatment's `base_cost` is the authoritative treatment charge.
- There is **no hidden Rs. 1,500 consultation fee** added to every bill.
- `Dental Consultation` therefore bills Rs. 1,500, not Rs. 3,000.
- Existing bills generated by the previous version are automatically recalculated when viewed, and `database_billing_fix.sql` can be run once to repair all stored bills.
- All displayed amounts are formatted to two decimal places.

### Eclipse setup
The project is configured for Java 21, matching the Java 21 JRE shown in Eclipse. In Eclipse, use **Project → Clean**, then **Maven → Update Project**, and restart/redeploy Tomcat.
