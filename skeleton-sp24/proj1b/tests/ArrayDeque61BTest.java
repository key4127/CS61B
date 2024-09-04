import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDeque61BTest {

//     @Test
//     @DisplayName("ArrayDeque61B has no fields besides backing array and primitives")
//     void noNonTrivialFields() {
//         List<Field> badFields = Reflection.getFields(ArrayDeque61B.class)
//                 .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(Object[].class) || f.isSynthetic()))
//                 .toList();
//
//         assertWithMessage("Found fields that are not array or primitives").that(badFields).isEmpty();
//     }

    @Test
    public void add_fist_from_empty() {
        Deque61B<Integer> AList = new ArrayDeque61B<>();
        assertThat(AList.toList()).containsExactly();

        AList.addFirst(1);
        AList.addFirst(2);
        AList.addFirst(3);
        assertThat(AList.toList()).containsExactly(3, 2, 1).inOrder();
    }

    @Test
    public void add_last_from_empty() {
        Deque61B<Integer> AList = new ArrayDeque61B<>();
        assertThat(AList.toList()).containsExactly();

        AList.addLast(1);
        AList.addLast(2);
        AList.addLast(3);
        assertThat(AList.toList()).containsExactly(1, 2, 3).inOrder();
    }

    @Test
    public void add_first_nonempty() {
        Deque61B<String> AList = new ArrayDeque61B<>();
        AList.addFirst("a");
        assertThat(AList.toList()).containsExactly("a");

        AList.addFirst("b");
        AList.addFirst("c");
        assertThat(AList.toList()).containsExactly("c", "b", "a").inOrder();
    }

    @Test
    public void add_last_nonempty() {
        Deque61B<String> AList = new ArrayDeque61B<>();
        AList.addLast("a");
        assertThat(AList.toList()).containsExactly("a");

        AList.addLast("b");
        AList.addLast("c");
        assertThat(AList.toList()).containsExactly("a", "b", "c").inOrder();
    }

    @Test
    public void add_first_trigger_resize() {
        Deque61B<Integer> AList = new ArrayDeque61B<>();
        for (int i = 1; i <= 9; i++) {
            AList.addFirst(i);
        }
        assertThat(AList.toList()).containsExactly(9, 8, 7, 6, 5, 4, 3, 2, 1).inOrder();
    }

    @Test
    public void add_last_trigger_resize() {
        Deque61B<Integer> AList = new ArrayDeque61B<>();
        for (int i = 1; i <= 9; i++) {
            AList.addLast(i);
        }
        assertThat(AList.toList()).containsExactly(1, 2, 3, 4, 5, 6, 7, 8, 9).inOrder();
    }

    @Test
    public void add_first_and_last() {
        Deque61B<Integer> AList_a = new ArrayDeque61B<>();
        AList_a.addFirst(1);
        AList_a.addLast(2);
        AList_a.addFirst(3);
        assertThat(AList_a.toList()).containsExactly(3, 1, 2).inOrder();

        Deque61B<Integer> AList_b = new ArrayDeque61B<>();
        AList_b.addLast(1);
        AList_b.addFirst(2);
        AList_b.addLast(3);
        assertThat(AList_b.toList()).containsExactly(2, 1, 3).inOrder();
    }

    @Test
    public void add_first_after_remove_to_empty() {
        Deque61B<Integer> AList = new ArrayDeque61B<>();
        AList.addFirst(1);
        AList.addFirst(2);
        AList.addFirst(3);
        assertThat(AList.toList()).containsExactly(3, 2, 1).inOrder();

        AList.removeFirst();
        AList.removeFirst();
        AList.removeFirst();
        assertThat(AList.toList()).containsExactly();

        AList.addFirst(1);
        AList.addFirst(2);
        AList.addFirst(3);
        assertThat(AList.toList()).containsExactly(3, 2, 1).inOrder();
    }

    @Test
    public void add_last_after_remove_to_empty() {
        Deque61B<Integer> AList = new ArrayDeque61B<>();
        AList.addLast(1);
        AList.addLast(2);
        AList.addLast(3);
        assertThat(AList.toList()).containsExactly(1, 2, 3).inOrder();

        AList.removeLast();
        AList.removeLast();
        AList.removeLast();
        assertThat(AList.toList()).containsExactly();

        AList.addLast(1);
        AList.addLast(2);
        AList.addLast(3);
        assertThat(AList.toList()).containsExactly(1, 2, 3).inOrder();
    }

    @Test
    public void remove_first() {
        Deque61B<Integer> AList = new ArrayDeque61B<>();
        AList.addFirst(1);
        AList.addFirst(2);
        AList.addFirst(3);
        assertThat(AList.toList()).containsExactly(3, 2, 1).inOrder();

        AList.removeFirst();
        AList.removeFirst();
        AList.removeFirst();
        assertThat(AList.toList()).containsExactly();

        AList.removeFirst();
        assertThat(AList.toList()).containsExactly();
    }

    @Test
    public void remove_last() {
        Deque61B<Integer> AList = new ArrayDeque61B<>();
        AList.addLast(1);
        AList.addLast(2);
        AList.addLast(3);
        assertThat(AList.toList()).containsExactly(1, 2, 3).inOrder();

        AList.removeLast();
        AList.removeLast();
        AList.removeLast();
        assertThat(AList.toList()).containsExactly();

        AList.removeLast();
        assertThat(AList.toList()).containsExactly();
    }

    @Test
    public void remove_first_to_empty() {
        Deque61B<Integer> AList = new ArrayDeque61B<>();
        AList.addFirst(1);
        AList.addFirst(2);
        AList.addFirst(3);
        assertThat(AList.toList()).containsExactly(3, 2, 1).inOrder();

        AList.removeFirst();
        AList.removeFirst();
        assertThat(AList.toList()).containsExactly(1);
        assertThat(AList.removeFirst()).isEqualTo(1);
    }

    @Test
    public void remove_last_to_empty() {
        Deque61B<Integer> AList = new ArrayDeque61B<>();
        AList.addLast(1);
        AList.addLast(2);
        AList.addLast(3);
        assertThat(AList.toList()).containsExactly(1, 2, 3).inOrder();

        AList.removeLast();
        AList.removeLast();
        assertThat(AList.toList()).containsExactly(1);
        assertThat(AList.removeLast()).isEqualTo(1);
    }

    @Test
    public void remove_first_to_one() {
        Deque61B<Integer> AList = new ArrayDeque61B<>();
        AList.addFirst(1);
        AList.addFirst(2);
        AList.addFirst(3);
        assertThat(AList.toList()).containsExactly(3, 2, 1).inOrder();

        AList.removeFirst();
        assertThat(AList.toList()).containsExactly(2, 1);
        assertThat(AList.removeFirst()).isEqualTo(2);
    }

    @Test
    public void remove_last_to_one() {
        Deque61B<Integer> AList = new ArrayDeque61B<>();
        AList.addLast(1);
        AList.addLast(2);
        AList.addLast(3);
        assertThat(AList.toList()).containsExactly(1, 2, 3).inOrder();

        AList.removeLast();
        assertThat(AList.toList()).containsExactly(1, 2);
        assertThat(AList.removeLast()).isEqualTo(2);
    }

    @Test
    public void remove_first_trigger_resize() {
        Deque61B<Integer> AList = new ArrayDeque61B<>();
        for (int i = 1; i <= 9; i++) {
            AList.addFirst(i);
        }
        for (int i = 1; i <= 5; i++) {
            AList.removeFirst();
        }
        assertThat(AList.toList()).containsExactly(4, 3, 2, 1);
    }

    @Test
    public void remove_last_trigger_resize() {
        Deque61B<Integer> AList = new ArrayDeque61B<>();
        for (int i = 1; i <= 9; i++) {
            AList.addLast(i);
        }
        for (int i = 1; i <= 5; i++) {
            AList.removeLast();
        }
        assertThat(AList.toList()).containsExactly(1, 2, 3, 4).inOrder();
    }

    @Test
    public void remove_first_and_last() {
        Deque61B<Integer> AList = new ArrayDeque61B<>();
        for (int i = 1; i <= 3; i++) {
            AList.addLast(i);
        }
        assertThat(AList.removeFirst()).isEqualTo(1);
        assertThat(AList.removeLast()).isEqualTo(3);
        assertThat(AList.removeFirst()).isEqualTo(2);
        assertThat(AList.removeLast()).isEqualTo(null);

        for (int i = 1; i <= 16; i++) {
            AList.addLast(i);
        }
        for (int i = 1; i <= 6; i++) {
            AList.removeLast();
            AList.removeFirst();
        }
        assertThat(AList.toList()).containsExactly(7, 8, 9, 10).inOrder();
    }

    @Test
    public void size() {
        Deque61B<Integer> AList = new ArrayDeque61B<>();
        assertThat(AList.size()).isEqualTo(0);

        AList.addLast(1);
        assertThat(AList.size()).isEqualTo(1);

        AList.addFirst(2);
        AList.addFirst(3);
        assertThat(AList.size()).isEqualTo(3);
    }

    @Test
    public void size_after_remove_to_empty() {
        Deque61B<String> AList = new ArrayDeque61B<>();
        AList.addFirst("a");
        AList.addLast("b");
        AList.addFirst("c");
        assertThat(AList.size()).isEqualTo(3);

        AList.removeFirst();
        AList.removeLast();
        assertThat(AList.size()).isEqualTo(1);

        AList.removeFirst();
        assertThat(AList.size()).isEqualTo(0);

        AList.addLast("a");
        AList.addFirst("b");
        AList.addLast("c");
        assertThat(AList.size()).isEqualTo(3);
    }

    @Test
    public void get_valid() {
        Deque61B<Integer> AList = new ArrayDeque61B<>();
        AList.addLast(1);
        AList.addLast(2);
        AList.addLast(3);
        assertThat(AList.get(0)).isEqualTo(1);
        assertThat(AList.get(1)).isEqualTo(2);
        assertThat(AList.get(2)).isEqualTo(3);

        AList.addFirst(0);
        assertThat(AList.get(0)).isEqualTo(0);
        assertThat(AList.get(1)).isEqualTo(1);
    }

    @Test
    public void get_oob_large() {
        Deque61B<Integer> AList = new ArrayDeque61B<>();
        assertThat(AList.get(0)).isEqualTo(null);

        AList.addFirst(1);
        assertThat(AList.get(1)).isEqualTo(null);
        assertThat(AList.get(100)).isEqualTo(null);
    }

    @Test
    public void get_oob_neg() {
        Deque61B<Integer> AList = new ArrayDeque61B<>();
        assertThat(AList.get(-1)).isEqualTo(null);

        AList.addFirst(1);
        assertThat(AList.get(-1)).isEqualTo(null);
        assertThat(AList.get(-100)).isEqualTo(null);
    }

    @Test
    public void get_recursive_valid() {
        Deque61B<Integer> AList = new ArrayDeque61B<>();
        AList.addLast(1);
        AList.addLast(2);
        AList.addLast(3);
        assertThat(AList.getRecursive(0)).isEqualTo(1);
        assertThat(AList.getRecursive(1)).isEqualTo(2);
        assertThat(AList.getRecursive(2)).isEqualTo(3);

        AList.addFirst(0);
        assertThat(AList.getRecursive(0)).isEqualTo(0);
        assertThat(AList.getRecursive(1)).isEqualTo(1);
    }

    @Test
    public void get_recursive_oob_large() {
        Deque61B<Integer> AList = new ArrayDeque61B<>();
        assertThat(AList.getRecursive(0)).isEqualTo(null);

        AList.addFirst(1);
        assertThat(AList.getRecursive(1)).isEqualTo(null);
        assertThat(AList.getRecursive(100)).isEqualTo(null);
    }

    @Test
    public void get_recursive_oob_neg() {
        Deque61B<Integer> AList = new ArrayDeque61B<>();
        assertThat(AList.getRecursive(-1)).isEqualTo(null);

        AList.addFirst(1);
        assertThat(AList.getRecursive(-1)).isEqualTo(null);
        assertThat(AList.getRecursive(-100)).isEqualTo(null);
    }

    @Test
    public void size_after_remove_from_empty() {
        Deque61B<Integer> AList = new ArrayDeque61B<>();
        AList.removeFirst();
        AList.removeLast();
        assertThat(AList.size()).isEqualTo(0);

        AList.addLast(1);
        assertThat(AList.size()).isEqualTo(1);

        AList.removeFirst();
        AList.removeLast();
        assertThat(AList.size()).isEqualTo(0);
    }

    @Test
    public void is_empty_true() {
        Deque61B<Integer> AList = new ArrayDeque61B<>();
        assertThat(AList.isEmpty()).isEqualTo(true);
    }

    @Test
    public void is_empty_false() {
        Deque61B<String> AList = new ArrayDeque61B<>();
        AList.addFirst("a");
        AList.addLast("b");
        AList.addFirst("c");
        assertThat(AList.isEmpty()).isEqualTo(false);
    }

    @Test
    public void is_empty_after_remove_to_empty() {
        Deque61B<Integer> AList = new ArrayDeque61B<>();
        assertThat(AList.isEmpty()).isEqualTo(true);

        AList.addFirst(1);
        AList.addLast(2);
        assertThat(AList.isEmpty()).isEqualTo(false);

        AList.removeLast();
        AList.removeFirst();
        assertThat(AList.isEmpty()).isEqualTo(true);
    }

    @Test
    public void to_list_empty() {
        Deque61B<Integer> AList = new ArrayDeque61B<>();
        assertThat(AList.toList()).containsExactly();
    }

    @Test
    public void to_list_nonempty() {
        Deque61B<String> AList = new ArrayDeque61B<>();
        AList.addFirst("a");
        AList.addFirst("b");
        AList.addLast("c");
        assertThat(AList.toList()).containsExactly("b", "a", "c").inOrder();
    }

    @Test
    public void resize_up_and_resize_down() {
        Deque61B<Integer> AList = new ArrayDeque61B<>();
        List<Integer> test_list = new ArrayList<>();
        for (int i = 1; i <= 16; i++) {
            AList.addLast(i);
            test_list.add(i);
        }
        assertThat(AList.toList()).isEqualTo(test_list);

        for (int i = 1; i <= 12; i++) {
            AList.removeLast();
            test_list.removeLast();
        }
        assertThat(AList.toList()).isEqualTo(test_list);
    }

    @Test
    public void get_and_size_and_is_empty_after_resizing() {
        Deque61B<Integer> AList = new ArrayDeque61B<>();
        for (int i = 1; i <= 16; i++) {
            AList.addLast(i);
        }
        get_size_empty_helper(AList, 16);

        for (int i = 1; i <= 12; i++) {
            AList.removeLast();
        }
        get_size_empty_helper(AList, 4);

        for (int i = 1; i <= 4; i++) {
            AList.removeFirst();
        }
        assertThat(AList.get(0)).isEqualTo(null);
        assertThat(AList.get(1)).isEqualTo(null);
        assertThat(AList.isEmpty()).isEqualTo(true);
        assertThat(AList.size()).isEqualTo(0);

        /*
        assertThat(AList.getRecursive(0)).isEqualTo(null);
        assertThat(AList.getRecursive(1)).isEqualTo(null);
         */
    }

    private void get_size_empty_helper(Deque61B<Integer> AList, int size) {
        assertThat(AList.get(size - 1)).isEqualTo(size);
        assertThat(AList.get(size)).isEqualTo(null);
        assertThat(AList.isEmpty()).isEqualTo(false);
        assertThat(AList.size()).isEqualTo(size);

        /*
        assertThat(AList.getRecursive(size - 1)).isEqualTo(size);
        assertThat(AList.getRecursive(size)).isEqualTo(null);
         */
    }
}
