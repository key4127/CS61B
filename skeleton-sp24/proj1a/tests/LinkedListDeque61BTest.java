import edu.princeton.cs.algs4.In;
import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

/** Performs some basic linked list tests. */
public class LinkedListDeque61BTest {

     @Test
     /** In this test, we have three different assert statements that verify that addFirst works correctly. */
     public void addFirstTestBasic() {
         Deque61B<String> lld1 = new LinkedListDeque61B<>();

         lld1.addFirst("back"); // after this call we expect: ["back"]
         assertThat(lld1.toList()).containsExactly("back").inOrder();

         lld1.addFirst("middle"); // after this call we expect: ["middle", "back"]
         assertThat(lld1.toList()).containsExactly("middle", "back").inOrder();

         lld1.addFirst("front"); // after this call we expect: ["front", "middle", "back"]
         assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();

         /* Note: The first two assertThat statements aren't really necessary. For example, it's hard
            to imagine a bug in your code that would lead to ["front"] and ["front", "middle"] failing,
            but not ["front", "middle", "back"].
          */
     }

     @Test
     /** In this test, we use only one assertThat statement. IMO this test is just as good as addFirstTestBasic.
      *  In other words, the tedious work of adding the extra assertThat statements isn't worth it. */
     public void addLastTestBasic() {
         Deque61B<String> lld1 = new LinkedListDeque61B<>();

         lld1.addLast("front"); // after this call we expect: ["front"]
         lld1.addLast("middle"); // after this call we expect: ["front", "middle"]
         lld1.addLast("back"); // after this call we expect: ["front", "middle", "back"]
         assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();
     }

     @Test
     /** This test performs interspersed addFirst and addLast calls. */
     public void addFirstAndAddLastTest() {
         Deque61B<Integer> lld1 = new LinkedListDeque61B<>();

         /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */
         lld1.addLast(0);   // [0]
         lld1.addLast(1);   // [0, 1]
         lld1.addFirst(-1); // [-1, 0, 1]
         lld1.addLast(2);   // [-1, 0, 1, 2]
         lld1.addFirst(-2); // [-2, -1, 0, 1, 2]

         assertThat(lld1.toList()).containsExactly(-2, -1, 0, 1, 2).inOrder();
     }

    // Below, you'll write your own tests for LinkedListDeque61B.

    @Test
    public void add_first_from_empty() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();
        assertThat(lld1.toList()).containsExactly();

