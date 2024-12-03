import java.util.Scanner;

public class DayOfWeek_2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int day = Integer.parseInt(scanner.nextLine());
        String[] daysOfWeek = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

        if (day >= 1 && day <= 7)
            System.out.println(daysOfWeek[day - 1]);

        else
            System.out.println("Invalid day!");

        scanner.close();
    }
}
