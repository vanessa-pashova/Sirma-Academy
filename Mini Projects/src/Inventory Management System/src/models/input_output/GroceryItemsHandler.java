package models.input_output;

import models.items.GroceryItems;

import java.io.*;
import java.util.TreeMap;

public abstract class GroceryItemsHandler implements CVSHandler<GroceryItems> {
    @Override
    public TreeMap<Integer, GroceryItems> loadFromCVS(String filename) {
        if(filename == null || filename.isEmpty())
            throw new IllegalArgumentException(">! Filename cannot be null or empty, [GroceryItemsHandler, loadFromCVS()]. ");

        File file = new File(filename);
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.print(">! Error while creating a loading file Groceries.cvs, [GroceryItemsHandler, loadFromCVS()].");
                System.out.println(e.getMessage());
            }
        }

        TreeMap<Integer, GroceryItems> items = new TreeMap<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();

            while ((line = reader.readLine()) != null) {
                String []components = line.split("|");
                int id = Integer.parseInt(components[0]);
                String name= components[1];
                double price = Double.parseDouble(components[2]);
                double discount = Double.parseDouble(components[3]);
                String description = components[4];
                double weight = Double.parseDouble(components[5]);
                int calories = Integer.parseInt(components[6]);
                String creationDate = components[7];
                String expiryDate = components[8];

                GroceryItems item = new GroceryItems(name, price, discount, description, weight, calories, creationDate, expiryDate);
                items.put(id, item);
            }
        } catch (IOException e) {
            System.out.print(">! Error while loading Clothes.cvs, [GroceryItemsHandler, loadFromCVS()]. ");
            System.out.println(e.getMessage());
        }

        return items;
    }

    @Override
    public void saveToCVS(String filename, TreeMap<Integer, GroceryItems> items) {
        if(filename == null || filename.isEmpty())
            throw new IllegalArgumentException(">! Filename cannot be null or empty, [ClothesItemsHandler, saveToCVS()]. ");

        File file = new File(filename);
        if(!file.exists()) {
            try {
                file.createNewFile();
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                writer.write("ID|Name|Price|Discount|Description|Weight|Calories|CreationDate|ExpiryDate");
                writer.newLine();
            } catch (IOException e) {
                System.out.print(">! Error while creating Groceries.cvs file, [GroceryItemsHandler, saveToCVS()]. ");
                System.out.println(e.getMessage());
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            items.forEach((id, item) -> {
                try {
                    writer.write(id + "|" + item.getName() + "|" + item.getPrice() + "|" + item.getDiscount() + "|" + item.getItemDescription() + "|" + item.getWeight() + "|" + item.getCalories() + "|" + item.getCreationDate() + "|" + item.getExpiryDate());
                    writer.newLine();
                } catch (IOException e) {
                    System.out.print(">! Error while saving Groceries.cvs. [GroceryItemsHandler, saveToCVS()]. ");
                    System.out.println(e.getMessage());
                }
            });
        } catch (IOException e) {
            System.out.print(">! Error while writing Groceries.cvs file, [GroceryItemsHandler, saveToCVS()]. ");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void removeFromCVS(String filename, int id) {
        if(filename == null || filename.isEmpty())
            throw new IllegalArgumentException(">! Filename cannot be null or empty, [GroceryItemsHandler, removeFromCVS()]. ");

        TreeMap<Integer, GroceryItems> items = loadFromCVS(filename);
        if(items.containsKey(id)) {
            items.remove(id);
            saveToCVS(filename, items);
        }

        else
            throw new IllegalArgumentException(">! Item does not exist, [GroceryItemsHandler, removeFromCVS()]. ");
    }
}