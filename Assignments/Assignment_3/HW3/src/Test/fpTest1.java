package Test;

import HW3.fp;

import static org.junit.jupiter.api.Assertions.*;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;



public class fpTest1 {


    <U> void print(U u) {
        System.out.println(u);
    }

    @Test
    void testMap() {
        List<String> list = new LinkedList<String>();
        list.add("Mary");
        list.add("Isla");
        list.add("Sam");

        List<Integer> result = fp.map(list, (String s)->{return s.hashCode();});
        
        Integer[] expected = {"Mary".hashCode(), "Isla".hashCode(), "Sam".hashCode()};

        assertArrayEquals(expected, result.toArray());
    }

    @Test
    void testFoldLeft() {

    }

    @Test 
    void testFoldRight() {

    }



}
