package models.interfaces_for_items;

public interface Categorizable {
    enum CategorizableType {
        BOOKS,
        CLOTHING,
        ELECTRONICS,
        GROCERIES
    }

    String getCategory();
    void setCategory(String type);
}