package com.sunrise.dental.controller;
import com.sunrise.dental.dao.*;
import com.sunrise.dental.service.*;
public final class AppContext {
    public static final AuthService AUTH=new AuthService(new UserDAOImpl());
    public static final AppointmentDAO APPT_DAO=new AppointmentDAOImpl();
    public static final AppointmentService APPT=new AppointmentService(APPT_DAO);
    public static final DentistDAO DENTIST_DAO=new DentistDAOImpl();
    public static final TreatmentDAO TREATMENT_DAO=new TreatmentDAOImpl();
    public static final BillingService BILL=new BillingService(new BillDAOImpl(),APPT_DAO);
    public static final ReportService REPORT=new ReportService(APPT_DAO);
    public static final LogService LOG=new LogService(new LogDAOImpl());
    private AppContext() {
    }
}
