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
        return this.len;
    }

    public T get(int index){
        if (index <0 || index>= this.len) {
            throw new IllegalArgumentException("Illegal Capacity: "+ this.capacity);
        }
        return this.arr[index];

    }

    public void set(int index, T item){
        if (index <0 || index>= this.len) {
            throw new IllegalArgumentException("Illegal Capacity: "+ this.capacity);
        }
        this.arr[index] = item;
    }

    boolean isEmpty(){
        return (size() <1);
    }

    public void clear(){
        for (int i = 0; i<this.capacity; i++) {
            this.arr[i] = null;
        }
        this.len = 0;
    }

    public void add(T elem){
        if (this.len + 1 >= this.capacity) this.resize(this.capacity * 2);
        this.arr[this.len] = elem;
        this.len++;

    }
    public void resize(int newCapacity){
        if (newCapacity <0) capacity = 1;
        T[] newArr = (T[]) new Object[newCapacity];
        for (int i = 0; i<this.capacity; i++) {
            newArr[i] = this.arr[i];
        }
        this.len = this.capacity;
        this.capacity = newCapacity;
        this.arr = newArr;
    }

    public T removeAt(int rm_index){
        if (rm_index >= this.len || rm_index <0) {
            throw new IndexOutOfBoundsException();
        }
        T[] newArr = (T[]) new Object[capacity-1];
        T removedData = null;
        for (int i=0; ; ) {

        }

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
