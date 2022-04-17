package HW3;

import java.util.List;
import java.util.LinkedList;
import java.util.function.Function;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.Comparator;

public class fp {
	
// f takes U to V.. ie. f.apply(U): V


	
public static <U,V> List<V> map(Iterable<U> l, Function<U,V> f) {
	// walk through the U's
	// use f at every stage f.apply
	// construct list of V's
	List<V> list = new LinkedList<V>();
	for (U x: l) {
		list.add(f.apply(x));
	}
	System.out.println(list);
	return list;
}


public static <U,V> V foldLeft(V e, Iterable<U>l, BiFunction<V,U,V> f){
	// walk through the U's [u1,u2, ..,un]
	//                       e
	// use f at every stage v1= f.apply(e,u1)
	//                         v2= f.apply(v1,u2)
	//						    v3= f.apply(v2,u3)..
	// return the last v
	V v = e;
	for (U u: l) {
		v = f.apply(v, u);
	}
	return v;
}


// similar to above
// but from the right
//     vn=  f(un,e)
//          vn-1 = f(un-1,vn)
// ..
// return the first v
public static <U,V> V foldRight(V e, List<U>l, BiFunction<U,V,V> f){
	int j = l.size() - 1;
	V v = e;
	while (j != 0) {
		int i = 0;
		for (U u : l) {
			if (i == j) {
				v = f.apply(u, v);
				j--;
			}
			i++;
		}
	}
	return v;
}



public static <U> Iterable<U> filter(Iterable<U> l, Predicate<U> p){
return null;
}

static <U> U minVal(Iterable<U> l, Comparator<U> c){
	// write using fold.  No other loops or recursion permitted. 
	return null;
}

static <U extends Comparable<U>> int minPos(Iterable<U> l){
	// write using fold.  No other loops or recursion permitted. 
	return 0;
}

	public static void main(String[] args) {
		// (1) Use map to implement the following behavior (described in Python).  i.e given a List<T> create a List<Integer> of the hashes of the objects.
		// names = ['Mary', 'Isla', 'Sam']
		// for i in range(len(names)):
		       // names[i] = hash(names[i])
		
		// (2) Use foldleft to calculate the sum of a list of integers.
		// i.e write a method: int sum(List<Integer> l)
		
		// (3) Use foldRight to concatenate a list of strings i.e write a method
		// String s (List<String> l)
		
		// (4) consider an array of Persons. Use filter to 
		// print the names of the Persons whose salary is
		// greater than 100000
		
		// (5) Use minVal to calculate the minimum of a List of 
		//       Integers
		
        // (6) Use minVal to calculate the maximum of a List of 
		//       Integers
		
		// (7)  Use minPos to calculate the position of the
		// minimum in  a List of  Integers

		// (8)  Use minPos to calculate the position of the
		// minimum in  a List of  String
	}

}

class Person{
	final int salary;
	final String name;
	
	Person(int salary, String name){
		this.salary = salary;
		this.name = name;
	}
	
	int getSalary() { return salary; }
	String name() { return name;}
	
}