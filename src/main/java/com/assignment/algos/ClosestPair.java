package com.assignment.algos;

import java.util.*;

public class ClosestPair {

    public static class Point {
        double x, y;
        public Point(double x, double y) { this.x = x; this.y = y; }
    }

    private static double dist(Point p1, Point p2) {
        double dx = p1.x - p2.x;
        double dy = p1.y - p2.y;
        return Math.sqrt(dx*dx + dy*dy);
    }

    public static double closestPair(Point[] pts) { return closestPair(pts, null); }

    public static double closestPair(Point[] pts, Metrics metrics) {
        if (pts == null || pts.length < 2) return Double.POSITIVE_INFINITY;
        if (metrics != null) metrics.startTimer();
        Point[] sorted = Arrays.copyOf(pts, pts.length);
        Arrays.sort(sorted, Comparator.comparingDouble(p -> p.x));
        double res = closestUtil(sorted, 0, sorted.length - 1, metrics);
        if (metrics != null) metrics.stopTimer();
        return res;
    }

    private static double closestUtil(Point[] pts, int left, int right, Metrics metrics) {
        if (metrics != null) metrics.enterRecursion();
        try {
            if (right - left <= 3) {
                return bruteForce(pts, left, right, metrics);
            }

            int mid = (left + right) / 2;
            Point midPoint = pts[mid];
            double dl = closestUtil(pts, left, mid, metrics);
            double dr = closestUtil(pts, mid + 1, right, metrics);
            double d = Math.min(dl, dr);

            List<Point> strip = new ArrayList<>();
            for (int i = left; i <= right; i++) {
                if (Math.abs(pts[i].x - midPoint.x) < d) strip.add(pts[i]);
            }

            return Math.min(d, stripClosest(strip, d, metrics));
        } finally {
            if (metrics != null) metrics.exitRecursion();
        }
    }

    private static double bruteForce(Point[] pts, int left, int right, Metrics metrics) {
        double min = Double.MAX_VALUE;
        for (int i = left; i <= right; i++) {
            for (int j = i + 1; j <= right; j++) {
                if (metrics != null) metrics.incComparisons();
                min = Math.min(min, dist(pts[i], pts[j]));
            }
        }
        return min;
    }

    private static double stripClosest(List<Point> strip, double d, Metrics metrics) {
        double min = d;
        strip.sort(Comparator.comparingDouble(p -> p.y));
        for (int i = 0; i < strip.size(); i++) {
            for (int j = i + 1; j < strip.size() && (strip.get(j).y - strip.get(i).y) < min; j++) {
                if (metrics != null) metrics.incComparisons();
                min = Math.min(min, dist(strip.get(i), strip.get(j)));
            }
        }
        return min;
    }
}
