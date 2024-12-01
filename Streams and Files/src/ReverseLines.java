import java.io.*;
import java.util.Scanner;

public class ReverseLines {
    public static void main(String[] args) {
        String inputFile = "/Users/vanessa.pashova/Desktop/Sirma Academy 24/Streams and Files/src/txts/text.txt",
                outputFile = "/Users/vanessa.pashova/Desktop/Sirma Academy 24/Streams and Files/src/txts/output.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             PrintWriter writer = new PrintWriter(new FileWriter(outputFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String reversedLine = new StringBuilder(line).reverse().toString();
                writer.println(reversedLine);
            }

            System.out.println("Saved in: " + outputFile);
        } catch (IOException e) {
            System.out.println("Error while working with files: " + e.getMessage());
        }

    }
}