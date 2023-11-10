package client;

import client.controller.MainMenuController;
import client.view.MainMenu.MainMenu;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    View view = new View();
    MainMenu mainMenu = view.mainMenu;
    MainMenuController mainMenuController = new MainMenuController(
      view,
      mainMenu
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
