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

	
	/** 
	 * 
	 * @param e
	 * @param Iterablel
	 * @param f
	 * @return V
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
		return v;
	}


	
	/** 
	 * @param v
	 * @param Listl
	 * @param BiFunctionf
	 * @param i
	 * @return V
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
	 * @param e
	 * @param Listl
	 * @param f
	 * @return V
	 */
	public static <U,V> V foldRight(V e, List<U>l, BiFunction<U,V,V> f){
		return foldRight_aux(e, l, f, l.size()-1);
	}



	
	/** 
	 * @param l
	 * @param p
	 * @return Iterable<U>
	 */
	public static <U> Iterable<U> filter(Iterable<U> l, Predicate<U> p){
		LinkedList<U> list = new LinkedList<U>();
		System.out.println(l);
		for (U u:l)
			if (p.test(u))
				list.add(u);
	return list;
	}

	
	/** 
	 * @param l
	 * @param c
	 * @return U
	 */
	public static <U> U minVal(Iterable<U> l, Comparator<U> c){
		// write using fold.  No other loops or recursion permitted.
		return foldLeft(l.iterator().next(), l, (U u1, U u2) -> {return c.compare(u1, u2) < 0 ? u1 : u2; }  );
	}

	private static class minPosAux<U extends Comparable<U>> implements BiFunction<U,U,U> {
		private U min;
		private Iterable<U> l;
		private int min_pos = 0;
		private boolean stop = false;

		public minPosAux (Iterable<U> l) { 
			this.l = l;
			this.min = minVal(this.l, (U u1, U u2) -> { return u1.compareTo(u2); });
		}

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

	
	/** 
	 * @param l
	 * @return int
	 */
	public static <U extends Comparable<U>> int minPos(Iterable<U> l){
		// write using fold.  No other loops or recursion permitted. 
		minPosAux<U> f = new minPosAux<U>(l);
		fp.foldLeft(null, l, f);
		return f.getMinPos();
	}

}