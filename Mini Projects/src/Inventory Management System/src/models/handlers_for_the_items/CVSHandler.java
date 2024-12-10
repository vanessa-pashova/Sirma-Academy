package models.handlers_for_the_items;

import java.util.TreeMap;

public interface CVSHandler<T> {
    TreeMap<Integer, T> loadFromCVS(String fileName);
    void saveToCVS(String fileName, TreeMap<Integer, T> items);
    void removeFromCVS(String fileName, int id);
}