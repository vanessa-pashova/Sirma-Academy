package models.handlers_for_the_items;

import models.items.BookItems;

import java.io.*;
import java.util.TreeMap;

public abstract class BookItemsHandler implements CVSHandler<BookItems> {
    @Override
    public TreeMap<Integer, BookItems> loadFromCVS(String filename) {
        if(filename == null || filename.isEmpty())
            throw new IllegalArgumentException(">! Filename cannot be null or empty, [BookItemsHandler, loadFromCVS().");

        File file = new File(filename);
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.print(">! Error while creating a loading file Books.cvs, [BookItemsHandler, loadFromCVS().]");
                System.out.println(e.getMessage());
            }
            System.out.println("[ Books.cvs created -> BookItemsHandler, loadFromCVS()]");
        }

        TreeMap<Integer, BookItems> items = new TreeMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();

            while((line = reader.readLine()) != null) {
                String []components = line.split("|");
                int id = Integer.parseInt(components[0]);
                String name = components[1];
                double price = Double.parseDouble(components[2]);
                double discount = Double.parseDouble(components[3]);
                String author = components[4];
                String genre = components[5];
                int totalPages = Integer.parseInt(components[6]);
                String publisher = components[7];
                String description = components[8];

                BookItems item = new BookItems(name, price, discount, author, genre, totalPages, publisher, description);
                items.put(id, item);
            }
        } catch (IOException e) {
            System.out.print(">! Error while loading Books.cvs, [BookItemsHandler, loadFromCVS().]");
            System.out.println(e.getMessage());
        }

        return items;
    }

    @Override
    public void saveToCVS(String filename, TreeMap<Integer, BookItems> items) {
        if(filename == null || filename.isEmpty())
            throw new IllegalArgumentException(">! Filename cannot be null or empty, [BookItemsHandler, saveToCVS()]. ");

        File file = new File(filename);
        if(!file.exists()) {
            try {
                file.createNewFile();
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                writer.write("ID|Name|Price|Discount|Author|Genre|TotalPages|Publisher|Description");
                writer.newLine();
            } catch (IOException e) {
                System.out.print(">! Error while creating Books.cvs, [BookItemsHandler, saveToCVS()].");
                System.out.println(e.getMessage());
            }
        }

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            items.forEach((id, item) -> {
                try {
                    writer.write(id + "|" + item.getName() + "|" + item.getPrice() + "|" + item.getDiscount() + "|" + item.getAuthor() + "|" + item.getGenre() + "|" + item.getTotalPages() + "|" + item.getPublisher() + "|" + item.getItemDescription());
                    writer.newLine();;
                } catch (IOException e) {
                    System.out.print(">! Error while saving Books.cvs, [BookItemsHandler, saveToCVS()].");
                    System.out.println(e.getMessage());
                }
            });
        } catch (IOException e) {
            System.out.print(">! Error while saving Books.cvs, [BookItemsHandler, saveToCVS()].");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void removeFromCVS(String filename, int id) {
        if(filename == null || filename.isEmpty())
            throw new IllegalArgumentException(">! Filename cannot be null or empty, [BookItemsHandler, removeFromCVS()]. ");

        TreeMap<Integer, BookItems> items = loadFromCVS(filename);
        if(items.containsKey(id)) {
            items.remove(id);
            saveToCVS(filename, items);
        }

        else
            throw new IllegalArgumentException(">! Item does not exist, [BookItemsHandler, removeFromCVS()]. ");
    }
}
