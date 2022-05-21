package shop.main;

import shop.ui.UIFactory;
import shop.ui.UI;
import shop.data.Data;

public class Main {
  private Main() {}
  public static void main(String[] args) {
    UI ui;
    UIFactory factory = new UIFactory();
    if (Math.random() <= 0.5) {
      ui = factory.getUI("TextUI");
    } else {
      ui = factory.getUI("PopupUI");
    }
    Control control = new Control(Data.newInventory(), ui);
    control.run();
  }
}
