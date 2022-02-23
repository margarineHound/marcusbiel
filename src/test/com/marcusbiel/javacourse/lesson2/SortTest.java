package com.marcusbiel.javacourse.lesson2;


import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class SortTest {

    public List<Integer> randomList(int size){
        Random random = new Random();
        List<Integer> newList = new ArrayList<>(size);
        for (int i =0; i<size; i++ )
            newList.add(random.nextInt(0,100));

        return newList;
    }

    @Test
    public void testQuickSort() {
        List<Integer> unsorted = randomList(10);
        System.out.println("Unsorted Array:\n" + unsorted.toString());
        List<Integer> sorted = Sort.quickSort(unsorted);

        System.out.println("Sorted Array:\n" + sorted.toString());
    }
}
