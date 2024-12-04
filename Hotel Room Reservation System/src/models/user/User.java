package models.user;

import models.additional_functions.PasswordValidator;
import static models.additional_functions.PasswordValidator.isValidPassword;

public class User {
    private String firstName, familyName, username, password, bookingHistory;

    public User(String firstName, String familyName, String username, String password, String bookingHistory) {
        this.setName(firstName, familyName);
        this.setUsername(username);
        this.setPassword(password);
        this.bookingHistory = bookingHistory;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getFamilyName() {
        return this.familyName;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getBookingHistory() {
        return this.bookingHistory;
    }

    void setName(String firstName, String familyName) {
        if (firstName == null || firstName.isEmpty() || familyName == null || familyName.isEmpty())
            throw new IllegalArgumentException(">! Name cannot be null or empty.");

        if (!Character.isUpperCase(firstName.charAt(0)) || !Character.isUpperCase(familyName.charAt(0)))
            throw new IllegalArgumentException(">! Names must start with uppercase letters.");

        this.firstName = firstName;
        this.familyName = familyName;
    }

    public void setUsername(String username) {
        if(username.isEmpty() || username.length() == 0)
            throw new IllegalArgumentException(">! Username cannot be empty.");

        this.username = username.toLowerCase();
    }

    public void setPassword(String password) {
        if(password.isEmpty() || password.length() == 0)
            throw new IllegalArgumentException(">! Password cannot be empty.");

        PasswordValidator validator = new PasswordValidator();
        if(!isValidPassword(password))
            throw new IllegalArgumentException(">! Invalid password format.");

        else
            this.password = password;
    }

    public String getFormattedBookingHistory() {
        if (this.bookingHistory == null || this.bookingHistory.isEmpty())
            return "No bookings available.";

        StringBuilder formattedHistory = new StringBuilder();
        String[] bookings = this.bookingHistory.split(";");

        for (int i = 0; i < bookings.length; i += 3) {
            formattedHistory.append("Room: ").append(bookings[i])
                    .append(", Start Date: ").append(bookings[i + 1])
                    .append(", End Date: ").append(bookings[i + 2]).append("\n");
        }

        return formattedHistory.toString();
    }

    @Override
    public String toString() {
        return ("[ " + this.firstName + " " + this.familyName + ": username --> " + this.username + " has the following booking history: " + this.bookingHistory + " ]\n");
    }
}