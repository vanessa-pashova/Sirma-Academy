import java.util.Scanner;

public class LargerNumber_2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int x = Integer.parseInt(scanner.nextLine()),
                y = Integer.parseInt(scanner.nextLine());

        if(x > y)
            System.out.printf("%d is greater than %d", x, y);

        else if(y > x)
            System.out.printf("%d is greater than %d", y, x);

        else
            System.out.println("They are equal");
    }
}
