import java.util.ArrayDeque;
import java.util.Scanner;

public class BalancedParentheses_7 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        ArrayDeque<Character> stack = new ArrayDeque<>();

        boolean isBalanced = true;

        for (int i = 0; i < input.length(); i++) {
            char current = input.charAt(i);

            if (current == '(' || current == '{' || current == '[')
                stack.push(current);

            else {
                if (stack.isEmpty()) {
                    isBalanced = false;
                    break;
                }

                char lastOpened = stack.pop();

                if (current == ')' && lastOpened != '(' ||
                        current == '}' && lastOpened != '{' ||
                        current == ']' && lastOpened != '[') {
                    isBalanced = false;
                    break;
                }
            }
        }

        if (isBalanced && stack.isEmpty())
            System.out.println("True");

        else
            System.out.println("False");
    }
}
