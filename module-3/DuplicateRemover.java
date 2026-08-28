import java.util.ArrayList;
import java.util.Random;

/*
 Marco Rodriguez
 CSD-420 - Module 3: Generics
 8/27/2026
*/

public class DuplicateRemover {

    public static void main(String[] args) {

        ArrayList<Integer> originalList = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 50; i++) {
            int randomNumber = random.nextInt(20) + 1;
            originalList.add(randomNumber);
        }

        System.out.println("Original List with 50 random values:");
        System.out.println(originalList);

        ArrayList<Integer> noDuplicatesList = removeDuplicates(originalList);

        System.out.println("\nNew List with duplicates removed:");
        System.out.println(noDuplicatesList);
    }

    public static <E> ArrayList<E> removeDuplicates(ArrayList<E> list) {
        ArrayList<E> uniqueList = new ArrayList<>();

        for (E element : list) {
            if (!uniqueList.contains(element)) {
                uniqueList.add(element);
            }
        }

        return uniqueList;
    }
}