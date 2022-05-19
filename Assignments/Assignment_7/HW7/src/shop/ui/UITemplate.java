package shop.ui;

import java.util.List;

public abstract class UITemplate<U,V> {
    private final String _heading;
    protected final List<Pair<U,V>> _template;

    UITemplate(String heading, List<Pair<U,V>> array) {
        this._heading = heading;
        this._template = array;
    }

    public int size() {
        return this._template.size();
    }

    public String getHeading() {
        return this._heading;
    }

    public U getPrompt(int i) {
        return this._template.get(i).getU();
    }

    public void runAction(int selection) {
    }

    public boolean checkInput(int i, String response) {
        return true;
    }
}
