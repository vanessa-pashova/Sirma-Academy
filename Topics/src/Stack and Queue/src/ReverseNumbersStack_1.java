import java.util.ArrayDeque;
import java.util.Scanner;

public class ReverseNumbersStack_1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayDeque<Integer> stack = new ArrayDeque<>();

        String[] input = scanner.nextLine().split(" ");

        for (String number : input)
            stack.push(Integer.parseInt(number));

        while (!stack.isEmpty())
            System.out.print(stack.pop() + " ");
    }
}
