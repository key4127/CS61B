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
        assertThat(AList.toList()).containsExactly();
    }
}
