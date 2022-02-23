package com.marcusbiel.javacourse.lesson2;


import java.util.ArrayList;
import java.util.List;

public class Sort {

    public static List<Integer> linearSort(List<Integer> unsorted) {

        int size = unsorted.size();
        if (size < 2) {
            return unsorted;
        }
        List<Integer> sorted = new ArrayList<>(size);
        for (int i = 0; i < size - 1; i++) {
            int min = unsorted.get(i);
            for (int j = i + 1; j < size; j++) {
                int current = unsorted.get(j);
                if (current < min) {
                    min = current;
                }
            }
            sorted.add(min);
        }
        return sorted;
    }


    public static List<Integer> quickSort(List<Integer> array) {
        System.out.println(array.toString());
        if (array.size() < 2) {
            return array;
        }

        int arr_size = array.size();
        int pivot_point = arr_size / 2;
        int pivot = array.get(pivot_point);
        List<Integer> arr1 = new ArrayList<>(arr_size / 2);
        List<Integer> arr2 = new ArrayList<>(array.size() - pivot_point - 1);

        for (int i = 0; i < arr_size; i++) {
            int curr = array.get(i);
            if (i == pivot_point) {
            } else if (curr < pivot)
                arr1.add(curr);
            else
                arr2.add(curr);
        }

        arr1 = quickSort(arr1);
        arr2 = quickSort(arr2);
        int arr1_size = arr1.size();
        int arr2_size = arr2.size();


        int max = arr1_size + arr2_size + 1;
        if (arr1_size == 0) {
            array.set(0, pivot);
            for (int i =0; i<arr2_size; i++ )
                array.set(i+1, arr2.get(i));
        }
        else if (arr2_size == 0) {
            for (int i =0; i<arr1_size; i++ ) {
                array.set(i, arr1.get(i));
            }
            array.set(arr1_size, pivot);
        }
        else {
            for (int i = 0; i < max; i++) {
                if (i < arr1_size) {
                    array.set(i, arr1.get(i));
                } else if (i == arr1_size) {
                    array.set(i, pivot);
                } else {
                    array.set(i, arr2.get(i - arr1_size - 1));
                }
            }
        }
        return array;
    }
}
