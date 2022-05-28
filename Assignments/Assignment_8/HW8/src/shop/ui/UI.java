package shop.ui;


public interface UI {
  public void displayMessage(String message);
  public void displayError(String message);
  public String[] processForm(UITemplate<String, UIFormTest> uiTemplate);
  public void processMenu(UITemplate<String, UIMenuAction> uiTemplate);
}