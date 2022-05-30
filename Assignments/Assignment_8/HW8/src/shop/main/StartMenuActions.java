package shop.main;

import java.util.Comparator;
import java.util.Iterator;

import shop.command.Command;
import shop.data.Data;
import shop.data.Inventory;
import shop.data.Record;
import shop.data.Video;
import shop.ui.UI;
import shop.ui.UIForm;
import shop.ui.UIMenuAction;

enum StartMenuActions implements UIMenuAction{
    DEFAULT("Default") {
        public void run() {
            this.ui.displayError(("doh!"));            
        }
    }, 
    ADD_REMOVE("Add/Remove copies of a video") {
        public void run() {
            String[] result1 = this.ui.processForm(UIForm.VIDEO);
            Video v = Data.newVideo(result1[0], Integer.parseInt(result1[1]), result1[2]);
            
            String[] result2 = this.ui.processForm(UIForm.ADD_REMOVE);
            Command c = Data.newAddCmd(this.inventory, v, Integer.parseInt(result2[0]));
            if (! c.run()) {
                this.ui.displayError("Command failed");
            }
        }
    }, 
    CHECK_IN("Check in a video") {
        public void run() {
            String[] result = this.ui.processForm(UIForm.VIDEO);
            Video v = Data.newVideo(result[0], Integer.parseInt(result[1]), result[2]);

            Command c = Data.newInCmd(this.inventory, v);
            if (! c.run()) {
                this.ui.displayError("Command failed");
            }
        }
    },
    CHECK_OUT("Check out a video") {
        public void run() {
            String[] result = this.ui.processForm(UIForm.VIDEO);
            Video v = Data.newVideo(result[0], Integer.parseInt(result[1]), result[2]);

            Command c = Data.newOutCmd(this.inventory, v);
            if (! c.run()) {
                this.ui.displayError("Command failed");
            }
        }
    },
    PRINT("Print the inventory") {
        public void run() {
            this.ui.displayMessage(this.inventory.toString());
        }
    },
    CLEAR("Clear the inventory") {
        public void run() {
            if (!Data.newClearCmd(this.inventory).run()) {
                this.ui.displayError("Command failed");
            }
        }
    },
    UNDO("Undo") {
        public void run() {
            if (!Data.newUndoCmd(this.inventory).run()) {
                this.ui.displayError("Command failed");
            }
        }
    }, 
    REDO("Redo") {
        public void run() {
            if (!Data.newRedoCmd(this.inventory).run()) {
                this.ui.displayError("Command failed");
            }
        }
    }, 
    TOP_TEN("Print top ten all time rentals in order") {
        public void run() {
            StringBuilder message = new StringBuilder();

            Comparator<Record> comparator = new Comparator<Record>() {
              public int compare(Record r1, Record r2){
                return Integer.compare(r2.numRentals(), r1.numRentals());
  
              }
            };
            Iterator<Record> sorted_inventory = this.inventory.iterator(comparator);
            int i = 0;
            while (sorted_inventory.hasNext() && i < 10) {
              message.append(sorted_inventory.next().toString() + "\n");
              i++;
            }
            this.ui.displayMessage(message.toString()); 
        }
    }, 
    QUIT("Exit") {
        public void run() {
          this.STATE = 1;
        }
    }, 
    INIT("Initialize with dummy contents") {
        public void run() {
            Data.newAddCmd(this.inventory, Data.newVideo("a", 2022, "m"), 1).run();
            Data.newAddCmd(this.inventory, Data.newVideo("b", 2022, "m"), 2).run();
            Data.newAddCmd(this.inventory, Data.newVideo("c", 2022, "m"), 3).run();
            Data.newAddCmd(this.inventory, Data.newVideo("d", 2022, "m"), 4).run();
            Data.newAddCmd(this.inventory, Data.newVideo("e", 2022, "m"), 5).run();
            Data.newAddCmd(this.inventory, Data.newVideo("f", 2022, "m"), 6).run();
            Data.newAddCmd(this.inventory, Data.newVideo("g", 2022, "m"), 7).run();
            Data.newAddCmd(this.inventory, Data.newVideo("h", 2022, "m"), 8).run();
            Data.newAddCmd(this.inventory, Data.newVideo("i", 2022, "m"), 9).run();
            Data.newAddCmd(this.inventory, Data.newVideo("j", 2022, "m"), 10).run();
            Data.newAddCmd(this.inventory, Data.newVideo("k", 2022, "m"), 11).run();
            Data.newAddCmd(this.inventory, Data.newVideo("l", 2022, "m"), 12).run();
            Data.newAddCmd(this.inventory, Data.newVideo("m", 2022, "m"), 13).run();
            Data.newAddCmd(this.inventory, Data.newVideo("n", 2022, "m"), 14).run();
            Data.newAddCmd(this.inventory, Data.newVideo("o", 2022, "m"), 15).run();
            Data.newAddCmd(this.inventory, Data.newVideo("p", 2022, "m"), 16).run();
            Data.newAddCmd(this.inventory, Data.newVideo("q", 2022, "m"), 17).run();
            Data.newAddCmd(this.inventory, Data.newVideo("r", 2022, "m"), 18).run();
            Data.newAddCmd(this.inventory, Data.newVideo("s", 2022, "m"), 19).run();
            Data.newAddCmd(this.inventory, Data.newVideo("t", 2022, "m"), 20).run();
            Data.newAddCmd(this.inventory, Data.newVideo("u", 2022, "m"), 21).run();
            Data.newAddCmd(this.inventory, Data.newVideo("v", 2022, "m"), 22).run();
            Data.newAddCmd(this.inventory, Data.newVideo("w", 2022, "m"), 23).run();
            Data.newAddCmd(this.inventory, Data.newVideo("x", 2022, "m"), 24).run();
            Data.newAddCmd(this.inventory, Data.newVideo("y", 2022, "m"), 25).run();
            Data.newAddCmd(this.inventory, Data.newVideo("z", 2022, "m"), 26).run();
          }
    };
    private final String _prompt;
    protected UI ui;
    protected Inventory inventory;
    protected int STATE = 2;
    private StartMenuActions(String prompt) {
        this._prompt = prompt;
    }
    public String getPrompt() {
        return this._prompt;
    }

    public void setUI(UI ui) {
      this.ui = ui;
    }
    public void setInventory(Inventory inventory) {
      this.inventory = inventory;
    }
    public int getState() {
        return this.STATE;
    }
}
