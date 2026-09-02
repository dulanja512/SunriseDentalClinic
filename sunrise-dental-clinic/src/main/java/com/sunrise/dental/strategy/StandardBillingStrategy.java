package com.sunrise.dental.strategy;
public class StandardBillingStrategy implements BillingStrategy {
    public double calculate(double t,double c) {
        return t+c;
    }
    public String name() {
        return "Standard Consultation Pricing";
    }
}
