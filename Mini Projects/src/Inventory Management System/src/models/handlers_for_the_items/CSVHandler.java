package models.handlers_for_the_items;

import java.util.TreeMap;

public interface CSVHandler<T> {
    TreeMap<String, T> loadFromCSV(String fileName);
    void saveToCSV(String fileName, TreeMap<String, T> items);
    void removeFromCSV(String fileName, String id);
}