package models.items;

public class BookItems extends InventoryManager {
    private String author;
    private String genre;      //can make it enum
    private int totalPages;
    private String publisher;

    public BookItems(String name, double price, String author, String genre, int totalPages, String publisher) {
        super(name, price, CategorizableType.BOOKS);
        this.setAuthor(author);
        this.setGenre(genre);
        this.setTotalPages(totalPages);
        this.setPublisher(publisher);
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        if (author == null || author.isEmpty()) {
            throw new IllegalArgumentException(">! Author name cannot be empty [BookItems, setAuthor()].");
        }
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        if (genre == null || genre.isEmpty())
            throw new IllegalArgumentException(">! Genre cannot be empty [BookItems, setGenre()].");

        this.genre = genre;
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
    public void printInventory() {
        if (this.getInventory().isEmpty()) {
            System.out.println("------ BOOK INVENTORY'S EMPTY, NOTHING TO SHOW ------");
        } else {
            System.out.println("------ PRINTING BOOK INVENTORY INFORMATION ------");
            this.getInventory().forEach((id, item) -> {
                System.out.println("ID: " + id +
                        ", Name: " + item.getName() +
                        ", Author: " + this.getAuthor() +
                        ", Genre: " + this.getGenre() +
                        ", Pages: " + this.getTotalPages() +
                        ", Publisher: " + this.getPublisher() +
                        ", Discount: " + item.getDiscount() +
                        ", Price (after discount): " + item.getPrice());
            });
            System.out.println("> Total Books: " + this.getInventory().size());
            System.out.println("-------------------------------------------");
        }
    }
}