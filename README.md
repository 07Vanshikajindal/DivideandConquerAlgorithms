Algorithm Assignment Report

roject Overview
This project implements and analyzes four classic divide-and-conquer algorithms: MergeSort, QuickSort, **Deterministic Select (Median of Medians), and Closest Pair of Points.  
The goal is to implement them with safe recursion patterns, analyze their running-time recurrences using the Master Theorem and Akra–Bazzi intuition, and validate theory against actual measurements.

---
Implemented Algorithms

1. MergeSort
- Recurrence: T(n) = 2T(n/2) + O(n)
- Complexity: O(n log n)
- Notes: Recursion depth = log2n, comparisons ≈ n log n.

2. QuickSort
- Recurrence (avg): T(n) = 2T(n/2) + O(n)
- Complexity: O(n log n) average, O(n2) worst-case
- Notes: With random pivoting, depth ≈ log2n.

3. Deterministic Select (Median of Medians)
- Recurrence: T(n) = T(n/5) + T(7n/10) + O(n)
- Complexity: O(n)
- Notes: Guarantees linear time, recursion depth constant.

4. Closest Pair of Points (Divide & Conquer)
- Recurrence: T(n) = 2T(n/2) + O(n)
- Complexity: O(n log n)
- Notes: Uses strip-based merge step.

---

Metrics & Architecture Notes
- Time (ms): runtime per trial.
- Max Depth:recursion stack depth observed.
- Comparisons: number of element comparisons (or geometric distance checks).
- CSV results were exported and visualized in Excel (time vs n, depth vs n, comparisons).

---

Measurements & Observations

MergeSort
- n=10,000 ~127k comparisons, depth = 11.
- Matches theory: O(n log n) comparisons, log2n recursion depth.

QuickSort
- n=10,000 ~28k comparisons, depth = 7-8.
- Runs faster than MergeSort in practice (fewer comparisons), while still O(n log n).
- Depth ~log2n as expected.

Deterministic Select
- n=20,000 ~40k comparisons, depth = 1.
- Perfectly consistent with O(n) theory and constant recursion depth.

Closest Pair
- n=2,000  ~3.5k comparisons, depth = 10.
- Scales as O(n log n), depth ~log2n as predicted.

---

Testing Strategy
- Each algorithm tested with JUnit.
- Strategies:
  - Random arrays for stress testing.
  - Edge cases: empty arrays, single element, duplicates, negative numbers.
  - Validation against brute force (Closest Pair) or Java's built-in sort (sorting algorithms).
- Tests confirm correctness and stability across different scenarios.

---

 Git Workflow & Commit Storyline
- Initial commit with base project structure.
- Separate commits for each algorithm implementation.
- Added unit tests for each algorithm.
- CSV logging + measurements commit.
- README and report documentation commit.

---

Summary: Theory vs Measurements
MergeSort: O(n log n), confirmed by ~127k comparisons at n=10k, depth log2n.
QuickSort: O(n log n) average, observed fewer comparisons than MergeSort in practice, confirming practical speed advantage.
Deterministic Select: O(n), measurements show linear scaling with constant recursion depth = 1.
Closest Pair:  O(n log n), measurements scale consistently with theory.

Overall, the experiments validate the asymptotic analysis, with measurements closely matching theoretical expectations.

---

