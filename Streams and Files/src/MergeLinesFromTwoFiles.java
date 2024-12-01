import java.io.*;

public class MergeLinesFromTwoFiles {
    public static void main(String[] args) {
        String file1 = "/Users/vanessa.pashova/Desktop/Sirma Academy 24/Streams and Files/src/txts/file1.txt";
        String file2 = "/Users/vanessa.pashova/Desktop/Sirma Academy 24/Streams and Files/src/txts/file2.txt";
        String output = "/Users/vanessa.pashova/Desktop/Sirma Academy 24/Streams and Files/src/txts/mergeFiles.txt";

        try (BufferedReader reader1 = new BufferedReader(new FileReader(file1));
             BufferedReader reader2 = new BufferedReader(new FileReader(file2));
             PrintWriter writer = new PrintWriter(new FileWriter(output))) {

            String line1, line2;

            while ((line1 = reader1.readLine()) != null | (line2 = reader2.readLine()) != null) {
                if (line1 != null)
                    writer.println(line1);

                if (line2 != null)
                    writer.println(line2);
            }

            System.out.println("Finished.");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
