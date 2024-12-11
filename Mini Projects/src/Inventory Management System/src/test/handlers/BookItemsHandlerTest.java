package test.handlers;

import models.handlers_for_the_items.BookItemsHandler;
import models.items.BookItems;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

public class BookItemsHandlerTest {
    private BookItemsHandler bookHandler;
    private final String testFile = "TestBooks.csv";

    @BeforeEach
    void setUp() {
        bookHandler = new BookItemsHandler() {};
        File file = new File(testFile);

        if (!file.exists())
            bookHandler.loadFromCSV(testFile);
    }

    @AfterEach
    void tearDown() {
        File file = new File(testFile);

        if (file.exists())
            assertTrue(file.delete(), "Failed to delete test file.");
    }

    @Test
    void testLoadFromCSV() {
        System.out.println("----- Testing loadFromCSV -----");

        TreeMap<String, BookItems> testBooks = new TreeMap<>();
        testBooks.put("B-1", new BookItems("Test Book", 0.05, 15.99, "Test Author", "FANTASY", 300, "Test Publisher", "A test description."));
        bookHandler.saveToCSV(testFile, testBooks);

        TreeMap<String, BookItems> loadedBooks = bookHandler.loadFromCSV(testFile);

        assertEquals("Test Book", loadedBooks.get("B-1").getName(), "Book name does not match.");
        assertEquals(1, loadedBooks.size(), "Expected 1 book in the test file.");
    }

    @Test
    void testSaveToCSV() {
        System.out.println("----- Testing saveToCSV -----");

        TreeMap<String, BookItems> testBooks = new TreeMap<>();
        testBooks.put("B-2", new BookItems("Another Test Book", 0.1, 20.00, "Another Author", "BIOGRAPHY", 400, "Another Publisher", "Another test description."));
        bookHandler.saveToCSV(testFile, testBooks);

        TreeMap<String, BookItems> loadedBooks = bookHandler.loadFromCSV(testFile);
        assertTrue(loadedBooks.containsKey("B-2"), "Book B-2 was not saved correctly.");
        assertEquals("Another Test Book", loadedBooks.get("B-2").getName(), "Book data does not match.");
    }

    @Test
    void testRemoveFromCSV() {
        System.out.println("----- Testing removeFromCSV -----");

        TreeMap<String, BookItems> testBooks = new TreeMap<>();
        testBooks.put("B-3", new BookItems("Removable Book", 0.2, 18.50, "Removable Author", "NON_FICTION", 350, "Removable Publisher", "Removable test description."));
        bookHandler.saveToCSV(testFile, testBooks);

        bookHandler.removeFromCSV(testFile, "B-3");
        TreeMap<String, BookItems> loadedBooks = bookHandler.loadFromCSV(testFile);
        assertFalse(loadedBooks.containsKey("B-3"), "Book B-3 was not removed correctly.");
    }

    @Test
    void testHeaderInCSV() {
        System.out.println("----- Testing CSV Header -----");

        TreeMap<String, BookItems> testBooks = new TreeMap<>();
        testBooks.put("B-1", new BookItems("Header Test Book", 0.05, 10.0, "Header Author", "FANTASY", 100, "Header Publisher", "Header description."));
        bookHandler.saveToCSV(testFile, testBooks);

        File file = new File(testFile);
        try (var reader = new java.io.BufferedReader(new java.io.FileReader(file))) {
            String header = reader.readLine();
            assertEquals("ID|Name|Price|Discount|Author|Genre|TotalPages|Publisher|Description", header, "CSV header does not match expected format.");
        } catch (Exception e) {
            fail("Error reading the test file: " + e.getMessage());
        }
    }

    @Test
    void testAddMultipleBooks() {
        System.out.println("----- Testing saveToCSV with multiple books -----");

        TreeMap<String, BookItems> testBooks = new TreeMap<>();
        testBooks.put("B-20", new BookItems("Book 20", 0.1, 12.99, "Author 20", "MYSTERY", 200, "Publisher 20", "Description 20"));
        testBooks.put("B-21", new BookItems("Book 21", 0.15, 14.99, "Author 21", "SCIENCE_FICTION", 300, "Publisher 21", "Description 21"));
        bookHandler.saveToCSV(testFile, testBooks);

        TreeMap<String, BookItems> loadedBooks = bookHandler.loadFromCSV(testFile);

        assertEquals(2, loadedBooks.size(), "Expected 2 books in the test file.");
        assertEquals("Book 20", loadedBooks.get("B-20").getName(), "Book B-20 data does not match.");
        assertEquals("Book 21", loadedBooks.get("B-21").getName(), "Book B-21 data does not match.");
    }

    @Test
    void testRemoveSpecificBook() {
        System.out.println("----- Testing removeFromCSV with specific book -----");

        TreeMap<String, BookItems> testBooks = new TreeMap<>();
        testBooks.put("B-30", new BookItems("Book 30", 0.1, 10.0, "Author 30", "FANTASY", 100, "Publisher 30", "Description 30"));
        testBooks.put("B-31", new BookItems("Book 31", 0.2, 15.0, "Author 31", "BIOGRAPHY", 150, "Publisher 31", "Description 31"));
        bookHandler.saveToCSV(testFile, testBooks);

        bookHandler.removeFromCSV(testFile, "B-30");

        TreeMap<String, BookItems> loadedBooks = bookHandler.loadFromCSV(testFile);
        assertEquals(1, loadedBooks.size(), "Expected 1 book after removal.");
        assertFalse(loadedBooks.containsKey("B-30"), "Book B-30 was not removed.");
        assertEquals("Book 31", loadedBooks.get("B-31").getName(), "Remaining book data does not match.");
    }
}
