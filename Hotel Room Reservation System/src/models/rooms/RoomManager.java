package models.rooms;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoomManager {
    private static final String ROOMS_FILE = "/Users/vanessa.pashova/Desktop/Sirma Academy 24/Hotel Room Reservation System/src/data/rooms.cvs";
    private static final String ROOMSTYPE_FILE = "/Users/vanessa.pashova/Desktop/Sirma Academy 24/Hotel Room Reservation System/src/data/rooms_types.cvs";

    private static final List<Room> rooms = new ArrayList<>();
    private static final Map<String, String[]> roomTypes = new HashMap<>();

    public static Map<String, String[]> getRoomTypes() {
        return roomTypes;
    }

    private static void saveRooms() {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(ROOMS_FILE))) {
            File file = new File(ROOMSTYPE_FILE);
            if(!file.exists()) {
                file.createNewFile();
                System.out.println("----- saveRooms() CREATED FILE: " + file.getAbsolutePath() + "-----\n");
            }

            for(Room room : rooms)
                writer.write(room.getRoomNumber() + "," + room.getRoomType() + "," + room.getPrice() + "," + room.getCancellationFee() + "," + room.getStatus() + '\n');

            System.out.println("----- saveRooms() FINISHED FILE: " + file.getAbsolutePath() + "-----\n");
        } catch (IOException e) {
            System.out.println(">! Error while saving rooms to cvs file.");
            System.out.println(e.getMessage());
        }
    }

    public static void loadRoomsTypes() {
        try(BufferedReader reader = new BufferedReader(new FileReader(ROOMSTYPE_FILE))) {
            File file = new File(ROOMSTYPE_FILE);
            if(!file.exists()) {
                System.out.println(">! Rooms file not found at: " + ROOMSTYPE_FILE);
                return;
            }

            String line = reader.readLine(); //so we skip the first line

            while((line = reader.readLine()) != null) {
                String []details = line.split(",");
                String type = details[0];
                String []amenities = details[1].split(";");
                roomTypes.put(type, amenities);
            }

            System.out.println("----- LOADED " + roomTypes.size() + " TYPES OF ROOMS -----\n");
        } catch (IOException e) {
            System.out.print("Failed to load rooms types --> ");
            System.out.println(e.getMessage());
        }
    }

    public static void loadRooms() {
        try(BufferedReader reader = new BufferedReader(new FileReader(ROOMS_FILE))) {
            File file = new File(ROOMS_FILE);
            if(!file.exists()) {
                System.out.println(">! Rooms file not found at: " + ROOMS_FILE);
                return;
            }

            String line = reader.readLine();

            while((line = reader.readLine()) != null) {
                String []details = line.split(",");
                int roomNumber = Integer.parseInt(details[0]);
                String roomType = details[1];
                double pricePerNight = Double.parseDouble(details[2]);
                double cancellationFee = Double.parseDouble(details[3]);
                String status = details[4];

                Room room = new Room(roomNumber, roomType, pricePerNight, cancellationFee, status);
                rooms.add(room);
                System.out.println("----- LOADED " + rooms.size() + " ROOMS -----\n");
            }
        } catch (IOException e) {
            System.out.println(">! Failed to load rooms --> ");
            System.out.println(e.getMessage());
        }
    }

    public static void printRooms(String roomType) {
        System.out.println("----- AVAILABLE ROOMS -----");
        for(Room room : rooms) {
            if(room.getStatus().equalsIgnoreCase("Available") && (roomType == null || room.getRoomType().equalsIgnoreCase(roomType)))
                System.out.println(room);
        }
    }

    public static List<Room> findAvailableRooms(String roomType) {
        if (!roomTypes.containsKey(roomType))
            throw new IllegalArgumentException(">! Invalid room type. Available types: " + roomTypes.keySet());

        List<Room> availableRooms = new ArrayList<>();
        for (Room room : rooms) {
            if (room.getRoomType().equalsIgnoreCase(roomType) && room.getStatus().equalsIgnoreCase("Available"))
                availableRooms.add(room);
        }

        return availableRooms;
    }
}
