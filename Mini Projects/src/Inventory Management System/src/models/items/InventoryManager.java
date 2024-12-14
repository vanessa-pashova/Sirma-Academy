package models.items;

import models.handlers_for_the_items.*;
import models.interfaces_for_items.Categorizable;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.Scanner;

public class InventoryManager{
    private TreeMap<String, AbstractItem> inventory;

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

    private void loadInventoryFromCSV() {
        String pathToDir = "/Users/vanessa.pashova/Desktop/Sirma Academy 24/Mini Projects/src/Inventory Management System/src/csvFiles/items/";

        BookItemsHandler bookHandler = new BookItemsHandler() {};
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

    private void saveToCSV(AbstractItem currentItem) {
        if (inventory.containsKey(currentItem.getID()))
            throw new IllegalArgumentException(">! Duplicate item ID detected: " + currentItem.getID());

        if(currentItem instanceof BookItems) {
            BookItemsHandler bookHandler = new BookItemsHandler() {};
            TreeMap<String, BookItems> books = bookHandler.loadFromCSV(getFilePathForItem(currentItem));
            books.put(currentItem.getID(), (BookItems)currentItem);
            bookHandler.saveToCSV(getFilePathForItem(currentItem), books);
        }

        else if(currentItem instanceof ClothingItems) {
            ClothesItemsHandler clothesHandler = new ClothesItemsHandler() {};
            TreeMap<String, ClothingItems> clothings = clothesHandler.loadFromCSV(getFilePathForItem(currentItem));
            clothings.put(currentItem.getID(), (ClothingItems) currentItem);
            clothesHandler.saveToCSV(getFilePathForItem(currentItem), clothings);
        }

        else if(currentItem instanceof ElectronicItems) {
            ElectronicItemsHandler electronicHandler = new ElectronicItemsHandler() {};
            TreeMap<String, ElectronicItems> electronics = electronicHandler.loadFromCSV(getFilePathForItem(currentItem));
            electronics.put(currentItem.getID(), (ElectronicItems) currentItem);
            electronicHandler.saveToCSV(getFilePathForItem(currentItem), electronics);
        }

        else if(currentItem instanceof GroceryItems) {
            GroceryItemsHandler groceryHandler = new GroceryItemsHandler() {};
            TreeMap<String, GroceryItems> groceries = groceryHandler.loadFromCSV(getFilePathForItem(currentItem));
            groceries.put(currentItem.getID(), (GroceryItems) currentItem);
            groceryHandler.saveToCSV(getFilePathForItem(currentItem), groceries);
        }
    }

    private void removeFromCSV(AbstractItem item) {
        if(item instanceof BookItems) {
            BookItemsHandler bookHandler = new BookItemsHandler() {};
            bookHandler.removeFromCSV(getFilePathForItem(item), item.getID());
        }

        else if(item instanceof ClothingItems) {
            ClothesItemsHandler clothesHandler = new ClothesItemsHandler() {};
            clothesHandler.removeFromCSV(getFilePathForItem(item), item.getID());
        }

        else if(item instanceof ElectronicItems) {
            ElectronicItemsHandler electronicHandler = new ElectronicItemsHandler() {};
            electronicHandler.removeFromCSV(getFilePathForItem(item), item.getID());
        }

        else if(item instanceof GroceryItems) {
            GroceryItemsHandler groceryHandler = new GroceryItemsHandler() {};
            groceryHandler.removeFromCSV(getFilePathForItem(item), item.getID());
        }
    }

    private double getTotalValue() {
        return inventory.values().stream().mapToDouble(AbstractItem::getPrice).sum();
    }

    private int getTotalQuantity() {
        return inventory.size();
    }

    public InventoryManager() {
        this.inventory = new TreeMap<>();
        this.loadInventoryFromCSV();
    }

    public TreeMap<String, AbstractItem> getInventory() {
        return this.inventory;
    }

    public void loadingInventory() {
        System.out.println("------ LOADING NEW ITEMS IN THE INVENTORY ------");
        Scanner scanner = new Scanner(System.in);
        int numberOfItems = Integer.parseInt(scanner.nextLine());

        if(numberOfItems <= 0) {
            System.out.println("[ No Items Loaded -> InventoryManager, loadingInventory() ]");
            return;
        }

        else {
            for(int i = 0; i < numberOfItems; i++) {
                System.out.print("> Insert item[" + (i + 1) + "] category: ");
                String itemCategory = scanner.nextLine();

                switch (itemCategory.toUpperCase()) {
                    case "BOOKS": {
                        System.out.print("> Insert item[" + (i + 1) + "] name: ");
                        String name = scanner.nextLine();
                        System.out.print("> Insert item[" + (i + 1) + "] price: ");
                        double price = Double.parseDouble(scanner.nextLine());
                        System.out.print("> Insert item[" + (i + 1) + "] discount: ");
                        double discount = Double.parseDouble(scanner.nextLine());
                        System.out.print("> Insert item[" + (i + 1) + "] author: ");
                        String author = scanner.nextLine();
                        System.out.print("> Insert item[" + (i + 1) + "] genre: ");
                        String genre = scanner.nextLine();
                        System.out.print("> Insert item[" + (i + 1) + "] total pages: ");
                        int totalPages = Integer.parseInt(scanner.nextLine());
                        System.out.print("> Insert item[" + (i + 1) + "] publisher: ");
                        String publisher = scanner.nextLine();
                        System.out.print("> Insert item[" + (i + 1) + "] description: ");
                        String description = scanner.nextLine();

                        AbstractItem currentItem = new BookItems(name, discount, price, author, genre, totalPages, publisher, description);
                        this.inventory.put(currentItem.getID(), currentItem);
                        saveToCSV(currentItem);
                        break;
                    }

                    case "CLOTHES": {
                        System.out.print("> Insert item[" + (i + 1) + "] name: ");
                        String name = scanner.nextLine();
                        System.out.print("> Insert item[" + (i + 1) + "] price: ");
                        double price = Double.parseDouble(scanner.nextLine());
                        System.out.print("> Insert item[" + (i + 1) + "] discount: ");
                        double discount = Double.parseDouble(scanner.nextLine());
                        System.out.print("> Insert item[" + (i + 1) + "] brand: ");
                        String brand = scanner.nextLine();
                        System.out.print("> Insert item[" + (i + 1) + "] size: ");
                        String size = scanner.nextLine();
                        System.out.print("> Insert item[" + (i + 1) + "] color: ");
                        String color = scanner.nextLine();
                        System.out.print("> Insert item[" + (i + 1) + "] details: ");
                        String details = scanner.nextLine();

                        AbstractItem currentItem = new ClothingItems(name, discount, price, brand, size, color, details);
                        this.inventory.put(currentItem.getID(), currentItem);
                        saveToCSV(currentItem);
                        break;
                    }

                    case "ELECTRONICS": {
                        System.out.print("> Insert item[" + (i + 1) + "] name: ");
                        String name = scanner.nextLine();
                        System.out.print("> Insert item[" + (i + 1) + "] price: ");
                        double price = Double.parseDouble(scanner.nextLine());
                        System.out.print("> Insert item[" + (i + 1) + "] discount: ");
                        double discount = Double.parseDouble(scanner.nextLine());
                        System.out.print("> Insert item[" + (i + 1) + "] brand: ");
                        String brand = scanner.nextLine();
                        System.out.print("> Insert item[" + (i + 1) + "] warranty: ");
                        int warranty = Integer.parseInt(scanner.nextLine());
                        System.out.print("> Insert item[" + (i + 1) + "] details: ");
                        String details = scanner.nextLine();

                        AbstractItem currentItem = new ElectronicItems(name, discount, price, brand, warranty, details);
                        this.inventory.put(currentItem.getID(), currentItem);
                        saveToCSV(currentItem);
                        break;
                    }

                    case "GROCERIES": {
                        System.out.print("> Insert item[" + (i + 1) + "] name: ");
                        String name = scanner.nextLine();
                        System.out.print("> Insert item[" + (i + 1) + "] price: ");
                        double price = Double.parseDouble(scanner.nextLine());
                        System.out.print("> Insert item[" + (i + 1) + "] discount: ");
                        double discount = Double.parseDouble(scanner.nextLine());
                        System.out.print("> Insert item[" + (i + 1) + "] description: ");
                        String description = scanner.nextLine();
                        System.out.print("> Insert item[" + (i + 1) + "] weight: ");
                        double weight = Double.parseDouble(scanner.nextLine());
                        System.out.print("> Insert item[" + (i + 1) + "] calories: ");
                        int calories = Integer.parseInt(scanner.nextLine());
                        System.out.print("> Insert item[" + (i + 1) + "] creation date (DD-MM-YYYY): ");
                        String creationDate = scanner.nextLine();
                        System.out.print("> Insert item[" + (i + 1) + "] expiration date (DD-MM-YYYY): ");
                        String expirationDate = scanner.nextLine();

                        AbstractItem currentItem = new GroceryItems(name, discount, price, description, weight, calories, creationDate, expirationDate);
                        this.inventory.put(currentItem.getID(), currentItem);
                        saveToCSV(currentItem);
                        break;
                    }

                    default: {
                        System.out.println(">! This item will not be added due to invalid category, [InventoryManager, loadingInventory()].");
                        break;
                    }
                }
            }
        }

        System.out.println("------ END OF LOADING NEW ITEMS ------");
    }

    public void soldOut(ArrayList<String> idList) {
        for (String id : idList) {
            if(inventory.containsKey(id)) {
                removeFromCSV(inventory.get(id));
                inventory.remove(id);
            }

            else
                System.out.println(">! Item " + id + " does not exist or has invalid ID, [InventoryManager, soldOut()].");
        }
    }

    public void setNewDiscount() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("------ NEW DISCOUNT ------");
        System.out.print("> Insert the category of the product (BOOKS || CLOTHES || ELECTRONICS): ");
        String category = scanner.nextLine();

        boolean exists = false;

        switch (category.toUpperCase()) {
            case "BOOKS": {
                BookItemsHandler bookItemsHandler = new BookItemsHandler() {};
                TreeMap<String, BookItems> books = bookItemsHandler.loadFromCSV("/Users/vanessa.pashova/Desktop/Sirma Academy 24/Mini Projects/src/Inventory Management System/src/csvFiles/items/Books.csv");

                System.out.println("------ CHOICE: ALL OR PARTICULAR ONES (CHOSEN BY GENRE) ------");
                System.out.print("> Type A (for all books no matter the genre) || Type P (for particular genre: ");
                String choice = scanner.nextLine();

                if(choice.equals("A")) {
                    System.out.print("> Insert the new discount [0, 1): ");
                    double newDiscount = Double.parseDouble(scanner.nextLine());

                    if(0 <= newDiscount && newDiscount < 1) {
                        for(Map.Entry<String, BookItems> entry : books.entrySet())
                            entry.getValue().setDiscount(newDiscount);

                        bookItemsHandler.saveToCSV("/Users/vanessa.pashova/Desktop/Sirma Academy 24/Mini Projects/src/Inventory Management System/src/csvFiles/items/Books.cvs", books);
                    }

                    else
                        throw new IllegalArgumentException(">! Invalid discount - must be [0, 1), [InventoryManager, setNewDiscount() --> case BOOKS].");
                }

                else if(choice.equals("P")) {
                    System.out.print("> Insert the genre of the book/s: ");
                    String title = scanner.nextLine();

                    if(title.equalsIgnoreCase("FANTASY") || title.equalsIgnoreCase("SCIENCE_FICTION") || title.equalsIgnoreCase("MYSTERY") ||
                            title.equalsIgnoreCase("NON_FICTION") || title.equalsIgnoreCase("BIOGRAPHY")) {
                        for(Map.Entry<String, BookItems> entry : books.entrySet()) {
                            if(entry.getValue().getGenre().toString().equalsIgnoreCase(title)) {
                                System.out.println("> Insert the new discount [0, 1): ");
                                double newDiscount = Double.parseDouble(scanner.nextLine());
                                entry.getValue().setDiscount(newDiscount);
                                exists = true;
                            }
                        }

                        bookItemsHandler.saveToCSV("/Users/vanessa.pashova/Desktop/Sirma Academy 24/Mini Projects/src/Inventory Management System/src/csvFiles/items/Books.cvs", books);
                    }

                    else
                        throw new IllegalArgumentException(">! Invalid genre given, [InventoryManager, setNewDiscount() --> case BOOKS].");
                }

                break;
            }

            case "CLOTHES": {
                ClothesItemsHandler clothesItemsHandler = new ClothesItemsHandler() {};
                TreeMap<String, ClothingItems> clothes = clothesItemsHandler.loadFromCSV("/Users/vanessa.pashova/Desktop/Sirma Academy 24/Mini Projects/src/Inventory Management System/src/csvFiles/items/Clothes.csv");
                System.out.print("> Insert the which brand to have a new discount [0, 1): ");
                String brand = scanner.nextLine();

                if(brand.equalsIgnoreCase("ZARA") || brand.equalsIgnoreCase("NIKE") || brand.equalsIgnoreCase("TOMMY_HILFIGER")) {
                    System.out.print("> Insert the new discount [0, 1): ");
                    double newDiscount = Double.parseDouble(scanner.nextLine());

                    if(0 <= newDiscount && newDiscount < 1) {
                        for(Map.Entry<String, ClothingItems> entry : clothes.entrySet()) {
                            if(entry.getValue().getBrand().toString().equalsIgnoreCase(brand)){
                                entry.getValue().setDiscount(newDiscount);
                                exists = true;
                            }
                        }

                        clothesItemsHandler.saveToCSV("/Users/vanessa.pashova/Desktop/Sirma Academy 24/Mini Projects/src/Inventory Management System/src/csvFiles/items/Clothes.csv", clothes);
                    }

                    else
                        throw new IllegalArgumentException(">! Invalid clothing brand given, [InventoryManager, setNewDiscount() --> case CLOTHES].");
                }

                break;
            }

            case "ELECTRONICS": {
                ElectronicItemsHandler electronicItemsHandler = new ElectronicItemsHandler() {};
                TreeMap<String, ElectronicItems> electronics = electronicItemsHandler.loadFromCSV("/Users/vanessa.pashova/Desktop/Sirma Academy 24/Mini Projects/src/Inventory Management System/src/csvFiles/items/Electronics.csv");
                System.out.print("> Insert the which company to have a new discount [0, 1): ");
                String company = scanner.nextLine();

                if(company.equalsIgnoreCase("APPLE") || company.equalsIgnoreCase("PHILIPS") || company.equalsIgnoreCase("SAMSUNG")) {
                    System.out.print("> Insert the new discount [0, 1): ");
                    double newDiscount = Double.parseDouble(scanner.nextLine());

                    if(0 <= newDiscount && newDiscount < 1) {
                        for(Map.Entry<String, ElectronicItems> entry : electronics.entrySet()) {
                            if(entry.getValue().getBrand().toString().equalsIgnoreCase(company)) {
                                entry.getValue().setDiscount(newDiscount);
                                exists = true;
                            }
                        }

                        electronicItemsHandler.saveToCSV("/Users/vanessa.pashova/Desktop/Sirma Academy 24/Mini Projects/src/Inventory Management System/src/csvFiles/items/Electronics.csv", electronics);
                    }

                    else
                        throw new IllegalArgumentException(">! Invalid electronics company given, [InventoryManager, setNewDiscount() --> case ELECTRONICS].");
                }

                break;
            }

            default: throw new IllegalStateException(">! Invalid category of product given, [InventoryManager, setNewDiscount() --> case default].");
        }

        System.out.println(exists ? "------ NEW DISCOUNT SUCCESSFULLY APPLIED ------" : "------ NO DISCOUNT APPLIED ------");
    }

