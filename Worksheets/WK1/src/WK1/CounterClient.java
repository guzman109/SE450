package WK1;

import WK1.Interface.Counter;

public class CounterClient implements Counter {
    private int counter;
    @Override
    public int get() {
        return this.counter;
    }

    @Override
    public void set(int x) {
        this.counter = x;
    }

    @Override
    public void inc() {
        this.counter++;
    }

}
