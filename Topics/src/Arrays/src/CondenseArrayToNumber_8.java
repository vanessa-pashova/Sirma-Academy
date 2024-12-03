import java.util.Scanner;

public class CondenseArrayToNumber_8 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split(",");
        int[] nums = new int[input.length];

        for (int i = 0; i < input.length; i++)
            nums[i] = Integer.parseInt(input[i]);

        while (nums.length > 1) {
            int[] condensed = new int[nums.length - 1];

            for (int i = 0; i < nums.length - 1; i++)
                condensed[i] = nums[i] + nums[i + 1];

            nums = condensed;
        }

        System.out.println(nums[0]);
        scanner.close();
    }
}
