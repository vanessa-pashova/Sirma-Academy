package models.interfaces_for_items;

public interface Categorizable {
    enum CategorizableType {
        BOOKS,
        CLOTHING,
        ELECTRONICS,
        GROCERIES
    }

    CategorizableType getCategory();
    void setCategory(CategorizableType type);
}