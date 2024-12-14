package models.items;

public class ElectronicItems extends AbstractItem {
    public enum Company {   //public only for the tests
        APPLE,
        PHILIPS,
        SAMSUNG;
    }

    private final String electronicID;
    private static int electronicIDCounter = 1;
    private int warrantyPeriod;
    private Company brand;

    private String generateElectronicID() {
        return "E-" + (electronicIDCounter++);
    }

    public ElectronicItems(String name, double discount, double price, String brand, int warrantyPeriod,  String details) {
        super(name, discount, price, CategorizableType.ELECTRONICS);
        this.electronicID = generateElectronicID();
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
            default -> throw new IllegalStateException(">! Invalid brand: " + brand + " [ElectronicItems, setBrand()].");
        }
    }

    public void setWarrantyPeriod(int warrantyPeriod) {
        if (warrantyPeriod < 0)
            throw new IllegalArgumentException(">! Warranty period cannot be negative, [ElectronicItems, setWarrantyPeriod].");

        else if (0 <= warrantyPeriod && warrantyPeriod <= 36)    //warranty value == months
            this.warrantyPeriod = warrantyPeriod;

        else
            throw new IllegalStateException(">! Warranty period must be between 0 and 3 (inclusive), [ElectronicItems, setWarrantyPeriod].");
    }

    @Override
    public void printDetails() {
        System.out.println("Name: " + this.getName() +
                " | Discount: " + this.calculatePrice() +
                " | Price (after discount: " + this.getPrice() +
                " | Brand: " + this.brand +
                " | Warranty Period: " + this.getWarrantyPeriod() +
                " | Category: " + this.getCategory() +
                " | Details: " + this.getItemDetails());
    }
}
