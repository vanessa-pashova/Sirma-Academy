package models.handlers_for_the_items;

import models.items.GroceryItems;

import java.io.*;
import java.util.TreeMap;

public class GroceryItemsHandler extends AbstractCSVHandler<GroceryItems> {
    @Override
    public TreeMap<String, GroceryItems> loadFromCSV(String filename) {
        if (filename == null || filename.isEmpty())
            throw new IllegalArgumentException(">! Filename cannot be null or empty, [GroceryItemsHandler, loadFromCSV()].");

        File file = new File(filename);
        if (!file.exists()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write("ID|Name|Price|Discount|Description|Weight|Calories|CreationDate|ExpiryDate");
                writer.newLine();
            } catch (IOException e) {
                throw new RuntimeException(">! Error while creating Groceries.csv, [GroceryItemsHandler, loadFromCSV()].", e);
            }
            System.out.println("[ Groceries.csv created -> GroceryItemsHandler, loadFromCSV()].");
        }

        TreeMap<String, GroceryItems> items = new TreeMap<>();
        int maxID = 0; // Track the highest ID

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine(); // Skip header

            while ((line = reader.readLine()) != null) {
                try {
                    String[] components = line.split("\\|");
                    String id = components[0];
                    String name = components[1];
                    double price = Double.parseDouble(components[2]);
                    double discount = Double.parseDouble(components[3]);
                    String description = components[4];
                    double weight = Double.parseDouble(components[5]);
                    int calories = Integer.parseInt(components[6]);
                    String creationDate = components[7];
                    String expiryDate = components[8];

                    GroceryItems item = new GroceryItems(id, name, discount, price, description, weight, calories, creationDate, expiryDate);
                    items.put(id, item);

                    // Update maxID based on the current item's ID
                    int currentID = Integer.parseInt(id.split("-")[1]);
                    if (currentID > maxID) {
                        maxID = currentID;
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println(">! Error reading CSV line: " + line + " [GroceryItemsHandler, loadFromCSV()].");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(">! Error while loading Groceries.csv, [GroceryItemsHandler, loadFromCSV()].", e);
        }

        // Update static ID counter to ensure consistency
        GroceryItems.updateGroceryIDCounter(maxID);
        return items;
    }

    @Override
    public void saveToCSV(String filename, TreeMap<String, GroceryItems> items) {
        if (filename == null || filename.isEmpty())
            throw new IllegalArgumentException(">! Filename cannot be null or empty, [GroceryItemsHandler, saveToCSV()].");

        File file = new File(filename);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write("ID|Name|Price|Discount|Description|Weight|Calories|CreationDate|ExpiryDate");
            writer.newLine();

            items.forEach((id, item) -> {
                try {
                    writer.write(id + "|" + item.getName() + "|" + item.getPrice() + "|" +
                            item.getDiscount() + "|" + item.getItemDescription() + "|" +
                            item.getWeight() + "|" + item.getCalories() + "|" +
                            item.getCreationDate() + "|" + item.getExpiryDate());
                    writer.newLine();
                } catch (IOException e) {
                    System.out.println(">! Error while writing to Groceries.csv [GroceryItemsHandler, saveToCSV()].");
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(">! Error while saving Groceries.csv, [GroceryItemsHandler, saveToCSV()].", e);
        }
    }

    @Override
    public void removeFromCSV(String filename, String id) {
        if (filename == null || filename.isEmpty())
            throw new IllegalArgumentException(">! Filename cannot be null or empty, [GroceryItemsHandler, removeFromCSV()].");

        TreeMap<String, GroceryItems> items = loadFromCSV(filename);
        if (items.remove(id) != null)
            saveToCSV(filename, items);

        else
            throw new IllegalArgumentException(">! Item does not exist, [GroceryItemsHandler, removeFromCSV()].");
    }
}