package shop.ui;

import java.util.ArrayList;
import java.util.List;

final class UIFormBuilder implements UIBuilder<String, UIFormTest, UITemplate<String, UIFormTest>> {
    protected final List<Pair<String, UIFormTest>> _menu = new ArrayList<Pair<String, UIFormTest>>();

    public void add(String u, UIFormTest v) {
        _menu.add(new Pair<String,UIFormTest>(u, v));
    }
    public UIForm toUITemplate(String heading) {
        if (null == heading)
        throw new IllegalArgumentException();
        if (this._menu.size() < 1)
        throw new IllegalStateException();

        return new UIForm(heading, this._menu);
    }
}

final class UIMenuBuilder implements UIBuilder<String, UIMenuAction, UITemplate<String, UIMenuAction>> {
    protected final List<Pair<String, UIMenuAction>> _menu = new ArrayList<Pair<String, UIMenuAction>>();

    public void add(String u, UIMenuAction v) {
        _menu.add(new Pair<String,UIMenuAction>(u, v));
    }
    public UIMenu toUITemplate(String heading) {
        if (null == heading)
        throw new IllegalArgumentException();
        if (this._menu.size() < 1)
        throw new IllegalStateException();

        return new UIMenu(heading, this._menu);
    }
}


