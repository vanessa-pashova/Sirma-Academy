package models.items;

import models.handlers_for_the_items.*;

import java.util.TreeMap;

public class InventoryManager extends AbstractItem {
    private TreeMap<String, AbstractItem> inventory;

    public InventoryManager() {
        this.inventory = new TreeMap<>();
    }

    public InventoryManager(String name, double discount, double price, CategorizableType category) {
        super(name, discount, price, category);
        this.inventory = new TreeMap<>();
    }

    public TreeMap<String, AbstractItem> getInventory() {
        return this.inventory;
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

    private String getFilePathForItem(AbstractItem item) {
        String pathToDir = "/Users/vanessa.pashova/Desktop/Sirma Academy 24/Mini Projects/src/Inventory Management System/src/csvFiles/items/";

        if (item instanceof BookItems)
            return pathToDir + "Books.csv";

        if (item instanceof ClothingItems)
            return pathToDir + "Clothes.csv";

        if (item instanceof ElectronicItems)
            return pathToDir + "Electronics.csv";

        if (item instanceof GroceryItems)
            return pathToDir + "Groceries.csv";

        throw new IllegalArgumentException(">! Unknown item type [InventoryManager, getFilePathForItem()].");
    }

    public void loadInventory() {
        BookItemsHandler bookHandler = new BookItemsHandler() {};
        String pathToDir = "/Users/vanessa.pashova/Desktop/Sirma Academy 24/Mini Projects/src/Inventory Management System/src/csvFiles/items/";
        TreeMap<String, BookItems> books = bookHandler.loadFromCSV(pathToDir + "Books.csv");
        this.inventory.putAll(books);

        ClothesItemsHandler clothesHandler = new ClothesItemsHandler() {};
        TreeMap<String, ClothingItems> clothings = clothesHandler.loadFromCSV(pathToDir + "Clothes.csv");
        this.inventory.putAll(clothings);

        ElectronicItemsHandler electronicHandler = new ElectronicItemsHandler() {};
        TreeMap<String, ElectronicItems> electronics = electronicHandler.loadFromCSV(pathToDir + "Electronics.csv");
        this.inventory.putAll(electronics);

        GroceryItemsHandler groceryHandler = new GroceryItemsHandler() {};
        TreeMap<String, GroceryItems> groceries = groceryHandler.loadFromCSV(pathToDir + "Groceries.csv");
        this.inventory.putAll(groceries);
    }

    public void addItem(AbstractItem item) {
        if (item == null)
            throw new IllegalArgumentException(">! Cannot add null item [InventoryManager, addItem()].");

        String id = generateUniqueID(item);
        inventory.put(id, item);

        String filePath = getFilePathForItem(item);

        if (item instanceof BookItems) {
            BookItemsHandler bookHandler = new BookItemsHandler() {};
            TreeMap<String, BookItems> books = bookHandler.loadFromCSV(filePath);
            books.put(id, (BookItems) item);
            bookHandler.saveToCSV(filePath, books);
        }

        else if (item instanceof ClothingItems) {
            ClothesItemsHandler clothesHandler = new ClothesItemsHandler() {};
            TreeMap<String, ClothingItems> clothes = clothesHandler.loadFromCSV(filePath);
            clothes.put(id, (ClothingItems) item);
            clothesHandler.saveToCSV(filePath, clothes);
        }

        else if (item instanceof ElectronicItems) {
            ElectronicItemsHandler electronicHandler = new ElectronicItemsHandler() {};
            TreeMap<String, ElectronicItems> electronics = electronicHandler.loadFromCSV(filePath);
            electronics.put(id, (ElectronicItems) item);
            electronicHandler.saveToCSV(filePath, electronics);
        }

        else if (item instanceof GroceryItems) {
            GroceryItemsHandler groceryHandler = new GroceryItemsHandler() {};
            TreeMap<String, GroceryItems> groceries = groceryHandler.loadFromCSV(filePath);
            groceries.put(id, (GroceryItems) item);
            groceryHandler.saveToCSV(filePath, groceries);
        }
    }

    public void removeItem(String id) {
        if (!inventory.containsKey(id))
            throw new IllegalArgumentException(">! Item does not exist [InventoryManager, removeItem()].");

        AbstractItem item = inventory.get(id);
        inventory.remove(id);

        String filePath = getFilePathForItem(item);

        if (item instanceof BookItems) {
            BookItemsHandler bookHandler = new BookItemsHandler() {};
            TreeMap<String, BookItems> books = bookHandler.loadFromCSV(filePath);
            bookHandler.removeFromCSV(filePath, id);
        }

        else if (item instanceof ClothingItems) {
            ClothesItemsHandler clothesHandler = new ClothesItemsHandler() {};
            TreeMap<String, ClothingItems> clothes = clothesHandler.loadFromCSV(filePath);
            clothesHandler.removeFromCSV(filePath, id);
        }

        else if (item instanceof ElectronicItems) {
            ElectronicItemsHandler electronicHandler = new ElectronicItemsHandler() {};
            TreeMap<String, ElectronicItems> electronics = electronicHandler.loadFromCSV(filePath);
            electronicHandler.removeFromCSV(filePath, id);
        }

        else if (item instanceof GroceryItems) {
            GroceryItemsHandler groceryHandler = new GroceryItemsHandler() {};
            TreeMap<String, GroceryItems> groceries = groceryHandler.loadFromCSV(filePath);
            groceryHandler.removeFromCSV(filePath, id);
        }
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
