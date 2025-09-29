package com.assignment.algos;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeterministicSelectTest {

    @Test
    public void testSmallArray() {
        int[] a = {12, 3, 5, 7, 4, 19, 26};
        int k = 3;
        int[] expected = Arrays.copyOf(a, a.length);
        Arrays.sort(expected);
        assertEquals(expected[k], DeterministicSelect.select(a, k));
    }

    @Test
    public void testRandomArrays() {
        Random rand = new Random();
        for (int t = 0; t < 50; t++) {
            int[] a = rand.ints(50, -100, 100).toArray();
            int k = rand.nextInt(a.length);
            int[] expected = Arrays.copyOf(a, a.length);
            Arrays.sort(expected);
            assertEquals(expected[k], DeterministicSelect.select(a, k));
        }
    }

    @Test
    public void testSingleElement() {
        int[] a = {42};
        int result = DeterministicSelect.select(a, 0);
        assertEquals(42, result);
    }

    @Test
    public void testAllEqualElements() {
        int[] a = {7, 7, 7, 7, 7};
        for (int k = 0; k < a.length; k++) {
            assertEquals(7, DeterministicSelect.select(Arrays.copyOf(a, a.length), k));
        }
    }

    @Test
    public void testNegativeNumbers() {
        int[] a = {-5, -10, -3, -8, -2};
        int[] expected = Arrays.copyOf(a, a.length);
        Arrays.sort(expected);
        for (int k = 0; k < a.length; k++) {
            assertEquals(expected[k], DeterministicSelect.select(Arrays.copyOf(a, a.length), k));
        }
    }
}
