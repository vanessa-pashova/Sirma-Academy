import java.util.ArrayDeque;
import java.util.Scanner;

public class MaximumElement_10 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        ArrayDeque<Integer> maxStack = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            String[] input = scanner.nextLine().split(" ");
            String command = input[0];

            if (command.equals("1")) {
                int x = Integer.parseInt(input[1]);
                stack.push(x);

                if (maxStack.isEmpty() || maxStack.peek() <= x)
                    maxStack.push(x);

            }

            else if (command.equals("2")) {
                int removedElement = stack.pop();

                if (removedElement == maxStack.peek())
                    maxStack.pop();
            }

            else if (command.equals("3"))
                System.out.println(maxStack.peek());
        }
    }
}
