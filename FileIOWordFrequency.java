import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class FileIOWordFrequency {
    public static void main(String[] args) {
        String inputFile = "input.txt";
        String outputFile = "output.txt";

        // Create a sample input file first (so the program is runnable out of the box)
        try (FileWriter sampleWriter = new FileWriter(inputFile)) {
            sampleWriter.write("Java is fun. Java is powerful. Learning Java is rewarding.");
        } catch (IOException e) {
            System.out.println("Error creating sample input file: " + e.getMessage());
        }

        Map<String, Integer> wordCount = new HashMap<>();

        // Reading the file
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Remove punctuation and split into words
                String[] words = line.toLowerCase().replaceAll("[^a-zA-Z\\s]", "").split("\\s+");
                for (String word : words) {
                    if (!word.isEmpty()) {
                        wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return;
        }

        // Writing the word frequency to output file
        try (FileWriter writer = new FileWriter(outputFile)) {
            for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
                writer.write(entry.getKey() + " : " + entry.getValue() + "\n");
            }
            System.out.println("Word frequency written to " + outputFile + " successfully!");
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }

        // Also display in console
        System.out.println("\nWord Frequency:");
        for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}
