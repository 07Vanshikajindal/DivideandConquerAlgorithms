package com.assignment.algos;

import java.util.Arrays;

public class QuickSort {
    private static final int INSERTION_CUTOFF = 16;

    public static void sort(int[] a) {
        sort(a, null);
    }

    public static void sort(int[] a, Metrics metrics) {
        if (Util.isNullOrEmpty(a) || a.length <= 1) return;
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

                // Random pivot using Util
                int p = Util.randomizedPartition(a, lo, hi);

                // recurse on smaller side, tail iterate on bigger
                if (p - lo < hi - p) {
                    sortRec(a, lo, p - 1, metrics);
                    lo = p + 1;
                } else {
                    sortRec(a, p + 1, hi, metrics);
                    hi = p - 1;
                }
            }
        } finally {
            if (metrics != null) metrics.exitRecursion();
        }
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

    public static void main(String[] args) {
        int[] a = {9, 3, 5, 1, 7, 4};
        System.out.println("Before: " + Arrays.toString(a));
        sort(a);
        System.out.println("After:  " + Arrays.toString(a));
    }
}
