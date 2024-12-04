package models.booking;

import models.rooms.Room;
import models.rooms.RoomManager;
import models.user.UserManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookingManager {
    public static final String RESERVATIONS = "/Users/vanessa.pashova/Desktop/Sirma Academy 24/Hotel Room Reservation System/src/data/reservations.cvs";

    public void makeReservation() {
        if(UserManager.currentUser == null) {
            System.out.println(">! Make sure you are logged in.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("> Insert the room type (Single/Double/Suit/Deluxe): ");
        String type = scanner.nextLine();
        System.out.println("> Insert arrival date: ");
        String arrivalDate = scanner.nextLine();
        System.out.println("> Insert departure date: ");
        String departureDate = scanner.nextLine();

        List<Room> availableRooms = RoomManager.findAvailableRooms(type);

    }
}
