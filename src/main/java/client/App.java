package client;

import client.Model;
import client.controller.MainMenuController;
import client.controller.RecipeScreenController;
import client.controller.RecordIngredientPromptController;
import client.controller.RecordIngredientScreenController;
import client.controller.RecordMealScreenController;
import client.controller.RecordMealTypePromptController;
import client.view.MainMenu.MainMenu;
import client.view.RecordScreen.RecordMealScreen;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    View view = new View();
    Model model = new Model();
    new MainMenuController(view, view.mainMenu, model);
    new RecipeScreenController(
      view,
      view.recipeScreen,
      view.mainMenu,
      model,
      null
    );
    new RecipeScreenController(
      view,
      view.recipeScreen,
      view.mainMenu,
      model,
      null
    );
    new RecordMealScreenController(view, view.recordMealScreen);
    new RecordMealTypePromptController(
      view.recordMealScreen.getRecordMealPrompt()
    );
    new RecordMealScreenController(view, view.recordMealScreenError);
    new RecordMealTypePromptController(
      view.recordMealScreenError.getRecordMealPrompt()
    );
    new RecordIngredientScreenController(view, view.recordIngredientScreen);
    new RecordIngredientPromptController(
      view.recordIngredientScreen.getRecordIngredientPrompt()
    );
    //Model model = new Model();
    //Controller controller = new Controller(view, model);

    // Set the title of the app
    primaryStage.setTitle("PantryPal");
    // Create scene of mentioned size with the border pane
    primaryStage.setScene(view.getScene());
    // Make window non-resizable
    primaryStage.setResizable(false);
    // Show the app
    primaryStage.show();
  }
}
