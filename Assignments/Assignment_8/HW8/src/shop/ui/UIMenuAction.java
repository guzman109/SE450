package shop.ui;
import shop.data.Inventory;

public interface UIMenuAction {
  public String getPrompt();
  public void run(UI ui, Inventory inventory);
}
