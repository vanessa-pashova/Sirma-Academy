package models.handlers_for_purchase;

import models.items.AbstractItem;
import models.user.Customer;

import java.util.TreeMap;

public class FavouritesHandler extends AbstractUserShoppingHandler {
    private final String fileName;

    public FavouritesHandler(String fileName) {
        if (fileName == null || fileName.isEmpty())
            throw new IllegalArgumentException(">! Filename cannot be null or empty, [FavouritesHandler].");
        this.fileName = fileName;
    }

    public TreeMap<String, AbstractItem> loadFavourites() {
        return super.loadFromCSV(fileName);
    }

    public void saveFavourites(TreeMap<String, AbstractItem> favourites, String email) {
        super.saveToCSV(fileName, favourites, email);
    }

    public void removeFavourite(String id) {
        super.removeFromCSV(fileName, id);
    }

    @Override
    public void saveToCSV(String fileName, TreeMap<String, AbstractItem> items) {}
}
