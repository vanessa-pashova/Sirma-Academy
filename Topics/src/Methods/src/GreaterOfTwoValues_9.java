import java.util.Scanner;

public class GreaterOfTwoValues_9 {

    public static int getMax(int a, int b) {
        return a > b ? a : b;
    }

    public static char getMax(char a, char b) {
        return a > b ? a : b;
    }

    public static String getMax(String a, String b) {
        return a.compareTo(b) > 0 ? a : b;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String type = scanner.nextLine();

        switch (type) {
            case "int":
                int num1 = Integer.parseInt(scanner.nextLine());
                int num2 = Integer.parseInt(scanner.nextLine());
                System.out.println(getMax(num1, num2));
                break;

            case "char":
                char char1 = scanner.nextLine().charAt(0);
                char char2 = scanner.nextLine().charAt(0);
                System.out.println(getMax(char1, char2));
                break;

            case "string":
                String str1 = scanner.nextLine();
                String str2 = scanner.nextLine();
                System.out.println(getMax(str1, str2));
                break;
        }
    }
}
