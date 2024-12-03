import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class CharacterFrequency {
    public static void main(String[] args) {
        Map<Character, Integer> map = new HashMap<>();
        String filename = "/Users/vanessa.pashova/Desktop/Sirma Academy 24/Streams and Files/src/txts/text.txt";

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))) {
            int ch;

            while((ch = bufferedReader.read()) != -1) {
                char alreadyRead_ch = (char)ch;
                map.put(alreadyRead_ch, map.getOrDefault(alreadyRead_ch, 0) + 1);
            }
        } catch (IOException e) {
            System.out.println("Error while reading file: " + e.getMessage());
        }

        for(Map.Entry<Character, Integer> entry : map.entrySet())
            System.out.println(entry.getKey() + ": " + entry.getValue());
    }
}
