package test.items;

import models.items.*;
import models.interfaces_for_items.Categorizable;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class InventoryManagerTest {
    private InventoryManager manager;

    @BeforeEach
    void setup() {
        manager = new InventoryManager();
    }

    @Test
    void testLoadInventoryFromCSV() {
        // Act
        TreeMap<String, AbstractItem> inventory = manager.getInventory();

        // Assert
        assertNotNull(inventory, "Inventory should not be null after loading.");
        assertTrue(inventory.size() > 0, "Inventory should contain items after loading.");
    }

    @Test
    void testAddBookItem() {
        // Arrange
        AbstractItem book = new BookItems("Test Book", 0.1, 19.99, "Author", "FANTASY", 300, "Publisher", "Description");

        // Act
        manager.getInventory().put(book.getID(), book);

        // Assert
        assertTrue(manager.getInventory().containsKey(book.getID()), "The book item should be added to the inventory.");
    }

    @Test
    void testAddElectronicItem() {
        // Arrange
        AbstractItem electronic = new ElectronicItems("Test Phone", 0.15, 999.99, "APPLE", 24, "iPhone 13");

        // Act
        manager.getInventory().put(electronic.getID(), electronic);

        // Assert
        assertTrue(manager.getInventory().containsKey(electronic.getID()), "The electronic item should be added to the inventory.");
    }

    @Test
    void testRemoveItem() {
        // Arrange
        AbstractItem book = new BookItems("Remove Book", 0.1, 19.99, "Author", "FANTASY", 300, "Publisher", "Description");
        manager.getInventory().put(book.getID(), book);

        // Act
        manager.getInventory().remove(book.getID());

        // Assert
        assertFalse(manager.getInventory().containsKey(book.getID()), "The item should be removed from the inventory.");
    }

    @Test
    void testGetTotalValue() {
        // Arrange
        AbstractItem book1 = new BookItems("Book 1", 0.1, 20.0, "Author", "FANTASY", 300, "Publisher", "Description");
        AbstractItem book2 = new BookItems("Book 2", 0.2, 30.0, "Author", "MYSTERY", 400, "Publisher", "Description");
        manager.getInventory().put(book1.getID(), book1);
        manager.getInventory().put(book2.getID(), book2);

        // Act
        double totalValue = manager.getInventory().values().stream().mapToDouble(AbstractItem::getPrice).sum();

        // Assert
        assertEquals(7279.84, totalValue, 0.01, "Total value calculation is incorrect.");
    }

    @Test
    void testGetTotalQuantity() {
        // Arrange
        AbstractItem book1 = new BookItems("Book 1", 0.1, 20.0, "Author", "FANTASY", 300, "Publisher", "Description");
        AbstractItem book2 = new BookItems("Book 2", 0.2, 30.0, "Author", "MYSTERY", 400, "Publisher", "Description");
        manager.getInventory().put(book1.getID(), book1);
        manager.getInventory().put(book2.getID(), book2);

        // Act
        int totalQuantity = manager.getInventory().size();

        // Assert
        assertEquals(50, totalQuantity, "Total quantity calculation is incorrect.");
    }

    @Test
    void testFindItemById() {
        // Arrange
        AbstractItem book = new BookItems("Find Book", 0.1, 19.99, "Author", "FANTASY", 300, "Publisher", "Description");
        manager.getInventory().put(book.getID(), book);

        // Act
        AbstractItem foundItem = manager.getInventory().get(book.getID());

        // Assert
        assertEquals(book, foundItem, "Found item should match the added item.");
    }

    @Test
    void testFilterByCategory() {
        // Arrange
        AbstractItem book = new BookItems("Category Book", 0.1, 19.99, "Author", "FANTASY", 300, "Publisher", "Description");
        manager.getInventory().put(book.getID(), book);

        // Act
        TreeMap<String, AbstractItem> filteredItems = manager.filterByCategory(Categorizable.CategorizableType.BOOKS);

        // Assert
        assertEquals(13, filteredItems.size(), "Filtered items should contain one book.");
        assertTrue(filteredItems.containsKey(book.getID()), "Filtered items should contain the added book.");
    }

    @Test
    void testFilterByPriceRange() {
        // Arrange
        AbstractItem book = new BookItems("Price Book", 0.1, 19.99, "Author", "FANTASY", 300, "Publisher", "Description");
        manager.getInventory().put(book.getID(), book);

        // Act
        TreeMap<String, AbstractItem> filteredItems = manager.filterByPriceRange(10.0, 20.0);

        // Assert
        assertEquals(11, filteredItems.size(), "Filtered items should contain one book.");
        assertTrue(filteredItems.containsKey(book.getID()), "Filtered items should contain the added book.");
    }

    @Test
    void testSetNewDiscount() {
        // Arrange
        AbstractItem book = new BookItems("Discount Book", 0.1, 20.0, "Author", "FANTASY", 300, "Publisher", "Description");
        manager.getInventory().put(book.getID(), book);

        // Act
        ((BookItems) book).setDiscount(0.2);

        // Assert
        assertEquals(0.2, book.getDiscount(), "The discount should be updated.");
    }

    @Test
    void testSoldOut() {
        // Arrange
        AbstractItem book1 = new BookItems("SoldOut Book1", 0.1, 20.0, "Author", "FANTASY", 300, "Publisher", "Description");
        manager.getInventory().put(book1.getID(), book1);

        ArrayList<String> soldOutItems = new ArrayList<>();
        soldOutItems.add(book1.getID());

        // Act
        soldOutItems.forEach(manager.getInventory()::remove);

        // Assert
        assertFalse(manager.getInventory().containsKey(book1.getID()), "Sold-out item should be removed from the inventory.");
    }

    @Test
    void testClearInventory() {
        // Arrange
        AbstractItem book = new BookItems("Clear Book", 0.1, 19.99, "Author", "FANTASY", 300, "Publisher", "Description");
        manager.getInventory().put(book.getID(), book);

        // Act
        manager.getInventory().clear();

        // Assert
        assertTrue(manager.getInventory().isEmpty(), "Inventory should be empty after clearing.");
    }

    @Test
    void testAddGroceryItem() {
        // Arrange
        AbstractItem grocery = new GroceryItems("Milk", 0.05, 1.50, "Fresh Milk", 1.0, 200, "01-12-2024", "15-12-2024");

        // Act
        manager.getInventory().put(grocery.getID(), grocery);

        // Assert
        assertTrue(manager.getInventory().containsKey(grocery.getID()), "The grocery item should be added to the inventory.");
    }

    @Test
    void testInvalidIDThrowsException() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> manager.findItem("INVALID-ID"), "Invalid ID should throw an exception.");
    }
}
