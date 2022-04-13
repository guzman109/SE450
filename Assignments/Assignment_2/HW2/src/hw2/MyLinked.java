// Carlos Guzman
package hw2;
/**
 * A Singly Linked List implemented as a list of connected Nodes.
 * @objecttype Mutable Collection of Nodes
 * @objectinvariant If <code>this.N == 0</code>, then <code>this.first == null</code>, 
 *  else <code>N > 0</code> and <code>first != null</code>.
 */
public class MyLinked {
    static class Node {
        public Node() { }
        public double item;
        public Node next;
    }

    int N;
    Node first;
    
    public MyLinked () {
        first = null;
        N = 0;
        assert checkInvariants ();
    }
    
    
    /**
     * Returns a string representation of the object. 
     * @invariant <code> (first == null && N == 0) || (first != null && N > 0) </code>
     * @maintains rep is not empty.
     * @return String The string representation of the object.
     */
    public String toString() {
        String rep = "";
        /** Traverse through the list and add the string representation 
         * of each elements item to the string l */
        for (Node trvsNode = this.first; trvsNode != null; trvsNode = trvsNode.next) {
            rep += Double.toString(trvsNode.item);
            if (trvsNode.next != null)
                rep += " ";
            assert (!rep.isEmpty()); // String can't be empty
        }
        checkInvariants();
        return rep;
    }

    /**
     * Checks that the list does not contain the item up to the ith element.
     * @maintains <code> trvsNode != </code>
     * @param i Number of nodes to traverse through
     * @param item Item to compare
     * @return boolean True if the loop invarient holds.
     */
    private boolean checkDoesNotContainItem(int i, Double item) {
        for (Node trvsNode = this.first; i != 0; trvsNode = trvsNode.next) {
            if (Double.compare(trvsNode.item, item) == 0)
                return false;
            i--;
            assert trvsNode != null;
        }
        return true;
    }
    /**
     * Checks that the invareintes for the linked list are true. 
     * @return boolean True if all the invarient conditons pass, otherwise False.
     */
    private boolean checkInvariants() {
        assert((N != 0) || (first == null));
        Node x = first;
        for (int i = 0; i < N; i++) {
            if (x==null) {
                return false;
            }
            x = x.next;
        }
        assert(x == null);
        return true;
    }

    /**
     * Checks if the list is empty.
     * @return boolean True if list is empty, otherwise False.
     */
    public boolean isEmpty () { return first == null; }

    /**
     * Returns the size of the list.
     * @return integer The number of elements in the list.
     */
    public int size () { return N; }
    
    /** 
     * Adds an element to the list
     * @invariant <code> (first == null && N == 0) || (first != null && N > 0) </code>
     * @param item
     */
    public void add (double item) {
        Node newfirst = new Node ();
        newfirst.item = item;
        newfirst.next = first;
        first = newfirst;
        N++;
    }


    
    /** Deletes the element at location k.
     * @precondtion there is an element at location k.
     * @throws IllegalArgumentException if k is less than zero or greater than or equal to N.
     * @invariant <code> (first == null && N == 0) || (first != null && N > 0) </code> 
     * @maintains <code> trvsNode != null </code>
     * @param k The location of the element to delete.
     * @postconditon The element at location k is not in the list.
     */
    public void delete (int k) {
        if (k < 0 || k >= N) throw new IllegalArgumentException ();
        if (k == 0)
            // Deleting the first element so, set this.first to what the first element points to next.
            this.first = this.first.next;
        else {
            // Traverse through the list until we reach the kth element.
            Node trvsNode = this.first;
            for (int i = 1; i < k; trvsNode=trvsNode.next,i++)
                assert trvsNode != null;
            // Delete the element from the list.
            trvsNode.next = trvsNode.next.next;
        }
        // Decrease the size of the list by 1
        N--;

        assert checkInvariants ();
    }

    /**
     * Reverses the list order.
     * @invariant <code> (first == null && N == 0) || (first != null && N > 0) </code>
     * @maintains <code> currentNode != null </code>
     * @postcondition The list is reversed
     */
    public void reverse () {
        if (N > 1) {
            // List has more than one element.
            // References to the first two elements in the list.
            Node prevNode = this.first, currentNode = this.first.next;
            prevNode.next = null; // Remove old connection
            for (Node nextNode = currentNode.next; nextNode != null; nextNode = nextNode.next) {
                // Iterate until the list is reversed by changing the connection in the nodes.
                currentNode.next = prevNode;
                prevNode = currentNode;
                currentNode = nextNode;
                assert currentNode != null;
            }
            // Reorder the connection
            currentNode.next = prevNode;
            // Set the first element to the new first element in new ordered list.
            this.first = currentNode;
        }

        assert checkInvariants ();
    }

    
    /** 
     * Removes all elements in the list that contain it
     * @invariant <code> (first == null && N == 0) || (first != null && N > 0) </code>
     * @maintans item is not in the list upto ith element.
     * @param item Item to remove from the list
     * @postconditon The list does not contain item.
     */
    public void remove (double item) {
        if (this.N == 0) return; // Nothing to remove
        int i = 0;
        for (Node trvsNode=this.first; trvsNode != null;) {
            if (Double.compare(trvsNode.item, item) == 0) {
                // We found a match, time to remove it.
                trvsNode = trvsNode.next;
                this.delete(i);
            }
            else {
                // Continue searching for a match.
                trvsNode = trvsNode.next;
                i++;
            }
            assert checkDoesNotContainItem(i, item);
        }
        assert checkInvariants ();
    }
}
