import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * Your implementation of a BST.
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
public class BST<T extends Comparable<? super T>> {

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private BSTNode<T> root;
    private int size;

    /**
     * Constructs a new BST.
     *
     * This constructor should initialize an empty BST.
     *
     * Since instance variables are initialized to their default values, there
     * is no need to do anything for this constructor.
     */
    public BST() {
        // DO NOT IMPLEMENT THIS CONSTRUCTOR!
    }

    /**
     * Constructs a new BST.
     *
     * This constructor should initialize the BST with the data in the
     * Collection. The data should be added in the same order it is in the
     * Collection.
     *
     * Hint: Not all Collections are indexable like Lists, so a regular for loop
     * will not work here. However, all Collections are Iterable, so what type
     * of loop would work?
     *
     * @param data the data to add
     * @throws java.lang.IllegalArgumentException if data or any element in data
     *                                            is null
     */
    public BST(Collection<T> data) {
        if (data == null || data.contains(null)) {
            throw new IllegalArgumentException("Cannot add null data to the BST");
        }
        size = 0;
        for (T element:data) {
            add(element);
        }
        

    }
    /**
     * add helper method
     * @param data data to be added
     * @param node node for adding
     */
    private void addHelper(T data, BSTNode<T> node) {
        if (data.compareTo(node.getData()) > 0) {
            if (node.getRight() == null) {
                node.setRight(new BSTNode<T>(data));
                size++;
            } else {
                addHelper(data, node.getRight());
            }
        } else if (data.compareTo(node.getData()) < 0) {
            if (node.getLeft() == null) {
                node.setLeft(new BSTNode<T>(data));
                size++;
            } else {
                addHelper(data, node.getLeft());
            }
        }
    }

