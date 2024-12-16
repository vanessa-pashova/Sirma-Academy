package models.items;

public class ClothingItems extends AbstractItem {
    public enum ClothesCategory {
        TSHIRT,
        JEANS,
        JACKET,
        SHOES
    }

    public enum Brands {
        NIKE,
        TOMMY_HILFIGER,
        ZARA
    }

    private final String clothingID;
    private static int clothingIDCounter = 1;
    private ClothesCategory clothesCategory;
    private Brands brand;
    private String size;
    private String color;

    private String generateClothingID() {
        return "C-" + (clothingIDCounter++);
    }

    public ClothingItems(String nameOfItem, double discount, double price, String brand, String size, String color, String details) {
        super(nameOfItem, discount, price, "CLOTHING");
        this.clothingID = generateClothingID();
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

    public void setName(String name) {
        switch (name.toUpperCase()) {
            case "T-SHIRT" -> this.clothesCategory = ClothesCategory.TSHIRT;
            case "JEANS" -> this.clothesCategory = ClothesCategory.JEANS;
            case "JACKET" -> this.clothesCategory = ClothesCategory.JACKET;
            case "SHOES" -> this.clothesCategory = ClothesCategory.SHOES;
            default -> throw new IllegalArgumentException(">! Invalid clothing category: " + name + ", [ClothingItems, setName()].");
        }
    }

    public void setBrand(String brand) {
        switch (brand.toUpperCase()) {
            case "NIKE" -> this.brand = Brands.NIKE;
            case "TOMMY_HILFIGER" -> this.brand = Brands.TOMMY_HILFIGER;
            case "ZARA" -> this.brand = Brands.ZARA;
            default -> throw new IllegalArgumentException(">! Invalid clothing brand: " + brand + ", [ClothingItems, setBrand()].");
        }
    }

    public void setSize(String size) {
        if (!size.matches("XS|S|M|L|XL"))
            throw new IllegalArgumentException(">! Invalid size of clothing item, [ClothingItems, setSize()].");

        this.size = size;
    }

    public void setColor(String color) {
        if (color.toLowerCase().equals("yellow") || color.toLowerCase().equals("green") || color.toLowerCase().equals("blue") ||
                color.toLowerCase().equals("red") || color.toLowerCase().equals("black") || color.toLowerCase().equals("white")) {
            this.color = color;
        } else
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
                " | Details: " + this.getItemDetails());
    }
}
