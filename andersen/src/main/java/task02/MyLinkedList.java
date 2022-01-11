package task02;

import task01.MyList;

import java.util.*;

public class MyLinkedList<T> implements MyList<T>,  MyDeque<T> {

    private int size;
    private Node<T> first;
    private Node<T> last;

    private static class Node<T> {
        Node<T> next;
        T element;
        Node<T> prev;

        private Node(Node<T> next, T value, Node<T> prev) {
            this.next = next;
            this.element = value;
            this.prev = prev;
        }
    }

    public MyLinkedList() {
    }

    @Override
    public boolean add(T element) {
        Node<T> currentNode;

        if (first == null) {
            currentNode = new Node<>(null, element, null);
            first = currentNode;
        } else {
            currentNode = new Node<>(null, element, last);
            last.next = currentNode;
        }
        last = currentNode;
        size++;
        return true;
    }

    @Override
    public void sort(Comparator<? super T> comparator) {
        for (int i = 0; i < size; i++) {
            Node<T> firstElement = first;

            while (firstElement.next != null) {
                Node<T> secondElement = firstElement.next;
                if (comparator.compare(firstElement.element, secondElement.element) > 0) {
                    Node<T> tmp = new Node<>(null, secondElement.element, null);
                    secondElement.element = firstElement.element;
                    firstElement.element = tmp.element;
                }
                firstElement = secondElement;
            }
        }
    }

    @Override
    public void addLast(T element) {
        add(element);
    }

    @Override
    public boolean add(int index, T element) {
        Objects.checkIndex(index, size + 1);
        Node<T> currentNode = new Node<>(null, element, null);

        if (first == null) {
            first = currentNode;
            last = currentNode;
        } else if (index == 0) {
            currentNode.next = first;
            currentNode.prev = last;
            first = currentNode;
        } else if (index == size) {
            last.next = currentNode;
            currentNode.prev = last;
            last = currentNode;
        } else {
            Node<T> prev = getNodeByIndex(index - 1);
            currentNode.next = prev.next;
            prev.next = currentNode;
        }

        size++;
        return true;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return getNodeByIndex(index).element;
    }

    @Override
    public T set(int index, T element) {
        Objects.checkIndex(index, size);
        Node<T> currentNode = getNodeByIndex(index);
        currentNode.element = element;

        return element;
    }

    @Override
    public String remove(int index) {
        Objects.checkIndex(index, size);

        if (index == 0) {
            first = first.next;
            if (first == null) {
                last = null;
            }
        } else {
            Node<T> prev = getNodeByIndex(index - 1);
            prev.next = prev.next.next;
            if (index == size - 1) {
                last = prev;
            }
        }
        size--;
        return null;
    }

    @Override
    public void remove(T element) {
        if (first == null) {
            return;
        }

        if (first.element == element) {
            first = first.next;
            return;
        }

        Node<T> currentNode = first;

        while (currentNode.next != null) {
            if (currentNode.next.element == element) {
                currentNode.next = currentNode.next.next;
                return;
            }
            currentNode = currentNode.next;
        }

    }

    public boolean isEmpty() {
        return first == null;
    }

    public T getFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return get(0);
    }

    public T getLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return get(size - 1);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void addFirst(T element) {
        Node<T> firstNode = new Node<>(null, element, null);
        firstNode.next = first;
        first = firstNode;

    }

    private Node<T> getNodeByIndex(int index) {
        Node<T> current = first;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    @Override
    public String toString() {
        return "MyLinkedList{" +
                "first=" + first +
                ", last=" + last +
                '}';
    }
}