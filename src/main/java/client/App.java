package client;

import client.controller.AccountScreenController;
import client.controller.MainMenuController;
import client.controller.RecipeScreenController;
import client.controller.RecordIngredientPromptController;
import client.controller.RecordIngredientScreenController;
import client.controller.RecordMealScreenController;
import client.controller.RecordMealTypePromptController;
import client.model.CreateAccountModel;
import client.model.LoginModel;
import client.model.Model;
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
    CreateAccountModel createAccountModel = new CreateAccountModel();
    LoginModel loginModel = new LoginModel();
    new MainMenuController(view, view.mainMenu);
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
    new AccountScreenController(
      view,
      model,
      createAccountModel,
      loginModel,
      view.accountScreen,
      view.mainMenu
    );
    new AccountScreenController(
      view,
      model,
      createAccountModel,
      loginModel,
      view.createAccountError,
      view.mainMenu
    );
    new AccountScreenController(
      view,
      model,
      createAccountModel,
      loginModel,
      view.passwordComfirmError,
      view.mainMenu
    );
    new AccountScreenController(
      view,
      model,
      createAccountModel,
      loginModel,
      view.incorrectPassword,
      view.mainMenu
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
