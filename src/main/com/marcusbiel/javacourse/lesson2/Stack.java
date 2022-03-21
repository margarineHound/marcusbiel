package com.marcusbiel.javacourse.lesson2;

import java.util.EmptyStackException;
import java.util.Iterator;

public class Stack <T> implements Iterable <T>{
    private final DoublyLinkedList <T> stack = new DoublyLinkedList<>();

    public Stack(){
    }

    public Stack(T element){
        this.stack.addFirst(element);
    }

    public boolean isEmpty(){
        return stack.isEmpty();
    }
    public T pop(){
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return stack.removeFirst();
    }

    public void push(T obj){
        this.stack.addFirst(obj);
    }

    public T peek(){
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return stack.peekFirst();
    }
//
//    this was mentioned in the lesson and then not covered in the implementation
//    am confused as to how this would be done. Do you pop everything? or search through the stack without popping
//    strick definition of search/pop would indicate that the stack would end up as a result of this process
//    public int search();

    public int size(){
        return stack.size();
    }

    @Override
    public Iterator <T> iterator() {
        return stack.iterator();
    }
}
