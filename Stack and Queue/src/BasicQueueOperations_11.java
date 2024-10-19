import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Scanner;

public class BasicQueueOperations_11 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int s = scanner.nextInt();
        int x = scanner.nextInt();

        ArrayDeque<Integer> queue = new ArrayDeque<>();

        for (int i = 0; i < n; i++)
            queue.offer(scanner.nextInt());

        for (int i = 0; i < s; i++)
            queue.poll();

        if (queue.contains(x))
            System.out.println("true");

        else {
            if (queue.isEmpty())
                System.out.println(0);

            else
                System.out.println(Collections.min(queue));
        }
    }
}
