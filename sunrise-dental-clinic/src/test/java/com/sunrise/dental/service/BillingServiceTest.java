package com.sunrise.dental.service;

import org.junit.jupiter.api.Test;
import com.sunrise.dental.strategy.BillingStrategy;
import com.sunrise.dental.strategy.StandardBillingStrategy;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BillingServiceTest {
    private final BillingStrategy strategy = new StandardBillingStrategy();

    @Test
    void treatmentPriceIsTheBillTotalWhenThereIsNoAdditionalFee() {
        assertEquals(5000.0, strategy.calculate(5000.0, 0.0, 0.0));
    }

    @Test
    void dentalConsultationIsNotChargedTwice() {
        assertEquals(1500.0, strategy.calculate(1500.0, 0.0, 0.0));
    }

    @Test
    void discountCannotMakeBillNegative() {
        assertEquals(0.0, strategy.calculate(5000.0, 0.0, 6000.0));
    }
}
