import java.util.ArrayList;
import java.util.List;

public class ListExercises {

    /** Returns the total sum in a list of integers */
	public static int sum(List<Integer> L) {
        // TODO: Fill in this function.
        int sum = 0;
        for (Integer elem : L) {
            sum += elem;
        }
        return sum;
    }

    /** Returns a list containing the even numbers of the given list */
    public static List<Integer> evens(List<Integer> L) {
        // TODO: Fill in this function.
        ArrayList<Integer> evens = new ArrayList<>();
        for (Integer elem : L) {
            if (elem % 2 == 0) {
                evens.add(elem);
            }
        }
        return evens;
    }

    /** Returns a list containing the common item of the two given lists */
    public static List<Integer> common(List<Integer> L1, List<Integer> L2) {
        // TODO: Fill in this function.
        ArrayList<Integer> commons = new ArrayList<>();
        for (Integer elem : L1) {
            if (L2.contains(elem)) {
                commons.add(elem);
            }
        }
        return commons;
    }


    /** Returns the number of occurrences of the given character in a list of strings. */
    public static int countOccurrencesOfC(List<String> words, char c) {
        // TODO: Fill in this function.
        int sum = 0;
        for (String str : words) {
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == c) {
                    sum++;
                }
            }
        }
        return sum;
    }
}
