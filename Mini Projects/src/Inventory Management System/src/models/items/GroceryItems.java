package models.items;

public class GroceryItems extends AbstractItem {
    private final String groceryID;
    private static int groceryIDCounter = 1; // Static counter for unique IDs
    private double weight;
    private int calories;

    // Generate a unique ID for new grocery items
    private String generateGroceryID() {
        return "G-" + (groceryIDCounter++);
    }

    // Constructor for new items
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

    // Constructor for loading items with existing IDs
    public GroceryItems(String id, String name, double discount, double price, String description, double weight, int calories, String creationDate, String expiryDate) {
        super(name, discount, price, "GROCERIES");
        this.groceryID = id;
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
        if (weight <= 0 || weight > 15)
            throw new IllegalArgumentException(">! Illegal weight. Must be > 0 and <= 15 [GroceryItems, setWeight()].");

        this.weight = weight;
    }

    public void setCalories(int calories) {
        if (calories < 0 || calories > 2500)
            throw new IllegalArgumentException(">! Illegal calories. Must be >= 0 and <= 2500 [GroceryItems, setCalories()].");

        this.calories = calories;
    }

    @Override
    public void printDetails() {
        System.out.println("ID: " + this.getID() + " | Name: " + this.getName() +
                " | Discount: " + this.getDiscount() +
                " | Price (after discount): " + this.getPrice() +
                " | Weight: " + this.getWeight() +
                " | Calories: " + this.getCalories() +
                " | Creation and Expiry date: " + this.getCreationDate() + " -> " + this.getExpiryDate() +
                " | Category: " + this.getCategory() +
                " | Description: " + this.getItemDescription());
    }

    // Update the static counter based on the highest existing ID
    public static void updateGroceryIDCounter(int maxID) {
        groceryIDCounter = maxID + 1;
    }
}
