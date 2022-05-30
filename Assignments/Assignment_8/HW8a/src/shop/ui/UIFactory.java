package shop.ui;

public class UIFactory {
  public UIFactory() {}
  // static private UI _UI = new PopupUI();
  // static private UI _UI = new TextUI();
  // static public UI ui () {
  //   return _UI;
  // }
  public UI getUI(String ui_type) {
    if (ui_type.equalsIgnoreCase("PopupUI")) {
      return new PopupUI();
    }
    else if (ui_type.equalsIgnoreCase("TextUI")) {
      return new TextUI();
    }
    else {
      return null;
    }
  }

}
