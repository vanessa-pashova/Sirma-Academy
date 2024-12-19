package models.user;

import models.items.AbstractItem;
import models.items.InventoryManager;
import models.user.handler_for_user.UserHandler;

import java.util.TreeMap;

public abstract class AbstractUser implements Role {
    protected final String PATH_FILE = "/Users/vanessa.pashova/Desktop/Sirma Academy 24/Mini Projects/src/Inventory Management System/src/csv_files/user/UsersDB.csv";
    protected String firstName, familyName, email, password;
    protected Role.RoleType role;

    protected InventoryManager inventory = new InventoryManager();

    public AbstractUser(String firstName, String familyName, String email, String password, String role) {
        this.setFirstName(firstName);
        this.setFamilyName(familyName);
        this.setEmail(email);
        this.setPassword(password);
        this.setRole(role);
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getFamilyName() {
        return this.familyName;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    @Override
    public String getRole() {
        return this.role.toString();
    }

    public InventoryManager getInventory() {
        return this.inventory;
    }

    public void setFirstName(String firstName) {
        if(firstName == null || firstName.isEmpty())
            throw new IllegalArgumentException(">! User's first name cannot be empty, [User, setFirstName()].");

        this.firstName = firstName.toUpperCase().charAt(0) + firstName.substring(1).toLowerCase();
    }

    public void setFamilyName(String familyName) {
        if(familyName == null || familyName.isEmpty())
            throw new IllegalArgumentException(">! User's family cannot be empty, [User, setFamilyName()].");

        this.familyName = familyName.toUpperCase().charAt(0) + familyName.substring(1).toLowerCase();
    }

    public void setEmail(String email) {
        if(email == null || email.isEmpty())
            throw new IllegalArgumentException(">! User's email cannot be empty, [User, setEmail()].");

        if(email.endsWith("@gmail.com") || email.endsWith("@abv.bg"))
            this.email = email.toLowerCase();

        else
            throw new IllegalArgumentException(">! Invalid email domain, [User, setEmail()].");
    }

    public void setPassword(String password) {
        if(password == null || password.isEmpty())
            throw new IllegalArgumentException(">! User's password cannot be empty, [User, setPassword()].");

        if(password.length() < 8 || password.length() > 16)
            throw new IllegalArgumentException(">! Invalid password length - must be between 8 and 16, [User, setPassword()].");

        boolean digit = false, capitalLetter = false, smallLetter = false;
        for(char symbol : password.toCharArray()) {
            if(Character.isDigit(symbol))
                digit = true;

            else if(Character.isUpperCase(symbol))
                capitalLetter = true;

            else if(Character.isLowerCase(symbol))
                smallLetter = true;

            if(digit && capitalLetter && smallLetter)
                break;
        }

        if(digit && capitalLetter && smallLetter)
            this.password = password;

        else
            throw new IllegalStateException(">! Invalid password format - must contain at least one capital and one small letter, and a digit as well, [User, setPassword()].");
    }

    @Override
    public void setRole(String role) {
        if(role == null || role.isEmpty())
            throw new IllegalArgumentException(">! User's role cannot be empty, [User, setRole()].");

        if("ADMIN".equalsIgnoreCase(role))
            this.role = Role.RoleType.ADMIN;

        else if("CUSTOMER".equalsIgnoreCase(role))
            this.role = Role.RoleType.CUSTOMER;

        else
            throw new IllegalArgumentException(">! Invalid customer role, [User, setRole()].");
    }

    public void itemsLookThru() {
        if (inventory.getInventory().isEmpty()) {
            System.out.println("------ INVENTORY IS EMPTY ------");
            return;
        }

        System.out.println("------ INVENTORY CURRENT AVAILABILITY ------");
        inventory.printInventory();
        System.out.println("--------------------------------------------");
    }

    public void filterCategory(String category) {
        if (category == null || category.isEmpty())
            throw new IllegalArgumentException(">! Category cannot be null or empty, [AbstractUser, filterCategory()].");

        TreeMap<String, AbstractItem> filtered = inventory.filterByCategory(category);

        if (filtered.isEmpty()) {
            System.out.println("------ NO ITEMS FOUND FOR CATEGORY: " + category.toUpperCase() + " ------");
            return;
        }

        System.out.println("------ FILTERED BY CATEGORY ------");
        for (AbstractItem item : filtered.values())
            item.printDetails();

        System.out.println("----------------------------------");
    }

    public void findItem(String id) {
        if (id == null || id.isEmpty())
            throw new IllegalArgumentException(">! Item ID cannot be null or empty, [Admin, findItem()].");

        AbstractItem item = inventory.findItem(id);

        if (item == null) {
            System.out.println(">! Item with ID: " + id + " not found, [Admin, findItem()].");
            return;
        }

        System.out.println("------ ITEM FOUND ------");
        item.printDetails();
        System.out.println("------------------------");
    }

    public void printInfoForMe() {
        System.out.println("First name: " + this.firstName + " | Family Name: " + this.familyName + " | Email: " + this.email + " | Role: " + this.role);
    }

    public void deleteMe() {
        UserHandler userHandler = new UserHandler();
        TreeMap<String, AbstractUser> users = userHandler.loadFromCSV(PATH_FILE);

        if (!users.containsKey(this.email))
            throw new IllegalArgumentException(">! User not found: " + this.email + ", [AbstractUser, deleteMe()].");

        userHandler.removeFromCSV(PATH_FILE, this.email);

        this.firstName = null;
        this.familyName = null;
        this.email = null;
        this.password = null;
        this.role = null;
    }
}