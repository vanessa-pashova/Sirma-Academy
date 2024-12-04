package models.rooms;

public class Room {
    private int roomNumber;
    private String roomType;

    private double price;
    private double cancellationFee;

    private String status;

    public Room(int roomNumber, String roomType, double price, double cancellationFee, String status) {
        this.setRoomNumber(roomNumber);
        this.setRoomType(roomType);
        this.setPrice(price);
        this.setCancellationFee(cancellationFee);
        this.setStatus(status);
    }

    public int getRoomNumber() {
        return this.roomNumber;
    }

    public String getRoomType() {
        return this.roomType;
    }

    public double getPrice() {
        return this.price;
    }

    public double getCancellationFee() {
        return this.cancellationFee;
    }

    public String getStatus() {
        return this.status;
    }

    public void setRoomNumber(int roomNumber) {
        if(roomNumber < 1)
            throw new IllegalArgumentException(">! Room number must be greater than 0");

        this.roomNumber = roomNumber;
    }

    public void setRoomType(String roomType) {
        if (!RoomManager.getRoomTypes().containsKey(roomType))
            throw new IllegalArgumentException("Invalid room type. Available types: " + RoomManager.getRoomTypes().keySet());

        this.roomType = roomType;
    }


    public void setCancellationFee(double cancellationFee) {
        if(cancellationFee < 0)
            throw new IllegalArgumentException(">! Cancellation fee cannot be negative");

        this.cancellationFee = cancellationFee;
    }

    public void setPrice(double price) {
        if(price < 40.0)
            throw new IllegalArgumentException(">! Price set too low.");

        this.price = price;
    }

    public void setStatus(String status) {
        if(status.equals("Available") || status.equals("Reserved"))
            this.status = status;

        else
            throw new IllegalArgumentException("Status ");
    }

    @Override
    public String toString() {
        return String.format("[ Room %d (%s), %.2f BGN/night ] --> Cancellation fee: %.2f BGN --> Status: %s",
                this.roomNumber, this.roomType, this.price, this.cancellationFee, this.status);
    }
}