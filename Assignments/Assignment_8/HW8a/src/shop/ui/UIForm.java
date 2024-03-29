package shop.ui;

import java.util.ArrayList;
import java.util.List;

public enum UIForm implements UITemplate<String, UIFormTest>{
  VIDEO("Enter Video") {
    protected void build_form() {
      this._template.add(new Pair<String, UIFormTest> ("Video", UIFormTestEnum.STRING));
      this._template.add(new Pair<String, UIFormTest> ("Year", UIFormTestEnum.YEAR));
      this._template.add(new Pair<String, UIFormTest> ("Director", UIFormTestEnum.STRING));
    }
  },
  ADD_REMOVE("") {
    protected void build_form() {
      this._template.add(new Pair<String, UIFormTest> ("Number of copies to add/remove", UIFormTestEnum.NUMBER));
    }
  };

  private final String _heading;
  protected final List<Pair<String, UIFormTest>> _template = new ArrayList<Pair<String, UIFormTest>>();

  private UIForm(String heading) {
    this._heading = heading;
    this.build_form();
  };
  protected abstract void build_form();
  
  public int size() {
    return this._template.size();
  }

  public String getHeading() {
      return this._heading;
  }

  public String getPrompt(int i) {
      return this._template.get(i).getU();
  }

  public boolean checkInput(int i, String response) {
    if (null == this._template.get(i))
      return true;
    return this._template.get(i).getV().run(response);
  }

  public void runAction(int selection) {
    ;    
  }
}