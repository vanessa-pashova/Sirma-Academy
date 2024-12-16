package models.credit_cards.payment_systems;

import models.credit_cards.interfaces.AmericanExpressInterface;

public class AmericanExpress extends AbstractCard implements AmericanExpressInterface {
    private String paymentSystem;

    public AmericanExpress(String firstName, String lastName, String cardNumber, String expiryDate, String ccv, String paymentSystem, double amount) {
        super(firstName, lastName, cardNumber, expiryDate, ccv, amount);
        this.setPaymentSystemAX(paymentSystem);
    }

    @Override
    public String getPaymentSystemAX() {
        return this.paymentSystem;
    }

    @Override
    public void setPaymentSystemAX(String paymentSystem) {
        if(paymentSystem == null || paymentSystem.isEmpty())
            throw new IllegalArgumentException(">! Payment System AX is null or empty, [AmericanExpress, setPaymentSystemAX()].");

        if(paymentSystem.equalsIgnoreCase("AmericanExpress"))
            this.paymentSystem = "AmericanExpress";

        else
            throw new IllegalArgumentException(">! Invalid payment system [" + paymentSystem + "].");
    }
}
