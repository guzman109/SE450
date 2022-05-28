package shop.ui;


import java.util.Comparator;
import java.util.Iterator;

import shop.command.Command;
import shop.data.Data;
import shop.data.Inventory;
import shop.data.Video;
import shop.data.Record;

enum StartMenuActions implements UIMenuAction{
    DEFAULT("Default") {
        public void run(UI ui, Inventory inventory) {
            ui.displayError(("doh!"));            
        }
    }, 
    ADD_REMOVE("Add/Remove copies of a video") {
        public void run(UI ui, Inventory inventory) {
            String[] result1 = ui.processForm(UIForm.VIDEO);
            Video v = Data.newVideo(result1[0], Integer.parseInt(result1[1]), result1[2]);
            
            String[] result2 = ui.processForm(UIForm.ADD_REMOVE);
            Command c = Data.newAddCmd(inventory, v, Integer.parseInt(result2[0]));
            if (! c.run()) {
                ui.displayError("Command failed");
            }
        }
    }, 
    CHECK_IN("Check in a video") {
        public void run(UI ui, Inventory inventory) {
            String[] result = ui.processForm(UIForm.VIDEO);
            Video v = Data.newVideo(result[0], Integer.parseInt(result[1]), result[2]);

            Command c = Data.newInCmd(inventory, v);
            if (! c.run()) {
                ui.displayError("Command failed");
            }
        }
    },
    CHECK_OUT("Check out a video") {
        public void run(UI ui, Inventory inventory) {
            String[] result = ui.processForm(UIForm.VIDEO);
            Video v = Data.newVideo(result[0], Integer.parseInt(result[1]), result[2]);

            Command c = Data.newOutCmd(inventory, v);
            if (! c.run()) {
                ui.displayError("Command failed");
            }
        }
    },
    PRINT("Print the inventory") {
        public void run(UI ui, Inventory inventory) {
            ui.displayMessage(inventory.toString());
        }
    },
    CLEAR("Clear the iventory") {
        public void run(UI ui, Inventory inventory) {
            if (!Data.newClearCmd(inventory).run()) {
                ui.displayError("Command failed");
            }
        }
    },
    UNDO("Undo") {
        public void run(UI ui, Inventory inventory) {
            if (!Data.newUndoCmd(inventory).run()) {
                ui.displayError("Command failed");
            }
        }
    }, 
    REDO("Redo") {
        public void run(UI ui, Inventory inventory) {
            if (!Data.newRedoCmd(inventory).run()) {
                ui.displayError("Command failed");
            }
        }
    }, 
    TOP_TEN("Print top ten all time rentals in order") {
        public void run(UI ui, Inventory inventory) {
            StringBuilder message = new StringBuilder();

            Comparator<Record> comparator = new Comparator<Record>() {
              public int compare(Record r1, Record r2){
                return Integer.compare(r2.numRentals(), r1.numRentals());
  
              }
            };
            Iterator<Record> sorted_inventory = inventory.iterator(comparator);
            int i = 0;
            while (sorted_inventory.hasNext() && i < 10) {
              message.append(sorted_inventory.next().toString() + "\n");
              i++;
            }
            ui.displayMessage(message.toString()); 
        }
    }, 
    EXIT("Exit") {
        public void run(UI ui, Inventory inventory) {
            // TODO Auto-generated method stub
            
        }
    }, 
    INIT("Initialize with dummy contents") {
        public void run(UI ui, Inventory inventory) {
            Data.newAddCmd(inventory, Data.newVideo("a", 2022, "m"), 1).run();
            Data.newAddCmd(inventory, Data.newVideo("b", 2022, "m"), 2).run();
            Data.newAddCmd(inventory, Data.newVideo("c", 2022, "m"), 3).run();
            Data.newAddCmd(inventory, Data.newVideo("d", 2022, "m"), 4).run();
            Data.newAddCmd(inventory, Data.newVideo("e", 2022, "m"), 5).run();
            Data.newAddCmd(inventory, Data.newVideo("f", 2022, "m"), 6).run();
            Data.newAddCmd(inventory, Data.newVideo("g", 2022, "m"), 7).run();
            Data.newAddCmd(inventory, Data.newVideo("h", 2022, "m"), 8).run();
            Data.newAddCmd(inventory, Data.newVideo("i", 2022, "m"), 9).run();
            Data.newAddCmd(inventory, Data.newVideo("j", 2022, "m"), 10).run();
            Data.newAddCmd(inventory, Data.newVideo("k", 2022, "m"), 11).run();
            Data.newAddCmd(inventory, Data.newVideo("l", 2022, "m"), 12).run();
            Data.newAddCmd(inventory, Data.newVideo("m", 2022, "m"), 13).run();
            Data.newAddCmd(inventory, Data.newVideo("n", 2022, "m"), 14).run();
            Data.newAddCmd(inventory, Data.newVideo("o", 2022, "m"), 15).run();
            Data.newAddCmd(inventory, Data.newVideo("p", 2022, "m"), 16).run();
            Data.newAddCmd(inventory, Data.newVideo("q", 2022, "m"), 17).run();
            Data.newAddCmd(inventory, Data.newVideo("r", 2022, "m"), 18).run();
            Data.newAddCmd(inventory, Data.newVideo("s", 2022, "m"), 19).run();
            Data.newAddCmd(inventory, Data.newVideo("t", 2022, "m"), 20).run();
            Data.newAddCmd(inventory, Data.newVideo("u", 2022, "m"), 21).run();
            Data.newAddCmd(inventory, Data.newVideo("v", 2022, "m"), 22).run();
            Data.newAddCmd(inventory, Data.newVideo("w", 2022, "m"), 23).run();
            Data.newAddCmd(inventory, Data.newVideo("x", 2022, "m"), 24).run();
            Data.newAddCmd(inventory, Data.newVideo("y", 2022, "m"), 25).run();
            Data.newAddCmd(inventory, Data.newVideo("z", 2022, "m"), 26).run();
          }
    };
    private final String _prompt;
    private StartMenuActions(String prompt) {
        this._prompt = prompt;
    }
    public String getPrompt() {
        return this._prompt;
    }
    // public abstract void run(UI ui, Inventory inventory);
}
