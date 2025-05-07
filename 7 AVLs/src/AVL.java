import java.util.Collection;
import java.util.NoSuchElementException;

/**
 * Your implementation of an AVL Tree.
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
public class AVL<T extends Comparable<? super T>> {
    // DO NOT ADD OR MODIFY INSTANCE VARIABLES.
    private AVLNode<T> root;
    private int size;

    /**
     * A no-argument constructor that should initialize an empty AVL.
     *
     * Since instance variables are initialized to their default values, there
     * is no need to do anything for this constructor.
     */
    public AVL() {
        // DO NOT IMPLEMENT THIS CONSTRUCTOR!
    }

    /**
     * Initializes the AVL tree with the data in the Collection. The data
     * should be added in the same order it appears in the Collection.
     *
     * @throws IllegalArgumentException if data or any element in data is null
     * @param data the data to add to the tree
     */
    public AVL(Collection<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot create AVL tree with null data!");
        }
        size = 0;
        for (T element:data) {
            if (element == null) {
                throw new IllegalArgumentException("Cannot create AVL tree with null element in data!");
            }
            add(element);
        }
    }

    /**
     * Adds the data to the AVL. Start by adding it as a leaf like in a regular
     * BST and then rotate the tree as needed.
     *
     * If the data is already in the tree, then nothing should be done (the
     * duplicate shouldn't get added, and size should not be incremented).
     *
     * Remember to recalculate heights and balance factors going up the tree,
     * rebalancing if necessary.
     *
     * @throws java.lang.IllegalArgumentException if the data is null
     * @param data the data to be added
     */
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot add null data to the BST");
        }
        root = addHelper(data, root);
    }

    /**
     * add helper method
     * @param data data to be added
     * @param node node for adding
     * @return node to be balanced
     */
    private AVLNode<T> addHelper(T data, AVLNode<T> node) {
        if (node == null) {
            size++;
            return new AVLNode<>(data);
        } else if (data.compareTo(node.getData()) > 0) {
            node.setRight(addHelper(data, node.getRight()));
        } else if (data.compareTo(node.getData()) < 0) {
            node.setLeft(addHelper(data, node.getLeft()));
        }
        adjustNode(node);
        if (Math.abs(node.getBalanceFactor()) > 1) {
            node = rebalance(node);
        }
        return node;
    }

    /**
     * helper method for changing heights and balancefactor
     * @param node node which the balancefactor and height needs to be changed
     */
    private void adjustNode(AVLNode<T> node) {
        int left = nodeHeight(node.getLeft());
        int right = nodeHeight(node.getRight());
        node.setBalanceFactor(left - right);
        node.setHeight(1 + Math.max(left, right));
    }

    /**
     * helper method for finding the height of a node
     * @param node node that needs height
     * @return height
     */
    private int nodeHeight(AVLNode<T> node) {
        if (node == null) {
            return -1;
        }
        return node.getHeight();
    }

    /**
     * helper method for balancing the tree
     * @param node the root of a subtree to balance
     * @return the new root of the subtree
     */
    private AVLNode<T> rebalance(AVLNode<T> node) {
        AVLNode<T> returnNode = node;
        if (node.getBalanceFactor() > 0) {
            if (node.getLeft().getBalanceFactor() < 0) {
                node.setLeft(rotateLeft(node.getLeft()));
                returnNode = rotateRight(node);
            } else {
                returnNode = rotateRight(node);
            }
        } else if (node.getBalanceFactor() < 0) {
            if (node.getRight().getBalanceFactor() > 0) {
                node.setRight(rotateRight(node.getRight()));
                returnNode = rotateLeft(node);
            } else {
                returnNode = rotateLeft(node);
            }
        }
        return returnNode;
    }

    /**
     * helper method for rotating left
     * @param node root of subtree to rotate
     * @return root node of updated subtree
     */
    private AVLNode<T> rotateLeft(AVLNode<T> node) {
        AVLNode<T> rightNode = node.getRight();
        node.setRight(rightNode.getLeft());
        rightNode.setLeft(node);
        adjustNode(node);
        adjustNode(rightNode);
        return rightNode;
    }

    /**
     * helper method for rotating right
     * @param node root of subtree to rotate
     * @return root node of updated subtree
     */
    private AVLNode<T> rotateRight(AVLNode<T> node) {
        AVLNode<T> leftNode = node.getLeft();
        node.setLeft(leftNode.getRight());
        leftNode.setRight(node);
        adjustNode(node);
        adjustNode(leftNode);
        return leftNode;
    }

    /**
     * Removes the data from the tree. There are 3 cases to consider:
     *
     * 1: the data is a leaf. In this case, simply remove it.
     * 2: the data has one child. In this case, simply replace it with its
     * child.
     * 3: the data has 2 children. Use the predecessor to replace the data,
     * not the successor. As a reminder, rotations can occur after removing
     * the predecessor node.
     *
     * Remember to recalculate heights going up the tree, rebalancing if
     * necessary.
     *
     * @throws IllegalArgumentException if the data is null
     * @throws java.util.NoSuchElementException if the data is not found
     * @param data the data to remove from the tree.
     * @return the data removed from the tree. Do not return the same data
     * that was passed in.  Return the data that was stored in the tree.
     */
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot remove null data from AVL tree");
        }
        AVLNode<T> temp = new AVLNode<T>(null);
        root = removeHelper(root, data, temp);
        size--;
        return temp.getData();
    }
    /**
     * 
     * @param data data to be removed
     * @param node reference to be swapped
     * @param dummy node to be removed
     * @return value that is removed
     */
    private AVLNode<T> removeHelper(AVLNode<T> node, T data, AVLNode<T> dummy) {
        if (node == null) {
            throw new NoSuchElementException("Data was not found in the BST");
        }
        int diff = data.compareTo(node.getData());
        if (diff < 0) {
            node.setLeft(removeHelper(node.getLeft(), data, dummy));
        } else if (diff > 0) {
            node.setRight(removeHelper(node.getRight(), data, dummy));
        } else {
            dummy.setData(node.getData());
            if (node.getLeft() == null && node.getRight() == null) {
                return null;
            } else if (node.getLeft() == null) {
                return node.getRight();
            } else if (node.getRight() == null) {
                return node.getLeft();
            } else {
                AVLNode<T> predecessor = new AVLNode<T>(null);
                node.setLeft(predecessor(node.getLeft(), predecessor));
                node.setData(predecessor.getData());
            }
        }
        adjustNode(node);
        if (Math.abs(node.getBalanceFactor()) > 1) {
            node = rebalance(node);
        }
        
        return node;
    }


    /**
    * Helper method for removeHelper for 2 children
    * @param node Node above successor
    * @param predecessor Node below root
    * @return predecessor node to be returned
    */
    private AVLNode<T> predecessor(AVLNode<T> node, AVLNode<T> predecessor) {
        if (node.getRight() == null) {
            predecessor.setData(node.getData());
            return node.getLeft();
        } else {
            node.setRight(predecessor(node.getRight(), predecessor));
        }
        return node;
    }

    /**
     * Returns the data in the tree matching the parameter passed in (think
     * carefully: should you use value equality or reference equality?).
     *
     * @throws IllegalArgumentException if the data is null
     * @throws java.util.NoSuchElementException if the data is not found
     * @param data the data to search for in the tree.
     * @return the data in the tree equal to the parameter. Do not return the
     * same data that was passed in.  Return the data that was stored in the
     * tree.
     */
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot get null data from AVL tree");
        }
        return getHelper(data, root);
    }
    /**
     * 
     * @param value value to be looked for
     * @param current current node to be searched
     * @return value if data is found
     */
    private T getHelper(T value, AVLNode<T> current) {
        if (current == null) {
            throw new NoSuchElementException(value + "is not in the AVL tree");
        }
        
        if (value.compareTo(current.getData()) < 0) {
            return getHelper(value, current.getLeft());
        } else if (value.compareTo(current.getData()) > 0) {
            return getHelper(value, current.getRight());
        } else {
            return current.getData();
        }
    }


    /**
     * Returns whether or not data equivalent to the given parameter is
     * contained within the tree. The same type of equality should be used as
     * in the get method.
     *
     * @throws IllegalArgumentException if the data is null
     * @param data the data to search for in the tree.
     * @return whether or not the parameter is contained within the tree.
     */
    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot check existence of null data.");
        }
        return containsHelper(data, root);
    }
    /**
     * 
     * @param value value to be looked for
     * @param current current node
     * @return if value exists
     */
    private boolean containsHelper(T value, AVLNode<T> current) {
        if (current == null) {
            return false;
        }
        
        if (value.compareTo(current.getData()) < 0) {
            return containsHelper(value, current.getLeft());
        } else if (value.compareTo(current.getData()) > 0) {
            return containsHelper(value, current.getRight());
        } else {
            return true;
        }
    }
    

    /**
     * Finds and retrieves the median data of the passed in AVL. 
     * 
     * This method will not need to traverse the entire tree to
     * function properly, so you should only traverse enough branches of the tree
     * necessary to find the median data and only do so once. Failure to do so will
     * result in an efficiency penalty. Additionally, note that this should be completed
     * with O(1) additional memory, not including the memory used by the call stack.
     *
     * Ex:
     * Given the following AVL composed of Integers
     *              50
     *            /    \
     *         25      75
     *        /  \     / \
     *      13   37  70  80
     *    /  \    \      \
     *   12  15    40    85
     * 
     * findMedian() should return 40
     *
     * @throws java.util.NoSuchElementException if the tree is empty or contains an even number of data
     * @return the median data of the AVL
     */
    public T findMedian() {
        if (size == 0 || size % 2 == 0) {
            throw new NoSuchElementException("AVL Tree is empty");
        }
        return findKthNode(root, size / 2, new int[]{0});
    }

    /**
     * helper method for finding median node
     * @param node current node
     * @param k median node count
     * @param count current node count
     * @return median node
     */
    private T findKthNode(AVLNode<T> node, int k, int[] count) {
        if (node == null) {
            return null;
        }
        
        T leftResult = findKthNode(node.getLeft(), k, count);
        if (leftResult != null) {
            return leftResult;
        }
        
        if (count[0] == k) {
            return node.getData();
        }
        count[0]++;
        
        return findKthNode(node.getRight(), k, count);
    }
    

    /**
     * Clears the tree.
     */
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Returns the height of the root of the tree.
     *
     * Since this is an AVL, this method does not need to traverse the tree
     * and should be O(1)
     *
     * @return the height of the root of the tree, -1 if the tree is empty
     */
    public int height() {
        return nodeHeight(root);
    }

    /**
     * Returns the size of the AVL tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return number of items in the AVL tree
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD
        return size;
    }

    /**
     * Returns the root of the AVL tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the root of the AVL tree
     */
    public AVLNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }
}