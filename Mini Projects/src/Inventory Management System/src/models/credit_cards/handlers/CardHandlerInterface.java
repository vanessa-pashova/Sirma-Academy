package models.credit_cards.handlers;

import models.credit_cards.payment_systems.AbstractCard;

import java.util.Map;

public interface CardHandlerInterface {
    Map<String, AbstractCard> loadCards();
    boolean uniqueCard(String cardNumber);
    void saveCards();
    public void addCard(AbstractCard card);
}
