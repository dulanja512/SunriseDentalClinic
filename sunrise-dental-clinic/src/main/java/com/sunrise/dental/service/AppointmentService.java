package com.sunrise.dental.service;
import com.sunrise.dental.dao.*;
import com.sunrise.dental.model.*;
import com.sunrise.dental.util.AppointmentValidator;
import java.time.*;
import java.util.*;
public class AppointmentService {
    private final AppointmentDAO dao;
    public AppointmentService(AppointmentDAO d) {
        dao=d;
    }
    public String create(Patient p,int dentistId,int treatmentId,LocalDate date,LocalTime time,int userId) {
        AppointmentValidator.validate(p.getFullName(),p.getAddress(),p.getContactNumber(),date,time);
        if(dao.isSlotTaken(dentistId,date,time))throw new IllegalArgumentException("Dentist is already booked for this date and time");
        Appointment a=new com.sunrise.dental.builder.AppointmentBuilder().dentist(dentistId).treatment(treatmentId).date(date).time(time).createdBy(userId).build();
        return dao.create(a,"",p);
    }
    public Appointment find(String n) {
        Appointment a=dao.findByNumber(n);
        if(a==null)throw new IllegalArgumentException("Appointment not found");
        return a;
    }
    public List<Appointment> all() {
        return dao.findAll();
    }
    public void cancel(int id) {
        if(!dao.updateStatus(id,Appointment.Status.CANCELLED))throw new IllegalArgumentException("Appointment not found");
    }
    public void complete(int id) {
        if(!dao.updateStatus(id,Appointment.Status.COMPLETED))throw new IllegalArgumentException("Appointment not found");
    }
    public int todayCount() {
        return dao.countByDate(LocalDate.now());
    }
}
