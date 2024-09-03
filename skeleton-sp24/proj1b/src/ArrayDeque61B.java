import java.util.ArrayList;
import java.util.List;

public class ArrayDeque61B<T> implements Deque61B<T> {
    private T[] items;
    private int next_first;
    private int next_last;
    private int size;

    // Next_first equals next_last will cause errors.
    public ArrayDeque61B() {
        items = (T[]) new Object[8];
        next_first = 0;
        next_last = 1;
        size = 0;
    }

    @Override
    public void addFirst(T x) {
        items[next_first] = x;
        next_first = Math.floorMod(next_first - 1, items.length);
        size += 1;
    }

    @Override
    public void addLast(T x) {
        items[next_last] = x;
        next_last = Math.floorMod(next_last + 1, items.length);
        size += 1;
    }

    @Override
    public List<T> toList() {
        List<T> return_list = new ArrayList<>();
        for (int i = 1; i <= size; i++) {
            return_list.add(items[Math.floorMod(next_first + i, items.length)]);
        }
        return return_list;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        int remove_index = Math.floorMod(next_first + 1, items.length);
        T x = items[remove_index];
        items[remove_index] = null;
        next_first = remove_index;
        size -= 1;
        return x;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        int remove_index = Math.floorMod(next_last - 1, items.length);
        T x = items[remove_index];
        items[remove_index] = null;
        next_last = remove_index;
        size -= 1;
        return x;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return items[Math.floorMod(next_first + index + 1, items.length)];
    }

    /* This function is not acquired. */
    @Override
    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return  getRecursive(next_first + 1, index);
    }

    // If the AList is real big, these may cause an overflow.
    private T getRecursive(int start, int index) {
        if (index == 0) {
            return items[Math.floorMod(start, items.length)];
        }
        return getRecursive(start + 1, index - 1);
    }
}
