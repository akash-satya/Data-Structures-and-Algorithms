import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * Your implementation of various sorting algorithms.
 *
 * @author Akash Satya
 * @version 1.0
 * @userid asatya8
 * @GTID 903896933
 *
 * Collaborators: None
 *
 * Resources: None
 * 
 * By typing 'I agree' below, you are agreeing that this is your
 * own work and that you are responsible for all the contents of 
 * this file. If this is left blank, this homework will receive a zero.
 * 
 * Agree Here: I agree
 */
public class Sorting {
    
    /**
     * Method for swapping two elements in the array
     * @param <T> data type to swap
     * @param arr array that has elements swapped
     * @param j right element to swap
     * @param k left element to swap
     */
    private static <T> void swap(T[] arr, int j, int k) {
        T temp = arr[j];
        arr[j] = arr[k];
        arr[k] = temp;
    }

    /**
     * Implement insertion sort.
     *
     * It should be:
     * in-place
     * stable
     * adaptive
     *
     * Have a worst case running time of:
     * O(n^2)
     *
     * And a best case running time of:
     * O(n)
     *
     * @param <T>        data type to sort
     * @param arr        the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @throws java.lang.IllegalArgumentException if the array or comparator is
     *                                            null
     */
    public static <T> void insertionSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("Comparator or array is null");
        }
        for (int i = 1; i < arr.length; i++) {
            int j = i;
            while (j > 0 && comparator.compare(arr[j - 1], arr[j]) > 0) {
                swap(arr, j, j - 1);
                j--;
            }
        }
        
    }

    /**
     * Implement cocktail sort.
     *
     * It should be:
     * in-place
     * stable
     * adaptive
     *
     * Have a worst case running time of:
     * O(n^2)
     *
     * And a best case running time of:
     * O(n)
     *
     * NOTE: See pdf for last swapped optimization for cocktail sort. You
     * MUST implement cocktail sort with this optimization
     *
     * @param <T>        data type to sort
     * @param arr        the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @throws java.lang.IllegalArgumentException if the array or comparator is
     *                                            null
     */
    public static <T> void cocktailSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("Comparator or array is null");
        }
    
        int start = 0;
        int end = arr.length - 1;
        boolean swapped = true;

        while (swapped) {
            swapped = false;
            int lastSwapped = start;

            for (int i = start; i < end; i++) {
                if (comparator.compare(arr[i], arr[i + 1]) > 0) {
                    swap(arr, i, i + 1);
                    swapped = true;
                    lastSwapped = i;
                }
            }
            end = lastSwapped;

            if (!swapped) {
                break;
            }
    
            swapped = false;
            lastSwapped = end;
    
            for (int i = end; i > start; i--) {
                if (comparator.compare(arr[i - 1], arr[i]) > 0) {
                    swap(arr, i - 1, i);
                    swapped = true;
                    lastSwapped = i;
                }
            }
            start = lastSwapped;
        }
    }

    /**
     * Implement merge sort.
     *
     * It should be:
     * out-of-place
     * stable
     * not adaptive
     *
     * Have a worst case running time of:
     * O(n log n)
     *
     * And a best case running time of:
     * O(n log n)
     *
     * You can create more arrays to run merge sort, but at the end, everything
     * should be merged back into the original T[] which was passed in.
     *
     * When splitting the array, if there is an odd number of elements, put the
     * extra data on the right side.
     *
     * Hint: If two data are equal when merging, think about which subarray
     * you should pull from first
     *
     * @param <T>        data type to sort
     * @param arr        the array to be sorted
     * @param comparator the Comparator used to compare the data in arr
     * @throws java.lang.IllegalArgumentException if the array or comparator is
     *                                            null
     */
    public static <T> void mergeSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("Comparator or array is null");
        }
        if (arr.length < 2) {
            return;
        }
        mergeSortRecursive(arr, comparator);
    }

    /**
     * recursively create the subarrays
     * @param <T>        data type to sort
     * @param array      array to be merge sorted
     * @param comparator the Comparator used to compare the data in arr
     */
    private static <T> void mergeSortRecursive(T[] array, Comparator<T> comparator) {
        if (array.length <= 1) {
            return;
        }
        
        int length = array.length;
        int midIdx = length / 2;
        
        T[] leftArray = (T[]) new Object[midIdx];
        T[] rightArray = (T[]) new Object[length - midIdx];
        
        
        for (int i = 0; i < midIdx; i++) {
            leftArray[i] = array[i];
        }
        for (int i = midIdx; i < length; i++) {
            rightArray[i - midIdx] = array[i];
        }
        
        
        mergeSortRecursive(leftArray, comparator);
        mergeSortRecursive(rightArray, comparator);
        
        
        merge(array, leftArray, rightArray, comparator);
    }
    
    /**
     * method to sort the subarrays
     * @param <T>        data type to sort
     * @param arr        the array to be sorted
     * @param leftArray  left array to be sorted
     * @param rightArray right array to be sorted
     * @param comparator the Comparator used to compare the data in arr
     */
    private static <T> void merge(T[] arr, T[] leftArray, T[] rightArray, Comparator<T> comparator) {
        int leftIdx = 0;
        int rightIdx = 0;
        int currIdx = 0;

        while (leftIdx < leftArray.length && rightIdx < rightArray.length) {
            if (comparator.compare(leftArray[leftIdx], rightArray[rightIdx]) <= 0) {
                arr[currIdx++] = leftArray[leftIdx++];
            } else {
                arr[currIdx++] = rightArray[rightIdx++];
            }
        }
        

        while (leftIdx < leftArray.length) {
            arr[currIdx++] = leftArray[leftIdx++];
        }
        

        while (rightIdx < rightArray.length) {
            arr[currIdx++] = rightArray[rightIdx++];
        }
    }

    /**
     * Implement kth select.
     *
     * Use the provided random object to select your pivots. For example if you
     * need a pivot between a (inclusive) and b (exclusive) where b > a, use
     * the following code:
     *
     * int pivotIndex = rand.nextInt(b - a) + a;
     *
     * If your recursion uses an inclusive b instead of an exclusive one,
     * the formula changes by adding 1 to the nextInt() call:
     *
     * int pivotIndex = rand.nextInt(b - a + 1) + a;
     *
     * It should be:
     * in-place
     *
     * Have a worst case running time of:
     * O(n^2)
     *
     * And a best case running time of:
     * O(n)
     *
     * You may assume that the array doesn't contain any null elements.
     *
     * Make sure you code the algorithm as you have been taught it in class.
     * There are several versions of this algorithm and you may not get full
     * credit if you do not implement the one we have taught you!
     *
     * @param <T>        data type to sort
     * @param k          the index to retrieve data from + 1 (due to
     *                   0-indexing) if the array was sorted; the 'k' in "kth
     *                   select"; e.g. if k == 1, return the smallest element
     *                   in the array
     * @param arr        the array that should be modified after the method
     *                   is finished executing as needed
     * @param comparator the Comparator used to compare the data in arr
     * @param rand       the Random object used to select pivots
     * @return the kth smallest element
     * @throws java.lang.IllegalArgumentException if the array or comparator
     *                                            or rand is null or k is not
     *                                            in the range of 1 to arr
     *                                            .length
     */
    public static <T> T kthSelect(int k, T[] arr, Comparator<T> comparator,
                                  Random rand) {

        if (arr == null || comparator == null || rand == null) {
            throw new IllegalArgumentException("Input parameters cannot be null");
        }
        if (k < 1 || k > arr.length) {
            throw new IllegalArgumentException("k must be between 1 and array length");
        }

        return quickSelect(arr, 0, arr.length - 1, k - 1, comparator, rand);
    }

    /**
     * quickselect method
     * @param start the start index
     * @param end the end index
     * @param <T>        data type to sort
     * @param k          the index to retrieve data from + 1 (due to
     *                   0-indexing) if the array was sorted; the 'k' in "kth
     *                   select"; e.g. if k == 1, return the smallest element
     *                   in the array
     * @param arr        the array that should be modified after the method
     *                   is finished executing as needed
     * @param comparator the Comparator used to compare the data in arr
     * @param rand       the Random object used to select pivots
     * @return the kth smallest element
     */
    private static <T> T quickSelect(T[] arr, int start, int end, int k, 
                                 Comparator<T> comparator, Random rand) {
        int pivotIndex = rand.nextInt(end - start + 1) + start;
        swap(arr, start, pivotIndex);

        int j = start;
        for (int i = start + 1; i <= end; i++) {
            if (comparator.compare(arr[i], arr[start]) < 0) {
                j++;
                swap(arr, i, j);
            }
        }
        swap(arr, start, j);


        if (j == k) {
            return arr[j];
        } else if (j > k) {
            return quickSelect(arr, start, j - 1, k, comparator, rand);
        }   else {
            return quickSelect(arr, j + 1, end, k, comparator, rand);
        }
    }

    /**
     * Implement LSD (least significant digit) radix sort.
     *
     * Make sure you code the algorithm as you have been taught it in class.
     * There are several versions of this algorithm and you may not get full
     * credit if you do not implement the one we have taught you!
     *
     * Remember you CANNOT convert the ints to strings at any point in your
     * code! Doing so may result in a 0 for the implementation.
     *
     * It should be:
     * out-of-place
     * stable
     * not adaptive
     *
     * Have a worst case running time of:
     * O(kn)
     *
     * And a best case running time of:
     * O(kn)
     *
     * You are allowed to make an initial O(n) passthrough of the array to
     * determine the number of iterations you need. The number of iterations
     * can be determined using the number with the largest magnitude.
     *
     * At no point should you find yourself needing a way to exponentiate a
     * number; any such method would be non-O(1). Think about how how you can
     * get each power of BASE naturally and efficiently as the algorithm
     * progresses through each digit.
     *
     * Refer to the PDF for more information on LSD Radix Sort.
     *
     * You may use ArrayList or LinkedList if you wish, but it may only be
     * used inside radix sort and any radix sort helpers. Do NOT use these
     * classes with other sorts. However, be sure the List implementation you
     * choose allows for stability while being as efficient as possible.
     *
     * Do NOT use anything from the Math class except Math.abs().
     *
     * @param arr the array to be sorted
     * @throws java.lang.IllegalArgumentException if the array is null
     */
    public static void lsdRadixSort(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Input array cannot be null.");
        }

        if (arr.length <= 1) {
            return;
        }


        int maxMagnitude = 0;
        for (int num : arr) {
            int absVal = Math.abs(num);
            if (absVal > maxMagnitude) {
                maxMagnitude = absVal;
            }
        }


        if (maxMagnitude == 0) {
            return;
        }


        int k = 0;
        while (maxMagnitude > 0) {
            maxMagnitude /= 10;
            k++;
        }

        int base = 1;

        for (int pass = 0; pass < k; pass++) {

            List<Integer>[] buckets = new ArrayList[19];
            for (int i = 0; i < 19; i++) {
                buckets[i] = new ArrayList<>();
            }

            for (int num : arr) {
                int digit = (num / base) % 10; 
                int bucketIndex = digit + 9;  
                buckets[bucketIndex].add(num);
            }

            int index = 0;
            for (List<Integer> bucket : buckets) {
                for (int val : bucket) {
                    arr[index++] = val;
                }
            }

            base *= 10;
        }
    }




    /**
     * Implement heap sort.
     *
     * It should be:
     * out-of-place
     * unstable
     * not adaptive
     *
     * Have a worst case running time of:
     * O(n log n)
     *
     * And a best case running time of:
     * O(n log n)
     *
     * Use java.util.PriorityQueue as the heap. Note that in this
     * PriorityQueue implementation, elements are removed from smallest
     * element to largest element.
     *
     * Initialize the PriorityQueue using its build heap constructor (look at
     * the different constructors of java.util.PriorityQueue).
     *
     * Return an int array with a capacity equal to the size of the list. The
     * returned array should have the elements in the list in sorted order.
     *
     * @param data the data to sort
     * @return the array with length equal to the size of the input list that
     * holds the elements from the list is sorted order
     * @throws java.lang.IllegalArgumentException if the data is null
     */
    public static int[] heapSort(List<Integer> data) {
        if (data == null) {
            throw new IllegalArgumentException("Comparator or array is null");
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>(data);
        int[] sorted = new int[data.size()];
        for (int i = 0; i < data.size(); i++) {
            sorted[i] = queue.remove();
        }
        return sorted;
    }
}
