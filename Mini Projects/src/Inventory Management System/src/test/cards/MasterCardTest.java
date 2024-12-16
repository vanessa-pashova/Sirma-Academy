package test.cards;

import models.credit_cards.payment_systems.MasterCard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MasterCardTest {
    @Test
    void testValidInitialization() {
        // Arrange
        String firstName = "John";
        String lastName = "Doe";
        String cardNumber = "1234567812345678";
        String expiryDate = "12/2025";
        String cvv = "123";
        String paymentSystem = "MasterCard";
        double amount = 1000.0;

        // Act
        MasterCard card = new MasterCard(firstName, lastName, cardNumber, expiryDate, cvv, paymentSystem, amount);

        // Assert
        assertEquals("John", card.getFirstName(), "First name does not match.");
        assertEquals("Doe", card.getFamilyName(), "Last name does not match.");
        assertEquals("1234567812345678", card.getNumber(), "Card number does not match.");
        assertEquals("12/2025", card.getExpiryDate(), "Expiry date does not match.");
        assertEquals("123", card.getCCV(), "CVV does not match.");
        assertEquals("MasterCard", card.getPaymentSystemMS(), "Payment system does not match.");
        assertEquals(1000.0, card.getBalance(), "Balance does not match.");
    }

    @Test
    void testSetPaymentSystemMSWithValidValue() {
        // Arrange
        MasterCard card = new MasterCard("Alice", "Johnson", "1122334455667788", "10/2026", "789", "MasterCard", 1200.0);

        // Act
        card.setPaymentSystemMS("MasterCard");

        // Assert
        assertEquals("MasterCard", card.getPaymentSystemMS(), "Payment system was not set correctly.");
    }

    @Test
    void testSetPaymentSystemMSWithInvalidValueThrowsException() {
        // Arrange
        MasterCard card = new MasterCard("Alice", "Johnson", "1122334455667788", "10/2026", "789", "MasterCard", 1200.0);

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> card.setPaymentSystemMS("Visa"));

        assertTrue(exception.getMessage().contains("Invalid payment system"), "Expected exception for invalid payment system.");
    }

    @Test
    void testSetAndGetMethods() {
        // Arrange
        MasterCard card = new MasterCard("Emily", "Davis", "9988776655443322", "09/2023", "321", "MasterCard", 2450.75);

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
                new MasterCard("Sarah", "Taylor", invalidCardNumber, "06/2028", "111", "MasterCard", 2750.0));

        assertTrue(exception.getMessage().contains("Invalid number length"), "Expected exception for invalid card number length.");
    }

    @Test
    void testInvalidExpiryDateThrowsException() {
        // Arrange
        String invalidExpiryDate = "13/2025";

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () ->
                new MasterCard("Chris", "Anderson", "5566778899001122", invalidExpiryDate, "222", "MasterCard", 1899.45));

        assertTrue(exception.getMessage().contains("Invalid date format"), "Expected exception for invalid expiry date.");
    }

    @Test
    void testInvalidCCVThrowsException() {
        // Arrange
        String invalidCCV = "12";

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new MasterCard("Daniel", "White", "9900112233445566", "01/2028", invalidCCV, "MasterCard", 3200.0));

        assertTrue(exception.getMessage().contains("Invalid CCV length"), "Expected exception for invalid CCV length.");
    }

    @Test
    void testBalanceCannotBeNegative() {
        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new MasterCard("Laura", "Thomas", "6677889900112233", "04/2026", "333", "MasterCard", -100.0));

        assertTrue(exception.getMessage().contains("Balance cannot be negative"), "Expected exception for negative balance.");
    }
}
