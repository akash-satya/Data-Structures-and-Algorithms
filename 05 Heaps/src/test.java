import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

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
 * @author CS 1332 TAs, david gu
 * @version 1.0
 */
public class test {

  private static final int TIMEOUT = 200;
  private MaxHeap<Integer> heap;

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
     * 2
     * / \
     * 1 4
     * / \
     * 6 8
     *
     * ->
     *
     * 8
     * / \
     * 6 4
     * / \
     * 2 1
     */

    ArrayList<Integer> data = new ArrayList<>();
    data.add(2);
    data.add(1);
    data.add(4);
    data.add(6);
    data.add(8);
    data.add(0);
    heap = new MaxHeap<>(data);

    assertEquals(6, heap.size());

    Integer[] expected = new Integer[13];
    expected[1] = 8;
    expected[2] = 6;
    expected[3] = 4;
    expected[4] = 2;
    expected[5] = 1;
    expected[6] = 0;
    assertArrayEquals(expected, heap.getBackingArray());

    data = new ArrayList<>();
    data.add(null);
    try {
      heap = new MaxHeap<>(data);
    } catch (IllegalArgumentException e) {

    }

    try {
      heap = new MaxHeap<>(null);
    } catch (IllegalArgumentException e) {

    }
  }

  @Test(timeout = TIMEOUT)
  public void testAdd() {
    /*
     * 4
     */
    heap.add(4);

    /*
     * 6
     * /
     * 4
     */
    heap.add(6);

    /*
     * 6
     * / \
     * 4 2
     */
    heap.add(2);

    /*
     * 8
     * / \
     * 6 2
     * /
     * 4
     */
    heap.add(8);

    /*
     * 8
     * / \
     * 6 2
     * / \
     * 4 1
     */
    heap.add(1);

    heap.add(10);

    heap.add(9);

    assertEquals(7, heap.size());

    Integer[] expected = new Integer[MaxHeap.INITIAL_CAPACITY];
    expected[1] = 10;
    expected[2] = 6;
    expected[3] = 9;
    expected[4] = 4;
    expected[5] = 1;
    expected[6] = 2;
    expected[7] = 8;
    assertArrayEquals(expected, heap.getBackingArray());

    try {
      heap.add(null);
    } catch (IllegalArgumentException e) {

    }

    heap.add(11);
    heap.add(12);
    heap.add(3);
    heap.add(5);
    heap.add(7);
    heap.add(14);
    heap.add(13);
    heap.add(15);

    expected = new Integer[MaxHeap.INITIAL_CAPACITY * 2];
    expected[1] = 15;
    expected[2] = 11;
    expected[3] = 14;
    expected[4] = 10;
    expected[5] = 5;
    expected[6] = 9;
    expected[7] = 13;
    expected[8] = 4;
    expected[9] = 6;
    expected[10] = 1;
    expected[11] = 3;
    expected[12] = 2;
    expected[13] = 7;
    expected[14] = 8;
    expected[15] = 12;
    assertArrayEquals(expected, heap.getBackingArray());

  }

  @Test(timeout = TIMEOUT)
  public void testRemove() {
    try {
      heap.remove();
    } catch (java.util.NoSuchElementException e) {

    }

    /*
     * 8
     * / \
     * 6 4
     * / \
     * 1 2
     */
    heap.add(4);
    heap.add(1);
    heap.add(6);
    heap.add(2);
    heap.add(8);
    assertEquals(5, heap.size());

    /*
     * 6
     * / \
     * 2 4
     * /
     * 1
     */
    assertEquals((Integer) 8, heap.remove());

    /*
     * 4
     * / \
     * 2 1
     */
    assertEquals((Integer) 6, heap.remove());

    /*
     * 2
     * /
     * 1
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
    try {
      heap.getMax();
    } catch (java.util.NoSuchElementException e) {

    }
    /*
     * 5
     * / \
     * 4 3
     * / \
     * 2 1
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
     * 5
     * / \
     * 4 3
     * / \
     * 2 1
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
     * 5
     * / \
     * 4 3
     * / \
     * 2 1
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
}
