package com.assignment.algos;

import java.util.Random;

public class Util {
    private static final Random rand = new Random();

    // swap two elements in an int array
    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    // partition used by quicksort/select
    public static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

    // randomized partition (for quicksort)
    public static int randomizedPartition(int[] arr, int low, int high) {
        int pivotIndex = rand.nextInt(high - low + 1) + low;
        swap(arr, pivotIndex, high);
        return partition(arr, low, high);
    }

    // check if array is null or empty (generic)
    public static <T> boolean isNullOrEmpty(T[] arr) {
        return arr == null || arr.length == 0;
    }

    // check if int[] is null or empty
    public static boolean isNullOrEmpty(int[] arr) {
        return arr == null || arr.length == 0;
    }
}
