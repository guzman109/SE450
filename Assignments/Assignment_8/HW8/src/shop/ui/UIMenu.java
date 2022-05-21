package shop.ui;

import java.util.List;

/**
 * @see UIBuilder
 */
final class UIMenu extends UITemplate<String, UIMenuAction>{
  UIMenu(String heading, List<Pair<String, UIMenuAction>> menu) {
    super(heading, menu);
  }
  public void runAction(int i) {
    this._template.get(i).getV().run();
  }
}