    public void setNewPrice() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("------ NEW PRICE ------");
        System.out.print("> Insert category -> B (for Books) || C (for Clothes) || E (for Electronics) || G (for Groceries): ");
        String choice = scanner.nextLine();
        boolean exists = false;

        switch (choice) {
            case "B": {
                BookItemsHandler bookItemsHandler = new BookItemsHandler() {};
                TreeMap<String, BookItems> books = bookItemsHandler.loadFromCSV("/Users/vanessa.pashova/Desktop/Sirma Academy 24/Mini Projects/src/Inventory Management System/src/csvFiles/items/Books.csv");

                System.out.print("> Insert title: ");
                String title = scanner.nextLine();
                System.out.print("> Insert the new price for this book: ");
                double newPrice = Double.parseDouble(scanner.nextLine());

                for(Map.Entry<String, BookItems> entry : books.entrySet()) {
                    if(entry.getValue().getName().equalsIgnoreCase(title)) {
                        entry.getValue().setPrice(newPrice);
                        exists = true;
                    }
                }

                if(!exists)
                    System.out.println(">! Item with this title was not found, [InventoryManager, setNewPrice() --> case BOOKS].");

                else
                    bookItemsHandler.saveToCSV("/Users/vanessa.pashova/Desktop/Sirma Academy 24/Mini Projects/src/Inventory Management System/src/csvFiles/items/Books.csv", books);

                break;
            }

            case "C": {
                ClothesItemsHandler clothesItemsHandler = new ClothesItemsHandler() {};
                TreeMap<String, ClothingItems> clothes = clothesItemsHandler.loadFromCSV("/Users/vanessa.pashova/Desktop/Sirma Academy 24/Mini Projects/src/Inventory Management System/src/csvFiles/items/Clothes.csv");

                System.out.print("> Insert clothing category (T-Shirt || Jeans || Jackets || Shoes): ");
                String category = scanner.nextLine();
                System.out.print("> Insert clothing size (XS || S || M || L || XL): ");
                String size = scanner.nextLine();
                System.out.print("> Insert price for this clothing: ");
                double price = Double.parseDouble(scanner.nextLine());

                if(("TSHIRT".equalsIgnoreCase(category) || "JEANS".equalsIgnoreCase(category) || "JACKETS".equalsIgnoreCase(category) || "SHOES".equalsIgnoreCase(category)) &&
                    "XS".equalsIgnoreCase(size) || "S".equalsIgnoreCase(size) || "M".equalsIgnoreCase(size) || "XL".equalsIgnoreCase(size)) {
                    for(Map.Entry<String, ClothingItems> entry : clothes.entrySet()) {
                        if(entry.getValue().getName().equalsIgnoreCase(category) && entry.getValue().getSize().equalsIgnoreCase(size)) {
                            entry.getValue().setPrice(price);
                            exists = true;
                        }
                    }

                    clothesItemsHandler.saveToCSV("/Users/vanessa.pashova/Desktop/Sirma Academy 24/Mini Projects/src/Inventory Management System/src/csvFiles/items/Clothes.csv", clothes);
                }

                else
                    throw new IllegalStateException(">! Invalid (clothing category || size) given, [InventoryManager, setNewPrice() --> case CLOTHES].");

                break;
            }

            case "E": {
                ElectronicItemsHandler electronicsHandler = new ElectronicItemsHandler() {};
                TreeMap<String, ElectronicItems> electronics = electronicsHandler.loadFromCSV("/Users/vanessa.pashova/Desktop/Sirma Academy 24/Mini Projects/src/Inventory Management System/src/csvFiles/items/Electronics.csv");

                System.out.print("> Insert the name of the electronic item: ");
                String name = scanner.nextLine();
                System.out.print("> Insert the brand company of the electronic item: ");
                String company = scanner.nextLine();
                System.out.print("> Insert the new price of the electronic item: ");
                double price = Double.parseDouble(scanner.nextLine());

                if("APPLE".equalsIgnoreCase(name) || "PHILIPS".equalsIgnoreCase(name) || "SAMSUNG".equalsIgnoreCase(name)) {
                    for(Map.Entry<String, ElectronicItems> entry : electronics.entrySet()) {
                        if(entry.getValue().getBrand().toString().equalsIgnoreCase(company) && entry.getValue().getName().equalsIgnoreCase(name)) {
                            entry.getValue().setPrice(price);
                            exists = true;
                        }
                    }

                    electronicsHandler.saveToCSV("/Users/vanessa.pashova/Desktop/Sirma Academy 24/Mini Projects/src/Inventory Management System/src/csvFiles/items/Electronics.csv", electronics);
                }

                else
                    throw new IllegalArgumentException(">! Invalid (name || brand company) inserted, [InventoryManager, setNewPrice() --> case ELECTRONICS].");

                break;
            }

            case "G": {
                GroceryItemsHandler groceryItemsHandler = new GroceryItemsHandler() {};
                TreeMap<String, GroceryItems> groceries = groceryItemsHandler.loadFromCSV("/Users/vanessa.pashova/Desktop/Sirma Academy 24/Mini Projects/src/Inventory Management System/src/csvFiles/items/GroceryItems.csv");

                System.out.print("> Insert the name of the grocery item: ");
                String name = scanner.nextLine();
                System.out.print("> Insert the new price of the grocery item: ");
                double price = Double.parseDouble(scanner.nextLine());

                for(Map.Entry<String, GroceryItems> entry : groceries.entrySet()) {
                    if(entry.getValue().getName().equalsIgnoreCase(name)) {
                        entry.getValue().setPrice(price);
                        exists = true;
                    }
                }

                if(exists)
                    groceryItemsHandler.saveToCSV("/Users/vanessa.pashova/Desktop/Sirma Academy 24/Mini Projects/src/Inventory Management System/src/csvFiles/items/GroceryItems.csv", groceries);

                else
                    throw new IllegalArgumentException(">! Invalid name inserted, [InventoryManager, setNewPrice() --> case GROCERIES].");

                break;
            }

            default: {
                throw new IllegalArgumentException(">! Invalid product category, [InventoryManager, setNewPrice() --> case default].");
            }
        }
    }

    public AbstractItem findItem(String id) {
        if (!inventory.containsKey(id))
            throw new IllegalArgumentException(">! Item does not exist [InventoryManager, findItem()].");

        return inventory.get(id);
    }

    public TreeMap<String, AbstractItem> filterByCategory(Categorizable.CategorizableType category) {
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

    public void clearInventory() {
        inventory.clear();
        System.out.println("----- INVENTORY CLEARED ------");
    }
}
