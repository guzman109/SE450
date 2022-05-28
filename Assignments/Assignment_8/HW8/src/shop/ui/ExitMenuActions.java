package shop.ui;


import java.util.Comparator;
import java.util.Iterator;

import shop.command.Command;
import shop.data.Data;
import shop.data.Inventory;
import shop.data.Video;
import shop.data.Record;

enum ExitMenuActions {
    DEFAULT("Default") {
        public int run () {
            return 1;    
        }
    },
    YES("Yes") {
        public int run() {
            return 0;
        }
    },
    NO("No") {
        public int run() {
            return 2;
        }
    };
    private final String _prompt;
    private ExitMenuActions(String prompt) {
        this._prompt = prompt;
    }
    public String getPrompt() {
        return this._prompt;
    }
    public abstract int run();
}
