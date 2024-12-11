package test.handlers;

import models.handlers_for_the_items.ElectronicItemsHandler;
import models.items.ElectronicItems;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

class ElectronicItemsHandlerTest {
    private ElectronicItemsHandler handler;
    private final String testFile = "TestElectronics.csv";

    @BeforeEach
    void setUp() {
        handler = new ElectronicItemsHandler() {};
        File file = new File(testFile);

        if (file.exists())
            assertTrue(file.delete(), "Failed to delete existing test file.");

        handler.loadFromCSV(testFile); // Ensure file exists
    }

    @AfterEach
    void tearDown() {
        File file = new File(testFile);

        if (file.exists())
            assertTrue(file.delete(), "Failed to delete test file.");
    }

    @Test
    void testLoadFromEmptyCSV() {
        TreeMap<String, ElectronicItems> items = handler.loadFromCSV(testFile);
        assertTrue(items.isEmpty(), "Expected no items in an empty file.");
    }

    @Test
    void testSaveSingleItemToCSV() {
        TreeMap<String, ElectronicItems> items = new TreeMap<>();
        items.put("E-1", new ElectronicItems("Smartphone", 0.15, 999.99, "APPLE", 24, "High-resolution screen."));
        handler.saveToCSV(testFile, items);

        TreeMap<String, ElectronicItems> loadedItems = handler.loadFromCSV(testFile);
        assertEquals(1, loadedItems.size(), "Expected one item in the file.");
        assertEquals("Smartphone", loadedItems.get("E-1").getName(), "Item name does not match.");
    }

    @Test
    void testSaveMultipleItemsToCSV() {
        TreeMap<String, ElectronicItems> items = new TreeMap<>();
        items.put("E-1", new ElectronicItems("Smartphone", 0.15, 999.99, "APPLE", 24, "High-resolution screen."));
        items.put("E-2", new ElectronicItems("Smartwatch", 0.10, 199.99, "SAMSUNG", 12, "Fitness tracking."));

        handler.saveToCSV(testFile, items);
        TreeMap<String, ElectronicItems> loadedItems = handler.loadFromCSV(testFile);

        assertEquals(2, loadedItems.size(), "Expected two items in the file.");
        assertTrue(loadedItems.containsKey("E-1"), "Item E-1 not found.");
        assertTrue(loadedItems.containsKey("E-2"), "Item E-2 not found.");
    }

    @Test
    void testRemoveExistingItem() {
        TreeMap<String, ElectronicItems> items = new TreeMap<>();
        items.put("E-1", new ElectronicItems("Smartphone", 0.15, 999.99, "APPLE", 24, "High-resolution screen."));
        handler.saveToCSV(testFile, items);

        handler.removeFromCSV(testFile, "E-1");
        TreeMap<String, ElectronicItems> loadedItems = handler.loadFromCSV(testFile);

        assertFalse(loadedItems.containsKey("E-1"), "Item E-1 was not removed.");
    }

    @Test
    void testRemoveNonExistingItemThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                handler.removeFromCSV(testFile, "E-99"));
        assertTrue(exception.getMessage().contains("Item does not exist"), "Expected exception for non-existing item.");
    }

    @Test
    void testHeaderIntegrityAfterSave() {
        TreeMap<String, ElectronicItems> items = new TreeMap<>();
        items.put("E-1", new ElectronicItems("Smartphone", 0.15, 999.99, "APPLE", 24, "High-resolution screen."));
        handler.saveToCSV(testFile, items);

        try (var reader = new java.io.BufferedReader(new java.io.FileReader(testFile))) {
            String header = reader.readLine();
            assertEquals("ID|Name|Price|Discount|Brand|Warranty|Details", header, "CSV header is incorrect.");
        } catch (Exception e) {
            fail("Error reading the test file: " + e.getMessage());
        }
    }

    @Test
    void testFileCreationOnLoad() {
        File file = new File(testFile);
        if (file.exists()) {
            assertTrue(file.delete(), "Failed to delete existing test file.");
        }

        handler.loadFromCSV(testFile);
        assertTrue(file.exists(), "File was not created during load.");
    }

    @Test
    void testSaveAndLoadSpecialCharacters() {
        TreeMap<String, ElectronicItems> items = new TreeMap<>();
        items.put("E-1", new ElectronicItems("Tablet", 0.2, 400.0, "PHILIPS", 12, "Portable & lightweight."));

        handler.saveToCSV(testFile, items);
        TreeMap<String, ElectronicItems> loadedItems = handler.loadFromCSV(testFile);

        assertEquals("Portable & lightweight.", loadedItems.get("E-1").getItemDetails(), "Special characters not handled correctly.");
    }

    @Test
    void testOverwriteFileWithNewItems() {
        TreeMap<String, ElectronicItems> initialItems = new TreeMap<>();
        initialItems.put("E-1", new ElectronicItems("Old Item", 0.1, 100.0, "SAMSUNG", 12, "Old details."));
        handler.saveToCSV(testFile, initialItems);

        TreeMap<String, ElectronicItems> newItems = new TreeMap<>();
        newItems.put("E-2", new ElectronicItems("New Item", 0.2, 200.0, "APPLE", 24, "New details."));
        handler.saveToCSV(testFile, newItems);

        TreeMap<String, ElectronicItems> loadedItems = handler.loadFromCSV(testFile);
        assertEquals(1, loadedItems.size(), "Expected only new items in the file.");
        assertFalse(loadedItems.containsKey("E-1"), "Old item was not overwritten.");
    }

    @Test
    void testSaveEmptyTreeMap() {
        handler.saveToCSV(testFile, new TreeMap<>());

        TreeMap<String, ElectronicItems> loadedItems = handler.loadFromCSV(testFile);
        assertTrue(loadedItems.isEmpty(), "Expected no items in the file.");
    }

    @Test
    void testRemoveLastItem() {
        TreeMap<String, ElectronicItems> items = new TreeMap<>();
        items.put("E-1", new ElectronicItems("Smartwatch", 0.1, 300.0, "APPLE", 12, "Fitness tracker."));
        handler.saveToCSV(testFile, items);

        handler.removeFromCSV(testFile, "E-1");
        TreeMap<String, ElectronicItems> loadedItems = handler.loadFromCSV(testFile);

        assertTrue(loadedItems.isEmpty(), "Expected file to be empty after removing the last item.");
    }
}
