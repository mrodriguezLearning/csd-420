import java.util.Arrays;
import java.util.Comparator;

/**
 * Marco Rodriguez
 * CSD420
 * Assignment 6.2
 * 9/12/2026
 */

public class BubbleSort {

    // Method 1: Sorts elements using the Comparable interface
    public static <E extends Comparable<E>> void bubbleSort(E[] list) {
        boolean needNextPass = true;

        for (int i = 1; i < list.length && needNextPass; i++) {
            needNextPass = false;

            for (int j = 0; j < list.length - i; j++) {

                // Compare adjacent elements using the natural ordering
                if (list[j].compareTo(list[j + 1]) > 0) {

                    // Swap elements using a temporary variable
                    E temp = list[j];
                    list[j] = list[j + 1];
                    list[j + 1] = temp;

                    needNextPass = true;
                }
            }
        }
    }

    // Method 2: Sorts elements using the Comparator interface
    public static <E> void bubbleSort(E[] list, Comparator<? super E> comparator) {
        boolean needNextPass = true;

        for (int i = 1; i < list.length && needNextPass; i++) {
            needNextPass = false;

            for (int j = 0; j < list.length - i; j++) {

                // Compare adjacent elements using the provided comparator
                if (comparator.compare(list[j], list[j + 1]) > 0) {

                    // Swap elements using a temporary variable
                    E temp = list[j];
                    list[j] = list[j + 1];
                    list[j + 1] = temp;

                    needNextPass = true;
                }
            }
        }
    }

    public static void main(String[] args) {

        // Test 1: Using Comparable with an Integer array
        Integer[] intArray = { 5, 3, 4, 9, 0, 1, 2, 7, 6, 8 };
        System.out.println("Original Integer array: " + Arrays.toString(intArray));

        bubbleSort(intArray);
        System.out.println("Sorted Integer array (Comparable): " + Arrays.toString(intArray));
        System.out.println();

        // Test 2: Using Comparable with a String array
        String[] stringArray1 = { "Zebra", "Apple", "Mango", "Banana", "Cat" };
        System.out.println("Original String array: " + Arrays.toString(stringArray1));

        bubbleSort(stringArray1);
        System.out.println("Sorted String array (Comparable): " + Arrays.toString(stringArray1));
        System.out.println();

        // Test 3: Using Comparator with a String array (Reverse Alphabetical)
        String[] stringArray2 = { "Zebra", "Apple", "Mango", "Banana", "Cat" };
        System.out.println("Original String array for Comparator: " + Arrays.toString(stringArray2));

        // Creating a custom comparator for reverse alphabetical order
        Comparator<String> reverseAlphabetical = new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s2.compareTo(s1);
            }
        };

        bubbleSort(stringArray2, reverseAlphabetical);
        System.out.println("Sorted String array (Comparator - Reverse): " + Arrays.toString(stringArray2));
    }
}