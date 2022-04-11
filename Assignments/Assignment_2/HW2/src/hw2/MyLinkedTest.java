package hw2;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class MyLinkedTest {
    
    private final MyLinked b = new MyLinked();

    @Test
    void testToString() {
        // Check empty list is an empty string
        assertEquals("", b.toString());

        
        b.add(1.5);
        assertEquals("1.5", b.toString());

        b.add(3.14159265359);
        assertEquals("3.14159265359 1.5", b.toString());

        b.delete(1);
        assertEquals("3.14159265359", b.toString());
    }
    void assertSizeAndElements(MyLinked l, int expected_size, String expected_string) {
        assertEquals(expected_size, l.size());
        assertEquals(expected_string, l.toString());
    }
    @Test
    void testDelete () {
        b.add (1);
        assertSizeAndElements(b, 1, "1.0");

        b.delete (0);
        assertSizeAndElements(b, 0, "");

        for (double i = 1; i < 13; i++) {
            b.add (i);
        }
        assertSizeAndElements(b, 12, "12.0 11.0 10.0 9.0 8.0 7.0 6.0 5.0 4.0 3.0 2.0 1.0");

        b.delete (0);
        assertSizeAndElements(b, 11, "11.0 10.0 9.0 8.0 7.0 6.0 5.0 4.0 3.0 2.0 1.0");    

        b.delete (10);        
        assertSizeAndElements(b, 10, "11.0 10.0 9.0 8.0 7.0 6.0 5.0 4.0 3.0 2.0");

        b.delete (4);
        assertSizeAndElements(b, 9, "11.0 10.0 9.0 8.0 6.0 5.0 4.0 3.0 2.0");
        
    }
    @Test
    void testReverse () {
        b.reverse ();
        assertSizeAndElements(b, 0, "");
        b.add (1);
        b.reverse ();
        assertSizeAndElements(b, 1, "1.0");

        b.add (2);
        b.reverse ();
        assertSizeAndElements(b, 2, "1.0 2.0");

        b.reverse ();
        assertSizeAndElements(b, 2, "2.0 1.0");

        for (double i = 3; i < 7; i++) {
            b.add (i);
            b.add (i);
        }
        b.reverse ();
        assertSizeAndElements(b, 10, "1.0 2.0 3.0 3.0 4.0 4.0 5.0 5.0 6.0 6.0");
    }
    @Test
    void testRemove () {
        b.remove (4);
        assertSizeAndElements(b, 0, "");

        b.add (1);
        b.remove (4);
        assertSizeAndElements(b, 1, "1.0");

        b.remove (1);
        assertSizeAndElements(b, 0, "");

        for (double i = 1; i < 5; i++) {
            b.add (i);
            b.add (i);
        }
        for (double i = 1; i < 5; i++) {
            b.add (i);
            b.add (i);
            b.add (i);
            b.add (i);
            b.add (i);
        }
        b.remove (9);
        assertSizeAndElements(b, 28, "4.0 4.0 4.0 4.0 4.0 3.0 3.0 3.0 3.0 3.0 2.0 2.0 2.0 2.0 2.0 1.0 1.0 1.0 1.0 1.0 4.0 4.0 3.0 3.0 2.0 2.0 1.0 1.0");

        b.remove (3);
        assertSizeAndElements(b, 21, "4.0 4.0 4.0 4.0 4.0 2.0 2.0 2.0 2.0 2.0 1.0 1.0 1.0 1.0 1.0 4.0 4.0 2.0 2.0 1.0 1.0");

        b.remove (1);
        assertSizeAndElements(b, 14, "4.0 4.0 4.0 4.0 4.0 2.0 2.0 2.0 2.0 2.0 4.0 4.0 2.0 2.0");

        b.remove (4);
        assertSizeAndElements(b, 7, "2.0 2.0 2.0 2.0 2.0 2.0 2.0");

        b.remove (2);
        assertSizeAndElements(b, 0, "");
    }
}
