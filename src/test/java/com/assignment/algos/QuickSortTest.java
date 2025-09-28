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
}
