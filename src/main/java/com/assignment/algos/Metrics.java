package com.assignment.algos;

// Metrics: counters, recursion depth tracker, elapsed time
public class Metrics {
    public long comparisons = 0;
    public long recursionDepth = 0;
    public long maxRecursionDepth = 0;
    public long startTimeNs = 0;
    public long endTimeNs = 0;

    // Increment comparison counter
    public void incComparisons() { comparisons++; }

    // Enter recursion, track max depth
    public void enterRecursion() {
        recursionDepth++;
        if (recursionDepth > maxRecursionDepth) maxRecursionDepth = recursionDepth;
    }

    // Exit recursion
    public void exitRecursion() {
        recursionDepth--;
    }

    // Start timer
    public void startTimer() { startTimeNs = System.nanoTime(); }

    // Stop timer
    public void stopTimer() { endTimeNs = System.nanoTime(); }

    // Elapsed time in milliseconds
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
