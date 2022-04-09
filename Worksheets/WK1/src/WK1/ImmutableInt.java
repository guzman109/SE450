package WK1;

import java.util.List;
import java.util.ArrayList;

public class ImmutableInt {
    final int x;
    public ImmutableInt (int x) { this.x = x; }

    public int get() { return x; }
    public ImmutableInt set (int y) { return new ImmutableInt(y); }

    public boolean equals (Object o) {
        if (! (o instanceof ImmutableInt))
            return false;
        return ((ImmutableInt) o).x == this.x;
    }
    public static void main(String[] args) {
        ImmutableInt i = new ImmutableInt(3);
        ImmutableInt k = i.set(5);

        List<ImmutableInt> a = new ArrayList<>();
        
        a.add(i);
        ImmutableInt j = new ImmutableInt(3);
        System.out.println(a.contains(j));
        System.out.println(a.contains(i));
    }
}
