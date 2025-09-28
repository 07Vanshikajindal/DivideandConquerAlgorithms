package com.assignment.algos;

import org.junit.jupiter.api.Test;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClosestPairTest {

    // Brute force O(n^2) method for testing
    private double bruteForce(ClosestPair.Point[] pts) {
        double min = Double.MAX_VALUE;
        for (int i = 0; i < pts.length; i++) {
            for (int j = i+1; j < pts.length; j++) {
                double dx = pts[i].x - pts[j].x;
                double dy = pts[i].y - pts[j].y;
                double d = Math.sqrt(dx*dx + dy*dy);
                min = Math.min(min, d);
            }
        }
        return min;
    }

    @Test
    public void testSmallSet() {
        ClosestPair.Point[] pts = {
                new ClosestPair.Point(0,0),
                new ClosestPair.Point(3,4),
                new ClosestPair.Point(7,7),
                new ClosestPair.Point(1,1)
        };
        double expected = bruteForce(pts);
        double result = ClosestPair.closestPair(pts);
        assertEquals(expected, result, 1e-6);
    }

    @Test
    public void testRandomSets() {
        Random rand = new Random();
        for (int t = 0; t < 20; t++) {
            ClosestPair.Point[] pts = new ClosestPair.Point[50];
            for (int i = 0; i < pts.length; i++) {
                pts[i] = new ClosestPair.Point(rand.nextDouble()*100, rand.nextDouble()*100);
            }
            double expected = bruteForce(pts);
            double result = ClosestPair.closestPair(pts);
            assertEquals(expected, result, 1e-6);
        }
    }
}
