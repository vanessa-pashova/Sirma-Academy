package models.user;

import models.credit_cards.handlers.CardHandler;
import models.credit_cards.payment_systems.AbstractCard;
import models.credit_cards.payment_systems.AmericanExpress;
import models.credit_cards.payment_systems.MasterCard;
import models.credit_cards.payment_systems.Visa;
import models.handlers_for_purchase.FavouritesHandler;
import models.handlers_for_purchase.PreviousPurchasesHandler;
import models.items.AbstractItem;

import java.util.Scanner;
import java.util.TreeMap;

public class Customer extends AbstractUser{
    private static final String FAVOURITES_FILE = "/Users/vanessa.pashova/Desktop/Sirma Academy 24/Mini Projects/src/Inventory Management System/src/csv_files/user/Favourites.csv";
    private static final String PREVIOUS_PURCHASES_FILE = "/Users/vanessa.pashova/Desktop/Sirma Academy 24/Mini Projects/src/Inventory Management System/src/csv_files/user/PreviousPurchases.csv";

    private FavouritesHandler favouritesHandler = new FavouritesHandler(FAVOURITES_FILE);
    private PreviousPurchasesHandler previousPurchasesHandler = new PreviousPurchasesHandler(PREVIOUS_PURCHASES_FILE);
    private CardHandler cardHandler = new CardHandler();

    private TreeMap<String, AbstractItem> favourites;
    private TreeMap<String, AbstractItem> previousPurchases;
    private TreeMap<String, AbstractItem> currentPurchase;
    private AbstractCard card;

    public void loadPreviousPurchases() {
        try {
            previousPurchases = previousPurchasesHandler.loadPreviousPurchases();
        } catch (Exception e) {
            System.out.print(">! Error while loading previous purchases, [Customer, loadPreviousPurchases()]. ");
            System.out.println(e.getMessage());
        }
    }

    public void loadFavourites() {
        if (this.favourites == null)
            this.favourites = new TreeMap<>();

        try {
            this.favourites = favouritesHandler.loadFavourites();
        } catch (Exception e) {
            System.out.print(">! Error while loading favourites, [Customer, loadFavourites()]. ");
            System.out.println(e.getMessage());
        }
    }

    public Customer(String firstName, String familyName, String email, String password, String role) {
        super(firstName, familyName, email, password, role);
    }

    public TreeMap<String, AbstractItem> getFavourites() {
        return favourites;
    }

    public TreeMap<String, AbstractItem> getPreviousPurchases() {
        return previousPurchases;
    }

    public TreeMap<String, AbstractItem> getCurrentPurchase() {
        return currentPurchase;
    }

    public AbstractCard getCard() {
        return this.card;
    }

    public void addCard(String number, String expiryDate, String ccv, String cardType, double amount) {
        switch (cardType) {
            case "Visa" -> this.card = new Visa(this.firstName, this.familyName, number, expiryDate, ccv, cardType, amount);
            case "MasterCard" -> this.card = new MasterCard(this.firstName, this.familyName, number, expiryDate, ccv, cardType, amount);
            case "AmericanExpress" -> this.card = new AmericanExpress(this.firstName, this.familyName, number, expiryDate, ccv, cardType, amount);
            default -> throw new IllegalStateException(">! Invalid card type, [User, addCard()].");
        }

        CardHandler cardHandler = new CardHandler();
        cardHandler.addCard(this.card);
    }

    public void checkBalance() {
        System.out.println("------ CHECK BALANCE ------");
        System.out.println("[ Your Balance: " + this.card.getBalance() + " ]");
        System.out.println("---------------------------");
    }

    public void addMoneyToCard(Customer customer, Scanner scanner) {
        if (customer.getCard() == null) {
            System.out.println(">! No card associated with this account.");
            return;
        }

        System.out.print("> Enter amount to add: ");
        try {
            double amount = Double.parseDouble(scanner.nextLine());
            if (amount <= 0) {
                System.out.println(">! Invalid amount. Please enter a positive value.");
                return;
            }

            customer.getCard().setBalance(customer.getCard().getBalance() + amount);
            System.out.println("------ MONEY ADDED SUCCESSFULLY ------");
            customer.checkBalance();
        } catch (NumberFormatException e) {
            System.out.println(">! Invalid input. Please enter a numeric value.");
        }
    }

