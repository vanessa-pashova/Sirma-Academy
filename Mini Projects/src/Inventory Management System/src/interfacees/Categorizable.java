package interfacees;

public interface Categorizable {
    enum CategorizableType {
        ELECTRONICS,
        BOOKS,
        CLOTHING,
        FOOD;
    }

    CategorizableType getCategory();
    void setCategory(CategorizableType type);
}