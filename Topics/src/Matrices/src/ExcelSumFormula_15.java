import java.util.Scanner;

public class ExcelSumFormula_15 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int rows = scanner.nextInt();
        int cols = scanner.nextInt();
        int[][] sheet = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++)
                sheet[i][j] = scanner.nextInt();
        }

        int startRow = scanner.nextInt();
        int endRow = scanner.nextInt();
        int startCol = scanner.nextInt();
        int endCol = scanner.nextInt();

        int sum = calculateSumInRange(sheet, startRow, endRow, startCol, endCol);
        System.out.println(sum);
    }

    public static int calculateSumInRange(int[][] sheet, int startRow, int endRow, int startCol, int endCol) {
        int sum = 0;

        for (int i = startRow; i <= endRow; i++) {
            for (int j = startCol; j <= endCol; j++)
                sum += sheet[i][j];
        }

        return sum;
    }
}
