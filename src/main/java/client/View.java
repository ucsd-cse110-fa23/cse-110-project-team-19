package client;

import client.view.MainMenu.MainMenu;
import client.view.RecipeScreen.RecipeScreen;
import client.view.RecordScreen.RecordIngredientScreen;
import client.view.RecordScreen.RecordMealScreen;
import java.util.HashMap;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class View {

  HashMap<String, BorderPane> scenes;
  Scene scene;
  MainMenu mainMenu = new MainMenu();
  RecipeScreen recipeScreen = new RecipeScreen();
  RecordIngredientScreen recordIngredientScreen = new RecordIngredientScreen();
  String mealType;

  public View() {
    scenes = new HashMap<>();
    scenes.put("main", mainMenu);

    scenes.put(
      "recordMeal",
      new RecordMealScreen(this, "Record the Meal Type for the recipe:")
    );
    scenes.put(
      "recordMealError",
      new RecordMealScreen(this, "Please repeat Meal Type:")
    );
    scenes.put("recipe", this.recipeScreen);
    // scenes.put("edit", new EditRecipeScreen());
    // scenes.put("delete", new DeleteConfirm());

    scene = new Scene(scenes.get("main"), 500, 600);
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
}
