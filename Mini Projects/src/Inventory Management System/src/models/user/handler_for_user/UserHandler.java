package models.user.handler_for_user;

import models.user.*;
import models.handlers_for_the_items.AbstractCSVHandler;

import java.io.*;
import java.util.TreeMap;

public class UserHandler extends AbstractCSVHandler<AbstractUser> {
    @Override
    public TreeMap<String, AbstractUser> loadFromCSV(String filePath) {
        if (filePath == null || filePath.isEmpty())
            throw new IllegalArgumentException(">! File path cannot be null or empty, [UserHandler, loadFromCSV()].");

        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.print(">! Error while creating loading file, [UserHandler, loadFromCSV()]. ");
                System.out.println(e.getMessage());
            }
            System.out.println("[ UsersDB.csv created -> UserHandler, loadFromCSV() ]");
        }

        TreeMap<String, AbstractUser> users = new TreeMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] components = line.split("\\|");
                try {
                    String role = components[0];
                    String firstName = components[1];
                    String familyName = components[2];
                    String email = components[3];
                    String password = components[4];

                    AbstractUser user = null;
                    if ("ADMIN".equalsIgnoreCase(role))
                        user = new Admin(firstName, familyName, email, password, role);

                    else if ("CUSTOMER".equalsIgnoreCase(role))
                        user = new Customer(firstName, familyName, email, password, role);

                    users.put(email, user);
                } catch (IllegalStateException e) {
                    System.out.println(">! Error while reading csv file, [UserHandler, loadFromCSV()]. " + e.getMessage());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(">! Error while loading UsersDB.csv, [UserHandler, loadFromCSV()].");
        }

        return users;
    }

    @Override
    public void saveToCSV(String filename, TreeMap<String, AbstractUser> users) {
        if (filename == null || filename.isEmpty())
            throw new IllegalArgumentException(">! File path cannot be null or empty, [UserHandler, saveToCSV()].");

        File file = new File(filename);

        TreeMap<String, AbstractUser> existingUsers = loadFromCSV(filename);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            if (!file.exists() || file.length() == 0) {
                writer.write("Role|FirstName|FamilyName|Email|Password");
                writer.newLine();
            }

            for (AbstractUser user : users.values()) {
                if (!existingUsers.containsKey(user.getEmail())) {
                    writer.write(user.getRole() + "|" + user.getFirstName() + "|" + user.getFamilyName() + "|" + user.getEmail() + "|" + user.getPassword());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.print(">! Error while saving in UsersDB.csv, [UserHandler, saveToCSV()].");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void removeFromCSV(String filename, String email) {
        if(filename == null || filename.isEmpty())
            throw new IllegalArgumentException(">! File path cannot be null or empty, [UserHanler, removeFromCSV()].");

        TreeMap<String, AbstractUser> users = loadFromCSV(filename);
        if(users.containsKey(email)) {
            users.remove(email);
            saveToCSV(filename, users);
        }

        else
            throw new IllegalArgumentException(">! User does not exist, [UserHanler, removeFromCSV()].");
    }
}
