import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import org.junit.Before;
import org.junit.Test;

/**
 * This is a set of unit tests for ArrayQueue and LinkedQueue.
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
public class QueueStudentTest {

    private static final int TIMEOUT = 200;
    private ArrayQueue<String> array;
    private LinkedQueue<String> linked;

    @Before
    public void setup() {
        array = new ArrayQueue<>();
        linked = new LinkedQueue<>();
    }

    @Test(timeout = TIMEOUT)
    public void testArrayQueueInitialization() {
        assertEquals(0, array.size());
        assertArrayEquals(new Object[ArrayQueue.INITIAL_CAPACITY], array.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedQueueInitialization() {
        assertEquals(0, linked.size());
        assertNull(linked.getHead());
        assertNull(linked.getTail());
    }

    @Test(timeout = TIMEOUT)
    public void testArrayEnqueueOneElement() {
        array.enqueue("0a");
        assertEquals(1, array.size());
        assertEquals("0a", array.peek());
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedEnqueueOneElement() {
        linked.enqueue("0a");
        assertEquals(1, linked.size());
        assertEquals("0a", linked.peek());
    }

    @Test(timeout = TIMEOUT)
    public void testArrayEnqueueMultipleElements() {
        for (int i = 0; i < 10; i++) {
            array.enqueue(i + "a");
        }
        assertEquals(10, array.size());
        assertEquals("0a", array.peek());
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedEnqueueMultipleElements() {
        for (int i = 0; i < 10; i++) {
            linked.enqueue(i + "a");
        }
        assertEquals(10, linked.size());
        assertEquals("0a", linked.peek());
    }

    @Test(timeout = TIMEOUT)
    public void testArrayDequeue() {
        array.enqueue("0a");
        array.enqueue("1a");
        assertEquals("0a", array.dequeue());
        assertEquals(1, array.size());
        assertEquals("1a", array.peek());
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedDequeue() {
        linked.enqueue("0a");
        linked.enqueue("1a");
        assertEquals("0a", linked.dequeue());
        assertEquals(1, linked.size());
        assertEquals("1a", linked.peek());
    }

    @Test(timeout = TIMEOUT, expected = java.util.NoSuchElementException.class)
    public void testArrayDequeueEmpty() {
        array.dequeue();
    }

    @Test(timeout = TIMEOUT, expected = java.util.NoSuchElementException.class)
    public void testLinkedDequeueEmpty() {
        linked.dequeue();
    }

    @Test(timeout = TIMEOUT)
    public void testArrayPeek() {
        array.enqueue("0a");
        array.enqueue("1a");
        assertEquals("0a", array.peek());
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedPeek() {
        linked.enqueue("0a");
        linked.enqueue("1a");
        assertEquals("0a", linked.peek());
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
    public void testArrayEnqueueDequeueUntilEmpty() {
        for (int i = 0; i < 5; i++) {
            array.enqueue(i + "a");
        }
        for (int i = 0; i < 5; i++) {
            assertEquals(i + "a", array.dequeue());
        }
        assertEquals(0, array.size());
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedEnqueueDequeueUntilEmpty() {
        for (int i = 0; i < 5; i++) {
            linked.enqueue(i + "a");
        }
        for (int i = 0; i < 5; i++) {
            assertEquals(i + "a", linked.dequeue());
        }
        assertEquals(0, linked.size());
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testArrayEnqueueNull() {
        array.enqueue(null);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testLinkedEnqueueNull() {
        linked.enqueue(null);
    }

    @Test(timeout = TIMEOUT)
    public void testArrayCircularBehavior() {
        for (int i = 0; i < 5; i++) {
            array.enqueue(i + "a");
        }
        for (int i = 0; i < 3; i++) {
            array.dequeue();
        }
        for (int i = 5; i < 10; i++) {
            array.enqueue(i + "a");
        }
        assertEquals(7, array.size());
        assertEquals("3a", array.peek());
    }

    @Test(timeout = TIMEOUT)
    public void testArrayResizing() {
        for (int i = 0; i < 10; i++) {
            array.enqueue(i + "a");
        }
        assertEquals(10, array.size());
        assertEquals("0a", array.peek());
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedLargeNumberOfElements() {
        for (int i = 0; i < 1000; i++) {
            linked.enqueue(i + "a");
        }
        assertEquals(1000, linked.size());
        assertEquals("0a", linked.peek());
    }

    @Test(timeout = TIMEOUT)
    public void testArrayDequeueAllAndEnqueueAgain() {
        for (int i = 0; i < 5; i++) {
            array.enqueue(i + "a");
        }
        for (int i = 0; i < 5; i++) {
            array.dequeue();
        }
        for (int i = 0; i < 5; i++) {
            array.enqueue(i + "b");
        }
        assertEquals(5, array.size());
        assertEquals("0b", array.peek());
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedDequeueAllAndEnqueueAgain() {
        for (int i = 0; i < 5; i++) {
            linked.enqueue(i + "a");
        }
        for (int i = 0; i < 5; i++) {
            linked.dequeue();
        }
        for (int i = 0; i < 5; i++) {
            linked.enqueue(i + "b");
        }
        assertEquals(5, linked.size());
        assertEquals("0b", linked.peek());
    }

    @Test(timeout = TIMEOUT)
    public void testArrayEnqueueAfterResizing() {
        for (int i = 0; i < 20; i++) {
            array.enqueue(i + "a");
        }
        assertEquals(20, array.size());
        assertEquals("0a", array.peek());
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedEnqueueAfterLargeNumberOfDequeues() {
        for (int i = 0; i < 100; i++) {
            linked.enqueue(i + "a");
        }
        for (int i = 0; i < 50; i++) {
            linked.dequeue();
        }
        for (int i = 100; i < 150; i++) {
            linked.enqueue(i + "a");
        }
        assertEquals(100, linked.size());
        assertEquals("50a", linked.peek());
    }

    @Test(timeout = TIMEOUT)
    public void testArrayPeekAfterMultipleDequeues() {
        for (int i = 0; i < 10; i++) {
            array.enqueue(i + "a");
        }
        for (int i = 0; i < 5; i++) {
            array.dequeue();
        }
        assertEquals("5a", array.peek());
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedPeekAfterMultipleDequeues() {
        for (int i = 0; i < 10; i++) {
            linked.enqueue(i + "a");
        }
        for (int i = 0; i < 5; i++) {
            linked.dequeue();
        }
        assertEquals("5a", linked.peek());
    }

    @Test(timeout = TIMEOUT)
    public void testArrayEnqueueDequeueMixed() {
        for (int i = 0; i < 5; i++) {
            array.enqueue(i + "a");
        }
        for (int i = 0; i < 3; i++) {
            array.dequeue();
        }
        for (int i = 5; i < 10; i++) {
            array.enqueue(i + "a");
        }
        assertEquals(7, array.size());
        assertEquals("3a", array.peek());
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedEnqueueDequeueMixed() {
        for (int i = 0; i < 5; i++) {
            linked.enqueue(i + "a");
        }
        for (int i = 0; i < 3; i++) {
            linked.dequeue();
        }
        for (int i = 5; i < 10; i++) {
            linked.enqueue(i + "a");
        }
        assertEquals(7, linked.size());
        assertEquals("3a", linked.peek());
    }

    @Test(timeout = TIMEOUT)
    public void testArrayEnqueueDequeueWrapAround() {
        for (int i = 0; i < 5; i++) {
            array.enqueue(i + "a");
        }
        for (int i = 0; i < 3; i++) {
            array.dequeue();
        }
        for (int i = 5; i < 10; i++) {
            array.enqueue(i + "a");
        }
        assertEquals(7, array.size());
        assertEquals("3a", array.peek());
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedEnqueueDequeueLarge() {
        for (int i = 0; i < 1000; i++) {
            linked.enqueue(i + "a");
        }
        for (int i = 0; i < 500; i++) {
            linked.dequeue();
        }
        assertEquals(500, linked.size());
        assertEquals("500a", linked.peek());
    }
}