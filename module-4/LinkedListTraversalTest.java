import java.util.LinkedList;
import java.util.Iterator;

/*
 Marco Rodriguez
 CSD-420 - Module 4: Generics & Lists, Stacks, and Queues
 8/27/2026
*/

public class LinkedListTraversalTest {

    public static void main(String[] args) {
        /* Run Test 1 with 50,000 integers */
        System.out.println("Test 1: 50,000 Integers");
        runTest(50000);

        /* Run Test 2 with 500,000 integers */
        System.out.println("\nTest 2: 500,000 Integers");
        runTest(500000);

    }

    /**
     * Populates a LinkedList with a specified number of integers and compares
     * the time taken to traverse it using an Iterator versus a for loop with
     * get(index).
     * 
     * size The number of elements to add to the LinkedList for testing.
     */
    public static void runTest(int size) {
        LinkedList<Integer> list = new LinkedList<>();

        // Populate the LinkedList with integers from 0 to size-1
        for (int i = 0; i < size; i++) {
            list.add(i);
        }

        // Test 1: Measure traversal time using an Iterator
        long startTime = System.currentTimeMillis();
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            iterator.next();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Iterator traversal time: " + (endTime - startTime) + " milliseconds");

        // Test 2: Measure traversal time using the get(index) method
        long startTimeGet = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            list.get(i);
        }
        long endTimeGet = System.currentTimeMillis();
        System.out.println("get(index) traversal time: " + (endTimeGet - startTimeGet) + " milliseconds");
    }
}