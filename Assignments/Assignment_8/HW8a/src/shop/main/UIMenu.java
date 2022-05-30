package shop.main;

import java.util.ArrayList;
import java.util.List;

import shop.data.Inventory;
import shop.ui.UI;
import shop.ui.UIMenuAction;
import shop.ui.UITemplate;

enum UIMenu implements UITemplate<String, UIMenuAction> {
    MAIN("Carlos's Video", 2) {
        protected void build_menu(UI ui, Inventory inventory) {
            for (StartMenuActions action : StartMenuActions.values()) {
                action.setUI(ui);
                action.setInventory(inventory);
                this._template.add(action);
            }
        }
    },
    EXIT("Are you sure you want to exit?", 1) {
        protected void build_menu(UI ui, Inventory inventory) {
            for (ExitMenuActions action : ExitMenuActions.values()) {
                this._template.add(action);
            }
        }
    };
    private final String _heading;
    protected final List<UIMenuAction> _template = new ArrayList<UIMenuAction>();
    private int STATE;
    
    private UIMenu(String heading, int stateNum) {
        this._heading = heading;
        this.STATE = stateNum;
    }

    protected abstract void build_menu(UI ui, Inventory inventory);


    public int getState() {
        return this.STATE;
    }

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
        this.STATE = this._template.get(i).getState();
    }

    public boolean checkInput(int i, String response) {
        return true;
    }
}
