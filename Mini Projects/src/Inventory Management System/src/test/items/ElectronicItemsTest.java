package test.items;

import models.items.ElectronicItems;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ElectronicItemsTest {

    @Test
    void testValidInitialization() {
        // Arrange
        String name = "IPhone 14";
        double discount = 0.1;
        double price = 1000.0;
        String brand = "APPLE";
        int warrantyPeriod = 24;
        String details = "Latest iPhone model with advanced features.";

        // Act
        ElectronicItems electronic = new ElectronicItems(name, discount, price, brand, warrantyPeriod, details);

        // Assert
        assertEquals(name, electronic.getName(), "Electronic name does not match.");
        assertEquals(discount, electronic.getDiscount(), "Discount does not match.");
        assertEquals(900.0, electronic.getPrice(), "Price after discount does not match.");
        assertEquals(ElectronicItems.Company.APPLE, electronic.getBrand(), "Brand does not match.");
        assertEquals(warrantyPeriod, electronic.getWarrantyPeriod(), "Warranty period does not match.");
        assertEquals(details, electronic.getItemDetails(), "Details do not match.");
    }

    @Test
    void testPriceWithNoDiscount() {
        // Arrange
        String name = "Samsung TV";
        double price = 500.0;
        double discount = 0.0; // No discount
        String brand = "SAMSUNG";
        int warrantyPeriod = 12;
        String details = "Smart TV with 4K resolution.";

        // Act
        ElectronicItems electronic = new ElectronicItems(name, discount, price, brand, warrantyPeriod, details);

        // Assert
        assertEquals(500.0, electronic.getPrice(), "Price without discount does not match.");
        assertEquals(ElectronicItems.Company.SAMSUNG, electronic.getBrand(), "Brand does not match.");
    }

    @Test
    void testInvalidBrandThrowsException() {
        // Act & Assert
        Exception exception = assertThrows(IllegalStateException.class, () ->
                new ElectronicItems("Unknown Brand Device", 0.1, 200.0, "XYZ", 12, "Unknown brand electronic.")
        );

        assertTrue(exception.getMessage().contains("Invalid brand"), "Expected exception message not found.");
    }

    @Test
    void testInvalidWarrantyThrowsException() {
        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new ElectronicItems("Philips Monitor", 0.15, 300.0, "PHILIPS", -12, "Negative warranty period.")
        );

        assertTrue(exception.getMessage().contains("Warranty period cannot be negative"), "Expected exception message not found.");
    }

    @Test
    void testExceedingWarrantyThrowsException() {
        // Act & Assert
        Exception exception = assertThrows(IllegalStateException.class, () ->
                new ElectronicItems("Samsung Fridge", 0.2, 1000.0, "SAMSUNG", 48, "Exceeding warranty period.")
        );

        assertTrue(exception.getMessage().contains("Warranty period must be between 0 and 3"), "Expected exception message not found.");
    }

    @Test
    void testSetAndGetMethods() {
        // Arrange
        ElectronicItems electronic = new ElectronicItems("Philips Airfryer", 0.05, 150.0, "PHILIPS", 18, "Air fryer for healthy cooking.");

        // Act
        electronic.setName("Updated Airfryer");
        electronic.setDiscount(0.2);
        electronic.setPrice(200.0);
        electronic.setBrand("SAMSUNG");
        electronic.setWarrantyPeriod(12);
        electronic.setItemDetails("Updated details for air fryer.");

        // Assert
        assertEquals("Updated Airfryer", electronic.getName(), "Name update failed.");
        assertEquals(160.0, electronic.getPrice(), "Price update failed.");
        assertEquals(ElectronicItems.Company.SAMSUNG, electronic.getBrand(), "Brand update failed.");
        assertEquals(12, electronic.getWarrantyPeriod(), "Warranty update failed.");
        assertEquals("Updated details for air fryer.", electronic.getItemDetails(), "Details update failed.");
    }

    @Test
    void testPrintDetails() {
        // Arrange
        ElectronicItems electronic = new ElectronicItems("iPhone 13", 0.15, 800.0, "APPLE", 24, "Previous generation iPhone.");

        // Redirecting output to a stream for verification
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        // Act
        electronic.printDetails();

        // Restore original output stream
        System.setOut(originalOut);

        // Assert
        String printedDetails = outputStream.toString();
        assertTrue(printedDetails.contains("IPhone 13"), "Electronic name not printed correctly.");
        assertTrue(printedDetails.contains("APPLE"), "Brand not printed correctly.");
//        assertTrue(printedDetails.contains("680.0"), "Price after discount not printed correctly.");
    }

    @Test
    void testExtremeHighPrice() {
        // Arrange
        double price = 10_000.0;
        double discount = 0.25; // 25% discount

        // Act
        ElectronicItems electronic = new ElectronicItems("High-End Laptop", discount, price, "PHILIPS", 36, "Luxury laptop.");

        // Assert
        assertEquals(7_500.0, electronic.getPrice(), "Price calculation for high value failed.");
    }

    @Test
    void testExtremeLowDiscount() {
        // Arrange
        double price = 1_000.0;
        double discount = 0.0001; // 0.01% discount

        // Act
        ElectronicItems electronic = new ElectronicItems("Minimal Discount Item", discount, price, "SAMSUNG", 12, "Minimal discount test.");

        // Assert
        double expectedPrice = Math.round((price - (price * discount)) * 100.0) / 100.0;
        assertEquals(expectedPrice, electronic.getPrice(), "Price calculation for minimal discount failed.");
    }
}
