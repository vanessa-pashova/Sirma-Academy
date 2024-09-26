import java.util.Scanner;

public class FaceOfFigures_4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String figure = scanner.nextLine();
        double area = 0.0;

        switch (figure.toLowerCase()) {
            case "square":
                double side = Double.parseDouble(scanner.nextLine());
                area = side * side;
                System.out.printf("Area of %s: %.2f", figure, area);
                break;

            case "rectangle":
                double a = Double.parseDouble(scanner.nextLine()),
                        b = Double.parseDouble(scanner.nextLine());
                area = a * b;
                System.out.printf("Area of %s: %.2f", figure, area);
                break;

            case "triangle":
                double c = Double.parseDouble(scanner.nextLine()),
                        h = Double.parseDouble(scanner.nextLine());
                area = (c * h) / 2;
                System.out.printf("Area of %s: %.2f", figure, area);
                break;

            case "circle":
                double PI = 3.14,
                        r = Double.parseDouble(scanner.nextLine());
                area = PI * r * r;
                System.out.printf("Area of %s: %.2f", figure, area);
                break;

            default:
                System.out.println("Invalid figure\n");
                break;
        }

        scanner.close();
    }
}
