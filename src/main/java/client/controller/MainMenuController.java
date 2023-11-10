package client.controller;

import client.View;
import client.view.MainMenu.MainMenu;
import javafx.event.ActionEvent;

public class MainMenuController {

  private MainMenu mainMenu;
  private View view;

  public MainMenuController(View view, MainMenu mainMenu) {
    this.mainMenu = mainMenu;
    this.view = view;

    this.mainMenu.setCreateButtonAction(this::handleCreateButton);
  }

  private void handleCreateButton(ActionEvent event) {
    view.setRoot("recordMeal");
  }
}
