import java.util.Scanner;

public class AirlineLuggageCharges_24 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int weight = Integer.parseInt(scanner.nextLine());
        int sumOfDimms = Integer.parseInt(scanner.nextLine());
        int fee = 0;

        boolean overweight = false;
        boolean oversize = false;

        if (weight > 50) {
            fee += 100;
            overweight = true;
        }

        if (sumOfDimms > 158) {
            oversize = true;
            int excess = sumOfDimms - 158;

            if (excess <= 20)
                fee += 50;

            else if (excess <= 50)
                fee += 100;

            else
                fee += 200;
        }

        if (overweight && oversize) {
            fee += 50;
            System.out.printf("%d fee: oversize and overweight", fee);
        }

        else if (overweight)
            System.out.printf("%d fee: overweight", fee);

        else if (oversize)
            System.out.printf("%d fee: oversize", fee);

        else
            System.out.println("No extra fees");


        scanner.close();
    }
}
