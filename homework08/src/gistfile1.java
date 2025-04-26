import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * This is a basic set of unit tests for Sorting.
 *
 * Passing these tests doesn't guarantee any grade on these assignments. These
 * student JUnits that we provide should be thought of as a sanity check to
 * help you get started on the homework and writing JUnits in general.
 *
 * We highly encourage you to write your own set of JUnits for each homework
 * to cover edge cases you can think of for each data structure. Your code must
 * work correctly and efficiently in all cases, which is why it's important
 * to write comprehensive tests to cover as many cases as possible.
 *
 * @author CS 1332 TAs, David Gu
 * @version 1.0
 */
public class gistfile1 {

    private static final int TIMEOUT = 200;
    private IntegerWrapper[] integers;
    private IntegerWrapper[] sortedIntegers;
    private ComparatorPlus<IntegerWrapper> comp;

    private IntegerWrapper[] zeroArray;
    private IntegerWrapper[] singleArray;

    private IntegerWrapper[] randomArray;
    private IntegerWrapper[] sortedRandomArray;

    private IntegerWrapper[] stableIntegers;
    private IntegerWrapper[] sortedStableIntegers;

    private IntegerWrapper[] adaptiveIntegersBad;
    private ComparatorPlus<IntegerWrapper> compBad;
    private IntegerWrapper[] adaptiveIntegersGood;
    private ComparatorPlus<IntegerWrapper> compGood;

    @Before
    public void setUp() {
        /*
            Initial array:
                index 0: 5
                index 1: 4
                index 2: 2
                index 3: 3
                index 4: 6
                index 5: 1
                index 6: 0
                index 7: 7
         */

        /*
            Sorted array:
                index 0: 0
                index 1: 1
                index 2: 2
                index 3: 3
                index 4: 4
                index 5: 5
                index 6: 6
                index 7: 7
         */

        integers = new IntegerWrapper[8];
        integers[0] = new IntegerWrapper(5);
        integers[1] = new IntegerWrapper(4);
        integers[2] = new IntegerWrapper(2);
        integers[3] = new IntegerWrapper(3);
        integers[4] = new IntegerWrapper(6);
        integers[5] = new IntegerWrapper(1);
        integers[6] = new IntegerWrapper(0);
        integers[7] = new IntegerWrapper(7);

        sortedIntegers = new IntegerWrapper[integers.length];
        for (int i = 0; i < sortedIntegers.length; i++) {
            sortedIntegers[i] = new IntegerWrapper(i);
        }

        comp = IntegerWrapper.getComparator();

        // Zero array
        zeroArray = new IntegerWrapper[0];

        // Single element array
        singleArray = new IntegerWrapper[1];
        singleArray[0] = new IntegerWrapper(0);

        // Random 100 array
        Random rand = new Random();
        randomArray = new IntegerWrapper[1000];
        for (int i = 0; i < randomArray.length; i++) {
            randomArray[i] = new IntegerWrapper(rand.nextInt());
        }
        sortedRandomArray = Arrays.copyOf(randomArray, randomArray.length);
        ComparatorPlus<IntegerWrapper> comp = IntegerWrapper.getComparator();
        Arrays.sort(sortedRandomArray, comp);

        // Stable array
        stableIntegers = new IntegerWrapper[8];
        stableIntegers[0] = new IntegerWrapper(5);
        stableIntegers[1] = new IntegerWrapper(4);
        stableIntegers[2] = new IntegerWrapper(3);
        stableIntegers[3] = new IntegerWrapper(3);
        stableIntegers[4] = new IntegerWrapper(6);
        stableIntegers[5] = new IntegerWrapper(3);
        stableIntegers[6] = new IntegerWrapper(0);
        stableIntegers[7] = new IntegerWrapper(5);

        sortedStableIntegers = new IntegerWrapper[8];
        sortedStableIntegers[0] = stableIntegers[6];
        sortedStableIntegers[1] = stableIntegers[2];
        sortedStableIntegers[2] = stableIntegers[3];
        sortedStableIntegers[3] = stableIntegers[5];
        sortedStableIntegers[4] = stableIntegers[1];
        sortedStableIntegers[5] = stableIntegers[0];
        sortedStableIntegers[6] = stableIntegers[7];
        sortedStableIntegers[7] = stableIntegers[4];

        // Adaptive array
        adaptiveIntegersBad = new IntegerWrapper[1000];
        for (int i = 0; i < adaptiveIntegersBad.length; i++) {
            adaptiveIntegersBad[1000 - 1 - i] = new IntegerWrapper(i);
        }
        compBad = IntegerWrapper.getComparator();

        adaptiveIntegersGood = new IntegerWrapper[1000];
        for (int i = 0; i < adaptiveIntegersGood.length; i++) {
            adaptiveIntegersGood[i] = new IntegerWrapper(i);
        }
        compGood = IntegerWrapper.getComparator();
    }

