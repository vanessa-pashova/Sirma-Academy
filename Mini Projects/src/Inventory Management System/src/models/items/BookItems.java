package models.items;

public class BookItems extends AbstractItem {
    public enum Genre {     //public only for the tests - they all passed
        FANTASY,
        SCIENCE_FICTION,
        MYSTERY,
        NON_FICTION,
        BIOGRAPHY
    }

    private String author;
    private Genre genre;
    private int totalPages;
    private String publisher;
    private static int bookIDCounter = 1;

    private String generateBookID() {
        return "B-" + (bookIDCounter++);
    }

    public BookItems(String name, double discount, double price,  String author, String genre, int totalPages, String publisher, String description) {
        super(name, discount, price, CategorizableType.BOOKS);
        this.setAuthor(author);
        this.setGenre(genre);
        this.setTotalPages(totalPages);
        this.setPublisher(publisher);
        this.setItemDescription(description);
        this.setFragile(false);
        this.setSellable(true);
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        if (author == null || author.isEmpty())
            throw new IllegalArgumentException(">! Author name cannot be empty [BookItems, setAuthor()].");

        this.author = author;
    }

    public Genre getGenre() {
        return this.genre;
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

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        if (totalPages <= 0)
            throw new IllegalArgumentException(">! Total pages must be greater than zero [BookItems, setTotalPages()].");

        this.totalPages = totalPages;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        if (publisher == null || publisher.isEmpty())
            throw new IllegalArgumentException(">! Publisher name cannot be empty [BookItems, setPublisher()].");

        this.publisher = publisher;
    }

    @Override
    public void printDetails() {
        System.out.println("Name: " + this.getName() +
                " | Author: " + this.getAuthor() +
                " | Genre: " + this.getGenre() +
                " | Pages: " + this.getTotalPages() +
                " | Publisher: " + this.getPublisher() +
                " | Discount: " + this.getDiscount() +
                " | Price (after discount): " + this.getPrice());
    }
}