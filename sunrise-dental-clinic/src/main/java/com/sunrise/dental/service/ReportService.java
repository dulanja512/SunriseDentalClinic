package com.sunrise.dental.service;
import com.sunrise.dental.dao.*;
import java.time.*;
import java.util.*;
public class ReportService {
    private final AppointmentDAO dao;
    public ReportService(AppointmentDAO d) {
        dao=d;
    }
    public Map<String,Object> dashboard() {
        Map<String,Object> m=new LinkedHashMap<>();
        m.put("todayAppointments",dao.countByDate(LocalDate.now()));
        m.put("totalAppointments",dao.findAll().size());
        return m;
    }
}