    public void addToFavourites(AbstractItem item) {
        if (item == null)
            throw new IllegalArgumentException(">! Item cannot be null, [User, addToFavourites()].");

        this.favourites.put(item.getID(), item);
        favouritesHandler.saveToCSV(FAVOURITES_FILE, favourites);
    }

    public void printFavourites() {
        if (this.favourites == null || this.favourites.isEmpty()) {
            System.out.println("------ NO ITEMS IN FAVOURITES ------");
            return;
        }

        System.out.println("------ FAVOURITE ITEMS ------");
        this.favourites.forEach((id, item) -> {
            System.out.println("ID: " + id + " | Name: " + item.getName() + " | Price: " + item.getPrice());
        });
        System.out.println("--------------------------------");
    }

    public void addToCurrentPurchase(AbstractItem item) {
        if (item == null)
            throw new IllegalArgumentException(">! Item cannot be null, [User, addToCurrentPurchase()].");

        if (this.currentPurchase == null)
            this.currentPurchase = new TreeMap<>();

        this.currentPurchase.put(item.getID(), item);
        System.out.println("[ Note that if you exit, the selected items won't be saved! ]");
        System.out.println("------ ITEM ADDED TO CURRENT PURCHASE ------");
    }

    public void addToCurrentPurchase(Customer customer, Scanner scanner) {
        System.out.print("> Enter the ID of the item to add to your current purchase: ");
        String itemId = scanner.nextLine();

        try {
            AbstractItem item = customer.getInventory().findItem(itemId);
            customer.addToCurrentPurchase(item);
            System.out.println("Item added to current purchase!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void printCurrentPurchase() {
        if (this.currentPurchase == null || this.currentPurchase.isEmpty()) {
            System.out.println("------ NO ITEMS IN CURRENT PURCHASE ------");
            return;
        }

        System.out.println("------ CURRENT PURCHASE ------");
        this.currentPurchase.forEach((id, item) -> {
            System.out.println("ID: " + id + " | Name: " + item.getName() + " | Price: " + item.getPrice() + "|" + item.getDiscount());
        });
        System.out.println("--------------------------------");
    }

    public void printPreviousPurchases(Customer customer) {
        TreeMap<String, AbstractItem> previousPurchases = customer.getPreviousPurchases();

        if (previousPurchases == null || previousPurchases.isEmpty()) {
            System.out.println("------ NO PREVIOUS PURCHASES ------");
            return;
        }

        System.out.println("------ PREVIOUS PURCHASES ------");
        previousPurchases.forEach((id, item) -> {
            System.out.println("ID: " + id + " | Name: " + item.getName() + " | Price: " + item.getPrice());
        });
        System.out.println("--------------------------------");
    }


    public void buyItems() {
        System.out.println("------ LET'S SEE IF YOUR BALANCE IS ENOUGH :) ------");
        int quantity = 0;
        for (AbstractItem item : this.currentPurchase.values()) {
            if (this.card.getBalance() >= item.getPrice()) {
                previousPurchases.put(item.getID(), item);
                previousPurchasesHandler.saveToCSV(PREVIOUS_PURCHASES_FILE, previousPurchases);

                currentPurchase.remove(item.getID());

                this.card.setBalance(this.card.getBalance() - item.getPrice());
                cardHandler.sendMoney(this.card.getNumber(), item.getPrice());

                quantity++;
            } else {
                System.out.println(">! You ran out of money, you bought " + quantity + " item(s).");
                break;
            }

        }

        System.out.println(quantity > 0 ? "------ THANK YOU FOR THE PURCHASE ------" : "---------- TILL NEXT TIME ----------");
    }

    @Override
    public void deleteMe() {
        super.deleteMe();

        this.favourites = null;
        this.currentPurchase = null;
        this.previousPurchases = null;
        this.card = null;
    }
}