    private void assertStability() {
        for (int i = 0; i < stableIntegers.length; i++) {
            assertTrue(stableIntegers[i] == sortedStableIntegers[i]);
        }
    }

    private void assertAdaptive(boolean isAdaptive) {
        assertArrayEquals(adaptiveIntegersBad, adaptiveIntegersGood);
        double percentage = (double) compGood.getCount() / compBad.getCount();
        if (isAdaptive) {
            assertTrue(percentage <= 0.1);
        } else {
            assertTrue(percentage >= 0.9);
        }
    }

    private void myTests(SortingAlgorithm sorter, boolean isStable, boolean isAdaptive) {
        // Test on zero array
        sorter.sort(zeroArray, comp);
        assertArrayEquals(new IntegerWrapper[0], zeroArray);

        // Test on single element array
        sorter.sort(singleArray, comp);
        assertArrayEquals(new IntegerWrapper[]{new IntegerWrapper(0)}, singleArray);

        // Test on random 1000 array
        sorter.sort(randomArray, comp);
        assertArrayEquals(sortedRandomArray, randomArray);

        // Test stability
        sorter.sort(stableIntegers, comp);
        if (isStable)
            assertStability();

        // Test adaptiveness
        sorter.sort(adaptiveIntegersBad, compBad);
        sorter.sort(adaptiveIntegersGood, compGood);
        assertAdaptive(isAdaptive);
    }

    @Test(timeout = TIMEOUT)
    public void testInsertionSort() {
        Sorting.insertionSort(integers, comp);
        assertArrayEquals(sortedIntegers, integers);
        assertTrue("Number of comparisons: " + comp.getCount(),
            comp.getCount() <= 19 && comp.getCount() != 0);

        SortingAlgorithm sorter = Sorting::insertionSort;
        myTests(sorter, true, true);
    }

    @Test(timeout = TIMEOUT)
    public void testCocktailSort() {
        Sorting.cocktailSort(integers, comp);
        assertArrayEquals(sortedIntegers, integers);
        assertTrue("Number of comparisons: " + comp.getCount(),
            comp.getCount() <= 21 && comp.getCount() != 0);

        SortingAlgorithm sorter = Sorting::cocktailSort;
        myTests(sorter, true, true);
    }

    @Test(timeout = TIMEOUT)
    public void testMergeSort() {
        Sorting.mergeSort(integers, comp);
        assertArrayEquals(sortedIntegers, integers);
        assertTrue("Number of comparisons: " + comp.getCount(),
            comp.getCount() <= 15 && comp.getCount() != 0);

        SortingAlgorithm sorter = Sorting::mergeSort;
        myTests(sorter, true, false);
    }

    @Test(timeout = TIMEOUT)
    public void testKthSelect() {
        int randomSeed = 4;
        assertEquals(new IntegerWrapper(0), Sorting.kthSelect(1,
                integers, comp, new Random(randomSeed)));
        assertTrue("Number of comparisons: " + comp.getCount(),
            comp.getCount() <= 8 && comp.getCount() != 0);

        IntegerWrapper[] array = new IntegerWrapper[1000];
        Random rand = new Random();
        for (int i = 1; i <= array.length; i++) {
            // Initiate array
            for (int j = 0; j < array.length; j++) {
                array[j] = new IntegerWrapper(rand.nextInt());
            }

            // Find kth element
            comp = IntegerWrapper.getComparator();
            IntegerWrapper[] copy = Arrays.copyOf(array, array.length);
            IntegerWrapper kth = Sorting.kthSelect(i, array, comp, new Random(randomSeed));
            Arrays.sort(copy, comp);
            assertEquals(copy[i - 1], array[i - 1]);
            assertEquals(kth, array[i - 1]);
        }
    }

