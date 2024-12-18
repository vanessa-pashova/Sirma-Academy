package models.items;

import models.interfaces_for_items.*;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class AbstractItem implements Item, Categorizable, Fragile, Perishable, Sellable, Discount, Printable, Identity {
    protected String itemName;
    protected double itemPrice;
    protected CategorizableType category;

    protected double itemDiscount;

    protected boolean fragile;
    protected String creationDate, expirationDate;
    protected boolean sellable;

    protected String itemDetails;
    protected String itemDescription;

    public AbstractItem(String name, double discount, double price, String category) {
        this.setName(name);
        this.setDiscount(discount);
        this.setPrice(price);
        this.setCategory(category);
    }

    public AbstractItem() {
        this.itemName = null;
        this.itemPrice = 0;
        this.category = null;
    }

    //Getters
    @Override
    public String getName() {
        return this.itemName;
    }

    @Override
    public double getDiscount() {
        return this.itemDiscount;
    }

    @Override
    public double getPrice() {
        return this.itemPrice;
    }

    @Override
    public String getCategory() {
        return this.category.toString();
    }

    @Override
    public boolean isFragile() {
        return this.fragile;
    }

    @Override
    public String getCreationDate() {
        return this.creationDate;
    }

    @Override
    public String getExpiryDate() {
        return this.expirationDate;
    }

    @Override
    public boolean isSellable() {
        return this.sellable;
    }

    @Override
    public String getItemDetails() {
        return this.itemDetails;
    }

    @Override
    public String getItemDescription() {
        return this.itemDescription;
    }

    //Setters
    @Override
    public void setName(String itemName) {
        if(itemName == null || itemName.isEmpty())
            throw new IllegalStateException(">! Name cannot be empty [AbstactItem, setName()].");

        this.itemName = Character.toUpperCase(itemName.charAt(0)) + itemName.substring(1);
    }

    @Override
    public void setDiscount(double discount) {
        if(discount < 0.0){
            this.itemDiscount = 0;
            throw new IllegalStateException(">! Discount cannot be less than 0%. [AbstactItem, setDiscount()].");
        }

        if(discount >= 1.0) {
            this.itemDiscount = 0;
            throw new IllegalStateException(">! Discount cannot be greater than 100%. [AbstactItem, setDiscount()].");
        }

        this.itemDiscount = discount;
        this.itemPrice = calculatePrice();
    }

    @Override
    public void setPrice(double itemPrice) {
        if(itemPrice <= 0)
            throw new IllegalStateException(">! Price cannot be less than 0.10 [AbstactItem, setPrice()].");

        this.itemPrice = itemPrice;
    }

    @Override
    public void setCategory(String category) {
        if(category == null)
            throw new IllegalStateException(">! Category cannot be empty [AbstactItem, setCategory()].");

        switch (category.toUpperCase()) {
            case "BOOKS" -> this.category = CategorizableType.BOOKS;
            case "CLOTHING" -> this.category = CategorizableType.CLOTHING;
            case "ELECTRONICS" -> this.category = CategorizableType.ELECTRONICS;
            case "GROCERIES" -> this.category = CategorizableType.GROCERIES;
            default -> throw new IllegalStateException(">! Invalid category, [AbstactItem, setCategory()].");
        }
    }

    @Override
    public void setFragile(boolean fragile) {
        this.fragile = fragile;
    }

    @Override
    public void setExpiry(String creationDate, String expirationDate) {
        if ((creationDate == null || creationDate.isEmpty()) || (expirationDate == null || expirationDate.isEmpty()))
            throw new IllegalStateException(">! Date cannot be empty [AbstractItem, setExpiry()].");

        final String DATE_FORMAT = "dd-MM-yyyy"; // Keep the desired date DATE_FORMAT
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);

        LocalDate creation, expiration;
        try {
            creation = LocalDate.parse(creationDate, formatter);
            expiration = LocalDate.parse(expirationDate, formatter);
        } catch (DateTimeException e) {
            throw new IllegalArgumentException(">! Invalid date DATE_FORMAT [AbstractItem, setExpiry()].", e);
        }

        if (creation.isAfter(expiration))
            throw new IllegalArgumentException(">! Expiry date cannot be before the creation date [AbstractItem, setExpiry()].");

        // Save the formatted strings instead of LocalDate.toString()
        this.creationDate = formatter.format(creation);
        this.expirationDate = formatter.format(expiration);
    }


    @Override
    public void setSellable(boolean sellable) {
        this.sellable = sellable;
    }

    @Override
    public void setItemDetails(String itemDetails) {
        if(itemDetails == null || itemDetails.isEmpty())
            this.itemDetails = "[ No details for this item were provided ]\n";

        else
            this.itemDetails = itemDetails;
    }

    @Override
    public void setItemDescription(String itemDescription) {
        if(itemDescription == null || itemDescription.isEmpty())
            this.itemDescription = "[ No description for this item was provided ]\n";

        else
            this.itemDescription = itemDescription;
    }

    @Override
    public double calculatePrice() {
        return this.itemPrice = Math.round(this.itemPrice * (1 - this.itemDiscount) * 100.0) / 100.0;
    }

    @Override
    public abstract void printDetails();
}