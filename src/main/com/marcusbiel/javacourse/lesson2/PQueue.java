package com.marcusbiel.javacourse.lesson2;

import java.util.*;

public class PQueue <T extends Comparable <T>> {

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

        for(int i = 0; i<heapCapacity; i++){
            this.mapAdd(elements[i], i);
            this.heap.add(elements[i]);
        }

        for (int i =Math.max(0, (heapSize/2)-1); i>=0; i--){
            sink(i);
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
             //heapSize is the location of the element
             this.heap.set(heapSize, element);
         }else{
             heap.add(element);
             heapCapacity++;
         }

         //add to the map to keep track of it
         this.mapAdd(element, heapSize);
         //swip up the node to adjust the location of the node because
//         it's added to the end;
         swim(heapSize);
         heapSize++;

     }
     private boolean less(int i, int j){
        T node1 = heap.get(i);
        T node2 = heap.get(j);
        return node1.compareTo(node2) <=0;
     }

    //bottom up node swim O(logn)

    private void swim(int k){
        //Grab the index of the next parent node wrt k
//         he's starting the heap from 0
//         if he was starting it from 1, then children are all
//         at 2k and 2k+1
//         and their parents would be floor(k/2)
         int parent = (k-1) /2;
         while (k>0 && less(k, parent)){

             swap(k, parent);
             k = parent;
             parent = (k-1)/2;
//
         }
     }
     private void sink(int k){
        while (k<this.heapSize ) {
            int leftChild = k * 2 + 1;
            int rightChild = k * 2 + 2;

            if (leftChild>= heapSize)
                break;
            int smallest = leftChild;

            if (rightChild < heapSize && less(rightChild, leftChild))
                smallest = rightChild;

            if (heap.get(k).compareTo(heap.get(smallest)) > 0)
                swap(k, smallest);
            else break;
            k = smallest;
        }
     }

    private void swap(int i, int j) {
        T i_val = heap.get(i);
        T j_val = heap.get(j);

        heap.set(i, j_val);
        heap.set(j, i_val);

        mapSwap(i_val, j_val, i, j);
    }


    public boolean remove(T element) {

        if (element == null)
            return false;

//        for O(n) using linear search:
//        for (for i=0; i<heapSize; i++){
//          if heap.get(i) == element{
//              heap.remove(i);
//              return true
//              }
//
//        }

//        Logarithmic Remove with map O(log(n))
        Integer index = mapGet(element);
        if (index != null)
            removeAt(index);
        return index != null;

    }

    private T removeAt(int index) {
        if(isEmpty())
            return null;

        T removed_data = heap.get(index);
        if (index == heapSize-1){
            mapRemove(removed_data, --heapSize);
            return removed_data;
        }else {

            swap(index, --heapSize);
            heap.set(heapSize, null);
            T elem = heap.get(index);
            sink(index);
            if (heap.get(index).equals(elem))
                swim(index);
            return removed_data;
        }
    }



    public boolean isMinHeap(int k){
        if (k>= heapSize || heapSize <1 )
            return true;

        int leftChild = 2*k +1;
        int rightChild = 2*k + 2;

        // compare parent with child and return false if either test fails
        // we haven't cheked whether left and right children exist
        if (leftChild < heapSize && !less(k, leftChild))
            return false;
        if (rightChild < heapSize && !less(k, rightChild))
            return false;

        return isMinHeap(leftChild) && isMinHeap(rightChild);
    }


    private void mapSwap(T i_val, T j_val, int i, int j) {

        TreeSet<Integer> i_set = map.get(i_val);
        TreeSet<Integer> j_set = map.get(j_val);
        i_set.remove(i);
        i_set.add(j);
        j_set.remove(j);
        j_set.add(i);
        map.put(i_val, j_set);
        map.put(j_val, i_set);

    }

    private void mapAdd(T element, int i) {


        TreeSet<Integer> temp = map.get(element);
        if(temp ==null){
            temp = new TreeSet<>();
            temp.add(i);
            map.put(element, temp);
        }
        else {
            temp.add(i);
            map.put(element, temp);
        }

    }

    private void mapRemove(T removed_data, int i) {
        TreeSet<Integer> temp = map.get(removed_data);
        if (temp.size() < 2) {
            map.remove(removed_data);

        }else {
            temp.remove(i);
            map.put(removed_data, temp);
        }
        return;
    }

    private Integer mapGet(T element) {
        TreeSet<Integer> temp = map.get(element);
        if (temp!= null)
            return temp.last();
        return null;
    }

    @Override
    public String toString(){
        return  heap.toString();
    }
}
