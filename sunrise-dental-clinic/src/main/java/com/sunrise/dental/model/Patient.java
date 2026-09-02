package com.sunrise.dental.model;
public class Patient {
    private int patientId;
    private String appointmentNumber,fullName,address,contactNumber,email;
    public Patient() {
    }
    public int getPatientId() {
        return patientId;
    }
    public void setPatientId(int v) {
        patientId=v;
    }
    public String getAppointmentNumber() {
        return appointmentNumber;
    }
    public void setAppointmentNumber(String v) {
        appointmentNumber=v;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String v) {
        fullName=v;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String v) {
        address=v;
    }
    public String getContactNumber() {
        return contactNumber;
    }
    public void setContactNumber(String v) {
        contactNumber=v;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String v) {
        email=v;
    }
}
