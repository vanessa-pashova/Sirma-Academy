import java.util.Scanner;

public class NumberFrom0To9_3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = Integer.parseUnsignedInt(scanner.nextLine());

        switch (num) {
            case 1:
                System.out.println("One\n");
                break;

            case 2:
                System.out.println("Two\n");
                break;

            case 3:
                System.out.println("Three\n");
                break;

            case 4:
                System.out.println("Four\n");
                break;

            case 5:
                System.out.println("Five\n");
                break;

            case 6:
                System.out.println("Six\n");
                break;

            case 7:
                System.out.println("Seven\n");
                break;

            case 8:
                System.out.println("Eight\n");
                break;

            case 9:
                System.out.println("Nine\n");
                break;

            default:
                System.out.println("Too big\n");
                break;
        }

        scanner.close();
    }
}
