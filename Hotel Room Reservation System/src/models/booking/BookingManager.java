package models.booking;

import models.rooms.Room;
import models.rooms.RoomManager;
import models.user.User;
import models.user.UserManager;

import java.io.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class BookingManager {
    private static List<Booking> bookings = new ArrayList<>();
    private static final String RESERVATIONS = "/Users/vanessa.pashova/Desktop/Sirma Academy 24/Hotel Room Reservation System/src/data/reservations.cvs";

    public static void loadReservationsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(RESERVATIONS))) {
            String line = reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");

                if (details.length != 6) {
                    System.out.println(">! Invalid line in reservations file: " + line);
                    continue;
                }

                try {
                    int bookingId = Integer.parseInt(details[0]);
                    int roomNumber = Integer.parseInt(details[1]);
                    String roomType = details[2];
                    String arrivalDate = details[3];
                    String departureDate = details[4];
                    String username = details[5];

                    Room room = RoomManager.findRoomByNumber(roomNumber);
                    if (room != null) {
                        Booking booking = new Booking(bookingId,
                                (int) ChronoUnit.DAYS.between(LocalDate.parse(arrivalDate), LocalDate.parse(departureDate)),
                                null,
                                arrivalDate,
                                departureDate,
                                UserManager.getUsersMap().get(username));

                        bookings.add(booking);
                    }

                    else
                        System.out.println(">! Room with number " + roomNumber + " not found. Skipping reservation.");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println(">! Error while loading reservations from file: " + e.getMessage());
        }
    }

    public static void saveReservationToFile(Booking booking) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RESERVATIONS, true))) {
            File file = new File(RESERVATIONS);
            if (!file.exists()) {
                file.createNewFile();
                writer.write("bookingId,roomNumber,roomType,arrivalDate,departureDate,users");
                writer.newLine();
            }

            String username = booking.getReservator().getUsername();

            writer.write(booking.getBookingID() + "," + booking.getRoom().getRoomNumber() + "," + booking.getRoom().getRoomType() + "," + booking.getArrivalDate() + "," + booking.getDepartureDate() + "," + username);
            writer.newLine();
        } catch (IOException e) {
            System.out.println(">! Error while saving reservation to file: " + e.getMessage());
        }
    }

    public void makeReservation() {
        if (UserManager.currentUser == null) {
            System.out.println(">! Make sure you are logged in.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("> Insert the room type (Single/Double/Suite/Deluxe): ");
        String type = scanner.nextLine();
        System.out.println("> Insert arrival date (yyyy-MM-dd): ");
        String arrivalDate = scanner.nextLine();
        System.out.println("> Insert departure date (yyyy-MM-dd): ");
        String departureDate = scanner.nextLine();

        List<Room> availableRooms = RoomManager.findAvailableRooms(type);

        if (availableRooms.isEmpty()) {
            System.out.println(">! No rooms available of type: " + type);
            return;
        }

        Room bookedRoom = availableRooms.get(0);
        System.out.println("> Found room: " + bookedRoom);

        try {
            LocalDate arrival = LocalDate.parse(arrivalDate);
            LocalDate departure = LocalDate.parse(departureDate);
            long nights = ChronoUnit.DAYS.between(arrival, departure);

            if (nights <= 0) {
                System.out.println(">! Invalid date range. Departure must be after arrival.");
                return;
            }

            bookedRoom.setStatus("Reserved");
            RoomManager.updateRoomStatusInFile(bookedRoom);

            int bookingID = bookings.size() + 1;
            Booking newBooking = new Booking(bookingID, (int) nights, bookedRoom, arrivalDate, departureDate, UserManager.currentUser);
            bookings.add(newBooking);

            saveReservationToFile(newBooking);

            System.out.println("> Booking successful! Nights: " + nights + ", Room: " + bookedRoom);

        } catch (IllegalArgumentException e) {
            System.out.println(">! Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println(">! Unexpected error during booking: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static List<Booking> getCurrentUserBookings() {
        if (UserManager.currentUser == null)
            throw new IllegalStateException(">! No user is currently logged in.");

        List<Booking> userBookings = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(RESERVATIONS))) {
            String line = reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                int bookingId = Integer.parseInt(details[0]);
                int roomNumber = Integer.parseInt(details[1]);
                String arrivalDate = details[3];
                String departureDate = details[4];
                String username = details[5];

                if (username.equals(UserManager.currentUser.getUsername())) {
                    Room room = RoomManager.findRoomByNumber(roomNumber);
                    if (room != null) {
                        Booking booking = new Booking(bookingId,
                                (int) ChronoUnit.DAYS.between(LocalDate.parse(arrivalDate), LocalDate.parse(departureDate)),
                                room,
                                arrivalDate,
                                departureDate,
                                UserManager.currentUser);
                        userBookings.add(booking);
                    }

                    else
                        System.out.println(">! Room with number " + roomNumber + " not found for booking ID: " + bookingId);
                }
            }
        } catch (IOException e) {
            System.out.println(">! Error while reading reservations: " + e.getMessage());
        }

        return userBookings;
    }

    public static void removeReservationFromFile(int bookingID) {
        File file = new File(RESERVATIONS);

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder updatedContent = new StringBuilder();
            String line = reader.readLine();

            if (line != null)
                updatedContent.append(line).append(System.lineSeparator());

            boolean reservationFound = false;
            int roomNumberToFree = -1;

            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                int currentBookingId = Integer.parseInt(details[0]);
                int roomNumber = Integer.parseInt(details[1]);
                String username = details[5];

                if (username.equals(UserManager.currentUser.getUsername()) && bookingID == currentBookingId) {
                    reservationFound = true;
                    roomNumberToFree = roomNumber;
                    continue;
                }

                updatedContent.append(line).append(System.lineSeparator());
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(updatedContent.toString());
            }

            if (reservationFound) {
                if (roomNumberToFree != -1) {
                    Room room = RoomManager.findRoomByNumber(roomNumberToFree);
                    if (room != null) {
                        room.setStatus("Available");
                        RoomManager.updateRoomStatusInFile(room);
                    }
                }

                System.out.println("> Reservation cancelled successfully.");
            }

            else
                System.out.println(">! No matching reservation found for the given ID.");

        } catch (IOException e) {
            System.out.println(">! Error while reading reservations: " + e.getMessage());
        }
    }


    public static void cancelReservation() {
        List<Booking> userBookings = getCurrentUserBookings();

        if (userBookings.isEmpty()) {
            System.out.println(">! You have no bookings to cancel.");
            return;
        }

        System.out.println("----- YOUR BOOKINGS -----");
        for (Booking booking : userBookings)
            System.out.println("> Booking ID: " + booking.getBookingID() + ", Room: " + booking.getRoom().getRoomType() + ", Dates: " + booking.getArrivalDate() + " - " + booking.getDepartureDate());

        System.out.println("-------------------------");

        Scanner scanner = new Scanner(System.in);
        System.out.print("> Enter the Booking ID to cancel: ");
        int bookingID = Integer.parseInt(scanner.nextLine());

        Booking bookingToCancel = null;
        for (Booking booking : userBookings) {
            if (booking.getBookingID() == bookingID) {
                bookingToCancel = booking;
                break;
            }
        }

        if (bookingToCancel != null) {
            RoomManager.updateRoomStatusInFile(bookingToCancel.getRoom());
            bookings.remove(bookingToCancel);
            bookingToCancel.getRoom().setStatus("Available");

            removeReservationFromFile(bookingID);

            System.out.println("> Booking cancelled successfully. Cancellation fee: " + bookingToCancel.getRoom().getCancellationFee() + " BGN.");
            System.out.println("-------------------------");
        }

        else
            System.out.println(">! Invalid Booking ID. Please try again.");
    }

    public static List<Booking> searchBookingsByRoomNumber(int roomNumber) {
        List<Booking> results = new ArrayList<>();
        for (Booking booking : bookings) {
            if (booking.getRoom().getRoomNumber() == roomNumber)
                results.add(booking);
        }

        return results;
    }

    public static List<Booking> searchBookingsByDateRange(LocalDate startDate, LocalDate endDate) {
        List<Booking> results = new ArrayList<>();
        for (Booking booking : bookings) {
            LocalDate arrival = LocalDate.parse(booking.getArrivalDate());
            LocalDate departure = LocalDate.parse(booking.getDepartureDate());

            if ((arrival.isAfter(startDate) || arrival.isEqual(startDate)) && (departure.isBefore(endDate) || departure.isEqual(endDate)))
                results.add(booking);
        }

        return results;
    }
}