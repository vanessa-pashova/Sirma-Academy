import java.util.Arrays;
import java.util.Scanner;

public class ListOfProducts_18 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] products = scanner.nextLine().split(",");
        Arrays.sort(products);

        for (int i = 0; i < products.length; i++)
            System.out.println((i + 1) + "." + products[i]);

        scanner.close();
    }
}
