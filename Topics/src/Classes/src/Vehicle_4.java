public class Vehicle_4 {
    private String type;
    private String model;
    private int enginePower;
    private double fuel;

    public Vehicle_4(String type, String model, int enginePower, double fuel) {
        this.type = type;
        this.model = model;
        this.enginePower = enginePower;
        this.fuel = fuel;
    }

    public void drive(double fuelLoss) {
        this.fuel -= fuelLoss;
    }

    public String getType() {
        return type;
    }

    public String getModel() {
        return model;
    }

    public int getEnginePower() {
        return enginePower;
    }

    public double getFuel() {
        return fuel;
    }

    public static void main(String[] args) {
        Vehicle_4 car = new Vehicle_4("Car", "ModelX", 150, 100.0);
        car.drive(20.0);
        System.out.println(car.getFuel());
    }
}
