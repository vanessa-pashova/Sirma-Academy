import java.util.Scanner;

public class GradeCalculator_17 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int score = Integer.parseInt(scanner.nextLine());

        if(90 <= score && score <= 100)
            System.out.println("A\n");

        else if(80 <= score && score <= 89)
            System.out.println("B\n");

        else if(70 <= score && score <= 79)
            System.out.println("C\n");

        else if(60 <= score && score <= 69)
            System.out.println("D\n");

        else if(score < 60)
            System.out.println("F\n");

        scanner.close();
    }
}
