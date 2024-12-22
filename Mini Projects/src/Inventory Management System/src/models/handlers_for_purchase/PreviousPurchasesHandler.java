package models.handlers_for_purchase;

import models.items.AbstractItem;
import models.user.Customer;

import java.util.TreeMap;

public class PreviousPurchasesHandler extends AbstractUserShoppingHandler {
    private final String fileName;

    public PreviousPurchasesHandler(String fileName) {
        if (fileName == null || fileName.isEmpty())
            throw new IllegalArgumentException(">! Filename cannot be null or empty, [PreviousPurchasesHandler].");
        this.fileName = fileName;
    }

    public TreeMap<String, AbstractItem> loadPreviousPurchases() {
        return super.loadFromCSV(fileName);
    }

    public void savePreviousPurchases(TreeMap<String, AbstractItem> purchases, String email) {
        super.saveToCSV(fileName, purchases, email);
    }

    public void removePreviousPurchase(String id) {
        super.removeFromCSV(fileName, id);
    }

    @Override
    public void saveToCSV(String fileName, TreeMap<String, AbstractItem> items) {}
}
