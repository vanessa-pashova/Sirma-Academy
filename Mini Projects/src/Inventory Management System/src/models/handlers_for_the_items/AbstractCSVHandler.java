package models.handlers_for_the_items;

import com.sun.source.tree.Tree;

import java.io.File;
import java.io.IOException;
import java.util.TreeMap;

public abstract class AbstractCSVHandler<T> implements CSVHandler<T> {
    @Override
    public TreeMap<String, T> loadFromCSV(String filename) {
        if (filename == null || filename.isEmpty())
            throw new IllegalArgumentException(">! Filename cannot be null or empty, [AbstractCSVHandler, loadFromCSV().");

        File file = new File(filename);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.print(">! Error while creating loading file, [AbstractCSVHandler, loadFromCSV()].");
                System.out.println(e.getMessage());
            }
        }

        return new TreeMap<>();
    }

    @Override
    public void saveToCSV(String filename, TreeMap<String, T> data) {
        if (filename == null || filename.isEmpty())
            throw new IllegalArgumentException(">! Filename cannot be null or empty, [AbstractCSVHandler, loadFromCSV().");

        File file = new File(filename);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.print(">! Error while creating loading file, [AbstractCSVHandler, loadFromCSV()].");
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public void removeFromCSV(String filename, String id) {
        if (filename == null || filename.isEmpty())
            throw new IllegalArgumentException(">! Filename cannot be null or empty, [AbstractCSVHandler, loadFromCSV().");

        TreeMap<String, T> items = loadFromCSV(filename);
        if (!items.containsKey(id))
            throw new IllegalArgumentException(">! Item does not exist, [AbstractCSVHandler, removeFromCSV().");

        else {
            items.remove(id);
            saveToCSV(filename, items);
        }
    }
}
