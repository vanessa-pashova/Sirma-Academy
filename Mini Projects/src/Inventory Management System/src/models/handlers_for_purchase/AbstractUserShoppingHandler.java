package models.handlers_for_purchase;

import models.handlers_for_the_items.CSVHandler;
import models.items.AbstractItem;
import models.items.InventoryManager;
import models.user.AbstractUser;
import models.user.Customer;
import models.user.handler_for_user.UserHandler;

import java.io.*;
import java.util.TreeMap;

public abstract class AbstractUserShoppingHandler implements CSVHandler<AbstractItem> {
    private InventoryManager inventory = new InventoryManager();
    private UserHandler userHandler = new UserHandler();
    private TreeMap<String, AbstractUser> users = new TreeMap<>();

    @Override
    public TreeMap<String, AbstractItem> loadFromCSV(String filename) {
        if (filename == null || filename.isEmpty())
            throw new IllegalArgumentException(">! Filename cannot be null or empty, [FavouritesHandler, loadFromCSV()].");

        File file = new File(filename);
        if(!file.exists()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
                writer.write("ID|ItemName|ItemPrice|ItemCategory|UserEmail");
                writer.newLine();
            } catch (IOException e) {
                System.out.print(">! Error while creating Favourites.csv, [FavouritesHandler, loadFromCSV()].");
                System.out.println(e.getMessage());
            }
        }
        System.out.println("[ Favourites.csv created -> FavouritesHandler, loadFromCSV() ]");

        TreeMap<String, AbstractItem> favourites = new TreeMap<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                String id = parts[0];
                String itemName = parts[1];
                double price = Double.parseDouble(parts[2]);
                String category = parts[3];
                String email = parts[4];

                AbstractItem item = inventory.getInventory().get(id);
                if(item == null)
                    throw new IllegalArgumentException(">! Item not found in inventory (anymore), [FavouritesHandler, loadFromCSV()]");

                else
                    favourites.put(id, item);
            }
        } catch (IOException e) {
            System.out.print(">! Error while loading Favourites.csv, [FavouritesHandler, loadFromCSV()]");
            System.out.println(e.getMessage());
        }

        return favourites;
    }

//    @Override
    public void saveToCSV(String filename, TreeMap<String, AbstractItem> favourites, String email) {
        if (filename == null || filename.isEmpty())
            throw new IllegalArgumentException(">! Filename cannot be null or empty, [BookItemsHandler, saveToCSV()].");

        File file = new File(filename);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("ID|ItemName|ItemPrice|ItemCategory|UserEmail");
            writer.newLine();

            favourites.forEach((id, item) -> {
                try {
                    writer.write(item.getID() + "|" + item.getName() + "|" + item.getPrice() + "|" + item.getCategory() + "|" + email);
                    writer.newLine();
                } catch (IOException e) {
                    System.out.print(">! Error writing Favourites.csv, [FavouritesHandler, saveToCSV()].");
                    System.out.println(e.getMessage());
                }
            });
        } catch (IOException e) {
            System.out.print(">! Error while saving Favourites.csv, [FavouritesHandler, saveToCSV()]");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void removeFromCSV(String filename, String id) {
        if (filename == null || filename.isEmpty())
            throw new IllegalArgumentException(">! Filename cannot be null or empty, [FavouritesHandler, removeFromCSV()].");

        TreeMap<String, AbstractItem> favourites = loadFromCSV(filename);
        if(favourites.containsKey(id)) {
            favourites.remove(id);
            saveToCSV(filename, favourites);
        }

        else
            throw new IllegalArgumentException(">! Item does not exists, [FavouritesHandler, removeFromCSV()].");
    }
}
