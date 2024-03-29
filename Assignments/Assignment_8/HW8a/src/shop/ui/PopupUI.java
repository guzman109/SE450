package shop.ui;

import javax.swing.JOptionPane;
//import java.io.IOException;

final class PopupUI implements UI {
  public void displayMessage(String message) {
    JOptionPane.showMessageDialog(null,message);
  }

  public void displayError(String message) {
    JOptionPane.showMessageDialog(null,message,"Error",JOptionPane.ERROR_MESSAGE);
  }

  // public void processMenu(UIMenu menu) {
  public void processMenu(UITemplate<String, UIMenuAction> menu) {
    StringBuilder b = new StringBuilder();
    b.append(menu.getHeading());
    b.append("\n");
    b.append("Enter choice by number:");
    b.append("\n");

    for (int i = 1; i < menu.size(); i++) {
      b.append("  " + i + ". " + menu.getPrompt(i));
      b.append("\n");
    }

    String response = JOptionPane.showInputDialog(b.toString());
    if (response == null) {
      response = "";
    }
    int selection;
    try {
      selection = Integer.parseInt(response, 10);
      if ((selection < 0) || (selection >= menu.size()))
        selection = 0;
    } catch (NumberFormatException e) {
      selection = 0;
    }

    menu.runAction(selection);
  }

  // public String[] processForm(UIForm form) {
  public String[] processForm(UITemplate<String, UIFormTest> form) {
    // TODO
    String[] responses = new String[form.size()];
    int i = 0;
    while (i < form.size()) {
      StringBuilder b = new StringBuilder();
      //Show prompt
      b.append(form.getPrompt(i) + ": ");

      // Get User input
      String response = JOptionPane.showInputDialog(b.toString());
      if (response == null) {
        response = "";
      }
      
      // Check if user input is valid
      if (form.checkInput(i, response)) {
        responses[i] = response;
        i++;
      }
      else {
        displayError("Invalid Input. Please try again.\n");
      }
    }
    return responses;
  }
}
