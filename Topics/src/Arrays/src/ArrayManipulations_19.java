import java.util.Scanner;
import java.util.Arrays;

public class ArrayManipulations_19 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] numbers = scanner.nextLine().split(" "),
                commands = scanner.nextLine().split(", ");

        for (String command : commands) {
            String[] commandParts = command.split(" ");
            String action = commandParts[0];

            if (action.equals("Add")) {
                String[] newArray = Arrays.copyOf(numbers, numbers.length + 1);
                newArray[numbers.length] = commandParts[1];
                numbers = newArray;
            }

            else if (action.equals("Remove")) {
                String elementToRemove = commandParts[1];
                int count = 0;

                for (String num : numbers) {
                    if (num.equals(elementToRemove))
                        count++;

                }

                String[] newArray = new String[numbers.length - count];
                int index = 0;

                for (String num : numbers) {
                    if (!num.equals(elementToRemove))
                        newArray[index++] = num;
                }

                numbers = newArray;
            }

            else if (action.equals("RemoveAt")) {
                int indexToRemove = Integer.parseInt(commandParts[1]);
                String[] newArray = new String[numbers.length - 1];
                int index = 0;

                for (int i = 0; i < numbers.length; i++) {
                    if (i != indexToRemove)
                        newArray[index++] = numbers[i];
                }

                numbers = newArray;
            }

            else if (action.equals("Insert")) {
                int insertIndex = Integer.parseInt(commandParts[2]);
                String elementToInsert = commandParts[1];
                String[] newArray = new String[numbers.length + 1];
                int index = 0;

                for (int i = 0; i < numbers.length; i++) {
                    if (i == insertIndex)
                        newArray[index++] = elementToInsert;

                    newArray[index++] = numbers[i];
                }

                if (insertIndex == numbers.length)
                    newArray[newArray.length - 1] = elementToInsert;

                numbers = newArray;
            }
        }

        for (String number : numbers)
            System.out.print(number + " ");

        scanner.close();
    }
}
