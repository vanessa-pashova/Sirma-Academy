package models.handlers_for_the_items;

import models.items.ClothingItems;

import java.io.*;
import java.util.TreeMap;

public class ClothesItemsHandler extends AbstractCSVHandler<ClothingItems> {
    @Override
    public TreeMap<String, ClothingItems> loadFromCSV(String filename) {
        if (filename == null || filename.isEmpty())
            throw new IllegalArgumentException(">! Filename cannot be null or empty, [ClothesItemsHandler, loadFromCSV()].");

        File file = new File(filename);
        if (!file.exists()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write("ID|Name|Price|Discount|Category|Brand|Size|Color|Details");
                writer.newLine();
            } catch (IOException e) {
                System.out.print(">! Error while creating Clothes.csv, [ClothesItemsHandler, loadFromCSV()]. ");
                System.out.println(e.getMessage());
            }
            System.out.println("[ Clothes.csv created -> ClothesItemsHandler, loadFromCSV()].");
        }

        TreeMap<String, ClothingItems> items = new TreeMap<>();
        int maxID = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine(); // Skip the header

            while ((line = reader.readLine()) != null) {
                String[] components = line.split("\\|");
                String id = components[0];
                String name = components[1];
                double price = Double.parseDouble(components[2]);
                double discount = Double.parseDouble(components[3]);
                String brand = components[5];
                String size = components[6];
                String color = components[7];
                String details = components[8];

                ClothingItems item = new ClothingItems(id, name, discount, price, brand, size, color, details);
                items.put(id, item);

                // Update the maximum ID
                int currentID = Integer.parseInt(id.split("-")[1]);
                if (currentID > maxID) {
                    maxID = currentID;
                }
            }
        } catch (IOException e) {
            System.out.print(">! Error while loading Clothes.csv, [ClothesItemsHandler, loadFromCSV()]. ");
            System.out.println(e.getMessage());
        }

        // Update the clothing ID counter
        ClothingItems.updateClothingIDCounter(maxID);
        return items;
    }

    @Override
    public void saveToCSV(String filename, TreeMap<String, ClothingItems> items) {
        if (filename == null || filename.isEmpty())
            throw new IllegalArgumentException(">! Filename cannot be null or empty, [ClothesItemsHandler, saveToCSV()].");

        File file = new File(filename);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write("ID|Name|Price|Discount|Category|Brand|Size|Color|Details");
            writer.newLine();

            items.forEach((id, item) -> {
                try {
                    writer.write(id + "|" + item.getName() + "|" + item.getPrice() + "|" + item.getDiscount() + "|" + item.getClothesCategory()
                            + "|" + item.getBrand() + "|" + item.getSize() + "|" + item.getColor() + "|" + item.getItemDetails());
                    writer.newLine();
                } catch (IOException e) {
                    System.out.print(">! Error while saving Clothes.csv. [ClothesItemsHandler, saveToCSV()]. ");
                    System.out.println(e.getMessage());
                }
            });
        } catch (IOException e) {
            System.out.print(">! Error while saving Clothes.csv, [ClothesItemsHandler, saveToCSV()]. ");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void removeFromCSV(String filename, String id) {
        if (filename == null || filename.isEmpty())
            throw new IllegalArgumentException(">! Filename cannot be null or empty, [ClothesItemsHandler, removeFromCSV()].");

        TreeMap<String, ClothingItems> items = loadFromCSV(filename);
        if (items.containsKey(id)) {
            items.remove(id);
            saveToCSV(filename, items);
        }

        else
            throw new IllegalArgumentException(">! Item does not exist, [ClothesItemsHandler, removeFromCSV()].");
    }
}
