package shop.ui;

import java.util.List;

/**
 * @see UIBuilder
 */
final class UIForm extends UITemplate<String, UIFormTest>{
  UIForm(String heading, List<Pair<String, UIFormTest>> array) {
    super(heading, array);
  }
  public boolean checkInput(int i, String input) {
    if (null == this._template.get(i))
      return true;
    return this._template.get(i).getV().run(input);
  }
}
