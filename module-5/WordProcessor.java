import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class WordProcessor {

    public static void main(String[] args) {
        String fileName = "collection_of_words.txt";

        // TreeSet automatically handles removing duplicates and sorting in ascending
        // order
        TreeSet<String> uniqueWords = new TreeSet<>();

        // Read words from the file
        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNext()) {
                String word = scanner.next();
                if (!word.isEmpty()) {
                    uniqueWords.add(word);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("The file " + fileName + " was not found.");
            return;
        }

        // Display non-duplicate words in ascending order
        System.out.println("Words in ascending order:");
        for (String word : uniqueWords) {
            System.out.println(word);
        }

        System.out.println(); // Blank line for readability

        // Display non-duplicate words in descending order
        System.out.println("Words in descending order:");
        Set<String> descendingWords = uniqueWords.descendingSet();
        for (String word : descendingWords) {
            System.out.println(word);
        }
    }

}