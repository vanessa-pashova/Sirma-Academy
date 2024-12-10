package models.items;

import models.interfaces.AbstractItem;

import java.util.TreeMap;
import java.util.Scanner;

public class InventoryManager extends AbstractItem {
    private TreeMap<String, AbstractItem> inventory;

    public InventoryManager() {
        this.inventory = new TreeMap<>();
    }

    public InventoryManager(String name, double price, CategorizableType category, double discount) {
        super(name, price, category, discount);
        this.inventory = new TreeMap<>();
    }

    public TreeMap<String, AbstractItem> getInventory() {
        return this.inventory;
    }

    public void addItem(AbstractItem item) {
        if (item == null)
            throw new IllegalArgumentException(">! Cannot add null item [InventoryManager, addItem()].");

        String id = generateUniqueID(item);
        inventory.put(id, item);
    }

    private String generateUniqueID(AbstractItem item) {
        String prefix;
        if (item instanceof BookItems)
            prefix = "B";

        else if (item instanceof ElectronicItems)
            prefix = "E";

        else if (item instanceof GroceryItems)
            prefix = "G";

        else if (item instanceof ClothingItems)
            prefix = "C";

        else
            throw new IllegalArgumentException(">! Unknown item type [InventoryManager, generateUniqueID()].");

        return prefix + "-" + (inventory.size() + 1);
    }

    public void removeItem(String id) {
        if (inventory.containsKey(id))
            inventory.remove(id);


        else
            throw new IllegalArgumentException(">! Item does not exist [InventoryManager, removeItem()].");
    }

    public void addItemDetails(String id, String details) {
        if (inventory.containsKey(id)) {
            AbstractItem item = inventory.get(id);
            item.setItemDetails(details);
        }

        else
            throw new IllegalArgumentException(">! Item does not exist [InventoryManager, addItemDetails()].");
    }

    public void addDescriptionToItem(String id, String description) {
        if (inventory.containsKey(id)) {
            AbstractItem item = inventory.get(id);
            item.setItemDescription(description);
        }

        else
            throw new IllegalArgumentException(">! Item does not exist [InventoryManager, addDescriptionToItem()].");
    }

    public AbstractItem findItem(String id) {
        if (!inventory.containsKey(id))
            throw new IllegalArgumentException(">! Item does not exist [InventoryManager, findItem()].");

        return inventory.get(id);
    }

    public void clearInventory() {
        inventory.clear();
        System.out.println("----- INVENTORY CLEARED ------");
    }

    public double getTotalValue() {
        return inventory.values().stream().mapToDouble(AbstractItem::getPrice).sum();
    }

    public TreeMap<String, AbstractItem> filterByCategory(CategorizableType category) {
        TreeMap<String, AbstractItem> filteredItems = new TreeMap<>();
        inventory.forEach((id, item) -> {
            if (item.getCategory() == category)
                filteredItems.put(id, item);
        });
        return filteredItems;
    }

    public TreeMap<String, AbstractItem> filterByPriceRange(double minPrice, double maxPrice) {
        TreeMap<String, AbstractItem> filteredItems = new TreeMap<>();
        inventory.forEach((id, item) -> {
            if (minPrice <= item.getPrice() && item.getPrice() <= maxPrice)
                filteredItems.put(id, item);
        });
        return filteredItems;
    }

    @Override
    public void printDetails() {}
}