package client.controller;

import client.View;
import client.model.CreateAccountModel;
import client.model.LoginModel;
import client.model.Model;
import client.view.AccountScreen.AccountScreen;
import client.view.MainMenu.MainMenu;
import client.view.MainMenu.Recipe;
import javafx.event.ActionEvent;

public class AccountScreenController {

  private View view;
  private Recipe recipe;
  private AccountScreen accountScreen;
  private MainMenu mainMenu;
  private CreateAccountModel createAccountModel;
  private Model model;
  private LoginModel loginModel;

  public AccountScreenController(
    View view,
    Model model,
    CreateAccountModel createAccountModel,
    LoginModel loginModel,
    AccountScreen accountScreen,
    MainMenu mainMenu
  ) {
    this.accountScreen = accountScreen;
    this.view = view;
    this.mainMenu = mainMenu;
    this.createAccountModel = createAccountModel;
    this.model = model;
    this.loginModel = loginModel;

    accountScreen.setLoginButtonAction(this::handleLogin);
    accountScreen.setCreateAccountAction(this::handleCreateAccount);
    accountScreen.setLoginSwitchAction(this::handleLoginSwitch);
    accountScreen.setCreateSwitchAction(this::handleCreateSwitch);
    accountScreen.switchToCreate();
  }

  private void handleLogin(ActionEvent event) {
    String username = accountScreen.getLogin().getUsername();
    String password = accountScreen.getLogin().getPassword();

    String response = loginModel.performRequest(
      "GET",
      null,
      null,
      username + "," + password
    );

    if (response.equals("Incorrect Password")) {
      // error handling
      view.setRoot("incorrectPassword");
      return;
    }

    view.setUsername(username);

    String query = username;
    response = model.performRequest("GET", null, null, query);
    if (response != null) {
      String[] recipes = response.split("~");
      for (String recipeContent : recipes) {
        recipe = new Recipe(view);
        recipe.setRecipe(recipeContent);

        //String recipeName = recipeContent.replaceAll("(?m)^[ \t]*\r?\n", "");
        String recipeName = recipeContent.substring(
          0,
          recipeContent.indexOf('\n')
        );

        recipe.getRecipeName().setText(recipeName);
        mainMenu.getRecipeList().getChildren().add(recipe);
        new RecipeScreenController(
          view,
          view.recipeScreen,
          mainMenu,
          model,
          recipe
        );
      }
    }
    view.setRoot("main");
  }

  private void handleCreateAccount(ActionEvent event) {
    String username = accountScreen.getCreateAccount().getUsername();
    String password = accountScreen.getCreateAccount().getPassword();
    String confirmPassword = accountScreen
      .getCreateAccount()
      .getPasswordConfirmation();

    if (!password.equals(confirmPassword)) {
      // some error handling
      view.setRoot("passwordConfirmError");
      return;
    }
    String response = createAccountModel.performRequest(
      "POST",
      username,
      password,
      null
    );

    if (response.equals("Duplicate Username")) {
      // some error handling
      view.setRoot("createAccountError");
      return;
    }
    view.setUsername(username);
    view.setRoot("main");
  }

  private void handleLoginSwitch(ActionEvent event) {
    accountScreen.switchToLogin();
  }

  private void handleCreateSwitch(ActionEvent event) {
    accountScreen.switchToCreate();
  }
}