    @Test(timeout = TIMEOUT)
    public void testLsdRadixSort() {
        int[] unsortedArray = new int[]{54, 28, 58, 84, 20, 122, -85, 3};
        int[] sortedArray = new int[]{-85, 3, 20, 28, 54, 58, 84, 122};
        Sorting.lsdRadixSort(unsortedArray);
        assertArrayEquals(sortedArray, unsortedArray);

        // Test on zero array
        int[] zeroArray = new int[0];
        Sorting.lsdRadixSort(zeroArray);
        assertArrayEquals(new int[0], zeroArray);

        // Test on single element array
        int[] singleArray = new int[]{0};
        Sorting.lsdRadixSort(singleArray);
        assertArrayEquals(new int[]{0}, singleArray);

        // Test on random 1000 array
        int[] randomArray = new int[1000];
        Random rand = new Random();
        for (int i = 0; i < randomArray.length; i++) {
            randomArray[i] = rand.nextInt();
        }
        int[] sortedRandomArray = Arrays.copyOf(randomArray, randomArray.length);
        Arrays.sort(sortedRandomArray);
        Sorting.lsdRadixSort(randomArray);
        assertArrayEquals(sortedRandomArray, randomArray);
    }

    @Test(timeout = TIMEOUT)
    public void testHeapSort() {
        int[] unsortedArray = new int[] {54, 28, 58, 84, 20, 122, -85, 3};
        List<Integer> unsortedList = new ArrayList<>();
        for (int i : unsortedArray) {
            unsortedList.add(i);
        }
        int[] sortedArray = new int[] {-85, 3, 20, 28, 54, 58, 84, 122};
        int[] actualArray = Sorting.heapSort(unsortedList);
        assertArrayEquals(sortedArray, actualArray);

        List<Integer> zeroList = new ArrayList<>();
        int[] zeroArray = new int[0];
        assertArrayEquals(zeroArray, Sorting.heapSort(zeroList));

        List<Integer> singleList = new ArrayList<>();
        singleList.add(0);
        int[] singleArray = new int[] {0};
        assertArrayEquals(singleArray, Sorting.heapSort(singleList));

        List<Integer> randomList = new ArrayList<>();
        Random rand = new Random();
        int[] randomArray = new int[1000];
        for (int i = 0; i < randomArray.length; i++) {
            randomArray[i] = rand.nextInt();
            randomList.add(randomArray[i]);
        }
        int[] sortedRandomArray = Arrays.copyOf(randomArray, randomArray.length);
        Arrays.sort(sortedRandomArray);
        int[] actualRandomArray = Sorting.heapSort(randomList);
        assertArrayEquals(sortedRandomArray, actualRandomArray);
    }

    /**
     * Class for testing proper sorting.
     */
    private static class IntegerWrapper {
        private Integer value;

        /**
         * Create an IntegerWrapper.
         *
         * @param value integer value
         */
        public IntegerWrapper(Integer value) {
            this.value = value;
        }

        /**
         * Get the value
         *
         * @return value of the integer
         */
        public Integer getValue() {
            return value;
        }

        /**
         * Set the value of the IntegerWrapper.
         *
         * @param value the new value
         */
        public void setValue(Integer value) {
            this.value = value;
        }

        /**
         * Create a comparator that compares the wrapped values.
         *
         * @return comparator that compares the wrapped values
         */
        public static ComparatorPlus<IntegerWrapper> getComparator() {
            return new ComparatorPlus<>() {
                @Override
                public int compare(IntegerWrapper int1,
                                   IntegerWrapper int2) {
                    incrementCount();
                    return int1.getValue().compareTo(int2.getValue());
                }
            };
        }

        @Override
        public String toString() {
            return "Value: " + value;
        }

        @Override
        public boolean equals(Object other) {
            if (other == null) {
                return false;
            }
            if (this == other) {
                return true;
            }
            return other instanceof IntegerWrapper
                && ((IntegerWrapper) other).value.equals(this.value);
        }
    }

    /**
     * Inner class that allows counting how many comparisons were made.
     */
    private abstract static class ComparatorPlus<T> implements Comparator<T> {
        private int count;

        /**
         * Get the number of comparisons made.
         *
         * @return number of comparisons made
         */
        public int getCount() {
            return count;
        }

        /**
         * Increment the number of comparisons made by one. Call this method in
         * your compare() implementation.
         */
        public void incrementCount() {
            count++;
        }
    }

    private interface SortingAlgorithm {
        void sort(IntegerWrapper[] arr, ComparatorPlus<IntegerWrapper> comp);
    }
}
