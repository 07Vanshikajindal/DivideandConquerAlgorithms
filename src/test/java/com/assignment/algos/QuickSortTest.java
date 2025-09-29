package com.assignment.algos;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class QuickSortTest {

    @Test
    public void testRandomArray() {
        int[] a = {9, 3, 5, 1, 7, 4};
        int[] expected = Arrays.copyOf(a, a.length);
        Arrays.sort(expected);
        QuickSort.sort(a);
        assertArrayEquals(expected, a);
    }

    @Test
    public void testEmptyArray() {
        int[] a = {};
        QuickSort.sort(a);
        assertArrayEquals(new int[]{}, a);
    }

    @Test
    public void testSingleElement() {
        int[] a = {42};
        QuickSort.sort(a);
        assertArrayEquals(new int[]{42}, a);
    }

    @Test
    public void testDuplicatesArray() {
        int[] a = {5, 3, 5, 1, 3, 4};
        int[] expected = Arrays.copyOf(a, a.length);
        Arrays.sort(expected);
        QuickSort.sort(a);
        assertArrayEquals(expected, a);
    }

    @Test
    public void testAlreadySortedArray() {
        int[] a = {1, 2, 3, 4, 5};
        int[] expected = Arrays.copyOf(a, a.length);
        QuickSort.sort(a);
        assertArrayEquals(expected, a);
    }

    @Test
    public void testReverseSortedArray() {
        int[] a = {5, 4, 3, 2, 1};
        int[] expected = Arrays.copyOf(a, a.length);
        Arrays.sort(expected);
        QuickSort.sort(a);
        assertArrayEquals(expected, a);
    }

    @Test
    public void testAllSameElements() {
        int[] a = {7, 7, 7, 7, 7};
        int[] expected = Arrays.copyOf(a, a.length);
        QuickSort.sort(a);
        assertArrayEquals(expected, a);
    }

    @Test
    public void testLargeArray() {
        int[] a = new int[1000];
        for (int i = 0; i < a.length; i++) a[i] = a.length - i;
        int[] expected = Arrays.copyOf(a, a.length);
        Arrays.sort(expected);
        QuickSort.sort(a);
        assertArrayEquals(expected, a);
    }
}
