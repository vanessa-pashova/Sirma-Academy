class Storage_5 {
    private double capacity;
    private int countProducts;
    private double totalCost;

    public Storage_5(double capacity) {
        this.capacity = capacity;
        this.countProducts = 0;
        this.totalCost = 0;
    }

    public void addProduct(String name, double price, int quantity) {
        double productCost = price * quantity;
        if (this.capacity >= quantity) {
            this.capacity -= quantity;
            this.totalCost += productCost;
            countProducts++;
        }
    }

    public void getProducts() {
        System.out.println(countProducts);
    }

    public double getTotalCost() {
        return this.totalCost;
    }

    public double getRemainingCapacity() {
        return this.capacity;
    }

    public static void main(String[] args) {
        Storage_5 storage = new Storage_5(50);

        storage.addProduct("cucumber", 1.50, 15);
        storage.addProduct("tomato", 0.90, 25);
        storage.addProduct("bread", 1.10, 8);

        storage.getProducts();
        System.out.println(storage.getRemainingCapacity());
        System.out.println(storage.getTotalCost());
    }
}