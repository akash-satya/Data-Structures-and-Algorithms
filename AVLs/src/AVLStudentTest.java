import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * These tests are not exhaustive.
 * 
 * @author CS 1332 TAs
 * @author yash
 * @version 1.0
 */
public class AVLStudentTest {
  private static final int TIMEOUT = 200;
  private AVL<Integer> avlTree;

  @Before
  public void setup() {
    avlTree = new AVL<>();
  }

  @Test(timeout = TIMEOUT)
  public void testAddRightRotation() {
    /*
     * 5 4
     * / / \
     * 4 -> 3 5
     * /
     * 3
     */
    avlTree.add(5);
    avlTree.add(4);
    avlTree.add(3);

    assertEquals(3, avlTree.size());

    AVLNode<Integer> root = avlTree.getRoot();
    assertEquals((Integer) 4, root.getData());
    assertEquals(1, root.getHeight());
    assertEquals(0, root.getBalanceFactor());
    assertEquals((Integer) 3, root.getLeft().getData());
    assertEquals(0, root.getLeft().getHeight());
    assertEquals(0, root.getLeft().getBalanceFactor());
    assertEquals((Integer) 5, root.getRight().getData());
    assertEquals(0, root.getRight().getHeight());
    assertEquals(0, root.getRight().getBalanceFactor());
  }

  @Test(timeout = TIMEOUT)
  public void testAddRightLeftRotationRoot() {
    /*
     * 3 4
     * \ / \
     * 5 -> 3 5
     * /
     * 4
     */
    avlTree.add(3);
    avlTree.add(5);
    avlTree.add(4);

    assertEquals(3, avlTree.size());

    AVLNode<Integer> root = avlTree.getRoot();
    assertEquals((Integer) 4, root.getData());
    assertEquals(1, root.getHeight());
    assertEquals(0, root.getBalanceFactor());
    assertEquals((Integer) 3, root.getLeft().getData());
    assertEquals(0, root.getLeft().getHeight());
    assertEquals(0, root.getLeft().getBalanceFactor());
    assertEquals((Integer) 5, root.getRight().getData());
    assertEquals(0, root.getRight().getHeight());
    assertEquals(0, root.getRight().getBalanceFactor());
  }

  @Test(timeout = TIMEOUT)
  public void testRemove() {
    /*
     * 646 646
     * / \ / \
     * 477 856 -> 386 856
     * / \ \
     * 386 526 526
     */
    Integer toBeRemoved = new Integer(477);
    avlTree.add(646);
    avlTree.add(toBeRemoved);
    avlTree.add(856);
    avlTree.add(386);
    avlTree.add(526);

    assertSame(toBeRemoved, avlTree.remove(new Integer(477)));

    assertEquals(4, avlTree.size());

    AVLNode<Integer> root = avlTree.getRoot();
    assertEquals((Integer) 646, root.getData());
    assertEquals(2, root.getHeight());
    assertEquals(1, root.getBalanceFactor());
    assertEquals((Integer) 386, root.getLeft().getData());
    assertEquals(1, root.getLeft().getHeight());
    assertEquals(-1, root.getLeft().getBalanceFactor());
    assertEquals((Integer) 856, root.getRight().getData());
    assertEquals(0, root.getRight().getHeight());
    assertEquals(0, root.getRight().getBalanceFactor());
    assertEquals((Integer) 526, root.getLeft().getRight().getData());
    assertEquals(0, root.getLeft().getRight().getHeight());
    assertEquals(0, root.getLeft().getRight().getBalanceFactor());
  }

  @Test(timeout = TIMEOUT)
  public void testGet() {
    /*
     * 477
     * / \
     * 386 526
     * \
     * 646
     */
    Integer maximum = new Integer(646);
    avlTree.add(477);
    avlTree.add(526);
    avlTree.add(386);
    avlTree.add(maximum);

    assertSame(maximum, avlTree.get(new Integer(646)));
  }

  @Test(timeout = TIMEOUT)
  public void testContains() {
    /*
     * 477
     * / \
     * 386 526
     * \
     * 646
     */
    avlTree.add(new Integer(477));
    avlTree.add(new Integer(526));
    avlTree.add(new Integer(386));
    avlTree.add(new Integer(646));

    assertEquals(true, avlTree.contains(new Integer(477)));
    assertEquals(true, avlTree.contains(new Integer(386)));
    assertEquals(true, avlTree.contains(new Integer(646)));
    assertEquals(false, avlTree.contains(new Integer(387)));
    assertEquals(false, avlTree.contains(new Integer(700)));
    assertEquals(false, avlTree.contains(new Integer(500)));
  }

  @Test(timeout = TIMEOUT)
  public void testHeight() {
    /*
     * 646
     * / \
     * 477 856
     * / \
     * 386 526
     */
    avlTree.add(646);
    avlTree.add(386);
    avlTree.add(856);
    avlTree.add(526);
    avlTree.add(477);

    assertEquals(2, avlTree.height());
  }

