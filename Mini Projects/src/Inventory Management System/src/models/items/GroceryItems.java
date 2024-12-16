package models.items;

public class GroceryItems extends AbstractItem {
    private final String groceryID;
    private static int groceryIDCounter = 1;
    private double weight;
    private int calories;

    private String generateGroceryID() {
        return "G-" + (groceryIDCounter++);
    }

    public GroceryItems(String name, double discount, double price, String description, double weight, int calories, String creationDate, String expiryDate) {
        super(name, discount, price, "GROCERIES");
        this.groceryID = generateGroceryID();
        this.setWeight(weight);
        this.setCalories(calories);
        this.setItemDescription(description);
        this.setExpiry(creationDate, expiryDate);
        this.setFragile(false);
        this.setSellable(true);
    }

    @Override
    public String getID() {
        return this.groceryID;
    }

    public double getWeight() {
        return this.weight;
    }

    public int getCalories() {
        return this.calories;
    }

    public void setWeight(double weight) {
        if (weight <= 0 || 15 < weight)
            throw new IllegalStateException(">! Illegal weight set. [GroceryItems, setWeight()].");

        this.weight = weight;
    }

    public void setCalories(int calories) {
        if (calories < 0 || 2500 < calories)
            throw new IllegalStateException(">! Illegal calories set. [GroceryItems].");

        this.calories = calories;
    }

    @Override
    public void printDetails() {
        System.out.println("ID: " + this.getID() + " | Name: " + this.getName() +
                " | Discount: " + this.getDiscount() +
                " | Price (after discount): " + this.getPrice() +
                " | Weight: " + this.getWeight() +
                " | Calories: " + this.getCalories() +
                " | Creation and Expiry date: " + this.getCreationDate() + " -> " + this.getExpiryDate());
    }
}