    /**
     * Adds the data to the tree.
     *
     * This must be done recursively.
     *
     * The data becomes a leaf in the tree.
     *
     * Traverse the tree to find the appropriate location. If the data is
     * already in the tree, then nothing should be done (the duplicate
     * shouldn't get added, and size should not be incremented).
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to add
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot add null data to the BST");
        }
        if (root == null) {
            root = new BSTNode<T>(data);
            size++;
        } else {
            addHelper(data, root);
        }
    }


    /**
     * Helper method for remove
     * @param node node that is being traversed
     * @param data data to be found and removed
     * @param dummy dummy node for removed data
     * @return node to be removed
     */
    private BSTNode<T> removeHelper(BSTNode<T> node, T data, BSTNode<T> dummy) {
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
                BSTNode<T> successor = new BSTNode<T>(null);
                node.setRight(successor(node.getRight(), successor));
                node.setData(successor.getData());
            }
        }
        return node;
    }
    

    /**
     * Helper method for removeHelper for 2 children
     * @param node Node above successor
     * @param successor Node below root
     * @return successor node to be returned
     */
    private BSTNode<T> successor(BSTNode<T> node, BSTNode<T> successor) {
        if (node.getLeft() == null) {
            successor.setData(node.getData());
            return node.getRight();
        } else {
            node.setLeft(successor(node.getLeft(), successor));
        }
        return node;
    }


    /**
     * Removes and returns the data from the tree matching the given parameter.
     *
     * This must be done recursively.
     *
     * There are 3 cases to consider:
     * 1: The node containing the data is a leaf (no children). In this case,
     * simply remove it.
     * 2: The node containing the data has one child. In this case, simply
     * replace it with its child.
     * 3: The node containing the data has 2 children. Use the successor to
     * replace the data. You MUST use recursion to find and remove the
     * successor (you will likely need an additional helper method to
     * handle this case efficiently).
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to remove
     * @return the data that was removed
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     */
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot remove null data from BST");
        }
        BSTNode<T> temp = new BSTNode<>(null);
        root = removeHelper(root, data, temp);
        size--;
        return temp.getData();
    }


    /**
     * Helper method for getting the data
     * @param data data to be found
     * @param node node to be searched
     * @return data that is found
     */
    private T getHelper(T data, BSTNode<T> node) {
        if (node == null) {
            throw new NoSuchElementException("Data is not in the BST");
        }
        int diff = data.compareTo(node.getData());
        if (data.equals(node.getData())) {
            return node.getData();
        } else if (diff < 0) {
            return getHelper(data, node.getLeft());
        } else if (diff > 0) {
            return getHelper(data, node.getRight());
        }
        return null;
    }

    /**
     * Returns the data from the tree matching the given parameter.
     *
     * This must be done recursively.
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to search for
     * @return the data in the tree equal to the parameter
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     */
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot get null data from BST");
        }
        return getHelper(data, root);
    }



    /**
     * contains helper method
     * @param data data to be checked
     * @param node node to start 
     * @return if data is in the tree or not
     */
    private boolean containsHelper(T data, BSTNode<T> node) {
        if (node == null) {
            return false;
        } else if (data.equals(node.getData())) {
            return true;
        } else { 
            if (data.compareTo(node.getData()) < 0) {
                return containsHelper(data, node.getLeft());
            } else {
                return containsHelper(data, node.getRight());
            }
        }
    }
    /**
     * Returns whether or not data matching the given parameter is contained
     * within the tree.
     *
     * This must be done recursively.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to search for
     * @return true if the parameter is contained within the tree, false
     * otherwise
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is null");
        }
        return containsHelper(data, root);
    }

    /**
     * Helps create pre-order traversal 
     * @param list List which pre-order traversal will be added
     * @param node node to be searched for pre-order traversal list
     */
    private void preorderHelper(BSTNode<T> node, List<T> list) {
        if (node == null) {
            return;
        } else {
            list.add(node.getData());
            preorderHelper(node.getLeft(), list);
            preorderHelper(node.getRight(), list);
        }
    }

    /**
     * Generate a pre-order traversal of the tree.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @return the preorder traversal of the tree
     */
    public List<T> preorder() {
        List<T> traversal = new ArrayList<>();
        preorderHelper(root, traversal);
        return traversal;
    }


    /**
     * Helps create in-order traversal 
     * @param list List which in-order traversal will be added
     * @param node node to be searched for in-order traversal
     */
    private void inorderHelper(BSTNode<T> node, List<T> list) {
        if (node == null) {
            return;
        } else {
            inorderHelper(node.getLeft(), list);
            list.add(node.getData());
            inorderHelper(node.getRight(), list);
        }
    }

    /**
     * Generate an in-order traversal of the tree.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @return the inorder traversal of the tree
     */
    public List<T> inorder() {
        List<T> traversal = new ArrayList<>();
        inorderHelper(root, traversal);
        return traversal;
    }



    /**
     * Helps create post-order traversal 
     * @param list List which post-order traversal will be added
     * @param node node to be searched for post-order traversal
     */
    private void postorderHelper(BSTNode<T> node, List<T> list) {
        if (node == null) {
            return;
        } else {
            postorderHelper(node.getLeft(), list);
            postorderHelper(node.getRight(), list);
            list.add(node.getData());
        }
    }


    /**
     * Generate a post-order traversal of the tree.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @return the postorder traversal of the tree
     */
    public List<T> postorder() {
        List<T> traversal = new ArrayList<>();
        postorderHelper(root, traversal);
        return traversal;
    }

    /**
     * Generate a level-order traversal of the tree.
     *
     * This does not need to be done recursively.
     *
     * Hint: You will need to use a queue of nodes. Think about what initial
     * node you should add to the queue and what loop / loop conditions you
     * should use.
     *
     * Must be O(n).
     *
     * @return the level order traversal of the tree
     */
    public List<T> levelorder() {
        List<T> traversal = new ArrayList<>();
        Queue<BSTNode<T>> queue = new LinkedList<>();
        if (size == 0) {
            return traversal;
        }
        queue.add(root);
        traversal.add(root.getData());
        while (!(queue.isEmpty())) {
            BSTNode<T> temp = queue.peek();
            if (temp.getLeft() != null) {
                queue.add(temp.getLeft());
                traversal.add(temp.getLeft().getData());
            }
            if (temp.getRight() != null) {
                queue.add(temp.getRight());
                traversal.add(temp.getRight().getData());
            }
            queue.remove();
        }

        return traversal;

    }
    /**
     *  helps find the height of the root
     * @param node node to be searched for height
     * @return height of the root of the tree
     */
    private int heightHelper(BSTNode<T> node) {
        if (node == null) {
            return -1;
        } else {
            return Math.max(heightHelper(node.getLeft()), heightHelper(node.getRight())) + 1;
        }
    }

    /**
     * Returns the height of the root of the tree.
     *
     * This must be done recursively.
     *
     * A node's height is defined as max(left.height, right.height) + 1. A
     * leaf node has a height of 0 and a null child has a height of -1.
     *
     * Must be O(n).
     *
     * @return the height of the root of the tree, -1 if the tree is empty
     */
    public int height() {
        return heightHelper(root);
    }

    /**
     * Clears the tree.
     *
     * Clears all data and resets the size.
     *
     * Must be O(1).
     */
    public void clear() {
        root = null;
        size = 0;
    }



    /**
     * klargest helper method 
     * @param elements list of elements
     * @param node node currently being searched
     * @param k length of list
     * @return list of k integers
     */
    private List<T> kLargestHelper(List<T> elements, BSTNode<T> node, int k) {
        if (node == null) {
            return elements;
        } else { 
            kLargestHelper(elements, node.getRight(), k);
            if (elements.size() == k) {
                return elements;
            } else {
                elements.add(0, node.getData());
                kLargestHelper(elements, node.getLeft(), k);
            }
        }
        return elements;

    }
    /**
     * Finds and retrieves the k-largest elements from the BST in sorted order,
     * least to greatest.
     *
     * This must be done recursively.
     *
     * In most cases, this method will not need to traverse the entire tree to
     * function properly, so you should only traverse the branches of the tree
     * necessary to get the data and only do so once. Failure to do so will
     * result in an efficiency penalty.
     *
     * EXAMPLE: Given the BST below composed of Integers:
     *
     *                50
     *              /    \
     *            25      75
     *           /  \
     *          12   37
     *         /  \    \
     *        10  15    40
     *           /
     *          13
     *
     * kLargest(5) should return the list [25, 37, 40, 50, 75].
     * kLargest(3) should return the list [40, 50, 75].
     *
     * Should have a running time of O(log(n) + k) for a balanced tree and a
     * worst case of O(n + k), with n being the number of data in the BST
     *
     * @param k the number of largest elements to return
     * @return sorted list consisting of the k largest elements
     * @throws java.lang.IllegalArgumentException if k < 0 or k > size
     */
    public List<T> kLargest(int k) {
        if (k > size) {
            throw new IllegalArgumentException(k + " is larger than the size of the BST");
        }
        List<T> values = new ArrayList<T>(k);
        return kLargestHelper(values, root, k);
    }


    /**
     * Returns the root of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the root of the tree
     */
    public BSTNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }

    /**
     * Returns the size of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the tree
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}
