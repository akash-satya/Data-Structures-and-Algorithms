import org.junit.Before;
import org.junit.Test;
import java.util.*;

import static org.junit.Assert.*;

public class sortingtests {

    private static final int TIMEOUT = 1000;
    private static final int LARGE_SIZE = 10000;

    private IntegerWrapper[] integers;
    private IntegerWrapper[] sortedIntegers;
    private ComparatorPlus<IntegerWrapper> comp;

    @Before
    public void setUp() {
        integers = new IntegerWrapper[]{
                new IntegerWrapper(5),
                new IntegerWrapper(4),
                new IntegerWrapper(2),
                new IntegerWrapper(3),
                new IntegerWrapper(6),
                new IntegerWrapper(1),
                new IntegerWrapper(0),
                new IntegerWrapper(7)
        };

        sortedIntegers = Arrays.stream(integers)
                .sorted(Comparator.comparing(IntegerWrapper::getValue))
                .toArray(IntegerWrapper[]::new);

        comp = IntegerWrapper.getComparator();
    }

    //  Basic Sort Tests
    @Test(timeout = TIMEOUT)
    public void testInsertionSort() {
        Sorting.insertionSort(integers, comp);
        assertArrayEquals("Insertion sort failed", sortedIntegers, integers);
    }

    @Test(timeout = TIMEOUT)
    public void testCocktailSort() {
        Sorting.cocktailSort(integers, comp);
        assertArrayEquals("Cocktail sort failed", sortedIntegers, integers);
    }

    @Test(timeout = TIMEOUT)
    public void testMergeSort() {
        Sorting.mergeSort(integers, comp);
        assertArrayEquals("Merge sort failed", sortedIntegers, integers);
    }

    //  LSD Radix Sort
    @Test(timeout = TIMEOUT)
    public void testLsdRadixSort() {
        int[] array = {54, -23, 0, 5, -1000, 42};
        int[] expected = {-1000, -23, 0, 5, 42, 54};
        Sorting.lsdRadixSort(array);
        assertArrayEquals("LSD Radix failed", expected, array);
    }

    //  Null Handling and Edge Cases
    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testNullArray() {
        Sorting.insertionSort(null, comp);
    }


    //  Stability Check
    @Test(timeout = TIMEOUT)
    public void testStability() {
        IntegerWrapper[] stableArray = {
                new IntegerWrapper(1),
                new IntegerWrapper(2),
                new IntegerWrapper(2),
                new IntegerWrapper(3),
                new IntegerWrapper(3)
        };

        Sorting.insertionSort(stableArray, comp);
        assertEquals("Failed stability check", stableArray[1], stableArray[2]);
        assertEquals("Failed stability check", stableArray[3], stableArray[4]);
    }

    //  Mixed Positive and Negative Values
    @Test(timeout = TIMEOUT)
    public void testMixedNumbers() {
        IntegerWrapper[] mixed = {
                new IntegerWrapper(-50),
                new IntegerWrapper(100),
                new IntegerWrapper(-10),
                new IntegerWrapper(0),
                new IntegerWrapper(99)
        };

        IntegerWrapper[] expected = {
                new IntegerWrapper(-50),
                new IntegerWrapper(-10),
                new IntegerWrapper(0),
                new IntegerWrapper(99),
                new IntegerWrapper(100)
        };

        Sorting.mergeSort(mixed, comp);
        assertArrayEquals("Failed on mixed numbers", expected, mixed);
    }

    //  Boundary Values
    @Test(timeout = TIMEOUT)
    public void testBoundaryValues() {
        IntegerWrapper[] boundary = {
                new IntegerWrapper(Integer.MIN_VALUE),
                new IntegerWrapper(0),
                new IntegerWrapper(Integer.MAX_VALUE),
                new IntegerWrapper(-1),
                new IntegerWrapper(1)
        };

        IntegerWrapper[] expected = {
                new IntegerWrapper(Integer.MIN_VALUE),
                new IntegerWrapper(-1),
                new IntegerWrapper(0),
                new IntegerWrapper(1),
                new IntegerWrapper(Integer.MAX_VALUE)
        };

        Sorting.mergeSort(boundary, comp);
        assertArrayEquals("Failed on boundary values", expected, boundary);
    }

    //  Large Dataset (Stress Test)
    @Test(timeout = TIMEOUT)
    public void testLargeDataset() {
        Random random = new Random(42);

        IntegerWrapper[] largeArray = new IntegerWrapper[LARGE_SIZE];
        IntegerWrapper[] expected = new IntegerWrapper[LARGE_SIZE];

        for (int i = 0; i < LARGE_SIZE; i++) {
            int val = random.nextInt();
            largeArray[i] = new IntegerWrapper(val);
            expected[i] = new IntegerWrapper(val);
        }

        Arrays.sort(expected, Comparator.comparing(IntegerWrapper::getValue));

        Sorting.mergeSort(largeArray, comp);
        assertArrayEquals("Failed on large dataset", expected, largeArray);
    }

    //  Repeated Randomized Testing for Consistency
    @Test(timeout = TIMEOUT)
    public void testRandomizedConsistency() {
        Random rand = new Random(42);

        for (int i = 0; i < 100; i++) {
            int size = rand.nextInt(100) + 10;
            IntegerWrapper[] arr = new IntegerWrapper[size];

            for (int j = 0; j < size; j++) {
                arr[j] = new IntegerWrapper(rand.nextInt(10000) - 5000);
            }

            IntegerWrapper[] expected = Arrays.copyOf(arr, arr.length);
            Arrays.sort(expected, Comparator.comparing(IntegerWrapper::getValue));

            Sorting.mergeSort(arr, comp);
            assertArrayEquals("Failed on random test run #" + i, expected, arr);
        }
    }

    //  Benchmarking Execution Time
    @Test(timeout = TIMEOUT)
    public void testPerformance() {
        Random rand = new Random();
        IntegerWrapper[] largeArray = new IntegerWrapper[LARGE_SIZE];

        for (int i = 0; i < LARGE_SIZE; i++) {
            largeArray[i] = new IntegerWrapper(rand.nextInt(100000));
        }

        long startTime = System.currentTimeMillis();
        Sorting.mergeSort(largeArray, comp);
        long duration = System.currentTimeMillis() - startTime;

        System.out.println("MergeSort Time for " + LARGE_SIZE + " elements: " + duration + "ms");
        assertTrue("Performance regression detected!", duration < 500);
    }


    @Test(timeout = TIMEOUT)
    public void testSortInvariant() {
        Random rand = new Random();

        for (int i = 0; i < 100; i++) {
            int size = rand.nextInt(100) + 1;
            IntegerWrapper[] arr = new IntegerWrapper[size];

            for (int j = 0; j < size; j++) {
                arr[j] = new IntegerWrapper(rand.nextInt(10000) - 5000);
            }

            Sorting.mergeSort(arr, comp);

            for (int k = 0; k < arr.length - 1; k++) {
                assertTrue("Failed sort invariant at index " + k,
                        arr[k].getValue() <= arr[k + 1].getValue());
            }
        }
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
}
