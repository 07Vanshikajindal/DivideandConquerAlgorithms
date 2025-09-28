package com.assignment.algos;

import org.junit.jupiter.api.Test;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class MergeSortTest {

    @Test
    public void testRandomArray() {
        int[] a = {5, 2, 9, 1, 5, 6};
        int[] expected = Arrays.copyOf(a, a.length);
        Arrays.sort(expected);   // Java’s built-in sort
        MergeSort.sort(a);       // Your algorithm
        assertArrayEquals(expected, a);
    }

    @Test
    public void testEmptyArray() {
        int[] a = {};
        MergeSort.sort(a);
        assertArrayEquals(new int[]{}, a);
    }

    @Test
    public void testSingleElement() {
        int[] a = {42};
        MergeSort.sort(a);
        assertArrayEquals(new int[]{42}, a);
    }
}
