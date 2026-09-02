package com.sunrise.dental.strategy;
public interface BillingStrategy {
    double calculate(double treatmentCost,double consultationFee);
    String name();
}
