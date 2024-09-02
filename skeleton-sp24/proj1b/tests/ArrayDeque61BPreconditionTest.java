import edu.princeton.cs.algs4.In;
import jh61b.utils.Reflection;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDeque61BPreconditionTest {

    @Test
    @DisplayName("ArrayDeque61B has no fields besides backing array and primitives")
    void noNonTrivialFields() {
        List<Field> badFields = Reflection.getFields(ArrayDeque61B.class)
                .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(Object[].class) || f.isSynthetic()))
                .toList();

        assertWithMessage("Found fields that are not array or primitives").that(badFields).isEmpty();
    }

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
}
