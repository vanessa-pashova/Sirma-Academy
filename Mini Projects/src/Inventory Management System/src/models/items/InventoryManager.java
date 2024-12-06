package models.items;

import interfacees.AbstractItem;

import java.util.TreeMap;

public class InventoryManager extends AbstractItem {
    private TreeMap<Integer, AbstractItem> inventory;       //Collection of items
    private static int nextID = 1;                          //ID of an item

    public InventoryManager() {}

    public InventoryManager(String name, double price, CategorizableType category) {
        super(name, price, category);
        this.inventory = new TreeMap<>();
    }

    public TreeMap<Integer, AbstractItem> getInventory() {
        return this.inventory;
    }

    public void addItem(AbstractItem item) {
        if(item == null)
            throw new IllegalArgumentException(">! Cannot add null item [InventoryManager, addItem()].");

        inventory.put(nextID++, item);
    }

    public void removeItem(int id) {
        if(inventory.containsKey(id))
            inventory.remove(id);

        else
            throw new IllegalArgumentException(">! Item does not exist [InventoryManager, removeItem()].");
    }

    public void printInventory() {
        if(inventory.isEmpty())
            System.out.println("------ INVENTORY'S EMPTY, NOTHING TO SHOW ------");

        else {
            System.out.println("------ PRINTING INVENTORY INFORMATION ------");
            this.inventory.forEach((id, item) -> {
                System.out.println("ID: " + id +
                        ", Name: " + item.getName() +
                        ", Discount: " + item.calculatePrice() +
                        ", Price (after dicount): " + item.getPrice() +
                        ", Category: " + item.getCategory());
            });
            System.out.println("> Total Items: " + inventory.size());
            System.out.println("-------------------------------------------");
        }
    }

    public AbstractItem findItem(int id) {
        if(!this.inventory.containsKey(id))
            throw new IllegalArgumentException(">! Item does not exist [InventoryManager, findItem()].");

        return this.inventory.get(id);
    }

    public void clearInventory() {
        this.inventory.clear();
        System.out.println("----- INVENTORY CLEARED ------");
    }

    public double getTotalValue() {
        return this.inventory.values().stream().mapToDouble(AbstractItem::getPrice).sum();
    }

    @Override
    public void setCategory(CategorizableType category) {
        super.setCategory(category);
    }
}