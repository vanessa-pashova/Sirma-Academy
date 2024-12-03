import java.util.Scanner;

public class SquareOfAsterisks_30 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print("*");

                if (j < n - 1)
                    System.out.print(" ");
            }
            
            System.out.println();
        }

        scanner.close();
    }
}
