package models.items;

import models.interfaces.AbstractItem;

import java.util.TreeMap;
import java.util.Scanner;

public class InventoryManager extends AbstractItem {
    private TreeMap<Integer, AbstractItem> inventory;       //Collection of items
    private static int nextID = 1;                          //ID of an item

    public InventoryManager() {}

    public InventoryManager(String name, double price, CategorizableType category, double discount) {
        super(name, price, category, discount);
        this.inventory = new TreeMap<>();
    }

    public TreeMap<Integer, AbstractItem> getInventory() {
        return this.inventory;
    }

    public void addItem(AbstractItem item) {
        if(item == null)
            throw new IllegalArgumentException(">! Cannot add null item [InventoryManager, addItem()].");

        inventory.put(nextID++, item);
    }

    public void removeItem(int id) {
        if(inventory.containsKey(id))
            inventory.remove(id);

        else
            throw new IllegalArgumentException(">! Item does not exist [InventoryManager, removeItem()].");
    }

    public void addItemDetails(int id, String name) {
        if(inventory.containsKey(id)) {
            AbstractItem item = inventory.get(id);
            Scanner scanner = new Scanner(System.in);
            String details = scanner.nextLine();
            item.setItemDetails(details);
        }

        else
            throw new IllegalArgumentException(">! Item does not exist [InventoryManager, addItemDetails()].");
    }

    public void addDescriptionToItem(int id, String name) {
        if(inventory.containsKey(id)) {
            AbstractItem item = inventory.get(id);
            Scanner scanner = new Scanner(System.in);
            String description = scanner.nextLine();
            item.setItemDescription(description);
        }

        else
            throw new IllegalArgumentException(">! Item does not exist [InventoryManager, addDescriptionToItem()].");
    }

    public void printInventory() {
        if(inventory.isEmpty())
            System.out.println("------ INVENTORY'S EMPTY, NOTHING TO SHOW ------");

        else {
            System.out.println("------ PRINTING INVENTORY INFORMATION ------");
            this.inventory.forEach((id, item) -> {
                System.out.println("ID: " + id +
                        ", Name: " + item.getName() +
                        ", Discount: " + item.calculatePrice() +
                        ", Price (after discount): " + item.getPrice() +
                        ", Category: " + item.getCategory());
            });
            System.out.println("> Total Items: " + inventory.size());
            System.out.println("-------------------------------------------");
        }
    }

    public AbstractItem findItem(int id) {
        if(!this.inventory.containsKey(id))
            throw new IllegalArgumentException(">! Item does not exist [InventoryManager, findItem()].");

        return this.inventory.get(id);
    }

    public void clearInventory() {
        this.inventory.clear();
        System.out.println("----- INVENTORY CLEARED ------");
    }

    public double getTotalValue() {
        return this.inventory.values().stream().mapToDouble(AbstractItem::getPrice).sum();
    }

    @Override
    public void setCategory(CategorizableType category) {
        super.setCategory(category);
    }

    //Filtration by Category
    public TreeMap<Integer, AbstractItem> filterByCategory(CategorizableType category) {
        TreeMap<Integer, AbstractItem> filteredItems = new TreeMap<>();
        inventory.forEach((id, item) -> {
            if(item.getCategory() == category)
                filteredItems.put(id, item);
        });

        return filteredItems;
    }

    //Filtration by PriceRange
    public TreeMap<Integer, AbstractItem> filterByPriceRange(double minPrice, double maxPrice) {
        TreeMap<Integer, AbstractItem> filteredItems = new TreeMap<>();
        inventory.forEach((id, item) -> {
            if(minPrice <= item.getPrice() && item.getPrice() <= maxPrice)
                filteredItems.put(id, item);
        });

        return filteredItems;
    }
}