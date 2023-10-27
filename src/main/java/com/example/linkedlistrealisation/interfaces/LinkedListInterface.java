package com.example.linkedlistrealisation.interfaces;

public interface LinkedListInterface<T> {
    boolean add(T element);
    boolean addByIndex(int id,T element);
    boolean remove(T element);
    boolean removeByIndex(int id);
    T get(int id);

}
