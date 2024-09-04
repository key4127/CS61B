package deque;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class LinkedListDeque61B<T> implements Deque61B<T> {
    private Node<T> sentinel;
    private int size;

    private class Node<T> {
        private T item;
        private Node<T> next;
        private Node<T> last;

        public Node(T val, Node<T> nextR, Node<T> lastR) {
            item = val;
            next = nextR;
            last = lastR;
        }
    }

    private class LinkedListIterator implements Iterator<T> {
        private int seer;
        private Node<T> node;

        public LinkedListIterator() {
            seer = 0;
            node = sentinel;
        }

        public boolean hasNext() {
            return seer < size;
        }

        public T next() {
            node = node.next;
            seer += 1;
            return node.item;
        }
    }

    public LinkedListDeque61B() {
        // sentinel = new Node<>(null, sentinel, sentinel) is wrong
        // before the assignment is completed, the sentinel is null
        sentinel = new Node<>(null, null, null);
        sentinel.next = sentinel;
        sentinel.last = sentinel;
        size = 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof LinkedListDeque61B<?> list) {
            if (list.size != size) {
                return false;
            }
            Node<T> x = sentinel;
            for (Object y : list) {
                x = x.next;
                if (y == x.item) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder return_string = new StringBuilder("[");
        Iterator<T> it = iterator();
        while (it.hasNext()) {
            return_string.append(it.next());
            if (it.hasNext()) {
                return_string.append(", ");
            }
        }
        return_string.append("]");
        return return_string.toString();
    }

    @Override
    public void addFirst(T x) {
        size += 1;
        Node<T> p = new Node<>(x, sentinel.next, sentinel);
        sentinel.next = p;
        p.next.last = p;
    }

    @Override
    public void addLast(T x) {
        size += 1;
        Node<T> p = new Node<>(x, sentinel, sentinel.last);
        sentinel.last = p;
        p.last.next = p;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        Node<T> p = sentinel.next;
        while (p != sentinel) {
            returnList.add(p.item);
            p = p.next;
        }
        return returnList;
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
        size -= 1;
        T val = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.last = sentinel;
        return val;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        size -= 1;
        T val = sentinel.last.item;
        sentinel.last = sentinel.last.last;
        sentinel.last.next = sentinel;
        return val;
    }

    @Override
    // I suppose the first index (besides the sentinel) is 0
    public T get(int index) {
        if (index < 0 || index > size) {
            return null;
        }
        Node<T> target = sentinel;
        for (int i = 0; i <= index; i++) {
            target = target.next;
        }
        return target.item;
    }

    @Override
    public T getRecursive(int index) {
        if (index < 0 || index > size) {
            return null;
        }
        return getRecursive(sentinel, index);
    }

    // the helper is private
    private T getRecursive(Node<T> p, int index) {
        if (index == 0) {
            return p.next.item;
        }
        return getRecursive(p.next, index - 1);
    }
}

