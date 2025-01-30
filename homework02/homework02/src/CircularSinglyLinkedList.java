import java.util.NoSuchElementException;



/**
 * Your implementation of a CircularSinglyLinkedList without a tail pointer.
 *
 * @author Akash Satya
 * @version 1.0
 * @userid asatya8 (i.e. gburdell3)
 * @GTID 903896933 (i.e. 900000000)
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
 * 
 */
public class CircularSinglyLinkedList<T> {

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private CircularSinglyLinkedListNode<T> head;
    private int size;

    /*
     * Do not add a constructor.
     */

    /**
     * Helper method for adding at the specified index
     * @param data the data to add at the specified index
     * @param node the node which is after the node being added
    */
    private void addNode(CircularSinglyLinkedListNode<T> node, T data) {
        CircularSinglyLinkedListNode<T> newNode = new CircularSinglyLinkedListNode<T>(node.getData());
        newNode.setNext(node.getNext());
        node.setNext(newNode);
        node.setData(data);
        size++;
    }

    /**
     * Adds the data to the specified index.
     *
     * Must be O(1) for indices 0 and size and O(n) for all other cases.
     *
     * @param index the index at which to add the new data
     * @param data  the data to add at the specified index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index > size
     * @throws java.lang.IllegalArgumentException  if data is null
     */
    public void addAtIndex(int index, T data) { 
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(String.format("Cannot add at index %d", index));
        } else if (data == null) {
            throw new IllegalArgumentException("Cannot add null data to linked list");
        } else if (isEmpty()) {
            CircularSinglyLinkedListNode<T> newNode = new CircularSinglyLinkedListNode<T>(data);
            head = newNode;
            head.setNext(head);
            size++; 
        } else if (index == 0) {
            addNode(head, data);
        } else if (index == size) {
            addNode(head, data);
            head = head.getNext();
        } else {
            CircularSinglyLinkedListNode<T> curr = head;
            for (int count = 0; count < index; count++) {
                curr = curr.getNext(); }
            addNode(curr, data); 
        }
    }
    

    

    /**
     * Adds the data to the front of the list.
     *
     * Must be O(1).
     *
     * @param data the data to add to the front of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToFront(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot add null data to linked list");
        }
        addAtIndex(0, data);
    }

    /**
     * Adds the data to the back of the list.
     *
     * Must be O(1).
     *
     * @param data the data to add to the back of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToBack(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot add null data to linked list");
        }
        addAtIndex(size, data);
    }

    
    
    /**
     * Helper method for adding at the specified index
     * @param node the node which is after the node being removed
     * @return the data formerly located at the specified index
    */
    private T removeNode(CircularSinglyLinkedListNode<T> node) {
        T result = node.getData();
        if (size > 1) {
            node.setData(node.getNext().getData());
            node.setNext(node.getNext().getNext());
        } else {
            head.setNext(null);
            head = null;
        }

        size--;
        return result;
    }
    
    
    /**
     * Removes and returns the data at the specified index.
     *
     * Must be O(1) for index 0 and O(n) for all other cases.
     *
     * @param index the index of the data to remove
     * @return the data formerly located at the specified index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T removeAtIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(String.format("Cannot remove at index %d", index));
        }
        if (index == 0) {
            return removeNode(head);
        } else {
            CircularSinglyLinkedListNode<T> curr = head;
            for (int count = 0; count < index; count++) {
                curr = curr.getNext();
            }
            if (index == size - 2) {
                T result = curr.getNext().getData();
                curr.setNext(head);
                size--;
                return result;
            } else {
                return removeNode(curr);
            }
        }

    }

    /**
     * Removes and returns the first data of the list.
     *
     * Must be O(1).
     *
     * @return the data formerly located at the front of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromFront() {
        if (size == 0) {
            throw new NoSuchElementException("No elements in the linked list");
        }
        return removeAtIndex(0);
    }

    /**
     * Removes and returns the last data of the list.
     *
     * Must be O(n).
     *
     * @return the data formerly located at the back of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromBack() {
        if (size == 0) {
            throw new NoSuchElementException("No elements in the linked list");
        }
        return removeAtIndex(size - 2);
    }

    /**
     * Returns the data at the specified index.
     *
     * Should be O(1) for index 0 and O(n) for all other cases.
     *
     * @param index the index of the data to get
     * @return the data stored at the index in the list
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(String.format("Cannot get data at index %d", index));
        } else if (index == 0) {
            return head.getData();
        } else {
            CircularSinglyLinkedListNode<T> curr = head;
            for (int count = 0; count <= index; count++) {
                if (count == index) {
                    return curr.getData();
                } else {
                    curr = curr.getNext();
                }
            }
        }
        return null;
    }

    /**
     * Returns whether or not the list is empty.
     *
     * Must be O(1).
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Clears the list.
     *
     * Clears all data and resets the size.
     *
     * Must be O(1).
     */
    public void clear() {
        head = null;
        size = 0;
    }

    /**
     * Removes and returns the last copy of the given data from the list.
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the list.
     *
     * Must be O(n).
     *
     * @param data the data to be removed from the list
     * @return the data that was removed
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if data is not found
     */
    public T removeLastOccurrence(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot add null data to linked list");
        }
        if (size == 0) {
            throw new NoSuchElementException("No elements in the linked list");
        } else {
            CircularSinglyLinkedListNode<T> curr = head;
            for (int i = 0; i <= size - 1; i++) {
                if (curr.getData().equals(data)) {
                    T result = curr.getData();
                    if (size == 1) {
                        head.setNext(null);
                        head = null;
                        size--;
                        return result;
                    } 
                    while (curr.getNext().getData().equals(data)) {
                        curr = curr.getNext();
                    }
                    curr.setData(curr.getNext().getData());
                    curr.setNext(curr.getNext().getNext());
                    size--;
                    return result;
                } else {
                    curr = curr.getNext();
                }
            }
            throw new NoSuchElementException("Data is not found in the linked list");
        }
        
    }

    /**
     * Returns an array representation of the linked list.
     *
     * Must be O(n) for all cases.
     *
     * @return the array of length size holding all of the data (not the
     * nodes) in the list in the same order
     */
    public T[] toArray() {
        T[] arrayRep = (T[]) new Object[size];
        for (int i = 0; i < size; i++) {
            arrayRep[i] = head.getData();
            head = head.getNext();
        }
        return arrayRep;
    }

    /**
     * Returns the head node of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the node at the head of the list
     */
    public CircularSinglyLinkedListNode<T> getHead() {
        // DO NOT MODIFY!
        return head;
    }

    /**
     * Returns the size of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the list
     */
    public int size() {
        // DO NOT MODIFY!
        return size;
    }
}
