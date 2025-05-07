import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 * Note: you should have passed the BSTStudentTest first to avoid any unexpected behaviors from this test.
 */
public class BSTEdgeTest {
    private List<Integer> data =
            Arrays.asList(10, 2, 6, 7, 5, 1, 17, 13, 20);
    private BST<Integer> bst;

    /**
     * @param list a list of integers
     * @return a copy of the original list sorted in ascending order
     */
    private List<Integer> sorted(List<Integer> list) {
        return list.stream()
                .sorted(Comparator.comparing(a -> a))
                .collect(Collectors.toList());
    }

    @Before
    public void setup() {
        // Construct a new BST with data.
        bst = new BST<>(data);
    }

    @Test
    public void testRemove() {
        // Make sure that remove works for every node in the tree.
        for (int i : data) {
            bst = new BST<>(data);
            assertEquals(i, (int) bst.remove(i));
            List<Integer> dataSorted = sorted(data);
            dataSorted.removeIf(a -> a == i);
            assertEquals(dataSorted, bst.inorder());
            System.out.println(dataSorted);
        }

        // Make sure that remove uses predecessor when needed, instead of
        // successor.
        bst = new BST<>(data);
        bst.remove(10);
        assertEquals(7, (int) bst.getRoot().getData());
    }




    @Test(expected = IllegalArgumentException.class)
    public void testIllegalArgumentException() {
        // Test for BST::findPathBetween
        

        // Test for BST::contains
        bst.contains(null);

        // Test for BST::get
        bst.get(null);
    }

    @Test()
    public void testOrder() {
        // Test BST::inorder
        assertEquals(sorted(data), bst.inorder());
        // Test BST::levelorder
        assertEquals(
                Arrays.asList(10, 2, 17, 1, 6, 13, 20, 5, 7),
                bst.levelorder()
        );
        // Test BST::preorder
        assertEquals(
                Arrays.asList(10, 2, 1, 6, 5, 7, 17, 13, 20),
                bst.preorder()
        );
        // Test BST::postorder
        assertEquals(
                Arrays.asList(1, 5, 7, 6, 2, 13, 20, 17, 10),
                bst.postorder()
        );
    }

    @Test()
    public void testContains() {
        assertTrue(bst.contains(5));
        assertTrue(bst.contains(10));
        assertTrue(bst.contains(13));
        assertFalse(bst.contains(12));
    }

}