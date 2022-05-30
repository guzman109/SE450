package shop.main;

import shop.data.Inventory;
import shop.ui.UI;
import shop.ui.UIMenuAction;

enum ExitMenuActions implements UIMenuAction{
    DEFAULT("Default") {
        public void run () {
            this.STATE = 1;    
        }
    },
    YES("Yes") {
        public void run() {
            this.STATE = 0;
        }
    },
    NO("No") {
        public void run() {
            this.STATE = 2;
        }
    };
    private final String _prompt;
    protected int STATE;

    private ExitMenuActions(String prompt) {
        this._prompt = prompt;
    }
    public String getPrompt() {
        return this._prompt;
    }

    public int getState() {
        return this.STATE;
    }
}
