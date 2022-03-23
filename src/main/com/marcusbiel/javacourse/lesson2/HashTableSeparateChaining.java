package com.marcusbiel.javacourse.lesson2;


import java.util.*;

class Entry <K, V>{
    int hash;
    K key;
    V value;

    public Entry (K key, V value){
        this.key = key;
        this.value = value;
        //do the hashcode here and cash it, because the cost of computation
//      may add up in the long run
        this.hash = key.hashCode();
    }
    public boolean equals(Entry<K, V> other){
        if(this.hash != other.hash)
            return false;
        return key.equals(other.key);
    }

    @Override
    public String toString(){
        return this.key + " ==> " + this.value;
    }

}

@SuppressWarnings("unchecked")
public class HashTableSeparateChaining <K,V> implements Iterable<K>{
    private static final int DEFAULT_CAPACITY = 3;
    private static final double DEFAULT_LOAD_FACTOR = 0.75;

    private double maxLoadCapacity;
    private int capacity, threshold, size = 0;

    //an array that stores linkedlists in it, just like how
    //an array of integers stores integers in it
    private LinkedList<Entry<K,V>> [] table;

    public HashTableSeparateChaining(){
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }
    public HashTableSeparateChaining(int capacity){
        this(capacity, DEFAULT_LOAD_FACTOR);
    }
    public HashTableSeparateChaining(double maxLoadCapacity){
        this(DEFAULT_CAPACITY, maxLoadCapacity);
    }
    public HashTableSeparateChaining(int capacity, double maxLoadCapacity){
        if (capacity<0)
            throw new IllegalArgumentException("Bad capacity");
        if (maxLoadCapacity <= 0 || Double.isNaN(maxLoadCapacity) || Double.isInfinite(maxLoadCapacity))
            throw new IllegalArgumentException("Bad maxLoadCapacity");

        this.maxLoadCapacity = maxLoadCapacity;
        this.capacity = Math.max(capacity, DEFAULT_CAPACITY);
        this.threshold = (int) maxLoadCapacity*capacity;
        this.table = new LinkedList[this.capacity];
    }
    public int size(){
        return this.size;
    }
    public boolean isEmpty(){
        return this.size==0;
    }
    private int normalizeIndex(int keyHash){
        return (keyHash & 0x7FFFFFF) % this.capacity;
    }

    public void clear(){
        for (int i=0;i<capacity;i++){
            this.table[i] = null;
        }
        this.size = 0;
    }

    public boolean containsKey(K key){
        return hashKey(key);
    }

    private boolean hashKey(K key) {
        int tempKey = key.hashCode();
        return table[normalizeIndex(tempKey)] != null;
    }
    public V add(K key, V value){
        return insert(key, value);
    }
    public V put(K key, V value){
        return insert(key, value);
    }

    private V insert(K key, V value) {
        if (key==null)
            throw new IllegalArgumentException("Bad key");
        Entry<K,V> newVal = new Entry<>(key, value);
        int hash = this.normalizeIndex(newVal.hash);
        return bucketInsertEntry(hash, newVal);

    }

    public V get(K key){
        if (key == null)
            return null;

        int bucketIndex = normalizeIndex(key.hashCode());
        Entry<K,V> tempEntry = bucketSeekEntry(bucketIndex, key);

        if (tempEntry != null)
            return tempEntry.value;
        return null;
    }



    public V remove(K key){
        if (key == null)
                return null;
        int tempBucketIndex = this.normalizeIndex(key.hashCode());
        return bucketRemoveEntry(tempBucketIndex, key);

    }

    private V bucketRemoveEntry(int index, K key) {

        Entry<K, V> tempEntry = bucketSeekEntry(index, key);
        if (tempEntry == null)
            return null;
        LinkedList<Entry<K,V>> newTable = table[index];
        newTable.remove(tempEntry);
        this.size--;
        return tempEntry.value;

    }
    private V bucketInsertEntry(int hash, Entry<K,V> newVal) {
        LinkedList<Entry<K,V>> existingList = table[hash];
        if (existingList == null){
            table[hash] = new LinkedList<>();
            existingList = table[hash];
        }

        Entry<K,V> existingEntry = bucketSeekEntry(hash, newVal.key);
        if (existingEntry == null) {
            existingList.add(newVal);
            if (this.size <threshold)
                this.size++;
            else
                resizeTable();
            return null;
        }else{
            V oldVal = existingEntry.value;
            existingEntry.value = newVal.value;
            return oldVal;
        }

    }

    private void resizeTable() {
        this.capacity *= 2;
        this.threshold = (int) maxLoadCapacity*this.capacity;
        LinkedList<Entry<K,V>> [] newtable = new LinkedList[capacity];
        for (int i =0; i<this.capacity/2;i++){
            if( table[i] != null) {
                LinkedList<Entry<K, V>> tempList = table[i];
                for(Entry<K,V> enrty: tempList) {
                    int newIndex = normalizeIndex(enrty.hash);
                    LinkedList<Entry<K,V>> newList = newtable[newIndex];
                    if (newList ==null)
                        newList = new LinkedList<>();
                    newList.add(enrty);
                }
                this.table[i].clear();
                this.table[i] = null;
            }
        }

        this.table = newtable;
    }

    private Entry<K,V> bucketSeekEntry(int bucketIndex, K key) {
        if (key == null)
            return null;
        LinkedList<Entry<K,V>> tempTable = table[bucketIndex];
        if (tempTable == null)
            return null;
        for (Entry<K,V> entry: tempTable){
            if (entry.key.equals(key))
                    return entry;
        }
        return null;
    }

    public List<K> keys(){
        List<K> newlist = new ArrayList<>(this.size);
        for (int i = 0; i<this.capacity; i++){
            LinkedList<Entry<K,V>> tempList = table[i];
            if (tempList == null) {
                continue;
            }
            for (Entry<K,V> entry:tempList) {
                newlist.add(entry.key);
            }
        }
        return newlist;
    }

    public List<V> values(){
        List<V> newlist = new ArrayList<>(this.size);
        for (int i = 0; i<this.capacity; i++){
            LinkedList<Entry<K,V>> tempList = table[i];
            if (tempList == null) {
                continue;
            }
            for (Entry<K,V> entry:tempList) {
                newlist.add(entry.value);
            }
        }
        return newlist;
    }


    @Override
    public Iterator<K> iterator() {
        return null;
    }
}
