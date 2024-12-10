package models.items;

public class GroceryItems extends InventoryManager {
    private double weight;
    private int calories;
    private static int groceryIDCounter = 1;

    private String generateGroceryID() {
        return "G-" + (groceryIDCounter++);
    }
    public GroceryItems(String name, double price, double discount, String description, double weight, int calories, String creationDate, String expiryDate) {
        super(name, price, CategorizableType.GROCERIES, discount);
        this.setWeight(weight);
        this.setCalories(calories);
        this.setItemDescription(description);
        this.setExpiry(creationDate, expiryDate);
        this.setFragile(false);
        this.setSellable(true);
    }

    public double getWeight() {
        return this.weight;
    }

    public int getCalories() {
        return this.calories;
    }

    public void setWeight(double weight) {
        if(weight <= 0 || 15 < weight)
            throw new IllegalStateException(">! Illegal weight set. [GroceryItems, setWeight()].");

        this.weight = weight;
    }

    public void setCalories(int calories) {
        if(calories < 0 || 2500 < calories)
            throw new IllegalStateException(">! Illegal calories set. [GroceryItems].");

        this.calories = calories;
    }

    @Override
    public void printDetails() {
        if(this.getInventory().isEmpty())
            System.out.println("------ GROCERY INVENTORY'S EMPTY, NOTHING TO SHOW ------");

        else {
            System.out.println("------ PRINTING GROCERY INVENTORY INFORMATION ------");
            this.getInventory().forEach((id, item) -> {
                System.out.println("ID: " + id +
                "| Name: " + item.getName() +
                "| Discount: " + item.getDiscount() +
                "| Price (after discount): " + item.getPrice() +
                "| Weight: " + this.getWeight() +
                "| Calories: " + this.getCalories() +
                "| Creation and Expiry date: " + this.getCreationDate() + " -> " + this.getExpiryDate());
            });
            System.out.println("> Total Items: " + this.getInventory().size());
            System.out.println("-------------------------------------------");
        }
    }
}