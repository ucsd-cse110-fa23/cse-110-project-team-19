package client;

import client.view.AccountScreen.AccountScreen;
import client.view.MainMenu.MainMenu;
import client.view.RecipeScreen.DetailedRecipeView;
import client.view.RecipeScreen.RecipeScreen;
import client.view.RecordScreen.RecordIngredientScreen;
import client.view.RecordScreen.RecordMealScreen;
import java.util.HashMap;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class View {

  HashMap<String, BorderPane> scenes;
  Scene scene;
  MainMenu mainMenu = new MainMenu();
  public RecipeScreen recipeScreen = new RecipeScreen(true);
  RecordIngredientScreen recordIngredientScreen = new RecordIngredientScreen();
  RecordMealScreen recordMealScreen = new RecordMealScreen(
    this,
    "Record the Meal Type for the recipe:"
  );
  RecordMealScreen recordMealScreenError = new RecordMealScreen(
    this,
    "Please repeat Meal Type:"
  );
  String mealType;
  AccountScreen accountScreen = new AccountScreen("", "");
  AccountScreen createAccountError = new AccountScreen(
    "Username already in use, please choose another username.", "");
  AccountScreen passwordComfirmError = new AccountScreen(
    "Password confirmation failed, please try again.", "");
  AccountScreen incorrectPassword = new AccountScreen(
    "", "Incorrect password, please try again.");
  String username;

  public View() {
    scenes = new HashMap<>();

    scenes.put("main", mainMenu);

    scenes.put("recipe", this.recipeScreen);

    scenes.put("recordMeal", this.recordMealScreen);

    scenes.put("recordMealError", this.recordMealScreenError);

    scenes.put("accountScreen", this.accountScreen);
    
    scenes.put("createAccountError", this.createAccountError);

    scenes.put("passwordConfirmError", this.passwordComfirmError);

    scenes.put("incorrectPassword", this.incorrectPassword);

    scene = new Scene(scenes.get("accountScreen"), 500, 600);
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

  public void setIncorrectPasswordScreen() {
    this.incorrectPassword.switchToLogin();
  }
}
