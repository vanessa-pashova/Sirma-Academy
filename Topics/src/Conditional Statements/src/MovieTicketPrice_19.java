import java.util.Scanner;

public class MovieTicketPrice_19 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int age = Integer.parseUnsignedInt(scanner.nextLine());

        if(age <= 12)
            System.out.println("5$");

        else if(13 <= age && age <= 19)
            System.out.println("8$");

        else
            System.out.println("10$");

        scanner.close();
    }
}
