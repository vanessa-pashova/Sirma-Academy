import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ExtractSpecificFilesFromZip {
    public static void main(String[] args) {
        String zipFilePath = "archive.zip", outputDir = "extracted_files";

        File outputDirectory = new File(outputDir);
        if (!outputDirectory.exists())
            outputDirectory.mkdir();

        try (ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(zipFilePath))) {
            ZipEntry entry;

            while ((entry = zipInputStream.getNextEntry()) != null) {
                if (entry.getName().endsWith(".txt")) {
                    File extractedFile = new File(outputDir, entry.getName());

                    new File(extractedFile.getParent()).mkdirs();

                    try (BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(extractedFile))) {
                        byte[] buffer = new byte[1024];
                        int bytesRead;

                        while ((bytesRead = zipInputStream.read(buffer)) != -1)
                            outputStream.write(buffer, 0, bytesRead);
                    }

                    System.out.println("File: " + extractedFile.getAbsolutePath());
                }

                zipInputStream.closeEntry();
            }

            System.out.println("Finished.\n");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}