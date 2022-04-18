package Test;

import HW3.*;

import static org.junit.jupiter.api.Assertions.*;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;



public class fpTest1 {
    
    List<Person> constructTestList() {
        List<Person> test_list = new LinkedList<Person>();
        test_list.add(new Person(100000, "Mary"));
        test_list.add(new Person (75000,"Isla"));
        test_list.add(new Person(55000, "Sam"));

        return test_list;
    }

    <U> void print(U u) {
        System.out.println(u);
    }

    private final List<Person> test_list = constructTestList();

    // (1) Use map to implement the following behavior (described in Python).  i.e given a List<T> create a List<Integer> of the hashes of the objects.
    // names = ['Mary', 'Isla', 'Sam']
    // for i in range(len(names)):
        // names[i] = hash(names[i])
    @Test
    void testMap() {
        List<Integer> result = fp.map(test_list, (Person p)->{return p.name().hashCode();});
        
        Integer[] expected = {"Mary".hashCode(), "Isla".hashCode(), "Sam".hashCode()};

        assertArrayEquals(expected, result.toArray());
    }

    // (2) Use foldleft to calculate the sum of a list of integers.
    // i.e write a method: int sum(List<Integer> l)
    @Test
    void testFoldLeft() {
        Integer result = fp.foldLeft(0, test_list, (Integer sum, Person p)->{ return p.getSalary() + sum; });

        Integer expected = 100000+75000+55000;

        assertEquals(expected, result);
    }

    // (3) Use foldRight to concatenate a list of strings i.e write a method
    // String s (List<String> l)
    @Test 
    void testFoldRight() {
        String result = fp.foldRight("", test_list, (Person p, String s)->{ return p.name().concat(s) ;});
        String expected = "MaryIslaSam";
        assertEquals(expected, result);
    }

    // (4) consider an array of Persons. Use filter to 
    // print the names of the Persons whose salary is
    // greater than 100000
    @Test
    void testFilter() {
        Iterable<Person> result = fp.filter(test_list, (Person p)-> { return p.getSalary() > 100000; });
        
        
        String[] names_expected = {"Mary"};
        Integer[] salaries_expected = {120000};
        Integer length_expected = 1;

        LinkedList<String> names_result = new LinkedList<String>();
        LinkedList<Integer> salaries_result = new LinkedList<Integer>();
        Integer length_result = 0;

        for (Person p : result) {
            names_result.add(p.name());
            salaries_result.add(p.getSalary());
            length_result++;
        }

        print(names_expected);
        print(names_result.toArray());
        assertArrayEquals(names_expected, names_result.toArray());
        assertArrayEquals(salaries_expected, salaries_result.toArray());
        assertEquals(length_expected, length_result);
    }


}
