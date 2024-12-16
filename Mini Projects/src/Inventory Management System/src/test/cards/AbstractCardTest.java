package test.cards;

import models.credit_cards.payment_systems.AbstractCard;
import org.junit.jupiter.api.*;

import java.time.DateTimeException;

import static org.junit.jupiter.api.Assertions.*;

class AbstractCardTest {

    private static class TestCard extends AbstractCard {
        TestCard(String firstName, String familyName, String number, String expiryDate, String CCV, double balance) {
            super(firstName, familyName, number, expiryDate, CCV, balance);
        }
    }

    @Test
    void testValidCardCreation() {
        AbstractCard card = new TestCard("John", "Doe", "1234567812345678", "12/2025", "123", 1000.0);

        assertEquals("John", card.getFirstName(), "First name should be correctly set.");
        assertEquals("Doe", card.getFamilyName(), "Family name should be correctly set.");
        assertEquals("1234567812345678", card.getNumber(), "Card number should be correctly set.");
        assertEquals("12/2025", card.getExpiryDate(), "Expiry date should be correctly set.");
        assertEquals("123", card.getCCV(), "CCV should be correctly set.");
        assertEquals(1000.0, card.getBalance(), "Balance should be correctly set.");
    }

    @Test
    void testInvalidFirstNameThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> new TestCard(null, "Doe", "1234567812345678", "12/2025", "123", 1000.0));
        assertTrue(exception.getMessage().contains("FirstName cannot be null or empty"));

        exception = assertThrows(IllegalArgumentException.class,
                () -> new TestCard("", "Doe", "1234567812345678", "12/2025", "123", 1000.0));
        assertTrue(exception.getMessage().contains("FirstName cannot be null or empty"));
    }

    @Test
    void testInvalidFamilyNameThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> new TestCard("John", null, "1234567812345678", "12/2025", "123", 1000.0));
        assertTrue(exception.getMessage().contains("FamilyName cannot be null or empty"));

        exception = assertThrows(IllegalArgumentException.class,
                () -> new TestCard("John", "", "1234567812345678", "12/2025", "123", 1000.0));
        assertTrue(exception.getMessage().contains("FamilyName cannot be null or empty"));
    }

    @Test
    void testInvalidCardNumberThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> new TestCard("John", "Doe", null, "12/2025", "123", 1000.0));
        assertTrue(exception.getMessage().contains("cannot be null or empty"));

        exception = assertThrows(IllegalArgumentException.class,
                () -> new TestCard("John", "Doe", "123", "12/2025", "123", 1000.0));
        assertTrue(exception.getMessage().contains("Invalid number length"));

        exception = assertThrows(IllegalArgumentException.class,
                () -> new TestCard("John", "Doe", "12345678ABCD5678", "12/2025", "123", 1000.0));
        assertTrue(exception.getMessage().contains("Invalid number format"));
    }

    @Test
    void testInvalidCCVThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> new TestCard("John", "Doe", "1234567812345678", "12/2025", null, 1000.0));
        assertTrue(exception.getMessage().contains("CCV cannot be null or empty"));

        exception = assertThrows(IllegalArgumentException.class,
                () -> new TestCard("John", "Doe", "1234567812345678", "12/2025", "12", 1000.0));
        assertTrue(exception.getMessage().contains("Invalid CCV length"));

        exception = assertThrows(IllegalArgumentException.class,
                () -> new TestCard("John", "Doe", "1234567812345678", "12/2025", "12A", 1000.0));
        assertTrue(exception.getMessage().contains("Invalid CCV"));
    }

    @Test
    void testInvalidExpiryDateThrowsException() {
        Exception exception = assertThrows(DateTimeException.class,
                () -> new TestCard("John", "Doe", "1234567812345678", "12-2025", "123", 1000.0));
        assertTrue(exception.getMessage().contains(">! Invalid date format, [AbstractCard, setExpiryDate()]."));

        exception = assertThrows(DateTimeException.class,
                () -> new TestCard("John", "Doe", "1234567812345678", "132025", "123", 1000.0));
        assertTrue(exception.getMessage().contains(">! Invalid date format, [AbstractCard, setExpiryDate()]."));
    }

    @Test
    void testInvalidBalanceThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> new TestCard("John", "Doe", "1234567812345678", "12/2025", "123", -1000.0));
        assertTrue(exception.getMessage().contains("Balance cannot be negative"));
    }

    @Test
    void testSettersModifyValues() {
        AbstractCard card = new TestCard("John", "Doe", "1234567812345678", "12/2025", "123", 1000.0);
        card.setFirstName("Jane");
        card.setFamilyName("Smith");
        card.setBalance(2000.0);

        assertEquals("Jane", card.getFirstName(), "First name should be updated.");
        assertEquals("Smith", card.getFamilyName(), "Family name should be updated.");
        assertEquals(2000.0, card.getBalance(), "Balance should be updated.");
    }
}
