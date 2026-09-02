package com.sunrise.dental.builder;
import com.sunrise.dental.model.Appointment;
import java.time.*;
public class AppointmentBuilder {
    private final Appointment a=new Appointment();
    public AppointmentBuilder patient(int v) {
        a.setPatientId(v);
        return this;
    }
    public AppointmentBuilder dentist(int v) {
        a.setDentistId(v);
        return this;
    }
    public AppointmentBuilder treatment(int v) {
        a.setTreatmentId(v);
        return this;
    }
    public AppointmentBuilder date(LocalDate v) {
        a.setAppointmentDate(v);
        return this;
    }
    public AppointmentBuilder time(LocalTime v) {
        a.setAppointmentTime(v);
        return this;
    }
    public AppointmentBuilder createdBy(int v) {
        a.setCreatedBy(v);
        return this;
    }
    public Appointment build() {
        if(a.getDentistId()<=0)throw new IllegalStateException("Please select a dentist");
        if(a.getTreatmentId()<=0)throw new IllegalStateException("Please select a treatment");
        if(a.getAppointmentDate()==null)throw new IllegalStateException("Please select an appointment date");
        if(a.getAppointmentTime()==null)throw new IllegalStateException("Please select an appointment time");
        if(a.getCreatedBy()<=0)throw new IllegalStateException("Your user session is invalid. Please log in again");
        return a;
    }
}
