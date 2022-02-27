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
        if (this.len + 1 >= this.capacity)
            this.resize(this.capacity * 2);
        this.arr[this.len] = elem;
        this.len++;

    }
    public void resize(int newCapacity){
        if (newCapacity <0) capacity = 1;
        T[] newArr = (T[]) new Object[newCapacity];
        for (int i = 0; i<this.capacity; i++) {
            newArr[i] = this.arr[i];
        }
//        this.len = this.capacity;
        this.capacity = newCapacity;
        this.arr = newArr;
    }

    public T removeAt(int rm_index){
        if (rm_index >= this.len || rm_index <0) {
            throw new IndexOutOfBoundsException();
        }

        T[] newArr = (T[]) new Object[capacity-1];
        T removedData = this.arr[rm_index];
        for (int i=0, j=0; i<len; i++, j++) {
            if (i == rm_index) {
                j--;
                continue;
            }
            newArr[j] = this.arr[i];
        }
        this.capacity = capacity-1;
        this.len = len-1;
        this.arr = newArr;
        return removedData;
    }

    public boolean remove(Object obj){
        for (int i=0; i<this.len ; i++ ) {
            if (this.arr[i].equals(obj)) {
                removeAt(i);
                return true;
            }
        }
        return false;
    }

    public int indexOf(Object obj){
        for (int i = 0; i<this.len ; i++ ) {
            if (this.arr[i].equals(obj)) {
                return i;
            }
        }
        return -1;
    }

    public boolean contains(Object obj){
        return this.indexOf(obj) != -1;
    }

    @Override
    public Iterator<T> iterator() {

        return new Iterator<T>() {
            int index = 0;
            public boolean hasNext() {
                return index < len;
            }

            public T next() {
                return arr[index];
            }
        };
    }

    @Override
    public String toString(){
        if (len<1) {
            return "[]";
        }
        StringBuilder stringBuilder = new StringBuilder(2+(3*this.len));
        stringBuilder.append("[");
        for (int i=0;i<this.len-1;i++ ) {
            stringBuilder.append(this.arr[i]).append(", ");
        }
        return stringBuilder.append(this.arr[this.len-1]).append("]").toString();
    }

}
