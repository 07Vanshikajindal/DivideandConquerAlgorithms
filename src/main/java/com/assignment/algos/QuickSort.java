package com.assignment.algos;

import java.util.Random;
import java.util.Arrays;

public class QuickSort {
    private static final int INSERTION_CUTOFF = 16;
    private static final Random RANDOM = new Random();
    public static void sort(int[] a) { sort(a, null); }

    public static void sort(int[] a, Metrics metrics) {
        if (a == null || a.length <= 1) return;
        if (metrics != null) metrics.startTimer();
        sortRec(a, 0, a.length - 1, metrics);
        if (metrics != null) metrics.stopTimer();
    }

    private static void sortRec(int[] a, int lo, int hi, Metrics metrics) {
        if (lo >= hi) return;
        if (metrics != null) metrics.enterRecursion();
        try {
            while (lo < hi) {
                int len = hi - lo + 1;
                if (len <= INSERTION_CUTOFF) {
                    insertionSort(a, lo, hi, metrics);
                    return;
                }
                int pivotIndex = lo + RANDOM.nextInt(len);
                int pivot = a[pivotIndex];
                swap(a, pivotIndex, hi);

                int p = partition(a, lo, hi, pivot, metrics);
                // recurse on smaller side
                if (p - lo < hi - p) {
                    sortRec(a, lo, p - 1, metrics);
                    lo = p + 1; // tail iterate on bigger side
                } else {
                    sortRec(a, p + 1, hi, metrics);
                    hi = p - 1;
                }
            }
        } finally {
            if (metrics != null) metrics.exitRecursion();
        }
    }

    private static int partition(int[] a, int lo, int hi, int pivot, Metrics metrics) {
        int i = lo;
        for (int j = lo; j < hi; j++) {
            if (metrics != null) metrics.incComparisons();
            if (a[j] < pivot) {
                swap(a, i, j);
                i++;
            }
        }
        swap(a, i, hi);
        return i;
    }

    private static void insertionSort(int[] a, int lo, int hi, Metrics metrics) {
        for (int i = lo + 1; i <= hi; i++) {
            int key = a[i];
            int j = i - 1;
            while (j >= lo) {
                if (metrics != null) metrics.incComparisons();
                if (a[j] > key) {
                    a[j + 1] = a[j];
                    j--;
                } else break;
            }
            a[j + 1] = key;
        }
    }

    private static void swap(int[] a, int i, int j) {
        int tmp = a[i]; a[i] = a[j]; a[j] = tmp;
    }

    public static void main(String[] args) {
        int[] a = {9,3,5,1,7,4};
        System.out.println("Before: " + Arrays.toString(a));
        sort(a);
        System.out.println("After:  " + Arrays.toString(a));
    }
}
