import edu.princeton.cs.algs4.In;

import java.util.*;

public class MapExercises {
    /** Returns a map from every lower case letter to the number corresponding to that letter, where 'a' is
     * 1, 'b' is 2, 'c' is 3, ..., 'z' is 26.
     */
    public static Map<Character, Integer> letterToNum() {
        // TODO: Fill in this function.
        Map<Character, Integer> letterMap = new HashMap<>();
        char c = 'a';
        int num = 1;
        for (int i = 1; i <= 26; i++) {
            letterMap.put(c, num);
            c++;
            num++;
        }
        return letterMap;
    }

    /** Returns a map from the integers in the list to their squares. For example, if the input list
     *  is [1, 3, 6, 7], the returned map goes from 1 to 1, 3 to 9, 6 to 36, and 7 to 49.
     */
    public static Map<Integer, Integer> squares(List<Integer> nums) {
        // TODO: Fill in this function.
        Map<Integer, Integer> squares = new HashMap<>();
        for (int elem : nums) {
            squares.put(elem, elem * elem);
        }
        return squares;
    }

    /** Returns a map of the counts of all words that appear in a list of words. */
    public static Map<String, Integer> countWords(List<String> words) {
        // TODO: Fill in this function.
        Map<String, Integer> countWords = new HashMap<>();
        for (String str : words) {
            if (countWords.containsKey(str)) {
                countWords.put(str, countWords.get(str) + 1);
            } else {
                countWords.put(str, 1);
            }
        }
        return countWords;
    }
}
