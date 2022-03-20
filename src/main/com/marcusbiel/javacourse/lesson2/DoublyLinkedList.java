package com.marcusbiel.javacourse.lesson2;

import java.util.Iterator;

public class DoublyLinkedList <T> implements Iterable<T>{

    int size = 0;
    public Node <T> head = null;
    public Node <T> tail = null;

    private class Node <T>{
        T data = null;
        Node <T> prev = null;
        Node <T> next = null;

        public Node (T data, Node <T> prev, Node <T> next){
            this.data = data;
            this.next = next;
            this.prev = prev;
        }

        @Override
        public String toString(){
            return this.data.toString();
        }

    }

    public int size(){
        return this.size;
    }

    public boolean isEmpty(){
        return (this.size == 0);
    }

    public void clear(){
        Node <T> temp = this.head;
        while (temp != null) {
            Node <T> t1 = temp.next;
            temp.next = null;
            temp.prev = null;
            temp.data = null;
            temp = t1;
        }
        this.head = null;
        this.tail = null;
        temp = null;
        this.size = 0;
    }

    //adds elements to the tail
    public void add(T element){

        addLast(element);

    }


    public void addFirst(T element){
        if (isEmpty()) {
            Node <T> temp = new Node<>(element, null, null);
            this.head = temp;
            this.tail = temp;

        }else {
            this.head.prev = new Node<>(element, null, this.head);
            this.head = head.prev;
        }
        this.size ++;

    }

    public void addLast(T element) {

        if (isEmpty()) {
            this.head = this.tail = new Node<>(element, null, null);
        } else {

            this.tail.next = new Node<>(element, this.tail, null);
            this.tail = this.tail.next;
        }
        this.size++;
    }

    public T peekFirst(){
        if (isEmpty())
            throw new RuntimeException("Empty List");

        return this.head.data;
    }

    public T peekLast(){
        if (isEmpty())
            throw new RuntimeException("Empty List");

        return this.tail.data;
    }

    public T removeFirst(){
        if (isEmpty())
            throw new RuntimeException("Empty List");
        else{
            T oldHead = this.head.data;
            this.head = this.head.next;
            this.head.prev = null;
            this.size--;
            if (isEmpty()) {
                this.head = this.tail = null;
            }
            return oldHead;
        }
    }

    public T removeLast(){
        if ((isEmpty()))
            throw new RuntimeException("Empty List");
        else{
            T oldHead = this.tail.data;
            this.tail = this.tail.prev;
            this.tail.next = null;
            this.size--;
            if (isEmpty()) {
                this.head = this.tail = null;
            }

            return oldHead;
        }
    }

    private T remove(Node<T> node){
        if (node == this.head)
            return this.removeFirst();
        if (node == this.tail)
            return this.removeLast();

        T temp = node.data;
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.data = null;
        node.next = node.prev = null;
        this.size --;
        return temp;
    }

    public T removeAt(int idx){
        if (idx < 0 || idx >= size()) {
            throw new IndexOutOfBoundsException();
        }

        int i;
        Node <T> temp;

        if (idx <= size/2) {
            i = 0;
            temp = this.head;
            while (i++<idx) {
                temp = temp.next;
            }
        } else {
            i= size-1;
            temp = this.tail;
            while (i-- > idx) {
                temp = temp.prev;
            }
        }
        return this.remove(temp);

    }

    public boolean remove(Object obj){
        Node<T> temp = this.head;
        // equals method throws a NullPointerException if invoked on a null
        if (obj == null) {
            while (temp.next != null) {
                if (temp.data == null) {
                    this.remove(temp);
                    return true;
                }
            }
        }
        else {
            while (temp.next != null) {
                if (temp.data.equals(obj)) {
                    this.remove(temp);
                    return true;
                }
            }
        }
        return false;
    }

    public int indexOf(Object obj){
        int i;
        Node<T> temp;

        if (obj == null) {
            for (i = 0, temp = this.head; temp!= null; i++, temp = temp.next) {
                if (temp.data == null) {
                    return i;
                }
            }
        }

        else{
            for (i = 0, temp = this.head; temp != null; i++, temp = temp.next) {
                if (temp.data.equals(obj)) {
                    return i;
                }
            }
        }
        return -1;
    }

    public boolean contains(Object obj){
        return indexOf(obj) != -1;
    }

    @Override
    public java.util.Iterator<T> iterator(){
        return new Iterator<T>() {
            private Node<T> temp = head;
            public boolean hasNext() {
                return (temp != null);
            }

            public T next() {
                T data = temp.data;
                temp = temp.next;
                return data;
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        Node <T> temp;
        int i;
        sb.append("[");
        for (i = 0, temp = this.head; size < size -1 ; i++, temp = temp.next ) {
            sb.append(temp.data).append(", ");
        }
        sb.append(temp.next.data).append("]");

        return sb.toString();
    }






}
