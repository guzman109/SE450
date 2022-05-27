package shop.ui;

public interface UITemplate<U,V> {
    public int size();
    public String getHeading();
    public U getPrompt(int i);
    public void runAction(int selection);
    public boolean checkInput(int i, String response);
}
