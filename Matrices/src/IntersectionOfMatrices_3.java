import java.util.Scanner;

public class IntersectionOfMatrices_3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int rows = scanner.nextInt();
        int cols = scanner.nextInt();
        scanner.nextLine();

        char[][] matrixA = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            String line = scanner.nextLine();

            for (int j = 0; j < cols; j++)
                matrixA[i][j] = line.charAt(j);
        }

        char[][] matrixB = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            String line = scanner.nextLine();

            for (int j = 0; j < cols; j++)
                matrixB[i][j] = line.charAt(j);
        }

        char[][] resultMatrix = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrixA[i][j] == matrixB[i][j])
                    resultMatrix[i][j] = matrixA[i][j];

                else
                    resultMatrix[i][j] = '*';
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++)
                System.out.print(resultMatrix[i][j] + " ");

            System.out.println();
        }
    }
}
