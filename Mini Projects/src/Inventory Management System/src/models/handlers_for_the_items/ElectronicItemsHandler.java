package models.handlers_for_the_items;

import models.items.ElectronicItems;

import java.io.*;
import java.util.TreeMap;

public abstract class ElectronicItemsHandler implements CVSHandler<ElectronicItems> {
    @Override
    public TreeMap<String, ElectronicItems> loadFromCVS(String filename) {
        if(filename == null || filename.isEmpty())
            throw new IllegalArgumentException(">! Filename cannot be null or empty, [ElectronicItemsHandler, loadFromCVS()]");

        File file = new File(filename);
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.print(">! Error while creating a loading file Electronics.cvs, [ElectronicItemsHandler, loadFromCVS()]. ");
                System.out.println(e.getMessage());
            }
            System.out.println("[ Electronics.cvs created -> ElectronicItemsHandler, loadFromCVS()]");
        }

        TreeMap<String, ElectronicItems> items = new TreeMap<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();

            while((reader.readLine()) != null) {
                String []components = line.split("|");
                String id = components[0];
                String name = components[1];
                double price = Double.parseDouble(components[2]);
                double discount = Double.parseDouble(components[3]);
                String brand = components[4];
                int warranty = Integer.parseInt(components[5]);
                String details = components[6];

                ElectronicItems item = new ElectronicItems(name, price, brand, warranty, discount, details);
                items.put(id, item);
            }
        } catch (IOException e) {
            System.out.print(">! Error while loading Electronics.cvs, [ElectronicItemsHandler, loadFromCVS()]. ");
            System.out.println(e.getMessage());
        }

        return items;
    }

    @Override
    public void saveToCVS(String filename, TreeMap<String, ElectronicItems> items) {
        if (filename == null || filename.isEmpty())
            throw new IllegalArgumentException(">! Filename cannot be null or empty, [ElectronicItemsHandler, saveToCVS()].");

        File file = new File(filename);
        if (!file.exists()) {
            try {
                file.createNewFile();
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                writer.write("ID|Name|Price|Discount|Brand|Warranty|Details");
                writer.newLine();
            } catch (IOException e) {
                System.out.print(">! Error while creating Electronics.cvs, [ElectronicItemsHandler, saveToCVS()]. ");
                System.out.println(e.getMessage());
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            items.forEach((id, item) -> {
                try {
                    writer.write(id + "|" + item.getName() + "|" + item.getPrice() + "|" + item.getDiscount() + "|" + item.getBrand() + "|" + item.getWarrantyPeriod());
                    writer.newLine();
                } catch (IOException e) {
                    System.out.print(">! Error while saving items in Electronics.cvs, [ElectronicItemsHandler, saveToCVS()]. ");
                    System.out.println(e.getMessage());
                }
            });
        } catch (IOException e) {
            System.out.print(">! Error while saving Electronics.cvs, [ElectronicItemsHandler, saveToCVS()]. ");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void removeFromCVS(String filename, int id) {
        if (filename == null || filename.isEmpty())
            throw new IllegalArgumentException(">! Filename cannot be null or empty, [ElectronicItemsHandler, removeFromCVS()].");

        TreeMap<String, ElectronicItems> items = loadFromCVS(filename);
        if (items.containsKey(id)) {
            items.remove(id);
            this.saveToCVS(filename, items);
        }

        else
            throw new IllegalArgumentException(">! Item does not exist, [ElectronicItemsHandler, removeFromCVS()].");
    }
}