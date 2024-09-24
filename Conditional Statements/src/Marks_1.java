import java.util.Scanner;

public class Marks_1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double mark = Double.parseDouble(scanner.nextLine());

        if(mark >= 5.5 && mark <= 6)
            System.out.println("Excellent!\n");

        else
            System.out.println("No output!\n");
    }
}