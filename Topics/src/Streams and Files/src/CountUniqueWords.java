import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class CountUniqueWords {
    public static void main(String[] args) {
        String inputFile = "/Users/vanessa.pashova/Desktop/Sirma Academy 24/Streams and Files/src/txts/text.txt";

        Set<String> uniqueWords = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;

            while((line = reader.readLine()) != null) {
                String []words = line.toLowerCase().split("\\W+");
                Collections.addAll(uniqueWords, words);
            }

            System.out.println(uniqueWords.size());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
