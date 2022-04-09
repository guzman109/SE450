package WK1.Test;
import org.junit.Assert;
import junit.framework.TestCase;
import WK1.ImmutableInt;
public class ImmutableIntTest extends  TestCase{
    public void testGet() {
        ImmutableInt i1 = new ImmutableInt(0);
        
        Assert.assertEquals(0, i1.get());

    }

    public void testSet() {
        ImmutableInt i1 = new ImmutableInt(14);
        ImmutableInt i2 = i1.set(50);
        Assert.assertEquals(14, i1.get());
        Assert.assertEquals(50, i2.get());
    }
    
    public void testEquals() {
        ImmutableInt i1 = new ImmutableInt(14);
        ImmutableInt i2 = i1.set(14);
        ImmutableInt i3 = i2.set(14);

        ImmutableInt i4 = new ImmutableInt(15);

        // Reflexive
        Assert.assertEquals(true, i1.equals(i1));
        Assert.assertEquals(true, i2.equals(i2));
        Assert.assertEquals(true, i4.equals(i4));

        // Symmetric
        Assert.assertEquals(true, i1.equals(i2));
        Assert.assertEquals(true, i2.equals(i1));
        Assert.assertEquals(false, i1.equals(i4));
        Assert.assertEquals(false, i4.equals(i1));


        // Transitive
        Assert.assertEquals(true, i1.equals(i2));
        Assert.assertEquals(true, i2.equals(i3));
        Assert.assertEquals(true, i1.equals(i3));

        Assert.assertEquals(false, i4.equals(i3));
        Assert.assertEquals(true, i1.equals(i3));


        // Consistent
        for (int i=0;i<10;i++) {
            Assert.assertEquals(true, i1.equals(i2));
            Assert.assertEquals(false, i4.equals(i3));
        }
        // Non-Nullity
        Assert.assertEquals(false, i1.equals(null));
        Assert.assertEquals(false, i4.equals(null));
        Assert.assertEquals(false, i2.equals(new Object ()));
    }
}
