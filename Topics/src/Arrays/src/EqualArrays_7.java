import java.util.Scanner;

public class EqualArrays_7 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] firstArr = scanner.nextLine().split(","),
                secondArr = scanner.nextLine().split(",");

        int sum = 0;
        boolean areIdentical = true;

        if (firstArr.length != secondArr.length) {
            System.out.println("Arrays are not identical. Found difference at 0 index.");
            return;
        }

        for (int i = 0; i < firstArr.length; i++) {
            int firstNum = Integer.parseInt(firstArr[i]),
                secondNum = Integer.parseInt(secondArr[i]);

            if (firstNum != secondNum) {
                System.out.println("Arrays are not identical. Found difference at " + i + " index.");
                areIdentical = false;
                break;
            }

            else
                sum += firstNum;
        }

        if (areIdentical)
            System.out.println("Arrays are identical. Sum: " + sum);

        scanner.close();
    }
}
