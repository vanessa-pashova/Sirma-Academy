import java.util.Scanner;

public class AtSea_16 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int days = Integer.parseInt(scanner.nextLine());
        String room = scanner.nextLine();
        String assessment = scanner.nextLine();

        double single = 25;
        double apartment = 50;
        double presidential = 100;
        double total = 0;

        days--;

        switch (room) {
            case "single room":
                total = days * single;
                break;

            case "apartment":
                total = days * apartment;
                if (days < 10)
                    total *= 0.7;

                else if (days <= 15)
                    total *= 0.65;

                else
                    total *= 0.5;

                break;

            case "presidential":
                total = days * presidential;
                if (days < 10)
                    total *= 0.9;

                else if (days <= 15)
                    total *= 0.85;

                else
                    total *= 0.8;

                break;

            default:
                System.out.println("Invalid room type");
                scanner.close();
                return;
        }

        if (assessment.equals("positive"))
            total *= 1.25;

        else if (assessment.equals("negative"))
            total *= 0.9;

        else {
            System.out.println("Invalid assessment");
            scanner.close();
            return;
        }

        System.out.printf("Total: %.2f%n", total);
        scanner.close();
    }
}
