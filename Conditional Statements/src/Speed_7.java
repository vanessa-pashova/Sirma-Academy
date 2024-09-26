import java.util.Scanner;

public class Speed_7 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int speed = Integer.parseUnsignedInt(scanner.nextLine());

        if(0 <= speed && speed <= 10)
            System.out.println("Slow\n");

        else if(11 <= speed && speed <= 60)
            System.out.println("Average\n");

        else if(61 <= speed && speed <= 120)
            System.out.println("Fast\n");

        else if(121 <= speed && speed <= 160)
            System.out.println("Super fast\n");

        else
            System.out.println("Turbo fast\n");

        scanner.close();
    }
}
