import java.util.Scanner;

public class DaysInAMonth_20 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int month = Integer.parseInt(scanner.nextLine());

        if(month == 2)
            System.out.println("28");

        else if(month % 2 != 0)
            System.out.println("31");

        else
            System.out.println("30");

        scanner.close();
    }
}