package models.user;

import models.user.handler_for_user.UserHandler;

import java.util.TreeMap;

public class UsersManager {
    private TreeMap<String, AbstractUser> users = new TreeMap<>();
    private UserHandler userHandler = new UserHandler() {};
    private final String pathToFile = "/Users/vanessa.pashova/Desktop/Sirma Academy 24/Mini Projects/src/Inventory Management System/src/csv_files/user/UsersDB.csv";

    public UsersManager() {
        loadUsers();
    }

    private void loadUsers() {
        try {
            users = userHandler.loadFromCSV(pathToFile);
        } catch (Exception e) {
            System.out.print(">! Error loading users from file: " + pathToFile + ", [Users, loadUsers()]. ");
            System.out.println(e.getMessage());
        }
    }

    public void saveUser(AbstractUser user) {
        if (user == null)
            throw new IllegalArgumentException(">! User cannot be null, [Users, saveUser()].");

        if (users.containsKey(user.getEmail()))
            throw new IllegalArgumentException(">! User already exists: " + user.getEmail() + ", [Users, saveUser()].");

        users.put(user.getEmail(), user);
        userHandler.saveToCSV(pathToFile, users);
        System.out.println("------ USER SAVED SUCCESSFULLY: " + user.getEmail() + " ------");
    }

    public void removeUser(String email) {
        if (email == null || email.isEmpty())
            throw new IllegalArgumentException(">! Email cannot be null or empty, [Users, removeUser()].");

        if (!users.containsKey(email))
            throw new IllegalArgumentException(">! User not found: " + email + ", [Users, removeUser()].");

        users.remove(email);
        userHandler.removeFromCSV(pathToFile, email);
        System.out.println("------ USER REMOVED SUCCESSFULLY: " + email + " ------");
    }

    public AbstractUser findUserByEmail(String email) {
        if (email == null || email.isEmpty())
            throw new IllegalArgumentException(">! Email cannot be null or empty, [Users, findUserByEmail()].");

        AbstractUser user = users.get(email);
        if (user == null)
            throw new IllegalArgumentException(">! User not found: " + email + ", [Users, findUserByEmail()].");

        return user;
    }

    public boolean userExists(String email) {
        if (email == null || email.isEmpty())
            throw new IllegalArgumentException(">! Email cannot be null or empty, [Users, userExists()].");

        return users.containsKey(email);
    }

    public void printAllUsers() {
        if (users.isEmpty()) {
            System.out.println("------ NO USERS IN THE SYSTEM ------");
            return;
        }

        System.out.println("------ USERS IN THE SYSTEM ------");
        users.values().forEach(user -> {
            System.out.println("Email: " + user.getEmail() + " | Role: " + user.getRole() +
                    " | Name: " + user.getFirstName() + " " + user.getFamilyName());
        });
        System.out.println("---------------------------------");
    }

    public void updateUser(String email, AbstractUser updatedUser) {
        if (email == null || email.isEmpty())
            throw new IllegalArgumentException(">! Email cannot be null or empty, [Users, updateUser()].");

        if (!users.containsKey(email))
            throw new IllegalArgumentException(">! User not found: " + email + ", [Users, updateUser()].");

        users.put(email, updatedUser);
        userHandler.saveToCSV(pathToFile, users);
        System.out.println("------ USER UPDATED SUCCESSFULLY: " + email + " ------");
    }

    public void filterUsersByRole(Role.RoleType roleType) {
        if (roleType == null)
            throw new IllegalArgumentException(">! Role cannot be null, [Users, filterUsersByRole()].");

        System.out.println("------ USERS WITH ROLE: " + roleType + " ------");
        users.values().stream()
                .filter(user -> user.getRole().equals(roleType.toString()))
                .forEach(user -> System.out.println("Email: " + user.getEmail() + " | Name: " +
                        user.getFirstName() + " " + user.getFamilyName()));
        System.out.println("---------------------------------------------");
    }

    public void clearAllUsers() {
        users.clear();
        userHandler.saveToCSV(pathToFile, users);
        System.out.println("------ ALL USERS HAVE BEEN REMOVED ------");
    }
}
