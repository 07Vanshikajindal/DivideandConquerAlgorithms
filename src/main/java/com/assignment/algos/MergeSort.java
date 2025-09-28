package com.assignment.algos;
import java.util.Arrays;
public class MergeSort {
    private static final int INSERTION_CUTOFF = 16;
    public static void sort(int[] a) {
        sort(a, null);
    }
    public static void sort(int[] a, Metrics metrics) {
        if (a == null || a.length <= 1) return;
        if (metrics != null) metrics.startTimer();
        int[] buf = new int[a.length]; 
        sortRec(a, buf, 0, a.length - 1, metrics);
        if (metrics != null) metrics.stopTimer();
    }

    private static void sortRec(int[] a, int[] buf, int lo, int hi, Metrics metrics) {
        if (metrics != null) metrics.enterRecursion();
        try {
            int len = hi - lo + 1;
            if (len <= INSERTION_CUTOFF) {
                insertionSort(a, lo, hi, metrics);
                return;
            }
            int mid = lo + (hi - lo) / 2;
            sortRec(a, buf, lo, mid, metrics);
            sortRec(a, buf, mid + 1, hi, metrics);
            merge(a, buf, lo, mid, hi, metrics);
        } finally {
            if (metrics != null) metrics.exitRecursion();
        }
    }
    private static void merge(int[] a, int[] buf, int lo, int mid, int hi, Metrics metrics) {
        int i = lo, j = mid + 1, k = lo;
        while (i <= mid && j <= hi) {
            if (metrics != null) metrics.incComparisons();
            if (a[i] <= a[j]) buf[k++] = a[i++];
            else buf[k++] = a[j++];
        }
        while (i <= mid) buf[k++] = a[i++];
        while (j <= hi) buf[k++] = a[j++];
        System.arraycopy(buf, lo, a, lo, hi - lo + 1);
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
        int[] a = {5,2,9,1,5,6};
        System.out.println("Before: " + Arrays.toString(a));
        sort(a);
        System.out.println("After:  " + Arrays.toString(a));
    }
}
