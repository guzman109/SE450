package shop.ui;

public interface UIMenuAction {
  public int getState();
  public String getPrompt();
  public void run();
}
