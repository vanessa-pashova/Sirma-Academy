package models.handlers_for_the_items;

import models.items.ElectronicItems;

import java.io.*;
import java.util.TreeMap;

public abstract class ElectronicItemsHandler extends AbstractCSVHandler<ElectronicItems> {
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
                System.out.print(">! Error while creating a loading file Electronics.csv, [ElectronicItemsHandler, loadFromCSV()]. ");
                System.out.println(e.getMessage());
            }
            System.out.println("[ Electronics.csv created -> ElectronicItemsHandler, loadFromCSV()]");
        }

        TreeMap<String, ElectronicItems> items = new TreeMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] components = line.split("\\|");
                String id = components[0];
                String name = components[1];
                double price = Double.parseDouble(components[2]);
                double discount = Double.parseDouble(components[3]);
                String brand = components[4];
                int warranty = Integer.parseInt(components[5]);
                String details = components[6];

                ElectronicItems item = new ElectronicItems(name, discount, price, brand, warranty, details);
                items.put(id, item);
            }
        } catch (IOException e) {
            System.out.print(">! Error while loading Electronics.csv, [ElectronicItemsHandler, loadFromCSV()]. ");
            System.out.println(e.getMessage());
        }

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
                    writer.write(id + "|" + item.getName() + "|" + item.getPrice() + "|" + item.getDiscount() + "|" + item.getBrand() + "|" + item.getWarrantyPeriod() +  "|" + item.getItemDetails());
                    writer.newLine();
                } catch (IOException e) {
                    System.out.print(">! Error while saving items in Electronics.csv, [ElectronicItemsHandler, saveToCSV()]. ");
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