package test.items;

import models.items.ClothingItems;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ClothingItemsTest {
    @Test
    void testValidInitialization() {
        // Arrange
        String name = "T-SHIRT";
        double discount = 0.2;
        double price = 50.0;
        String brand = "NIKE";
        String size = "M";
        String color = "blue";
        String details = "Comfortable cotton t-shirt.";

        // Act
        ClothingItems clothing = new ClothingItems(name, discount, price, brand, size, color, details);

        // Assert
        assertEquals(40.0, clothing.getPrice(), "Price calculation with discount failed.");
        assertEquals(ClothingItems.Brands.NIKE, clothing.getBrand(), "Brand does not match.");
        assertEquals(ClothingItems.ClothesCategory.TSHIRT, clothing.getClothesCategory(), "Clothing category does not match.");
        assertEquals(size, clothing.getSize(), "Size does not match.");
        assertEquals(color, clothing.getColor(), "Color does not match.");
        assertEquals(details, clothing.getItemDetails(), "Details do not match.");
    }

    @Test
    void testPriceWithoutDiscount() {
        // Arrange
        String name = "JEANS";
        double price = 80.0;
        double discount = 0.0; // No discount
        String brand = "ZARA";
        String size = "L";
        String color = "black";
        String details = "Stylish black jeans.";

        // Act
        ClothingItems clothing = new ClothingItems(name, discount, price, brand, size, color, details);

        // Assert
        assertEquals(80.0, clothing.getPrice(), "Price calculation without discount failed.");
    }

    @Test
    void testInvalidCategoryThrowsException() {
        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new ClothingItems("HATS", 0.1, 30.0, "NIKE", "S", "red", "Invalid category.")
        );

        assertTrue(exception.getMessage().contains("Invalid clothing category"), "Expected exception message not found.");
    }

    @Test
    void testInvalidBrandThrowsException() {
        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new ClothingItems("T-SHIRT", 0.1, 30.0, "ADIDAS", "M", "blue", "Invalid brand.")
        );

        assertTrue(exception.getMessage().contains("Invalid clothing brand"), "Expected exception message not found.");
    }

    @Test
    void testInvalidSizeThrowsException() {
        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new ClothingItems("JACKET", 0.1, 100.0, "ZARA", "XXL", "black", "Invalid size.")
        );

        assertTrue(exception.getMessage().contains("Invalid size of clothing item"), "Expected exception message not found.");
    }

    @Test
    void testInvalidColorThrowsException() {
        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new ClothingItems("SHOES", 0.1, 60.0, "TOMMY_HILFIGER", "M", "purple", "Invalid color.")
        );

        assertTrue(exception.getMessage().contains("Invalid color of clothing item"), "Expected exception message not found.");
    }

    @Test
    void testSetAndGetMethods() {
        // Arrange
        ClothingItems clothing = new ClothingItems("T-SHIRT", 0.1, 20.0, "NIKE", "M", "blue", "Details.");

        // Act
        clothing.setName("JACKET");
        clothing.setDiscount(0.3);
        clothing.setPrice(100.0);
        clothing.setBrand("TOMMY_HILFIGER");
        clothing.setSize("L");
        clothing.setColor("red");
        clothing.setItemDetails("Updated details.");

        // Assert
        assertEquals(70.0, clothing.getPrice(), "Price update failed.");
        assertEquals(ClothingItems.ClothesCategory.JACKET, clothing.getClothesCategory(), "Category update failed.");
        assertEquals(ClothingItems.Brands.TOMMY_HILFIGER, clothing.getBrand(), "Brand update failed.");
        assertEquals("L", clothing.getSize(), "Size update failed.");
        assertEquals("red", clothing.getColor(), "Color update failed.");
        assertEquals("Updated details.", clothing.getItemDetails(), "Details update failed.");
    }

    @Test
    void testPrintDetails() {
        // Arrange
        ClothingItems clothing = new ClothingItems("SHOES", 0.25, 120.0, "ZARA", "S", "yellow", "Stylish shoes.");

        // Redirecting output to a stream for verification
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        // Act
        clothing.printDetails();

        // Restore original output stream
        System.setOut(originalOut);

        // Assert
        String printedDetails = outputStream.toString();
        assertTrue(printedDetails.contains("SHOES"), "Category not printed correctly.");
        assertTrue(printedDetails.contains("ZARA"), "Brand not printed correctly.");
        assertTrue(printedDetails.contains("yellow"), "Color not printed correctly.");
        assertTrue(printedDetails.contains("90.0"), "Price not printed correctly.");
    }

    @Test
    void testExtremeDiscount() {
        // Arrange
        double price = 100.0;
        double discount = 0.99; // 99% discount

        // Act
        ClothingItems clothing = new ClothingItems("T-SHIRT", discount, price, "NIKE", "M", "blue", "Extreme discount.");

        // Assert
        assertEquals(1.0, clothing.getPrice(), "Price calculation with extreme discount failed.");
    }

    @Test
    void testZeroPriceThrowsException() {
        // Act & Assert
        Exception exception = assertThrows(IllegalStateException.class, () ->
                new ClothingItems("T-SHIRT", 0.1, 0.0, "NIKE", "M", "blue", "Zero price.")
        );

        assertTrue(exception.getMessage().contains(">! Price cannot be less than 0.10 [AbstactItem, setPrice()]."), "Expected exception message not found.");
    }

    @Test
    void testNegativePriceThrowsException() {
        // Act & Assert
        Exception exception = assertThrows(IllegalStateException.class, () ->
                new ClothingItems("T-SHIRT", 0.1, -50.0, "NIKE", "M", "blue", "Negative price.")
        );

        assertTrue(exception.getMessage().contains(">! Price cannot be less than 0.10 [AbstactItem, setPrice()]."), "Expected exception message not found.");
    }

    @Test
    void testExtremePriceAndDiscount() {
        // Arrange
        String name = "T-SHIRT";
        double price = 1_000.0; // Large price
        double discount = 0.5;  // 50% discount
        String brand = "NIKE";
        String size = "M";
        String color = "yellow";
        String details = "High-end exclusive t-shirt.";

        // Act
        ClothingItems clothing = new ClothingItems(name, discount, price, brand, size, color, details);

        // Assert
        assertEquals(500.0, clothing.getPrice(), "Price calculation for high-value item failed.");
    }
}
