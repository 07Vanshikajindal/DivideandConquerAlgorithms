package com.assignment.algos;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeterministicSelectTest {

    @Test
    public void testSmallArray() {
        int[] a = {12, 3, 5, 7, 4, 19, 26};
        int k = 3; // 4th smallest
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
}
