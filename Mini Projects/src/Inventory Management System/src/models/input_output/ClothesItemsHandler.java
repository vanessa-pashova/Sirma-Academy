package models.input_output;

import com.sun.source.tree.Tree;
import models.items.ClothingItems;

import java.io.*;
import java.util.TreeMap;

public abstract class ClothesItemsHandler implements CVSHandler<ClothingItems> {
    @Override
    public TreeMap<Integer, ClothingItems> loadFromCVS(String filename) {
        if(filename == null || filename.isEmpty())
            throw new IllegalArgumentException(">! Filename cannot be null or empty, [ClothesItemsHandler, loadFromCVS()]. ");

        File file = new File(filename);
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.print(">! Error while creating a loading file Clothes.cvs, [ClothesItemsHandler, loadFromCVS()]. ");
                System.out.println(e.getMessage());
            }
            throw new IllegalArgumentException(">! File does not exist but will be created [ClothesItemsHandler, loadFromCVS()]. ");
        }

        TreeMap<Integer, ClothingItems> items = new TreeMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();

            while((line = reader.readLine()) != null) {
                String []components = line.split("|");
                int id = Integer.parseInt(components[0]);
                String name = components[1];
                double price = Double.parseDouble(components[2]);
                double discount = Double.parseDouble(components[3]);
                String category = components[4];
                String brand = components[5];
                String size = components[6];
                String color = components[7];
                String details = components[8];

                ClothingItems item = new ClothingItems(name, price, discount, brand, size, color, details);
                items.put(id, item);
            }
        } catch (IOException e) {
            System.out.print(">! Error while loading Clothes.cvs, [ClothesItemsHandler, loadFromCVS()]. ");
            System.out.println(e.getMessage());
        }

        return items;
    }

    @Override
    public void saveToCVS(String filename, TreeMap<Integer, ClothingItems> items) {
        if(filename == null || filename.isEmpty())
            throw new IllegalArgumentException(">! Filename cannot be null or empty, [ClothesItemsHandler, saveToCVS()]. ");

        File file = new File(filename);
        if(!file.exists()) {
            try {
                file.createNewFile();
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                writer.write("ID|Name|Price|Discount|Category|Brand|Size|Color|Details");
                writer.newLine();
            } catch (IOException e) {
                System.out.print(">! Error while creating Clothes.cvs file, [ClothesItemsHandler, saveToCVS()]. ");
                System.out.println(e.getMessage());
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            items.forEach((id, item) -> {
                try {
                    writer.write(id + "|" + item.getName() + "|" + item.getPrice() + "|" + item.getDiscount() + "|" + item.getCategory() + "|" + item.getBrand() + "|" + item.getSize() + "|" + item.getColor() + "|" + item.getItemDetails());
                    writer.newLine();
                } catch (IOException e) {
                    System.out.print(">! Error while saving items in Electronics CVS file. [ClothesItemsHandler, saveToCVS()]. ");
                    System.out.println(e.getMessage());
                }
            });
        } catch (IOException e) {
            System.out.print(">! Error while writing Clothes.cvs file, [ClothesItemsHandler, saveToCVS()]. ");
        }
    }

    @Override
    public void removeFromCVS(String filename, int id) {
        if(filename == null || filename.isEmpty())
            throw new IllegalArgumentException(">! Filename cannot be null or empty, [ClothesItemsHandler, removeFromCVS()]. ");

        TreeMap<Integer, ClothingItems> items = loadFromCVS(filename);
        if(items.containsKey(id)) {
            items.remove(id);
            saveToCVS(filename, items);
        }

        else
            throw new IllegalArgumentException(">! Item does not exist, [ClothesItemsHandler, removeFromCVS()]. ");
    }
}
