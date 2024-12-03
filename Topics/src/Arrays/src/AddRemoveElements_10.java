import java.util.Scanner;

public class AddRemoveElements_10 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] commands = scanner.nextLine().split(",");
        int[] array = new int[commands.length];

        int size = 0,
                number = 1;

        for (String command : commands) {
            if (command.equals("add")) {
                array[size] = number;
                size++;
            }

            else if (command.equals("remove")) {
                if (size > 0)
                    size--;
            }

            number++;
        }

        if (size == 0)
            System.out.println("Empty");

        else {
            for (int i = 0; i < size; i++)
                System.out.print(array[i] + " ");
        }

        scanner.close();
    }
}
