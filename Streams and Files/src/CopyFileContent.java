import java.io.*;

public class CopyFileContent {
    public static void main(String[] args) {
        String file = "/Users/vanessa.pashova/Desktop/Sirma Academy 24/Streams and Files/src/txts/text.txt",
                copyFile = "/Users/vanessa.pashova/Desktop/Sirma Academy 24/Streams and Files/src/txts/copy.txt";

        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
             BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(copyFile))) {
            byte[] buffer = new byte[1024];
            int bytesRead;

            while((bytesRead = bis.read(buffer)) != -1)
                bos.write(buffer, 0, bytesRead);

            System.out.println("Finished.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
