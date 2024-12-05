package models.mains;

import models.booking.Booking;
import models.booking.BookingManager;
import models.rooms.RoomManager;
import models.user.UserManager;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class HotelReservationSystem {
    public static void main(String[] args) {
        UserManager.loadUsers();
        RoomManager.loadRoomsTypes();
        RoomManager.loadRooms();
        BookingManager.loadReservationsFromFile();

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            if (UserManager.currentUser == null) {
                System.out.println("------- WELCOME TO THE HOTEL RESERVATION SYSTEM -------");
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("3. Exit");
                System.out.print("> Choose an option: ");

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> UserManager.registerNewUser();

                    case 2 -> {
                        boolean loggedIn = UserManager.logInUser();
                        if (!loggedIn)
                            System.out.println(">! Login failed. Try again.");
                    }

                    case 3 -> {
                        running = false;
                        System.out.println("> Exiting... Goodbye!");
                    }

                    default -> System.out.println(">! Invalid choice. Try again.");
                }
            }

            else {
                System.out.println("1. View Your Bookings");
                System.out.println("2. Make a Reservation");
                System.out.println("3. Cancel a Reservation");
                System.out.println("4. Search Bookings");
                System.out.println("5. Logout");
                System.out.println("6. Exit");
                System.out.print("> Choose an option: ");

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> {
                        System.out.println("----- YOUR BOOKINGS -----");

                        if (BookingManager.getCurrentUserBookings().isEmpty())
                            System.out.println(">! No bookings found.\n-------------------------");

                        else {
                            BookingManager.getCurrentUserBookings().forEach(System.out::print);
                            System.out.println("-------------------------");
                        }
                    }

                    case 2 -> new BookingManager().makeReservation();

                    case 3 -> BookingManager.cancelReservation();

                    case 4 -> {
                        System.out.println("----- SEARCH BOOKINGS -----");
                        System.out.println("1. Search by Room Number");
                        System.out.println("2. Search by Date Range");
                        System.out.print("> Choose an option: ");
                        int searchOption = scanner.nextInt();
                        scanner.nextLine();

                        switch (searchOption) {
                            case 1 -> {
                                System.out.print("> Enter the room number: ");
                                int roomNumber = scanner.nextInt();
                                scanner.nextLine();
                                List<Booking> bookings = BookingManager.searchBookingsByRoomNumber(roomNumber);

                                if (bookings.isEmpty())
                                    System.out.println(">! No bookings found for room number: " + roomNumber);

                                else
                                    bookings.forEach(System.out::println);
                            }

                            case 2 -> {
                                System.out.print("> Enter the start date (yyyy-MM-dd): ");
                                String startDateStr = scanner.nextLine();
                                System.out.print("> Enter the end date (yyyy-MM-dd): ");
                                String endDateStr = scanner.nextLine();
                                try {
                                    LocalDate startDate = LocalDate.parse(startDateStr);
                                    LocalDate endDate = LocalDate.parse(endDateStr);
                                    List<Booking> bookings = BookingManager.searchBookingsByDateRange(startDate, endDate);

                                    if (bookings.isEmpty())
                                        System.out.println(">! No bookings found for the given date range.");

                                    else
                                        bookings.forEach(System.out::println);
                                } catch (Exception e) {
                                    System.out.println(">! Invalid date format. Please use yyyy-MM-dd.");
                                }
                            }

                            default -> System.out.println(">! Invalid search option.");
                        }
                    }

                    case 5 -> {
                        UserManager.currentUser = null;
                        System.out.println("> You have logged out.");
                    }

                    case 6 -> {
                        running = false;
                        System.out.println("> Exiting... Goodbye!");
                    }

                    default -> System.out.println(">! Invalid choice. Try again.");
                }
            }
        }

        scanner.close();
    }
}
