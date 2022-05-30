package shop.main;

import shop.ui.UI;
import shop.ui.UIError;
import shop.ui.UIMenuAction;
import shop.ui.UITemplate;
import shop.ui.UIForm;
import shop.data.Data;
import shop.data.Inventory;
import shop.data.Video;
import shop.data.Record;
import shop.command.Command;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

class Control {
  
private static final int EXITED = 0;
  private static final int EXIT = 1;
  private static final int START = 2;
  private static final int NUMSTATES = 10;
  private List<UIMenu> _menus;
  private static int _state;
    
  private static Inventory _inventory;
  private static UI _ui;
  
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
      }
    } catch (UIError e) {
      _ui.displayError("UI closed");
    }
  }
  
  private void addSTART(int stateNum) {
    _menus.set(stateNum, UIMenu.MAIN);
  }
  private void addEXIT(int stateNum) {
    _menus.set(stateNum, UIMenu.EXIT);
  }


  

  private enum UIMenu implements UITemplate<String, UIMenuAction> {
    MAIN("Carlos's Video", 2) {
      protected void build_menu() {
        for (StartMenuActions a : StartMenuActions.values())
          this._template.add(a);
      }
    },
    EXIT("Are you sure you want to exit?", 1) {
      protected void build_menu() {
      for (ExitMenuActions a : ExitMenuActions.values())
        this._template.add(a);
      }
    };
    private final String _heading;
    protected final List<UIMenuAction> _template = new ArrayList<UIMenuAction>();

    private UIMenu(String heading, int stateNum) {
      this._heading = heading;
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

    public void runAction(int i) {
      this._template.get(i).run();
    }

    public boolean checkInput(int i, String response) {
      return true;
    }
  }

}