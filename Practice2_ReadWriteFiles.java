import java.io.*;

public class Practice2_ReadWriteFiles {
    public static void main(String[] args) {
        String fileName = "sample.txt";

        // Writing to a file
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write("Hello, this is a sample text file.\n");
            writer.write("Java file handling is easy with FileWriter and FileReader.\n");
            System.out.println("Data written to file successfully!");
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }

        // Reading from the file
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            System.out.println("\n----- File Contents -----");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
