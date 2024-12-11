package test.items;

import models.items.BookItems;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class BookItemsTest {
    @Test
    void testBookItemsInitialization() {
        // Arrange
        String name = "The Hobbit";
        double discount = 0.1;
        double price = 19.99;
        String author = "J.R.R. Tolkien";
        String genre = "FANTASY";
        int totalPages = 310;
        String publisher = "HarperCollins";
        String description = "A classic fantasy tale.";

        // Act
        BookItems book = new BookItems(name, discount, price, author, genre, totalPages, publisher, description);

        // Assert
        assertEquals(name, book.getName(), "Book name does not match.");
        assertEquals(discount, book.getDiscount(), "Book discount does not match.");
        assertEquals(Math.round((price - (price * discount)) * 100.0) / 100.0, book.getPrice(), "Book price does not match.");
        assertEquals(author, book.getAuthor(), "Book author does not match.");
        assertEquals(BookItems.Genre.FANTASY, book.getGenre(), "Book genre does not match.");
        assertEquals(totalPages, book.getTotalPages(), "Book total pages do not match.");
        assertEquals(publisher, book.getPublisher(), "Book publisher does not match.");
        assertEquals(description, book.getItemDescription(), "Book description does not match.");
    }

    @Test
    void testPriceCalculation() {
        // Arrange
        double price = 20.0;
        double discount = 0.2; // 20% discount
        BookItems book = new BookItems("Dune", discount, price, "Frank Herbert", "SCIENCE_FICTION", 412, "Ace Books", "A science fiction masterpiece.");

        // Assert
        assertEquals(Math.round((price - (price * discount)) * 100.0) / 100.0, 16.0, "Price after discount calculation is incorrect.");
    }

    @Test
    void testInvalidGenreThrowsException() {
        // Arrange
        String invalidGenre = "UNKNOWN";

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new BookItems("Invalid Genre Book", 0.1, 15.99, "Author", invalidGenre, 200, "Publisher", "Description")
        );

        assertTrue(exception.getMessage().contains("Invalid genre"), "Expected exception message not found.");
    }

    @Test
    void testSetAndGetMethods() {
        // Arrange
        BookItems book = new BookItems("Initial Book", 0.05, 10, "Author", "FANTASY", 100, "Publisher", "Description");

        // Act
        book.setName("Updated Book");
        book.setPrice(15.0);
        book.setDiscount(0.2);
        book.setAuthor("Updated Author");
        book.setGenre("BIOGRAPHY");
        book.setTotalPages(250);
        book.setPublisher("Updated Publisher");
        book.setItemDescription("Updated description");

        // Assert
        assertEquals("Updated Book", book.getName(), "Name update failed.");
        assertEquals(0.2, book.getDiscount(), "Discount update failed.");
        assertEquals(14.25, book.getPrice(), "Price update failed.");
        assertEquals("Updated Author", book.getAuthor(), "Author update failed.");
        assertEquals(BookItems.Genre.BIOGRAPHY, book.getGenre(), "Genre update failed.");
        assertEquals(250, book.getTotalPages(), "Total pages update failed.");
        assertEquals("Updated Publisher", book.getPublisher(), "Publisher update failed.");
        assertEquals("Updated description", book.getItemDescription(), "Description update failed.");
    }

    @Test
    void testInvalidTotalPagesThrowsException() {
        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new BookItems("Invalid Pages Book", 0.1, 15.99, "Author", "FANTASY", -50, "Publisher", "Description")
        );

        assertTrue(exception.getMessage().contains("Total pages must be greater than zero"), "Expected exception message not found.");
    }

    @Test
    void testPrintDetails() {
        // Arrange
        BookItems book = new BookItems("Sapiens", 0.2, 21.5, "Yuval Noah Harari", "NON_FICTION", 498, "Harper", "A brief history of humankind.");

        // Redirecting output to a stream for verification
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        // Act
        book.printDetails();

        // Restore original output stream
        System.setOut(originalOut);

        // Assert
        String printedDetails = outputStream.toString();
        assertTrue(printedDetails.contains("Sapiens"), "Book details not printed correctly.");
        assertTrue(printedDetails.contains("Yuval Noah Harari"), "Author not printed correctly.");
        assertTrue(printedDetails.contains("17.2"), "Price not printed correctly.");
    }

    @Test
    void testValidInitializationWithNoDiscount() {
        // Arrange
        String name = "1984";
        double price = 10.0;
        double discount = 0.0; // No discount
        String author = "George Orwell";
        String genre = "SCIENCE_FICTION";
        int totalPages = 328;
        String publisher = "Secker & Warburg";
        String description = "Dystopian masterpiece.";

        // Act
        BookItems book = new BookItems(name, discount, price, author, genre, totalPages, publisher, description);

        // Assert
        assertEquals(10.0, book.getPrice(), "Price calculation with no discount failed.");
    }

    @Test
    void testHighDiscount() {
        // Arrange
        String name = "High Discount Book";
        double price = 100.0;
        double discount = 0.5; // 50% discount

        // Act
        BookItems book = new BookItems(name, discount, price, "Author", "FANTASY", 300, "Publisher", "Description");

        // Assert
        assertEquals(50.0, book.getPrice(), "Price calculation with high discount failed.");
    }

    @Test
    void testNegativePriceThrowsException() {
        // Act & Assert
        Exception exception = assertThrows(IllegalStateException.class, () ->
                new BookItems("Negative Price Book", 0.1, -10.0, "Author", "MYSTERY", 200, "Publisher", "Description")
        );

        assertTrue(exception.getMessage().contains(">! Price cannot be less than 0.10 [AbstactItem, setPrice()]."), "Expected exception message not found.");
    }

    @Test
    void testZeroPrice() {
        // Act & Assert
        Exception exception = assertThrows(IllegalStateException.class, () ->
                new BookItems("Zero Price Book", 0.1, 0.0, "Author", "NON_FICTION", 150, "Publisher", "Description")
        );

        assertTrue(exception.getMessage().contains(">! Price cannot be less than 0.10 [AbstactItem, setPrice()]."), "Expected exception message not found.");
    }

    @Test
    void testExtremeHighPrice() {
        // Arrange
        double price = 1_000_000.0;
        double discount = 0.1;

        // Act
        BookItems book = new BookItems("Expensive Book", discount, price, "Rich Author", "BIOGRAPHY", 500, "Luxury Publisher", "Exclusive edition.");

        // Assert
        assertEquals(900_000.0, book.getPrice(), "Price calculation for high price failed.");
    }

    @Test
    void testExtremeLowDiscount() {
        // Arrange
        double price = 50.0;
        double discount = 0.0001; // 0.01% discount

        // Act
        BookItems book = new BookItems("Minimal Discount Book", discount, price, "Author", "FANTASY", 300, "Publisher", "Description");

        // Assert
        double expectedPrice = Math.round((price - (price * discount)) * 100.0) / 100.0;
        assertEquals(expectedPrice, book.getPrice(), "Price calculation for minimal discount failed.");
    }

    @Test
    void testGenreCaseInsensitive() {
        // Act
        BookItems bookLowerCase = new BookItems("Lowercase Genre", 0.1, 15.0, "Author", "fantasy", 300, "Publisher", "Description");
        BookItems bookMixedCase = new BookItems("Mixedcase Genre", 0.1, 15.0, "Author", "fAnTaSy", 300, "Publisher", "Description");

        // Assert
        assertEquals(BookItems.Genre.FANTASY, bookLowerCase.getGenre(), "Lowercase genre not accepted.");
        assertEquals(BookItems.Genre.FANTASY, bookMixedCase.getGenre(), "Mixed case genre not accepted.");
    }

    @Test
    void testNegativeDiscountThrowsException() {
        // Act & Assert
        Exception exception = assertThrows(IllegalStateException.class, () ->
                new BookItems("Negative Discount Book", -0.1, 15.99, "Author", "FANTASY", 200, "Publisher", "Description")
        );

        assertTrue(exception.getMessage().contains(">! Discount cannot be less than 0%. [AbstactItem, setDiscount()]."), "Expected exception message not found.");
    }

    @Test
    void testInvalidDiscountGreaterThanOneThrowsException() {
        // Act & Assert
        Exception exception = assertThrows(IllegalStateException.class, () ->
                new BookItems("Excessive Discount Book", 1.5, 15.99, "Author", "MYSTERY", 200, "Publisher", "Description")
        );

        assertTrue(exception.getMessage().contains(">! Discount cannot be greater than 100%. [AbstactItem, setDiscount()]."), "Expected exception message not found.");
    }

    @Test
    void testEmptyDescriptionDefaults() {
        // Arrange
        BookItems book = new BookItems("No Description Book", 0.1, 10.0, "Author", "NON_FICTION", 100, "Publisher", "");

        // Assert
        assertEquals("[ No description for this item was provided ]\n", book.getItemDescription(), "Default description not applied.");
    }

    @Test
    void testLargeBookDetails() {
        // Arrange
        String largeDescription = "This is a very long description.".repeat(50);
        BookItems book = new BookItems("Large Description Book", 0.1, 20.0, "Author", "BIOGRAPHY", 400, "Publisher", largeDescription);

        // Assert
        assertEquals(largeDescription, book.getItemDescription(), "Large description handling failed.");
    }
}
