package models.items;

import models.interfaces.AbstractItem;

public class ClothingItems extends AbstractItem {
    public enum ClothesCategory {
        TSHIRTS,
        JEANS,
        JACKETS,
        SHOES;
    }

    public enum Brands {
        NIKE,
        TOMMY_HILFIGER,
        ZARA;
    }

    private ClothesCategory clothesCategory;
    private Brands brand;
    private String size;
    private String color;
    private static int clothingIDCounter = 1;

    private String generateClothingID() {
        return "C-" + (clothingIDCounter++);
    }

    public ClothingItems(String nameOfItem, double discount, double price, String brand, String size, String color, String details) {
        super(nameOfItem, discount, price, CategorizableType.CLOTHING);
        this.setBrand(brand);
        this.setSize(size);
        this.setColor(color);
        this.setItemDetails(details);
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
            case "TSHIRTS" -> this.clothesCategory = ClothesCategory.TSHIRTS;
            case "JEANS" -> this.clothesCategory = ClothesCategory.JEANS;
            case "JACKETS" -> this.clothesCategory = ClothesCategory.JACKETS;
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
        System.out.println("Name: " + this.getName() +
                " | Discount: " + this.getDiscount() +
                " | Price (after discount): " + this.getPrice() +
                " | Clothing item: " + this.clothesCategory +
                " | Brand: " + this.brand +
                " | Size: " + this.size +
                " | Color: " + this.color +
                " | Details: " + this.getItemDetails());
    }
}
