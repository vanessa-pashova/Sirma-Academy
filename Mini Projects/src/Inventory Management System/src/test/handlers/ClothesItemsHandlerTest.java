package test.handlers;

import models.items.ClothingItems;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ClothesItemsHandlerTest {
    @Test
    void testValidInitialization() {
        // Arrange
        String name = "TSHIRT";
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
        assertEquals(ClothingItems.ClothesCategory.TSHIRT, clothing.getName(), "Clothing category does not match.");
        assertEquals(size, clothing.getSize(), "Size does not match.");
        assertEquals(color, clothing.getColor(), "Color does not match.");
        assertEquals(details, clothing.getItemDetails(), "Details do not match.");
    }

    @Test
    void testPriceWithoutDiscount() {
        // Arrange
        String name = "JACKETS";
        double price = 100.0;
        double discount = 0.0; // No discount
        String brand = "ZARA";
        String size = "L";
        String color = "black";
        String details = "Stylish black jacket.";

        // Act
        ClothingItems clothing = new ClothingItems(name, discount, price, brand, size, color, details);

        // Assert
        assertEquals(100.0, clothing.getPrice(), "Price calculation without discount failed.");
    }

    @Test
    void testDiscountBoundaryValues() {
        // Arrange
        String name = "JEANS";
        String brand = "TOMMY_HILFIGER";
        String size = "M";
        String color = "red";
        String details = "Stylish jeans.";

        // Act
        ClothingItems clothingZeroDiscount = new ClothingItems(name, 0.0, 50.0, brand, size, color, details);
        ClothingItems clothingMaxDiscount = new ClothingItems(name, 0.99, 50.0, brand, size, color, details);

        // Assert
        assertEquals(50.0, clothingZeroDiscount.getPrice(), "Zero discount price calculation failed.");
        assertEquals(0.5, clothingMaxDiscount.getPrice(), "Maximum discount price calculation failed.");
    }

    @Test
    void testInvalidCategoryThrowsException() {
        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new ClothingItems("HAT", 0.1, 30.0, "NIKE", "S", "red", "Invalid category.")
        );

        assertTrue(exception.getMessage().contains("Invalid clothing category"), "Expected exception message not found.");
    }

    @Test
    void testInvalidBrandThrowsException() {
        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new ClothingItems("TSHIRT", 0.1, 30.0, "ADIDAS", "M", "blue", "Invalid brand.")
        );

        assertTrue(exception.getMessage().contains("Invalid clothing brand"), "Expected exception message not found.");
    }

    @Test
    void testInvalidSizeThrowsException() {
        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new ClothingItems("JEANS", 0.1, 80.0, "ZARA", "XXL", "green", "Invalid size.")
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
        ClothingItems clothing = new ClothingItems("TSHIRT", 0.1, 50.0, "NIKE", "M", "blue", "Details.");

        // Act
        clothing.setName("JACKETS");
        clothing.setDiscount(0.3);
        clothing.setPrice(100.0);
        clothing.setBrand("TOMMY_HILFIGER");
        clothing.setSize("L");
        clothing.setColor("red");
        clothing.setItemDetails("Updated details.");

        // Assert
        assertEquals(70.0, clothing.getPrice(), "Price update failed.");
        assertEquals(ClothingItems.ClothesCategory.JACKETS, clothing.getName(), "Category update failed.");
        assertEquals(ClothingItems.Brands.TOMMY_HILFIGER, clothing.getBrand(), "Brand update failed.");
        assertEquals("L", clothing.getSize(), "Size update failed.");
        assertEquals("red", clothing.getColor(), "Color update failed.");
        assertEquals("Updated details.", clothing.getItemDetails(), "Details update failed.");
    }

    @Test
    void testEdgeCasePrice() {
        // Arrange
        double price = 0.10;
        String name = "SHOES";
        String brand = "NIKE";
        String size = "M";
        String color = "black";
        String details = "Minimum valid price.";

        // Act
        ClothingItems clothing = new ClothingItems(name, 0.0, price, brand, size, color, details);

        // Assert
        assertEquals(0.10, clothing.getPrice(), "Edge case price failed.");
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
    void testDiscountZeroAndHigh() {
        // Arrange
        double price = 200.0;
        ClothingItems clothingZero = new ClothingItems("JACKETS", 0.0, price, "TOMMY_HILFIGER", "M", "blue", "Details.");
        ClothingItems clothingHigh = new ClothingItems("TSHIRT", 0.9, price, "NIKE", "L", "red", "Details.");

        // Assert
        assertEquals(200.0, clothingZero.getPrice(), "Price without discount failed.");
        assertEquals(20.0, clothingHigh.getPrice(), "Price with high discount failed.");
    }
}
