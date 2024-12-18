package models.items;

public class BookItems extends AbstractItem {
    public enum Genre {
        FANTASY,
        SCIENCE_FICTION,
        MYSTERY,
        NON_FICTION,
        BIOGRAPHY
    }

    private final String bookID;
    private static int bookIDCounter = 1;
    private String author;
    private Genre genre;
    private int totalPages;
    private String publisher;

    private String generateBookID() {
        return "B-" + (bookIDCounter++);
    }

    public BookItems(String id, String name, double discount, double price, String author, String genre, int totalPages, String publisher, String description) {
        super(name, discount, price, "BOOKS");
        this.bookID = id;
        this.setAuthor(author);
        this.setGenre(genre);
        this.setTotalPages(totalPages);
        this.setPublisher(publisher);
        this.setItemDescription(description);
        this.setFragile(false);
        this.setSellable(true);
    }

    public BookItems(String name, double discount, double price, String author, String genre, int totalPages, String publisher, String description) {
        super(name, discount, price, "BOOKS");
        this.bookID = generateBookID();
        this.setAuthor(author);
        this.setGenre(genre);
        this.setTotalPages(totalPages);
        this.setPublisher(publisher);
        this.setItemDescription(description);
        this.setFragile(false);
        this.setSellable(true);
    }

    @Override
    public String getID() {
        return this.bookID;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return this.genre.toString();
    }

    public int getTotalPages() {
        return totalPages;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setAuthor(String author) {
        if (author == null || author.isEmpty())
            throw new IllegalArgumentException(">! Author name cannot be empty [BookItems, setAuthor()].");

        this.author = author;
    }

    public void setGenre(String genre) {
        switch (genre.toUpperCase()) {
            case "FANTASY" -> this.genre = Genre.FANTASY;
            case "SCIENCE_FICTION" -> this.genre = Genre.SCIENCE_FICTION;
            case "MYSTERY" -> this.genre = Genre.MYSTERY;
            case "NON_FICTION" -> this.genre = Genre.NON_FICTION;
            case "BIOGRAPHY" -> this.genre = Genre.BIOGRAPHY;
            default -> throw new IllegalArgumentException(">! Invalid genre [" + genre + "].");
        }
    }

    public void setTotalPages(int totalPages) {
        if (totalPages <= 0)
            throw new IllegalArgumentException(">! Total pages must be greater than zero [BookItems, setTotalPages()].");

        this.totalPages = totalPages;
    }

    public void setPublisher(String publisher) {
        if (publisher == null || publisher.isEmpty())
            throw new IllegalArgumentException(">! Publisher name cannot be empty [BookItems, setPublisher()].");

        this.publisher = publisher;
    }

    @Override
    public void printDetails() {
        System.out.println("ID: " + this.getID() + " | Name: " + this.getName() +
                " | Discount: " + this.getDiscount() +
                " | Price (after discount): " + this.getPrice() +
                " | Author: " + this.getAuthor() +
                " | Genre: " + this.getGenre() +
                " | Pages: " + this.getTotalPages() +
                " | Publisher: " + this.getPublisher() +
                " | Category: " + this.getCategory() +
                " | Description: " + this.getItemDescription());
    }

    public static void updateBookIDCounter(int maxID) {
        bookIDCounter = maxID + 1;
    }
}