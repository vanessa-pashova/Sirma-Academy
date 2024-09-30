import java.util.Scanner;

public class PadawanEquipment_19 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double money = Double.parseDouble(scanner.nextLine());
        int students = Integer.parseInt(scanner.nextLine());
        double priceLightsaber = Double.parseDouble(scanner.nextLine());
        double priceRobe = Double.parseDouble(scanner.nextLine());
        double priceBelt = Double.parseDouble(scanner.nextLine());

        int lightsabersNeeded = (int) Math.ceil(students * 1.1);

        int freeBelts = students / 6;
        int beltsNeeded = students - freeBelts;

        double totalCost = (lightsabersNeeded * priceLightsaber) + (students * priceRobe) + (beltsNeeded * priceBelt);

        if (totalCost <= money)
            System.out.printf("The money is enough - it would cost %.2flv.", totalCost);

        else
            System.out.printf("George Lucas will need %.2flv more.", totalCost - money);

        scanner.close();
    }
}
