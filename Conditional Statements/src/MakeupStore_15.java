import java.util.Scanner;

public class MakeupStore_15 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double renovationCost = Double.parseDouble(scanner.nextLine());
        int countPowder = Integer.parseInt(scanner.nextLine());
        int countLipstick = Integer.parseInt(scanner.nextLine());
        int countSpiral = Integer.parseInt(scanner.nextLine());
        int countShadows = Integer.parseInt(scanner.nextLine());
        int countConcealer = Integer.parseInt(scanner.nextLine());

        double pricePowder = 2.60;
        double priceLipstick = 3.00;
        double priceSpiral = 4.10;
        double priceShadows = 8.20;
        double priceConcealer = 2.00;

        double totalIncome = (countPowder * pricePowder) +
                (countLipstick * priceLipstick) +
                (countSpiral * priceSpiral) +
                (countShadows * priceShadows) +
                (countConcealer * priceConcealer);

        int totalItemCount = countPowder + countLipstick + countSpiral + countShadows + countConcealer;

        if (totalItemCount >= 50)
            totalIncome *= 0.75;

        totalIncome *= 0.90;

        if (totalIncome >= renovationCost)
            System.out.printf("Yes! %.2f lv left.%n", totalIncome - renovationCost);

        else
            System.out.printf("Not enough money! %.2f lv needed.%n", renovationCost - totalIncome);


        scanner.close();
    }
}
