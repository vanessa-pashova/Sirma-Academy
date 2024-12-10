package models.items;

public class ClothingItems extends InventoryManager {
    enum ClothesCategory {
        TSHIRTS,
        JEANS,
        JACKETS,
        SHOES;
    }

    enum Brands {
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

    public ClothingItems(String nameOfItem, double price, double discount, String brand, String size, String color, String details) {
        this.setName(nameOfItem);
        this.setPrice(price);
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
        if (name.toUpperCase().equals("TSHIRTS") || name.toUpperCase().equals("T-SHIRTS"))
            this.clothesCategory = ClothesCategory.TSHIRTS;

        else if(name.toUpperCase().equals("JEANS"))
            this.clothesCategory = ClothesCategory.JEANS;

        else if(name.toUpperCase().equals("JACKETS"))
            this.clothesCategory = ClothesCategory.JACKETS;

        else if(name.toUpperCase().equals("SHOES"))
            this.clothesCategory = ClothesCategory.SHOES;

        else
            throw new IllegalArgumentException(">! Invalid name of clothing item, [ClothingItems, setName()].");
    }

    public void setBrand(String brand) {
        if(brand.toUpperCase().equals("NIKE"))
            this.brand = Brands.NIKE;

        else if(brand.toUpperCase().equals("TOMMY_HILFIGER"))
            this.brand = Brands.TOMMY_HILFIGER;

        else if(brand.toUpperCase().equals("ZARA"))
            this.brand = Brands.ZARA;

        else
            throw new IllegalArgumentException(">! Invalid brand of clothing item, [setBrand()].");
    }

    public void setSize(String size) {
        if(!size.matches("XS|S|M|L|XL"))
            throw new IllegalArgumentException(">! Invalid size of clothing item, [ClothingItems, setSize()].");

        this.size = size;
    }

    public void setColor(String color) {
        if(color.toLowerCase().equals("yellow") || color.toLowerCase().equals("green") || color.toLowerCase().equals("blue") ||
                color.toLowerCase().equals("red") || color.toLowerCase().equals("black") || color.toLowerCase().equals("white")) {
            this.color = color;
        }

        else
            throw new IllegalArgumentException(">! Invalid color of clothing item, [ClothingItems, setColor()].");
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
                        "| Clothing item: " + this.clothesCategory +
                        "| Brand: " + this.brand +
                        "| Size: " + this.size +
                        "| Color: " + this.color +
                        "| Details: " + this.getItemDetails());
            });
            System.out.println("> Total Items: " + this.getInventory().size());
            System.out.println("-------------------------------------------");
        }
    }
}
