package models.credit_cards.payment_systems;

import models.credit_cards.interfaces.MasterCardInterface;

public class MasterCard extends AbstractCard implements MasterCardInterface {
    private String paymentSystem;

    public MasterCard(String firstName, String lastName, String cardNumber, String expiryDate, String cvv, String cardType, double amount) {
        super(firstName, lastName, cardNumber, expiryDate, cvv, amount);
        this.paymentSystem = cardType;
    }

    @Override
    public String getPaymentSystemMS() {
        return this.paymentSystem;
    }

    @Override
    public void setPaymentSystemMS(String paymentSystem) {
        if(paymentSystem == null || paymentSystem.isEmpty())
            throw new IllegalArgumentException(">! Payment System MS is null or empty, [MasterCard, setPaymentSystemMS()].");

        if(paymentSystem.equalsIgnoreCase("MasterCard"))
            this.paymentSystem = "MasterCard";

        else
            throw new IllegalArgumentException(">! Invalid payment system [" + paymentSystem + "].");
    }
}
