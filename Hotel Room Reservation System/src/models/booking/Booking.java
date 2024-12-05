package models.booking;

import models.rooms.Room;
import models.user.User;
import models.user.UserManager;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Booking {
    private int bookingID, nightsNumber;
    private Room room;
    private String arrivalDate, departureDate;
    private User reservator;

    public Booking(int bookingID, int nightsNumber, Room room, String arrivalDate, String departureDate, User reservator) {
        this.setBookingID(bookingID);
        this.setNightsNumber(nightsNumber);
        this.room = room;
        this.setArrivalDate(arrivalDate);
        this.setDepartureDate(departureDate);
        this.setReservator(reservator);
    }

    public int getBookingID() {
        return this.bookingID;
    }

    public int getNightsNumber() {
        return this.nightsNumber;
    }

    public Room getRoom() {
        return this.room;
    }

    public String getArrivalDate() {
        return this.arrivalDate;
    }

    public String getDepartureDate() {
        return this.departureDate;
    }

    public User getReservator() {
        return this.reservator;
    }

    public void setBookingID(int bookingID) {
        if(bookingID < 0)
            throw new IllegalArgumentException(">! Invalid booking ID.");

        this.bookingID = bookingID;
    }

    public void setNightsNumber(int nightsNumber) {
        if(nightsNumber <= 0)
            throw new IllegalArgumentException(">! Invalid nights number (negative value).");

        else if(nightsNumber > 14)
            throw new IllegalArgumentException(">! Only 14 nights at most allowed.");

        this.nightsNumber = nightsNumber;
    }

    public void setArrivalDate(String arrivalDate) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            this.arrivalDate = LocalDate.parse(arrivalDate, formatter).toString();
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException(">! Invalid arrival date format. Use yyyy-MM-dd.");
        }
    }

    public void setDepartureDate(String departureDate) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            this.departureDate = LocalDate.parse(departureDate, formatter).toString();
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException(">! Invalid departure date format. Use yyyy-MM-dd.");
        }
    }

    public void setReservator(User reservator) {
        if (reservator == null)
            throw new IllegalArgumentException(">! No data for the reservator.");

        if (!UserManager.getUsersMap().containsKey(reservator.getUsername()))
            throw new IllegalArgumentException(">! User not found in the system.");

        this.reservator = reservator;
    }

    public double calculateTotalCost() {
        return this.nightsNumber * this.room.getPrice();
    }

    @Override
    public String toString() {
        if (this.reservator == null)
            return "[ Invalid Booking: Reservator not found ]";

        return "[ BookingID: " + this.bookingID + ", User: " + this.reservator.getFirstName() + " " + this.reservator.getFamilyName() +
                ", Nights: " + this.nightsNumber + ", Total Cost: " + calculateTotalCost() + " BGN, Room: " + this.room.getRoomType() + " ]\n";
    }
}