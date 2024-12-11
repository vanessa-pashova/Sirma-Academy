package models.interfaces_for_items;

public interface Categorizable {
    enum CategorizableType {
        ELECTRONICS,
        BOOKS,
        CLOTHING,
        GROCERIES;
    }

    CategorizableType getCategory();
    void setCategory(CategorizableType type);
}