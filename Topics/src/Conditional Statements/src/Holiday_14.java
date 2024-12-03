import java.util.Scanner;

public class Holiday_14 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double camp = 0;
        double budget = Double.parseDouble(scanner.nextLine());
        String season = scanner.nextLine();

        if(budget <= 100) {
            System.out.println("Somewhere in Bulgaria");

            if(season.equals("summer")) {
                camp = budget * 0.3;
                System.out.printf("Camp: %.2f", camp);
            }

            else if(season.equals("winter")) {
                camp = budget * 0.7;
                System.out.printf("Camp: %.2f", camp);
            }
        }

        else if(101 <= budget && budget <= 1000) {
            System.out.println("Somewhere in Europe");

            if(season.equals("summer")) {
                camp = budget * 0.4;
                System.out.printf("Camp: %.2f", camp);
            }

            else if(season.equals("winter")) {
                camp = budget * 0.8;
                System.out.printf("Camp: %.2f", camp);
            }
        }

        else if(budget > 1000) {
            System.out.println("Somewhere in Asia");
            camp = budget * 0.9;
            System.out.printf("Camp: %.2f", camp);
        }

        scanner.close();
    }
}
