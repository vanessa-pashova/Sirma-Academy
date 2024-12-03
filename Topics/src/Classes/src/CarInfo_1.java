import java.util.Scanner;

public class CarInfo_1 {
    private String brand;
    private String model;
    private int horsePower;

    public CarInfo_1(String brand, String model, int horsePower) {
        this.brand = brand;
        this.model = model;
        this.horsePower = horsePower;
    }

    public String carInfo() {
        return "The car is: " + this.brand + " " + this.model + " – " + this.horsePower + " HP.";
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        CarInfo_1[] cars = new CarInfo_1[n];

        for (int i = 0; i < n; i++) {
            String[] data = scanner.nextLine().split(" ");
            String brand = data[0];
            String model = data[1];
            int horsePower = Integer.parseInt(data[2]);

            cars[i] = new CarInfo_1(brand, model, horsePower);
        }

        for (int i = 0; i < n; i++)
            System.out.println(cars[i].carInfo());
    }
}
