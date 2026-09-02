package com.sunrise.dental.service;
import org.junit.jupiter.api.*;
import com.sunrise.dental.strategy.*;
import static org.junit.jupiter.api.Assertions.*;
class BillingServiceTest {
    @Test void standardBillCalculation() {
        BillingStrategy s=new StandardBillingStrategy();
        assertEquals(6500,s.calculate(5000,1500));
    }
}
