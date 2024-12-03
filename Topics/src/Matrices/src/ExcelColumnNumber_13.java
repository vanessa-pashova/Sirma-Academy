import java.util.Scanner;

public class ExcelColumnNumber_13 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String columnName = scanner.nextLine();

        System.out.println(convertToNumber(columnName));
    }

    public static int convertToNumber(String columnName) {
        int result = 0;

        for (int i = 0; i < columnName.length(); i++)
            result = result * 26 + (columnName.charAt(i) - 'A' + 1);

        return result;
    }
}
