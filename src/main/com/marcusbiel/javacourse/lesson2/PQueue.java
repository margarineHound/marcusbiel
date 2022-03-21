package com.marcusbiel.javacourse.lesson2;

import java.util.*;

public class PQueue <T extends Comparator <T>> {

    //how many elements are currently in the heap
    private int heapSize = 0;
    //max capacity of the heap
    private int heapCapacity;
    //use a list (aka dynamic array) to keep track of elements
    private List<T> heap = null;

    // instead of creating a key-value pair, which is allowed to have one unique key
    // for each value, we map each value to a TreeSet, meaning the set contains
    // a series of unique indices (indices cannot be duplicates because they indicate the position
    // of various nodes), for each value, thereby creating a set within a set (values are unique,
    // that way you don't need multiple keys for multiple indices)
    private Map<T, TreeSet<Integer>> map = new HashMap<>();

    public PQueue(){
        this(1);

    }

    public PQueue(int size){
        this.heap = new ArrayList<>(size);
        this.heapCapacity = size;
    }


    public PQueue(T[] elements){
        this(elements.length);

        this.heapCapacity = elements.length;
        this.heapSize = elements.length;

        for(T i: elements){
            this.mapAdd(elements[i], i);
            this.add(i);


        }


    }
    //O(nlogn)
    public PQueue(Collection <T> elements){
        this(elements.size());
        for(T i: elements){
            this.add(i);
        }

    }
     public boolean isEmpty(){
        return (this.heapSize == 0);
     }

     //O(n)
     public void clear(){
         for (int i =0;i<this.heapCapacity ;i++ ) {
             this.heap.set(i, null);
         }
         this.heapSize = 0;
         map.clear();
     }

     public int size(){
        return this.heapSize;
     }

     public T peek(){
         if (isEmpty()) {
             return null;
         }
         return this.heap.get(0);
     }

     public T poll(){
        this.removeAt(0);
     }

     public boolean contains(T element){
         if (element == null) {
             return false;
         }else
             //map look up with O(1)
            // worth if additions and removals are comparable
             return map.containsKey(element);

         //if no map, we can do a linear scan with O(n)
         //
         // for (int i=0; i<heapSize; i++)
//            if heap.get(i).equals(element) return true;
//         return false

     }
     public void add(T element){
         if (element == null) {
             throw new IllegalArgumentException();
         }
         if(heapSize < heapCapacity){
             this.heap.set(heapSize, element);
         }else{
             heap.add(element);
             heapCapacity++;
         }
         cmd
     }





}
