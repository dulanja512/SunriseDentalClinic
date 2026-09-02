package com.sunrise.dental.util;
import org.junit.jupiter.api.*;
import java.time.*;
import static org.junit.jupiter.api.Assertions.*;
class AppointmentValidatorTest {
    @Test void validDataPasses() {
        assertDoesNotThrow(()->AppointmentValidator.validate("John Doe","Kandy","0712345678",LocalDate.now().plusDays(1),LocalTime.of(10,0)));
    }
    @Test void invalidContactFails() {
        assertThrows(IllegalArgumentException.class,()->AppointmentValidator.validate("John","A","123",LocalDate.now().plusDays(1),LocalTime.of(10,0)));
    }
    @Test void pastDateFails() {
        assertThrows(IllegalArgumentException.class,()->AppointmentValidator.validate("John","A","0712345678",LocalDate.now().minusDays(1),LocalTime.of(10,0)));
    }
}
