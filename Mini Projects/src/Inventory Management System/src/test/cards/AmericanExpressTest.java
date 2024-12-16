package test.cards;

import models.credit_cards.payment_systems.AmericanExpress;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AmericanExpressTest {
    @Test
    void testValidInitialization() {
        // Arrange
        String firstName = "John";
        String lastName = "Doe";
        String cardNumber = "1234567812345678";
        String expiryDate = "12/2025";
        String ccv = "123";
        String paymentSystem = "AmericanExpress";
        double amount = 1000.0;

        // Act
        AmericanExpress card = new AmericanExpress(firstName, lastName, cardNumber, expiryDate, ccv, paymentSystem, amount);

        // Assert
        assertEquals("John", card.getFirstName(), "First name does not match.");
        assertEquals("Doe", card.getFamilyName(), "Last name does not match.");
        assertEquals("1234567812345678", card.getNumber(), "Card number does not match.");
        assertEquals("12/2025", card.getExpiryDate(), "Expiry date does not match.");
        assertEquals("123", card.getCCV(), "CCV does not match.");
        assertEquals("AmericanExpress", card.getPaymentSystemAX(), "Payment system does not match.");
        assertEquals(1000.0, card.getBalance(), "Balance does not match.");
    }

    @Test
    void testInvalidPaymentSystemThrowsException() {
        // Arrange
        String firstName = "Jane";
        String lastName = "Doe";
        String cardNumber = "8765432187654321";
        String expiryDate = "11/2024";
        String ccv = "456";
        String invalidPaymentSystem = "Visa";
        double amount = 500.0;

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new AmericanExpress(firstName, lastName, cardNumber, expiryDate, ccv, invalidPaymentSystem, amount));

        assertTrue(exception.getMessage().contains("Invalid payment system"), "Expected exception for invalid payment system.");
    }

    @Test
    void testSetPaymentSystemAXWithValidValue() {
        // Arrange
        AmericanExpress card = new AmericanExpress("Alice", "Johnson", "1122334455667788", "10/2026", "789", "AmericanExpress", 1200.0);

        // Act
        card.setPaymentSystemAX("AmericanExpress");

        // Assert
        assertEquals("AmericanExpress", card.getPaymentSystemAX(), "Payment system was not set correctly.");
    }

    @Test
    void testSetPaymentSystemAXWithInvalidValueThrowsException() {
        // Arrange
        AmericanExpress card = new AmericanExpress("Alice", "Johnson", "1122334455667788", "10/2026", "789", "AmericanExpress", 1200.0);

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> card.setPaymentSystemAX("MasterCard"));

        assertTrue(exception.getMessage().contains("Invalid payment system"), "Expected exception for invalid payment system.");
    }

    @Test
    void testSetAndGetMethods() {
        // Arrange
        AmericanExpress card = new AmericanExpress("Emily", "Davis", "9988776655443322", "09/2023", "321", "AmericanExpress", 2450.75);

        // Act
        card.setFirstName("Robert");
        card.setFamilyName("Brown");
        card.setBalance(3000.0);

        // Assert
        assertEquals("Robert", card.getFirstName(), "First name update failed.");
        assertEquals("Brown", card.getFamilyName(), "Last name update failed.");
        assertEquals(3000.0, card.getBalance(), "Balance update failed.");
    }

    @Test
    void testInvalidCardNumberThrowsException() {
        // Arrange
        String invalidCardNumber = "12345678";

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new AmericanExpress("Sarah", "Taylor", invalidCardNumber, "06/2028", "111", "AmericanExpress", 2750.0));

        assertTrue(exception.getMessage().contains("Invalid number length"), "Expected exception for invalid card number length.");
    }

    @Test
    void testInvalidExpiryDateThrowsException() {
        // Arrange
        String invalidExpiryDate = "13/2025";

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () ->
                new AmericanExpress("Chris", "Anderson", "5566778899001122", invalidExpiryDate, "222", "AmericanExpress", 1899.45));

        assertTrue(exception.getMessage().contains("Invalid date format"), "Expected exception for invalid expiry date.");
    }

    @Test
    void testInvalidCCVThrowsException() {
        // Arrange
        String invalidCCV = "12";

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new AmericanExpress("Daniel", "White", "9900112233445566", "01/2028", invalidCCV, "AmericanExpress", 3200.0));

        assertTrue(exception.getMessage().contains("Invalid CCV length"), "Expected exception for invalid CCV length.");
    }

    @Test
    void testBalanceCannotBeNegative() {
        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new AmericanExpress("Laura", "Thomas", "6677889900112233", "04/2026", "333", "AmericanExpress", -100.0));

        assertTrue(exception.getMessage().contains("Balance cannot be negative"), "Expected exception for negative balance.");
    }
}
