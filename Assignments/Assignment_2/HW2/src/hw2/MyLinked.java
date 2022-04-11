package hw2;

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
    
    public String toString() {
        String l = "";
        for (Node trvsNode = this.first; trvsNode != null; trvsNode = trvsNode.next) {
            l += Double.toString(trvsNode.item);
            if (trvsNode.next != null)
                l += " ";
        }
        return l;
    }

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

    public boolean isEmpty () { return first == null; }
    public int size () { return N; }
    public void add (double item) {
        Node newfirst = new Node ();
        newfirst.item = item;
        newfirst.next = first;
        first = newfirst;
        N++;
    }


    // delete the kth element
    public void delete (int k) {
        assert checkInvariants();
        if (k < 0 || k >= N) throw new IllegalArgumentException ();
        if (k == 0)
            this.first = this.first.next;
        else {
            Node trvsNode = this.first;
            for (int i = 1; i < k; trvsNode=trvsNode.next,i++);

            trvsNode.next = trvsNode.next.next;
        }
        N--;

        assert checkInvariants ();
    }

    // reverse the list "in place"... without creating any new nodes
    public void reverse () {
        assert checkInvariants();
        if (N > 1) {
            Node prevNode = this.first, currentNode = this.first.next;
            prevNode.next = null;
            for (Node nextNode = currentNode.next; nextNode != null; nextNode = nextNode.next) {
                currentNode.next = prevNode;
                prevNode = currentNode;
                currentNode = nextNode;
            }
            currentNode.next = prevNode;
            this.first = currentNode;
        }

        assert checkInvariants ();
    }

    // remove 
    public void remove (double item) {
        assert checkInvariants();
        if (this.N == 0) return;
        int i = 0;
        for (Node trvsNode=this.first; trvsNode != null;) {
            if (Double.compare(trvsNode.item, item) == 0) {
                trvsNode = trvsNode.next;
                this.delete(i);
            }
            else {
                trvsNode = trvsNode.next;
                i++;
            }
        }
        assert checkInvariants ();
    }
}
