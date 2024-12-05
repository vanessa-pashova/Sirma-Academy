package models;

import interfacees.AbstractItem;

public class ItemInventory extends AbstractItem {
    private static int itemIDCounter = 0;   // Static counter for unique IDs
    private final int itemID;               // Unique ID for each item
    private int quantity;

    public ItemInventory(String name, double price, CategorizableType category, int quantity) {
        super(name, price, category);       // Initialize fields in AbstractItem
        this.itemID = ++itemIDCounter;      // Automatically assign unique ID
        setQuantity(quantity);              // Validate and set quantity
    }

    public final int getItemIDCounter() {
        return itemID;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        if(quantity <= 0)
            this.quantity = 0;

        else
            this.quantity = quantity;
    }
}