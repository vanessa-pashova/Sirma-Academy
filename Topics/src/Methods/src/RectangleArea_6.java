import java.util.Scanner;

public class RectangleArea_6 {

    public static int calculateArea(int width, int length) {
        return width * length;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int width = scanner.nextInt();
        int length = scanner.nextInt();

        int area = calculateArea(width, length);
        System.out.println(area);
    }
}
