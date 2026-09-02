package com.sunrise.dental.service;
import com.sunrise.dental.dao.*;
import com.sunrise.dental.model.*;
import com.sunrise.dental.strategy.*;
public class BillingService {
    private final BillDAO billDAO;
    private final AppointmentDAO apptDAO;
    private final BillingStrategy strategy;
    public BillingService(BillDAO b,AppointmentDAO a) {
        billDAO=b;
        apptDAO=a;
        strategy=new StandardBillingStrategy();
    }
    public Bill generate(int appointmentId) {
        Appointment a=apptDAO.findByNumber(findNumber(appointmentId));
        if(a==null)throw new IllegalArgumentException("Appointment not found");
        Bill existing=billDAO.findByAppointment(appointmentId);
        if(existing!=null)return existing;
        Bill b=new Bill();
        b.setAppointmentId(appointmentId);
        b.setConsultationFee(1500);
        b.setTreatmentCost(a.getTreatment().getBaseCost());
        b.setDiscount(0);
        b.setTotalAmount(strategy.calculate(b.getTreatmentCost(),b.getConsultationFee()));
        b.setPricingStrategy(strategy.name());
        billDAO.save(b);
        return b;
    }
    private String findNumber(int id) {
        for(Appointment a:apptDAO.findAll())if(a.getAppointmentId()==id)return a.getAppointmentNumber();
        throw new IllegalArgumentException("Appointment not found");
    }
}