        lld1.addFirst("add_first_from_empty");
        assertThat(lld1.toList()).containsExactly("add_first_from_empty").inOrder();
    }

    @Test
    public void add_first_nonempty() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();
        lld1.addFirst("sth");
        assertThat(lld1.toList()).containsExactly("sth").inOrder();

        lld1.addFirst("sth_else_1");
        lld1.addFirst("sth_else_2");
        lld1.addFirst("sth_else_3");
        assertThat(lld1.toList()).containsExactly("sth_else_3", "sth_else_2", "sth_else_1", "sth").inOrder();
    }

    @Test
    public void add_last_from_empty() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();
        assertThat(lld1.toList()).containsExactly();

        lld1.addLast("add_first_from_empty");
        assertThat(lld1.toList()).containsExactly("add_first_from_empty").inOrder();
    }

    @Test
    public void add_last_nonempty() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();
        lld1.addLast("sth");
        assertThat(lld1.toList()).containsExactly("sth").inOrder();

        lld1.addLast("sth_else_1");
        lld1.addLast("sth_else_2");
        lld1.addLast("sth_else_3");
        assertThat(lld1.toList()).containsExactly("sth", "sth_else_1", "sth_else_2", "sth_else_3").inOrder();
    }

    @Test
    public void add_first_after_remove_to_empty() {
         Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
         lld1.addLast(1);
         lld1.addLast(2);
         lld1.addLast(3);
         assertThat(lld1.toList()).containsExactly(1, 2, 3).inOrder();

         lld1.removeFirst();
         lld1.removeFirst();
         lld1.removeFirst();
         lld1.addFirst(1);
         assertThat(lld1.toList()).containsExactly(1).inOrder();
    }

    @Test
    public void add_last_after_remove_to_empty() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        lld1.addFirst(1);
        lld1.addFirst(2);
        lld1.addFirst(3);
        assertThat(lld1.toList()).containsExactly(3, 2, 1).inOrder();

        lld1.removeLast();
        lld1.removeLast();
        lld1.removeLast();
        lld1.addLast(1);
        assertThat(lld1.toList()).containsExactly(1).inOrder();
    }

    @Test
    public void remove_first() {
         Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
         lld1.addFirst(1);
         lld1.addLast(2);
         assertThat(lld1.removeFirst()).isEqualTo(1);
         assertThat(lld1.toList()).containsExactly(2).inOrder();
    }

    @Test
    public void remove_last() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        lld1.addFirst(1);
        lld1.addLast(2);
        assertThat(lld1.removeLast()).isEqualTo(2);
        assertThat(lld1.toList()).containsExactly(1).inOrder();
    }

    @Test
    public void remove_first_to_empty() {
         Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
         lld1.addFirst(1);
         lld1.addFirst(2);
         lld1.addFirst(3);
         lld1.removeFirst();
         lld1.removeFirst();
         assertThat(lld1.removeFirst()).isEqualTo(1);
         assertThat(lld1.isEmpty()).isEqualTo(true);
    }

    @Test
    public void remove_last_to_empty() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        lld1.addFirst(1);
        lld1.addFirst(2);
        lld1.addFirst(3);
        lld1.removeLast();
        lld1.removeLast();
        assertThat(lld1.removeLast()).isEqualTo(3);
        assertThat(lld1.isEmpty()).isEqualTo(true);
    }

    @Test
    public void remove_first_to_one() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        lld1.addFirst(1);
        lld1.addFirst(2);
        lld1.addFirst(3);
        lld1.addFirst(4);
        lld1.removeFirst();
        lld1.removeFirst();
        assertThat(lld1.removeFirst()).isEqualTo(2);
    }

    @Test
    public void remove_last_to_one() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        lld1.addFirst(1);
        lld1.addFirst(2);
        lld1.addFirst(3);
        lld1.addFirst(4);
        lld1.removeLast();
        lld1.removeLast();
        assertThat(lld1.removeLast()).isEqualTo(3);
    }

    @Test
    public void get_valid() {
         Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
         lld1.addFirst(1);
         assertThat(lld1.get(0)).isEqualTo(1);

         lld1.removeLast();
         lld1.addLast(2);
         lld1.addLast(3);
         assertThat(lld1.get(0)).isEqualTo(2);
         assertThat(lld1.get(1)).isEqualTo(3);
    }

    @Test
    public void get_oob_large() {
         Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
         lld1.addLast(1);
         lld1.addLast(2);
         assertThat(lld1.get(2)).isEqualTo(null);
         assertThat(lld1.get(100)).isEqualTo(null);
    }

    @Test
    public void get_oob_neg() {
         Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
         lld1.addLast(1);
         lld1.addLast(2);
         assertThat(lld1.get(-1)).isEqualTo(null);
         assertThat(lld1.get(-100)).isEqualTo(null);
    }

    @Test
    public void get_recursive_valid() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        lld1.addFirst(1);
        assertThat(lld1.getRecursive(0)).isEqualTo(1);

        lld1.removeLast();
        lld1.addLast(2);
        lld1.addLast(3);
        assertThat(lld1.getRecursive(0)).isEqualTo(2);
        assertThat(lld1.getRecursive(1)).isEqualTo(3);
    }

    @Test
    public void get_recursive_oob_large() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        lld1.addLast(1);
        lld1.addLast(2);
        assertThat(lld1.get(2)).isEqualTo(null);
        assertThat(lld1.get(100)).isEqualTo(null);
    }

    @Test
    public void get_recursive_oob_neg() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        lld1.addLast(1);
        lld1.addLast(2);
        assertThat(lld1.get(-1)).isEqualTo(null);
        assertThat(lld1.get(-100)).isEqualTo(null);
    }

    @Test
    public void size() {
         Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
         assertThat(lld1.size()).isEqualTo(0);

         lld1.addFirst(1);
         assertThat(lld1.size()).isEqualTo(1);

         lld1.addLast(2);
         assertThat(lld1.size()).isEqualTo(2);

         lld1.addFirst(3);
         assertThat(lld1.size()).isEqualTo(3);
    }

    @Test
    public void size_after_remove_to_empty() {
         Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
         lld1.addLast(1);
         lld1.addLast(2);
         lld1.addLast(3);
         assertThat(lld1.size()).isEqualTo(3);

         lld1.removeFirst();
         lld1.removeFirst();
         assertThat(lld1.size()).isEqualTo(1);

         lld1.removeFirst();
         assertThat(lld1.size()).isEqualTo(0);

         lld1.addLast(1);
         assertThat(lld1.size()).isEqualTo(1);
    }

    @Test
    public void size_after_remove_from_empty() {
         Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
         lld1.removeFirst();
         lld1.removeLast();
         assertThat(lld1.size()).isEqualTo(0);

         lld1.addLast(1);
         lld1.addLast(2);
         assertThat(lld1.size()).isEqualTo(2);
    }

    @Test
    public void is_empty_true() {
         Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
         assertThat(lld1.isEmpty()).isEqualTo(true);

         lld1.addFirst(1);
         lld1.removeFirst();
         assertThat(lld1.isEmpty()).isEqualTo(true);

         lld1.addLast(1);
         lld1.removeLast();
         assertThat(lld1.isEmpty()).isEqualTo(true);

         lld1.addLast(1);
         lld1.addFirst(2);
         lld1.addLast(3);
         lld1.removeLast();
         lld1.removeFirst();
         lld1.removeFirst();
         assertThat(lld1.isEmpty()).isEqualTo(true);
    }

    @Test
    public void is_empty_false() {
         Deque61B<Integer> lld1 = new LinkedListDeque61B<>();

         lld1.addFirst(1);
         assertThat(lld1.isEmpty()).isEqualTo(false);

         lld1.removeFirst();
         lld1.addLast(1);
         assertThat(lld1.isEmpty()).isEqualTo(false);

         lld1.removeLast();
         lld1.addFirst(1);
         lld1.addFirst(2);
         lld1.addFirst(3);
         assertThat(lld1.isEmpty()).isEqualTo(false);
    }

    @Test
    public void to_list_empty() {
         Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
         assertThat(lld1.toList()).containsExactly();

         lld1.addFirst(1);
         lld1.addLast(2);
         lld1.removeFirst();
         lld1.removeLast();
         assertThat(lld1.toList()).containsExactly();
    }

    @Test
    public void to_list_nonempty() {
         Deque61B<Integer> lld1 = new LinkedListDeque61B<>();

         lld1.addFirst(1);
         lld1.addLast(2);
         lld1.addFirst(3);
         assertThat(lld1.toList()).containsExactly(3, 1, 2).inOrder();

         lld1.removeFirst();
         lld1.removeLast();
         lld1.addFirst(0);
         assertThat(lld1.toList()).containsExactly(0, 1).inOrder();
    }
}