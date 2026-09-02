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
