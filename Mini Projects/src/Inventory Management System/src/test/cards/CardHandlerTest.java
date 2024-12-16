package test.cards;

import models.credit_cards.handlers.CardHandler;
import models.credit_cards.payment_systems.AbstractCard;
import models.credit_cards.payment_systems.MasterCard;
import models.credit_cards.payment_systems.Visa;
import org.junit.jupiter.api.*;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CardHandlerTest {
    private CardHandler handler;

    @BeforeEach
    void setUp() {
        handler = new CardHandler();
    }

    @Test
    void testLoadCards() {
        Map<String, AbstractCard> cards = handler.loadCards();
        assertEquals(12, cards.size(), "Expected 12 cards loaded from the file.");
        assertTrue(cards.containsKey("9988776655443322"), "Card with number 9988776655443322 should exist.");
        assertTrue(cards.containsKey("1234567812345678"), "Card with number 1234567812345678 should exist.");
    }

    @Test
    void testAddUniqueCard() {
        AbstractCard newCard = new Visa("Sashka", "Vaseva", "1000334411223344", "01/2026", "888", "Visa", 3099.0);
        handler.addCard(newCard);

        Map<String, AbstractCard> cards = handler.loadCards();
        assertEquals(12, cards.size(), "Expected 13 cards after adding a new one.");
        assertTrue(cards.containsKey("1122334411223344"), "New card should be present.");
    }

    @Test
    void testAddDuplicateCardThrowsException() {
        AbstractCard duplicateCard = new Visa("Robert", "Brown", "9988776655443322", "09/2023", "321", "Visa", 2450.75);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> handler.addCard(duplicateCard));
        assertTrue(exception.getMessage().contains("already saved"), "Expected error for duplicate card.");
    }

    @Test
    void testUniqueCard() {
        assertFalse(handler.uniqueCard("9988776655443322"), "Card 9988776655443322 should not be unique.");
        assertTrue(handler.uniqueCard("4455667700112233"), "New card number should be unique.");
    }

    @Test
    void testAddAmountToExistingCard() {
        Map<String, AbstractCard> cards = handler.loadCards();
        handler.addAmount("9988776655443322", 500.0);
        assertEquals(6950.75, cards.get("9988776655443322").getBalance(), "Balance should be updated correctly.");
    }

    @Test
    void testAddAmountToNonExistingCardThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> handler.addAmount("1122334455667789", 500.0));
        assertTrue(exception.getMessage().contains("Card does not exist"), "Expected error for non-existing card.");
    }

    @Test
    void testSendMoneyFromExistingCard() {
        Map<String, AbstractCard> cards = handler.loadCards();
        handler.sendMoney("2233445566778899", 500.0);
        assertEquals(1340.19, cards.get("2233445566778899").getBalance(), "Balance should be deducted correctly.");
    }

    @Test
    void testSendMoneyFromNonExistingCardThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> handler.sendMoney("1122334455667799", 500.0));
        assertTrue(exception.getMessage().contains("Card does not exist"), "Expected error for non-existing card.");
    }

    @Test
    void testSendMoneyOverBalance() {
        Map<String, AbstractCard> cards = handler.loadCards();
        handler.addAmount("1234567812345678", 100.0);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> handler.sendMoney("1234567812345678", 2000.0));
        assertTrue(exception.getMessage().contains("Balance cannot be negative"), "Expected error for insufficient balance.");
    }

    @Test
    void testSaveAndLoadIntegrity() {
        AbstractCard newCard = new MasterCard("Emma", "Clark", "9988001122334455", "02/2029", "555", "MasterCard", 4000.0);
        handler.addCard(newCard);

        Map<String, AbstractCard> cardsAfterSave = handler.loadCards();
        assertTrue(cardsAfterSave.containsKey("9988001122334455"), "Card should be present after saving and reloading.");
        assertEquals(13, cardsAfterSave.size(), "Expected 13 cards after adding a new one.");
    }
}