package shop.ui;

import java.util.ArrayList;
import java.util.List;

import shop.data.Inventory;

// /**
//  * @see UIBuilder
//  */
// final class UIMenu extends UITemplate<String, UIMenuAction>{
//   UIMenu(String heading, List<Pair<String, UIMenuAction>> menu) {
//     super(heading, menu);
//   }
  // public void runAction(int i) {
  //   this._template.get(i).getV().run();
  // }
// }

enum UIMenu implements UITemplate<String, UIMenuAction> {
  MAIN("Carlos's Video", 2) {
    protected void build_menu() {
      for (StartMenuActions a : StartMenuActions.values())
        this._template.add(a);
    }
  };
  private final int STATE;
  private final String _heading;
  protected final List<UIMenuAction> _template = new ArrayList<UIMenuAction>();

  private UIMenu(String heading, int stateNum) {
    this._heading = heading;
    this.STATE = stateNum;
    this.build_menu();
  }

  protected abstract void build_menu();

  public int size() {
    return this._template.size();
  }

  public String getHeading() {
      return this._heading;
  }

  public String getPrompt(int i) {
      return this._template.get(i).getPrompt();
  }

  public void runAction(int i, UI ui, Inventory inventory) {
    this._template.get(i).run(ui, inventory);
  }

  public boolean checkInput(int i, String response) {
    return true;
  }

}
