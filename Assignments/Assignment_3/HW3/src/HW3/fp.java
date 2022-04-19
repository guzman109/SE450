package HW3;

import java.util.List;
import java.util.LinkedList;
import java.util.function.Function;
import java.util.function.BiFunction;
import java.util.function.Predicate;


import java.util.Comparator;

public class fp {
	
	private static <U,V> void checkMapInvariant(Iterable<U> l, List<V> r) {
		assert(l.iterator().hasNext() != r.isEmpty());
	}
	private static <U,V> boolean checkMapLoopInvariant(Iterable<U> l, Function<U,V> f, List<V> r, int pos) {
		for (int i = 0; i <= pos; i++) {
			boolean in_list = false;
			for (U x: l) {
				V m = f.apply(x);
				if (m.hashCode() == r.get(i).hashCode())
					in_list = true;
			}
			if (!in_list)
				return false;
		}

		return true;
	}
	
	/** Maps an iterable of generic type U to a List of generic type V
	 * @invariant If l is empty then return value is empty.
	 * @maintains Every element in r is mapped from an element in l.
	 * @param l the iterable class to map to a list
	 * @param f the mapping function from U to V
	 * @return List<V> mapped List of type V
	 */
	// f takes U to V.. ie. f.apply(U): V
		
	public static <U,V> List<V> map(Iterable<U> l, Function<U,V> f) {
		// walk through the U's
		// use f at every stage f.apply
		// construct list of V's
		List<V> r = new LinkedList<>();
		int pos = 0;
		for (U x: l) {
			r.add(f.apply(x));
			assert checkMapLoopInvariant(l, f, r, pos);
			pos++;
		}

		// assert
		checkMapInvariant(l, r);
		return r;
	}

	private static <U,V> boolean checkFoldInvariant(V e, Iterable<U>l, V v) {
		boolean pass = l.iterator().hasNext() ? true : false;
		if (!pass && e.hashCode() == v.hashCode())
			pass = true;
		return pass;
	}
	/** 
	 * Combines elements from left to right.
	 * @invariant if l is empty then #v is equal to #e 
	 * @param e intial top element
	 * @param l Collection of elements to combine
	 * @param f combining function
	 * @return V combined elements.
	 */
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
		assert (checkFoldInvariant(e, l, v));
		return v;
	}


	
	/** 
	 * Auxilery function to foldRight. It recursively combines the elements in the list
	 * @param v intial top element
	 * @param l Collection of elements to combine 
	 * @param f combining function
	 * @param i current position
	 * @return V combined elements
	 */
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
	
	/** 
	 * Combines elements from right to left
	 * @param e intial top element
	 * @param l Collection of elements to combine
	 * @param f combining function
	 * @return V combined elements
	 */
	public static <U,V> V foldRight(V e, List<U>l, BiFunction<U,V,V> f){
		V v = foldRight_aux(e, l, f, l.size()-1);
		assert (checkFoldInvariant(e, l, v));
		return v;
	}


	private static <U> boolean checkFilterLoopInvariant(Iterable<U> l, LinkedList<U> r) {
		for (U u: r) {
			boolean in_l = false;
			for (U v: l) {
				if (u.hashCode() == v.hashCode())
					in_l = true;
			}
			if (!in_l)
				return false;
		}
		return true;
	}

	
	/**
	 * Filters out elements in the collection that don't meet the criteria. 
	 * @postconditon Every element in #r meets the criteria.
	 * @maintains Every elmenent in #r is in #l.
	 * @param l Collection to filter elements.
	 * @param p Criteria function
	 * @return Iterable<U> collection of elements that met the criteria.
	 */
	public static <U> Iterable<U> filter(Iterable<U> l, Predicate<U> p){
		LinkedList<U> r = new LinkedList<U>();

		for (U u:l) {
			if (p.test(u))
				r.add(u);
			assert checkFilterLoopInvariant(l, r);
		}
		
		return r;
	}

	
	/** 
	 * Finds the minimum value in a collection
	 * @postcondition returned element is the minimum valued element in the collection
	 * @param l Collection to find the minimum value.
	 * @param c Comparing function between the elemetns of the collection
	 * @return U Minimum Value in the list
	 */
	public static <U> U minVal(Iterable<U> l, Comparator<U> c){
		// write using fold.  No other loops or recursion permitted.
		return foldLeft(l.iterator().next(), l, (U u1, U u2) -> {return c.compare(u1, u2) < 0 ? u1 : u2; }  );
	}

	/**
	 * Auxilery class to find the position of the minimu valued element in the list.
	 */
	private static class minPosAux<U extends Comparable<U>> implements BiFunction<U,U,U> {
		private U min;
		private Iterable<U> l;
		private int min_pos = 0;
		private boolean stop = false;

		public minPosAux (Iterable<U> l) { 
			this.l = l;
			this.min = minVal(this.l, (U u1, U u2) -> { return u1.compareTo(u2); });
		}

		/**
		 * Returns the position of the minimum valued element in the list.
		 * @return int the position of the minimum valued element in the list.
		 */
		public int getMinPos() { return this.min_pos; }

		@Override
		public U apply(U t, U u) {
			// TODO Auto-generated method stub
			if (!this.stop) {
				if ((this.min.compareTo(u) == 0)) 
					this.stop = true;
				else
					this.min_pos++;
			}

			return u;
		}
		
	}

	
	/** Finds the positon of the minimum valued element in the collection
	 * @param l Collection of elements
	 * @return int position of the minimum valued element in the collection
	 */
	public static <U extends Comparable<U>> int minPos(Iterable<U> l){
		// write using fold.  No other loops or recursion permitted. 
		minPosAux<U> f = new minPosAux<U>(l);
		fp.foldLeft(null, l, f);
		return f.getMinPos();
	}

}