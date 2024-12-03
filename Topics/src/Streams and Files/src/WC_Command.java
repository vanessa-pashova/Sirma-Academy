import java.io.*;

public class WC_Command {
    public static void main(String[] args) {
        String filename = "/Users/vanessa.pashova/Desktop/Sirma Academy 24/Streams and Files/src/txts/text.txt";
        int lineCnt = 0, wordCnt = 0, symbolCnt = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;

            while((line = reader.readLine()) != null) {
                lineCnt++;

                String[] words = line.trim().split("\\s+");
                if(line.trim().isEmpty())
                    wordCnt += 0;

                else
                    wordCnt += words.length;

                symbolCnt += line.length();
            }

            System.out.println("Line count: " + lineCnt);
            System.out.println("Word count: " + wordCnt);
            System.out.println("Symbol count: " + symbolCnt);
        } catch (IOException e) {
            System.out.println("Error while reading file");
            return;
        }
    }
}
