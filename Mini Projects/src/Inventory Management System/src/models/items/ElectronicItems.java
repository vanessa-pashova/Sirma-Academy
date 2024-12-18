package models.items;

public class ElectronicItems extends AbstractItem {
    public enum Company {
        APPLE,
        PHILIPS,
        SAMSUNG
    }

    private final String electronicID;
    private static int electronicIDCounter = 1; // Static counter for generating new IDs
    private int warrantyPeriod;
    private Company brand;

    // Generate a unique ID for new items
    private String generateElectronicID() {
        return "E-" + (electronicIDCounter++);
    }

    // Constructor for new items
    public ElectronicItems(String name, double discount, double price, String brand, int warrantyPeriod, String details) {
        super(name, discount, price, "ELECTRONICS");
        this.electronicID = generateElectronicID();
        this.setBrand(brand);
        this.setWarrantyPeriod(warrantyPeriod);
        this.setFragile(true);
        this.setSellable(true);
        this.setItemDetails(details);
    }

    // Constructor for loading items with existing IDs
    public ElectronicItems(String id, String name, double discount, double price, String brand, int warrantyPeriod, String details) {
        super(name, discount, price, "ELECTRONICS");
        this.electronicID = id;
        this.setBrand(brand);
        this.setWarrantyPeriod(warrantyPeriod);
        this.setFragile(true);
        this.setSellable(true);
        this.setItemDetails(details);
    }

    @Override
    public String getID() {
        return this.electronicID;
    }

    public Company getBrand() {
        return brand;
    }

    public int getWarrantyPeriod() {
        return this.warrantyPeriod;
    }

    public void setBrand(String brand) {
        switch (brand.toUpperCase()) {
            case "APPLE" -> this.brand = Company.APPLE;
            case "PHILIPS" -> this.brand = Company.PHILIPS;
            case "SAMSUNG" -> this.brand = Company.SAMSUNG;
            default -> throw new IllegalArgumentException(">! Invalid brand: " + brand + " [ElectronicItems, setBrand()].");
        }
    }

    public void setWarrantyPeriod(int warrantyPeriod) {
        if (warrantyPeriod < 0 || warrantyPeriod > 36)
            throw new IllegalArgumentException(">! Warranty period must be between 0 and 36 months, [ElectronicItems, setWarrantyPeriod()].");

        this.warrantyPeriod = warrantyPeriod;
    }

    @Override
    public void printDetails() {
        System.out.println("ID: " + this.getID() + " | Name: " + this.getName() +
                " | Discount: " + this.getDiscount() +
                " | Price (after discount): " + this.getPrice() +
                " | Brand: " + this.brand +
                " | Warranty Period: " + this.getWarrantyPeriod() +
                " | Category: " + this.getCategory() +
                " | Details: " + this.getItemDetails());
    }

    // Update the static counter based on the highest ID found
    public static void updateElectronicIDCounter(int maxID) {
        electronicIDCounter = maxID + 1;
    }
}
