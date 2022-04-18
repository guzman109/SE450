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
	List<V> list = new LinkedList<>();
	for (U x: l) {
		list.add(f.apply(x));
	}
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
private static <U,V> V foldRight_aux(V v, List<U>l, BiFunction<U,V,V>f, int i) {
	if (i >= 0)
		v = foldRight_aux(f.apply(l.get(i), v), l, f, --i);
	return v;
}
public static <U,V> V foldRight(V e, List<U>l, BiFunction<U,V,V> f){
	return foldRight_aux(e, l, f, l.size()-1);
}



public static <U> Iterable<U> filter(Iterable<U> l, Predicate<U> p){
	LinkedList<U> list = new LinkedList<U>();
	for (U u:l)
		if (p.test(u))
			list.addFirst(u);
return list;
}

static <U> U minVal(Iterable<U> l, Comparator<U> c){
	// write using fold.  No other loops or recursion permitted.
	return foldLeft(l.iterator().next(), l, (U u1, U u2) -> {return c.compare(u1, u2) < 0 ? u1 : u2; }  );
}

// static <U extends Comparable<U>> int minPos(Iterable<U> l){
// 	// write using fold.  No other loops or recursion permitted. 
// 	l.iterator().next().compareTo(o)
// 	return foldRight(0, l, (int x, int y)->{});
// }

	public static void main(String[] args) {

		

		

	
		
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