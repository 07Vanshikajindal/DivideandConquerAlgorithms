package com.assignment.algos;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Benchmark {
    private static final String CSV = "data/results.csv";

    public static void main(String[] args) throws Exception {
        // create data directory if it doesn't exist
        Files.createDirectories(Paths.get("data"));

        // create CSV with header if not exists
        Path p = Paths.get(CSV);
        if (!Files.exists(p)) {
            try (PrintWriter w = new PrintWriter(new FileWriter(CSV, true))) {
                w.println("algorithm,n,trial,time_ms,max_depth,comparisons");
            }
        }

        int[] nsSort = {100, 1000, 5000, 10000, 20000};
        int trials = 5;
        Random rnd = new Random(12345);

        // MergeSort benchmark
        for (int n : nsSort) {
            for (int t = 1; t <= trials; t++) {
                int[] a = rnd.ints(n, -1_000_000, 1_000_000).toArray();
                int[] copy = Arrays.copyOf(a, a.length);
                Metrics m = new Metrics();
                MergeSort.sort(copy, m);
                appendRow("MergeSort", n, t, m);
            }
        }

        // QuickSort benchmark
        for (int n : nsSort) {
            for (int t = 1; t <= trials; t++) {
                int[] a = rnd.ints(n, -1_000_000, 1_000_000).toArray();
                int[] copy = Arrays.copyOf(a, a.length);
                Metrics m = new Metrics();
                QuickSort.sort(copy, m);
                appendRow("QuickSort", n, t, m);
            }
        }

        // Deterministic Select benchmark (median)
        for (int n : nsSort) {
            for (int t = 1; t <= trials; t++) {
                int[] a = rnd.ints(n, -1_000_000, 1_000_000).toArray();
                int[] copy = Arrays.copyOf(a, a.length);
                int k = n / 2;
                Metrics m = new Metrics();
                DeterministicSelect.select(copy, k, m);
                appendRow("DeterministicSelect", n, t, m);
            }
        }

        // Closest Pair benchmark
        int[] nsPoints = {100, 500, 1000, 2000};
        for (int n : nsPoints) {
            for (int t = 1; t <= trials; t++) {
                ClosestPair.Point[] pts = new ClosestPair.Point[n];
                for (int i = 0; i < n; i++) {
                    pts[i] = new ClosestPair.Point(rnd.nextDouble() * 1e6, rnd.nextDouble() * 1e6);
                }
                Metrics m = new Metrics();
                // Make sure method exists: closestPair(pts, Metrics)
                ClosestPair.closestPair(pts, m);
                appendRow("ClosestPair", n, t, m);
            }
        }

        System.out.println("Benchmark completed. CSV: " + CSV);
    }

    // Write a row in the CSV
    private static void appendRow(String algo, int n, int trial, Metrics m) {
        try (PrintWriter w = new PrintWriter(new FileWriter(CSV, true))) {
            w.printf("%s,%d,%d,%d,%d,%d%n",
                    algo,
                    n,
                    trial,
                    m.elapsedMillis(),
                    m.maxRecursionDepth,
                    m.comparisons);
        } catch (IOException e) {
            System.err.println("Error writing CSV: " + e.getMessage());
        }
    }
}
