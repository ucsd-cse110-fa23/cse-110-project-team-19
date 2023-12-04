package client.controller;

import com.sun.tools.javac.Main;

import client.View;
import client.model.CreateAccountModel;
import client.model.LoginModel;
import client.model.Model;
import client.view.MainMenu.MainMenu;
import client.view.RecipeScreen.RecipeScreen;
import javafx.scene.control.TextField;
import client.view.AccountScreen.*;

import javafx.event.ActionEvent;

public class MainMenuController {

  private MainMenu mainMenu;
  private View view;
  private AccountScreen accountScreen;

  

  

  public MainMenuController(View view, MainMenu mainMenu, AccountScreen accountScreen) {
    this.mainMenu = mainMenu;
    this.view = view;
    this.accountScreen = accountScreen;
    


    this.mainMenu.setCreateButtonAction(this::handleCreateButton);
    this.mainMenu.setLogOutButtonAction(this::handleLogOutButton);
  }

  private void handleCreateButton(ActionEvent event) {
    view.setRoot("recordMeal");
  }

  private void handleLogOutButton(ActionEvent event) {

    
    accountScreen.getLogin().clearLogin();
    accountScreen.getCreateAccount().clearCreateAccount();
    mainMenu.clearRecipeList();
    
    view.setRoot("accountScreen");
    

    
  }
}
