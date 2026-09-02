package com.sunrise.dental.service;
import org.junit.jupiter.api.*;
import com.sunrise.dental.util.PasswordHasher;
import static org.junit.jupiter.api.Assertions.*;
class AuthServiceTest {
    @Test void passwordHashCanBeVerified() {
        String h=PasswordHasher.hash("admin123");
        assertTrue(PasswordHasher.matches("admin123",h));
        assertFalse(PasswordHasher.matches("wrong",h));
    }
}
