import java.io.*;

public class ReplaceWord {
    public static void main(String[] args) {
        String inputFile = "/Users/vanessa.pashova/Desktop/Sirma Academy 24/Streams and Files/src/txts/input.txt";
        String outputFile = "/Users/vanessa.pashova/Desktop/Sirma Academy 24/Streams and Files/src/txts/output.txt";

        String wordToReplace = null;
        String replacementWord = null;

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String firstLine = reader.readLine();

            if (firstLine != null && firstLine.contains("->")) {
                String[] parts = firstLine.split("->");

                if (parts.length == 2) {
                    wordToReplace = parts[0].trim();
                    replacementWord = parts[1].trim();
                }

                else
                    throw new IllegalArgumentException("Invalid first line format.");
            }

            else
                throw new IllegalArgumentException("First line must look like: 'word1 -> word2'!");

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
                String line;

                while ((line = reader.readLine()) != null) {
                    String replacedLine = line.replace(wordToReplace, replacementWord);
                    writer.write(replacedLine);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Error while working with files: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Error while working with the input file: " + e.getMessage());
        }
    }
}
