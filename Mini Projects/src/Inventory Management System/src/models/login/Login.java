package models.login;

import models.user.AbstractUser;
import models.user.Admin;
import models.user.Customer;
import models.user.handler_for_user.UserHandler;

import java.util.TreeMap;

public class Login {
    private String username, password;
    private UserHandler userHandler = new UserHandler();
    private final String FILE_PATH = "/Users/vanessa.pashova/Desktop/Sirma Academy 24/Mini Projects/src/Inventory Management System/src/csv_files/user/UsersDB.csv";
    private TreeMap<String, AbstractUser> users;

    public Login() {
        reloadUsers();
    }

    public void reloadUsers() {
        this.users = userHandler.loadFromCSV(FILE_PATH);
    }

    public void login(String email, String password) {
        if (email == null || email.isEmpty() || password == null || password.isEmpty())
            throw new IllegalArgumentException(">! Email and/or password are empty, [Login, login()].");

        AbstractUser user = users.get(email);
        if (user != null) {
            if (password.equals(user.getPassword())) {
                this.username = email;
                this.password = password;
                System.out.println("------ LOGIN SUCCESS ------");
            }

            else
                throw new IllegalArgumentException(">! Incorrect password, [Login, login()].");
        }

        else
            throw new IllegalArgumentException(">! User not found, [Login, login()].");
    }

    public void register(String firstName, String familyName, String email, String password, String role) {
        if (firstName == null || firstName.isEmpty() || familyName == null || familyName.isEmpty() || email == null || email.isEmpty() || password == null || password.isEmpty())
            throw new IllegalArgumentException(">! (First name || Family name || email || password) is/are empty, [Login, register()].");

        if (users.containsKey(email))
            throw new IllegalArgumentException(">! User already exists, [Login, register()].");

        AbstractUser user = new Customer(firstName, familyName, email, password, role);
        users.put(email, user);
        userHandler.saveToCSV(FILE_PATH, users);

        System.out.println("------ REGISTER SUCCESS ------");
    }

    public AbstractUser getUser(String email) {
        if (email == null || email.isEmpty())
            throw new IllegalArgumentException(">! Email cannot be null or empty, [Login, getUser()].");

        AbstractUser user = users.get(email);
        if (user == null)
            throw new IllegalArgumentException(">! User not found, [Login, getUser()].");

        return user;
    }
}
