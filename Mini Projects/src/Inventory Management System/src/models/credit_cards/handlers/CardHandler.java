package models.credit_cards.handlers;

import models.credit_cards.payment_systems.AbstractCard;
import models.credit_cards.payment_systems.AmericanExpress;
import models.credit_cards.payment_systems.MasterCard;
import models.credit_cards.payment_systems.Visa;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class CardHandler implements CardHandlerInterface {
    private Map<String, AbstractCard> cards;
    private final String filename = "/Users/vanessa.pashova/Desktop/Sirma Academy 24/Mini Projects/src/Inventory Management System/src/csvFiles/credit_cards/Credentials.csv";

    public CardHandler() {
        cards = new HashMap<>();
    }

    @Override
    public Map<String, AbstractCard> loadCards() {
        File file = new File(this.filename);
        if(!file.exists()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
               writer.write("PaymentSystem|CardNumber|ExpiryDate|CCV|FirstName|FamilyName");
               writer.newLine();
            } catch (IOException e) {
                System.out.print(">! Error while creating loading file! [AbstractCardHandler, loadCards()].");
                System.out.println(e.getMessage());
            }
            System.out.println("[ Credentials.csv created -> AbstractCardHandler, loadCards()].");
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();

            while((line = reader.readLine()) != null) {
                String []components = line.split("\\|");
                String paymentSystem = components[0];
                String cardNumber = components[1];
                String expiryDate = components[2];
                String ccv = components[3];
                String firstName = components[4];
                String familyName = components[5];

                AbstractCard card = null;
                if(paymentSystem.equalsIgnoreCase("Visa"))
                    card = new Visa(firstName, familyName, cardNumber, expiryDate, ccv, paymentSystem);

                else if(paymentSystem.equalsIgnoreCase("MasterCard"))
                    card = new MasterCard(firstName, familyName, cardNumber, expiryDate, ccv, paymentSystem);

                else if(paymentSystem.equalsIgnoreCase("AmericanExpress"))
                    card = new AmericanExpress(firstName, familyName, cardNumber, expiryDate, ccv, paymentSystem);

                else
                    throw new IllegalArgumentException(">! Unknown payment system [" + paymentSystem + "] [AbstractCardHandler, loadCards()].");

                cards.put(cardNumber, card);
            }
        } catch (IOException e) {
            System.out.print(">! Error while reading file! [AbstractCardHandler, loadCards()].");
            System.out.println(e.getMessage());
        }

        return cards;
    }

    public boolean uniqueCard(String cardNumber) {
        if (cards.containsKey(cardNumber))
            return false;

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line = reader.readLine();

            while ((line = reader.readLine()) != null) {
                if (line.contains(cardNumber))
                    return false;
            }
        } catch (IOException e) {
            System.out.println(">! Error while validating card uniqueness: " + e.getMessage());
        }

        return true;
    }

    @Override
    public void saveCards() {
        File file = new File(this.filename);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            if (file.length() == 0) {
                writer.write("PaymentSystem|CardNumber|ExpiryDate|CCV|FirstName|FamilyName");
                writer.newLine();
            }

            cards.forEach((cardNumber, card) -> {
                try {
                    writer.write(card.getClass().getSimpleName() + "|" + card.getNumber() + "|" + card.getExpiryDate() + "|" + card.getCCV() + "|" + card.getFirstName() + "|" + card.getFamilyName());
                    writer.newLine();
                } catch (IOException e) {
                    System.out.print(">! Error while saving in Credentials.csv! [AbstractCardHandler, saveCards()].");
                    System.out.println(e.getMessage());
                }
            });
        } catch (IOException e) {
            System.out.print(">! Error while saving in Credentials.csv, [AbstractCardHandler, saveCards()].");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void addCard(AbstractCard card) {
        if (cards.isEmpty())
            loadCards();

        if (!uniqueCard(card.getNumber()))
            throw new IllegalArgumentException(">! This card is already saved [AbstractCardHandler, addCard()].");

        cards.put(card.getNumber(), card);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write(card.getClass().getSimpleName() + "|" + card.getNumber() + "|" + card.getExpiryDate() + "|" + card.getCCV() + "|" + card.getFirstName() + "|" + card.getFamilyName());
            writer.newLine();
        } catch (IOException e) {
            System.out.print(">! Error while saving the card in Credentials.csv! [AbstractCardHandler, addCard()].");
            System.out.println(e.getMessage());
        }
    }
}
