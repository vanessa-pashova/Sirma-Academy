package models.items;

public class GroceryItems extends InventoryManager {
    private double weight;
    private int calories;

    public GroceryItems(String name, double price, double weight, int calories) {
        super(name, price, CategorizableType.GROCERIES);
        this.setWeight(weight);
        this.setCalories(calories);
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
    public void printInventory() {
        if(this.getInventory().isEmpty())
            System.out.println("------ GROCERY INVENTORY'S EMPTY, NOTHING TO SHOW ------");

        else {
            System.out.println("------ PRINTING GROCERY INVENTORY INFORMATION ------");
            this.getInventory().forEach((id, item) -> {
                System.out.println("ID: " + id +
                " Name: " + item.getName() +
                " Discount: " + item.getDiscount() +
                " Price (after discount): " + item.getPrice() +
                " Weight: " + this.getWeight() +
                " Calories: " + this.getCalories());
            });
            System.out.println("> Total Items: " + this.getInventory().size());
            System.out.println("-------------------------------------------");
        }
    }
}