package com.sunrise.dental.strategy;

/** Calculates a transparent clinic bill from the selected treatment price. */
public interface BillingStrategy {
    double calculate(double treatmentCost, double consultationFee, double discount);
    String name();
}
