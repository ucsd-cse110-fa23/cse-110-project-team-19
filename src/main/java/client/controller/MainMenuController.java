package client.controller;

import client.View;
import client.model.CreateAccountModel;
import client.model.LoginModel;
import client.model.Model;
import client.view.AccountScreen.*;
import client.view.MainMenu.MainMenu;
import client.view.RecipeScreen.RecipeScreen;
import com.sun.tools.javac.Main;
import java.io.FileWriter;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainMenuController {

  private MainMenu mainMenu;
  private View view;
  private AccountScreen accountScreen;

  public MainMenuController(
    View view,
    MainMenu mainMenu,
    AccountScreen accountScreen
  ) {
    this.mainMenu = mainMenu;
    this.view = view;
    this.accountScreen = accountScreen;

    this.mainMenu.setCreateButtonAction(this::handleCreateButton);
    this.mainMenu.setLogOutButtonAction(this::handleLogOutButton);
    this.mainMenu.setsortButtonAction(this::handlesortButton);
    this.mainMenu.setfilterButtonAction(this::handlefilterButton);
  }

  private void handleCreateButton(ActionEvent event) {
    view.setRoot("recordMeal");
  }

  private void handleLogOutButton(ActionEvent event) {
    accountScreen.getLogin().clearLogin();
    accountScreen.getCreateAccount().clearCreateAccount();
    mainMenu.clearRecipeList();

    try {
      FileWriter fw = new FileWriter("automaticLogin.txt");
      fw.write("false");
      fw.close();
    } catch (Exception e) {}

    view.setRoot("accountScreen");
  }

  private void handlesortButton(ActionEvent event) {
    Stage addStage = new Stage();
    addStage.setTitle("Sort");
    GridPane grid = new GridPane();
    grid.setHgap(10);
    grid.setVgap(10);
    grid.setPadding(new Insets(50, 10, 50, 10));
    addStage.setScene(new Scene(grid, 300, 400));
    addStage.setResizable(false);
    addStage.show();

    Label prompt = new Label("Sort by: ");

    prompt.setStyle(
      "-fx-border-width: 0; -fx-font-weight: bold; -fx-font-size: 15px"
    );
    Button AZButton = new Button("Alphabetical A-Z");
    AZButton.setFocusTraversable(false);
    Button ZAButton = new Button("Alphabetical Z-A");
    AZButton.setFocusTraversable(false);
    Button newcButton = new Button("Chronological new");
    AZButton.setFocusTraversable(false);
    Button oldcButton = new Button("Chronological old");
    AZButton.setFocusTraversable(false);
    Button clearButton = new Button("Clear");
    clearButton.setFocusTraversable(false);

    VBox buttonBox = new VBox(10);
    buttonBox
      .getChildren()
      .addAll(prompt, AZButton, ZAButton, newcButton, oldcButton, clearButton);
    grid.add(buttonBox, 8, 2);

    prompt.setAlignment(Pos.CENTER);
    buttonBox.setAlignment(Pos.CENTER);
    AZButton.setOnAction(e1 -> {
      // hasn't assign anything yet
      addStage.close();
    });

    ZAButton.setOnAction(e1 -> {
      // hasn't assign anything yet
      addStage.close();
    });

    newcButton.setOnAction(e1 -> {
      // hasn't assign anything yet
      addStage.close();
    });

    oldcButton.setOnAction(e1 -> {
      // hasn't assign anything yet
      addStage.close();
    });

    clearButton.setOnAction(e1 -> {
      addStage.close();
    });
  }

  private void handlefilterButton(ActionEvent event) {
    Stage addStage = new Stage();
    addStage.setTitle("Filter");
    GridPane grid = new GridPane();
    grid.setHgap(10);
    grid.setVgap(10);
    grid.setPadding(new Insets(50, 10, 50, 10));
    addStage.setScene(new Scene(grid, 240, 300));
    addStage.setResizable(false);
    addStage.show();

    Label prompt = new Label("Filter by meal type:");

    prompt.setStyle(
      "-fx-border-width: 0; -fx-font-weight: bold; -fx-font-size: 15px"
    );
    Button breakfastButton = new Button("Breakfast");
    breakfastButton.setFocusTraversable(false);
    Button lunchButton = new Button("Lunch");
    lunchButton.setFocusTraversable(false);
    Button dinnerButton = new Button("Dinner");
    dinnerButton.setFocusTraversable(false);
    Button clearButton = new Button("Clear");
    clearButton.setFocusTraversable(false);

    VBox buttonBox = new VBox(10);
    buttonBox
      .getChildren()
      .addAll(prompt, breakfastButton, lunchButton, dinnerButton, clearButton);
    grid.add(buttonBox, 5, 2);

    buttonBox.setAlignment(Pos.CENTER);
    breakfastButton.setOnAction(e1 -> {
      // hasn't assign anything yet
      addStage.close();
    });

    lunchButton.setOnAction(e1 -> {
      // hasn't assign anything yet
      addStage.close();
    });

    dinnerButton.setOnAction(e1 -> {
      // hasn't assign anything yet
      addStage.close();
    });

    clearButton.setOnAction(e1 -> {
      addStage.close();
    });
  }
}
