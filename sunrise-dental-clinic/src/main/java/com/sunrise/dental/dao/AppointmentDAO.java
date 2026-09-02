package com.sunrise.dental.dao;
import com.sunrise.dental.model.Appointment;
import java.time.*;
import java.util.*;
public interface AppointmentDAO {
    String create(Appointment a,String number,com.sunrise.dental.model.Patient p);
    Appointment findByNumber(String n);
    List<Appointment> findAll();
    boolean isSlotTaken(int dentistId,LocalDate d,LocalTime t);
    boolean updateStatus(int id,Appointment.Status s);
    int countByDate(LocalDate d);
}
