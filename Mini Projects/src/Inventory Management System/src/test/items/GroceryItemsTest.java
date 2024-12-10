package test.items;

import models.items.GroceryItems;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.DateTimeException;
import java.util.zip.DataFormatException;

import static org.junit.jupiter.api.Assertions.*;

class GroceryItemsTest {

    @Test
    void testValidInitialization() {
        // Arrange
        String name = "Apple";
        double discount = 0.1;
        double price = 3.0;
        String description = "Fresh red apple.";
        double weight = 0.5;
        int calories = 52;
        String creationDate = "10-12-2024";
        String expiryDate = "20-12-2024";

        // Act
        GroceryItems grocery = new GroceryItems(name, discount, price, description, weight, calories, creationDate, expiryDate);

        // Assert
        assertEquals(name, grocery.getName(), "Name does not match.");
        assertEquals(Math.round((price - (price * discount)) * 100.0) / 100.0, grocery.getPrice(), "Price does not match.");
        assertEquals(description, grocery.getItemDescription(), "Description does not match.");
        assertEquals(weight, grocery.getWeight(), "Weight does not match.");
        assertEquals(calories, grocery.getCalories(), "Calories do not match.");
        assertEquals("2024-12-10", grocery.getCreationDate(), "Creation date does not match.");
        assertEquals("2024-12-20", grocery.getExpiryDate(), "Expiry date does not match.");
    }

    @Test
    void testPriceCalculationWithNoDiscount() {
        // Arrange
        String name = "Banana";
        double discount = 0.0;
        double price = 1.5;
        String description = "Yellow banana.";
        double weight = 1.0;
        int calories = 89;
        String creationDate = "10-12-2024";
        String expiryDate = "15-12-2024";

        // Act
        GroceryItems grocery = new GroceryItems(name, discount, price, description, weight, calories, creationDate, expiryDate);

        // Assert
        assertEquals(1.5, grocery.getPrice(), "Price without discount does not match.");
    }

    @Test
    void testInvalidWeightThrowsException() {
        // Act & Assert
        Exception exception = assertThrows(IllegalStateException.class, () ->
                new GroceryItems("Heavy Item", 0.1, 10.0, "Too heavy.", 16.0, 200, "10-12-2024", "20-12-2024")
        );

        assertTrue(exception.getMessage().contains("Illegal weight set"), "Expected exception message not found.");
    }

    @Test
    void testInvalidCaloriesThrowsException() {
        // Act & Assert
        Exception exception = assertThrows(IllegalStateException.class, () ->
                new GroceryItems("High Calorie Item", 0.1, 5.0, "Too many calories.", 0.5, 3000, "10-12-2024", "20-12-2024")
        );

        assertTrue(exception.getMessage().contains("Illegal calories set"), "Expected exception message not found.");
    }

    @Test
    void testInvalidDatesThrowsException() {
        // Act & Assert
        Exception exception = assertThrows(DateTimeException.class, () ->
                new GroceryItems("Item With Invalid Dates", 0.1, 2.0, "Invalid date range.", 1.0, 100, "20-12-2024", "10-12-2024")
        );

        assertTrue(exception.getMessage().contains("Expiry date is before the one of the creation"), "Expected exception message not found.");
    }

    @Test
    void testSetAndGetMethods() {
        // Arrange
        GroceryItems grocery = new GroceryItems("Orange", 0.05, 2.0, "Juicy orange.", 0.3, 47, "10-12-2024", "15-12-2024");

        // Act
        grocery.setName("Updated Orange");
        grocery.setDiscount(0.2);
        grocery.setPrice(2.5);
        grocery.setWeight(0.4);
        grocery.setCalories(50);
        grocery.setItemDescription("Updated juicy orange.");
        grocery.setExpiry("11-12-2024", "16-12-2024");

        // Assert
        assertEquals("Updated Orange", grocery.getName(), "Name update failed.");
        assertEquals(2.0, grocery.getPrice(), "Price update failed.");
        assertEquals(0.2, grocery.getDiscount(), "Discount update failed.");
        assertEquals(0.4, grocery.getWeight(), "Weight update failed.");
        assertEquals(50, grocery.getCalories(), "Calories update failed.");
        assertEquals("Updated juicy orange.", grocery.getItemDescription(), "Description update failed.");
        assertEquals("2024-12-11", grocery.getCreationDate(), "Creation date update failed.");
        assertEquals("2024-12-16", grocery.getExpiryDate(), "Expiry date update failed.");
    }

    @Test
    void testPrintDetails() {
        // Arrange
        GroceryItems grocery = new GroceryItems("Milk", 0.1, 3.0, "Fresh milk.", 1.0, 150, "10-12-2024", "12-12-2024");

        // Redirect output to a stream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        // Act
        grocery.printDetails();

        // Restore original output
        System.setOut(originalOut);

        // Assert
        String printedDetails = outputStream.toString();
        assertTrue(printedDetails.contains("Milk"), "Name not printed correctly.");
//        assertTrue(printedDetails.contains("Fresh milk."), "Description not printed correctly.");
        assertTrue(printedDetails.contains("2.7"), "Price after discount not printed correctly.");
        assertTrue(printedDetails.contains("1.0"), "Weight not printed correctly.");
        assertTrue(printedDetails.contains("150"), "Calories not printed correctly.");
    }

    @Test
    void testExtremeDiscount() {
        // Arrange
        double price = 10.0;
        double discount = 0.9; // 90% discount

        // Act
        GroceryItems grocery = new GroceryItems("Cheap Item", discount, price, "Extreme discount.", 0.5, 100, "10-12-2024", "20-12-2024");

        // Assert
        assertEquals(1.0, grocery.getPrice(), "Extreme discount calculation failed.");
    }
}
