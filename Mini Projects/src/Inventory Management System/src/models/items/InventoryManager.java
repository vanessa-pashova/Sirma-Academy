package models.items;

import models.handlers_for_the_items.*;

import java.util.*;

public class InventoryManager{
    private TreeMap<String, AbstractItem> inventory;
    private final String pathToDir = "/Users/vanessa.pashova/Desktop/Sirma Academy 24/Mini Projects/src/Inventory Management System/src/csv_files/items/";

    private String getFilePathForItem(AbstractItem item) {
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

    public void loadInventoryFromCSV() {
        BookItemsHandler bookHandler = new BookItemsHandler() {};
        TreeMap<String, BookItems> books = bookHandler.loadFromCSV(pathToDir + "Books.csv");
        this.inventory.putAll(books);

        ClothesItemsHandler clothesHandler = new ClothesItemsHandler() {};
        TreeMap<String, ClothingItems> clothing = clothesHandler.loadFromCSV(pathToDir + "Clothes.csv");
        this.inventory.putAll(clothing);

        ElectronicItemsHandler electronicHandler = new ElectronicItemsHandler() {};
        TreeMap<String, ElectronicItems> electronics = electronicHandler.loadFromCSV(pathToDir + "Electronics.csv");
        this.inventory.putAll(electronics);

        GroceryItemsHandler groceryHandler = new GroceryItemsHandler() {};
        TreeMap<String, GroceryItems> groceries = groceryHandler.loadFromCSV(pathToDir + "Groceries.csv");
        this.inventory.putAll(groceries);
    }

    private void updateIDCounters() {
        // Load all CSV files and find the maximum ID for each type
        BookItemsHandler bookHandler = new BookItemsHandler();
        ClothesItemsHandler clothesHandler = new ClothesItemsHandler();
        ElectronicItemsHandler electronicHandler = new ElectronicItemsHandler();
        GroceryItemsHandler groceryHandler = new GroceryItemsHandler();

        TreeMap<String, BookItems> books = bookHandler.loadFromCSV("path_to_books.csv");
        TreeMap<String, ClothingItems> clothes = clothesHandler.loadFromCSV("path_to_clothes.csv");
        TreeMap<String, ElectronicItems> electronics = electronicHandler.loadFromCSV("path_to_electronics.csv");
        TreeMap<String, GroceryItems> groceries = groceryHandler.loadFromCSV("path_to_groceries.csv");

        // Update counters based on the maximum ID found
        BookItems.updateBookIDCounter(getMaxID(books.keySet()));
        ClothingItems.updateClothingIDCounter(getMaxID(clothes.keySet()));
        ElectronicItems.updateElectronicIDCounter(getMaxID(electronics.keySet()));
        GroceryItems.updateGroceryIDCounter(getMaxID(groceries.keySet()));
    }

    // Helper method to extract the maximum numeric part of IDs
    private int getMaxID(Set<String> ids) {
        return ids.stream()
                .mapToInt(id -> Integer.parseInt(id.split("-")[1]))
                .max()
                .orElse(0);
    }

    private void saveItemToCSV(AbstractItem item) {
        if (item instanceof BookItems) {
            BookItemsHandler handler = new BookItemsHandler();
            TreeMap<String, BookItems> books = handler.loadFromCSV("/Users/vanessa.pashova/Desktop/Sirma Academy 24/Mini Projects/src/Inventory Management System/src/csv_files/items/Books.csv");
            books.put(item.getID(), (BookItems) item);
            handler.saveToCSV("/Users/vanessa.pashova/Desktop/Sirma Academy 24/Mini Projects/src/Inventory Management System/src/csv_files/items/Books.csv", books);
        }

        else if (item instanceof ClothingItems) {
            ClothesItemsHandler handler = new ClothesItemsHandler();
            TreeMap<String, ClothingItems> clothes = handler.loadFromCSV("/Users/vanessa.pashova/Desktop/Sirma Academy 24/Mini Projects/src/Inventory Management System/src/csv_files/items/Clothes.csv");
            clothes.put(item.getID(), (ClothingItems) item);
            handler.saveToCSV("/Users/vanessa.pashova/Desktop/Sirma Academy 24/Mini Projects/src/Inventory Management System/src/csv_files/items/Clothes.csv", clothes);
        }

        else if (item instanceof ElectronicItems) {
            ElectronicItemsHandler handler = new ElectronicItemsHandler();
            TreeMap<String, ElectronicItems> electronics = handler.loadFromCSV("/Users/vanessa.pashova/Desktop/Sirma Academy 24/Mini Projects/src/Inventory Management System/src/csv_files/items/Electronics.csv");
            electronics.put(item.getID(), (ElectronicItems) item);
            handler.saveToCSV("/Users/vanessa.pashova/Desktop/Sirma Academy 24/Mini Projects/src/Inventory Management System/src/csv_files/items/Electronics.csv", electronics);
        }

        else if (item instanceof GroceryItems) {
            GroceryItemsHandler handler = new GroceryItemsHandler();
            TreeMap<String, GroceryItems> groceries = handler.loadFromCSV("/Users/vanessa.pashova/Desktop/Sirma Academy 24/Mini Projects/src/Inventory Management System/src/csv_files/items/Groceries.csv");
            groceries.put(item.getID(), (GroceryItems) item);
            handler.saveToCSV("/Users/vanessa.pashova/Desktop/Sirma Academy 24/Mini Projects/src/Inventory Management System/src/csv_files/items/Groceries.csv", groceries);
        }
    }

    public void removeFromCSV(AbstractItem item) {
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

    public InventoryManager() {
        this.inventory = new TreeMap<>();
        this.loadInventoryFromCSV();
    }

    public TreeMap<String, AbstractItem> getInventory() {
        return this.inventory;
    }

    public double getTotalValue() {
        return inventory.values().stream().mapToDouble(AbstractItem::getPrice).sum();
    }

    public int getTotalQuantity() {
        return inventory.size();
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

    private void addBookItem(Scanner scanner, int index) {
        System.out.print("> Insert item[" + (index + 1) + "] name: ");
        String name = scanner.nextLine();
        System.out.print("> Insert item[" + (index + 1) + "] price: ");
        double price = Double.parseDouble(scanner.nextLine());
        System.out.print("> Insert item[" + (index + 1) + "] discount: ");
        double discount = Double.parseDouble(scanner.nextLine());
        System.out.print("> Insert item[" + (index + 1) + "] author: ");
        String author = scanner.nextLine();
        System.out.print("> Insert item[" + (index + 1) + "] genre: ");
        String genre = scanner.nextLine();
        System.out.print("> Insert item[" + (index + 1) + "] total pages: ");
        int totalPages = Integer.parseInt(scanner.nextLine());
        System.out.print("> Insert item[" + (index + 1) + "] publisher: ");
        String publisher = scanner.nextLine();
        System.out.print("> Insert item[" + (index + 1) + "] description: ");
        String description = scanner.nextLine();

        BookItems book = new BookItems(name, discount, price, author, genre, totalPages, publisher, description);
        this.inventory.put(book.getID(), book);
        saveItemToCSV(book);
    }

    private void addClothingItem(Scanner scanner, int index) {
        System.out.print("> Insert item[" + (index + 1) + "] name: ");
        String name = scanner.nextLine();
        System.out.print("> Insert item[" + (index + 1) + "] price: ");
        double price = Double.parseDouble(scanner.nextLine());
        System.out.print("> Insert item[" + (index + 1) + "] discount: ");
        double discount = Double.parseDouble(scanner.nextLine());
        System.out.print("> Insert item[" + (index + 1) + "] brand: ");
        String brand = scanner.nextLine();
        System.out.print("> Insert item[" + (index + 1) + "] size: ");
        String size = scanner.nextLine();
        System.out.print("> Insert item[" + (index + 1) + "] color: ");
        String color = scanner.nextLine();
        System.out.print("> Insert item[" + (index + 1) + "] details: ");
        String details = scanner.nextLine();

        ClothingItems clothing = new ClothingItems(name, discount, price, brand, size, color, details);
        this.inventory.put(clothing.getID(), clothing);
        saveItemToCSV(clothing);
    }

    private void addElectronicItem(Scanner scanner, int index) {
        System.out.print("> Insert item[" + (index + 1) + "] name: ");
        String name = scanner.nextLine();
        System.out.print("> Insert item[" + (index + 1) + "] price: ");
        double price = Double.parseDouble(scanner.nextLine());
        System.out.print("> Insert item[" + (index + 1) + "] discount: ");
        double discount = Double.parseDouble(scanner.nextLine());
        System.out.print("> Insert item[" + (index + 1) + "] brand: ");
        String brand = scanner.nextLine();
        System.out.print("> Insert item[" + (index + 1) + "] warranty: ");
        int warranty = Integer.parseInt(scanner.nextLine());
        System.out.print("> Insert item[" + (index + 1) + "] details: ");
        String details = scanner.nextLine();

        ElectronicItems electronics = new ElectronicItems(name, discount, price, brand, warranty, details);
        this.inventory.put(electronics.getID(), electronics);
        saveItemToCSV(electronics);
    }

    private void addGroceryItem(Scanner scanner, int index) {
        System.out.print("> Insert item[" + (index + 1) + "] name: ");
        String name = scanner.nextLine();
        System.out.print("> Insert item[" + (index + 1) + "] price: ");
        double price = Double.parseDouble(scanner.nextLine());
        System.out.print("> Insert item[" + (index + 1) + "] discount: ");
        double discount = Double.parseDouble(scanner.nextLine());
        System.out.print("> Insert item[" + (index + 1) + "] description: ");
        String description = scanner.nextLine();
        System.out.print("> Insert item[" + (index + 1) + "] weight: ");
        double weight = Double.parseDouble(scanner.nextLine());
        System.out.print("> Insert item[" + (index + 1) + "] calories: ");
        int calories = Integer.parseInt(scanner.nextLine());
        System.out.print("> Insert item[" + (index + 1) + "] creation date (DD-MM-YYYY): ");
        String creationDate = scanner.nextLine();
        System.out.print("> Insert item[" + (index + 1) + "] expiration date (DD-MM-YYYY): ");
        String expirationDate = scanner.nextLine();

        GroceryItems groceries = new GroceryItems(name, discount, price, description, weight, calories, creationDate, expirationDate);
        this.inventory.put(groceries.getID(), groceries);
        saveItemToCSV(groceries);
    }

    public void loadNewItems() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("------ LOADING NEW ITEMS ------");
        System.out.print("> Number of items to load: ");
        int numberOfItems = Integer.parseInt(scanner.nextLine());

        if (numberOfItems <= 0) {
            System.out.println(">! No items to load.");
            return;
        }

        for (int i = 0; i < numberOfItems; i++) {
            System.out.print("> Insert item category (BOOKS, CLOTHES, ELECTRONICS, GROCERIES): ");
            String category = scanner.nextLine();

            switch (category.toUpperCase()) {
                case "BOOKS":
                    addBookItem(scanner, i);
                    break;
                case "CLOTHES":
                    addClothingItem(scanner, i);
                    break;
                case "ELECTRONICS":
                    addElectronicItem(scanner, i);
                    break;
                case "GROCERIES":
                    addGroceryItem(scanner, i);
                    break;
                default:
                    System.out.println(">! Invalid category. Skipping item.");
            }
        }
        System.out.println("------ END OF LOADING ITEMS ------");
    }


    public void setNewDiscount() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("------ NEW DISCOUNT ------");
        System.out.print("> Insert the category of the product (BOOKS || CLOTHES || ELECTRONICS || GROCERIES): ");
        String category = scanner.nextLine();

        boolean exists = false;

        switch (category.toUpperCase()) {
            case "BOOKS": {
                BookItemsHandler bookItemsHandler = new BookItemsHandler();
                TreeMap<String, BookItems> books = bookItemsHandler.loadFromCSV("/Users/vanessa.pashova/Desktop/Sirma Academy 24/Mini Projects/src/Inventory Management System/src/csv_files/items/Books.csv");

                System.out.println("------ CHOICE: ALL OR PARTICULAR ONES (CHOSEN BY ID OR GENRE) ------");
                System.out.print("> Type A (for all books) || Type P (for particular ones by ID): ");
                String choice = scanner.nextLine();

                if (choice.equalsIgnoreCase("A")) {
                    System.out.print("> Insert the new discount [0, 1): ");
                    double newDiscount = Double.parseDouble(scanner.nextLine());

                    if (0 <= newDiscount && newDiscount < 1) {
                        for (BookItems book : books.values()) {
                            book.setDiscount(newDiscount);
                            exists = true;
                        }

                        bookItemsHandler.saveToCSV("/Users/vanessa.pashova/Desktop/Sirma Academy 24/Mini Projects/src/Inventory Management System/src/csv_files/items/Books.csv", books);
                    }
                }

                else if (choice.equalsIgnoreCase("P")) {
                    System.out.print("> Insert the ID of the book: ");
                    String id = scanner.nextLine();

                    if (books.containsKey(id)) {
                        System.out.print("> Insert the new discount [0, 1): ");
                        double newDiscount = Double.parseDouble(scanner.nextLine());

                        if (0 <= newDiscount && newDiscount < 1) {
                            books.get(id).setDiscount(newDiscount);
                            exists = true;

                            bookItemsHandler.saveToCSV("/Users/vanessa.pashova/Desktop/Sirma Academy 24/Mini Projects/src/Inventory Management System/src/csv_files/items/Books.csv", books);
                        }
                    }

                    else
                        throw new IllegalArgumentException(">! Book with this ID was not found, [InventoryManager, setNewDiscount() --> case BOOKS].");
                }

                break;
            }

            case "CLOTHES": {
                ClothesItemsHandler clothesItemsHandler = new ClothesItemsHandler();
                TreeMap<String, ClothingItems> clothes = clothesItemsHandler.loadFromCSV("/Users/vanessa.pashova/Desktop/Sirma Academy 24/Mini Projects/src/Inventory Management System/src/csv_files/items/Clothes.csv");

                System.out.print("> Insert the ID of the clothing item: ");
                String id = scanner.nextLine();

                if (clothes.containsKey(id)) {
                    System.out.print("> Insert the new discount [0, 1): ");
                    double newDiscount = Double.parseDouble(scanner.nextLine());

                    if (0 <= newDiscount && newDiscount < 1) {
                        for (ClothingItems clothing : clothes.values()) {
                            if (clothing.getID().equalsIgnoreCase(id)) {
                                clothing.setDiscount(newDiscount);
                                exists = true;
                            }
                        }

                        clothesItemsHandler.saveToCSV("/Users/vanessa.pashova/Desktop/Sirma Academy 24/Mini Projects/src/Inventory Management System/src/csv_files/items/Clothes.csv", clothes);
                    }
                }

                else
                    throw new IllegalArgumentException(">! Clothing item with this ID was not found, [InventoryManager, setNewDiscount() --> case CLOTHES].");

                break;
            }

            case "ELECTRONICS": {
                ElectronicItemsHandler electronicItemsHandler = new ElectronicItemsHandler();
                TreeMap<String, ElectronicItems> electronics = electronicItemsHandler.loadFromCSV("/Users/vanessa.pashova/Desktop/Sirma Academy 24/Mini Projects/src/Inventory Management System/src/csv_files/items/Electronics.csv");

                System.out.print("> Insert the ID of the electronic item: ");
                String id = scanner.nextLine();

                if (electronics.containsKey(id)) {
                    System.out.print("> Insert the new discount [0, 1): ");
                    double newDiscount = Double.parseDouble(scanner.nextLine());

                    if (0 <= newDiscount && newDiscount < 1) {
                        for (ElectronicItems electronic : electronics.values()) {
                            if (electronic.getID().equalsIgnoreCase(id)) {
                                electronic.setDiscount(newDiscount);
                                exists = true;
                            }
                        }

                        electronicItemsHandler.saveToCSV("/Users/vanessa.pashova/Desktop/Sirma Academy 24/Mini Projects/src/Inventory Management System/src/csv_files/items/Electronics.csv", electronics);
                    }
                }

                else
                    throw new IllegalArgumentException(">! Electronic item with this ID was not found, [InventoryManager, setNewDiscount() --> case ELECTRONICS].");

                break;
            }

            case "GROCERIES": {
                GroceryItemsHandler groceryItemsHandler = new GroceryItemsHandler();
                TreeMap<String, GroceryItems> groceries = groceryItemsHandler.loadFromCSV("/Users/vanessa.pashova/Desktop/Sirma Academy 24/Mini Projects/src/Inventory Management System/src/csv_files/items/Groceries.csv");

                System.out.print("> Insert the ID of the grocery item: ");
                String id = scanner.nextLine();

                if (groceries.containsKey(id)) {
                    System.out.print("> Insert the new discount [0, 1): ");
                    double newDiscount = Double.parseDouble(scanner.nextLine());

                    if (0 <= newDiscount && newDiscount < 1) {
                        groceries.get(id).setDiscount(newDiscount);
                        groceryItemsHandler.saveToCSV("/Users/vanessa.pashova/Desktop/Sirma Academy 24/Mini Projects/src/Inventory Management System/src/csv_files/items/Groceries.csv", groceries);
                        exists = true;
                    }
                }

                else
                    throw new IllegalArgumentException(">! Grocery item with this ID was not found, [InventoryManager, setNewDiscount() --> case GROCERIES].");

                break;
            }

            default:
                throw new IllegalStateException(">! Invalid category of product given, [InventoryManager, setNewDiscount() --> case default].");
        }
    }

    public void setNewPrice() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("------ NEW PRICE ------");
        System.out.print("> Insert category -> B (for Books) || C (for Clothes) || E (for Electronics) || G (for Groceries): ");
        String choice = scanner.nextLine();
        boolean exists = false;

        switch (choice.toUpperCase()) {
            case "B": {
                BookItemsHandler bookItemsHandler = new BookItemsHandler();
                TreeMap<String, BookItems> books = bookItemsHandler.loadFromCSV("/Users/vanessa.pashova/Desktop/Sirma Academy 24/Mini Projects/src/Inventory Management System/src/csv_files/items/Books.csv");

                System.out.print("> Insert the ID of the book: ");
                String id = scanner.nextLine().trim();
                System.out.print("> Insert the new price for this book: ");
                double newPrice = Double.parseDouble(scanner.nextLine().trim());

                if (books.containsKey(id)) {
                    books.get(id).setPrice(newPrice);
                    bookItemsHandler.saveToCSV("/Users/vanessa.pashova/Desktop/Sirma Academy 24/Mini Projects/src/Inventory Management System/src/csv_files/items/Books.csv", books);
                    exists = true;
                }

                if(!exists)
                    System.out.println(">! Item with this title was not found, [InventoryManager, setNewPrice() --> case BOOKS].");

                break;
            }

            case "C": {
                ClothesItemsHandler clothesItemsHandler = new ClothesItemsHandler() {};
                TreeMap<String, ClothingItems> clothes = clothesItemsHandler.loadFromCSV("/Users/vanessa.pashova/Desktop/Sirma Academy 24/Mini Projects/src/Inventory Management System/src/csv_files/items/Clothes.csv");

                System.out.print("> Insert the ID of the clothing item: ");
                String id = scanner.nextLine().trim();
                System.out.print("> Insert the new price for this clothing item: ");
                double newPrice = Double.parseDouble(scanner.nextLine().trim());

                if (clothes.containsKey(id)) {
                    clothes.get(id).setPrice(newPrice);
                    clothesItemsHandler.saveToCSV("/Users/vanessa.pashova/Desktop/Sirma Academy 24/Mini Projects/src/Inventory Management System/src/csv_files/items/Clothes.csv", clothes);
                    exists = true;
                }

                if(!exists)
                    throw new IllegalStateException(">! Item with this ID was not found, [InventoryManager, setNewPrice() --> case CLOTHES].");

                break;
            }

            case "E": {
                ElectronicItemsHandler electronicsHandler = new ElectronicItemsHandler();
                TreeMap<String, ElectronicItems> electronics = electronicsHandler.loadFromCSV("/Users/vanessa.pashova/Desktop/Sirma Academy 24/Mini Projects/src/Inventory Management System/src/csv_files/items/Electronics.csv");

                System.out.print("> Insert the ID of the electronic item: ");
                String id = scanner.nextLine().trim();
                System.out.print("> Insert the new price for this electronic item: ");
                double newPrice = Double.parseDouble(scanner.nextLine().trim());

                if (electronics.containsKey(id)) {
                    electronics.get(id).setPrice(newPrice);
                    electronicsHandler.saveToCSV("/Users/vanessa.pashova/Desktop/Sirma Academy 24/Mini Projects/src/Inventory Management System/src/csv_files/items/Electronics.csv", electronics);
                    exists = true;
                }

                if(!exists)
                    throw new IllegalArgumentException(">! Item with this ID was not found, [InventoryManager, setNewPrice() --> case ELECTRONICS].");

                break;
            }

            case "G": {
                GroceryItemsHandler groceryItemsHandler = new GroceryItemsHandler() {};
                TreeMap<String, GroceryItems> groceries = groceryItemsHandler.loadFromCSV("/Users/vanessa.pashova/Desktop/Sirma Academy 24/Mini Projects/src/Inventory Management System/src/csv_files/items/Groceries.csv");

                System.out.print("> Insert the ID of the grocery item: ");
                String id = scanner.nextLine().trim();
                System.out.print("> Insert the new price of the grocery item: ");
                double price = Double.parseDouble(scanner.nextLine().trim());

                if (groceries.containsKey(id)) {
                    groceries.get(id).setPrice(price); // Update the price
                    groceryItemsHandler.saveToCSV("/Users/vanessa.pashova/Desktop/Sirma Academy 24/Mini Projects/src/Inventory Management System/src/csv_files/items/Groceries.csv", groceries);
                    exists = true;
                }

                if (!exists)
                    throw new IllegalArgumentException(">! Item with this ID was not found, [InventoryManager, setNewPrice() --> case GROCERIES].");

                break;
            }

            default: throw new IllegalArgumentException(">! Invalid id given, [InventoryManager, setNewPrice() --> case default].");
        }
    }

    public void updateInventory() {
        System.out.println("------ UPDATING INVENTORY ------");
        System.out.print("> Insert an action (load || discount || price): ");
        Scanner scanner = new Scanner(System.in);

        String action = scanner.nextLine();

        switch (action.toLowerCase()) {
            case "load":
                loadNewItems();
                break;

            case "discount":
                setNewDiscount();
                break;

            case "price":
                setNewPrice();
                break;

            default:
                throw new IllegalArgumentException(">! Invalid action: " + action + ", [Admin, updateInventory()].");
        }
    }


    public AbstractItem findItem(String id) {
        if (!inventory.containsKey(id))
            throw new IllegalArgumentException(">! Item does not exist [InventoryManager, findItem()].");

        return inventory.get(id);
    }

    public TreeMap<String, AbstractItem> filterByCategory(String category) {
        TreeMap<String, AbstractItem> filteredItems = new TreeMap<>();

        inventory.forEach((id, item) -> {
            if (item.getCategory().equalsIgnoreCase(category))
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

    public void printInventory() {
        inventory.forEach((id, item) -> {
            item.printDetails();
        });
    }

    public void clearInventory() {
        this.inventory.clear();
        System.out.println("----- INVENTORY CLEARED ------");
    }
}
