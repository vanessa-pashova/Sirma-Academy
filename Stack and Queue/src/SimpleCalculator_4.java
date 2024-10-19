import java.util.ArrayDeque;
import java.util.Scanner;

public class SimpleCalculator_4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split(" ");
        ArrayDeque<String> stack = new ArrayDeque<>();

        for (String element : input)
            stack.addLast(element);


        int result = Integer.parseInt(stack.pollFirst());

        while (!stack.isEmpty()) {
            String operator = stack.pollFirst();
            int nextNumber = Integer.parseInt(stack.pollFirst());

            if (operator.equals("+"))
                result += nextNumber;

            else if (operator.equals("-"))
                result -= nextNumber;
        }

        System.out.println(result);
    }
}
