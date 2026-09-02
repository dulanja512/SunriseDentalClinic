package com.sunrise.dental.service;
import com.sunrise.dental.dao.*;
import com.sunrise.dental.model.*;
import java.util.*;
public class PatientService {
    private final AppointmentDAO dao;
    public PatientService(AppointmentDAO d) {
        dao=d;
    }
    public Appointment findByAppointment(String n) {
        return dao.findByNumber(n);
    }
    public List<Appointment> records() {
        return dao.findAll();
    }
}
