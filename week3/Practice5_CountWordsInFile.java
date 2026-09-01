import java.io.*;

public class Practice5_CountWordsInFile {
    public static void main(String[] args) {
        String fileName = "wordcount_sample.txt";

        // Create a sample file so the program is runnable out of the box
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write("Java is a popular programming language. Java is used everywhere.");
        } catch (IOException e) {
            System.out.println("Error creating sample file: " + e.getMessage());
        }

        int totalWords = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    String[] words = line.trim().split("\\s+");
                    totalWords += words.length;
                }
            }
            System.out.println("Total number of words in the file: " + totalWords);

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
