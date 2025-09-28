package com.assignment.algos;
// Metrics: counters, depth tracker, CSV writer
public class Metrics {
    public long comparisons = 0;
    public long recursionDepth = 0;
    public long maxRecursionDepth = 0;
    public long startTimeNs = 0;
    public long endTimeNs = 0;

    public void incComparisons() { comparisons++; }

    public void enterRecursion() {
        recursionDepth++;
        if (recursionDepth > maxRecursionDepth) maxRecursionDepth = recursionDepth;
    }

    public void exitRecursion() {
        recursionDepth--;
    }

    public void startTimer() { startTimeNs = System.nanoTime(); }
    public void stopTimer() { endTimeNs = System.nanoTime(); }

    public long elapsedMillis() {
        return (endTimeNs - startTimeNs) / 1_000_000;
    }

    @Override
    public String toString() {
        return "time_ms=" + elapsedMillis() +
                ", comparisons=" + comparisons +
                ", maxDepth=" + maxRecursionDepth;
    }
}
