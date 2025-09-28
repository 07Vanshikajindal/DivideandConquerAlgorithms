package com.assignment.algos;

import java.util.Arrays;

public class DeterministicSelect {
    public static int select(int[] arr, int k) { return select(arr, k, null); }

    public static int select(int[] arr, int k, Metrics metrics) {
        if (arr == null || arr.length == 0 || k < 0 || k >= arr.length)
            throw new IllegalArgumentException("Invalid input");
        if (metrics != null) metrics.startTimer();
        int res = selectRec(arr, 0, arr.length - 1, k, metrics);
        if (metrics != null) metrics.stopTimer();
        return res;
    }

    private static int selectRec(int[] arr, int left, int right, int k, Metrics metrics) {
        if (metrics != null) metrics.enterRecursion();
        try {
            while (true) {
                if (left == right) return arr[left];

                int pivotIndex = medianOfMedians(arr, left, right, metrics);
                pivotIndex = partition(arr, left, right, pivotIndex, metrics);

                if (k == pivotIndex) return arr[k];
                else if (k < pivotIndex) right = pivotIndex - 1;
                else left = pivotIndex + 1;
            }
        } finally {
            if (metrics != null) metrics.exitRecursion();
        }
    }

    private static int partition(int[] arr, int left, int right, int pivotIndex, Metrics metrics) {
        int pivotValue = arr[pivotIndex];
        swap(arr, pivotIndex, right);
        int storeIndex = left;
        for (int i = left; i < right; i++) {
            if (metrics != null) metrics.incComparisons();
            if (arr[i] < pivotValue) {
                swap(arr, storeIndex, i);
                storeIndex++;
            }
        }
        swap(arr, storeIndex, right);
        return storeIndex;
    }

    private static int medianOfMedians(int[] arr, int left, int right, Metrics metrics) {
        int n = right - left + 1;
        if (n < 5) {
            Arrays.sort(arr, left, right + 1);
            return left + n / 2;
        }

        int numMedians = 0;
        for (int i = left; i <= right; i += 5) {
            int subRight = Math.min(i + 4, right);
            Arrays.sort(arr, i, subRight + 1);
            int medianIndex = i + (subRight - i) / 2;
            swap(arr, left + numMedians, medianIndex);
            numMedians++;
        }

        return medianOfMedians(arr, left, left + numMedians - 1, metrics);
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i]; arr[i] = arr[j]; arr[j] = tmp;
    }

    public static void main(String[] args) {
        int[] a = {12,3,5,7,4,19,26};
        int k = 3;
        System.out.println("kth = " + select(a, k));
    }
}
