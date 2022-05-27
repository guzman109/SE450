package shop.ui;

import shop.data.Data;
import shop.data.Video;

public enum UIMenuActionEnum implements UIMenuAction{
    DEFAULT {
        public void run(UI ui) {
            ui.displayError(("doh!"));            
        }
    }, 
    ADD_REMOVE {
        public void run(UI ui) {
            String[] result1 = ui.processForm(UIForm.VIDEO);
            Video v = Data.newVideo(result1[0], Integer.parseInt(result1[1]), result1[2]);
            
            UIForm f = UIForm.ADD_REMOVE;
        }
    }, 
    CHECK_IN {
        public void run(UI ui) {
            // TODO Auto-generated method stub
            
        }
    },
    CHECK_OUT {
        public void run(UI ui) {
            // TODO Auto-generated method stub
            
        }
    },
    PRINT {
        public void run(UI ui) {
            // TODO Auto-generated method stub
            
        }
    },
    CLEAR {
        public void run(UI ui) {
            // TODO Auto-generated method stub
            
        }
    },
    UNDO {
        public void run(UI ui) {
            // TODO Auto-generated method stub
            
        }
    }, 
    REDO {
        public void run(UI ui) {
            // TODO Auto-generated method stub
            
        }
    }, 
    TOP_TEN {
        public void run(UI ui) {
            // TODO Auto-generated method stub
            
        }
    }, 
    EXIT {
        public void run(UI ui) {
            // TODO Auto-generated method stub
            
        }
    }, 
    INIT {
        public void run(UI ui) {
            // TODO Auto-generated method stub
            
        }
    };

    // public abstract void run(UI ui);
    
}
