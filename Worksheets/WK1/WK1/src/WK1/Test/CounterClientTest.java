package WK1.Test;

import org.junit.Assert;
import junit.framework.TestCase;
import WK1.CounterClient;
public class CounterClientTest extends TestCase {
    public CounterClientTest(String name) {
        super(name);
    }

    CounterClient c1 = new CounterClient();

    public void testGet() {
        Assert.assertEquals(0, c1.get());
    }

    public void testSet() {
        c1.set(5);
        Assert.assertEquals(5, c1.get());
    }

    public void testInc() {
        c1.inc();
        Assert.assertEquals(1, c1.get());
        c1.set(6);
        c1.inc();
        Assert.assertEquals(7, c1.get());
    }
}
