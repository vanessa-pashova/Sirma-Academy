package interfacees;

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