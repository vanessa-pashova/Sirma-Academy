package models.interfaces;

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