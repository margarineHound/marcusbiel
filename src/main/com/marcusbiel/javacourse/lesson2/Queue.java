package com.marcusbiel.javacourse.lesson2;


import java.util.Iterator;
import java.util.LinkedList;

public class Queue <T> implements Iterable <T>{
    private LinkedList <T> list = new LinkedList<T>();

    public Queue(){

    }

    public Queue(T data){
        this.list.addFirst(data);
    }

    public int size(){
        return this.list.size();
    }

    public void enqueue(T data){
        this.list.addLast(data);
    }

    public T dequeue(){
        return this.list.removeFirst();
    }

    public boolean isEmpty(){
        return this.list.isEmpty();
    }

    public T peek(){
        if (isEmpty()) {
            throw new RuntimeException("List Empty");

        }else {
            return this.list.peekFirst();
        }
    }

    public T poll(){
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }else{
            return this.list.pollFirst();
        }
    }

    public void offer(T data){
        this.list.offerLast(data);
    }

    @Override
    public Iterator<T> iterator() {
        return this.list.iterator();
    }
}
