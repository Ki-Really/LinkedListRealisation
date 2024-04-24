package com.example.linkedlistrealisation.dataStructures;

import com.example.linkedlistrealisation.interfaces.LinkedListInterface;

import java.util.Comparator;
import java.util.function.Consumer;

public class LinkedListRealisation<T>  implements LinkedListInterface<T> {
    private Node first;
    private Node last;
    private int size = 0;


    public int getSize() {
        return size;
    }

    public void clear() {
        first = null;
        last = null;
        size = 0;
    }

    @Override
    public boolean add(T value) {
        if (size == 0) {
            first = new Node(null, null, value);
            last = first;
        } else {
            Node secondLast = last;
            last = new Node(null, secondLast, value);
            secondLast.next = last;
        }
        size++;
        return true;
    }

    @Override
    public boolean addByIndex(int id, T value) {
        if (id < 0 || id > size) {
            throw new IndexOutOfBoundsException();
        }
        if (id == size) {
            add(value);
            return true;
        }

        Node nodeToReplace = getNode(id);
        Node nodeToReplacePrev = nodeToReplace.prev;

        Node nodeToAdd = new Node(nodeToReplace, nodeToReplacePrev, value);

        nodeToReplace.prev = nodeToAdd;
        if (nodeToReplacePrev != null) {
            nodeToReplacePrev.next = nodeToAdd;
        } else {
            first = nodeToAdd;
        }
        size++;
        return true;
    }

    @Override
    public boolean remove(T value) {
        Node tempNode = first;
        for (int i = 0; i < size; i++) {
            if (tempNode.value.equals(value)) {
                return removeByIndex(i);
            }
            tempNode = tempNode.next;
        }
        return false;
    }


    @Override
    public boolean removeByIndex(int id) {
        Node nodeToRemove = getNode(id);
        Node nodeToRemovePrev = nodeToRemove.prev;
        Node nodeToRemoveNext = nodeToRemove.next;

        if (nodeToRemoveNext != null) {
            nodeToRemoveNext.prev = nodeToRemovePrev;
        } else {
            last = nodeToRemovePrev;
        }
        if (nodeToRemovePrev != null) {
            nodeToRemovePrev.next = nodeToRemoveNext;
        } else {
            first = nodeToRemoveNext;
        }
        size--;
        return true;
    }

    @Override
    public T get(int id) {
        return getNode(id).value;
    }

/*    LinkedList<Object> linkedList;
    linkedList*/

    private Node getNode(int id) {
        if (id < 0 || id >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node tempNode = first;
        for (int i = 0; i < id; i++) {
            tempNode = tempNode.next;
        }
        return tempNode;
    }

    public Node getFirst() {
        return first;
    }

    // возвращаем середину списка
    private Node split(Node head) {
        Node fast = head, slow = head;
        while (fast.next != null
                && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        Node temp = slow.next;
        slow.next = null;
        return temp;
    }

    public LinkedListRealisation<T> sort(Comparator comparator) {
        Node node = mergeSort(first, comparator);
        first = node;
        return this;
    }

    public Node mergeSort(Node node, Comparator comparator) {
        if (node == null || node.next == null) {
            return node;
        }
        Node second = split(node);

        // проходим рекурсивно по частям списка
        node = mergeSort(node, comparator);
        second = mergeSort(second, comparator);

        // Сливаем части воедино
        return merge(node, second, comparator);
    }

    // Функция слияния
    private Node merge(Node first, Node second, Comparator comparator) {

        if (first == null) {
            return second;
        }


        if (second == null) {
            return first;
        }

        // Выбираем меньшее значение
        if (comparator.compare(first.value, second.value) < 0) {
            first.next = merge(first.next, second, comparator);
            first.next.prev = first;
            first.prev = null;
            return first;
        } else {
            second.next = merge(first, second.next, comparator);
            second.next.prev = second;
            second.prev = null;
            return second;
        }
    }


    public void forEach(Consumer<T> consumer) {
        Node current = this.first;
        while (current != null) {
            consumer.accept(current.value);
            current = current.next;
        }
    }

    private class Node {
        private Node next;
        private Node prev;
        private T value;

        public Node(Node next, Node prev, T value) {
            this.next = next;
            this.prev = prev;
            this.value = value;
        }
    }
}
