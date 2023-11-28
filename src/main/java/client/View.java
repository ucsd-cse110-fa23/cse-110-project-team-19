package client;

import client.controller.RecipeScreenController;
import client.controller.ViewController;
import client.model.Model;
import client.model.Model;
import client.model.Model;
import client.view.AccountScreen.AccountScreen;
import client.view.MainMenu.MainMenu;
import client.view.MainMenu.Recipe;
import client.view.RecipeScreen.DetailedRecipeView;
import client.view.RecipeScreen.RecipeScreen;
import client.view.RecordScreen.RecordIngredientScreen;
import client.view.RecordScreen.RecordMealScreen;
import client.view.ServerScreen.ServerStatus;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import server.MyServer;

public class View {

  HashMap<String, BorderPane> scenes;
  Scene scene;
  public MainMenu mainMenu = new MainMenu();
  public RecipeScreen recipeScreen = new RecipeScreen(true);
  public ServerStatus serverStatus = new ServerStatus();
  RecordIngredientScreen recordIngredientScreen = new RecordIngredientScreen();
  RecordMealScreen recordMealScreen = new RecordMealScreen(
    this,
    "Record the Meal Type for the recipe: \n (Breakfast, Lunch, or Dinner)"
  );
  RecordMealScreen recordMealScreenError = new RecordMealScreen(
    this,
    "Please repeat Meal Type: \n (Say Breakfast, Lunch, or Dinner)"
  );
  String mealType;
  AccountScreen accountScreen = new AccountScreen("", "");
  AccountScreen createAccountError = new AccountScreen(
    "Username already in use, please choose another username.",
    ""
  );
  AccountScreen passwordComfirmError = new AccountScreen(
    "Password confirmation failed, please try again.",
    ""
  );
  AccountScreen incorrectPassword = new AccountScreen(
    "",
    "Incorrect password, please try again."
  );
  AccountScreen invalidUsername = new AccountScreen(
    "",
    "Username does not exist for any account, please try again."
  );
  String username;

  //boolean successfulCreation;

  public View() {
    scenes = new HashMap<>();

    scenes.put("main", mainMenu);

    scenes.put("recipe", this.recipeScreen);

    scenes.put("recordMeal", this.recordMealScreen);

    scenes.put("recordMealError", this.recordMealScreenError);

    scenes.put("accountScreen", this.accountScreen);

    scenes.put("serverDown", this.serverStatus);

    scenes.put("createAccountError", this.createAccountError);

    scenes.put("passwordConfirmError", this.passwordComfirmError);

    scenes.put("incorrectPassword", this.incorrectPassword);

    scenes.put("invalidUsername", this.invalidUsername);

    try {
      FileReader fr = new FileReader("automaticLogin.txt");
      BufferedReader br = new BufferedReader(fr);
      String line = br.readLine();
      if (line.equals("false")) {
        scene = new Scene(scenes.get("accountScreen"), 500, 600);
      } else if (line.equals("true")) {
        username = br.readLine();
        String query = username;
        Model model = new Model();
        String response = model.performRequest("GET", null, null, query);
        if (response != null) {
          String[] recipes = response.split("~");
          for (String recipeContent : recipes) {
            Recipe recipe = new Recipe(this);
            recipe.setRecipe(recipeContent);

            String recipeName = recipeContent.substring(
              0,
              recipeContent.indexOf('\n')
            );

            recipe.getRecipeName().setText(recipeName);
            mainMenu.getRecipeList().getChildren().add(recipe);
            new RecipeScreenController(
              this,
              recipeScreen,
              mainMenu,
              model,
              recipe
            );
          }
        }
        scene = new Scene(scenes.get("main"), 500, 600);
      }
      br.close();
    } catch (Exception e) {}
  }

  public BorderPane getRoot(String key) {
    return scenes.get(key);
  }

  public void setRoot(String key) {
    scene.setRoot(scenes.get(key));
  }

  public Scene getScene() {
    return scene;
  }

  public void setMealType(String type) {
    this.mealType = type;
    scene.setRoot(recordIngredientScreen);
  }

  public String getMealType() {
    return this.mealType;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getUsername() {
    return this.username;
  }

  public void setInvalidUsernameScreen() {
    this.invalidUsername.switchToLogin();
  }

  public void setIncorrectPasswordScreen() {
    this.incorrectPassword.switchToLogin();
  }
}
