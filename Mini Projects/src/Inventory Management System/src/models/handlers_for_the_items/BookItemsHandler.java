package models.handlers_for_the_items;

import models.items.BookItems;

import java.io.*;
import java.util.TreeMap;

public class BookItemsHandler extends AbstractCSVHandler<BookItems> {
    @Override
    public TreeMap<String, BookItems> loadFromCSV(String filename) {
        if (filename == null || filename.isEmpty())
            throw new IllegalArgumentException(">! Filename cannot be null or empty, [BookItemsHandler, loadFromCSV()].");

        File file = new File(filename);
        if (!file.exists()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write("ID|Name|Price|Discount|Author|Genre|TotalPages|Publisher|Description");
                writer.newLine();
            } catch (IOException e) {
                System.out.print(">! Error while creating Books.csv, [BookItemsHandler, loadFromCSV()].");
                System.out.println(e.getMessage());
            }
            System.out.println("[ Books.csv created -> BookItemsHandler, loadFromCSV() ]");
        }

        TreeMap<String, BookItems> items = new TreeMap<>();
        int maxID = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line = reader.readLine(); //Skip the header
            while ((line = reader.readLine()) != null) {
                String[] components = line.split("\\|");
                String id = components[0];
                String name = components[1];
                double price = Double.parseDouble(components[2]);
                double discount = Double.parseDouble(components[3]);
                String author = components[4];
                String genre = components[5];
                int totalPages = Integer.parseInt(components[6]);
                String publisher = components[7];
                String description = components[8];

                BookItems item = new BookItems(id, name, discount, price, author, genre, totalPages, publisher, description);
                items.put(id, item);


                int currentID = Integer.parseInt(id.split("-")[1]);
                if (currentID > maxID)
                    maxID = currentID;
            }
        } catch (IOException e) {
            System.out.println(">! Error while loading Books.csv: " + e.getMessage());
        }

        BookItems.updateBookIDCounter(maxID);
        return items;
    }

    @Override
    public void saveToCSV(String filename, TreeMap<String, BookItems> items) {
        if (filename == null || filename.isEmpty())
            throw new IllegalArgumentException(">! Filename cannot be null or empty, [BookItemsHandler, saveToCSV()].");

        File file = new File(filename);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write("ID|Name|Price|Discount|Author|Genre|TotalPages|Publisher|Description");
            writer.newLine();

            items.forEach((id, item) -> {
                try {
                    writer.write(id + "|" + item.getName() + "|" + item.getPrice() + "|" + item.getDiscount() + "|" +
                            item.getAuthor() + "|" + item.getGenre() + "|" + item.getTotalPages() + "|" +
                            item.getPublisher() + "|" + item.getItemDescription());
                    writer.newLine();
                } catch (IOException e) {
                    System.out.print(">! Error while writing Books.csv, [BookItemsHandler, saveToCSV()].");
                    System.out.println(e.getMessage());
                }
            });
        } catch (IOException e) {
            System.out.print(">! Error while saving Books.csv, [BookItemsHandler, saveToCSV()].");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void removeFromCSV(String filename, String id) {
        if (filename == null || filename.isEmpty())
            throw new IllegalArgumentException(">! Filename cannot be null or empty, [BookItemsHandler, removeFromCSV()].");

        TreeMap<String, BookItems> items = loadFromCSV(filename);
        if (items.containsKey(id)) {
            items.remove(id);
            saveToCSV(filename, items);
        }

        else
            throw new IllegalArgumentException(">! Item does not exist, [BookItemsHandler, removeFromCSV()].");
    }
}
