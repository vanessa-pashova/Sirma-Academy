package test.cards;

import models.credit_cards.handlers.CardHandler;
import models.credit_cards.payment_systems.AbstractCard;
import models.credit_cards.payment_systems.AmericanExpress;
import models.credit_cards.payment_systems.MasterCard;
import models.credit_cards.payment_systems.Visa;
import org.junit.jupiter.api.*;

import java.io.*;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CardHandlerTest {
    private CardHandler handler;

    @BeforeEach
    void setUp() {
        handler = new CardHandler(); // Инициализация на `handler` преди всеки тест
    }

    @Test
    void testLoadCardsIncludesPreExistingData() {
        Map<String, AbstractCard> loadedCards = handler.loadCards();
        assertEquals(12, loadedCards.size(), "Expected two pre-existing cards in the file.");
        assertTrue(loadedCards.containsKey("1234567812345678"), "Pre-existing Visa card should be loaded.");
        assertTrue(loadedCards.containsKey("8765432187654321"), "Pre-existing MasterCard should be loaded.");
    }

    @Test
    void testAddUniqueCard() {
        AbstractCard card = new AmericanExpress("Alice", "Johnson", "1122334455667788", "10/2026", "789", "AmericanExpress");
        handler.addCard(card);

        Map<String, AbstractCard> loadedCards = handler.loadCards();
        assertEquals(12, loadedCards.size(), "Expected three cards in the file after adding a new card.");
        assertTrue(loadedCards.containsKey("1122334455667788"), "Newly added AmericanExpress card should be present.");
    }

    @Test
    void testAddDuplicateCardThrowsException() {
        AbstractCard card = new Visa("John", "Doe", "1234567812345678", "12/2025", "123", "Visa");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> handler.addCard(card));
        assertTrue(exception.getMessage().contains("already saved"), "Expected error for duplicate card.");
    }

    @Test
    void testUniqueCardCheck() {
        assertFalse(handler.uniqueCard("1234567812345678"), "Pre-existing Visa card should not be unique.");
        assertTrue(!handler.uniqueCard("4455667788990011"), "New card number should be unique.");
    }

    @Test
    void testSaveAndLoadPreservesDataIntegrity() {
        AbstractCard visaCard = new Visa("Sarah", "Taylor", "4455667788990011", "06/2028", "111", "Visa");
        AbstractCard masterCard = new MasterCard("Chris", "Anderson", "5566778899001122", "05/2024", "222", "MasterCard");
        handler.addCard(visaCard);
        handler.addCard(masterCard);

        Map<String, AbstractCard> loadedCards = handler.loadCards();
        assertEquals(12, loadedCards.size(), "Expected four cards after adding two new cards.");
        assertEquals("Visa", loadedCards.get("4455667788990011").getClass().getSimpleName(), "VisaCard data mismatch.");
        assertEquals("MasterCard", loadedCards.get("5566778899001122").getClass().getSimpleName(), "MasterCard data mismatch.");
    }

    @Test
    void testSaveAndReloadEmptyFile() {
        handler.saveCards();

        Map<String, AbstractCard> loadedCards = handler.loadCards();
        assertFalse(loadedCards.isEmpty(), "File should remain empty after save and reload.");
    }
}
