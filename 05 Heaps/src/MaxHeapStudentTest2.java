import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

/**
 * This is a basic set of unit tests for MaxHeap.
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
 * @author CS 1332 TAs, tkapoor30
 * @version 1.1
 */
public class MaxHeapStudentTest2 {

    private static final int TIMEOUT = 200;
    private MaxHeap<Integer> heap;
    public final int INITIAL_CAPACITY = 13;

    @Before
    public void setUp() {
        heap = new MaxHeap<>();
    }

    @Test(timeout = TIMEOUT)
    public void testInitialization() {
        assertEquals(0, heap.size());
        assertArrayEquals(new Comparable[MaxHeap.INITIAL_CAPACITY],
            heap.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testBuildHeap() {
        /*
                 2
               /   \
              1     4
             / \
            6   8

            ->

                 8
               /   \
              6     4
             / \
            2   1
        */

        ArrayList<Integer> data = new ArrayList<>();
        data.add(2);
        data.add(1);
        data.add(4);
        data.add(6);
        data.add(8);
        heap = new MaxHeap<>(data);

        assertEquals(5, heap.size());

        Integer[] expected = new Integer[11];
        expected[1] = 8;
        expected[2] = 6;
        expected[3] = 4;
        expected[4] = 2;
        expected[5] = 1;
        assertArrayEquals(expected, heap.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testAdd() {
        /*
                 4
        */
        heap.add(4);

        /*
                 6
               /
              4
        */
        heap.add(6);

        /*
                 6
               /   \
              4     2
        */
        heap.add(2);

        /*
                 8
               /   \
              6     2
             /
            4
        */
        heap.add(8);

        /*
                 8
               /   \
              6     2
             / \
            4   1
        */
        heap.add(1);

        assertEquals(5, heap.size());

        Integer[] expected = new Integer[MaxHeap.INITIAL_CAPACITY];
        expected[1] = 8;
        expected[2] = 6;
        expected[3] = 2;
        expected[4] = 4;
        expected[5] = 1;
        assertArrayEquals(expected, heap.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testRemove() {
        /*
                 8
               /   \
              6     4
             / \
            1   2
        */
        heap.add(4);
        heap.add(1);
        heap.add(6);
        heap.add(2);
        heap.add(8);
        assertEquals(5, heap.size());

        /*
                 6
               /   \
              2     4
             /
            1
        */
        assertEquals((Integer) 8, heap.remove());

        /*
                 4
               /   \
              2     1
        */
        assertEquals((Integer) 6, heap.remove());

        /*
                 2
               /
              1
        */
        assertEquals((Integer) 4, heap.remove());

        assertEquals(2, heap.size());

        Integer[] expected = new Integer[MaxHeap.INITIAL_CAPACITY];
        expected[1] = 2;
        expected[2] = 1;
        assertArrayEquals(expected, heap.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testGetMax() {
        /*
                 5
               /   \
              4     3
             / \
            2   1
         */

        heap.add(5);
        heap.add(4);
        heap.add(3);
        heap.add(2);
        heap.add(1);
        assertEquals(5, heap.size());

        assertSame(5, heap.getMax());
    }

    @Test(timeout = TIMEOUT)
    public void testIsEmpty() {
        assertTrue(heap.isEmpty());

        /*
                 5
               /   \
              4     3
             / \
            2   1
         */

        heap.add(5);
        heap.add(4);
        heap.add(3);
        heap.add(2);
        heap.add(1);
        assertEquals(5, heap.size());

        assertFalse(heap.isEmpty());
    }

    @Test(timeout = TIMEOUT)
    public void testClear() {
        /*
                 5
               /   \
              4     3
             / \
            2   1
         */

        heap.add(5);
        heap.add(4);
        heap.add(3);
        heap.add(2);
        heap.add(1);
        assertEquals(5, heap.size());

        heap.clear();

        assertTrue(heap.isEmpty());
        assertEquals(0, heap.size());
        assertArrayEquals(new Comparable[MaxHeap.INITIAL_CAPACITY],
            heap.getBackingArray());
    }


    // ### MY TESTS ###

    @Test
    public void testAddThrowsIllegalArgumentExceptionOnNullData() {
        assertEquals(0, heap.size());
        assertThrows(IllegalArgumentException.class, () -> {
            heap.add(null);
        });
        assertEquals(0, heap.size());
    }

    @Test
    public void testRemoveThrowsNoSuchElementExceptionOnEmptyHeap() {
        assertEquals(0, heap.size());
        assertThrows(NoSuchElementException.class, () -> {
            heap.remove();
        });
        assertEquals(0, heap.size());
    }

    @Test
    public void testGetMaxThrowsNoSuchElementExceptionOnEmptyHeap() {
        assertEquals(0, heap.size());
        assertThrows(NoSuchElementException.class, () -> {
            heap.getMax();
        });
        assertEquals(0, heap.size());
    }

    @Test
    public void testClearOnEmptyHeap() {
        heap.clear();
        assertEquals(0, heap.size());
        Object[] backingArray = heap.getBackingArray();
        assertEquals(INITIAL_CAPACITY, backingArray.length);
        assertNull(backingArray[0]);
        assertNull(backingArray[1]);
        assertNull(backingArray[2]);
        assertNull(backingArray[3]);
        assertNull(backingArray[4]);
        assertNull(backingArray[5]);
        assertNull(backingArray[6]);
        assertNull(backingArray[7]);
        assertNull(backingArray[8]);
        assertNull(backingArray[9]);
        assertNull(backingArray[10]);
        assertNull(backingArray[11]);
        assertNull(backingArray[12]);

    }

    @Test
    public void testResizeForAdd() {
        // add 12 elements
        heap.add(120);
        heap.add(140);
        heap.add(160);
        heap.add(20);
        heap.add(40);
        heap.add(60);
        heap.add(80);
        heap.add(100);
        heap.add(10);
        heap.add(5);
        heap.add(15);
        heap.add(25);
        assertEquals(12, heap.size());

        /*
                            160
                           /   \
                         120   140
                       /    \  /  \
                     100    40 60  80
                    /  \   /\  /
                   20  10 5 15 25
         */

        Object[] backingArray = heap.getBackingArray();
        assertEquals(INITIAL_CAPACITY, backingArray.length);
        assertNull(backingArray[0]);
        assertEquals(160, backingArray[1]);
        assertEquals(120, backingArray[2]);
        assertEquals(140, backingArray[3]);
        assertEquals(100, backingArray[4]);
        assertEquals(40, backingArray[5]);
        assertEquals(60, backingArray[6]);
        assertEquals(80, backingArray[7]);
        assertEquals(20, backingArray[8]);
        assertEquals(10, backingArray[9]);
        assertEquals(5, backingArray[10]);
        assertEquals(15, backingArray[11]);
        assertEquals(25, backingArray[12]);

        // resize needed
        heap.add(1);
        backingArray = heap.getBackingArray();
        // 13 * 2 = 26
        assertEquals(26, backingArray.length);
        assertEquals(1, backingArray[13]);
        assertEquals(13, heap.size());
    }

    @Test
    public void testMaxHeapConstructorThrowsIllegalArgumentExceptionForNullArrayList() {
        assertThrows(IllegalArgumentException.class, () -> {
            MaxHeap<Integer> maxHeap = new MaxHeap<>(null);
        });
    }

    @Test
    public void testMaxHeapConstructorThrowsIllegalArgumentExceptionForAnArrayListWithNullData() {
        ArrayList<Integer> dataList = new ArrayList<>();
        dataList.add(1);
        dataList.add(3);
        dataList.add(null);

        assertThrows(IllegalArgumentException.class, () -> {
            MaxHeap<Integer> maxHeap = new MaxHeap<>(dataList);
        });
    }

    @Test
    public void testMaxHeapConstructorCreatesAGoodHeap() {
        ArrayList<Integer> dataList = new ArrayList<>();
        dataList.add(160);
        dataList.add(120);
        dataList.add(140);
        dataList.add(100);
        dataList.add(40);
        dataList.add(60);
        dataList.add(80);
        dataList.add(20);
        dataList.add(10);
        dataList.add(5);
        assertEquals(10, dataList.size());

        heap = new MaxHeap<>(dataList);
        Object[] backingArray = heap.getBackingArray();

        assertEquals(21, backingArray.length);
        // 2 * 10 + 1 = 21

        assertNull(backingArray[0]);
        assertEquals(160, backingArray[1]);
        assertEquals(120, backingArray[2]);
        assertEquals(140, backingArray[3]);
        assertEquals(100, backingArray[4]);
        assertEquals(40, backingArray[5]);
        assertEquals(60, backingArray[6]);
        assertEquals(80, backingArray[7]);
        assertEquals(20, backingArray[8]);
        assertEquals(10, backingArray[9]);
        assertEquals(5, backingArray[10]);
        assertEquals(10, heap.size());

    }

    @Test
    public void testAdd2() {
        // add 12 elements
        heap.add(120);
        heap.add(140);
        heap.add(160);
        heap.add(20);
        heap.add(40);
        heap.add(60);
        heap.add(80);
        heap.add(100);
        heap.add(10);
        heap.add(5);
        heap.add(15);
        heap.add(25);
        assertEquals(12, heap.size());

        /*
                            160
                           /   \
                         120   140
                       /    \  /  \
                     100    40 60  80
                    /  \   /\  /
                   20  10 5 15 25
         */

        // check correct MaxHeap order
        Object[] backingArray = heap.getBackingArray();
        assertEquals(INITIAL_CAPACITY, backingArray.length);
        assertNull(backingArray[0]);
        assertEquals(160, backingArray[1]);
        assertEquals(120, backingArray[2]);
        assertEquals(140, backingArray[3]);
        assertEquals(100, backingArray[4]);
        assertEquals(40, backingArray[5]);
        assertEquals(60, backingArray[6]);
        assertEquals(80, backingArray[7]);
        assertEquals(20, backingArray[8]);
        assertEquals(10, backingArray[9]);
        assertEquals(5, backingArray[10]);
        assertEquals(15, backingArray[11]);
        assertEquals(25, backingArray[12]);


    }

    @Test
    public void testRemove2() {
        // add 12 elements
        heap.add(120);
        heap.add(140);
        heap.add(160);
        heap.add(20);
        heap.add(40);
        heap.add(60);
        heap.add(80);
        heap.add(100);
        heap.add(10);
        heap.add(5);
        heap.add(15);
        heap.add(25);
        assertEquals(12, heap.size());

        /*
                            160
                           /   \
                         120   140
                       /    \  /  \
                     100    40 60  80
                    /  \   /\  /
                   20  10 5 15 25
         */

        // check correct MaxHeap order
        Object[] backingArray = heap.getBackingArray();
        assertEquals(INITIAL_CAPACITY, backingArray.length);
        assertNull(backingArray[0]);
        assertEquals(160, backingArray[1]);
        assertEquals(120, backingArray[2]);
        assertEquals(140, backingArray[3]);
        assertEquals(100, backingArray[4]);
        assertEquals(40, backingArray[5]);
        assertEquals(60, backingArray[6]);
        assertEquals(80, backingArray[7]);
        assertEquals(20, backingArray[8]);
        assertEquals(10, backingArray[9]);
        assertEquals(5, backingArray[10]);
        assertEquals(15, backingArray[11]);
        assertEquals(25, backingArray[12]);

        Integer removedData = heap.remove();
        assertEquals((Integer) 160, removedData);

        /*
                            140
                           /   \
                         120   80
                       /    \  /  \
                     100    40 60  25
                    /  \   /\
                   20  10 5 15
         */

        backingArray = heap.getBackingArray();
        assertEquals(INITIAL_CAPACITY, backingArray.length);
        assertNull(backingArray[0]);
        assertEquals(140, backingArray[1]);
        assertEquals(120, backingArray[2]);
        assertEquals(80, backingArray[3]);
        assertEquals(100, backingArray[4]);
        assertEquals(40, backingArray[5]);
        assertEquals(60, backingArray[6]);
        assertEquals(25, backingArray[7]);
        assertEquals(20, backingArray[8]);
        assertEquals(10, backingArray[9]);
        assertEquals(5, backingArray[10]);
        assertEquals(15, backingArray[11]);
        assertNull(backingArray[12]);
        assertEquals(11, heap.size());


    }

    @Test
    public void testRemoveUntilEmpty() {
        heap.add(3);
        heap.add(2);
        heap.add(1);
        assertEquals(3, heap.size());

        /*

                   3
                  / \
                 2   1
         */

        Object[] backingArray = heap.getBackingArray();
        assertNull(backingArray[0]);
        assertEquals(3, backingArray[1]);
        assertEquals(2, backingArray[2]);
        assertEquals(1, backingArray[3]);

        Integer removedData = heap.remove();
        assertEquals(3, (int) removedData);
        assertEquals(2, heap.size());

        assertNull(backingArray[0]);
        assertEquals(2, backingArray[1]);
        assertEquals(1, backingArray[2]);
        assertNull(backingArray[3]);

        /*

                 2
                /
               1
         */

        removedData = heap.remove();
        assertEquals(2, (int) removedData);
        assertEquals(1, heap.size());

        assertNull(backingArray[0]);
        assertEquals(1, backingArray[1]);
        assertNull(backingArray[2]);
        assertNull(backingArray[3]);

        /*

                 1
                / \

         */

        removedData = heap.remove();
        assertEquals(1, (int) removedData);
        assertEquals(0, heap.size());

        assertNull(backingArray[0]);
        assertNull(backingArray[1]);
        assertNull(backingArray[2]);
        assertNull(backingArray[3]);



    }



}