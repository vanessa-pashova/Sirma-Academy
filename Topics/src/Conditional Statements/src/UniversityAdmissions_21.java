import java.util.Scanner;

public class UniversityAdmissions_21 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int score = Integer.parseInt(scanner.nextLine()),
                extras = Integer.parseInt(scanner.nextLine());

        if(score >= 90)
            System.out.println("Admitted");

        else if((80 <= score && score <= 89) && extras >= 2)
            System.out.println("Admitted");

        else
            System.out.println("Not admitted");

        scanner.close();
    }
}
