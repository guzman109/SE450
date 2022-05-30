package shop.ui;

final class Pair<U,V> {
    private final U u;
    private final V v;

    Pair (U u, V v) {
        this.u = u;
        this.v = v;
    }

    public U getU() {
        return u;
    }

    public V getV() {
        return v;
    }
}
