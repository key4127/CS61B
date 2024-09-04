import deque.ArrayDeque61B;
import deque.Deque61B;
import org.junit.jupiter.api.Test;

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
    public void iterator() {
        Deque61B<Integer> AList = new ArrayDeque61B<>();
        for (int i = 1; i <= 10; i++)
            AList.addLast(i);
        int index = 0;
        for (Object elem : AList) {
            assertThat(elem).isEqualTo(AList.get(index));
            index++;
        }
        for (int i = 1; i <= 8; i++) {
            AList.removeFirst();
        }
        index = 0;
        for (Object elem : AList) {
            assertThat(elem).isEqualTo(AList.get(index));
            index++;
        }
    }

    @Test
    public void equal() {
        Deque61B<Integer> AList_int_a = new ArrayDeque61B<>();
        Deque61B<Integer> AList_int_b = new ArrayDeque61B<>();
        Deque61B<String> AList_string = new ArrayDeque61B<>();
        assertThat(AList_int_a.equals(AList_int_a)).isEqualTo(true);
        assertThat(AList_int_a.equals(AList_int_b)).isEqualTo(true);

        for (int i = 1; i <= 10; i++) {
            AList_int_a.addLast(i);
            AList_int_b.addLast(i);
            AList_string.addLast(String.valueOf(i));
        }
        assertThat(AList_int_a.equals(AList_int_b)).isEqualTo(true);
        assertThat(AList_int_a.equals(AList_string)).isEqualTo(false);

        AList_int_b.removeLast();
        assertThat(AList_int_a.equals(AList_int_b)).isEqualTo(false);

        AList_int_b.addLast(11);
        assertThat(AList_int_a.equals(AList_int_b)).isEqualTo(false);

        for (int i = 1; i <= 10; i++) {
            AList_int_a.removeLast();
            AList_int_b.removeFirst();
        }
        assertThat(AList_int_a.equals(AList_int_b)).isEqualTo(true);

        for (int i = 1; i <= 10; i++) {
            AList_int_a.addLast(i);
            AList_int_b.addLast(i);
            assertThat(AList_int_a.equals(AList_int_b)).isEqualTo(true);
        }
    }

    @Test
    public void to_string() {
        Deque61B<Integer> AList_int = new ArrayDeque61B<>();
        assertThat(AList_int.toString()).isEqualTo("[]");

        for (int i = 1; i <= 10; i++) {
            AList_int.addLast(i);
        }
        assertThat(AList_int.toString()).isEqualTo("[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]");

        for (int i = 1; i <= 8; i++) {
            AList_int.removeFirst();
        }
        assertThat(AList_int.toString()).isEqualTo("[9, 10]");

        for (int i = 8; i >= 7; i--) {
            AList_int.addFirst(i);
        }
        assertThat(AList_int.toString()).isEqualTo("[7, 8, 9, 10]");

        Deque61B<String> AList_string = new ArrayDeque61B<>();
        AList_string.addLast("a");
        AList_string.addLast("b");
        AList_string.addLast("c");
        assertThat(AList_string.toString()).isEqualTo("[a, b, c]");
    }
}
