package deque;

import net.sf.saxon.expr.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MaxArrayDeque61B<T> implements Deque61B<T>{
    private T[] items;
    private int next_first;
    private int next_last;
    private int size;

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

    public MaxArrayDeque61B() {
        items = (T[]) new Object[8];
        next_first = 0;
        next_last = 1;
        size = 0;
    }

    @Override
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

    @Override
    public void addFirst(T x) {
        if (size == items.length) {
            resize(items.length * 2);
        }
        items[next_first] = x;
        next_first = Math.floorMod(next_first - 1, items.length);
        size += 1;
    }

    @Override
    public void addLast(T x) {
        if (size == items.length) {
            resize(items.length * 2);
        }
        items[next_last] = x;
        next_last = Math.floorMod(next_last + 1, items.length);
        size += 1;
    }

    @Override
    public List<T> toList() {
        List<T> return_list = new ArrayList<>();
        /*for (T elem : this) {
            return_list.add(elem);
        }*/
        Iterator<T> it = new MaxArrayIterator();
        while (it.hasNext()) {
            return_list.add(it.next());
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

        if (items.length >= 8 && size <= items.length * 0.25) {
            resize(items.length / 2);
        }
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

        if (items.length >= 8 && size <= items.length * 0.25) {
            resize(items.length / 2);
        }
        return x;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return items[Math.floorMod(next_first + index + 1, items.length)];
    }

    @Override
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
}
