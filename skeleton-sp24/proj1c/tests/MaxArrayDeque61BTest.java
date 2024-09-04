import edu.princeton.cs.algs4.In;
import net.sf.saxon.expr.Component;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import deque.MaxArrayDeque61B;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class MaxArrayDeque61BTest {
    private static class StringLengthComparator implements Comparator<String> {
        public int compare(String a, String b) {
            return a.length() - b.length();
        }
    }

    @Test
    public void basicTest() {
        MaxArrayDeque61B<String> mad = new MaxArrayDeque61B<>(new StringLengthComparator());
        mad.addFirst("");
        mad.addFirst("2");
        mad.addFirst("fury road");
        assertThat(mad.max()).isEqualTo("fury road");
    }

    // these are basic tests to test the basic methods, namely methods about max and comparators.
    @Test
    public void add_fist_from_empty() {
        MaxArrayDeque61B<Integer> mad = new MaxArrayDeque61B<>();
        assertThat(mad.toList()).containsExactly();

        mad.addFirst(1);
        mad.addFirst(2);
        mad.addFirst(3);
        assertThat(mad.toList()).containsExactly(3, 2, 1).inOrder();
    }

    @Test
    public void add_last_from_empty() {
        MaxArrayDeque61B<Integer> mad = new MaxArrayDeque61B<>();
        assertThat(mad.toList()).containsExactly();

        mad.addLast(1);
        mad.addLast(2);
        mad.addLast(3);
        assertThat(mad.toList()).containsExactly(1, 2, 3).inOrder();
    }

    @Test
    public void add_first_nonempty() {
        MaxArrayDeque61B<String> mad = new MaxArrayDeque61B<>();
        mad.addFirst("a");
        assertThat(mad.toList()).containsExactly("a");

        mad.addFirst("b");
        mad.addFirst("c");
        assertThat(mad.toList()).containsExactly("c", "b", "a").inOrder();
    }

    @Test
    public void add_last_nonempty() {
        MaxArrayDeque61B<String> mad = new MaxArrayDeque61B<>();
        mad.addLast("a");
        assertThat(mad.toList()).containsExactly("a");

        mad.addLast("b");
        mad.addLast("c");
        assertThat(mad.toList()).containsExactly("a", "b", "c").inOrder();
    }

    @Test
    public void add_first_trigger_resize() {
        MaxArrayDeque61B<Integer> mad = new MaxArrayDeque61B<>();
        for (int i = 1; i <= 9; i++) {
            mad.addFirst(i);
        }
        assertThat(mad.toList()).containsExactly(9, 8, 7, 6, 5, 4, 3, 2, 1).inOrder();
    }

    @Test
    public void add_last_trigger_resize() {
        MaxArrayDeque61B<Integer> mad = new MaxArrayDeque61B<>();
        for (int i = 1; i <= 9; i++) {
            mad.addLast(i);
        }
        assertThat(mad.toList()).containsExactly(1, 2, 3, 4, 5, 6, 7, 8, 9).inOrder();
    }

    @Test
    public void add_first_and_last() {
        MaxArrayDeque61B<Integer> mad_a = new MaxArrayDeque61B<>();
        mad_a.addFirst(1);
        mad_a.addLast(2);
        mad_a.addFirst(3);
        assertThat(mad_a.toList()).containsExactly(3, 1, 2).inOrder();

        MaxArrayDeque61B<Integer> mad_b = new MaxArrayDeque61B<>();
        mad_b.addLast(1);
        mad_b.addFirst(2);
        mad_b.addLast(3);
        assertThat(mad_b.toList()).containsExactly(2, 1, 3).inOrder();
    }

    @Test
    public void add_first_after_remove_to_empty() {
        MaxArrayDeque61B<Integer> mad = new MaxArrayDeque61B<>();
        mad.addFirst(1);
        mad.addFirst(2);
        mad.addFirst(3);
        assertThat(mad.toList()).containsExactly(3, 2, 1).inOrder();

        mad.removeFirst();
        mad.removeFirst();
        mad.removeFirst();
        assertThat(mad.toList()).containsExactly();

        mad.addFirst(1);
        mad.addFirst(2);
        mad.addFirst(3);
        assertThat(mad.toList()).containsExactly(3, 2, 1).inOrder();
    }

    @Test
    public void add_last_after_remove_to_empty() {
        MaxArrayDeque61B<Integer> mad = new MaxArrayDeque61B<>();
        mad.addLast(1);
        mad.addLast(2);
        mad.addLast(3);
        assertThat(mad.toList()).containsExactly(1, 2, 3).inOrder();

        mad.removeLast();
        mad.removeLast();
        mad.removeLast();
        assertThat(mad.toList()).containsExactly();

        mad.addLast(1);
        mad.addLast(2);
        mad.addLast(3);
        assertThat(mad.toList()).containsExactly(1, 2, 3).inOrder();
    }

    @Test
    public void remove_first() {
        MaxArrayDeque61B<Integer> mad = new MaxArrayDeque61B<>();
        mad.addFirst(1);
        mad.addFirst(2);
        mad.addFirst(3);
        assertThat(mad.toList()).containsExactly(3, 2, 1).inOrder();

        mad.removeFirst();
        mad.removeFirst();
        mad.removeFirst();
        assertThat(mad.toList()).containsExactly();

        mad.removeFirst();
        assertThat(mad.toList()).containsExactly();
    }

    @Test
    public void remove_last() {
        MaxArrayDeque61B<Integer> mad = new MaxArrayDeque61B<>();
        mad.addLast(1);
        mad.addLast(2);
        mad.addLast(3);
        assertThat(mad.toList()).containsExactly(1, 2, 3).inOrder();

        mad.removeLast();
        mad.removeLast();
        mad.removeLast();
        assertThat(mad.toList()).containsExactly();

        mad.removeLast();
        assertThat(mad.toList()).containsExactly();
    }

    @Test
    public void remove_first_to_empty() {
        MaxArrayDeque61B<Integer> mad = new MaxArrayDeque61B<>();
        mad.addFirst(1);
        mad.addFirst(2);
        mad.addFirst(3);
        assertThat(mad.toList()).containsExactly(3, 2, 1).inOrder();

        mad.removeFirst();
        mad.removeFirst();
        assertThat(mad.toList()).containsExactly(1);
        assertThat(mad.removeFirst()).isEqualTo(1);
    }

    @Test
    public void remove_last_to_empty() {
        MaxArrayDeque61B<Integer> mad = new MaxArrayDeque61B<>();
        mad.addLast(1);
        mad.addLast(2);
        mad.addLast(3);
        assertThat(mad.toList()).containsExactly(1, 2, 3).inOrder();

        mad.removeLast();
        mad.removeLast();
        assertThat(mad.toList()).containsExactly(1);
        assertThat(mad.removeLast()).isEqualTo(1);
    }

    @Test
    public void remove_first_to_one() {
        MaxArrayDeque61B<Integer> mad = new MaxArrayDeque61B<>();
        mad.addFirst(1);
        mad.addFirst(2);
        mad.addFirst(3);
        assertThat(mad.toList()).containsExactly(3, 2, 1).inOrder();

        mad.removeFirst();
        assertThat(mad.toList()).containsExactly(2, 1);
        assertThat(mad.removeFirst()).isEqualTo(2);
    }

    @Test
    public void remove_last_to_one() {
        MaxArrayDeque61B<Integer> mad = new MaxArrayDeque61B<>();
        mad.addLast(1);
        mad.addLast(2);
        mad.addLast(3);
        assertThat(mad.toList()).containsExactly(1, 2, 3).inOrder();

        mad.removeLast();
        assertThat(mad.toList()).containsExactly(1, 2);
        assertThat(mad.removeLast()).isEqualTo(2);
    }

    @Test
    public void remove_first_trigger_resize() {
        MaxArrayDeque61B<Integer> mad = new MaxArrayDeque61B<>();
        for (int i = 1; i <= 9; i++) {
            mad.addFirst(i);
        }
        for (int i = 1; i <= 5; i++) {
            mad.removeFirst();
        }
        assertThat(mad.toList()).containsExactly(4, 3, 2, 1);
    }

    @Test
    public void remove_last_trigger_resize() {
        MaxArrayDeque61B<Integer> mad = new MaxArrayDeque61B<>();
        for (int i = 1; i <= 9; i++) {
            mad.addLast(i);
        }
        for (int i = 1; i <= 5; i++) {
            mad.removeLast();
        }
        assertThat(mad.toList()).containsExactly(1, 2, 3, 4).inOrder();
    }

    @Test
    public void remove_first_and_last() {
        MaxArrayDeque61B<Integer> mad = new MaxArrayDeque61B<>();
        for (int i = 1; i <= 3; i++) {
            mad.addLast(i);
        }
        assertThat(mad.removeFirst()).isEqualTo(1);
        assertThat(mad.removeLast()).isEqualTo(3);
        assertThat(mad.removeFirst()).isEqualTo(2);
        assertThat(mad.removeLast()).isEqualTo(null);

        for (int i = 1; i <= 16; i++) {
            mad.addLast(i);
        }
        for (int i = 1; i <= 6; i++) {
            mad.removeLast();
            mad.removeFirst();
        }
        assertThat(mad.toList()).containsExactly(7, 8, 9, 10).inOrder();
    }

    @Test
    public void size() {
        MaxArrayDeque61B<Integer> mad = new MaxArrayDeque61B<>();
        assertThat(mad.size()).isEqualTo(0);

        mad.addLast(1);
        assertThat(mad.size()).isEqualTo(1);

        mad.addFirst(2);
        mad.addFirst(3);
        assertThat(mad.size()).isEqualTo(3);
    }

    @Test
    public void size_after_remove_to_empty() {
        MaxArrayDeque61B<String> mad = new MaxArrayDeque61B<>();
        mad.addFirst("a");
        mad.addLast("b");
        mad.addFirst("c");
        assertThat(mad.size()).isEqualTo(3);

        mad.removeFirst();
        mad.removeLast();
        assertThat(mad.size()).isEqualTo(1);

        mad.removeFirst();
        assertThat(mad.size()).isEqualTo(0);

        mad.addLast("a");
        mad.addFirst("b");
        mad.addLast("c");
        assertThat(mad.size()).isEqualTo(3);
    }

    @Test
    public void get_valid() {
        MaxArrayDeque61B<Integer> mad = new MaxArrayDeque61B<>();
        mad.addLast(1);
        mad.addLast(2);
        mad.addLast(3);
        assertThat(mad.get(0)).isEqualTo(1);
        assertThat(mad.get(1)).isEqualTo(2);
        assertThat(mad.get(2)).isEqualTo(3);

        mad.addFirst(0);
        assertThat(mad.get(0)).isEqualTo(0);
        assertThat(mad.get(1)).isEqualTo(1);
    }

    @Test
    public void get_oob_large() {
        MaxArrayDeque61B<Integer> mad = new MaxArrayDeque61B<>();
        assertThat(mad.get(0)).isEqualTo(null);

        mad.addFirst(1);
        assertThat(mad.get(1)).isEqualTo(null);
        assertThat(mad.get(100)).isEqualTo(null);
    }

    @Test
    public void get_oob_neg() {
        MaxArrayDeque61B<Integer> mad = new MaxArrayDeque61B<>();
        assertThat(mad.get(-1)).isEqualTo(null);

        mad.addFirst(1);
        assertThat(mad.get(-1)).isEqualTo(null);
        assertThat(mad.get(-100)).isEqualTo(null);
    }

    @Test
    public void get_recursive_valid() {
        MaxArrayDeque61B<Integer> mad = new MaxArrayDeque61B<>();
        mad.addLast(1);
        mad.addLast(2);
        mad.addLast(3);
        assertThat(mad.getRecursive(0)).isEqualTo(1);
        assertThat(mad.getRecursive(1)).isEqualTo(2);
        assertThat(mad.getRecursive(2)).isEqualTo(3);

        mad.addFirst(0);
        assertThat(mad.getRecursive(0)).isEqualTo(0);
        assertThat(mad.getRecursive(1)).isEqualTo(1);
    }

    @Test
    public void get_recursive_oob_large() {
        MaxArrayDeque61B<Integer> mad = new MaxArrayDeque61B<>();
        assertThat(mad.getRecursive(0)).isEqualTo(null);

        mad.addFirst(1);
        assertThat(mad.getRecursive(1)).isEqualTo(null);
        assertThat(mad.getRecursive(100)).isEqualTo(null);
    }

    @Test
    public void get_recursive_oob_neg() {
        MaxArrayDeque61B<Integer> mad = new MaxArrayDeque61B<>();
        assertThat(mad.getRecursive(-1)).isEqualTo(null);

        mad.addFirst(1);
        assertThat(mad.getRecursive(-1)).isEqualTo(null);
        assertThat(mad.getRecursive(-100)).isEqualTo(null);
    }

    @Test
    public void size_after_remove_from_empty() {
        MaxArrayDeque61B<Integer> mad = new MaxArrayDeque61B<>();
        mad.removeFirst();
        mad.removeLast();
        assertThat(mad.size()).isEqualTo(0);

        mad.addLast(1);
        assertThat(mad.size()).isEqualTo(1);

        mad.removeFirst();
        mad.removeLast();
        assertThat(mad.size()).isEqualTo(0);
    }

    @Test
    public void is_empty_true() {
        MaxArrayDeque61B<Integer> mad = new MaxArrayDeque61B<>();
        assertThat(mad.isEmpty()).isEqualTo(true);
    }

    @Test
    public void is_empty_false() {
        MaxArrayDeque61B<String> mad = new MaxArrayDeque61B<>();
        mad.addFirst("a");
        mad.addLast("b");
        mad.addFirst("c");
        assertThat(mad.isEmpty()).isEqualTo(false);
    }

    @Test
    public void is_empty_after_remove_to_empty() {
        MaxArrayDeque61B<Integer> mad = new MaxArrayDeque61B<>();
        assertThat(mad.isEmpty()).isEqualTo(true);

        mad.addFirst(1);
        mad.addLast(2);
        assertThat(mad.isEmpty()).isEqualTo(false);

        mad.removeLast();
        mad.removeFirst();
        assertThat(mad.isEmpty()).isEqualTo(true);
    }

    @Test
    public void to_list_empty() {
        MaxArrayDeque61B<Integer> mad = new MaxArrayDeque61B<>();
        assertThat(mad.toList()).containsExactly();
    }

    @Test
    public void to_list_nonempty() {
        MaxArrayDeque61B<String> mad = new MaxArrayDeque61B<>();
        mad.addFirst("a");
        mad.addFirst("b");
        mad.addLast("c");
        assertThat(mad.toList()).containsExactly("b", "a", "c").inOrder();
    }

    @Test
    public void resize_up_and_resize_down() {
        MaxArrayDeque61B<Integer> mad = new MaxArrayDeque61B<>();
        List<Integer> test_list = new ArrayList<>();
        for (int i = 1; i <= 16; i++) {
            mad.addLast(i);
            test_list.add(i);
        }
        assertThat(mad.toList()).isEqualTo(test_list);

        for (int i = 1; i <= 12; i++) {
            mad.removeLast();
            test_list.removeLast();
        }
        assertThat(mad.toList()).isEqualTo(test_list);
    }

    @Test
    public void get_and_size_and_is_empty_after_resizing() {
        MaxArrayDeque61B<Integer> mad = new MaxArrayDeque61B<>();
        for (int i = 1; i <= 16; i++) {
            mad.addLast(i);
        }
        get_size_empty_helper(mad, 16);

        for (int i = 1; i <= 12; i++) {
            mad.removeLast();
        }
        get_size_empty_helper(mad, 4);

        for (int i = 1; i <= 4; i++) {
            mad.removeFirst();
        }
        assertThat(mad.get(0)).isEqualTo(null);
        assertThat(mad.get(1)).isEqualTo(null);
        assertThat(mad.isEmpty()).isEqualTo(true);
        assertThat(mad.size()).isEqualTo(0);

        /*
        assertThat(mad.getRecursive(0)).isEqualTo(null);
        assertThat(mad.getRecursive(1)).isEqualTo(null);
        */
    }

    private void get_size_empty_helper(MaxArrayDeque61B<Integer> mad, int size) {
        assertThat(mad.get(size - 1)).isEqualTo(size);
        assertThat(mad.get(size)).isEqualTo(null);
        assertThat(mad.isEmpty()).isEqualTo(false);
        assertThat(mad.size()).isEqualTo(size);

        /*
        assertThat(mad.getRecursive(size - 1)).isEqualTo(size);
        assertThat(mad.getRecursive(size)).isEqualTo(null);
        */
    }

    // These are tests related to comparators.
    @Test
    public void default_comparator() {
        MaxArrayDeque61B<String> mad = new MaxArrayDeque61B<>();
        assertThat(mad.max()).isEqualTo(null);

        mad.addLast("a");
        mad.addLast("ab");
        mad.addLast("abc");
        assertThat(mad.max()).isEqualTo("abc");

        mad.removeLast();
        assertThat(mad.max()).isEqualTo("ab");

        mad.addFirst("abc");
        assertThat(mad.max()).isEqualTo("abc");
    }

    @Test
    public void natural_order_comparator() {
        MaxArrayDeque61B<Integer> mad = new MaxArrayDeque61B<Integer>(Comparator.naturalOrder());
        assertThat(mad.max()).isEqualTo(null);

        for (int i = 1; i <= 5; i++) {
            mad.addFirst(2 * i - 1);
            mad.addLast(2 * i);
        }
        assertThat(mad.max()).isEqualTo(10);

        for (int i = 1; i <= 5; i++) {
            mad.removeFirst();
            mad.removeLast();
        }
        assertThat(mad.max()).isEqualTo(null);
    }

    @Test
    public void change_comparator() {
        MaxArrayDeque61B<Integer> mad = new MaxArrayDeque61B<>();
        mad.addLast(1);
        mad.addLast(2);
        assertThat(mad.max()).isEqualTo(1);
        assertThat(mad.max(Comparator.naturalOrder())).isEqualTo(2);
        assertThat(mad.max()).isEqualTo(1);
    }
}
