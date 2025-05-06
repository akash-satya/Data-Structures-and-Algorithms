import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

/**
 * This is a basic set of unit tests for ArrayList.
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
 * @author CS 1332 TAs
 * @version 1.0
 */
public class ArrayListStudentTest {

    private static final int TIMEOUT = 200;
    private ArrayList<String> list;

    @Before
    public void setUp() {
        list = new ArrayList<>();
    }

    @Test(timeout = TIMEOUT)
    public void testInitialization() {
        assertEquals(0, list.size());
        assertArrayEquals(new Object[ArrayList.INITIAL_CAPACITY],
                list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testAddAtIndex() {
        list.addAtIndex(0, "2a");   // 2a
        list.addAtIndex(0, "1a");   // 1a, 2a
        list.addAtIndex(2, "4a");   // 1a, 2a, 4a
        list.addAtIndex(2, "3a");   // 1a, 2a, 3a, 4a
        list.addAtIndex(0, "0a");   // 0a, 1a, 2a, 3a, 4a

        assertEquals(5, list.size());

        Object[] expected = new Object[ArrayList.INITIAL_CAPACITY];
        expected[0] = "0a";
        expected[1] = "1a";
        expected[2] = "2a";
        expected[3] = "3a";
        expected[4] = "4a";
        assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testAddToFront() {
        list.addToFront("4a");  // 4a
        list.addToFront("3a");  // 3a, 4a
        list.addToFront("2a");  // 2a, 3a, 4a
        list.addToFront("1a");  // 1a, 2a, 3a, 4a
        list.addToFront("0a");  // 0a, 1a, 2a, 3a, 4a

        assertEquals(5, list.size());

        Object[] expected = new Object[ArrayList.INITIAL_CAPACITY];
        expected[0] = "0a";
        expected[1] = "1a";
        expected[2] = "2a";
        expected[3] = "3a";
        expected[4] = "4a";
        assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testAddToBack() {
        list.addToBack("0a");   // 0a
        list.addToBack("1a");   // 0a, 1a
        list.addToBack("2a");   // 0a, 1a, 2a
        list.addToBack("3a");   // 0a, 1a, 2a, 3a
        list.addToBack("4a");   // 0a, 1a, 2a, 3a, 4a

        assertEquals(5, list.size());

        Object[] expected = new Object[ArrayList.INITIAL_CAPACITY];
        expected[0] = "0a";
        expected[1] = "1a";
        expected[2] = "2a";
        expected[3] = "3a";
        expected[4] = "4a";
        assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveAtIndex() {
        String temp = "2a"; // For equality checking.

        list.addAtIndex(0, "0a");   // 0a
        list.addAtIndex(1, "1a");   // 0a, 1a
        list.addAtIndex(2, temp);   // 0a, 1a, 2a
        list.addAtIndex(3, "3a");   // 0a, 1a, 2a, 3a
        list.addAtIndex(4, "4a");   // 0a, 1a, 2a, 3a, 4a
        list.addAtIndex(5, "5a");   // 0a, 1a, 2a, 3a, 4a, 5a
        assertEquals(6, list.size());

        // assertSame checks for reference equality whereas assertEquals checks
        // value equality.
        assertSame(temp, list.removeAtIndex(2));    // 0a, 1a, 3a, 4a, 5a

        assertEquals(5, list.size());

        Object[] expected = new Object[ArrayList.INITIAL_CAPACITY];
        expected[0] = "0a";
        expected[1] = "1a";
        expected[2] = "3a";
        expected[3] = "4a";
        expected[4] = "5a";
        assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveFromFront() {
        String temp = "0a"; // For equality checking.

        list.addAtIndex(0, temp);   // 0a
        list.addAtIndex(1, "1a");   // 0a, 1a
        list.addAtIndex(2, "2a");   // 0a, 1a, 2a
        list.addAtIndex(3, "3a");   // 0a, 1a, 2a, 3a
        list.addAtIndex(4, "4a");   // 0a, 1a, 2a, 3a, 4a
        list.addAtIndex(5, "5a");   // 0a, 1a, 2a, 3a, 4a, 5a
        assertEquals(6, list.size());

        // assertSame checks for reference equality whereas assertEquals checks
        // value equality.
        assertSame(temp, list.removeFromFront());   // 1a, 2a, 3a, 4a, 5a

        assertEquals(5, list.size());

        Object[] expected = new Object[ArrayList.INITIAL_CAPACITY];
        expected[0] = "1a";
        expected[1] = "2a";
        expected[2] = "3a";
        expected[3] = "4a";
        expected[4] = "5a";
        assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveFromBack() {
        String temp = "5a"; // For equality checking.

        list.addAtIndex(0, "0a");   // 0a
        list.addAtIndex(1, "1a");   // 0a, 1a
        list.addAtIndex(2, "2a");   // 0a, 1a, 2a
        list.addAtIndex(3, "3a");   // 0a, 1a, 2a, 3a
        list.addAtIndex(4, "4a");   // 0a, 1a, 2a, 3a, 4a
        list.addAtIndex(5, temp);   // 0a, 1a, 2a, 3a, 4a, 5a
        assertEquals(6, list.size());

        // assertSame checks for reference equality whereas assertEquals checks
        // value equality.
        assertSame(temp, list.removeFromBack());    // 0a, 1a, 2a, 3a, 4a

        assertEquals(5, list.size());

        Object[] expected = new Object[ArrayList.INITIAL_CAPACITY];
        expected[0] = "0a";
        expected[1] = "1a";
        expected[2] = "2a";
        expected[3] = "3a";
        expected[4] = "4a";
        assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testGet() {
        list.addAtIndex(0, "0a");   // 0a
        list.addAtIndex(1, "1a");   // 0a, 1a
        list.addAtIndex(2, "2a");   // 0a, 1a, 2a
        list.addAtIndex(3, "3a");   // 0a, 1a, 2a, 3a
        list.addAtIndex(4, "4a");   // 0a, 1a, 2a, 3a, 4a
        assertEquals(5, list.size());

        assertEquals("0a", list.get(0));
        assertEquals("1a", list.get(1));
        assertEquals("2a", list.get(2));
        assertEquals("3a", list.get(3));
        assertEquals("4a", list.get(4));
    }

    @Test(timeout = TIMEOUT)
    public void testIsEmpty() {
        // Should be empty at initialization
        assertTrue(list.isEmpty());

        // Should not be empty after adding elements
        list.addAtIndex(0, "0a");   // 0a
        list.addAtIndex(1, "1a");   // 0a, 1a
        list.addAtIndex(2, "2a");   // 0a, 1a, 2a
        list.addAtIndex(3, "3a");   // 0a, 1a, 2a, 3a
        list.addAtIndex(4, "4a");   // 0a, 1a, 2a, 3a, 4a
        assertEquals(5, list.size());

        assertFalse(list.isEmpty());
    }

    @Test(timeout = TIMEOUT)
    public void testClear() {
        list.addAtIndex(0, "0a");   // 0a
        list.addAtIndex(1, "1a");   // 0a, 1a
        list.addAtIndex(2, "2a");   // 0a, 1a, 2a
        list.addAtIndex(3, "3a");   // 0a, 1a, 2a, 3a
        list.addAtIndex(4, "4a");   // 0a, 1a, 2a, 3a, 4a
        assertEquals(5, list.size());

        // Clearing the list should empty the array and reset size
        list.clear();

        assertEquals(0, list.size());
        assertArrayEquals(new Object[ArrayList.INITIAL_CAPACITY],
                list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void myTestAddAtIndex() {
        list.addAtIndex(0, "str1");
        list.addAtIndex(0, "str2");
        list.addAtIndex(0, "str3");
        list.addAtIndex(1, "str4");
        // [str3, str4, str2, str1]; size = 4
        assertEquals(4, list.size());
        Object[] expected = new Object[ArrayList.INITIAL_CAPACITY];
        expected[0] = "str3";
        expected[1] = "str4";
        expected[2] = "str2";
        expected[3] = "str1";
        assertArrayEquals(expected, list.getBackingArray());

        // If array is full, can we still add? INITIAL_CAPACITY is 9, so let's add 10 elements
        list.clear();
        list.addAtIndex(0, "1");
        list.addAtIndex(1, "2");
        list.addAtIndex(2, "3");
        list.addAtIndex(3, "4");
        list.addAtIndex(4, "5");
        list.addAtIndex(5, "6");
        list.addAtIndex(6, "7");
        list.addAtIndex(7, "8");
        list.addAtIndex(8, "9");
        list.addAtIndex(9, "10");
        // backingArray will double, with null everywhere that isn't filled
        Object[] expectedFull = new Object[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", null, null, null, null,
            null, null, null, null};
        assertEquals(10, list.size());
        assertArrayEquals(expectedFull, list.getBackingArray());

        // Test addToFront
        // list.addAtIndex(0, "front");
        list.addToFront("front");
        Object[] expectedFront = new Object[]{"front", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", null, null,
            null, null, null, null, null};
        assertArrayEquals(expectedFront, list.getBackingArray());

        // Test addToBack
        // list.addAtIndex(list.size(), "back");
        list.addToBack("back");
        Object[] expectedBack = new Object[]{"front", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "back", null,
            null, null, null, null, null};
        assertArrayEquals(expectedBack, list.getBackingArray());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddSmallIndex() {
        list.addAtIndex(0, "1");
        list.addAtIndex(1, "2");
        list.addAtIndex(2, "3");
        list.addAtIndex(-1, "Index < 0");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddBigIndex() {
        list.addAtIndex(0, "1");
        list.addAtIndex(1, "2");
        list.addAtIndex(2, "3");
        list.addAtIndex(4, "Index > size");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNull() {
        list.addAtIndex(0, null);
    }

    @Test(timeout = TIMEOUT)
    public void myTestRemoveAtIndex() {
        list.addAtIndex(0, "1");
        list.addAtIndex(1, "2");
        list.addAtIndex(2, "3");
        list.addAtIndex(3, "4");
        list.addAtIndex(4, "5");
        list.addAtIndex(5, "6");
        list.addAtIndex(6, "7");
        list.addAtIndex(7, "8");
        list.addAtIndex(8, "9");

        // Standard removal
        list.removeAtIndex(1);
        Object[] expected = new Object[]{"1", "3", "4", "5", "6", "7", "8", "9", null};
        assertArrayEquals(expected, list.getBackingArray());

        // Remove from Front
        list.removeAtIndex(0);
        Object[] expectedFront = new Object[]{"3", "4", "5", "6", "7", "8", "9", null, null};
        assertArrayEquals(expectedFront, list.getBackingArray());

        // Remove from back
        list.removeAtIndex(list.size() - 1);
        Object[] expectedBack = new Object[]{"3", "4", "5", "6", "7", "8", null, null, null};
        assertArrayEquals(expectedBack, list.getBackingArray());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveSmallIndex() {
        list.addAtIndex(0, "1");
        list.addAtIndex(1, "2");
        list.addAtIndex(2, "3");
        list.removeAtIndex(-1);
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testRemoveBigIndex() {
        list.addAtIndex(0, "1");
        list.addAtIndex(1, "2");
        list.addAtIndex(2, "3");
        list.removeAtIndex(3);
    }

    @Test (expected = NoSuchElementException.class)
    public void testEmptyRemoveFront() {
        list.removeFromFront();
    }

    @Test (expected = NoSuchElementException.class)
    public void testEmptyRemoveBack() {
        list.removeFromBack();
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testSmallGetIndex() {
        list.addAtIndex(0, "1");
        list.addAtIndex(1, "2");
        list.addAtIndex(2, "3");
        list.get(-1);
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testBigGetIndex() {
        list.addAtIndex(0, "1");
        list.addAtIndex(1, "2");
        list.addAtIndex(2, "3");
        list.get(3);
    }
}