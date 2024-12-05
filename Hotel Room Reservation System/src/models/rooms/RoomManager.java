package models.rooms;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoomManager {
    private static final String ROOMS_FILE = "/Users/vanessa.pashova/Desktop/Sirma Academy 24/Hotel Room Reservation System/src/data/rooms.cvs";
    private static final String ROOMSTYPE_FILE = "/Users/vanessa.pashova/Desktop/Sirma Academy 24/Hotel Room Reservation System/src/data/room_types.cvs";

    private static final List<Room> rooms = new ArrayList<>();
    private static final Map<String, String[]> roomTypes = new HashMap<>();

    public static Map<String, String[]> getRoomTypes() {
        return roomTypes;
    }

    public static List<Room>getRooms() {
        return rooms;
    }

    private static void saveRooms() {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(ROOMS_FILE, true))) {
            File file = new File(ROOMS_FILE);
            if(!file.exists()) {
                file.createNewFile();
                System.out.println("------- saveRooms() CREATED FILE: " + file.getAbsolutePath() + " -------");
            }

            for(Room room : rooms) {
                writer.write(room.getRoomNumber() + "," + room.getRoomType() + "," + room.getPrice() + "," + room.getCancellationFee() + "," + room.getStatus() + '\n');
                writer.newLine();
            }

            System.out.println("------- saveRooms() FINISHED FILE: " + file.getAbsolutePath() + " -------");
        } catch (IOException e) {
            System.out.println(">! Error while saving rooms to cvs file.");
            System.out.println(e.getMessage());
        }
    }

    public static Room findRoomByNumber(int roomNumber) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber)
                return room;
        }

        return null;
    }

    public static void loadRoomsTypes() {
        try (BufferedReader reader = new BufferedReader(new FileReader(ROOMSTYPE_FILE))) {
            File file = new File(ROOMSTYPE_FILE);
            if (!file.exists()) {
                System.out.println(">! Rooms types file not found at: " + ROOMSTYPE_FILE);
                return;
            }

            String line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                if (details.length != 3) {
                    System.out.println(">! Skipping invalid line: " + line);
                    continue;
                }

                String type = details[0].trim();
                String[] amenities = details[1].split(";");

                roomTypes.put(type, amenities);
            }

            System.out.println("------- LOADED " + roomTypes.size() + " TYPES OF ROOMS -------");
        } catch (IOException e) {
            System.out.println(">! Failed to load room types --> " + e.getMessage());
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

            while ((line = reader.readLine()) != null) {
                try {
                    String[] details = line.split(",");
                    int roomNumber = Integer.parseInt(details[0]);
                    String roomType = details[1];
                    double pricePerNight = Double.parseDouble(details[2]);
                    double cancellationFee = Double.parseDouble(details[3]);
                    String status = details[4];

                    Room room = new Room(roomNumber, roomType, pricePerNight, cancellationFee, status);
                    rooms.add(room);
                    System.out.println("> Loaded room: " + room);
                } catch (Exception e) {
                    System.out.println(">! Error processing line: " + line);
                    System.out.println(e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println(">! Failed to load rooms --> ");
            System.out.println(e.getMessage());
        }
    }

    public static List<Room> findAvailableRooms(String roomType) {
        List<Room> availableRooms = new ArrayList<>();

        if (roomType == null || roomType.isEmpty()) {
            for (Room room : rooms) {
                if (room.getStatus().equalsIgnoreCase("Available"))
                    availableRooms.add(room);
            }
        }

        else {
            if (!roomTypes.containsKey(roomType))
                throw new IllegalArgumentException(">! Invalid room type. Allowed types: " + roomTypes.keySet());

            for (Room room : rooms) {
                if (room.getRoomType().equalsIgnoreCase(roomType) && room.getStatus().equalsIgnoreCase("Available"))
                    availableRooms.add(room);
            }
        }

        return availableRooms;
    }

    public static void updateRoomStatusInFile(Room room) {
        try {
            File file = new File(ROOMS_FILE);
            if (!file.exists()) {
                System.out.println(">! Rooms file not found. Cannot update status.");
                return;
            }

            List<String> updatedLines = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;

                while ((line = reader.readLine()) != null) {
                    String[] details = line.split(",");
                    if (details[0].equals(String.valueOf(room.getRoomNumber()))) {
                        details[4] = room.getStatus();
                        updatedLines.add(String.join(",", details));
                    }

                    else
                        updatedLines.add(line);
                }
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, false))) {
                for (String updatedLine : updatedLines) {
                    writer.write(updatedLine);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println(">! Error while updating room status in file: " + e.getMessage());
            e.printStackTrace();
        }
    }

}