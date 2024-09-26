import java.util.Scanner;

public class MovieClassifivation_23 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int age = Integer.parseUnsignedInt(scanner.nextLine());

        if(age <= 12)
            System.out.println("U-rated movies");

        else if(13 <= age && age <= 17)
            System.out.println("U and PG-13 rated movies");

        else
            System.out.println("All movies");

        scanner.close();
    }
}