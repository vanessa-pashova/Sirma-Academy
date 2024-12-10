package models.items;

import models.interfaces.AbstractItem;

public class ElectronicItems extends AbstractItem {
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
            default -> throw new IllegalStateException(">! Invalid brand: " + brand + "[ElectronicItems, setBrand()].");
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
