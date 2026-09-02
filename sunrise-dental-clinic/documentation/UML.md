# UML Blueprint

## Actors
- Staff
- Administrator (specialized staff role)

## Core use cases
Login; Register New Appointment; Search Appointment; Calculate and Print Bill; View Reports; Help; Logout.
Admin additionally views audit logs.

## Key relationships
- Register Appointment <<include>> Validate Appointment
- Register Appointment <<include>> Check Dentist Availability
- Calculate Bill <<include>> Retrieve Treatment Cost
- Calculate Bill <<include>> Print Receipt
- Administrator -- View Audit Logs

## Main classes
User, Patient, Dentist, Treatment, Appointment, Bill, SystemLog, AuthService, AppointmentService, BillingService, ReportService, DAO interfaces/implementations, controllers.

Sequence diagrams should cover Login, Register Appointment and Generate Bill.
