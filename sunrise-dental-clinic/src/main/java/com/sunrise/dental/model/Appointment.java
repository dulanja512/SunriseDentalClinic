package com.sunrise.dental.model;
import java.time.*;
public class Appointment {
    public enum Status {
        BOOKED,COMPLETED,CANCELLED
    }
    private int appointmentId,patientId,dentistId,treatmentId,createdBy;
    private String appointmentNumber;
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
    private Status status;
    private Patient patient;
    private Dentist dentist;
    private Treatment treatment;
    public int getAppointmentId() {
        return appointmentId;
    }
    public void setAppointmentId(int v) {
        appointmentId=v;
    }
    public int getPatientId() {
        return patientId;
    }
    public void setPatientId(int v) {
        patientId=v;
    }
    public int getDentistId() {
        return dentistId;
    }
    public void setDentistId(int v) {
        dentistId=v;
    }
    public int getTreatmentId() {
        return treatmentId;
    }
    public void setTreatmentId(int v) {
        treatmentId=v;
    }
    public int getCreatedBy() {
        return createdBy;
    }
    public void setCreatedBy(int v) {
        createdBy=v;
    }
    public String getAppointmentNumber() {
        return appointmentNumber;
    }
    public void setAppointmentNumber(String v) {
        appointmentNumber=v;
    }
    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }
    public void setAppointmentDate(LocalDate v) {
        appointmentDate=v;
    }
    public LocalTime getAppointmentTime() {
        return appointmentTime;
    }
    public void setAppointmentTime(LocalTime v) {
        appointmentTime=v;
    }
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status v) {
        status=v;
    }
    public Patient getPatient() {
        return patient;
    }
    public void setPatient(Patient v) {
        patient=v;
    }
    public Dentist getDentist() {
        return dentist;
    }
    public void setDentist(Dentist v) {
        dentist=v;
    }
    public Treatment getTreatment() {
        return treatment;
    }
    public void setTreatment(Treatment v) {
        treatment=v;
    }
}
