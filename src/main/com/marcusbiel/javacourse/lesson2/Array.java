package com.marcusbiel.javacourse.lesson2;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

@SuppressWarnings("unchecked")
public class Array <T> implements Iterable<T> {
    private T [] arr;
    private int len = 0;  // length user thinks array is
    private int capacity = 0; // actual array size

    public Array(){
        this(16);
    }

    public Array(int capacity) {
        if (capacity<0)
            throw new IllegalArgumentException("Illegal Capacity: "+ capacity);

        this.capacity = capacity;
        this.arr = (T[]) new Object[capacity];
    }

    int size(){
        return len;
    }

    public T get(int index){
        return this.arr[index];
    }

    public void set(int index, T item){

        this.arr[index] = item;
    }



    boolean isEmpty(){
        return (size() <1);
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator<T> spliterator() {
        return Iterable.super.spliterator();
    }
}
