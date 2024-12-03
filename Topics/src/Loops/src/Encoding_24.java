import java.util.Scanner;

public class Encoding_24 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number = Integer.parseInt(scanner.nextLine());

        String numberAsString = Integer.toString(number);
        String reversedNumber = new StringBuilder(numberAsString).reverse().toString();

        for (int i = 0; i < reversedNumber.length(); i++) {
            int digit = Character.getNumericValue(reversedNumber.charAt(i));

            if (digit == 0)
                System.out.println("ZERO");

            else {
                char symbol = (char) (digit + 33);
                
                for (int j = 0; j < digit; j++)
                    System.out.print(symbol);

                System.out.println();
            }
        }

        scanner.close();
    }
}
