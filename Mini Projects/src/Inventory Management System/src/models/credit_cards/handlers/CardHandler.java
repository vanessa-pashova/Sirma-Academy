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
    private final String FILE = "/Users/vanessa.pashova/Desktop/Sirma Academy 24/Mini Projects/src/Inventory Management System/src/csv_files/credit_cards/Credentials.csv";

    public CardHandler() {
        cards = new HashMap<>();
    }

    @Override
    public Map<String, AbstractCard> loadCards() {
        File file = new File(this.FILE);
        if(!file.exists()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
               writer.write("PaymentSystem|CardNumber|ExpiryDate|CCV|FirstName|FamilyName");
               writer.newLine();
            } catch (IOException e) {
                System.out.print(">! Error while creating loading file! [CardHandler, loadCards()].");
                System.out.println(e.getMessage());
            }
            System.out.println("[ Credentials.csv created -> CardHandler, loadCards()].");
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
                double amount = Double.parseDouble(components[6]);

                AbstractCard card = null;
                if(paymentSystem.equalsIgnoreCase("Visa"))
                    card = new Visa(firstName, familyName, cardNumber, expiryDate, ccv, paymentSystem, amount);

                else if(paymentSystem.equalsIgnoreCase("MasterCard"))
                    card = new MasterCard(firstName, familyName, cardNumber, expiryDate, ccv, paymentSystem, amount);

                else if(paymentSystem.equalsIgnoreCase("AmericanExpress"))
                    card = new AmericanExpress(firstName, familyName, cardNumber, expiryDate, ccv, paymentSystem, amount);

                else
                    throw new IllegalArgumentException(">! Unknown payment system [" + paymentSystem + "] [CardHandler, loadCards()].");

                cards.put(cardNumber, card);
            }
        } catch (IOException e) {
            System.out.print(">! Error while reading file! [CardHandler, loadCards()].");
            System.out.println(e.getMessage());
        }

        return cards;
    }

    public boolean uniqueCard(String cardNumber) {
        if (cards.containsKey(cardNumber))
            return false;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE))) {
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
        File file = new File(this.FILE);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            if (file.length() == 0) {
                writer.write("PaymentSystem|CardNumber|ExpiryDate|CCV|FirstName|FamilyName");
                writer.newLine();
            }

            cards.forEach((cardNumber, card) -> {
                try {
                    writer.write(card.getClass().getSimpleName() + "|" + card.getNumber() + "|" + card.getExpiryDate() + "|" + card.getCCV() + "|" + card.getFirstName() + "|" + card.getFamilyName() + "|" + card.getBalance());
                    writer.newLine();
                } catch (IOException e) {
                    System.out.print(">! Error while saving in Credentials.csv! [CardHandler, saveCards()].");
                    System.out.println(e.getMessage());
                }
            });
        } catch (IOException e) {
            System.out.print(">! Error while saving in Credentials.csv, [CardHandler, saveCards()].");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void addCard(AbstractCard card) {
        if (cards.isEmpty())
            loadCards();

        if (!uniqueCard(card.getNumber()))
            throw new IllegalArgumentException(">! This card is already saved [CardHandler, addCard()].");

        cards.put(card.getNumber(), card);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE, true))) {
            writer.write(card.getClass().getSimpleName() + "|" + card.getNumber() + "|" + card.getExpiryDate() + "|" + card.getCCV() + "|" + card.getFirstName() + "|" + card.getFamilyName() + "|" + card.getBalance());
            writer.newLine();
        } catch (IOException e) {
            System.out.print(">! Error while saving the card in Credentials.csv! [CardHandler, addCard()].");
            System.out.println(e.getMessage());
        }
    }

    public void addAmount(String cardNumber, double amount) {
        if(amount <= 0)
            throw new IllegalArgumentException(">! Amount cannot be less or equal than zero!, [CardHandler, addAmount()].");

        if(cards.containsKey(cardNumber)) {
            this.cards.get(cardNumber).setBalance(cards.get(cardNumber).getBalance() + amount);
            removeAllCards();
            saveCards();
        }

        else
            throw new IllegalArgumentException(">! Card does not exist, [CardHandler, addAmount()].");
    }

    public void sendMoney(String cardNumber, double amount) {
        if(cards.containsKey(cardNumber)) {
            this.cards.get(cardNumber).setBalance(cards.get(cardNumber).getBalance() - Math.abs(amount));
            removeAllCards();
            saveCards();
        }

        else
            throw new IllegalArgumentException(">! Card does not exist! [CardHandler, sendMoney()].");
    }

    public void removeAllCards() {
        File file = new File(this.FILE);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write("PaymentSystem|CardNumber|ExpiryDate|CCV|FirstName|FamilyName|Balance");
            writer.newLine();
        } catch (IOException e) {
            System.out.print(">! Error while clearing the file Credentials.csv! [CardHandler, removeAllCards()].");
            System.out.println(e.getMessage());
        }
    }
}
