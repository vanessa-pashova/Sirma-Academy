import java.util.Scanner;

public class NegativePositiveNumbers_13 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split(",");
        int[] arr = new int[input.length];
        int index = 0;

        for (int i = 0; i < input.length; i++) {
            int num = Integer.parseInt(input[i]);

            if (num < 0)
                System.out.println(num);

            else
                arr[index++] = num;
        }

        for (int i = 0; i < index; i++)
            System.out.println(arr[i]);

        scanner.close();
    }
}
