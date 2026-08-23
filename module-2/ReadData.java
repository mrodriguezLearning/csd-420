import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.EOFException;
import java.io.IOException;

/**
 * CSD 420
 * Module 2 Assisgnment 2.2
 * Marco Rodriguez
 * The ReadData class reads sets of integers and doubles from a binary data file
 * and prints them to the console.
 */
public class ReadData {
    /**
     * The main method executes the file reading process. It opens the specified
     * data file and sequentially reads sets of 5 integers and 5 doubles until
     * the end of the file is reached
     */
    public static void main(String[] args) {
        try (DataInputStream input = new DataInputStream(new FileInputStream("Marco_datafile.dat"))) {
            int setNumber = 1;

            while (true) {
                try {
                    System.out.println("Data Set " + setNumber + ":");

                    System.out.print("Integers: ");
                    for (int i = 0; i < 5; i++) {
                        System.out.print(input.readInt() + " ");
                    }
                    System.out.println();

                    System.out.print("Doubles: ");
                    for (int i = 0; i < 5; i++) {
                        System.out.printf("%.2f ", input.readDouble());
                    }
                    System.out.println("\n");

                    setNumber++;
                } catch (EOFException eof) {
                    break;
                }
            }

            System.out.println("End of file reached.");

        } catch (IOException e) {
            System.out.println("An error occurred during file reading: " + e.getMessage());
        }
    }
}