import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

/**
 * Your JUnit suite for ArrayList.
 *
 * @author Akash Satya
 * @version 1.0
 * @userid asatya8
 * @GTID 903896933
 *
 * Collaborators: N/A
 *
 * Resources: N/A
 * 
 * By typing 'I agree' below, you are agreeing that this is your
 * own work and that you are responsible for all the contents of 
 * this file. If this is left blank, this homework will receive a zero.
 * 
 * Agree Here: I agree
 * 
 */
public class ArrayListStudentTestEC {

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
        assertTrue(list.isEmpty());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddSmallIndex() {
        list.addAtIndex(-1, "index is less than 0");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddBigIndex() {
        list.addAtIndex(1, "index is more than size");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNull() {
        list.addAtIndex(0, null);
    }
    @Test(timeout = TIMEOUT)
    public void testAddToFront() {
        list.addToFront("787878");  
        list.addToFront("786");  

        assertEquals(2, list.size());

        Object[] expected = new Object[ArrayList.INITIAL_CAPACITY];
        expected[0] = "786";
        expected[1] = "787878";
        assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testAddToBack() {
        list.addToBack("787878");  
        list.addToBack("786");  

        assertEquals(2, list.size());

        Object[] expected = new Object[ArrayList.INITIAL_CAPACITY];
        expected[0] = "787878";
        expected[1] = "786";
        assertArrayEquals(expected, list.getBackingArray());
    }
    @Test(timeout = TIMEOUT)
    public void testAddAtIndex() {
        list.addAtIndex(0, "213");   
        list.addAtIndex(1, "175");   
        list.addAtIndex(2, "345");  

        assertEquals(3, list.size());

        Object[] expected = new Object[ArrayList.INITIAL_CAPACITY];
        expected[1] = "175";
        expected[0] = "213";
        expected[2] = "345";
        assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
public void testReSize() {
    list.addAtIndex(0, "str1");
    list.addAtIndex(0, "str2");
    list.addAtIndex(0, "str3");
    list.addAtIndex(1, "str4");
    assertEquals(4, list.size());
    
    Object[] expected = new Object[ArrayList.INITIAL_CAPACITY];
    String[] expectedItems = {"str3", "str4", "str2", "str1"};
    for (int i = 0; i < expectedItems.length; i++) {
        expected[i] = expectedItems[i];
    }
    assertArrayEquals(expected, list.getBackingArray());
    
    
    list.clear();
    for (int i = 1; i <= 10; i++) {
        list.addAtIndex(list.size(), "" + i);  
    }
    Object[] expectedFull = new Object[18];
    for (int i = 0; i < 10; i++) {
        expectedFull[i] = "" + (i + 1);
    }
    assertEquals(10, list.size());
    assertArrayEquals(expectedFull, list.getBackingArray());
    
    list.addToFront("front");
    Object[] expectedFront = new Object[18];
    expectedFront[0] = "front";
    for (int i = 0; i < 10; i++) {
        expectedFront[i + 1] = "" + (i + 1);
    }
    assertArrayEquals(expectedFront, list.getBackingArray());
    
    list.addToBack("back");
    Object[] expectedBack = new Object[18];
    expectedBack[0] = "front";
    for (int i = 0; i < 10; i++) {
        expectedBack[i + 1] = "" + (i + 1);
    }
    expectedBack[11] = "back";
    assertArrayEquals(expectedBack, list.getBackingArray());
}
    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveSmallIndex() {
        list.removeAtIndex(-1);
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testRemoveBigIndex() {
        list.removeAtIndex(1);
    }

    @Test (expected = NoSuchElementException.class)
    public void testEmptyRemoveFront() {
        list.removeFromFront();
    }

    @Test (expected = NoSuchElementException.class)
    public void testEmptyRemoveBack() {
        list.removeFromBack();
    }


    @Test(timeout = TIMEOUT)
    public void testRemoveAtIndex() {

        list.addAtIndex(0, "123"); 
        list.addAtIndex(1, "234"); 
        list.addAtIndex(2, "235"); 
        assertEquals(3, list.size());

        
        assertSame("234", list.removeAtIndex(1));    

        assertEquals(2, list.size());
        Object[] expected = new Object[ArrayList.INITIAL_CAPACITY];
        expected[0] = "123";
        expected[1] = "235";
        assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveFromFront() {

        list.addAtIndex(0, "1233"); 
        list.addAtIndex(1, "123");  
        assertEquals(2, list.size());

        assertSame("1233", list.removeFromFront());  

        assertEquals(1, list.size());

        Object[] expected = new Object[ArrayList.INITIAL_CAPACITY];
        expected[0] = "123";
        assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveFromBack() {

        list.addAtIndex(0, "1233"); 
        list.addAtIndex(1, "123");  
        assertEquals(2, list.size());

        assertSame("123", list.removeFromBack());  

        assertEquals(1, list.size());

        Object[] expected = new Object[ArrayList.INITIAL_CAPACITY];
        expected[0] = "1233";
        assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testGet() {
        list.addAtIndex(0, "123"); 
        assertEquals(1, list.size());

        assertEquals("123", list.get(0));
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testSmallGetIndex() {
        list.get(-1);
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testBigGetIndex() {
        list.get(0);
    }

    @Test(timeout = TIMEOUT)
    public void testIsEmpty() {
        assertTrue(list.isEmpty());

        list.addAtIndex(0, "132");
        assertEquals(1, list.size());

        assertFalse(list.isEmpty());
    }

    @Test(timeout = TIMEOUT)
    public void testClear() {
        list.addAtIndex(0, "123");

        list.clear();

        assertEquals(0, list.size());
        assertArrayEquals(new Object[ArrayList.INITIAL_CAPACITY],
                list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void myTestRemoveAtIndex() {
        list.addAtIndex(0, "345");
        list.addAtIndex(1, "234");
        list.addAtIndex(2, "123");
        list.addAtIndex(3, "2");
        list.addAtIndex(4, "4");
        list.addAtIndex(5, "5");
        list.addAtIndex(6, "6");
        list.addAtIndex(7, "7");
        list.addAtIndex(8, "8");

        list.removeAtIndex(1);
        Object[] expected = new Object[]{"345","123", "2", "4","5","6","7","8",null};
        assertArrayEquals(expected, list.getBackingArray());
        list.removeAtIndex(0);
        Object[] expectedFront = new Object[]{"123", "2", "4","5","6","7","8",null,null};
        assertArrayEquals(expectedFront, list.getBackingArray());
        list.removeAtIndex(list.size() - 1);
        Object[] expectedBack = new Object[]{ "123","2", "4","5","6","7",null,null,null};
        assertArrayEquals(expectedBack, list.getBackingArray());
    }


    



    
 }