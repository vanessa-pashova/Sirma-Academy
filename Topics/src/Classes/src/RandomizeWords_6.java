import java.util.Random;
import java.util.Scanner;

public class RandomizeWords_6 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a sentence:");
        String input = scanner.nextLine();

        String[] words = input.split(" ");

        Random random = new Random();

        for (int i = 0; i < words.length; i++) {
            int randomIndex = random.nextInt(words.length);
            String temp = words[i];
            words[i] = words[randomIndex];
            words[randomIndex] = temp;
        }

        for (String word : words)
            System.out.println(word);
    }
}
