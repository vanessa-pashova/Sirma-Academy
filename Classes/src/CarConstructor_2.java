import java.util.Scanner;

public class CarConstructor_2 {
    private String brand;
    private String model;
    private int horsePower;

    public CarConstructor_2(String brand) {
        this(brand, "unknown", -1);
    }

    public CarConstructor_2(String brand, String model) {
        this(brand, model, -1);
    }

    public CarConstructor_2(String brand, String model, int horsePower) {
        this.brand = brand;
        this.model = model;
        this.horsePower = horsePower;
    }

    public String carInfo() {
        return String.format("The car is: %s %s - %d HP.", this.brand, this.model, this.horsePower);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < n; i++) {
            String[] carData = scanner.nextLine().split(" ");
            CarConstructor_2 car;

            if (carData.length == 1)
                car = new CarConstructor_2(carData[0]);

            else if (carData.length == 2)
                car = new CarConstructor_2(carData[0], carData[1]);

            else
                car = new CarConstructor_2(carData[0], carData[1], Integer.parseInt(carData[2]));

            System.out.println(car.carInfo());
        }
    }
}
