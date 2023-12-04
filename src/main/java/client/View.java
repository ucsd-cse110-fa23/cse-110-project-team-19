package client;

import client.controller.RecipeScreenController;
import client.controller.ViewController;
import client.model.Model;
import client.controller.RecipeScreenController;
import client.model.Model;
import client.view.AccountScreen.AccountScreen;
import client.view.MainMenu.MainMenu;
import client.view.MainMenu.Recipe;
import client.view.RecipeScreen.DetailedRecipeView;
import client.view.RecipeScreen.RecipeScreen;
import client.view.RecordScreen.RecordIngredientScreen;
import client.view.RecordScreen.RecordMealScreen;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class View {

  HashMap<String, BorderPane> scenes;
  Scene scene;
  public MainMenu mainMenu = new MainMenu();
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
  AccountScreen accountScreen = new AccountScreen();
  String username;

  public View() {
    scenes = new HashMap<>();

    scenes.put("main", mainMenu);

    scenes.put("recipe", this.recipeScreen);

    scenes.put("recordMeal", this.recordMealScreen);

    scenes.put("recordMealError", this.recordMealScreenError);

    scenes.put("accountScreen", this.accountScreen);

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
}
