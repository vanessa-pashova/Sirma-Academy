import java.io.*;

public class ExtractFile {
    public static void main(String[] args) {
        String filepath = "/Users/vanessa.pashova/Desktop/Sirma Academy 24/Streams and Files/src/txts/address.txt";
        String filename = null, format = null;
        int len = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line = reader.readLine();

            if(line == null || line.isEmpty())
                throw new IllegalArgumentException("Empty file");

            String[] parts = line.split("\\\\"); //wtf just for a \
            if(parts.length == 0)
                throw new IllegalArgumentException("Invalid or empty address.");

            len = line.length();
            String targetFile = parts[parts.length - 1];

            int dotIndex = targetFile.lastIndexOf('.');
            if (0 < dotIndex && dotIndex < targetFile.length() - 1) {
                filename = targetFile.substring(0, dotIndex);
                format = targetFile.substring(dotIndex + 1);
            }

            else {
                filename = targetFile;
                format = "No extension";
            }

            System.out.println("Filename: " + filename);
            System.out.println("Format: " + format);
            System.out.println("Length: " + len);

        } catch (IOException e) {
            System.out.println("Error while reading file. " + e.getMessage());
        }
    }
}
