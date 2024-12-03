import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;

public class WordLengths {
    private static void readWordsFromFile(String path) {
        //try-catch-resources
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line = "";

            while((line = reader.readLine()) != null) {
                String words[] = line.split("\\s+");

                for(String word : words)
                    System.out.print(word.length() + " ");
            }
        } catch (IOException e) {
            System.out.println("Error occurred while reading the file.\n");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String path = "/Users/vanessa.pashova/Desktop/Sirma Academy 24/Streams and Files/src/txts/text.txt";
        readWordsFromFile(path);
    }
}
