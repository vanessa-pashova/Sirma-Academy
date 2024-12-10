package models.items;

public class ElectronicItems extends InventoryManager {
    enum Company {
        APPLE,
        PHILIPS,
        SAMSUNG;
    }

    private int warrantyPeriod;
    private Company brand;
    private static int electronicIDCounter = 1;

    private String generateElectronicID() {
        return "E-" + (electronicIDCounter++);
    }

    public ElectronicItems(String name, double price, String brand, int warrantyPeriod, double discount, String details) {
        super(name, price, CategorizableType.ELECTRONICS, discount);
        this.setBrand(brand);
        this.setWarrantyPeriod(warrantyPeriod);
        this.setFragile(true);
        this.setSellable(true);
        this.setItemDetails(details);
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
        }
    }

    public void setWarrantyPeriod(int warrantyPeriod) {
        if(warrantyPeriod < 0)
            throw new IllegalArgumentException(">! Warranty period cannot be negative, [ElectronicItems, setWarrantyPeriod].");

        else if(0 <= warrantyPeriod && warrantyPeriod <= 36)    //warranty value == months
            this.warrantyPeriod = warrantyPeriod;

        else
            throw new IllegalStateException(">! Warranty period must be between 0 and 3 (inclusive), [ElectronicItems, setWarrantyPeriod].");
    }

    @Override
    public void printDetails() {
        if(this.getInventory().isEmpty())
            System.out.println("------ ELECTRONIC INVENTORY'S EMPTY, NOTHING TO SHOW ------");

        else {
            System.out.println("------ PRINTING ELECTRONIC INVENTORY INFORMATION ------");
            this.getInventory().forEach((id, item) -> {
                System.out.println("ID: " + id +
                        "| Name: " + item.getName() +
                        "| Discount: " + item.calculatePrice() +
                        "| Price (after discount: " + item.getPrice() +
                        "| Brand: " + this.brand +
                        "| Warranty Period: " + this.getWarrantyPeriod() +
                        "| Category: " + item.getCategory() +
                        "| Details: " + item.getItemDetails());
            });
            System.out.println("> Total Items: " + this.getInventory().size());
            System.out.println("-------------------------------------------");
        }
    }
}