  @Test(timeout = TIMEOUT)
  public void testFindMedian() {
    /*
     * 76
     * / \
     * 34 90
     * / \
     * 20 40
     * 
     * median: 40
     */

    avlTree.add(76);
    avlTree.add(34);
    avlTree.add(90);
    avlTree.add(20);
    avlTree.add(40);

    AVLNode<Integer> root = avlTree.getRoot();
    assertEquals(root.getLeft().getRight().getData(), avlTree.findMedian());

    /*
     * 76
     * / \
     * 34 90
     * / \ / \
     * 20 40 81 100
     * 
     * median: 76
     */
    avlTree.add(81);
    avlTree.add(100);
    assertEquals(root.getData(), avlTree.findMedian());
  }

  @Test(timeout = TIMEOUT)
  public void testConstructorAndClear() {
    /*
     * 7
     * / \
     * 1 24
     */

    List<Integer> toAdd = new ArrayList<>();
    toAdd.add(7);
    toAdd.add(24);
    toAdd.add(1);
    avlTree = new AVL<>(toAdd);

    avlTree.clear();
    assertEquals(null, avlTree.getRoot());
    assertEquals(0, avlTree.size());
  }

  @Test(timeout = TIMEOUT)
  public void testAddDuplicate() {
    avlTree.add(5);
    avlTree.add(3);
    avlTree.add(7);

    int initialSize = avlTree.size();
    avlTree.add(5); // Adding a duplicate

    assertEquals(initialSize, avlTree.size());
  }

  @Test(timeout = TIMEOUT)
  public void testRemoveRoot() {
    avlTree.add(10);
    avlTree.add(5);
    avlTree.add(15);

    avlTree.remove(10);

    assertEquals(2, avlTree.size());
    assertEquals((Integer) 5, avlTree.getRoot().getData());
  }

  @Test(timeout = TIMEOUT)
  public void testFindMedianOdd() {
    avlTree.add(10);
    avlTree.add(20);
    avlTree.add(15);

    assertEquals((Integer) 15, avlTree.findMedian());
  }

  @Test(timeout = TIMEOUT)
  public void testFindMedianEven() {
    avlTree.add(10);
    avlTree.add(20);
    avlTree.add(15);
    avlTree.add(25);

    try {
      avlTree.findMedian();
    } catch (NoSuchElementException e) {
      return; // Expected exception
    }
    assert false : "Expected NoSuchElementException";
  }

  @Test(timeout = TIMEOUT)
  public void testRemoveLeaf() {
    avlTree.add(10);
    avlTree.add(5);
    avlTree.add(15);

    avlTree.remove(5);

    assertEquals(2, avlTree.size());
    assertEquals((Integer) 10, avlTree.getRoot().getData());
    assertEquals((Integer) 15, avlTree.getRoot().getRight().getData());
  }

  @Test(timeout = TIMEOUT)
  public void testClear() {
    avlTree.add(10);
    avlTree.add(20);
    avlTree.add(15);

    avlTree.clear();

    assertEquals(0, avlTree.size());
    assertEquals(-1, avlTree.height());
  }

  @Test(timeout = TIMEOUT)
  public void testHeightEmpty() {
    assertEquals(-1, avlTree.height());
  }

  @Test(timeout = TIMEOUT)
  public void testAddSingleElement() {
    avlTree.add(10);

    assertEquals(1, avlTree.size());
    assertEquals((Integer) 10, avlTree.getRoot().getData());
    assertEquals(0, avlTree.getRoot().getHeight());
  }

  @Test(timeout = TIMEOUT)
  public void testRemoveNodeWithTwoChildren() {
    avlTree.add(10);
    avlTree.add(5);
    avlTree.add(20);
    avlTree.add(15);

    avlTree.remove(10);

    assertEquals(3, avlTree.size());
    assertEquals((Integer) 15, avlTree.getRoot().getData()); // Predecessor of 10
  }

  @Test(timeout = TIMEOUT)
  public void testMultipleRotations() {
    avlTree.add(10);
    avlTree.add(5);
    avlTree.add(6);
    avlTree.add(4);
    avlTree.add(2);
    avlTree.add(0);

    /*
     *     4
     *    /  \
     *   1    6
     *  /    / \
     * 0    5  10
     */

    assertEquals(6, avlTree.size());
    assertEquals((Integer) 4, avlTree.getRoot().getData()); // Root after rotations
  }

  @Test(timeout = TIMEOUT)
  public void testAddNull() {
    try {
      avlTree.add(null);
    } catch (IllegalArgumentException e) {
      return; // Expected exception
    }
    assert false : "Expected IllegalArgumentException";
  }

  @Test(timeout = TIMEOUT)
  public void testSizeAfterAddRemove() {
    avlTree.add(10);
    avlTree.add(20);
    avlTree.add(5);

    assertEquals(3, avlTree.size());

    avlTree.remove(5);
    assertEquals(2, avlTree.size());

    avlTree.add(15);
    assertEquals(3, avlTree.size());
  }

}