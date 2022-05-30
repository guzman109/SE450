package shop.main;

import shop.ui.UI;
import shop.ui.UIError;

import shop.data.Inventory;

import java.util.List;
import java.util.ArrayList;

class Control {
  
private static final int EXITED = 0;
  private static final int EXIT = 1;
  private static final int START = 2;
  private static final int NUMSTATES = 10;
  private List<UIMenu> _menus;
  private static int _state;
    
  private Inventory _inventory;
  private UI _ui;
  
  Control(Inventory inventory, UI ui) {
    _inventory = inventory;
    _ui = ui;

    _menus = new ArrayList<UIMenu> ();
    for (int i = 0; i < NUMSTATES; i++) {
      _menus.add(null);
    }
    _state = START;
    addSTART(START);
    addEXIT(EXIT);
  }
  
  void run() {
    try {
      while (_state != EXITED) {
        _ui.processMenu(_menus.get(_state));
        _state = _menus.get(_state).getState();
      }
    } catch (UIError e) {
      _ui.displayError("UI closed");
    }
  }
  
  private void addSTART(int stateNum) {
    UIMenu menu = UIMenu.MAIN;
    menu.setAttributes(_ui, _inventory);
    _menus.set(stateNum, menu);
  }
  private void addEXIT(int stateNum) {
    UIMenu menu = UIMenu.EXIT;
    menu.setAttributes(_ui, _inventory);
    _menus.set(stateNum, menu);
  }
}