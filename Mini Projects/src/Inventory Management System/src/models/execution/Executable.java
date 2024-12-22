package models.execution;

import models.items.AbstractItem;
import models.user.Admin;
import models.user.Customer;
import models.user.AbstractUser;
import models.login.Login;

import java.util.Scanner;

public class Executable {
    private Login loginManager = new Login();
    private AbstractUser loggedInUser;

    public void execute() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println();
            System.out.println("------ WELCOME TO THE INVENTORY MANAGEMENT SYSTEM ------");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("> Choose an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> login(scanner);
                case "2" -> register(scanner);
                case "3" -> {
                    exit = true;
                    System.out.println("Goodbye!");
                }
                default -> System.out.println(">! Invalid option, please try again.");
            }
        }
    }

    private void login(Scanner scanner) {
        System.out.print("> Enter your email: ");
        String email = scanner.nextLine();
        System.out.print("> Enter your password: ");
        String password = scanner.nextLine();

        try {
            loginManager.login(email, password);
            loggedInUser = loginManager.getUser(email);

            if (loggedInUser instanceof Admin admin)
                adminMenu(admin, scanner);

            else if (loggedInUser instanceof Customer customer)
                customerMenu(customer, scanner);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void register(Scanner scanner) {
        System.out.print("> Enter your first name: ");
        String firstName = scanner.nextLine();
        System.out.print("> Enter your family name: ");
        String familyName = scanner.nextLine();
        System.out.print("> Enter your email: ");
        String email = scanner.nextLine();
        System.out.print("> Enter your password: ");
        String password = scanner.nextLine();
        System.out.print("> Choose role (ADMIN or CUSTOMER): ");
        String role = scanner.nextLine();

        try {
            loginManager.register(firstName, familyName, email, password, role);
            this.loginManager.reloadUsers();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void adminMenu(Admin admin, Scanner scanner) {
        boolean exit = false;

        while (!exit) {
            System.out.println();
            System.out.println("------ ADMIN MENU ------");
            System.out.println("1. Look Through Items");
            System.out.println("2. Filter Inventory by Category");
            System.out.println("3. Find Item by ID");
            System.out.println("4. Update Inventory");
            System.out.println("5. Clear All Inventory Items");
            System.out.println("6. View Total Inventory Quantity");
            System.out.println("7. View Total Inventory Value");
            System.out.println("8. Print Info for Admin");
            System.out.println("9. Delete Admin Account");
            System.out.println("10. Logout");
            System.out.print("> Choose an option: ");
            String choice = scanner.nextLine();
            System.out.println();

            switch (choice) {
                case "1" -> admin.itemsLookThru();
                case "2" -> filterByCategory(admin, scanner);
                case "3" -> findItem(admin, scanner);
                case "4" -> admin.updateInventory();
                case "5" -> admin.clearAllItems();

                case "6" -> {
                    int totalQuantity = admin.getTotalQuantity();
                    System.out.println("------ TOTAL INVENTORY QUANTITY ------");
                    System.out.printf("> Total quantity: %d%n", totalQuantity);
                }

                case "7" -> {
                    double totalValue = admin.getTotalValue();
                    System.out.println("------ TOTAL INVENTORY VALUE ------");
                    System.out.printf("> Total value: %.2f%n", totalValue);
                }

                case "8" -> admin.printInfoForMe();

                case "9" -> {
                    try {
                        admin.deleteMe();
                        this.loginManager.reloadUsers();
                        exit = true;

                        System.out.println("------ RETURNING TO LOGIN MENU ------");
                    } catch (Exception e) {
                        System.out.println(">! Error during account deletion: " + e.getMessage());
                    }
                }

                case "10" -> {
                    exit = true;
                    System.out.println("Logging out...");
                }

                default -> System.out.println(">! Invalid option, please try again.");
            }
        }
    }

    private void customerMenu(Customer customer, Scanner scanner) {
        boolean exit = false;

        while (!exit) {
            System.out.println();
            System.out.println("------ CUSTOMER MENU ------");
            System.out.println("1. View Inventory");
            System.out.println("2. Find Item by ID");
            System.out.println("3. Filter Inventory by Category");
            System.out.println("4. View Previous Purchases");
            System.out.println("5. Add to Favourites");
            System.out.println("6. View Favourites");
            System.out.println("7. Add Item to Current Purchase");
            System.out.println("8. View Current Purchase");
            System.out.println("9. Buy Items");
            System.out.println("10. Print Info for Customer");
            System.out.println("11. Add a Payment Card");
            System.out.println("12. Check Card Balance");
            System.out.println("13. Add Money to Card");
            System.out.println("14. Delete Customer Account");
            System.out.println("15. Logout");
            System.out.print("> Choose an option: ");
            String choice = scanner.nextLine();
            System.out.println();

            switch (choice) {
                case "1" -> customer.getInventory().printInventory();
                case "2" -> findItem(customer, scanner);
                case "3" -> filterByCategory(customer, scanner);
                case "4" -> customer.printPreviousPurchases(customer);
                case "5" -> addToFavourites(customer, scanner);
                case "6" -> customer.printFavourites();
                case "7" -> customer.addToCurrentPurchase(customer, scanner);
                case "8" -> customer.printCurrentPurchase();
                case "9" -> customer.buyItems();
                case "10" -> customer.printInfoForMe();

                case "11" -> {
                    System.out.print("> Enter card number: ");
                    String number = scanner.nextLine();
                    System.out.print("> Enter expiry date (MM/YYYY): ");
                    String expiryDate = scanner.nextLine();
                    System.out.print("> Enter CCV: ");
                    String ccv = scanner.nextLine();
                    System.out.print("> Enter card type (Visa, MasterCard, AmericanExpress): ");
                    String cardType = scanner.nextLine();
                    System.out.print("> Enter initial balance: ");
                    double amount = Double.parseDouble(scanner.nextLine());

                    try {
                        customer.addCard(number, expiryDate, ccv, cardType, amount);
                        System.out.println("------ CARD ADDED SUCCESSFULLY ------");
                    } catch (IllegalStateException e) {
                        System.out.println(e.getMessage());
                    }
                }

                case "12" -> {
                    if (customer.getCard() == null)
                        System.out.println(">! No card associated with this account, [Executable, customerMenu --> case 12].");

                    else
                        customer.checkBalance();
                }

                case "13" -> customer.addMoneyToCard(customer, scanner);

                case "14" -> {
                    customer.deleteMe();
                    this.loginManager.reloadUsers();
                    exit = true;

                    System.out.println("------ RETURNING TO LOGIN MENU ------");
                }

                case "15" -> {
                    exit = true;
                    System.out.println("Logging out...");
                }

                default -> System.out.println(">! Invalid option, please try again.");
            }
        }
    }

    private void findItem(AbstractUser user, Scanner scanner) {
        System.out.print("> Enter the ID of the item to find: ");
        String id = scanner.nextLine();

        try {
            user.findItem(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void filterByCategory(AbstractUser user, Scanner scanner) {
        System.out.print("> Enter the category to filter by: ");
        String category = scanner.nextLine();
        System.out.println();

        try {
            user.filterCategory(category);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void addToFavourites(Customer customer, Scanner scanner) {
        System.out.print("> Enter the ID of the item to add to favourites: ");
        String itemId = scanner.nextLine();

        try {
            AbstractItem item = customer.getInventory().findItem(itemId);
            customer.addToFavourites(item);
            System.out.println("Item added to favourites!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
