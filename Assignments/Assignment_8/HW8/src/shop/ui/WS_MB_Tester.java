package shop.ui;

public class WS_MB_Tester {

    public static void main(String[] args) {
    // TODO Auto-generated method stub
      final State _state = new State();
      UI  _ui = new shop.ui.TextUI(); // initialized to a TextUI


      UIMenuBuilder mb = new UIMenuBuilder();
        
      mb.add("Default", new UIMenuAction() { 
          public void run() {
              _ui.displayError("Yo, Proper Input Please");
          } 
      });
      
      mb.add("Yes", new UIMenuAction() {
          public void run() {
              _state.set(2);
          }
      });
      
      mb.add("No", new UIMenuAction() {
          public void run() {
              _state.set(0);
          }
      });
      
      UIMenu m = mb.toUITemplate("Are you sure you want to exit?");

      
      UIMenuBuilder mb2 = new UIMenuBuilder();
        
      mb2.add("Default", new UIMenuAction() { 
          public void run() {
              _ui.displayError("Yo, Proper Input Please");
          } 
      });
      
      mb2.add("Yes", new UIMenuAction() {
          public void run() {
              _state.set(1);
          }
      });
          
      UIMenu m2 = mb.toUITemplate("Go to choosing exit?");
          
          
      UIMenu[] _menus = new UIMenu[2];
      _menus[0] = m2;
      _menus[1] = m;

      while (_state.get() != 2) {
          _ui.processMenu(_menus[_state.get()]);
      }
    }
}

class State{
    private int x;
    
    int get() {
        return x;
    }
    
    void set(int y) {
        x = y;
    }
}