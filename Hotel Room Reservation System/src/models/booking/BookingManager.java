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
    private User currentUser;

    public static void loadReservationsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(RESERVATIONS))) {
            String line = reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                int bookingId = Integer.parseInt(details[0]);
                int roomNumber = Integer.parseInt(details[1]);
                String arrivalDate = details[2];
                String departureDate = details[3];
                String username = details[4];
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
                writer.write("arrivalDate,departureDate,roomType,reservationId,username");
                writer.newLine();
            }

            String username = booking.getReservator().getUsername();

            writer.write(booking.getBookingID() + "," + booking.getRoom().getRoomNumber() + "," + booking.getArrivalDate() + "," + booking.getDepartureDate() + "," + username);
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

            int bookingID = bookings.size() + 1;
            Booking newBooking = new Booking(bookingID, (int) nights, bookedRoom, arrivalDate, departureDate, UserManager.currentUser);
            bookings.add(newBooking);

            saveReservationToFile(newBooking);

            bookedRoom.setStatus("Reserved");
            RoomManager.updateRoomStatusInFile(bookedRoom);

            System.out.println("> Booking successful! Nights: " + nights + ", Room: " + bookedRoom);

        } catch (IllegalArgumentException e) {
            System.out.println(">! Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println(">! Unexpected error during booking: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static List<Booking> getCurrentUserBookings() {
        if (UserManager.currentUser == null) {
            throw new IllegalStateException(">! No user is currently logged in.");
        }

        List<Booking> userBookings = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(RESERVATIONS))) {
            String line = reader.readLine(); // Пропускаме заглавния ред

            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                String arrivalDate = details[0];
                String departureDate = details[1];
                String roomType = details[2];
                int reservationId = Integer.parseInt(details[3]);
                String username = details[4];

                if (username.equals(UserManager.currentUser.getUsername())) {
                    List<Room> availableRooms = RoomManager.findAvailableRooms(roomType);

                    if (availableRooms.isEmpty()) {
                        System.out.println(">! No rooms available of type: " + roomType);
//                        return null;
                    }

                    Room room = availableRooms.get(0);
                    if (room != null) {
                        Booking booking = new Booking(reservationId,
                                (int) ChronoUnit.DAYS.between(LocalDate.parse(arrivalDate), LocalDate.parse(departureDate)),
                                room,
                                arrivalDate,
                                departureDate,
                                UserManager.currentUser);
                        userBookings.add(booking);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(">! Error while reading reservations: " + e.getMessage());
        }

        return userBookings;
    }

    public static void removeReservationFromFile(int bookingID) {
        File file = new File(RESERVATIONS);
        File tempFile = new File("temp_" + RESERVATIONS); // Временен файл за запис

        try (BufferedReader reader = new BufferedReader(new FileReader(file));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String line = reader.readLine(); // Четем първия ред (заглавната част)
            if (line != null) {
                writer.write(line); // Записваме заглавната част във временния файл
                writer.newLine();
            }

            boolean reservationFound = false;

            // Четем останалите редове
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                int currentBookingID = Integer.parseInt(details[3]); // Резервейшън ID
                String username = details[4]; // Потребителско име

                // Пропускаме реда, ако е резервацията на текущия потребител с дадения ID
                if (currentBookingID == bookingID && username.equals(UserManager.currentUser.getUsername())) {
                    reservationFound = true;
                    continue; // Пропускаме този ред (не го записваме във временния файл)
                }

                writer.write(line); // Записваме всички останали редове
                writer.newLine();
            }

            if (reservationFound) {
                System.out.println("> Reservation removed successfully.");
            } else {
                System.out.println(">! No matching reservation found for the given ID.");
            }

        } catch (IOException e) {
            System.out.println(">! Error while removing reservation: " + e.getMessage());
        }

        // Заменяме оригиналния файл с временния
        if (!file.delete() || !tempFile.renameTo(file)) {
            System.out.println(">! Error replacing the original file.");
        }
    }

    public static void cancelReservation() {
        List<Booking> userBookings = getCurrentUserBookings();

        if (userBookings.isEmpty()) {
            System.out.println(">! You have no bookings to cancel.");
            return;
        }

        System.out.println("----- YOUR BOOKINGS -----");
        for (Booking booking : userBookings) {
            System.out.println("> Booking ID: " + booking.getBookingID() + ", Room: " + booking.getRoom().getRoomType() + ", Dates: " + booking.getArrivalDate() + " - " + booking.getDepartureDate());
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("> Enter the Booking ID to cancel: ");
        int bookingID = scanner.nextInt();
        scanner.nextLine();

        Booking bookingToCancel = null;
        for (Booking booking : userBookings) {
            if (booking.getBookingID() == bookingID) {
                bookingToCancel = booking;
                break;
            }
        }

        if (bookingToCancel != null) {
            bookingToCancel.getRoom().setStatus("Available");
            RoomManager.updateRoomStatusInFile(bookingToCancel.getRoom());
            bookings.remove(bookingToCancel);

            removeReservationFromFile(bookingID); // Изтриваме резервацията от файла

            System.out.println("> Booking cancelled successfully. Cancellation fee: " + bookingToCancel.getRoom().getCancellationFee() + " BGN.");
        } else {
            System.out.println(">! Invalid Booking ID. Please try again.");
        }
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