package com.sunrise.dental.strategy;

public class StandardBillingStrategy implements BillingStrategy {
    @Override
    public double calculate(double treatmentCost, double consultationFee, double discount) {
        double subtotal = Math.max(0.0, treatmentCost) + Math.max(0.0, consultationFee);
        return Math.max(0.0, subtotal - Math.max(0.0, discount));
    }

    @Override
    public String name() {
        return "Standard Treatment Pricing";
    }
}
