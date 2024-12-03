import java.io.File;

public class CalculateDirectorySize {
    public static long calculateDirectorySize(File directory) {
        long size = 0;

        File[] files = directory.listFiles();
        if(files != null) {
            for(File file : files) {
                if(file.isFile())
                    size += file.length();

                else if(file.isDirectory())
                    calculateDirectorySize(file);
            }
        }

        return size;
    }

    public static void main(String[] args) {
        String path = "/Users/vanessa.pashova/Desktop/Sirma Academy 24/Streams and Files/src/";
        File directory = new File(path);

        if(!directory.exists() || !directory.isDirectory())
            throw new IllegalArgumentException("Invalid directory");

        else {
            long size = calculateDirectorySize(directory);
            System.out.println("Directory size: " + size);
        }
    }
}
