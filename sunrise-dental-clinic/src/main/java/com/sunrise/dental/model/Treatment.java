package com.sunrise.dental.model;
public class Treatment {
    private int treatmentId;
    private String treatmentName,description;
    private double baseCost;
    public int getTreatmentId() {
        return treatmentId;
    }
    public void setTreatmentId(int v) {
        treatmentId=v;
    }
    public String getTreatmentName() {
        return treatmentName;
    }
    public void setTreatmentName(String v) {
        treatmentName=v;
    }
    public double getBaseCost() {
        return baseCost;
    }
    public void setBaseCost(double v) {
        baseCost=v;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String v) {
        description=v;
    }
}
