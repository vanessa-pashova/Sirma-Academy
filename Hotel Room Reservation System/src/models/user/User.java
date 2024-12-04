package models.user;

import models.additional_functions.PasswordValidator;
import static models.additional_functions.PasswordValidator.isValidPassword;

public class User {
    private String firstName, familyName, username, password, bookingHistory;

    public User(String firstName, String familyName, String username, String password, String bookingHistory) {
        this.setName(firstName, familyName);
        this.username = username;
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
        if((firstName == null || firstName.isEmpty()) || (familyName == null || familyName.isEmpty()))
            throw new IllegalArgumentException(">! Name cannot be null or empty.");

        char firstCharFirstName = firstName.charAt(0), firstCharLastName = firstName.charAt(0);
        if(!Character.isUpperCase(firstCharFirstName) || !Character.isUpperCase(firstCharLastName))
            throw new IllegalArgumentException(">! Name contains invalid characters.");

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

    public void addBooking(String booking) {
        if(bookingHistory.isEmpty())
            bookingHistory = booking;

        else
            bookingHistory += ";" + booking;
    }

    @Override
    public String toString() {
        return ("[ " + this.firstName + " " + this.familyName + ": username --> " + this.username + " has the following booking history: " + this.bookingHistory + " ]\n");
    }
}