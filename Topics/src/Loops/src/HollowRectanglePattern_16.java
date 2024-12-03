import java.util.Scanner;

public class HollowRectanglePattern_16{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine()),
                m = Integer.parseInt(scanner.nextLine());

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(i == 0 || i == (n - 1) || j == 0 || j == (m - 1))
                    System.out.print("*");

                else
                    System.out.print(" ");
            }

            System.out.println();
        }

        scanner.close();
    }
}
