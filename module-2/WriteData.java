import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

/**
 * CSD 420
 * Module 2 Assignment 2.2
 * Marco Rodriguez
 * The WriteData class generates random integers and doubles and writes them
 * to a binary data file in append mode.
 */
public class WriteData {
    /**
     * The main method executes the file writing process. It generates 5 random
     * integers and 5 random doubles, and appends them to the specified data file
     */
    public static void main(String[] args) {
        int[] randomInts = new int[5];
        double[] randomDoubles = new double[5];
        Random random = new Random();

        for (int i = 0; i < 5; i++) {
            randomInts[i] = random.nextInt(100);
            randomDoubles[i] = random.nextDouble() * 100.0;
        }

        try (DataOutputStream output = new DataOutputStream(new FileOutputStream("Marco_datafile.dat", true))) {

            for (int i = 0; i < 5; i++) {
                output.writeInt(randomInts[i]);
            }

            for (int i = 0; i < 5; i++) {
                output.writeDouble(randomDoubles[i]);
            }

            System.out.println("Data successfully written to Marco_datafile.dat");

        } catch (IOException e) {
            System.out.println("An error occurred during file writing: " + e.getMessage());
        }
    }
}