package com.sunrise.dental.util;
import java.time.*;
public final class AppointmentValidator {
    private AppointmentValidator() {
    }
    public static void validate(String name,String address,String contact,LocalDate date,LocalTime time) {
        if(name==null||name.isBlank())throw new IllegalArgumentException("Patient name is required");
        if(address==null||address.isBlank())throw new IllegalArgumentException("Address is required");
        if(contact==null||!contact.matches("0[0-9]{9}"))throw new IllegalArgumentException("Contact number must contain 10 digits");
        if(date==null||date.isBefore(LocalDate.now()))throw new IllegalArgumentException("Appointment date cannot be in the past");
        if(time==null)throw new IllegalArgumentException("Appointment time is required");
    }
}
