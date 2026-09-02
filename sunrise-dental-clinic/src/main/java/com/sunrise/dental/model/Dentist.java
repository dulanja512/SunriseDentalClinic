package com.sunrise.dental.model;
public class Dentist {
    private int dentistId;
    private String fullName,specialization,contactNumber;
    private boolean available;
    public int getDentistId() {
        return dentistId;
    }
    public void setDentistId(int v) {
        dentistId=v;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String v) {
        fullName=v;
    }
    public String getSpecialization() {
        return specialization;
    }
    public void setSpecialization(String v) {
        specialization=v;
    }
    public String getContactNumber() {
        return contactNumber;
    }
    public void setContactNumber(String v) {
        contactNumber=v;
    }
    public boolean isAvailable() {
        return available;
    }
    public void setAvailable(boolean v) {
        available=v;
    }
}
