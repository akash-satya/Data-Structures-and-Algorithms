import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import org.junit.Before;
import org.junit.Test;

/**
 * This is a set of unit tests for ArrayStack and LinkedStack.
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
 * @author Jeet Hirenkumar Dekivadia
 * @version 1.0
 */
public class StackStudentTest {

    private static final int TIMEOUT = 200;
    private ArrayStack<String> array;
    private LinkedStack<String> linked;

    @Before
    public void setup() {
        array = new ArrayStack<>();
        linked = new LinkedStack<>();
    }

    @Test(timeout = TIMEOUT)
    public void testArrayStackInitialization() {
        assertEquals(0, array.size());
        assertArrayEquals(new Object[ArrayStack.INITIAL_CAPACITY], array.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedStackInitialization() {
        assertEquals(0, linked.size());
        assertNull(linked.getHead());
    }

    @Test(timeout = TIMEOUT)
    public void testArrayPushOneElement() {
        array.push("0a");
        assertEquals(1, array.size());
        assertEquals("0a", array.peek());
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedPushOneElement() {
        linked.push("0a");
        assertEquals(1, linked.size());
        assertEquals("0a", linked.peek());
    }

    @Test(timeout = TIMEOUT)
    public void testArrayPushMultipleElements() {
        for (int i = 0; i < 10; i++) {
            array.push(i + "a");
        }
        assertEquals(10, array.size());
        assertEquals("9a", array.peek());
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedPushMultipleElements() {
        for (int i = 0; i < 10; i++) {
            linked.push(i + "a");
        }
        assertEquals(10, linked.size());
        assertEquals("9a", linked.peek());
    }

    @Test(timeout = TIMEOUT)
    public void testArrayPop() {
        array.push("0a");
        array.push("1a");
        assertEquals("1a", array.pop());
        assertEquals(1, array.size());
        assertEquals("0a", array.peek());
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedPop() {
        linked.push("0a");
        linked.push("1a");
        assertEquals("1a", linked.pop());
        assertEquals(1, linked.size());
        assertEquals("0a", linked.peek());
    }

    @Test(timeout = TIMEOUT, expected = java.util.NoSuchElementException.class)
    public void testArrayPopEmpty() {
        array.pop();
    }

    @Test(timeout = TIMEOUT, expected = java.util.NoSuchElementException.class)
    public void testLinkedPopEmpty() {
        linked.pop();
    }

    @Test(timeout = TIMEOUT)
    public void testArrayPeek() {
        array.push("0a");
        array.push("1a");
        assertEquals("1a", array.peek());
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedPeek() {
        linked.push("0a");
        linked.push("1a");
        assertEquals("1a", linked.peek());
    }

    @Test(timeout = TIMEOUT, expected = java.util.NoSuchElementException.class)
    public void testArrayPeekEmpty() {
        array.peek();
    }

    @Test(timeout = TIMEOUT, expected = java.util.NoSuchElementException.class)
    public void testLinkedPeekEmpty() {
        linked.peek();
    }

    @Test(timeout = TIMEOUT)
    public void testArrayPushPopUntilEmpty() {
        for (int i = 0; i < 5; i++) {
            array.push(i + "a");
        }
        for (int i = 4; i >= 0; i--) {
            assertEquals(i + "a", array.pop());
        }
        assertEquals(0, array.size());
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedPushPopUntilEmpty() {
        for (int i = 0; i < 5; i++) {
            linked.push(i + "a");
        }
        for (int i = 4; i >= 0; i--) {
            assertEquals(i + "a", linked.pop());
        }
        assertEquals(0, linked.size());
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testArrayPushNull() {
        array.push(null);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testLinkedPushNull() {
        linked.push(null);
    }

    @Test(timeout = TIMEOUT)
    public void testArrayResizing() {
        for (int i = 0; i < 10; i++) {
            array.push(i + "a");
        }
        assertEquals(10, array.size());
        assertEquals("9a", array.peek());
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedLargeNumberOfElements() {
        for (int i = 0; i < 1000; i++) {
            linked.push(i + "a");
        }
        assertEquals(1000, linked.size());
        assertEquals("999a", linked.peek());
    }

    @Test(timeout = TIMEOUT)
    public void testArrayPushPopMixed() {
        for (int i = 0; i < 5; i++) {
            array.push(i + "a");
        }
        for (int i = 0; i < 3; i++) {
            array.pop();
        }
        for (int i = 5; i < 10; i++) {
            array.push(i + "a");
        }
        assertEquals(7, array.size());
        assertEquals("9a", array.peek());
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedPushPopMixed() {
        for (int i = 0; i < 5; i++) {
            linked.push(i + "a");
        }
        for (int i = 0; i < 3; i++) {
            linked.pop();
        }
        for (int i = 5; i < 10; i++) {
            linked.push(i + "a");
        }
        assertEquals(7, linked.size());
        assertEquals("9a", linked.peek());
    }

    @Test(timeout = TIMEOUT)
    public void testArrayPushPopLarge() {
        for (int i = 0; i < 1000; i++) {
            array.push(i + "a");
        }
        for (int i = 0; i < 500; i++) {
            array.pop();
        }
        assertEquals(500, array.size());
        assertEquals("499a", array.peek());
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedPushPopLarge() {
        for (int i = 0; i < 1000; i++) {
            linked.push(i + "a");
        }
        for (int i = 0; i < 500; i++) {
            linked.pop();
        }
        assertEquals(500, linked.size());
        assertEquals("499a", linked.peek());
    }

    @Test(timeout = TIMEOUT)
    public void testArrayPushPopResize() {
        for (int i = 0; i < 20; i++) {
            array.push(i + "a");
        }
        for (int i = 0; i < 10; i++) {
            array.pop();
        }
        assertEquals(10, array.size());
        assertEquals("9a", array.peek());
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedPushPopResize() {
        for (int i = 0; i < 20; i++) {
            linked.push(i + "a");
        }
        for (int i = 0; i < 10; i++) {
            linked.pop();
        }
        assertEquals(10, linked.size());
        assertEquals("9a", linked.peek());
    }
}