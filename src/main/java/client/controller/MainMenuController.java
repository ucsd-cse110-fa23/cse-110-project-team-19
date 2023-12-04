package client.controller;

import client.View;
import client.model.CompareAlphabetical;
import client.model.CompareChrono;
import client.model.CreateAccountModel;
import client.model.LoginModel;
import client.model.Model;
import client.view.AccountScreen.*;
import client.view.MainMenu.*;
import client.view.MainMenu.MainMenu;
import client.view.RecipeScreen.RecipeScreen;
import com.sun.tools.javac.Main;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Collections;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
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
    ZAButton.setFocusTraversable(false);
    Button newcButton = new Button("Newest to Oldest");
    newcButton.setFocusTraversable(false);
    Button oldcButton = new Button("Oldest to Newest");
    oldcButton.setFocusTraversable(false);
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
      sortContacts("A-Z");
      mainMenu.getHeader().switchToAlphaAZ();
      addStage.close();
    });

    ZAButton.setOnAction(e1 -> {
      // hasn't assign anything yet
      sortContacts("Z-A");
      mainMenu.getHeader().switchToAlphaZA();
      addStage.close();
    });

    newcButton.setOnAction(e1 -> {
      // hasn't assign anything yet
      sortContacts("new");
      mainMenu.getHeader().switchToChronoNewOld();
      addStage.close();
    });

    oldcButton.setOnAction(e1 -> {
      // hasn't assign anything yet
      sortContacts("old");
      mainMenu.getHeader().switchToChronoOldNew();
      addStage.close();
    });

    clearButton.setOnAction(e1 -> {
      mainMenu.getHeader().switchToClear();
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
      mainMenu.getHeader().switchToBreakfast();
      addStage.close();
    });

    lunchButton.setOnAction(e1 -> {
      // hasn't assign anything yet
      mainMenu.getHeader().switchToLunch();
      addStage.close();
    });

    dinnerButton.setOnAction(e1 -> {
      // hasn't assign anything yet
      mainMenu.getHeader().switchToDinner();
      addStage.close();
    });

    clearButton.setOnAction(e1 -> {
      mainMenu.getHeader().switchToClear();
      addStage.close();
    });
  }

  public void sortContacts(String str) {
    ArrayList<Recipe> recipes = new ArrayList<>();
    for (int i = 0; i < mainMenu.getRecipeList().getChildren().size(); i++) {
      if (mainMenu.getRecipeList().getChildren().get(i) instanceof Recipe) {
        recipes.add(((Recipe) mainMenu.getRecipeList().getChildren().get(i)));
      }
    }

    if (str.equals("A-Z")) {
      Collections.sort(recipes, new CompareAlphabetical());
    } else if (str.equals("Z-A")) {
      Collections.sort(recipes, (new CompareAlphabetical()).reversed());
    } else if (str.equals("new")) {
      Collections.sort(recipes, (new CompareChrono()).reversed());
    } else if (str.equals("old")) {
      Collections.sort(recipes, (new CompareChrono()));
    }
    mainMenu.getRecipeList().getChildren().clear();
    for (int i = 0; i < recipes.size(); i++) {
      this.mainMenu.getRecipeList().getChildren().add((Node) recipes.get(i));
    }
  }
}
