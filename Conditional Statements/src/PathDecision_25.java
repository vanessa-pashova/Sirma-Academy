import java.util.Scanner;

public class PathDecision_25 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String left = scanner.nextLine(),
                right = scanner.nextLine();

        if(left.equals("sword")) {
            if(right.equals("shield"))
                System.out.println("Path to the castle");

            else
                System.out.println("Path to the forest");
        }

        else if(left.equals("map")) {
            if(right.equals("coins"))
                System.out.println("Go to the town");

            else
                System.out.println("Camp");
        }

        else
            System.out.println("Wander aimlessly");

        scanner.close();
    }
}
