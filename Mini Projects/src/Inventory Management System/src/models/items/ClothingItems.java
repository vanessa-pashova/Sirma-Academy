package models.items;

public class ClothingItems extends AbstractItem {
    public enum ClothesCategory {
        SHIRT,
        TROUSERS,
        JACKET,
        SHOES
    }

    public enum Brands {
        NIKE,
        TOMMY_HILFIGER,
        ZARA
    }

    private final String clothingID;
    private static int clothingIDCounter = 1; // Static counter for generating new IDs
    private ClothesCategory clothesCategory;
    private Brands brand;
    private String size;
    private String color;

    // Generate a unique ID
    private String generateClothingID() {
        return "C-" + (clothingIDCounter++);
    }

    // Constructor for creating new items
    public ClothingItems(String nameOfItem, double discount, double price, String brand, String size, String color, String details) {
        super(nameOfItem, discount, price, "CLOTHING");
        this.clothingID = generateClothingID();
        this.setClothesCategory(nameOfItem);
        this.setBrand(brand);
        this.setSize(size);
        this.setColor(color);
        this.setItemDetails(details);
    }

    // Constructor for loading items with existing IDs
    public ClothingItems(String id, String nameOfItem, double discount, double price, String brand, String size, String color, String details) {
        super(nameOfItem, discount, price, "CLOTHING");
        this.clothingID = id;
        this.setClothesCategory(nameOfItem);
        this.setBrand(brand);
        this.setSize(size);
        this.setColor(color);
        this.setItemDetails(details);
    }

    @Override
    public String getID() {
        return this.clothingID;
    }

    public ClothesCategory getClothesCategory() {
        return this.clothesCategory;
    }

    public Brands getBrand() {
        return this.brand;
    }

    public String getSize() {
        return this.size;
    }

    public String getColor() {
        return this.color;
    }

    @Override
    public void setName(String name) {
        if(!("T-SHIRT".equalsIgnoreCase(name) || "CROP-TOP".equalsIgnoreCase(name) || "SHIRT".equalsIgnoreCase(name) || "TROUSERS".equalsIgnoreCase(name) || "JEANS".equalsIgnoreCase(name) ||
                "WEDGE".equalsIgnoreCase(name) || "JACKET".equalsIgnoreCase(name) || "COAT".equalsIgnoreCase(name) || "SHOES".equalsIgnoreCase(name) || "TRAINERS".equalsIgnoreCase(name)))
            throw new IllegalArgumentException(">! Invalid clothing name, [ClothingItems, setName()].");

        super.setName(name);
    }

    public void setClothesCategory(String name) {
        setName(name);
        switch (this.getName().toUpperCase()) {
            case "T-SHIRT", "CROP-TOP", "SHIRT" -> this.clothesCategory = ClothesCategory.SHIRT;
            case "TROUSERS", "JEANS", "WEDGE"   -> this.clothesCategory = ClothesCategory.TROUSERS;
            case "JACKET", "COAT"               -> this.clothesCategory = ClothesCategory.JACKET;
            case "SHOES", "TRAINERS"            -> this.clothesCategory = ClothesCategory.SHOES;
            default -> throw new IllegalArgumentException(">! Invalid clothing category: " + name + ", [ClothingItems, setName()].");
        }
    }

    public void setBrand(String brand) {
        switch (brand.toUpperCase()) {
            case "NIKE"           -> this.brand = Brands.NIKE;
            case "TOMMY_HILFIGER" -> this.brand = Brands.TOMMY_HILFIGER;
            case "ZARA"           -> this.brand = Brands.ZARA;
            default -> throw new IllegalArgumentException(">! Invalid clothing brand: " + brand + ", [ClothingItems, setBrand()].");
        }
    }

    public void setSize(String size) {
        if (!size.matches("XS|S|M|L|XL"))
            throw new IllegalArgumentException(">! Invalid size of clothing item, [ClothingItems, setSize()].");

        this.size = size;
    }

    public void setColor(String color) {
        if (color.toLowerCase().matches("yellow|green|blue|red|black|white"))
            this.color = color;

        else
            throw new IllegalArgumentException(">! Invalid color of clothing item, [ClothingItems, setColor()].");
    }

    @Override
    public void printDetails() {
        System.out.println("ID: " + this.getID() + " | Name: " + this.getName() +
                " | Discount: " + this.getDiscount() +
                " | Price (after discount): " + this.getPrice() +
                " | Clothing item: " + this.clothesCategory +
                " | Brand: " + this.brand +
                " | Size: " + this.size +
                " | Color: " + this.color +
                " | Category: " + this.getCategory() +
                " | Details: " + this.getItemDetails());
    }

    // Update the static counter based on the highest ID found
    public static void updateClothingIDCounter(int maxID) {
        clothingIDCounter = maxID + 1;
    }
}
