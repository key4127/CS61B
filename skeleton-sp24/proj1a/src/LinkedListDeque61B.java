import java.util.ArrayList;
import java.util.List;

public class LinkedListDeque61B<T> implements Deque61B<T> {
    private Node<T> sentinel;
    private int size;

    private class Node<T> {
        public T item;
        public Node<T> next;
        public Node<T> last;

        public Node(T val, Node<T> nextR, Node<T> lastR) {
            item = val;
            next = nextR;
            last = lastR;
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
    // I suppose the first index (besides the sentinel) is 1
    public T get(int index) {
        if (index < 0 || index > size) {
            return null;
        }
        Node<T> target = sentinel;
        for (int i = 0; i < index; i++) {
            target = target.next;
        }
        return target.item;
    }

    @Override
    public T getRecursive(int index) {
        if (index <= 0 || index > size) {
            return null;
        }
        return getRecursive(sentinel, index);
    }

    public T getRecursive(Node<T> p, int index) {
        if (index == 0) {
            return p.item;
        }
        return getRecursive(p.next, index - 1);
    }
}
