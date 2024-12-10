package models.items;

public class BookItems extends InventoryManager {
    enum Genre {
        FANTASY,
        SCIENCE_FICTION,
        MYSTERY,
        NON_FICTION,
        BIOGRAPHY;
    }

    private String author;
    private Genre genre;
    private int totalPages;
    private String publisher;

    public BookItems(String name, double price, double discount, String author, String genre, int totalPages, String publisher, String description) {
        super(name, price, CategorizableType.BOOKS, discount);
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
        if (author == null || author.isEmpty()) {
            throw new IllegalArgumentException(">! Author name cannot be empty [BookItems, setAuthor()].");
        }
        this.author = author;
    }

    public Genre getGenre() {
        return this.genre;
    }

    public void setGenre(String genre) {
        switch (genre.toLowerCase()) {
            case "FANTASY" -> this.genre = Genre.FANTASY;
            case "SCIENCE_FICTION" -> this.genre = Genre.SCIENCE_FICTION;
            case "MYSTERY" -> this.genre = Genre.MYSTERY;
            case "NON_FICTION" -> this.genre = Genre.NON_FICTION;
            case "BIOGRAPHY" -> this.genre = Genre.BIOGRAPHY;
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
    public void printInventory() {
        if (this.getInventory().isEmpty()) {
            System.out.println("------ BOOK INVENTORY'S EMPTY, NOTHING TO SHOW ------");
        } else {
            System.out.println("------ PRINTING BOOK INVENTORY INFORMATION ------");
            this.getInventory().forEach((id, item) -> {
                System.out.println("ID: " + id +
                        "| Name: " + item.getName() +
                        "| Author: " + this.getAuthor() +
                        "| Genre: " + this.getGenre() +
                        "| Pages: " + this.getTotalPages() +
                        "| Publisher: " + this.getPublisher() +
                        "| Discount: " + item.getDiscount() +
                        "| Price (after discount): " + item.getPrice());
            });
            System.out.println("> Total Books: " + this.getInventory().size());
            System.out.println("-------------------------------------------");
        }
    }
}