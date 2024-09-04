package deque;

import net.sf.saxon.expr.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class MaxArrayDeque61B<T> implements Iterable<T>{
    private T[] items;
    private int next_first;
    private int next_last;
    private int size;
    Comparator<T> comparator;

    private class MaxArrayIterator implements Iterator<T>{
        int num;
        int index;

        public MaxArrayIterator() {
            num = 0;
            index = Math.floorMod(next_first + 1, items.length);
        }

        public boolean hasNext() {
            return num < size;
        }

        public T next() {
            T x = items[index];
            index = Math.floorMod(index + 1, items.length);
            num += 1;
            return x;
        }
    }

    private class DefaultComparator implements Comparator<T> {
        @Override
        public int compare(T o1, T o2) {
            return o1.toString().length() - o2.toString().length();
        }
    }

    public MaxArrayDeque61B() {
        items = (T[]) new Object[8];
        next_first = 0;
        next_last = 1;
        size = 0;
        comparator = new DefaultComparator();
    }

    public MaxArrayDeque61B(Comparator<T> c) {
        this();
        comparator = c;
    }

    public Iterator<T> iterator() {
        return new MaxArrayIterator();
    }

    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            a[i] = get(i);
        }
        items = a;
        next_first = items.length - 1;
        next_last = size;
    }

    public void addFirst(T x) {
        if (size == items.length) {
            resize(items.length * 2);
        }
        items[next_first] = x;
        next_first = Math.floorMod(next_first - 1, items.length);
        size += 1;
    }

    public void addLast(T x) {
        if (size == items.length) {
            resize(items.length * 2);
        }
        items[next_last] = x;
        next_last = Math.floorMod(next_last + 1, items.length);
        size += 1;
    }

    public List<T> toList() {
        List<T> return_list = new ArrayList<>();
        for (T elem : this) {
            return_list.add(elem);
        }
        return return_list;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }

        int remove_index = Math.floorMod(next_first + 1, items.length);
        T x = items[remove_index];
        items[remove_index] = null;
        next_first = remove_index;
        size -= 1;

        if (items.length >= 8 && size <= items.length * 0.25) {
            resize(items.length / 2);
        }
        return x;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }

        int remove_index = Math.floorMod(next_last - 1, items.length);
        T x = items[remove_index];
        items[remove_index] = null;
        next_last = remove_index;
        size -= 1;

        if (items.length >= 8 && size <= items.length * 0.25) {
            resize(items.length / 2);
        }
        return x;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return items[Math.floorMod(next_first + index + 1, items.length)];
    }

    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return getRecursive(next_first + 1, index);
    }

    private T getRecursive(int start, int index) {
        if (index == 0) {
            return items[Math.floorMod(start, items.length)];
        }
        return getRecursive(start + 1, index - 1);
    }

    public T max() {
        if (isEmpty()) {
            return null;
        }
        T max = get(0);
        for (T elem : this) {
            if (comparator.compare(max, elem) < 0) {
                max = elem;
            }
        }
        return max;
    }

    public T max(Comparator<T> c) {
        Comparator<T> original_comparator = comparator;
        comparator = c;
        T max = max();
        comparator = original_comparator;
        return max;
    }
}
