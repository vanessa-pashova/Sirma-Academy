import java.util.Scanner;

public class PotionBrewingDecision_26 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String firstIng = scanner.nextLine(),
                secondIng = scanner.nextLine();

        if(firstIng.equals("herbs")) {
            if(secondIng.equals("water"))
                System.out.println("health potion");

            else if(secondIng.equals("oil"))
                System.out.println("stealth potion");

            else
                System.out.println("stamina potion");
        }

        else if(firstIng.equals("berries")) {
            if(secondIng.equals("sugar"))
                System.out.println("speed potion");

            else
                System.out.println("energy potion");
        }

        else
            System.out.println("can't brew any potion");

        scanner.close();
    }
}
