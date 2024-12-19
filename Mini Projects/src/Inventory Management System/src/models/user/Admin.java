package models.user;
import models.user.handler_for_user.UserHandler;

import java.util.TreeMap;

public class Admin extends AbstractUser {
    private UserHandler userHandler = new UserHandler() {};
    private TreeMap<String, AbstractUser> users = new TreeMap<>();
    private final String PATH_FILE = "/Users/vanessa.pashova/Desktop/Sirma Academy 24/Mini Projects/src/Inventory Management System/src/csv_files/user/UsersDB.csv";

    public Admin(String firstName, String familyName, String email, String password, String role) {
        super(firstName, familyName, email, password, role);
    }

    public void updateInventory() {
        inventory.updateInventory();
    }

    public void clearAllItems() {
        this.getInventory().clearInventory();
    }

    public double getTotalValue() {
        return inventory.getTotalValue();
    }

    public int getTotalQuantity() {
        return this.inventory.getTotalQuantity();
    }

    @Override
    public void deleteMe() {
        super.deleteMe();
        System.out.println("------ ADMIN ACCOUNT IS DELETED ------");
    }
}
