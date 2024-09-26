import java.util.Scanner;

public class CollatzConjecture_15 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = Integer.parseUnsignedInt(scanner.nextLine());
        System.out.print(num + " ");

        while(num != 1) {
            if(num % 2 == 0)
                num /= 2;

            else {
                num *= 3;
                num++;
            }

            System.out.print(num + " ");
        }

        scanner.close();
    }
}
