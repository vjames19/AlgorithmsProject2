package com.github.vjames19;

import java.util.Arrays;

/**
 * Created by vjames19 on 3/25/15.
 */
public class MergeSort {

    public static void mergeSort(Comparable[] a, final int SELECTION_SORT_THRESHOLD) {
        Comparable[] temp = new Comparable[a.length];
        mergeSort(a, temp, 0, a.length - 1, SELECTION_SORT_THRESHOLD);
    }

    public static void mergeSort(Comparable[] a, Comparable[] temp, int low, int high, final int SELECTION_SORT_THRESHOLD) {
        if (high <= low) return;

        // Use selection sort if the partition size < SELECTION_SORT_THRESHOLD
        if (high - low < SELECTION_SORT_THRESHOLD) {
            for (int i = low; i < high; i++) {
                int minIndex = i;
                for (int j = i + 1; j <= high; j++) {
                    if (a[j].compareTo(a[minIndex]) < 0) {
                        minIndex = j;
                    }
                }

                if (minIndex != i) {
                    Comparable t = a[i];
                    a[i] = a[minIndex];
                    a[minIndex] = t;
                }

            }
            return;
        }

        int mid = (low + high) >>> 1;
        mergeSort(a, temp, low, mid, SELECTION_SORT_THRESHOLD);
        mergeSort(a, temp, mid + 1, high, SELECTION_SORT_THRESHOLD);
        merge(a, temp, low, mid, high);
    }

    public static void merge(Comparable[] a, Comparable[] temp, int low, int mid, int high) {
        // Copy elements to temp;
        for (int i = low; i <= high; i++) {
            temp[i] = a[i];
        }

        // merge the sorted halves in to a.
        int i = low, j = mid + 1;
        for (int k = low; k <= high; k++) {
            if (i > mid) { // finished merging the lower half.
                a[k] = temp[j++];
            } else if (j > high) { // finished merging the upper half.
                a[k] = temp[i++];
            } else if (temp[j].compareTo(temp[i]) < 0) { // The element in the upper half is smaller, put it first.
                a[k] = temp[j++];
            } else { // the element in the lower half is smaller.
                a[k] = temp[i++];
            }
        }
    }

    public static void selectionSort(Comparable[] a, int low, int high) {
        for (int i = low; i < high; i++) {
            int minIndex = i;
            for (int j = i + 1; j <= high; j++) {
                if (a[j].compareTo(a[minIndex]) < 0) {
                    minIndex = j;
                }
            }

            if (minIndex != i) {
                Comparable t = a[i];
                a[i] = a[minIndex];
                a[minIndex] = t;
            }

        }
    }

    /**
     * Executes the merge sort algorithm with different selection sort threshold to gather data and conclude which
     * threshold works best.
     */
    public static void main(String[] args) {
        Comparable[] ints = generateRandom(1000000);
        mergeSort(ints, 10);

        System.out.println("selection sort threshold, time(ns)");
        Timer timer = new Timer();
        long minTime = Long.MAX_VALUE;
        int bestThreshold = -1;
        for (int threshold = 0; threshold < 30; threshold++) {
            Comparable[] copy = Arrays.copyOf(ints, ints.length);

            timer.start();
            mergeSort(copy, threshold);
            long took = timer.stop();
            if (took < minTime) {
                minTime = took;
                bestThreshold = threshold;
            }
            System.out.printf("%d, %d\n", threshold, took);
        }

        System.out.printf("Merge sort with lowest time %d ns had a threshold of %d \n", minTime, bestThreshold);

    }

    public static Comparable[] generateRandom(int n) {
        Comparable[] ints = new Comparable[n];
        for (int i = 0; i < n; i++) {
            ints[i] = (int) (Math.random() * 1000);
        }

        return ints;
    }

    public static boolean isSorted(Comparable[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            if (a[i].compareTo(a[i + 1]) > 0) {
                return false;
            }
        }

        return true;
    }
}
