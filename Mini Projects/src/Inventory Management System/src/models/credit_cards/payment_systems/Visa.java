package models.credit_cards.payment_systems;

import models.credit_cards.interfaces.VisaInterface;

public class Visa extends AbstractCard implements VisaInterface {
    private String paymentSystem;

    public Visa(String firstName, String lastName, String cardNumber, String expiryDate, String ccv, String cardType, double amount) {
        super(firstName, lastName, cardNumber, expiryDate, ccv, amount);
        this.setPaymentSystemVS(cardType);
    }

    @Override
    public String getPaymentSystemVS() {
        return this.paymentSystem;
    }

    @Override
    public void setPaymentSystemVS(String paymentSystem) {
        if (paymentSystem == null || paymentSystem.isEmpty())
            throw new IllegalArgumentException(">! Payment System VS is null or empty, [VisaCard, setPaymentSystemVS()].");

        if (paymentSystem.equalsIgnoreCase("Visa"))
            this.paymentSystem = "Visa";
        else
            throw new IllegalArgumentException(">! Invalid payment system [" + paymentSystem + "].");
    }
}
