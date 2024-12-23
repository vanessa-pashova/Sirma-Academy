package models.handlers_for_the_items;

import models.items.ElectronicItems;

import java.io.*;
import java.util.TreeMap;

public class ElectronicItemsHandler extends AbstractCSVHandler<ElectronicItems> {
    @Override
    public TreeMap<String, ElectronicItems> loadFromCSV(String filename) {
        if (filename == null || filename.isEmpty())
            throw new IllegalArgumentException(">! Filename cannot be null or empty, [ElectronicItemsHandler, loadFromCSV()]");

        File file = new File(filename);
        if (!file.exists()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write("ID|Name|Price|Discount|Brand|Warranty|Details");
                writer.newLine();
            } catch (IOException e) {
                System.out.print(">! Error while creating Electronics.csv, [ElectronicItemsHandler, loadFromCSV()]. ");
                System.out.println(e.getMessage());
            }
            System.out.println("[ Electronics.csv created -> ElectronicItemsHandler, loadFromCSV()].");
        }

        TreeMap<String, ElectronicItems> items = new TreeMap<>();
        int maxID = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine(); // Skip header

            while ((line = reader.readLine()) != null) {
                String[] components = line.split("\\|");
                String id = components[0];
                String name = components[1];
                double price = Double.parseDouble(components[2]);
                double discount = Double.parseDouble(components[3]);
                String brand = components[4];
                int warranty = Integer.parseInt(components[5]);
                String details = components[6];

                ElectronicItems item = new ElectronicItems(id, name, discount, price, brand, warranty, details);
                items.put(id, item);

                // Extract the numeric part of the ID and find the max
                int currentID = Integer.parseInt(id.split("-")[1]);
                if (currentID > maxID)
                    maxID = currentID;
            }
        } catch (IOException e) {
            System.out.print(">! Error while loading Electronics.csv, [ElectronicItemsHandler, loadFromCSV()]. ");
            System.out.println(e.getMessage());
        }

        // Update the static ID counter based on the maximum ID found
        ElectronicItems.updateElectronicIDCounter(maxID);
        return items;
    }

    @Override
    public void saveToCSV(String filename, TreeMap<String, ElectronicItems> items) {
        if (filename == null || filename.isEmpty())
            throw new IllegalArgumentException(">! Filename cannot be null or empty, [ElectronicItemsHandler, saveToCSV()].");

        File file = new File(filename);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write("ID|Name|Price|Discount|Brand|Warranty|Details");
            writer.newLine();

            items.forEach((id, item) -> {
                try {
                    writer.write(id + "|" + item.getName() + "|" + item.getPrice() + "|" + item.getDiscount() + "|" +
                            item.getBrand() + "|" + item.getWarrantyPeriod() + "|" + item.getItemDetails());
                    writer.newLine();
                } catch (IOException e) {
                    System.out.print(">! Error while writing Electronics.csv, [ElectronicItemsHandler, saveToCSV()]. ");
                    System.out.println(e.getMessage());
                }
            });
        } catch (IOException e) {
            System.out.print(">! Error while saving Electronics.csv, [ElectronicItemsHandler, saveToCSV()]. ");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void removeFromCSV(String filename, String id) {
        if (filename == null || filename.isEmpty())
            throw new IllegalArgumentException(">! Filename cannot be null or empty, [ElectronicItemsHandler, removeFromCSV()].");

        TreeMap<String, ElectronicItems> items = loadFromCSV(filename);
        if (items.containsKey(id)) {
            items.remove(id);
            this.saveToCSV(filename, items);
        }

        else
            throw new IllegalArgumentException(">! Item does not exist, [ElectronicItemsHandler, removeFromCSV()].");
    }
}