import java.util.Scanner;

public class SumOfVowels_6 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String word = scanner.nextLine();
        int sum = 0;

        for(int i = 0; i < word.length(); i++) {
            switch (word.charAt(i)) {
                case 'a':
                    sum += 1;
                    break;

                case 'e':
                    sum += 2;
                    break;

                case 'i':
                    sum += 3;
                    break;

                case 'o':
                    sum += 4;
                    break;

                case 'u':
                    sum += 5;
                    break;
            }
        }

        System.out.print("Sum: " + sum);
        scanner.close();
    }
}
