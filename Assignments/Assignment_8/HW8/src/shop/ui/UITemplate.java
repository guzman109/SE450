package shop.ui;

import shop.data.Inventory;

public interface UITemplate<U,V> {
    public int size();
    public U getPrompt(int i);
    public void runAction(int i, UI ui, Inventory inventory);
    public boolean checkInput(int i, String response);
}
