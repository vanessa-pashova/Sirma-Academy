import java.util.ArrayDeque;
import java.util.Scanner;

public class PrinterQueue_8 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayDeque<String> queue = new ArrayDeque<>();

        String command = scanner.nextLine();
        while (!command.equals("print")) {
            if (command.equals("cancel")) {
                if (queue.isEmpty())
                    System.out.println("Standby");

                else
                    System.out.println("Canceled " + queue.poll());
            }

            else
                queue.offer(command);

            command = scanner.nextLine();
        }

        while (!queue.isEmpty())
            System.out.println(queue.poll());
    }
}
