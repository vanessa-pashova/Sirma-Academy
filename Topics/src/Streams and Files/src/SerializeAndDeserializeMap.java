import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class SerializeAndDeserializeMap {
    public static void main(String[] args) {
        String filename = "/Users/vanessa.pashova/Desktop/Sirma Academy 24/Streams and Files/src/txts/map.ser";

        Map<String, Integer> map = new HashMap<>();
        map.put("Vanesa", 24);
        map.put("Martin", 28);
        map.put("Lyuba", 21);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(map);
            System.out.println("Map is serialized.\n");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            Map<String, Integer> deserializedMap = (Map<String, Integer>) ois.readObject();
            System.out.println("Map is deserialized.\n");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
