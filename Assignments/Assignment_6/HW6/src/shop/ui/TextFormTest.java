package shop.ui;

public class TextFormTest {
    public static void main(String[] args) {
        UI  _ui = new shop.ui.TextUI(); // initialized to a TextUI

        UIFormTest yearTest = new UIFormTest() {
            public boolean run(String input) {
              try {
                int i = Integer.parseInt(input);
                return i > 1800 && i < 5000;
              } catch (NumberFormatException e) {
                return false;
              }
            }
          };
        UIFormTest _numberTest = new UIFormTest() {
            public boolean run(String input) {
              try {
                Integer.parseInt(input);
                return true;
              } catch (NumberFormatException e) {
                return false;
              }
            }
          };
        UIFormTest _stringTest = new UIFormTest() {
            public boolean run(String input) {
              return ! "".equals(input.trim());
            }
          };
    
          UIFormBuilder f = new UIFormBuilder();
          f.add("Title", _stringTest);
          f.add("Year", yearTest);
          f.add("Director", _stringTest);
          UIForm _getVideoForm = f.toUIForm("Enter Video");
          String[] result2 = _ui.processForm(_getVideoForm);
    }
}
