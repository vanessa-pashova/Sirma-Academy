import java.util.Scanner;

public class RotateArray_11 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] array = scanner.nextLine().split(",");
        int rotations = Integer.parseInt(scanner.nextLine()) % array.length;

        for (int i = 0; i < rotations; i++) {
            String lastElement = array[array.length - 1];

            for (int j = array.length - 1; j > 0; j--)
                array[j] = array[j - 1];

            array[0] = lastElement;
        }

        for (String element : array)
            System.out.print(element + " ");

        scanner.close();
    }
}
