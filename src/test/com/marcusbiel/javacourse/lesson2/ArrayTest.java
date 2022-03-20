package com.marcusbiel.javacourse.lesson2;


import org.junit.Test;

public class ArrayTest {



    public void printCurrent(Array array, int i){
        System.out.println("Added: " + array.get(i) + "\tLen: " + array.size());
    }


    public Array testAdd() {
        Array<Integer> array = new Array(3);
        for (int i =0; i <7; i++) {
            array.add(i);
//            System.out.println("Added: " + i + "\tCapacity: " + array.capacity + "\tLen: " + array.len);
            printCurrent(array, i);
        }

        System.out.println(array.toString());

        return array;
    }

    @Test
    public void testSize() {
        testAdd();
    }

    @Test
    public void testGet() {
        testAdd();

    }

    @Test
    public void testSet() {
        Array<Integer> array = testAdd();
        int j = 12;
        for (int i = 0; i<7; i++) {
            array.set(i, j--);
            printCurrent(array, i);
        }
        System.out.println(array.toString());


    }

    @Test
    public void testIsEmpty() {
    }

    @Test
    public void testClear() {
        Array<Integer> array = testAdd();
        array.clear();
        System.out.println(array.isEmpty() + "\tsize is : "  + array.size());
    }



    @Test
    public void testResize() {
    }

    @Test
    public void testRemoveAt() {
        testAdd().
    }

    @Test
    public void testIterator() {
    }

    @Test
    public void testTestSize() {
    }

    @Test
    public void testTestGet() {
    }

    @Test
    public void testTestSet() {
    }

    @Test
    public void testTestIsEmpty() {
    }

    @Test
    public void testTestClear() {
    }

    @Test
    public void testTestAdd() {
    }

    @Test
    public void testTestResize() {
    }

    @Test
    public void testTestRemoveAt() {
    }

    @Test
    public void testRemove() {
    }

    @Test
    public void testIndexOf() {
    }

    @Test
    public void testContains() {
    }

    @Test
    public void testTestIterator() {
    }

    @Test
    public void testTestToString() {

    }
}
