package com.sunrise.dental.model;
import java.time.LocalDateTime;
public class Bill {
    private int billId,appointmentId;
    private double consultationFee,treatmentCost,discount,totalAmount;
    private String pricingStrategy;
    private LocalDateTime generatedAt;
    public int getBillId() {
        return billId;
    }
    public void setBillId(int v) {
        billId=v;
    }
    public int getAppointmentId() {
        return appointmentId;
    }
    public void setAppointmentId(int v) {
        appointmentId=v;
    }
    public double getConsultationFee() {
        return consultationFee;
    }
    public void setConsultationFee(double v) {
        consultationFee=v;
    }
    public double getTreatmentCost() {
        return treatmentCost;
    }
    public void setTreatmentCost(double v) {
        treatmentCost=v;
    }
    public double getDiscount() {
        return discount;
    }
    public void setDiscount(double v) {
        discount=v;
    }
    public double getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(double v) {
        totalAmount=v;
    }
    public String getPricingStrategy() {
        return pricingStrategy;
    }
    public void setPricingStrategy(String v) {
        pricingStrategy=v;
    }
    public LocalDateTime getGeneratedAt() {
        return generatedAt;
    }
    public void setGeneratedAt(LocalDateTime v) {
        generatedAt=v;
    }
}
