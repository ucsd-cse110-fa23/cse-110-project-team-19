package client;

import client.view.DeleteConfirm;
import client.view.EditRecipeScreen;
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
    scenes.put("recipe", new RecipeScreen(this));
    scenes.put("recordBF", new RecordIngredientScreen(this, "breakfast"));
    scenes.put("recordLN", new RecordIngredientScreen(this, "lunch"));
    scenes.put("recordDR", new RecordIngredientScreen(this, "dinner"));
    scenes.put("edit", new EditRecipeScreen());
    scenes.put("delete", new DeleteConfirm());

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
}
