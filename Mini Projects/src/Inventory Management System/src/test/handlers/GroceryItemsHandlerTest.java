package test.handlers;

import models.handlers_for_the_items.GroceryItemsHandler;
import models.items.GroceryItems;
import org.junit.jupiter.api.*;

import java.io.*;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

class GroceryItemsHandlerTest {
    private GroceryItemsHandler handler;
    private final String testFile = "TestGroceries.csv";

    @BeforeEach
    void setUp() {
        handler = new GroceryItemsHandler() {};
        File file = new File(testFile);

        if (file.exists())
            assertTrue(file.delete(), "Failed to clean up test file.");
    }

    @AfterEach
    void tearDown() {
        File file = new File(testFile);

        if (file.exists())
            assertTrue(file.delete(), "Failed to delete test file.");
    }

    @Test
    void testLoadFromCSVCreatesFileIfNotExists() {
        TreeMap<String, GroceryItems> items = handler.loadFromCSV(testFile);
        assertTrue(new File(testFile).exists(), "File should be created if it doesn't exist.");
        assertTrue(items.isEmpty(), "Loaded items should be empty for a new file.");
    }

    @Test
    void testSaveSingleItemToCSV() {
        TreeMap<String, GroceryItems> items = new TreeMap<>();
        items.put("G-1", new GroceryItems("Apple", 0.1, 1.99, "Fresh and juicy", 1.0, 52, "01-01-2023", "01-01-2024"));
        handler.saveToCSV(testFile, items);

        TreeMap<String, GroceryItems> loadedItems = handler.loadFromCSV(testFile);
        assertEquals(1, loadedItems.size(), "Expected one item in the file.");
        assertEquals("Apple", loadedItems.get("G-1").getName(), "Item name does not match.");
        assertEquals(1.61, loadedItems.get("G-1").getPrice(), "Price does not match.");
        assertEquals(0.1, loadedItems.get("G-1").getDiscount(), "Discount does not match.");
        assertEquals(52, loadedItems.get("G-1").getCalories(), "Calories do not match.");
    }

    @Test
    void testSaveMultipleItemsToCSV() {
        TreeMap<String, GroceryItems> items = new TreeMap<>();
        items.put("G-1", new GroceryItems("Banana", 0.2, 2.99, "Fresh bananas", 1.5, 105, "01-02-2023", "01-02-2024"));
        items.put("G-2", new GroceryItems("Orange", 0.15, 1.49, "Vitamin-rich", 2.0, 62, "01-03-2023", "01-03-2024"));
        handler.saveToCSV(testFile, items);

        TreeMap<String, GroceryItems> loadedItems = handler.loadFromCSV(testFile);
        assertEquals(2, loadedItems.size(), "Expected two items in the file.");
        assertEquals("Banana", loadedItems.get("G-1").getName(), "Item G-1 name does not match.");
        assertEquals("Orange", loadedItems.get("G-2").getName(), "Item G-2 name does not match.");
    }

    @Test
    void testRemoveItemFromCSV() {
        TreeMap<String, GroceryItems> items = new TreeMap<>();
        items.put("G-1", new GroceryItems("Milk", 0.05, 1.29, "Dairy product", 1.0, 42, "01-04-2023", "01-04-2024"));
        handler.saveToCSV(testFile, items);

        handler.removeFromCSV(testFile, "G-1");
        TreeMap<String, GroceryItems> loadedItems = handler.loadFromCSV(testFile);
        assertTrue(loadedItems.isEmpty(), "Item G-1 should have been removed.");
    }

    @Test
    void testLoadWithInvalidFileName() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> handler.loadFromCSV(""));
        assertTrue(exception.getMessage().contains("Filename cannot be null or empty"), "Expected error for invalid file name.");
    }

    @Test
    void testSaveToInvalidFileName() {
        TreeMap<String, GroceryItems> items = new TreeMap<>();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> handler.saveToCSV("", items));
        assertTrue(exception.getMessage().contains("Filename cannot be null or empty"), "Expected error for invalid file name.");
    }

    @Test
    void testRemoveNonExistentItemThrowsException() {
        TreeMap<String, GroceryItems> items = new TreeMap<>();
        handler.saveToCSV(testFile, items);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> handler.removeFromCSV(testFile, "G-99"));
        assertTrue(exception.getMessage().contains("Item does not exist"), "Expected error for non-existent item.");
    }

    @Test
    void testSaveAndLoadPreservesDataIntegrity() {
        TreeMap<String, GroceryItems> items = new TreeMap<>();
        items.put("G-1", new GroceryItems("Bread", 0.1, 2.5, "Whole grain bread", 0.5, 250, "01-05-2023", "01-05-2024"));
        items.put("G-2", new GroceryItems("Cheese", 0.15, 5.99, "Aged cheese", 0.2, 402, "01-06-2023", "01-06-2024"));
        handler.saveToCSV(testFile, items);

        TreeMap<String, GroceryItems> loadedItems = handler.loadFromCSV(testFile);
        assertEquals(2, loadedItems.size(), "Expected two items after reloading.");
        assertEquals("Bread", loadedItems.get("G-1").getName(), "Item G-1 name does not match after reloading.");
        assertEquals("Cheese", loadedItems.get("G-2").getName(), "Item G-2 name does not match after reloading.");
    }

    @Test
    void testSaveAndReloadEmptyFile() {
        TreeMap<String, GroceryItems> items = new TreeMap<>();
        handler.saveToCSV(testFile, items);

        TreeMap<String, GroceryItems> loadedItems = handler.loadFromCSV(testFile);
        assertTrue(loadedItems.isEmpty(), "File should remain empty after save and reload.");
    }

    @Test
    void testRemoveFromEmptyFileThrowsException() {
        TreeMap<String, GroceryItems> items = new TreeMap<>();
        handler.saveToCSV(testFile, items);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> handler.removeFromCSV(testFile, "G-1"));
        assertTrue(exception.getMessage().contains("Item does not exist"), "Expected error for removing from empty file.");
    }

    @Test
    void testHeaderIntegrityInCSVFile() {
        TreeMap<String, GroceryItems> items = new TreeMap<>();
        items.put("G-1", new GroceryItems("Apple", 0.1, 1.99, "Fresh and juicy", 1.0, 52, "01-01-2023", "01-01-2024"));
        handler.saveToCSV(testFile, items);

        File file = new File(testFile);
        try (var reader = new BufferedReader(new FileReader(file))) {
            String header = reader.readLine();
            assertEquals("ID|Name|Price|Discount|Description|Weight|Calories|CreationDate|ExpiryDate", header, "Header does not match expected format.");
        } catch (Exception e) {
            fail("Failed to verify file header: " + e.getMessage());
        }
    }
}
