package Test;

import HW3.*;

import static org.junit.jupiter.api.Assertions.*;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;



public class fpTest1 {
    
    List<Person> constructTestList() {
        List<Person> test_list = new LinkedList<Person>();
        test_list.add(new Person(120000, "Mary"));
        test_list.add(new Person (75000,"Isla"));
        test_list.add(new Person(55000, "Sam"));

        return test_list;
    }

    <U> void print(U u) {
        System.out.println(u);
    }

    private final List<Person> test_list = constructTestList();

    @Test
    void testMap() {
        // (1) Use map to implement the following behavior (described in Python).  i.e given a List<T> create a List<Integer> of the hashes of the objects.
        // names = ['Mary', 'Isla', 'Sam']
        // for i in range(len(names)):
            // names[i] = hash(names[i])
        List<Integer> result = fp.map(test_list, (Person p)->{return p.name().hashCode();});
        
        Integer[] expected = {"Mary".hashCode(), "Isla".hashCode(), "Sam".hashCode()};

        assertArrayEquals(expected, result.toArray());
    }


    @Test
    void testFoldLeft() {
        // (2) Use foldleft to calculate the sum of a list of integers.
        // i.e write a method: int sum(List<Integer> l)
        Integer result = fp.foldLeft(0, test_list, (Integer sum, Person p)->{ return p.getSalary() + sum; });

        Integer expected = 120000+75000+55000;

        assertEquals(expected, result);
    }

    @Test 
    void testFoldRight() {
        // (3) Use foldRight to concatenate a list of strings i.e write a method
        // String s (List<String> l)
        String result = fp.foldRight("", test_list, (Person p, String s)->{ return p.name().concat(s) ;});
        String expected = "MaryIslaSam";
        assertEquals(expected, result);
    }

    @Test
    void testFilter() {
        // (4) consider an array of Persons. Use filter to 
        // print the names of the Persons whose salary is
        // greater than 100000
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

        assertArrayEquals(names_expected, names_result.toArray());
        assertArrayEquals(salaries_expected, salaries_result.toArray());
        assertEquals(length_expected, length_result);

        Iterable<Person> result_empty = fp.filter(new LinkedList<Person>(), (Person p)-> { return p.getSalary() > 100000; });
        LinkedList<Person> empty = new LinkedList<Person>();
        
        // Shouldn't run because list is empty.
        for (Person p:result_empty)
            empty.add(p);

        assertArrayEquals((new LinkedList<>()).toArray(), empty.toArray());
    }

    @Test
    void testMinVal() {
        // (5) Use minVal to calculate the minimum of a List of 
		//       Integers
		Person least_paid = fp.minVal(test_list, (Person p1, Person p2)->{ return Integer.compare(p1.getSalary(), p2.getSalary()); });
        assertEquals("Sam", least_paid.name());
        assertEquals(55000, least_paid.getSalary());

        // (6) Use minVal to calculate the maximum of a List of 
		//       Integers
        Person most_paid = fp.minVal(test_list, (Person p1, Person p2)->{ return Integer.compare(p1.getSalary(), p2.getSalary())*(-1); });
        assertEquals("Mary", most_paid.name());
        assertEquals(120000, most_paid.getSalary());

        LinkedList<Integer> int_list = new LinkedList<Integer>();
        
        for (int i=0; i<5000; i++)
            int_list.addFirst(i+234);
        
        int minVal = fp.minVal(int_list, (Integer x, Integer y)->{ return Integer.compare(x, y); });
        assertEquals(234, minVal);

        int maxVal = fp.minVal(int_list, (Integer x, Integer y)->{ return Integer.compare(x,y)*(-1); });
        assertEquals(5233, maxVal);
    }

    @Test
    void testMinPos() {
        // (7)  Use minPos to calculate the position of the
		// minimum in  a List of  Integers
        int min_pos_Int = fp.minPos( fp.map(test_list, (Person p)->{ return p.getSalary(); }) );
        assertEquals(2, min_pos_Int);

		// (8)  Use minPos to calculate the position of the
		// minimum in  a List of  String
        int min_pos_Str = fp.minPos( fp.map(test_list, (Person p)->{ return p.name(); }) );
        assertTrue(1==min_pos_Str);

        LinkedList<Integer> int_list = new LinkedList<Integer>();
        int_list.add(0);
        int_list.add(1);
        int_list.add(-1);
        int_list.add(2);
        int_list.add(3);
        int int_result = fp.minPos(int_list);
        assertEquals(2, int_result);

        LinkedList<String> str_list = new LinkedList<String>();
        str_list.add("A");
        str_list.add("B");
        str_list.add("Z");
        str_list.add("a");
        str_list.add("A");
        int str_result = fp.minPos(str_list);
        assertTrue(0==str_result);
    }
}
