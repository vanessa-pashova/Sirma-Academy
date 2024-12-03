import java.util.Scanner;

public class NumberInTheRange_11 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = Integer.parseInt(scanner.nextLine());

        if((-100 <= num && num <= -1) || (1 <= num && num <= 100))
            System.out.println("In range\n");

        else
            System.out.println("Out of range");

        scanner.close();
    }
}
