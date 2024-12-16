package models.user;

import models.credit_cards.handlers.CardHandler;
import models.credit_cards.payment_systems.AbstractCard;
import models.credit_cards.payment_systems.AmericanExpress;
import models.credit_cards.payment_systems.MasterCard;
import models.credit_cards.payment_systems.Visa;
import models.handlers_for_purchase.FavouritesHandler;
import models.items.AbstractItem;
import models.items.InventoryManager;

import java.util.Scanner;
import java.util.TreeMap;

public class User {
    private String firstName, familyName, email, password;
    private AbstractCard card;

    private TreeMap<String, AbstractItem> previousPurchases, currentPurchase, favourites;
    private InventoryManager inventoryManager = new InventoryManager() {};

    public User(String firstName, String familyName, String email, String password) {
        this.setFirstName(firstName);
        this.setFamilyName(familyName);
        this.setEmail(email);
        this.setPassword(password);
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getFamilyName() {
        return this.familyName;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setFirstName(String firstName) {
        if(firstName == null || firstName.isEmpty())
            throw new IllegalArgumentException(">! User's first name cannot be empty, [User, setFirstName()].");

        this.firstName = firstName.toUpperCase().charAt(0) + firstName.substring(1).toLowerCase();
    }

    public void setFamilyName(String familyName) {
        if(familyName == null || familyName.isEmpty())
            throw new IllegalArgumentException(">! User's family cannot be empty, [User, setFamilyName()].");

        this.familyName = familyName.toUpperCase().charAt(0) + familyName.substring(1).toLowerCase();
    }

    public void setEmail(String email) {
        if(email == null || email.isEmpty())
            throw new IllegalArgumentException(">! User's email cannot be empty, [User, setEmail()].");

        if(!email.endsWith("@gmail.com") || !email.endsWith("@abv.bg"))
            throw new IllegalArgumentException(">! Invalid email domain, [User, setEmail()].");

        this.email = email.toLowerCase();
    }

    public void setPassword(String password) {
        if(password == null || password.isEmpty())
            throw new IllegalArgumentException(">! User's password cannot be empty, [User, setPassword()].");

        if(password.length() < 8 || password.length() > 16)
            throw new IllegalArgumentException(">! Invalid password length - must be between 8 and 16, [User, setPassword()].");

        boolean digit = false, capitalLetter = false, smallLetter = false;
        for(char symbol : password.toCharArray()) {
            if(Character.isDigit(symbol))
                digit = true;

            else if(Character.isUpperCase(symbol))
                capitalLetter = true;

            else if(Character.isLowerCase(symbol))
                smallLetter = true;

            if(digit && capitalLetter && smallLetter)
                break;
        }

        if(digit && capitalLetter && smallLetter)
            this.password = password;

        else
            throw new IllegalStateException(">! Invalid password format - must contain at least one capital and one small letter, and a digit as well, [User, setPassword()].");
    }

    public void addCard(String number, String expiryDate, String ccv, String cardType) {
        switch (cardType) {
            case "Visa" -> this.card = new Visa(this.firstName, this.familyName, number, expiryDate, ccv, cardType);
            case "MasterCard" -> this.card = new MasterCard(this.firstName, this.familyName, number, expiryDate, ccv, cardType);
            case "AmericanExpress" -> this.card = new AmericanExpress(this.firstName, this.familyName, number, expiryDate, ccv, cardType);
            default -> throw new IllegalStateException(">! Invalid card type, [User, addCard()].");
        }

        CardHandler cardHandler = new CardHandler();
        cardHandler.addCard(this.card);
    }

    public void itemsLookThru() {
        System.out.println("------ INVENTORY CURRENT AVAILABILITY ------");
        inventoryManager.printInventory();
        System.out.println("--------------------------------------------");
    }

    public void addToFavourites(String id) {
        FavouritesHandler favouritesHandler = new FavouritesHandler() {};
        if(id == null || id.isEmpty())
            throw new IllegalArgumentException(">! Item's id cannot be empty, [User, addToFavourites()].");

        if(inventoryManager.getInventory().get(id) != null) {
            favourites.put(id, inventoryManager.getInventory().get(id));
            favouritesHandler.saveToCSV("/Users/vanessa.pashova/Desktop/Sirma Academy 24/Mini Projects/src/Inventory Management System/src/csv_files/user/Favourites.csv", favourites);
        }

        else
            throw new IllegalStateException(">! Item does not exist in the inventory, [User, addToFavourites()].");
    }

    public void printFavourites() {
        System.out.println("------ MY FAVOURITES ------");
        favourites.forEach((id, item) -> {
            item.printDetails();
        });
        System.out.println("---------------------------");
    }


}