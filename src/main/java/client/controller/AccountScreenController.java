package client.controller;

import client.View;
import client.model.CreateAccountModel;
import client.model.LoginModel;
import client.model.Model;
import client.model.RecipeImage;
import client.view.AccountScreen.AccountScreen;
import client.view.MainMenu.MainMenu;
import client.view.MainMenu.Recipe;
import java.io.FileWriter;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import server.MyServer;

public class AccountScreenController {

  private View view;
  private Recipe recipe;
  private AccountScreen accountScreen;
  private MainMenu mainMenu;
  private CreateAccountModel createAccountModel;
  private Model model;
  private LoginModel loginModel;
  private RecipeImage recipeImage;

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

    if (response.equals("Not Valid Username")) {
      view.setRoot("invalidUsername");
      view.setInvalidUsernameScreen();
      return;
    } else if (response.equals("Incorrect Password")) {
      // error handling
      view.setRoot("incorrectPassword");
      view.setIncorrectPasswordScreen();
      return;
    }

    if (accountScreen.getLogin().automaticLogin()) {
      try {
        FileWriter fw = new FileWriter("automaticLogin.txt");
        fw.write("true\n");
        fw.write(username);
        fw.close();
      } catch (Exception e) {}
    }

    view.setUsername(username);

    String query = username;
    if (MyServer.isServerRunning()) {
      response = model.performRequest("GET", null, null, query);
    } else {
      view.setRoot("serverDown");
      return;
    }
    if (response != null) {
      String[] recipes = response.split("~");
      //System.out.println(recipes);
      for (String recipeContent : recipes) {
        recipe = new Recipe(view);
        recipe.setRecipe(
          recipeContent.substring(0, recipeContent.indexOf("|"))
        );
        recipe.setTime();

        //String recipeName = recipeContent.replaceAll("(?m)^[ \t]*\r?\n", "");
        String recipeName = recipeContent.substring(
          0,
          recipeContent.indexOf('\n')
        );
        String mealType = recipeContent.substring(
          recipeContent.indexOf("|") + 1
        );

        recipe.getRecipeName().setText(recipeName);
        try {
          recipeImage = new RecipeImage();
          recipeImage.NewImage(recipeName);
        } catch (Exception e1) {}
        recipe.setImageURL(recipeImage.getURL());

        recipe.setMealTypeTag(mealType);
        mainMenu.getRecipeList().getChildren().add(0, recipe);
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
    String response = "";
    if (MyServer.isServerRunning()) {
      response =
        createAccountModel.performRequest("POST", username, password, null);
    } else {
      view.setRoot("serverDown");
      return;
    }

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
