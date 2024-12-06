package models.items;

public class ElectronicItems extends InventoryManager {
    private int warrantyPeriod;

    public ElectronicItems(String name, double price, int warrantyPeriod) {
        super(name, price, CategorizableType.ELECTRONICS);
        this.setWarrantyPeriod(warrantyPeriod);
    }

    public int getWarrantyPeriod() {
        return this.warrantyPeriod;
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
    public void printInventory() {
        if(this.getInventory().isEmpty())
            System.out.println("------ ELECTRONIC INVENTORY'S EMPTY, NOTHING TO SHOW ------");

        else {
            System.out.println("------ PRINTING ELECTRONIC INVENTORY INFORMATION ------");
            this.getInventory().forEach((id, item) -> {
                System.out.println("ID: " + id +
                        ", Name: " + item.getName() +
                        ", Discount: " + item.calculatePrice() +
                        ", Price (after discount: " + item.getPrice() +
                        ", Warranty Period: " + this.getWarrantyPeriod() +
                        ", Category: " + item.getCategory());
            });
            System.out.println("> Total Items: " + this.getInventory().size());
            System.out.println("-------------------------------------------");
        }
    }
}
