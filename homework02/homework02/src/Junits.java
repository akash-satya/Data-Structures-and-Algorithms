import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.NoSuchElementException; // Add this import
import org.junit.Before;
import org.junit.Test;

/**
 * This is a basic set of unit tests for CircularSinglyLinkedList.
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
public class Junits {

    private static final int TIMEOUT = 200;
    private CircularSinglyLinkedList<String> list;

    @Before
    public void setUp() {
        list = new CircularSinglyLinkedList<>();
    }

    @Test(timeout = TIMEOUT)
    public void testInitialization() {
        assertEquals(0, list.size());
        assertNull(list.getHead());
    }

    @Test(timeout = TIMEOUT)
    public void testAddAtIndex() {
        list.addAtIndex(0, "2a");   // 2a
        list.addAtIndex(0, "1a");   // 1a, 2a
        list.addAtIndex(2, "4a");   // 1a, 2a, 4a
        list.addAtIndex(2, "3a");   // 1a, 2a, 3a, 4a
        list.addAtIndex(0, "0a");   // 0a, 1a, 2a, 3a, 4a

        assertEquals(5, list.size());

        CircularSinglyLinkedListNode<String> cur = list.getHead();
        assertNotNull(cur);
        assertEquals("0a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("1a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("2a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("3a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("4a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals(list.getHead(), cur);
    }

    @Test(timeout = TIMEOUT)
    public void testAddToFront() {
        list.addToFront("4a");  // 4a
        list.addToFront("3a");  // 3a, 4a
        list.addToFront("2a");  // 2a, 3a, 4a
        list.addToFront("1a");  // 1a, 2a, 3a, 4a
        list.addToFront("0a");  // 0a, 1a, 2a, 3a, 4a

        assertEquals(5, list.size());

        CircularSinglyLinkedListNode<String> cur = list.getHead();
        assertNotNull(cur);
        assertEquals("0a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("1a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("2a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("3a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("4a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals(list.getHead(), cur);
    }

    @Test(timeout = TIMEOUT)
    public void testAddToBack() {
        list.addToBack("0a");   // 0a
        list.addToBack("1a");   // 0a, 1a
        list.addToBack("2a");   // 0a, 1a, 2a
        list.addToBack("3a");   // 0a, 1a, 2a, 3a
        list.addToBack("4a");   // 0a, 1a, 2a, 3a, 4a

        assertEquals(5, list.size());

        CircularSinglyLinkedListNode<String> cur = list.getHead();
        assertNotNull(cur);
        assertEquals("0a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("1a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("2a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("3a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("4a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals(list.getHead(), cur);
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveAtIndex() {
        String temp = "2a";

        list.addAtIndex(0, "0a");   // 0a
        list.addAtIndex(1, "1a");   // 0a, 1a
        list.addAtIndex(2, temp);   // 0a, 1a, 2a
        list.addAtIndex(3, "3a");   // 0a, 1a, 2a, 3a
        list.addAtIndex(4, "4a");   // 0a, 1a, 2a, 3a, 4a
        list.addAtIndex(5, "5a");   // 0a, 1a, 2a, 3a, 4a, 5a
        assertEquals(6, list.size());

        assertSame(temp, list.removeAtIndex(2));    // 0a, 1a, 3a, 4a, 5a

        assertEquals(5, list.size());

        CircularSinglyLinkedListNode<String> cur = list.getHead();
        assertNotNull(cur);
        assertEquals("0a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("1a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("3a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("4a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("5a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals(list.getHead(), cur);
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveFromFront() {
        String temp = "0a";

        list.addAtIndex(0, temp);   // 0a
        list.addAtIndex(1, "1a");   // 0a, 1a
        list.addAtIndex(2, "2a");   // 0a, 1a, 2a
        list.addAtIndex(3, "3a");   // 0a, 1a, 2a, 3a
        list.addAtIndex(4, "4a");   // 0a, 1a, 2a, 3a, 4a
        list.addAtIndex(5, "5a");   // 0a, 1a, 2a, 3a, 4a, 5a
        assertEquals(6, list.size());

        assertSame(temp, list.removeFromFront());   // 1a, 2a, 3a, 4a, 5a

        assertEquals(5, list.size());

        CircularSinglyLinkedListNode<String> cur = list.getHead();
        assertNotNull(cur);
        assertEquals("1a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("2a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("3a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("4a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("5a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals(list.getHead(), cur);
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveFromBack() {
        String temp = "5a";

        list.addAtIndex(0, "0a");   // 0a
        list.addAtIndex(1, "1a");   // 0a, 1a
        list.addAtIndex(2, "2a");   // 0a, 1a, 2a
        list.addAtIndex(3, "3a");   // 0a, 1a, 2a, 3a
        list.addAtIndex(4, "4a");   // 0a, 1a, 2a, 3a, 4a
        list.addAtIndex(5, temp);   // 0a, 1a, 2a, 3a, 4a, 5a
        assertEquals(6, list.size());

        assertSame(temp, list.removeFromBack());    // 0a, 1a, 2a, 3a, 4a

        assertEquals(5, list.size());

        CircularSinglyLinkedListNode<String> cur = list.getHead();
        assertNotNull(cur);
        assertEquals("0a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("1a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("2a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("3a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("4a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals(list.getHead(), cur);
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
        assertTrue(list.isEmpty());

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

        list.clear();

        assertEquals(0, list.size());
        assertNull(list.getHead());
    }

    @Test(timeout = TIMEOUT)
    public void testLastOccurrence() {
        String temp = "2a";

        list.addAtIndex(0, "0a");   // 0a
        list.addAtIndex(1, "1a");   // 0a, 1a
        list.addAtIndex(2, "2a");   // 0a, 1a, 2a
        list.addAtIndex(3, temp);   // 0a, 1a, 2a, 2a
        list.addAtIndex(4, "3a");   // 0a, 1a, 2a, 2a, 3a
        list.addAtIndex(5, "4a");   // 0a, 1a, 2a, 2a, 3a, 4a
        assertEquals(6, list.size());

        assertSame(temp, list.removeLastOccurrence("2a")); // 0a, 1a, 2a, 3a, 4a
        assertEquals(5, list.size());

        CircularSinglyLinkedListNode<String> cur = list.getHead();
        assertNotNull(cur);
        assertEquals("0a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("1a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("2a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("3a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("4a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals(list.getHead(), cur);
    }

    @Test(timeout = TIMEOUT)
    public void testToArray() {
        list.addAtIndex(0, "0a");   // 0a
        list.addAtIndex(1, "1a");   // 0a, 1a
        list.addAtIndex(2, "2a");   // 0a, 1a, 2a
        list.addAtIndex(3, "3a");   // 0a, 1a, 2a, 3a
        list.addAtIndex(4, "4a");   // 0a, 1a, 2a, 3a, 4a
        assertEquals(5, list.size());

        String[] expected = new String[5];
        expected[0] = "0a";
        expected[1] = "1a";
        expected[2] = "2a";
        expected[3] = "3a";
        expected[4] = "4a";
        assertArrayEquals(expected, list.toArray());
    }


    // ######## MY TESTS ######## \\
    @Test (timeout = TIMEOUT)
    public void testAddAtIndexThrowsIndexOutOfBoundsWithNegativeIndex() {
        list.addAtIndex(0, "0a");   // 0a
        list.addAtIndex(1, "1a");   // 0a, 1a
        list.addAtIndex(2, "2a");   // 0a, 1a, 2a
        list.addAtIndex(3, "3a");   // 0a, 1a, 2a, 3a
        list.addAtIndex(4, "4a");   // 0a, 1a, 2a, 3a, 4a
        try {
    list.addAtIndex(-1, "-1a");
    fail("Expected exception IndexOutOfBoundsException.class was not thrown.");
} catch (IndexOutOfBoundsException e) {
    // Expected exception caught
}
 // IndexOutOfBounds
    }

    @Test (timeout = TIMEOUT)
    public void testAddAtIndexThrowsIndexOutOfBoundsWithIndexGreaterThanSize() {
        list.addAtIndex(0, "0a"); // 0a size = 1
        list.addAtIndex(1, "1a"); // 0a, 1a size = 2
        list.addAtIndex(2, "2a"); // 0a, 1a, 2a size = 3
        list.addAtIndex(3, "3a"); // 0a, 1a, 2a, 3a size = 4
        try {
    list.addAtIndex(5, "4a");
    fail("Expected exception IndexOutOfBoundsException.class was not thrown.");
} catch (IndexOutOfBoundsException e) {
    // Expected exception caught
}

    }

    @Test (timeout = TIMEOUT)
    public void testAddAtIndexThrowsIllegalArgumentExceptionWithNullData() {
        list.addAtIndex(0, "0a"); // 0a size = 1
        list.addAtIndex(1, "1a"); // 0a, 1a size = 2
        list.addAtIndex(2, "2a"); // 0a, 1a, 2a size = 3
        list.addAtIndex(3, "3a"); // 0a, 1a, 2a, 3a size = 4
        try {
    list.addAtIndex(0, null);
    fail("Expected exception IllegalArgumentException.class was not thrown.");
} catch (IllegalArgumentException e) {
    // Expected exception caught
}

    }

    @Test (timeout = TIMEOUT)
    public void testAddToFrontThrowsIllegalArgumentExceptionWithNullData() {
        list.addAtIndex(0, "0a"); // 0a size = 1
        list.addAtIndex(1, "1a"); // 0a, 1a size = 2
        list.addAtIndex(2, "2a"); // 0a, 1a, 2a size = 3
        list.addAtIndex(3, "3a"); // 0a, 1a, 2a, 3a size = 4

        try {
    list.addToFront(null);
    fail("Expected exception IllegalArgumentException.class was not thrown.");
} catch (IllegalArgumentException e) {
    // Expected exception caught
}

    }

    @Test (timeout = TIMEOUT)
    public void testAddToFrontOnEmptyCSLL() {
        // null
        assertEquals(0, list.size());

        list.addToFront("0a"); // 0a (should also be head)

        assertEquals(1, list.size()); // size should be 1

        CircularSinglyLinkedListNode<String> currentNode = list.getHead();

        assertSame(list.getHead(), currentNode);
        assertEquals("0a", currentNode.getData());
        assertEquals("0a", list.getHead().getData());
        assertSame(list.getHead().getNext(), currentNode.getNext());
        assertSame(list.getHead(), currentNode.getNext());
        assertEquals(list.getHead().getNext().getData(), currentNode.getNext().getData());
        assertSame("0a", currentNode.getNext().getData());
    }

    @Test (timeout = TIMEOUT)
    public void testAddToBackThrowsIllegalArgumentExceptionWithNullData() {
        list.addAtIndex(0, "0a"); // 0a size = 1
        list.addAtIndex(1, "1a"); // 0a, 1a size = 2

        assertEquals(2, list.size());

        list.addAtIndex(2, "2a"); // 0a, 1a, 2a size = 3
        list.addAtIndex(3, "3a"); // 0a, 1a, 2a, 3a size = 4

        assertEquals(4, list.size());
        try {
    list.addToBack(null);
    fail("Expected exception IllegalArgumentException.class was not thrown.");
} catch (IllegalArgumentException e) {
    // Expected exception caught
}

    }

    @Test (timeout = TIMEOUT)
    public void testAddToBackOnEmptyCSLL() {

        list.addToBack("0a"); // 0a

        assertTrue(2 != list.size());
        assertEquals(1, list.size());

        assertSame("0a", list.get(0));
        assertSame("0a", list.getHead().getData());
        assertEquals("0a", list.get(0));
        assertEquals("0a", list.getHead().getData());
    }

    @Test (timeout = TIMEOUT)
    public void removeAtIndexThrowsIndexOutOfBoundsWithNegativeIndex() {
        list.addAtIndex(0, "0a");   // 0a
        list.addAtIndex(1, "1a");   // 0a, 1a
        list.addAtIndex(2, "2a");   // 0a, 1a, 2a
        list.addAtIndex(3, "3a");   // 0a, 1a, 2a, 3a
        list.addAtIndex(4, "4a");   // 0a, 1a, 2a, 3a, 4a

        try {
    list.removeAtIndex(-1);
    fail("Expected exception IndexOutOfBoundsException.class was not thrown.");
} catch (IndexOutOfBoundsException e) {
    // Expected exception caught
}
 // IndexOutOfBounds
    }

    @Test (timeout = TIMEOUT)
    public void removeAtIndexThrowsIndexOutOfBoundsWithIndexEqualToSize() {
        list.addAtIndex(0, "0a");   // 0a
        list.addAtIndex(1, "1a");   // 0a, 1a
        list.addAtIndex(2, "2a");   // 0a, 1a, 2a
        list.addAtIndex(3, "3a");   // 0a, 1a, 2a, 3a
        list.addAtIndex(4, "4a");   // 0a, 1a, 2a, 3a, 4a

        assertEquals(5, list.size());
        try {
    list.removeAtIndex(list.size());
    fail("Expected exception IndexOutOfBoundsException.class was not thrown.");
} catch (IndexOutOfBoundsException e) {
    // Expected exception caught
}

        try {
    list.removeAtIndex(5);
    fail("Expected exception IndexOutOfBoundsException.class was not thrown.");
} catch (IndexOutOfBoundsException e) {
    // Expected exception caught
}

    }

    @Test (timeout = TIMEOUT)
    public void removeAtIndexThrowsIndexOutOfBoundsWithIndexGreaterThanSize() {
        list.addAtIndex(0, "0a");   // 0a
        list.addAtIndex(1, "1a");   // 0a, 1a
        list.addAtIndex(2, "2a");   // 0a, 1a, 2a
        list.addAtIndex(3, "3a");   // 0a, 1a, 2a, 3a
        list.addAtIndex(4, "4a");   // 0a, 1a, 2a, 3a, 4a

        assertEquals(5, list.size());
        try {
    list.removeAtIndex(list.size() + 1);
    fail("Expected exception IndexOutOfBoundsException.class was not thrown.");
} catch (IndexOutOfBoundsException e) {
    // Expected exception caught
}

        try {
    list.removeAtIndex(6);
    fail("Expected exception IndexOutOfBoundsException.class was not thrown.");
} catch (IndexOutOfBoundsException e) {
    // Expected exception caught
}

    }

    @Test (timeout = TIMEOUT)
    public void removeAtIndexThrowsIndexOutOfBoundsOnEmptyCSLL() {

        try {
    list.removeAtIndex(0);
    fail("Expected exception IndexOutOfBoundsException.class was not thrown.");
} catch (IndexOutOfBoundsException e) {
    // Expected exception caught
}

    }

    @Test (timeout = TIMEOUT)
    public void testRemoveAtIndexOnCSLLWithSizeOneAndReturnValue() {

        list.addToFront("0a"); // 0a

        var removed = list.removeAtIndex(0); // empty list, head and head next = null

        assertEquals(null, list.getHead());

        try {
    list.getHead().getNext();
    fail("Expected exception NullPointerException.class was not thrown.");
} catch (NullPointerException e) {
    // Expected exception caught
}

        assertSame(0, list.size());
        assertEquals(0, list.size());

        assertEquals("0a", removed);

    }

    @Test (timeout = TIMEOUT)
    public void testRemoveAtIndexZero2() {
        list.addAtIndex(0, "0a");   // 0a
        list.addAtIndex(1, "1a");   // 0a, 1a
        list.addAtIndex(2, "2a");   // 0a, 1a, 2a
        list.addAtIndex(3, "3a");   // 0a, 1a, 2a, 3a
        list.addAtIndex(4, "4a");   // 0a, 1a, 2a, 3a, 4a

        list.removeAtIndex(0); // head in same position, only data should have been moved
        // 1a (head), 2a, 3a, 4a

        assertEquals(4, list.size());

        CircularSinglyLinkedListNode<String> currentNode = list.getHead();

        assertSame("1a", currentNode.getData());
        assertEquals("1a", currentNode.getData());
        assertEquals(list.getHead(), currentNode);
        assertNotNull(currentNode);
        assertNotNull(list.getHead());
        assertEquals("1a", list.getHead().getData());

        currentNode = currentNode.getNext();
        assertNotNull(currentNode);
        assertEquals("2a", currentNode.getData());

        currentNode = currentNode.getNext();
        assertNotNull(currentNode);
        assertEquals("3a", currentNode.getData());

        currentNode = currentNode.getNext();
        assertNotNull(currentNode);
        assertEquals("4a", currentNode.getData());

        currentNode = currentNode.getNext();
        assertNotNull(currentNode);
        assertSame(list.getHead(), currentNode);
        assertEquals("1a", currentNode.getData());
    }

    @Test (timeout = TIMEOUT)
    public void removeFromFrontThrowsNoSuchElementExceptionWithEmptyList() {


        try {
    list.removeFromFront();
    fail("Expected exception NoSuchElementException.class was not thrown.");
} catch (NoSuchElementException e) {
    // Expected exception caught
}
 // IndexOutOfBounds
    }

    @Test (timeout = TIMEOUT)
    public void removeFromBackThrowsNoSuchElementExceptionWithEmptyList() {


        try {
    list.removeFromBack();
    fail("Expected exception NoSuchElementException.class was not thrown.");
} catch (NoSuchElementException e) {
    // Expected exception caught
}
 // IndexOutOfBounds
    }

    @Test (timeout = TIMEOUT)
    public void removeFromBackActuallyWorks() {

        list.addAtIndex(0, "0a");   // 0a
        list.addAtIndex(1, "1a");   // 0a, 1a
        list.addAtIndex(2, "2a");   // 0a, 1a, 2a
        list.addAtIndex(3, "3a");   // 0a, 1a, 2a, 3a
        list.addAtIndex(4, "4a");   // 0a, 1a, 2a, 3a, 4a

        list.removeFromBack(); // head in same position, only 2nd to last pointer moved to head now
        // 0a (head), 1a, 2a, 3a (should point to head)

        assertEquals(4, list.size());

        CircularSinglyLinkedListNode<String> currentNode = list.getHead();

        assertSame("0a", currentNode.getData());
        assertEquals("0a", currentNode.getData());
        assertEquals(list.getHead(), currentNode);
        assertNotNull(currentNode);
        assertNotNull(list.getHead());
        assertEquals("0a", list.getHead().getData());

        currentNode = currentNode.getNext();
        assertNotNull(currentNode);
        assertEquals("1a", currentNode.getData());

        currentNode = currentNode.getNext();
        assertNotNull(currentNode);
        assertEquals("2a", currentNode.getData());

        currentNode = currentNode.getNext();
        assertNotNull(currentNode);
        assertEquals("3a", currentNode.getData());

        currentNode = currentNode.getNext();
        assertNotNull(currentNode);
        assertSame(list.getHead(), currentNode);
        assertEquals("0a", currentNode.getData());
    }

    @Test (timeout = TIMEOUT)
    public void testGetThrowsIndexOutOfBoundsForNegativeIndex() {
        list.addAtIndex(0, "0a");   // 0a
        list.addAtIndex(1, "1a");   // 0a, 1a
        list.addAtIndex(2, "2a");   // 0a, 1a, 2a
        list.addAtIndex(3, "3a");   // 0a, 1a, 2a, 3a
        list.addAtIndex(4, "4a");   // 0a, 1a, 2a, 3a, 4a
        try {
    list.get(-1);
    fail("Expected exception IndexOutOfBoundsException.class was not thrown.");
} catch (IndexOutOfBoundsException e) {
    // Expected exception caught
}
 // IndexOutOfBounds
    }

    @Test (timeout = TIMEOUT)
    public void testGetThrowsIndexOutOfBoundsForIndexEqualToSize() {
        list.addAtIndex(0, "0a");   // 0a
        list.addAtIndex(1, "1a");   // 0a, 1a
        list.addAtIndex(2, "2a");   // 0a, 1a, 2a
        list.addAtIndex(3, "3a");   // 0a, 1a, 2a, 3a
        list.addAtIndex(4, "4a"); // 0a, 1a, 2a, 3a, 4a

        assertEquals(5, list.size());

        try {
    list.get(list.size());
    fail("Expected exception IndexOutOfBoundsException.class was not thrown.");
} catch (IndexOutOfBoundsException e) {
    // Expected exception caught
}
 // IndexOutOfBounds

        try {
    list.get(5);
    fail("Expected exception IndexOutOfBoundsException.class was not thrown.");
} catch (IndexOutOfBoundsException e) {
    // Expected exception caught
}

    }

    @Test (timeout = TIMEOUT)
    public void testGetThrowsIndexOutOfBoundsForIndexGreaterThanSize() {
        list.addAtIndex(0, "0a");   // 0a
        list.addAtIndex(1, "1a");   // 0a, 1a
        list.addAtIndex(2, "2a");   // 0a, 1a, 2a
        list.addAtIndex(3, "3a");   // 0a, 1a, 2a, 3a
        list.addAtIndex(4, "4a"); // 0a, 1a, 2a, 3a, 4a

        assertEquals(5, list.size());

        assertEquals(6, list.size() + 1);

        try {
    list.get(list.size() + 1);
    fail("Expected exception IndexOutOfBoundsException.class was not thrown.");
} catch (IndexOutOfBoundsException e) {
    // Expected exception caught
}

        try {
    list.get(6);
    fail("Expected exception IndexOutOfBoundsException.class was not thrown.");
} catch (IndexOutOfBoundsException e) {
    // Expected exception caught
}

    }

    @Test (timeout = TIMEOUT)
    public void testRemoveLastOccurrenceThrowsIllegalArgumentExceptionWithNullInputData() {

        list.addAtIndex(0, "0a");   // 0a
        list.addAtIndex(1, "1a");   // 0a, 1a
        list.addAtIndex(2, "2a");   // 0a, 1a, 2a
        list.addAtIndex(3, "3a");   // 0a, 1a, 2a, 3a
        list.addAtIndex(4, "4a"); // 0a, 1a, 2a, 3a, 4a

        try {
    list.removeLastOccurrence(null);
    fail("Expected exception IllegalArgumentException.class was not thrown.");
} catch (IllegalArgumentException e) {
    // Expected exception caught
}

    }

    @Test (timeout = TIMEOUT)
    public void testRemoveLastOccurrenceThrowsNoSuchElementExceptionWithEmptyCSLL() {

        // empty CSLL

        try {
    list.removeLastOccurrence("0a");
    fail("Expected exception NoSuchElementException.class was not thrown.");
} catch (NoSuchElementException e) {
    // Expected exception caught
}

    }

    @Test (timeout = TIMEOUT)
    public void testRemoveLastOccurrenceThrowsNoSuchElementExceptionWhenDataNotFound() {
        list.addAtIndex(0, "0a");   // 0a
        list.addAtIndex(1, "1a");   // 0a, 1a
        list.addAtIndex(2, "2a");   // 0a, 1a, 2a
        list.addAtIndex(3, "3a");   // 0a, 1a, 2a, 3a
        list.addAtIndex(4, "4a"); // 0a, 1a, 2a, 3a, 4a

        try {
    list.removeLastOccurrence("5a");
    fail("Expected exception NoSuchElementException.class was not thrown.");
} catch (NoSuchElementException e) {
    // Expected exception caught
}

    }

    @Test (timeout = TIMEOUT)
    public void testRemoveLastOccurrenceForSomeCSLLWithRepeatedOccurrence() {
        list.addAtIndex(0, "0a");   // 0a
        list.addAtIndex(1, "1a");   // 0a, 1a
        list.addAtIndex(2, "2a");   // 0a, 1a, 2a
        list.addAtIndex(3, "2a");   // 0a, 1a, 2a, 3a
        list.addAtIndex(4, "4a"); // 0a, 1a, 2a, 3a, 4a

        // removing 2a

        assertEquals(5, list.size());

        list.removeLastOccurrence("2a");
        assertEquals("4a", list.get(3));
        assertEquals(4, list.size());
    }

    @Test (timeout = TIMEOUT)
    public void testRemoveLastOccurrenceForSomeCSLLWithRepeatedOccurrence2() {
        list.addAtIndex(0, "0a");   // 0a
        list.addAtIndex(1, "2a");   // 0a, 1a
        list.addAtIndex(2, "2a");   // 0a, 1a, 2a
        list.addAtIndex(3, "3a");   // 0a, 1a, 2a, 3a
        list.addAtIndex(4, "4a"); // 0a, 1a, 2a, 3a, 4a

        // removing 2a

        assertEquals(5, list.size());

        list.removeLastOccurrence("2a");
        assertEquals("4a", list.get(3));
        assertEquals("0a", list.get(0));
        assertEquals("2a", list.get(1));
        assertEquals("3a", list.get(2));
    }

    @Test (timeout = TIMEOUT)
    public void removeLastOccurrenceWhenItShouldRemoveHead() {
        list.addAtIndex(0, "0a");   // 0a
        list.addAtIndex(1, "2a");   // 0a, 1a
        list.addAtIndex(2, "2a");   // 0a, 1a, 2a
        list.addAtIndex(3, "3a");   // 0a, 1a, 2a, 3a
        list.addAtIndex(4, "4a"); // 0a, 1a, 2a, 3a, 4a

        // removing 0a

        var usingRemoveFromFront = list.removeFromFront();

        list.addAtIndex(0, "0a");   // 0a
        list.addAtIndex(1, "1a");   // 0a, 1a
        list.addAtIndex(2, "2a");   // 0a, 1a, 2a
        list.addAtIndex(3, "3a");   // 0a, 1a, 2a, 3a
        list.addAtIndex(4, "4a"); // 0a, 1a, 2a, 3a, 4a

        // removing 0a

        var usingRemoveLastOccurrence = list.removeLastOccurrence("0a");
        assertEquals("0a", usingRemoveFromFront);
        assertEquals("0a", usingRemoveLastOccurrence);
        assertEquals(usingRemoveFromFront, usingRemoveLastOccurrence);
    }

    @Test (timeout = TIMEOUT)
    public void removeLastOccurrenceWhenItShouldRemoveHead2() {
        list.addAtIndex(0, "0a");   // 0a
        list.addAtIndex(1, "1a");   // 0a, 1a
        list.addAtIndex(2, "2a");   // 0a, 1a, 2a
        list.addAtIndex(3, "3a");   // 0a, 1a, 2a, 3a
        list.addAtIndex(4, "4a"); // 0a, 1a, 2a, 3a, 4a

        // removing 0a

        list.removeLastOccurrence("0a");
        assertEquals(4, list.size());

        CircularSinglyLinkedListNode<String> currentNode = list.getHead();
        assertNotNull(currentNode);
        assertEquals("1a", currentNode.getData());
        assertSame(list.getHead().getData(), currentNode.getData());
        assertSame("1a", list.getHead().getData());
        assertSame("1a", list.get(0));
        assertSame(currentNode.getData(), list.get(0));

        currentNode = currentNode.getNext();
        assertNotNull(currentNode);
        assertEquals("2a", currentNode.getData());
        assertSame("2a", list.get(1));
        assertSame(currentNode.getData(), list.get(1));

        currentNode = currentNode.getNext();
        assertNotNull(currentNode);
        assertEquals("3a", currentNode.getData());
        assertSame("3a", list.get(2));
        assertSame(currentNode.getData(), list.get(2));

        currentNode = currentNode.getNext();
        assertNotNull(currentNode);
        assertEquals("4a", currentNode.getData());
        assertSame("4a", list.get(3));
        assertSame(currentNode.getData(), list.get(3));

        currentNode = currentNode.getNext();
        assertNotNull(currentNode);
        assertSame(list.getHead(), currentNode);
        assertEquals("1a", currentNode.getData());
        assertSame(currentNode.getData(), list.getHead().getData());
    }

    @Test (timeout = TIMEOUT)
    public void testToArrayOnEmptyCSLL() {

        String[] expected = new String[0];
        Object[] expected2 = new Object[0];
        assertArrayEquals(expected, list.toArray());
        assertArrayEquals(expected2, list.toArray());
    }

    @Test (timeout = TIMEOUT)
    public void testToArrayOnCSLL() {
        list.addAtIndex(0, "0a");   // 0a
        list.addAtIndex(1, "1a");   // 0a, 1a
        list.addAtIndex(2, "2a");   // 0a, 1a, 2a

        assertEquals(3, list.size());
        String[] expectedArray = new String[3];
        assertEquals(expectedArray.length, list.size());
        assertEquals(expectedArray.length, 3);

        expectedArray[0] = "0a";
        expectedArray[1] = "1a";
        expectedArray[2] = "2a";

        assertArrayEquals(expectedArray, list.toArray());

        assertEquals(expectedArray[2], list.get(2));

    }

    @Test (timeout = TIMEOUT)
    public void testIsEmptyOnEmptyCSLL() {

        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
    }

    @Test (timeout = TIMEOUT)
    public void testClearOnEmptyCSLL() {

        list.clear();
        assertSame(null, list.getHead());
        assertEquals(0, list.size());

    }

    @Test (timeout = TIMEOUT)
    public void testClearOnNonEmptyCSLL() {
        list.addAtIndex(0, "0a");   // 0a
        list.addAtIndex(1, "1a");   // 0a, 1a
        list.addAtIndex(2, "2a");   // 0a, 1a, 2a

        list.clear();
        assertSame(null, list.getHead());
        assertEquals(0, list.size());

        try {
    list.get(2);
    fail("Expected exception IndexOutOfBoundsException.class was not thrown.");
} catch (IndexOutOfBoundsException e) {
    // Expected exception caught
}


    }

    @Test (timeout = TIMEOUT)
    public void testIsEmptyOnNonEmptyCSLL() {
        list.addAtIndex(0, "0a");   // 0a
        list.addAtIndex(1, "1a");   // 0a, 1a
        list.addAtIndex(2, "2a");   // 0a, 1a, 2a

        assertFalse(list.isEmpty());
        assertTrue(!list.isEmpty());
    }

    @Test (timeout = TIMEOUT)
    public void testRemoveLastOccurrenceOnSingleItemEqualsTargetData() {
        list.addAtIndex(0, "0a");   // 0a
        String data = list.removeLastOccurrence("0a");

        assertSame(null, list.getHead());
        assertSame(data, "0a");
    }
}
