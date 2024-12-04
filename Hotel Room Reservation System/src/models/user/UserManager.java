package models.user;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UserManager {
    private static final String USERS_FILE = "/Users/vanessa.pashova/Desktop/Sirma Academy 24/Hotel Room Reservation System/src/data/users.cvs";
    private static Map<String, User> users = new HashMap<>();
    public static User currentUser;

    public static Map<String, User> getUsersMap() {
        return users;
    }

    private static void saveUser(User user) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USERS_FILE, true))) { // true за append режим
            File file = new File(USERS_FILE);
            if (!file.exists()) {
                file.createNewFile();
                System.out.println("------- saveUser() CREATED FILE: " + file.getAbsolutePath() + " -------");
            }

            writer.write(user.getFirstName() + "," + user.getFamilyName() + "," + user.getUsername() + "," + user.getPassword() + "," + user.getBookingHistory());
            writer.newLine();
            System.out.println("------- saveUser() FINISHED FILE: " + file.getAbsolutePath() + " -------");
        } catch (IOException e) {
            System.out.println(">! Error while saving user to csv file.");
            System.out.println(e.getMessage());
        }
    }

    public static void loadUsers() {
        try(BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE))) {
            String line = reader.readLine();

            while((line = reader.readLine()) != null) {
                String []details = line.split(",");
                String firstName = details[0];
                String familyName = details[1];
                String username = details[2];
                String password = details[3];
                String bookingHistory = details.length > 4 ? details[4] : "";

                User user = new User(firstName, familyName, username, password, bookingHistory);
                users.put(username, user);
            }

            System.out.println("------- USERS SUCCESSFULLY LOADED -------");
        } catch (IOException e) {
            System.out.println(">! Error reading users file.");
            System.out.println(e.getMessage());
        }
    }

    public static void registerNewUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("------- ADD NEW USER -------");
        System.out.println("> Enter first and last name: ");
        String firstName = scanner.nextLine(), lastName = scanner.nextLine();
        System.out.println("> Enter Username: ");
        String username = scanner.nextLine();

        if (users.containsKey(username)) {
            System.out.println(">! Username already exists. Please choose another one.");
            return;
        }

        System.out.println("> Enter Password: ");
        String password = scanner.nextLine();
        System.out.println("> Enter Booking History: ");
        String bookingHistory = scanner.nextLine();

        User user = new User(firstName, lastName, username, password, bookingHistory);
        users.put(username, user);
        saveUser(user);
        System.out.println("------- NEW USER REGISTERED SUCCESSFULLY -------");
    }

    public static boolean logInUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("------- LOGIN -------");
        System.out.println("Enter Username: ");
        String username = scanner.nextLine();
        System.out.println("Enter Password: ");
        String password = scanner.nextLine();

        User user = users.get(username);
        if(user == null || !password.equals(user.getPassword())) {
            System.out.println(">! Invalid username or password.");
            return false;
        }

       currentUser = user;
        System.out.println("------- WELCOME TO RESERVATION SYSTEM, " + currentUser.getFirstName() + " " + currentUser.getFamilyName() + " -------");
        return true;
    }
}
